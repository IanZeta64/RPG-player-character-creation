package br.edu.fatecfranca.projetopoo.rpgcharactercreation.service.impl;

import br.edu.fatecfranca.projetopoo.rpgcharactercreation.exceptions.EntityDuplicatedException;
import br.edu.fatecfranca.projetopoo.rpgcharactercreation.exceptions.EntityNotFoundException;
import br.edu.fatecfranca.projetopoo.rpgcharactercreation.model.dto.request.RaceDTORequest;
import br.edu.fatecfranca.projetopoo.rpgcharactercreation.model.dto.response.RaceDTOResponse;
import br.edu.fatecfranca.projetopoo.rpgcharactercreation.model.entity.RaceEntity;
import br.edu.fatecfranca.projetopoo.rpgcharactercreation.repository.RaceRepository;
import br.edu.fatecfranca.projetopoo.rpgcharactercreation.service.RaceService;
import org.springframework.stereotype.Service;

import java.util.List;

import static br.edu.fatecfranca.projetopoo.rpgcharactercreation.utils.GlobalConstants.RACE;

@Service
public class RaceServiceImpl implements RaceService {
  private final RaceRepository repository;

  public RaceServiceImpl(RaceRepository repository) {
    this.repository = repository;
  }

  @Override
  public RaceDTOResponse save(RaceDTORequest request) {
    if (repository.existsByName(request.name())) {
      throw new EntityDuplicatedException(RACE);
    }
    return repository.save(request.toEntity()).toResponse();
  }

  @Override
  public List<RaceDTOResponse> getAll() {
    return repository.findAll().stream().map(RaceEntity::toResponse).toList();
  }

  @Override
  public RaceDTOResponse getById(Long id) {
    return repository.findById(id)
      .orElseThrow(() -> new EntityNotFoundException(RACE, id.toString()))
      .toResponse();
  }

  @Override
  public List<RaceDTOResponse> findByName(String name) {
    return repository.findByNameStartingWith(name).stream().map(RaceEntity::toResponse).toList();
  }

  @Override
  public RaceDTOResponse update(Long id, RaceDTORequest request) {
    var entityUpdated = repository.findById(id)
      .map(entity -> entity.update(request.toEntity()))
      .orElseThrow(() -> new EntityNotFoundException(RACE, id.toString()));
    return repository.save(entityUpdated).toResponse();
  }

  @Override
  public RaceDTOResponse changeName(Long id, String name) {
    var entity = repository.findById(id)
      .orElseThrow(() -> new EntityNotFoundException(RACE, id.toString()));
    entity.setName(name);
    return repository.save(entity).toResponse();
  }

  @Override
  public void delete(Long id) {
    var entity = repository.findById(id)
      .orElseThrow(() -> new EntityNotFoundException(RACE, id.toString()));
    repository.delete(entity);
  }
}
