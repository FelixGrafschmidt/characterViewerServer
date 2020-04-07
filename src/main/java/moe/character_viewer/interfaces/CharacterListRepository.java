package moe.character_viewer.interfaces;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import moe.character_viewer.models.CharacterListModel;

@Repository
public interface CharacterListRepository extends CrudRepository<CharacterListModel, String> {
}
