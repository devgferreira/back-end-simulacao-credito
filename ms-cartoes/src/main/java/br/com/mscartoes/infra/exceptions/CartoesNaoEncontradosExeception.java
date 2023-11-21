package br.com.mscartoes.infra.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CartoesNaoEncontradosExeception extends RuntimeException {
    private final ExceptionResponse exceptionResponse;

    public CartoesNaoEncontradosExeception(ExceptionResponse exceptionResponse) {
        super();
        this.exceptionResponse = exceptionResponse;
    }
}
