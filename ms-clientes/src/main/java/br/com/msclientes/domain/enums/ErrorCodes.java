package br.com.msclientes.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCodes {

    CLIENTE_NAO_ENCONTRADO("Cliente não foi encontrado"),
    CLIENTE_JA_EXISTE("Cliente já existe"),
    CPF_INVALIDO("CPF invalido, o CPF precisar conter 11 caracters");
    private final String message;

}