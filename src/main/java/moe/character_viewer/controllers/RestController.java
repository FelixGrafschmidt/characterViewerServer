package moe.character_viewer.controllers;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import moe.character_viewer.interfaces.CharacterListRepository;
import moe.character_viewer.models.Character;
import moe.character_viewer.models.CharacterListModel;

/**
 * RestController
 */
@Controller
@ResponseBody
public class RestController {

	@Autowired
	private CharacterListRepository repo;

	@PostMapping(value = { "/saveList",
			"/saveList/{id}" }, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
	public String saveList(@RequestBody List<Character> characters, HttpServletResponse response,
			@PathVariable(required = false) String id) {
		if (id == null) {
			id = UUID.randomUUID().toString();
			var characterList = new CharacterListModel(characters, id);
			repo.save(characterList);
		} else {
			var characterList = repo.findById(id).orElse(new CharacterListModel());
			characterList.setCharacters(characters);
			characterList.setId(id);
			repo.save(characterList);
		}
		return id;
	}

	@GetMapping(value = "/getList/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Character> getList(@PathVariable String id) {

		CharacterListModel characterList = repo.findById(id).orElse(new CharacterListModel());

		return characterList.getCharacters();
	}
}
