package br.edu.fatecfranca.projetopoo.rpgcharactercreation.controller;

import br.edu.fatecfranca.projetopoo.rpgcharactercreation.model.dto.request.CombatClassDTORequest;
import br.edu.fatecfranca.projetopoo.rpgcharactercreation.model.dto.response.CombatClassDTOResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/combat-class")
public interface CombatClassController {
  @PostMapping
  ResponseEntity<CombatClassDTOResponse> save(@Validated @RequestBody CombatClassDTORequest request);

  @GetMapping
  ResponseEntity<List<CombatClassDTOResponse>> getAll();

  @GetMapping("/{id}")
  ResponseEntity<CombatClassDTOResponse> getById(@PathVariable Long id);

  @GetMapping("/search")
  ResponseEntity<List<CombatClassDTOResponse>> findByName(@RequestParam String name);

  @PutMapping("/{id}")
  ResponseEntity<CombatClassDTOResponse> update(@Validated @RequestBody CombatClassDTORequest request, @PathVariable Long id);

  @PatchMapping("{id}/change/name")
  ResponseEntity<CombatClassDTOResponse> changeName(@PathVariable Long id, @RequestParam String name);

  @DeleteMapping("/{id}")
  ResponseEntity<Void> delete(@PathVariable Long id);
}
