package br.com.mscartoes.application.services;

import br.com.mscartoes.domain.interfaces.IClienteCartaoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class ClienteCartaoServiceTest {
    @InjectMocks
    private ClienteCartaoService _clienteCartaoService;
    @Spy
    private ModelMapper _mapper;
    @Mock
    private IClienteCartaoRepository _clienteCartaoRepository;
    @Test
    void listCartoesByCpf() {
    }
}