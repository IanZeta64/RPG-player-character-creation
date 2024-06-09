package br.edu.fatecfranca.projetopoo.rpgcharactercreation.controller;

import br.edu.fatecfranca.projetopoo.rpgcharactercreation.model.dto.request.RaceDTORequest;
import br.edu.fatecfranca.projetopoo.rpgcharactercreation.model.dto.response.RaceDTOResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/race")
public interface RaceController {
  @PostMapping
  ResponseEntity<RaceDTOResponse> save(@Validated @RequestBody RaceDTORequest request);

  @GetMapping
  ResponseEntity<List<RaceDTOResponse>> getAll();

  @GetMapping("/{id}")
  ResponseEntity<RaceDTOResponse> getById(@PathVariable Long id);

  @GetMapping("/search")
  ResponseEntity<List<RaceDTOResponse>> findByName(@RequestParam String name);

  @PutMapping("/{id}")
  ResponseEntity<RaceDTOResponse> update(@Validated @RequestBody RaceDTORequest request, @PathVariable Long id);

  @PatchMapping("{id}/change/name")
  ResponseEntity<RaceDTOResponse> changeName(@PathVariable Long id, @RequestParam String name);

  @DeleteMapping("/{id}")
  ResponseEntity<Void> delete(@PathVariable Long id);
}
