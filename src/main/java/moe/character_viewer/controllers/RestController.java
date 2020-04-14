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
import moe.character_viewer.models.Collection;
import moe.character_viewer.models.ListModel;

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

	@PostMapping(value = "/saveList/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
	public String saveList(@RequestBody ListModel list, HttpServletResponse response, @PathVariable String id) {
		var characterList = characterRepository.findById(id).orElse(new ListModel());
		characterList.setCharacters(list.getCharacters() != null ? list.getCharacters() : List.of());
		characterList.setId(id);
		characterList.setName(list.getName());
		characterRepository.save(characterList);
		return id;
	}

	@GetMapping(value = "/getList/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ListModel getList(@PathVariable String id, HttpServletResponse response) {

		ListModel characterList = characterRepository.findById(id).orElse(new ListModel());

		if (characterList.getId() == null) {
			response.setStatus(HttpStatus.NOT_FOUND.value());
		} else if (characterList.getCharacters() == null) {
			characterList.setCharacters(List.of());
		}

		return characterList;
	}

	@PostMapping(value = "/saveCollection/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
	public String saveCollection(@RequestBody Collection collection, @PathVariable String id) {
		var loadedCollection = collectionRepository.findById(id).orElse(new Collection());
		loadedCollection.setLists(collection.getLists());
		loadedCollection.setId(id);
		collectionRepository.save(loadedCollection);
		return id;
	}

	@GetMapping(value = "/getCollection/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Collection getCollection(@PathVariable String id, HttpServletResponse response) {

		var collection = collectionRepository.findById(id).orElse(new Collection());

		if (collection.getLists() == null) {
			collection.setLists(List.of());
		}

		if (collection.getId() == null) {
			response.setStatus(HttpStatus.NOT_FOUND.value());
		}

		return collection;
	}

}
