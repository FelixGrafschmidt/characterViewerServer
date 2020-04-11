package moe.character_viewer.models;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.data.redis.core.RedisHash;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@RedisHash("Collection")
public class Collection extends ArrayList<ListModel> {
	private static final long serialVersionUID = 4669031138114889257L;

	@JsonIgnore
	private String id;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
