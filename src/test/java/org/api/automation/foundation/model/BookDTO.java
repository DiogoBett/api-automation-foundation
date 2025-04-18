package org.api.automation.foundation.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

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
    private String createdAt;
}