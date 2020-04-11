package moe.character_viewer.controllers;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import moe.character_viewer.interfaces.CharacterListRepository;
import moe.character_viewer.interfaces.CollectionRepository;
import moe.character_viewer.models.Character;
import moe.character_viewer.models.CharacterListModel;
import moe.character_viewer.models.Collection;

/**
 * RestController
 */
@Controller
@ResponseBody
public class RestController {

	@Autowired
	private CharacterListRepository characterRepository;

	@Autowired
	private CollectionRepository collectionRepository;

	@PostMapping(value = "/saveCharacters/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
	public String saveCharacters(@RequestBody List<Character> characters, HttpServletResponse response,
			@PathVariable String id) {
		var characterList = characterRepository.findById(id).orElse(new CharacterListModel());
		characterList.setCharacters(characters);
		characterList.setId(id);
		characterRepository.save(characterList);
		return id;
	}

	@GetMapping(value = "/getCharacters/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Character> getCharacters(@PathVariable String id, HttpServletResponse response) {

		CharacterListModel characterList = characterRepository.findById(id).orElse(new CharacterListModel());

		if (characterList.getCharacters().isEmpty()) {
			response.setStatus(HttpStatus.NOT_FOUND.value());
		}

		return characterList.getCharacters();
	}

	@PostMapping(value = "/saveCollection/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
	public String saveCollection(@RequestBody Collection collection, @PathVariable String id) {
		var loadedCollection = collectionRepository.findById(id).orElse(new Collection());
		loadedCollection.clear();
		loadedCollection.addAll(collection);
		loadedCollection.setId(id);
		collectionRepository.save(loadedCollection);
		return id;
	}

	@GetMapping(value = "/getCollection/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Collection getCollection(@PathVariable String id, HttpServletResponse response) {

		var collection = collectionRepository.findById(id).orElse(new Collection());

		if (collection.isEmpty()) {
			response.setStatus(HttpStatus.NOT_FOUND.value());
		}

		return collection;
	}

}
