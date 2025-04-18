package org.api.automation.foundation.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MessageDTO {

    @JsonProperty("status")
    private String status;

    @JsonProperty("message")
    private String message;
}
