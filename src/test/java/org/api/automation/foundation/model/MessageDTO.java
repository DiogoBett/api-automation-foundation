package org.api.automation.foundation.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

@Data
public class MessageDTO {

  @JsonProperty("status")
  private String status;

  @JsonProperty("message")
  private String message;

  @JsonProperty("errors")
  private List<ErrorDTO> errors;

  @Data
  public static class ErrorDTO {

    @JsonProperty("path")
    private String path;

    @JsonProperty("message")
    private String message;

    @JsonProperty("errorCode")
    private String errorCode;
  }
}
