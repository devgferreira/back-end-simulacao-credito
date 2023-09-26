package br.com.msclientes.application.services;

import br.com.msclientes.application.dtos.ClienteDTO;
import br.com.msclientes.application.interfaces.IClienteService;
import br.com.msclientes.domain.interfaces.IClienteRepository;
import br.com.msclientes.domain.model.Cliente;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService implements IClienteService {

    private final IClienteRepository _clienteRepository;
    private final ModelMapper _modelMapper;

    @Autowired
    public ClienteService(IClienteRepository clienteRepository, ModelMapper modelMapper) {
        _clienteRepository = clienteRepository;
        _modelMapper = modelMapper;
    }

    @Override
    public ClienteDTO save(ClienteDTO clienteDTO) {
        Cliente cliente = _modelMapper.map(clienteDTO, Cliente.class);
        return _modelMapper.map(_clienteRepository.save(cliente), ClienteDTO.class) ;
    }

    @Override
    public ClienteDTO getByCPF(String cpf) {
        return _modelMapper.map(_clienteRepository.findByCpf(cpf).orElse(new Cliente()), ClienteDTO.class);
    }
}
