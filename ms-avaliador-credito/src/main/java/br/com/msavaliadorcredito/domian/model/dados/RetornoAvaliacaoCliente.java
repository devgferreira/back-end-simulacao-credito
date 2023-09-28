package br.com.msavaliadorcredito.domian.model.dados;

import br.com.msavaliadorcredito.domian.model.cartao.CartaoAprovado;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class RetornoAvaliacaoCliente {
    private List<CartaoAprovado> cartoes;
}
