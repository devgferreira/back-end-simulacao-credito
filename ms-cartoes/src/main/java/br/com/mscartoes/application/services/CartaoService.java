package br.com.mscartoes.application.services;

import br.com.mscartoes.application.dtos.CartaoDTO;
import br.com.mscartoes.application.interfaces.ICartaoService;
import br.com.mscartoes.domain.enums.ErrorCodes;
import br.com.mscartoes.domain.interfaces.ICartaoRepository;
import br.com.mscartoes.domain.model.Cartao;
import br.com.mscartoes.infra.constants.ErrorConstants;
import br.com.mscartoes.infra.exceptions.CartaoNaoEncontradoExeception;
import br.com.mscartoes.infra.exceptions.ExceptionResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartaoService implements ICartaoService {

    private final ICartaoRepository _cartaoRepository;

    private final ModelMapper _modelMapper;
    @Autowired
    public CartaoService(ICartaoRepository cartaoRepository, ModelMapper modelMapper) {
        _cartaoRepository = cartaoRepository;
        _modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public CartaoDTO criarCartao(CartaoDTO cartaoDTO) {
        Cartao cartao = _modelMapper.map(cartaoDTO, Cartao.class);
        return _modelMapper.map(_cartaoRepository.save(cartao), CartaoDTO.class) ;
    }

    @Override
    public List<CartaoDTO> getCartoesRendaMenorIgual(Long renda){
        BigDecimal rendaBigDecimal = BigDecimal.valueOf(renda);
        List<Cartao> cartoes = _cartaoRepository.findByRendaLessThanEqual(rendaBigDecimal);
        if(cartoes == null){
            throw new CartaoNaoEncontradoExeception(new ExceptionResponse(ErrorCodes.CARTAO_NAO_ENCONTRADO, ErrorConstants.CARTAO_NAO_ENCONTRADO));
        }
        return cartoes.stream()
                .map(entity -> _modelMapper.map(entity, CartaoDTO.class))
                .collect(Collectors.toList());
    }


}
