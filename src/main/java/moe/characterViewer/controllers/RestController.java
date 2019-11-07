package moe.characterViewer.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * RestController
 */
@Controller
public class RestController {

	@PostMapping(value = "saveList")
	public String saveList(@RequestBody String list) {
		return "";
	}

}
