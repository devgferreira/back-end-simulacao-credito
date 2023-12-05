package br.com.msclientes.infra.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ClienteJaExisteExeception extends RuntimeException {
    private ExceptionResponse exceptionResponse;

    public ClienteJaExisteExeception(ExceptionResponse exceptionResponse) {
        super();
        this.exceptionResponse = exceptionResponse;
    }
}
