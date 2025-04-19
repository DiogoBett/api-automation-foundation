package org.api.automation.foundation.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class MessageDTO {

    @JsonProperty("status")
    private String status;

    @JsonProperty("message")
    private String message;

    @JsonProperty("errors")
    private List<ErrorDTO> errors;

    @Data
    private static class ErrorDTO {

        @JsonProperty("path")
        private String path;

        @JsonProperty("message")
        private String message;

        @JsonProperty("errorCode")
        private String errorCode;
    }
}