package br.com.mscartoes.infra.constants;

import lombok.AccessLevel;
import lombok.Getter;

@Getter(AccessLevel.PRIVATE)
public class ErrorConstants {
    public static final String CARTAO_NAO_ENCONTRADO = "[MS-CARTOES] Cartão não foi encontrado";
    public static final String CLIENTE_NAO_ENCONTRADO = "[MS-CARTOES] Cliente não foi encontrado";
    public static final String CARTOES_NAO_ENCONTRADOS = "[MS-CARTOES] Cartoes não foram encontrados";
}
