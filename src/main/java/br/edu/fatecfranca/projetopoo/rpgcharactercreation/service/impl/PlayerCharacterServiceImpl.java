package br.edu.fatecfranca.projetopoo.rpgcharactercreation.service.impl;

import br.edu.fatecfranca.projetopoo.rpgcharactercreation.exceptions.EntityDuplicatedException;
import br.edu.fatecfranca.projetopoo.rpgcharactercreation.exceptions.EntityNotFoundException;
import br.edu.fatecfranca.projetopoo.rpgcharactercreation.model.dto.request.PlayerCharacterDTORequest;
import br.edu.fatecfranca.projetopoo.rpgcharactercreation.model.dto.response.PlayerCharacterDTOResponse;
import br.edu.fatecfranca.projetopoo.rpgcharactercreation.model.entity.PlayerCharacterEntity;
import br.edu.fatecfranca.projetopoo.rpgcharactercreation.model.enums.AttributeEnum;
import br.edu.fatecfranca.projetopoo.rpgcharactercreation.repository.CombatClassRepository;
import br.edu.fatecfranca.projetopoo.rpgcharactercreation.repository.PlayerCharacterRepository;
import br.edu.fatecfranca.projetopoo.rpgcharactercreation.repository.RaceRepository;
import br.edu.fatecfranca.projetopoo.rpgcharactercreation.service.PlayerCharacterService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static br.edu.fatecfranca.projetopoo.rpgcharactercreation.utils.GlobalConstants.*;

@Service
public class PlayerCharacterServiceImpl implements PlayerCharacterService {
  private final PlayerCharacterRepository characterRepository;
  private final RaceRepository raceRepository;
  private final CombatClassRepository combatClassRepository;

  public PlayerCharacterServiceImpl(PlayerCharacterRepository characterRepository, RaceRepository raceRepository, CombatClassRepository combatClassRepository) {
    this.characterRepository = characterRepository;
    this.raceRepository = raceRepository;
    this.combatClassRepository = combatClassRepository;
  }

  @Override
  public PlayerCharacterDTOResponse save(PlayerCharacterDTORequest request) {
    if (characterRepository.existsByName(request.name())) {
      throw new EntityDuplicatedException(PLAYER_CHARACTER);
    }
    var race = raceRepository.findById(request.raceId()).orElseThrow(() -> new EntityNotFoundException(RACE, request.raceId().toString()));
    var combatClass = combatClassRepository.findById(request.combatClassId()).orElseThrow(() -> new EntityNotFoundException(COMBAT_CLASS, request.combatClassId().toString()));
    return characterRepository.save(request.toEntity(race, combatClass)).toResponse();
  }

  @Override
  public List<PlayerCharacterDTOResponse> getAll() {
    return characterRepository.findAll().stream().map(PlayerCharacterEntity::toResponse).toList();
  }

  @Override
  public PlayerCharacterDTOResponse getById(String uuid) {
    return characterRepository.findById(UUID.fromString(uuid))
      .orElseThrow(() -> new EntityNotFoundException(PLAYER_CHARACTER, uuid))
      .toResponse();
  }

  @Override
  public List<PlayerCharacterDTOResponse> findByName(String name) {
    return characterRepository.findByNameStartingWith(name).stream().map(PlayerCharacterEntity::toResponse).toList();
  }

  @Override
  public PlayerCharacterDTOResponse update(String uuid, PlayerCharacterDTORequest request) {
    var entity = characterRepository.findById(UUID.fromString(uuid))
      .orElseThrow(() -> new EntityNotFoundException(PLAYER_CHARACTER, uuid));

    if(!Objects.equals(entity.getRace().getId(), request.raceId())){
      entity.setRace(raceRepository.findById(request.raceId())
        .orElseThrow(() -> new EntityNotFoundException(RACE, request.raceId().toString())));
    }

    if(!Objects.equals(entity.getCombatClass().getId(), request.combatClassId())){
      entity.setCombatClass(combatClassRepository.findById(request.combatClassId())
        .orElseThrow(() -> new EntityNotFoundException(COMBAT_CLASS, request.combatClassId().toString())));
    }

   var entityUpdated = entity.update(request.toEntity(entity.getRace(), entity.getCombatClass()));
    return characterRepository.save(entityUpdated).toResponse();
  }

  @Override
  public PlayerCharacterDTOResponse changeName(String uuid, String name) {
    var entity = characterRepository.findById(UUID.fromString(uuid))
      .orElseThrow(() -> new EntityNotFoundException(PLAYER_CHARACTER, uuid));
    entity.setName(name);
    return characterRepository.save(entity).toResponse();
  }

  @Override
  public PlayerCharacterDTOResponse levelUp(String uuid) {
    var entity = characterRepository.findById(UUID.fromString(uuid))
      .orElseThrow(() -> new EntityNotFoundException(PLAYER_CHARACTER, uuid));
    var oldLevel = entity.getLevel();
    var oldHitPoints = entity.getHitPoints();
    var hpDice = oldHitPoints / oldLevel;
    entity.setLevel(Math.min(oldLevel+1, MAX_LEVEL));
    entity.setHitPoints(hpDice * entity.getLevel());
    return characterRepository.save(entity).toResponse();
  }

  @Override
  public PlayerCharacterDTOResponse changeAttribute(String uuid, String attribute, Integer value) {
    var entity = characterRepository.findById(UUID.fromString(uuid))
      .orElseThrow(() -> new EntityNotFoundException(PLAYER_CHARACTER, uuid));
    entity.getAttributes().put(AttributeEnum.valueOf(attribute), value);
    return characterRepository.save(entity).toResponse();
  }

  @Override
  public PlayerCharacterDTOResponse changeRace(String uuid, Long raceId) {
    var playerCharacter = characterRepository.findById(UUID.fromString(uuid))
      .orElseThrow(() -> new EntityNotFoundException(PLAYER_CHARACTER, uuid));
    var race = raceRepository.findById(raceId).orElseThrow(() -> new EntityNotFoundException(RACE, raceId.toString()));
    playerCharacter.setRace(race);
    return characterRepository.save(playerCharacter).toResponse();
  }

  @Override
  public PlayerCharacterDTOResponse changeCombatClass(String uuid, Long combatClassId) {
    var playerCharacter = characterRepository.findById(UUID.fromString(uuid))
      .orElseThrow(() -> new EntityNotFoundException(PLAYER_CHARACTER, uuid));
    var combatClass = combatClassRepository.findById(combatClassId).orElseThrow(() -> new EntityNotFoundException(COMBAT_CLASS, combatClassId.toString()));
    playerCharacter.setCombatClass(combatClass);
    return characterRepository.save(playerCharacter).toResponse();
  }

  @Override
  public void delete(String uuid) {
    var entity = characterRepository.findById(UUID.fromString(uuid))
      .orElseThrow(() -> new EntityNotFoundException(RACE, uuid));
    characterRepository.delete(entity);
  }
}
