package br.com.mscartoes.application.dtos.response;

import br.com.mscartoes.application.dtos.ClienteCartaoDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartoesPorClienteResponse {
    private String nome;
    private String bandeira;
    private BigDecimal limiteLiberado;

    public static CartoesPorClienteResponse fromModel(ClienteCartaoDTO clienteCartoes) {
        return new CartoesPorClienteResponse(
                clienteCartoes.getCartao().getNome(),
                clienteCartoes.getCartao().getBandeira().toString(),
                clienteCartoes.getLimite()
        );
    }
}
