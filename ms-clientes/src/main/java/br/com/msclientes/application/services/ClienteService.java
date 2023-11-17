package br.com.msclientes.application.services;

import br.com.msclientes.application.dtos.ClienteDTO;
import br.com.msclientes.application.interfaces.IClienteService;
import br.com.msclientes.domain.enums.ErrorCodes;
import br.com.msclientes.domain.interfaces.IClienteRepository;
import br.com.msclientes.domain.model.Cliente;
import br.com.msclientes.infra.constants.ErrorConstants;
import br.com.msclientes.infra.exceptions.ClienteJaExisteExeception;
import br.com.msclientes.infra.exceptions.ClienteNaoEncontradoExeception;
import br.com.msclientes.infra.exceptions.CpfInvalidoExeception;
import br.com.msclientes.infra.exceptions.ExceptionResponse;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CPFValidator;
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
        if (_clienteRepository.findByCpf(cliente.getCpf()).isPresent()) {
            throw new ClienteJaExisteExeception(new ExceptionResponse(ErrorCodes.CLIENTE_JA_EXISTE, ErrorConstants.CLIENTEO_JA_EXISTE));
        }
        if (!validator(cliente.getCpf())) {
            throw new CpfInvalidoExeception(new ExceptionResponse(ErrorCodes.CPF_INVALIDO, ErrorConstants.CPF_INVALIDO));
        }
        return _modelMapper.map(_clienteRepository.save(cliente), ClienteDTO.class);
    }

    @Override
    public ClienteDTO getByCPF(String cpf) {
        Cliente cliente = _clienteRepository.findByCpf(cpf).orElseThrow(
                () -> new ClienteNaoEncontradoExeception(
                        new ExceptionResponse(ErrorCodes.CLIENTE_NAO_ENCONTRADO,
                                ErrorConstants.CLIENTE_NAO_ENCONTRADO))
        );
        return _modelMapper.map(cliente, ClienteDTO.class);
    }

    public boolean validator(String cpf) {
        CPFValidator validator = new CPFValidator();
        validator.initialize(null);
        return validator.isValid(cpf, null);
    }
}
