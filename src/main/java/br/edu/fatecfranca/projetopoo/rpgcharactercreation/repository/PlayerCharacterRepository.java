package br.edu.fatecfranca.projetopoo.rpgcharactercreation.repository;

import br.edu.fatecfranca.projetopoo.rpgcharactercreation.model.entity.PlayerCharacterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PlayerCharacterRepository extends JpaRepository<PlayerCharacterEntity, UUID> {
  boolean existsByName(String name);

  List<PlayerCharacterEntity> findByNameStartingWith(String name);
}
