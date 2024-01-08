package br.com.mscartoes.infra.exceptions;

import br.com.mscartoes.domain.enums.ErrorCodes;
import lombok.Data;

@Data
public class ExceptionResponse {
    private final String code;
    private final String message;

    public ExceptionResponse(final ErrorCodes errorCode, String details) {
        this.code = errorCode.name();
        this.message = errorCode.getMessage();
    }


}
