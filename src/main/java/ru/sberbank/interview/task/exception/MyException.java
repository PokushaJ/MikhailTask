package ru.sberbank.interview.task.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class MyException extends RuntimeException {

    private final Integer code;
    private final HttpStatus httpStatus;
    private final String message;

    public MyException(Integer code, HttpStatus status, String message) {
        super(message);
        this.code = code;
        this.httpStatus = status;
        this.message = message;
    }
}
