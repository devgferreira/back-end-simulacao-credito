package br.com.mscartoes.application.interfaces;

import br.com.mscartoes.application.dtos.ClienteCartaoDTO;

import java.util.List;

public interface IClienteCartaoService {
    List<ClienteCartaoDTO> listCartoesByCpf(String cpf);
}

