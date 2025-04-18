package org.api.automation.foundation.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.api.automation.foundation.model.BookDTO;
import org.api.automation.foundation.model.MessageDTO;
import org.junit.Assert;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.api.automation.foundation.constants.Constants.*;

@Slf4j
public class JSONUtil {

    @NonNull
    public static Object jsonToDto(String json, String dto) {
        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

        try {
            switch (dto) {
                // Add Additional Response DTO(s) Here
                case DTO_BOOK:
                    return objectMapper.readValue(json, BookDTO.class);
                case DTO_BOOK_LIST:
                    return objectMapper.readValue(json, new TypeReference<List<BookDTO>>() {});
                case DTO_MESSAGE:
                    return objectMapper.readValue(json, MessageDTO.class);
                default:
                    String defaultError = LOG_ERROR_DTO_ARGUMENT + dto;
                    log.error(defaultError);
                    Assert.fail(defaultError);
                    return null;
            }
        } catch (Exception e) {
            String exceptionError = LOG_ERROR_READ_JSON_STRING + e.getMessage();
            log.error(exceptionError);
            Assert.fail(exceptionError);
            return null;
        }
    }

    public static String dtoToString(Object dto) {
        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        try {
            return objectMapper.writeValueAsString(dto);
        } catch (JsonProcessingException e) {
            log.error(LOG_ERROR_READ_DTO + dto.toString());
            return EMPTY_JSON;
        }
    }

    public static String jsonToString(String jsonFileName) {
        return jsonToString(jsonFileName, PATH_JSON);
    }

    public static String jsonToString(String fileName, String jsonFilePath) {
        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
        String filePath = jsonFilePath + fileName + FORMAT_JSON;

        try {
            return objectMapper.writeValueAsString(objectMapper.readTree(new File(filePath)));
        } catch (IOException e) {
            log.error(LOG_ERROR_READ_JSON_FILE + filePath);
            return EMPTY_JSON;
        }
    }
}