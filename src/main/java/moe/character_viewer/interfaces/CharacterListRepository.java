package moe.character_viewer.interfaces;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import moe.character_viewer.models.ListModel;

@Repository
public interface CharacterListRepository extends CrudRepository<ListModel, String> {
}
