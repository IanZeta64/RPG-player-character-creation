package br.edu.fatecfranca.projetopoo.rpgcharactercreation.repository;

import br.edu.fatecfranca.projetopoo.rpgcharactercreation.model.entity.RaceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RaceRepository extends JpaRepository<RaceEntity, Long> {
   boolean existsByName(String name);
   List<RaceEntity> findByNameStartingWith(String name);
}
