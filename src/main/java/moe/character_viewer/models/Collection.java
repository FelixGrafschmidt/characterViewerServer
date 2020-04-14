package moe.character_viewer.models;

import java.util.List;

import org.springframework.data.redis.core.RedisHash;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@RedisHash("Collection")
@Data
public class Collection {

	private String id;

	private List<ListModel> lists;

}
