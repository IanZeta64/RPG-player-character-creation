package br.edu.fatecfranca.projetopoo.rpgcharactercreation.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

import static br.edu.fatecfranca.projetopoo.rpgcharactercreation.utils.GlobalConstants.METHOD_ARGUMENT_NOT_VALID_ERROR_MESSAGE;

@ControllerAdvice
public class ExceptionHandlerControllerAdvice extends ResponseEntityExceptionHandler {

  @ExceptionHandler(EntityNotFoundException.class)
  protected ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException ex) {
    var httpStatus = HttpStatus.NOT_FOUND;
    ErrorResponse errorResponse = new ErrorResponse(httpStatus.value(), httpStatus.getReasonPhrase(), List.of(ex.getMessage()));
    return new ResponseEntity<>(errorResponse, httpStatus);
  }
  @ExceptionHandler(EntityDuplicatedException.class)
  protected ResponseEntity<Object> handleEntityDuplicatedException(EntityDuplicatedException ex) {
    var httpStatus = ex.getStatus();
    ErrorResponse errorResponse = new ErrorResponse(httpStatus.value(), httpStatus.getReasonPhrase(), List.of(ex.getMessage()));
    return new ResponseEntity<>(errorResponse, httpStatus);
  }
  @ExceptionHandler(IllegalArgumentException.class)
  protected ResponseEntity<Object> handleInvalidUUID(IllegalArgumentException ex, WebRequest request) {
    HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
    ErrorResponse errorResponse = new ErrorResponse(httpStatus.value(), httpStatus.getReasonPhrase(),  List.of(ex.getMessage()));
    return handleExceptionInternal(ex, errorResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
    MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status,
    WebRequest request) {
    List<String> errorMessageList = getErrorMessages(ex.getBindingResult());
    HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
    ErrorResponse errorResponse = new ErrorResponse(httpStatus.value(), httpStatus.getReasonPhrase(), errorMessageList);
    return new ResponseEntity<>(errorResponse, httpStatus);
  }

  private List<String> getErrorMessages(BindingResult bindingResult) {
    List<String> errorMessages = new ArrayList<>();
    bindingResult.getFieldErrors().forEach(error ->
      errorMessages.add(getMethodArgumentNotValidErrorMessage(error)));
    bindingResult.getGlobalErrors().forEach(error ->
      errorMessages.add(getMethodArgumentNotValidErrorMessage(error)));
    return errorMessages;
  }
  private String getMethodArgumentNotValidErrorMessage(FieldError error) {
    return String.format(METHOD_ARGUMENT_NOT_VALID_ERROR_MESSAGE, error.getField(), error.getDefaultMessage());
  }

  private String getMethodArgumentNotValidErrorMessage(ObjectError error) {
    return String.format(METHOD_ARGUMENT_NOT_VALID_ERROR_MESSAGE, error.getObjectName(), error.getDefaultMessage());
  }
}
