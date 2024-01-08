package br.com.mscartoes.application.interfaces;

import br.com.mscartoes.application.dtos.CartaoDTO;

import java.util.List;

public interface ICartaoService {

    CartaoDTO criarCartao(CartaoDTO cartaoDTO);

    List<CartaoDTO> getCartoesRendaMenorIgual(Long renda);
}
