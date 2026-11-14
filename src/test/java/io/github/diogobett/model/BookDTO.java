package io.github.diogobett.model;

import static io.github.diogobett.constants.Constants.PATTERN_DATE_CREATED_AT;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
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
  @JsonFormat(pattern = PATTERN_DATE_CREATED_AT)
  private LocalDateTime createdAt;
}
