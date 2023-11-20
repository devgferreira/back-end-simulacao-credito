package br.com.mscartoes.infra.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CartaoNaoEncontradoExeception extends RuntimeException {
    private final ExceptionResponse exceptionResponse;

    public CartaoNaoEncontradoExeception(ExceptionResponse exceptionResponse) {
        super();
        this.exceptionResponse = exceptionResponse;
    }
}
