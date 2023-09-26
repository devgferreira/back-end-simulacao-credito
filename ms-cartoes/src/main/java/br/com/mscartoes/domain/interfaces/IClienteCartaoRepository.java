package br.com.mscartoes.domain.interfaces;

import br.com.mscartoes.domain.model.Cartao;
import br.com.mscartoes.domain.model.ClienteCartao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface IClienteCartaoRepository extends JpaRepository<ClienteCartao, Long> {
    List<ClienteCartao> findByCpf(String cpf);
}
