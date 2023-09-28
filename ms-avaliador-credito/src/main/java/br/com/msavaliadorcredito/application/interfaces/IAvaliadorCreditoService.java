package br.com.msavaliadorcredito.application.interfaces;

import br.com.msavaliadorcredito.domian.model.SituacaoCliente;
import br.com.msavaliadorcredito.infra.clients.exceptions.DadosClienteNotFoundException;
import br.com.msavaliadorcredito.infra.clients.exceptions.ErroComunicacaoMicroserviceException;

public interface IAvaliadorCreditoService {
   SituacaoCliente obterSituacaoCliente(String cpf) throws DadosClienteNotFoundException, ErroComunicacaoMicroserviceException;
}
