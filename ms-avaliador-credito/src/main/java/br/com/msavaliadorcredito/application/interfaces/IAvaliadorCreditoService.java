package br.com.msavaliadorcredito.application.interfaces;

import br.com.msavaliadorcredito.domian.model.RetornoAvaliacaoCliente;
import br.com.msavaliadorcredito.domian.model.SituacaoCliente;
import br.com.msavaliadorcredito.infra.clients.exceptions.DadosClienteNotFoundException;
import br.com.msavaliadorcredito.infra.clients.exceptions.ErroComunicacaoMicroserviceException;

public interface IAvaliadorCreditoService {

   RetornoAvaliacaoCliente realizarAvalicao(String cpf, Long renda) throws ErroComunicacaoMicroserviceException, DadosClienteNotFoundException;
   SituacaoCliente obterSituacaoCliente(String cpf) throws DadosClienteNotFoundException, ErroComunicacaoMicroserviceException;
}
