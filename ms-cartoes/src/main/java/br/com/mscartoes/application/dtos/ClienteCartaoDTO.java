package br.com.mscartoes.application.dtos;

import br.com.mscartoes.domain.model.Cartao;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class ClienteCartaoDTO {
    private Long id;
    private String cpf;

    private Cartao cartao;
    private BigDecimal limite;

    public ClienteCartaoDTO(String cpf) {
        this.cpf = cpf;
    }
}
