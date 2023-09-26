package br.com.mscartoes.domain.interfaces;

import br.com.mscartoes.domain.model.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICartaoRepository extends JpaRepository<Cartao, Long> {
}
