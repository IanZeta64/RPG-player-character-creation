package br.edu.fatecfranca.projetopoo.rpgcharactercreation.service;

import br.edu.fatecfranca.projetopoo.rpgcharactercreation.model.dto.request.RaceDTORequest;
import br.edu.fatecfranca.projetopoo.rpgcharactercreation.model.dto.response.RaceDTOResponse;

import java.util.List;

public interface RaceService {
  RaceDTOResponse save(RaceDTORequest request);

  List<RaceDTOResponse> getAll();

  RaceDTOResponse getById(Long id);

  List<RaceDTOResponse> findByName(String name);

  RaceDTOResponse update(Long id, RaceDTORequest request);

  RaceDTOResponse changeName(Long id, String name);

  void delete(Long id);

}
