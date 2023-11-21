package br.com.mscartoes.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCodes {

    CARTAO_NAO_ENCONTRADO("Cartão não foi encontrado"),
    CLIENTE_NAO_ENCONTRADO("Cliente não foi encontrado"),
    CARTOES_NAO_ENCONTRADOS("Cartões não foram encontrados");
    private final String message;

}