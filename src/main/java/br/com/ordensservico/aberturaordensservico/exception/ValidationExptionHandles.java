package br.com.ordensservico.aberturaordensservico.exception;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice 
public class ValidationExptionHandles {
    @ExceptionHandler (MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException erro) {
     Map<String, String> mensagens = new LinkedHashMap<>();
     erro.getBindingResult().getFieldErrors().forEach(Campo -> {
         mensagens.put(Campo.getField(), Campo.getDefaultMessage());
     });
     return ResponseEntity.badRequest().body(mensagens);
 }}