package org.api.automation.foundation.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CreateBookDTO {

    @JsonProperty("actionReason")
    private String actionReason;

    @JsonProperty("title")
    private String title;

    @JsonProperty("author")
    private String author;

    @JsonProperty("genre")
    private String genre;

    @JsonProperty("yearPublished")
    private int yearPublished;

    public CreateBookDTO(String actionReason, String title, String author, String genre, int yearPublished) {
        this.actionReason = actionReason;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.yearPublished = yearPublished;
    }
}
