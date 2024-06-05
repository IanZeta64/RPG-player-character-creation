package br.edu.fatecfranca.projetopoo.rpgcharactercreation.exceptions;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
public class ErrorResponse {
    private int status;
    private String error;
    private List<String> messages;
    private LocalDateTime instant;
    public ErrorResponse(int status, String error, List<String> messageList) {
      this.status = status;
      this.error = error;
      this.messages = messageList;
      this.instant = LocalDateTime.now();
    }

  public int getStatus() {
    return status;
  }

  public String getError() {
    return error;
  }

  public List<String> getMessages() {
    return messages;
  }

  public String getInstant() {
    return instant.toString();
  }
}
