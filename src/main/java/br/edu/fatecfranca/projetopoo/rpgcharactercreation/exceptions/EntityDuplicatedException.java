package br.edu.fatecfranca.projetopoo.rpgcharactercreation.exceptions;

import org.springframework.http.HttpStatus;

import static br.edu.fatecfranca.projetopoo.rpgcharactercreation.utils.GlobalConstants.ENTITY_DUPLICATED_EXCEPTION_MESSAGE;

public class EntityDuplicatedException extends RuntimeException{
  private final HttpStatus status = HttpStatus.CONFLICT;
  public EntityDuplicatedException(String entityName) {
    super(String.format(ENTITY_DUPLICATED_EXCEPTION_MESSAGE, entityName));
  }
  public HttpStatus getStatus() {
    return status;
  }
}
