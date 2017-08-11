/**
 * 
 */
package com.cadastra.cliente.controles;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cadastra.cliente.excecoes.NegocioException;
import com.cadastra.cliente.modelo.Pessoa;
import com.cadastra.cliente.modelo.PessoaFisica;
import com.cadastra.cliente.modelo.PessoaJuridica;
import com.cadastra.cliente.servicos.PessoaServico;

/**
 * Controlador das requisições e serviços que a aplicação disponibiliza para pessoas
 * 
 * @author Daniel Ferraz
 * @since 4 de ago de 2017
 *
 */
@RestController
public class PessoaControle {
	
	@Autowired
	private PessoaServico pessoaService;

	/**
	 * URL: {@code http://localhost:8080/pessoa/fisica/cadastrar}<br>
	 * 
	 * Serviço de cadastro de pessoa fisica
	 * 
	 * @param pessoa Dados da pessoa
	 * @return Dados da pessoa cadastrada
	 * @exception NegocioException Erro de negócio ocorrido no serviço
	 */
	@RequestMapping(path="/pessoa/fisica/cadastrar", method=RequestMethod.POST)
	public Pessoa cadastrar(@RequestBody PessoaFisica pessoa) {		
		return pessoaService.gravar(pessoa);
	}
	
	/**
	 * URL: {@code http://localhost:8080/pessoa/juridica/cadastrar}<br>
	 * 
	 * Serviço de cadastro de pessoa juridica
	 *
	 * @param pessoa Dados da pessoa
	 * @return Dados da pessoa cadastrada
	 * @exception NegocioException Erro de negócio ocorrido no serviço
	 */
	@RequestMapping(path="/pessoa/juridica/cadastrar", method=RequestMethod.POST)
	public Pessoa cadastrar(@RequestBody PessoaJuridica pessoa) {
		return pessoaService.gravar(pessoa);
	}
	
	/**
	 * URL: {@code http://localhost:8080/pessoa/fisica/atualizar?documento=documento}<br>
	 * 
	 * Serviço de atualização de pessoa fisica
	 * 
	 * @param documento Código do documento
	 * @param pessoa Dados da pessoa
	 * @return Dados da pessoa atualizada
	 * @exception NegocioException Erro de negócio ocorrido no serviço
	 */
	@RequestMapping(path="/pessoa/fisica/atualizar", method=RequestMethod.POST)
	public Pessoa atualizar(@RequestParam String documento, @RequestBody PessoaFisica pessoa) {
		return pessoaService.atualizarPorDocumento(documento, pessoa);
	}
	
	/**
	 * URL: {@code http://localhost:8080/pessoa/juridica/atualizar?documento=documento}<br>
	 * 
	 * Serviço de atualização de pessoa juridica
	 * 
	 * @param documento Código do documento
	 * @param pessoa Dados da pessoa
	 * @return Dados da pessoa atualizada
	 * @exception NegocioException Erro de negócio ocorrido no serviço
	 */
	@RequestMapping(path="/pessoa/juridica/atualizar", method=RequestMethod.POST)
	public Pessoa atualizar(@RequestParam String documento, @RequestBody PessoaJuridica pessoa) {
		return pessoaService.atualizarPorDocumento(documento, pessoa);
	}
	
	/**
	 * URL: {@code http://localhost:8080/pessoa/buscar?documento=documento}<br>
	 * 
	 * Consulta uma pessoa pelo código do documento
	 * 
	 * @param documento Código do documento
	 * @return Dados da pessoa consultada
	 * @exception NegocioException Erro de negócio ocorrido no serviço
	 */
	@RequestMapping(path="/pessoa/buscar", params="documento", method=RequestMethod.GET)
	public Pessoa listar(@RequestParam String documento){
		return pessoaService.buscarPorDocumento(documento);
	}
	
	/**
	 * URL: {@code http://localhost:8080/pessoa/excluir?documento=documento}<br>
	 * 
	 * Exclui uma pessoa pelo código do documento
	 * 
	 * @param documento Código do documento
	 * @return Dados da pessoa excluida
	 * @exception NegocioException Erro de negócio ocorrido no serviço
	 */
	@RequestMapping(path="/pessoa/excluir", params="documento" , method=RequestMethod.DELETE)
	public Pessoa excluir(@RequestParam String documento){
		return pessoaService.excluirPorDocumento(documento);
	}
	
	/**
	 * URL: {@code http://localhost:8080/pessoa/carros/validar?documento=documento}<br>
	 * 
	 * Consulta uma pessoa pelo código do documento
	 * 
	 * @param documento Código do documento
	 * @return Dados da pessoa consultada
	 * @exception NegocioException Erro de negócio ocorrido no serviço
	 */
	@RequestMapping(path="/pessoa/carros/validar", params="documento", method=RequestMethod.GET)
	public Pessoa validarCarros(@RequestParam String documento){
		return pessoaService.atualizarDadosNoServicoPorDocumento(documento);
	}
	
	/**
	 * URL: {@code http://localhost:8081/carros/consultar?status=OK}<br>
	 * Realiza a consulta de um status no sistema utilizando uma requisição GET tendo o parametro {@code status} como status a ser contabilizado na URL<br>
	 * Formato do JSON de resposta<br>
	 * 
	 * {<br>		
	 * 		"status":"Status do veículo",<br>
	 * 		"pf":"Quantidade de carros de pessoa fisica com o status"<br>
	 * 		"pj":"Quantidade de carros de pessoa juridica com o status"<br>
	 * }
	 * 
	 * @param status Status de placa a ser consultada
	 * @return Retorna o JSON com os dados de quantidade de placas cadastradas
	 * @exception NegocioException Retorna um HTTP 400 em caso de falha na validação dos dados
	 */
	@RequestMapping(path = "/carros/consultar", params = "status", method = RequestMethod.GET)
	public Map<String, Object> buscarPorStatus(@RequestParam String status) {
		Long[] quantidades = pessoaService.consultarQuantidadeCarrosPorStatus(status);
		HashMap<String, Object> mapa = new HashMap<>();
		mapa.put("status", status);
		mapa.put("pf", quantidades[0]);
		mapa.put("pj", quantidades[1]);
		return mapa;
	}

	
}
