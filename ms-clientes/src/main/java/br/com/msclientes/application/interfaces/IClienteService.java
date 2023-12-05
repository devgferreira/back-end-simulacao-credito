package br.com.msclientes.application.interfaces;

import br.com.msclientes.application.dtos.ClienteDTO;

public interface IClienteService {
    ClienteDTO criarCliente(ClienteDTO clienteDTO);

    ClienteDTO getByCPF(String cpf);
}
