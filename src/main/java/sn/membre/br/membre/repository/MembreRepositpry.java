package sn.membre.br.membre.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sn.membre.br.membre.entity.MembreEntity;
import sn.membre.br.membre.model.Membre;
@Repository
public interface MembreRepositpry extends CrudRepository<MembreEntity,Long> {
}
