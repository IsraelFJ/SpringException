package com.example.projetoSpringExccoes.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, Object>> handlerRuntimeException(RuntimeException erro){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("Mensagem", erro.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handlerMethodArgumentNotValidException(MethodArgumentNotValidException erro){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("Mensagem", erro.getFieldErrors().get(0).getDefaultMessage()));
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<Map<String, Object>> HttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException erro){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("Mensagem", "Recurso não encontrado"));
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<Map<String, Object>> NoResourceFoundException(NoResourceFoundException erro){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("Mensagem", "URL invalida ou incompleta, por favor corrgir e tentar novamente"));
    }



}
