package br.com.msclientes.application.services;

import br.com.msclientes.application.dtos.ClienteDTO;

import static br.com.msclientes.common.ClienteConstatns.CLIENTEDTO_INVALIDO;
import static br.com.msclientes.common.ClienteConstatns.CLIENTEDTO_VALIDO;
import br.com.msclientes.domain.interfaces.IClienteRepository;
import br.com.msclientes.domain.model.Cliente;
import br.com.msclientes.infra.exceptions.ClienteJaExisteExeception;
import br.com.msclientes.infra.exceptions.CpfInvalidoExeception;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClienteServiceTest {
    @InjectMocks
    private ClienteService _clienteService;
    @Spy
    private ModelMapper _mapper;
    @Mock
    private IClienteRepository _clienteRepository;

    @Test
    void criarCliente_ComOCpfNaoUsado_RetornandoCLienteDTO() {

        Cliente cliente = _mapper.map(CLIENTEDTO_VALIDO, Cliente.class);
        when(_clienteRepository.save(any())).thenReturn(cliente);

        ClienteDTO result = _clienteService.criarCliente(CLIENTEDTO_VALIDO);

        assertEquals(CLIENTEDTO_VALIDO, result);
    }

    @Test
    void criarCliente_ComOCpUsado_RetornandoThrowsClienteJaExisteExeception() {

        Cliente cliente = _mapper.map(CLIENTEDTO_VALIDO, Cliente.class);
        cliente.setId(1L);

        when(_clienteRepository.findByCpf(cliente.getCpf())).thenReturn(Optional.of(cliente));

        assertThrows(ClienteJaExisteExeception.class,
                () -> _clienteService.criarCliente(CLIENTEDTO_VALIDO));
    }

    @Test
    void getByCPF_ComOCpfValido_RetornandoClienteDTO () {
        Cliente cliente = _mapper.map(CLIENTEDTO_VALIDO, Cliente.class);
        when(_clienteRepository.findByCpf(cliente.getCpf())).thenReturn(Optional.of(cliente));
        ClienteDTO result = _clienteService.getByCPF(cliente.getCpf());
        assertEquals(CLIENTEDTO_VALIDO, result);
    }


}