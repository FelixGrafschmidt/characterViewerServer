package moe.character_viewer.controllers;

import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import org.bson.Document;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import moe.character_viewer.models.CharacterList;
import moe.character_viewer.models.Result;

/**
 * RestController
 */
@Controller
@ResponseBody
public class RestController {

	private static MongoClient client;
	private static MongoDatabase db;
	private static MongoCollection<Document> collection;

	private static ObjectMapper objectMapper;

	static {
		client = MongoClients.create("mongodb://127.0.0.1:27017");
		db = client.getDatabase("characterlists");
		collection = db.getCollection("characterlists");

		objectMapper = new ObjectMapper();
	}

	@PostMapping(value = "/saveList")
	public String saveList(@RequestBody CharacterList characters, HttpServletResponse response) {

		Result result = new Result();
		result.setStatus("error");

		try {
			int id = characters.get_id();
			if (id == 0) {
				id = UUID.randomUUID().hashCode();
				characters.set_id(id);
				collection.insertOne(Document.parse(objectMapper.writeValueAsString(characters)));
			} else {
				collection.findOneAndReplace(Filters.eq("_id", id),
						Document.parse(objectMapper.writeValueAsString(characters)));
			}
			result.setStatus("success");
			result.set_id(id);
		} catch (JsonMappingException e) {
			response.setStatus(500);
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			response.setStatus(500);
			e.printStackTrace();
		}

		return result.toString();
	}

	@GetMapping(value = "getList", params = "id")
	public String getList(@RequestParam int id) {

		Document doc = collection.find(Filters.eq("_id", id)).first();
		return doc.toJson();
	}
}
