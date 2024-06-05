package br.edu.fatecfranca.projetopoo.rpgcharactercreation.service.impl;

import br.edu.fatecfranca.projetopoo.rpgcharactercreation.exceptions.EntityDuplicatedException;
import br.edu.fatecfranca.projetopoo.rpgcharactercreation.exceptions.EntityNotFoundException;
import br.edu.fatecfranca.projetopoo.rpgcharactercreation.model.dto.request.CombatClassDTORequest;
import br.edu.fatecfranca.projetopoo.rpgcharactercreation.model.dto.response.CombatClassDTOResponse;
import br.edu.fatecfranca.projetopoo.rpgcharactercreation.model.entity.CombatClassEntity;
import br.edu.fatecfranca.projetopoo.rpgcharactercreation.repository.CombatClassRepository;
import br.edu.fatecfranca.projetopoo.rpgcharactercreation.service.CombatClassService;
import org.springframework.stereotype.Service;

import java.util.List;

import static br.edu.fatecfranca.projetopoo.rpgcharactercreation.utils.GlobalConstants.COMBAT_CLASS;

@Service
public class CombatClassServiceImpl implements CombatClassService {

  private final CombatClassRepository repository;

  public CombatClassServiceImpl(CombatClassRepository repository) {
    this.repository = repository;
  }

  @Override
  public CombatClassDTOResponse save(CombatClassDTORequest request) {
    if (repository.existsByName(request.name())) {
      throw new EntityDuplicatedException(COMBAT_CLASS);
    }
    return repository.save(request.toEntity()).toResponse();
  }

  @Override
  public List<CombatClassDTOResponse> getAll() {
    return repository.findAll().stream().map(CombatClassEntity::toResponse).toList();
  }

  @Override
  public CombatClassDTOResponse getById(Long id) {
    return repository.findById(id)
      .orElseThrow(() -> new EntityNotFoundException(COMBAT_CLASS, id.toString()))
      .toResponse();
  }

  @Override
  public List<CombatClassDTOResponse> findByName(String name) {
    return repository.findByNameStartingWith(name).stream().map(CombatClassEntity::toResponse).toList();
  }

  @Override
  public CombatClassDTOResponse update(Long id, CombatClassDTORequest request) {
    var entityUpdated = repository.findById(id)
      .map(entity -> entity.update(request.toEntity()))
      .orElseThrow(() -> new EntityNotFoundException(COMBAT_CLASS, id.toString()));
    return repository.save(entityUpdated).toResponse();
  }

  @Override
  public CombatClassDTOResponse changeName(Long id, String name) {
    var entity = repository.findById(id)
      .orElseThrow(() -> new EntityNotFoundException(COMBAT_CLASS, id.toString()));
    entity.setName(name);
    return repository.save(entity).toResponse();
  }

  @Override
  public void delete(Long id) {
    var entity = repository.findById(id)
      .orElseThrow(() -> new EntityNotFoundException(COMBAT_CLASS, id.toString()));
    repository.delete(entity);
  }
}
