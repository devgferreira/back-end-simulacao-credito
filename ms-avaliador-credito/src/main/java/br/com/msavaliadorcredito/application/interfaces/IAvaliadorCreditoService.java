package br.com.msavaliadorcredito.application.interfaces;

import br.com.msavaliadorcredito.domian.model.dados.RetornoAvaliacaoCliente;
import br.com.msavaliadorcredito.domian.model.cliente.SituacaoCliente;
import br.com.msavaliadorcredito.infra.exceptions.DadosClienteNotFoundException;
import br.com.msavaliadorcredito.infra.exceptions.ErroComunicacaoMicroserviceException;

public interface IAvaliadorCreditoService {

   RetornoAvaliacaoCliente realizarAvalicao(String cpf, Long renda) throws ErroComunicacaoMicroserviceException, DadosClienteNotFoundException;
   SituacaoCliente obterSituacaoCliente(String cpf) throws DadosClienteNotFoundException, ErroComunicacaoMicroserviceException;
}
