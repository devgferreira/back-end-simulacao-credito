package br.com.mscartoes.application.services;

import br.com.mscartoes.application.dtos.CartaoDTO;
import br.com.mscartoes.application.dtos.ClienteCartaoDTO;
import br.com.mscartoes.application.interfaces.IClienteCartaoService;
import br.com.mscartoes.domain.enums.ErrorCodes;
import br.com.mscartoes.domain.interfaces.ICartaoRepository;
import br.com.mscartoes.domain.interfaces.IClienteCartaoRepository;
import br.com.mscartoes.domain.model.Cartao;
import br.com.mscartoes.domain.model.ClienteCartao;
import br.com.mscartoes.infra.constants.ErrorConstants;
import br.com.mscartoes.infra.exceptions.ClienteNaoEncontradoExeception;
import br.com.mscartoes.infra.exceptions.ExceptionResponse;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteCartaoService implements IClienteCartaoService {
    private final IClienteCartaoRepository _clienteCartaoRepository;
    private final ModelMapper _modelMapper;

    public ClienteCartaoService(IClienteCartaoRepository clienteCartaoRepository, ModelMapper modelMapper) {
        _clienteCartaoRepository = clienteCartaoRepository;
        _modelMapper = modelMapper;
    }

    @Override
    public List<ClienteCartaoDTO> listCartoesByCpf(String cpf) {
        List<ClienteCartao> cliente = _clienteCartaoRepository.findByCpf(cpf);
        if(cliente == null){
            throw new ClienteNaoEncontradoExeception(new ExceptionResponse(ErrorCodes.CLIENTE_NAO_ENCONTRADO, ErrorConstants.CLIENTE_NAO_ENCONTRADO));
        }
        return cliente.stream()
                .map(entity -> _modelMapper.map(entity, ClienteCartaoDTO.class))
                .collect(Collectors.toList());
    }

}
