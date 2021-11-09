package ru.sberbank.interview.task.exception;

import lombok.Getter;

@Getter
public class MyException extends RuntimeException {

    private final Integer code;
    private final String message;
    private final String details;

    public MyException(Integer code, String message, String details) {
        super(message);
        this.code = code;
        this.message = message;
        this.details = details;
    }
}
