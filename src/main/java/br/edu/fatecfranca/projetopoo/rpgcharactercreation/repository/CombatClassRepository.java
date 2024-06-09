package br.edu.fatecfranca.projetopoo.rpgcharactercreation.repository;

import br.edu.fatecfranca.projetopoo.rpgcharactercreation.model.entity.CombatClassEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CombatClassRepository extends JpaRepository<CombatClassEntity, Long> {
  boolean existsByName(String name);

  List<CombatClassEntity> findByNameStartingWith(String name);
}
