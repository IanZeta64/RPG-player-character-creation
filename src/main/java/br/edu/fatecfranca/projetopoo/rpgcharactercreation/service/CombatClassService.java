package br.edu.fatecfranca.projetopoo.rpgcharactercreation.service;

import br.edu.fatecfranca.projetopoo.rpgcharactercreation.model.dto.request.CombatClassDTORequest;
import br.edu.fatecfranca.projetopoo.rpgcharactercreation.model.dto.response.CombatClassDTOResponse;

import java.util.List;

public interface CombatClassService {
  CombatClassDTOResponse save(CombatClassDTORequest request);

  List<CombatClassDTOResponse> getAll();

  CombatClassDTOResponse getById(Long id);

  List<CombatClassDTOResponse> findByName(String name);

  CombatClassDTOResponse update(Long id, CombatClassDTORequest request);

  CombatClassDTOResponse changeName(Long id, String name);

  void delete(Long id);
}
