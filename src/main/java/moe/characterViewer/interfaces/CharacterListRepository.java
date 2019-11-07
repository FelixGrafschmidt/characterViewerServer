package moe.characterViewer.interfaces;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterListRepository extends CrudRepository<String, String> {
}
