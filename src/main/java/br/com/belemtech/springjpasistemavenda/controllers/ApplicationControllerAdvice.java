package br.com.belemtech.springjpasistemavenda.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.belemtech.springjpasistemavenda.exception.ApiErros;
import br.com.belemtech.springjpasistemavenda.exception.RegraNegocioException;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(RegraNegocioException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErros handlerRegraNegocioException(RegraNegocioException ex) {
        String mensagem = ex.getMessage();
        return new ApiErros(mensagem);
    }

}
