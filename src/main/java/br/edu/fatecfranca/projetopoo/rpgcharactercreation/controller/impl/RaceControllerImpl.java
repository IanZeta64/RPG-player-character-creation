package br.edu.fatecfranca.projetopoo.rpgcharactercreation.controller.impl;

import br.edu.fatecfranca.projetopoo.rpgcharactercreation.controller.RaceController;
import br.edu.fatecfranca.projetopoo.rpgcharactercreation.model.dto.request.RaceDTORequest;
import br.edu.fatecfranca.projetopoo.rpgcharactercreation.model.dto.response.RaceDTOResponse;
import br.edu.fatecfranca.projetopoo.rpgcharactercreation.service.RaceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
public class RaceControllerImpl implements RaceController {
  private final RaceService service;

  public RaceControllerImpl(RaceService service) {
    this.service = service;
  }

  @Override
  public ResponseEntity<RaceDTOResponse> save(RaceDTORequest request) {
    var response = service.save(request);
    return ResponseEntity.created(URI.create("/race/" + response.id())).body(response);
  }

  @Override
  public ResponseEntity<List<RaceDTOResponse>> getAll() {
    return ResponseEntity.ok(service.getAll());
  }

  @Override
  public ResponseEntity<RaceDTOResponse> getById(Long id) {
    return ResponseEntity.ok(service.getById(id));
  }

  @Override
  public ResponseEntity<List<RaceDTOResponse>> findByName(String name) {
    return ResponseEntity.ok(service.findByName(name));
  }

  @Override
  public ResponseEntity<RaceDTOResponse> update(RaceDTORequest request, Long id) {
    return ResponseEntity.accepted().body(service.update(id, request));
  }

  @Override
  public ResponseEntity<RaceDTOResponse> changeName(Long id, String name) {
    return ResponseEntity.accepted().body(service.changeName(id, name));
  }

  @Override
  public ResponseEntity<Void> delete(Long id) {
    service.delete(id);
    return ResponseEntity.noContent().build();
  }
}
