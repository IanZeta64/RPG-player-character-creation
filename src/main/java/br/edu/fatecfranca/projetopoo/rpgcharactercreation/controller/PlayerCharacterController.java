package br.edu.fatecfranca.projetopoo.rpgcharactercreation.controller;

import br.edu.fatecfranca.projetopoo.rpgcharactercreation.model.dto.request.PlayerCharacterDTORequest;
import br.edu.fatecfranca.projetopoo.rpgcharactercreation.model.dto.response.PlayerCharacterDTOResponse;
import br.edu.fatecfranca.projetopoo.rpgcharactercreation.model.enums.AttributeEnum;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/player-character")
public interface PlayerCharacterController {
  @PostMapping
  ResponseEntity<PlayerCharacterDTOResponse> save(@Validated @RequestBody PlayerCharacterDTORequest request);
  @GetMapping
  ResponseEntity<List<PlayerCharacterDTOResponse>> getAll();

  @GetMapping("/{uuid}")
  ResponseEntity<PlayerCharacterDTOResponse> getById(@PathVariable String uuid);

  @GetMapping("/search")
  ResponseEntity<List<PlayerCharacterDTOResponse>> findByName(@RequestParam String name);

  @PatchMapping("/level-up/{uuid}")
  ResponseEntity<PlayerCharacterDTOResponse> levelUp(@PathVariable String uuid);

  @PutMapping("/{uuid}")
  ResponseEntity<PlayerCharacterDTOResponse> update(@Validated @RequestBody PlayerCharacterDTORequest request, @PathVariable String uuid);

  @PatchMapping("/change/name/{uuid}")
  ResponseEntity<PlayerCharacterDTOResponse> changeName(@PathVariable String uuid, @RequestParam String name);

  @PatchMapping("/change/bonus/{uuid}")
  ResponseEntity<PlayerCharacterDTOResponse> changeAttribute(@PathVariable String uuid,
                                                             @Validated @RequestParam(name = "attribute") @Pattern(regexp = "^(STRENGTH|DEXTERITY|CONSTITUTION|INTELLIGENCE|WISDOM|CHARISMA)$", message = "Invalid attribute type provided") String attributeEnum,
                                                             @Validated @Min(value = 1, message = "Attribute value must be at least 1") @Max(value = 2, message = "Attribute value must be at most 20") @RequestParam Integer value);

  @PatchMapping("/change/race/{uuid}")
  ResponseEntity<PlayerCharacterDTOResponse> changeRace(@PathVariable String uuid, @RequestParam Long raceId);

  @PatchMapping("/change/combat-class/{uuid}")
  ResponseEntity<PlayerCharacterDTOResponse> changeCombatClass(@PathVariable String uuid, @RequestParam Long combatClassId);

  @DeleteMapping("/{uuid}")
    ResponseEntity<Void> delete(@PathVariable String uuid);
}
