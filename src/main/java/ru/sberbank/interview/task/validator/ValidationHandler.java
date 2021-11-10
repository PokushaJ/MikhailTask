package ru.sberbank.interview.task.validator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.sberbank.interview.task.controller.dto.support.ErrorRes;
import ru.sberbank.interview.task.exception.MyException;

@ControllerAdvice
@Slf4j
public class ValidationHandler {

    @ExceptionHandler({Exception.class})
    protected ResponseEntity<ErrorRes> handleException(Exception ex) {
        log.error("Error in service", ex);
        ErrorRes errorRes = null;
        HttpStatus httpStatus = null;
        if (ex instanceof MyException) {
            MyException e = (MyException) ex;
            errorRes = new ErrorRes(e.getCode(), e.getMessage());
            httpStatus = e.getHttpStatus();
        }
        if (errorRes == null || errorRes.getCode() == null && errorRes.getMessage() == null) {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            errorRes = new ErrorRes(500, "Сервис временно недоступен");
        }
        return ResponseEntity.status(httpStatus).body(errorRes);
    }

}
