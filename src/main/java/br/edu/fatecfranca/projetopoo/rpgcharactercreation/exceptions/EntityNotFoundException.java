package br.edu.fatecfranca.projetopoo.rpgcharactercreation.exceptions;

import org.springframework.http.HttpStatus;

import static br.edu.fatecfranca.projetopoo.rpgcharactercreation.utils.GlobalConstants.ENTITY_NOT_FOUNDED_EXCEPTION_MESSAGE;

public class EntityNotFoundException extends RuntimeException{
  public final HttpStatus status = HttpStatus.NOT_FOUND;

  public EntityNotFoundException(String entityName, String id) {
    super(String.format(ENTITY_NOT_FOUNDED_EXCEPTION_MESSAGE, entityName, id));
  }
  public HttpStatus getStatus() {
    return status;
  }
}
