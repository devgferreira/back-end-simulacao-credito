package br.com.mscartoes.application.services;

import br.com.mscartoes.application.dtos.CartaoDTO;
import br.com.mscartoes.domain.interfaces.ICartaoRepository;
import br.com.mscartoes.domain.model.Cartao;
import org.junit.jupiter.api.Test;
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
import static br.com.mscartoes.common.CartaoConstants.CARTAO_DTO_VALIDO;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CartaoServiceTest {
    @InjectMocks
    private CartaoService _cartaoService;
    @Spy
    private ModelMapper _mapper;
    @Mock
    private ICartaoRepository _cartaoRepository;
    @Test
    void criarCartao_ComOCartaoValido_RetoranandoCartao(){
        Cartao cartao = _mapper.map(CARTAO_DTO_VALIDO, Cartao.class);
        when(_cartaoRepository.save(any())).thenReturn(cartao);
        CartaoDTO result = _cartaoService.criarCartao(CARTAO_DTO_VALIDO);

        assertEquals(CARTAO_DTO_VALIDO, result);

    }

    @Test
    public void getCartoesRendaMenorIgual_ComCartaoValido_RetornandoListaDeCartoes() {
        // Arrange
        long renda = 50000L;
        BigDecimal rendaBigDecimal = BigDecimal.valueOf(renda);
        List<Cartao> cartoes = new ArrayList<>();
        Cartao cartao = new Cartao();
        cartoes.add(cartao);

        when(_cartaoRepository.findByRendaLessThanEqual(rendaBigDecimal)).thenReturn(cartoes);

        List<CartaoDTO> result = _cartaoService.getCartoesRendaMenorIgual(renda);

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(_cartaoRepository, times(1)).findByRendaLessThanEqual(rendaBigDecimal);
    }
}