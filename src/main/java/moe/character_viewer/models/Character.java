package moe.character_viewer.models;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * Character
 */
@Data
public class Character {

	private String name = "";

	private String origin = "";

	private String imageUrl = "";

	private List<Character> variants = new ArrayList<Character>();

	private List<Character> partners = new ArrayList<Character>();
}
