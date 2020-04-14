package moe.character_viewer.models;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

/**
 * Character
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Character {

	private String name = "";

	private String origin = "";

	private String imageUrl = "";

	private List<Character> variants = new ArrayList<>();

	private List<Character> partners = new ArrayList<>();
}
