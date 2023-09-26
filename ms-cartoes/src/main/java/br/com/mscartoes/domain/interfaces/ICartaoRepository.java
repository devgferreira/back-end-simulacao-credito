package br.com.mscartoes.domain.interfaces;

import br.com.mscartoes.application.dtos.CartaoDTO;
import br.com.mscartoes.domain.model.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface ICartaoRepository extends JpaRepository<Cartao, Long> {
    List<Cartao> findByRendaLessThanEqual(BigDecimal rendaBigDecimal);
}
