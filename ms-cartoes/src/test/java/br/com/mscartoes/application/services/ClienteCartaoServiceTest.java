package br.com.mscartoes.application.services;

import br.com.mscartoes.application.dtos.ClienteCartaoDTO;
import br.com.mscartoes.domain.interfaces.IClienteCartaoRepository;
import br.com.mscartoes.domain.model.Cartao;
import br.com.mscartoes.domain.model.ClienteCartao;
import br.com.mscartoes.infra.exceptions.CartoesNaoEncontradosExeception;
import br.com.mscartoes.infra.exceptions.ClienteNaoEncontradoExeception;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

import static br.com.mscartoes.common.ClienteCartaoConstants.CLIENTE_CARTAO_DTO;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClienteCartaoServiceTest {
    @InjectMocks
    private ClienteCartaoService _clienteCartaoService;
    @Spy
    private ModelMapper _mapper;
    @Mock
    private IClienteCartaoRepository _clienteCartaoRepository;
    @Test
    void listCartoesByCpf_ComCpfValido_RetornandoListaCartoes() {
        String cpf = "12345678901";

        ClienteCartao clienteCartao = _mapper.map(CLIENTE_CARTAO_DTO, ClienteCartao.class);
        List<ClienteCartao> clienteCartaoList = new ArrayList<>();
        clienteCartaoList.add(clienteCartao);
        when(_clienteCartaoRepository.findByCpf(cpf)).thenReturn(clienteCartaoList);

        List<ClienteCartaoDTO> result = _clienteCartaoService.listCartoesByCpf(cpf);
        assertNotNull(result);
        assertEquals(clienteCartaoList.size(), result.size());
        verify(_clienteCartaoRepository, times(1)).findByCpf(cpf);
    }

    @Test
    void listCartoesByCpf_ComCpfInvalido_RetornandoClienteNaoEncontradoExeception() {
        when(_clienteCartaoRepository.findByCpf(any())).thenReturn(null);

        assertThrows(ClienteNaoEncontradoExeception.class, () -> {
            _clienteCartaoService.listCartoesByCpf("12345678901");
        });
    }


}