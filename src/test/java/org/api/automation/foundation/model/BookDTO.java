package org.api.automation.foundation.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

import static org.api.automation.foundation.constants.Constants.PATTERN_DATE_CREATED_AT;

@Data
public class BookDTO {

    @JsonProperty("title")
    private String title;

    @JsonProperty("author")
    private String author;

    @JsonProperty("id")
    private String id;

    @JsonProperty("genre")
    private String genre;

    @JsonProperty("yearPublished")
    private int year;

    @JsonProperty("checkedOut")
    private boolean checkedOut;

    @JsonProperty("createdAt")
    @JsonFormat(pattern = PATTERN_DATE_CREATED_AT)
    private LocalDateTime createdAt;
}