package br.edu.fatecfranca.projetopoo.rpgcharactercreation.controller.impl;

import br.edu.fatecfranca.projetopoo.rpgcharactercreation.controller.PlayerCharacterController;
import br.edu.fatecfranca.projetopoo.rpgcharactercreation.model.dto.request.PlayerCharacterDTORequest;
import br.edu.fatecfranca.projetopoo.rpgcharactercreation.model.dto.response.PlayerCharacterDTOResponse;
import br.edu.fatecfranca.projetopoo.rpgcharactercreation.service.PlayerCharacterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
public class PlayerCharacterControllerImpl implements PlayerCharacterController {

  private final PlayerCharacterService service;

  public PlayerCharacterControllerImpl(PlayerCharacterService service) {
    this.service = service;
  }

  @Override
  public ResponseEntity<PlayerCharacterDTOResponse> save(PlayerCharacterDTORequest request) {
    var response = service.save(request);
    return ResponseEntity.created(URI.create("/player-character/" + response.id())).body(response);
  }

  @Override
  public ResponseEntity<List<PlayerCharacterDTOResponse>> getAll() {
    return ResponseEntity.ok(service.getAll());
  }

  @Override
  public ResponseEntity<PlayerCharacterDTOResponse> getById(String uuid) {
    return ResponseEntity.ok(service.getById(uuid));
  }

  @Override
  public ResponseEntity<List<PlayerCharacterDTOResponse>> findByName(String name) {
    return ResponseEntity.ok(service.findByName(name));
  }

  @Override
  public ResponseEntity<PlayerCharacterDTOResponse> levelUp(String uuid) {
    return ResponseEntity.accepted().body(service.levelUp(uuid));
  }

  @Override
  public ResponseEntity<PlayerCharacterDTOResponse> update(PlayerCharacterDTORequest request, String uuid) {
    return ResponseEntity.accepted().body(service.update(uuid, request));
  }

  @Override
  public ResponseEntity<PlayerCharacterDTOResponse> changeName(String uuid, String name) {
    return ResponseEntity.accepted().body(service.changeName(uuid, name));
  }

  @Override
  public ResponseEntity<PlayerCharacterDTOResponse> changeAttribute(String uuid, String attribute, Integer value) {
    return ResponseEntity.accepted().body(service.changeAttribute(uuid, attribute, value));
  }

  @Override
  public ResponseEntity<PlayerCharacterDTOResponse> changeRace(String uuid, Long raceId) {
    return ResponseEntity.accepted().body(service.changeRace(uuid, raceId));
  }

  @Override
  public ResponseEntity<PlayerCharacterDTOResponse> changeCombatClass(String uuid, Long combatClassId) {
    return ResponseEntity.accepted().body(service.changeCombatClass(uuid, combatClassId));
  }

  @Override
  public ResponseEntity<Void> delete(String uuid) {
    service.delete(uuid);
    return ResponseEntity.noContent().build();
  }
}
