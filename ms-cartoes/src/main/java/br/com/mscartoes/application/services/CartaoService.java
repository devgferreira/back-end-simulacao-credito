package br.com.mscartoes.application.services;

import br.com.mscartoes.application.dtos.CartaoDTO;
import br.com.mscartoes.application.interfaces.ICartaoService;
import br.com.mscartoes.domain.interfaces.ICartaoRepository;
import br.com.mscartoes.domain.model.Cartao;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public CartaoDTO save(CartaoDTO cartaoDTO) {
        Cartao cartao = _modelMapper.map(cartaoDTO, Cartao.class);
        return _modelMapper.map(_cartaoRepository.save(cartao), CartaoDTO.class) ;
    }

    @Override
    public List<CartaoDTO> getCartoesRendaMenorIgual(Long renda) {
        return null;
    }
}
