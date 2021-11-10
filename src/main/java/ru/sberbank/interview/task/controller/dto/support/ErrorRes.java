package ru.sberbank.interview.task.controller.dto.support;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorRes {

    private Integer code;
    private String message;

    public ErrorRes(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
