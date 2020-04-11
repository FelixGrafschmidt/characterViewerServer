package moe.character_viewer.models;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.redis.core.RedisHash;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("CharacterList")
public class CharacterListModel {
	private List<Character> characters = new ArrayList<>();
	private String id;
}
