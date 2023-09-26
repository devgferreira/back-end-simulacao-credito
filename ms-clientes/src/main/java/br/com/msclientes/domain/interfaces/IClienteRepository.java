package br.com.msclientes.domain.interfaces;

import br.com.msclientes.domain.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findByCPF(String cpf);
}


