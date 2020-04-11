package moe.character_viewer.interfaces;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import moe.character_viewer.models.Collection;

@Repository
public interface CollectionRepository extends CrudRepository<Collection, String> {
}
