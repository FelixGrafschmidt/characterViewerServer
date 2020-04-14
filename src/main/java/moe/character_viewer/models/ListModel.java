package moe.character_viewer.models;

import java.util.List;

import org.springframework.data.redis.core.RedisHash;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("CharacterList")
public class ListModel {
	private String id;
	private String name;
	private List<Character> characters;
}
