package com.example.convert.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;


@ControllerAdvice
public class ExceptionGlobal {
    private static final Logger LOGGER =
            LoggerFactory.getLogger(ExceptionGlobal.class);
    /**
     * Обрабатывает исключение типа HttpClientErrorException
     * и возвращает ответ с кодом состояния 400.
     * @param ex      исключение типа HttpClientErrorException,
     *                которое нужно обработать
     * @param request запрос, который вызвал исключение
     * @return ответ с кодом состояния 400 Bad Request
     */
    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<Object> handleHttpClientErrorException(
            final HttpClientErrorException ex,
            final WebRequest request) {
        LOGGER.error("400 Bad Request");
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("error", "400 Bad Request");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }
    /**
     * Обрабатывает исключение типа RuntimeException
     * и возвращает ответ с кодом состояния 500.
     *
     * @param ex      исключение типа RuntimeException, которое нужно обработать
     * @param request запрос, который вызвал исключение
     * @return ответ с кодом состояния 500 Internal Server Error
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleRuntimeException(
            final RuntimeException ex,
            final WebRequest request) {
        LOGGER.error("500 Internal Server Error");
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("error", "500 Internal Server Error");
        return ResponseEntity.status(
                HttpStatus.INTERNAL_SERVER_ERROR).body(responseBody);
    }
}