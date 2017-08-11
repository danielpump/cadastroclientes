package com.cadastra.cliente;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Relação de testes de regras que a aplicação aplica para os cenários de
 * Veículo
 * 
 * @author Daniel Ferraz
 * @since 9 de ago de 2017
 *
 */
@RunWith(SpringRunner.class)
@Profile("test")
public class PessoaTest extends ApplicationTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testeDeConsultaDePessoaPorCPF() throws Exception {
		String jsonResposta = this.mockMvc.perform(get("/pessoa/buscar?documento=12345678901")).andExpect(status().isOk())
				.andReturn().getResponse().getContentAsString();

		assertThat(jsonResposta).isEqualTo("{\"nome\":\"Pessoa fisica 1\",\"documento\":\"12345678901\",\"tipoPessoa\":\"PF\",\"carros\":[{\"modelo\":\"Modelo 1\",\"placa\":\"AAA1111\"}]}");

	}
	
	@Test
	public void testeDeConsultaDePessoaPorCNPJ() throws Exception {
		String jsonResposta = this.mockMvc.perform(get("/pessoa/buscar?documento=12345678901234")).andExpect(status().isOk())
				.andReturn().getResponse().getContentAsString();

		assertThat(jsonResposta).isEqualTo("{\"nome\":\"Pessoa juridica 1\",\"documento\":\"12345678901234\",\"tipoPessoa\":\"PJ\",\"carros\":[{\"modelo\":\"Modelo 4\",\"placa\":\"AAA1114\"}]}");

	}

	@Test
	public void testeDeConsultaDePessoaPorCPFComPessoaInexistente() throws Exception {
		String message = this.mockMvc.perform(get("/pessoa/buscar?documento=12345678903")).andExpect(status().isBadRequest())
				.andReturn().getResolvedException().getMessage();
		assertThat(message).isEqualTo("Registro de pessoa sem cadastro no banco de dados");
	}
	
	@Test
	public void testeDeConsultaDePessoaPorCNPJComPessoaInexistente() throws Exception {
		String message = this.mockMvc.perform(get("/pessoa/buscar?documento=12345679801236")).andExpect(status().isBadRequest())
				.andReturn().getResolvedException().getMessage();
		assertThat(message).isEqualTo("Registro de pessoa sem cadastro no banco de dados");
	}
	
	@Test
	public void testeDeExclusaoDePessoaPorCPF() throws Exception {
		String jsonResposta = this.mockMvc.perform(delete("/pessoa/excluir?documento=12345678901")).andExpect(status().isOk())
				.andReturn().getResponse().getContentAsString();

		assertThat(jsonResposta).isEqualTo("{\"nome\":\"Pessoa fisica 1\",\"documento\":\"12345678901\",\"tipoPessoa\":\"PF\",\"carros\":[{\"modelo\":\"Modelo 1\",\"placa\":\"AAA1111\"}]}");

		String message = this.mockMvc.perform(delete("/pessoa/excluir?documento=12345678901")).andExpect(status().isBadRequest())
				.andReturn().getResolvedException().getMessage();
		assertThat(message).isEqualTo("Registro de pessoa sem cadastro no banco de dados");		
	}
	
	@Test
	public void testeDeExclusaoDePessoaPorCNPJ() throws Exception {
		String jsonResposta = this.mockMvc.perform(delete("/pessoa/excluir?documento=12345678901234")).andExpect(status().isOk())
				.andReturn().getResponse().getContentAsString();

		assertThat(jsonResposta).isEqualTo("{\"nome\":\"Pessoa juridica 1\",\"documento\":\"12345678901234\",\"tipoPessoa\":\"PJ\",\"carros\":[{\"modelo\":\"Modelo 4\",\"placa\":\"AAA1114\"}]}");

		String message = this.mockMvc.perform(delete("/pessoa/excluir?documento=12345678901234")).andExpect(status().isBadRequest())
				.andReturn().getResolvedException().getMessage();
		
		assertThat(message).isEqualTo("Registro de pessoa sem cadastro no banco de dados");
	}

	@Test
	public void testeDeExclusaoDePessoaPorCPFComPessoaInexistente() throws Exception {
		String message = this.mockMvc.perform(delete("/pessoa/excluir?documento=12345678903")).andExpect(status().isBadRequest())
				.andReturn().getResolvedException().getMessage();
		assertThat(message).isEqualTo("Registro de pessoa sem cadastro no banco de dados");
	}
	
	@Test
	public void testeDeExclusaoDePessoaPorCNPJComPessoaInexistente() throws Exception {
		String message = this.mockMvc.perform(delete("/pessoa/excluir?documento=12345679801236")).andExpect(status().isBadRequest())
				.andReturn().getResolvedException().getMessage();
		assertThat(message).isEqualTo("Registro de pessoa sem cadastro no banco de dados");
	}
	
	@Test
	public void testeDeCadastroDePessoaFisica() throws Exception {
		String jsonResposta = this.mockMvc
				.perform(post("/pessoa/fisica/cadastrar").contentType(MediaType.APPLICATION_JSON_UTF8)
						.content("{\"nome\":\"Pessoa fisica 11\",\"documento\":\"12345678921\",\"tipoPessoa\":\"PF\",\"carros\":[{\"modelo\":\"Modelo 13\",\"placa\":\"AAA1121\"}]}"))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		assertThat(jsonResposta).isEqualTo("{\"nome\":\"Pessoa fisica 11\",\"documento\":\"12345678921\",\"tipoPessoa\":\"PF\",\"carros\":[{\"modelo\":\"Modelo 13\",\"placa\":\"AAA1121\"}]}");
	}
	
	@Test
	public void testeDeCadastroDePessoaJuridica() throws Exception {
		String jsonResposta = this.mockMvc
				.perform(post("/pessoa/juridica/cadastrar").contentType(MediaType.APPLICATION_JSON_UTF8)
						.content("{\"nome\":\"Pessoa juridica 11\",\"documento\":\"12345678901244\",\"tipoPessoa\":\"PJ\",\"carros\":[{\"modelo\":\"Modelo 14\",\"placa\":\"AAA1122\"}]}"))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		assertThat(jsonResposta).isEqualTo("{\"nome\":\"Pessoa juridica 11\",\"documento\":\"12345678901244\",\"tipoPessoa\":\"PJ\",\"carros\":[{\"modelo\":\"Modelo 14\",\"placa\":\"AAA1122\"}]}");
	}
	
	@Test
	public void testeDeCadastroDePessoaFisicaComLetrasMinusculas() throws Exception {
		String jsonResposta = this.mockMvc
				.perform(post("/pessoa/fisica/cadastrar").contentType(MediaType.APPLICATION_JSON_UTF8)
						.content("{\"nome\":\"Pessoa fisica 11\",\"documento\":\"12345678921\",\"tipoPessoa\":\"pf\",\"carros\":[{\"modelo\":\"Modelo 13\",\"placa\":\"aaa1121\"}]}"))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		assertThat(jsonResposta).isEqualTo("{\"nome\":\"Pessoa fisica 11\",\"documento\":\"12345678921\",\"tipoPessoa\":\"PF\",\"carros\":[{\"modelo\":\"Modelo 13\",\"placa\":\"AAA1121\"}]}");
	}
	
	@Test
	public void testeDeCadastroDePessoaJuridicaComLetrasMinusculas() throws Exception {
		String jsonResposta = this.mockMvc
				.perform(post("/pessoa/juridica/cadastrar").contentType(MediaType.APPLICATION_JSON_UTF8)
						.content("{\"nome\":\"Pessoa juridica 11\",\"documento\":\"12345678901244\",\"tipoPessoa\":\"pj\",\"carros\":[{\"modelo\":\"Modelo 14\",\"placa\":\"aaa1122\"}]}"))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		assertThat(jsonResposta).isEqualTo("{\"nome\":\"Pessoa juridica 11\",\"documento\":\"12345678901244\",\"tipoPessoa\":\"PJ\",\"carros\":[{\"modelo\":\"Modelo 14\",\"placa\":\"AAA1122\"}]}");
	}
	
	@Test
	public void testeDeCadastroDePessoaFisicaComTipoPessoaErrado() throws Exception {
		String mensagem = this.mockMvc
				.perform(post("/pessoa/fisica/cadastrar").contentType(MediaType.APPLICATION_JSON_UTF8)
						.content("{\"nome\":\"Pessoa fisica 11\",\"documento\":\"12345678921\",\"tipoPessoa\":\"PJ\",\"carros\":[{\"modelo\":\"Modelo 13\",\"placa\":\"AAA1121\"}]}"))
				.andExpect(status().isBadRequest()).andReturn().getResolvedException().getMessage();

		assertThat(mensagem).isEqualTo("Tipo de pessoa indeterminado");
	}
	
	@Test
	public void testeDeCadastroDePessoaJuridicaComTipoPessoaErrado() throws Exception {
		String mensagem = this.mockMvc
				.perform(post("/pessoa/juridica/cadastrar").contentType(MediaType.APPLICATION_JSON_UTF8)
						.content("{\"nome\":\"Pessoa juridica 11\",\"documento\":\"12345678901244\",\"tipoPessoa\":\"PF\",\"carros\":[{\"modelo\":\"Modelo 14\",\"placa\":\"AAA1122\"}]}"))
				.andExpect(status().isBadRequest()).andReturn().getResolvedException().getMessage();

		assertThat(mensagem).isEqualTo("Tipo de pessoa indeterminado");
	}
	
	@Test
	public void testeDeCadastroDePessoaFisicaSemCPF() throws Exception {
		String message = this.mockMvc
				.perform(post("/pessoa/fisica/cadastrar").contentType(MediaType.APPLICATION_JSON_UTF8)
						.content("{\"nome\":\"Pessoa fisica 11\",\"tipoPessoa\":\"PF\",\"carros\":[{\"modelo\":\"Modelo 13\",\"placa\":\"AAA1121\"}]}"))
				.andExpect(status().isBadRequest()).andReturn().getResolvedException().getMessage();

		assertThat(message).isEqualTo("Documento deve ser preenchido");
	}
	
	@Test
	public void testeDeCadastroDePessoaJuridicaSemCNPJ() throws Exception {
		String message = this.mockMvc
				.perform(post("/pessoa/juridica/cadastrar").contentType(MediaType.APPLICATION_JSON_UTF8)
						.content("{\"nome\":\"Pessoa juridica 11\",\"tipoPessoa\":\"PJ\",\"carros\":[{\"modelo\":\"Modelo 14\",\"placa\":\"AAA1122\"}]}"))
				.andExpect(status().isBadRequest()).andReturn().getResolvedException().getMessage();

		assertThat(message).isEqualTo("Documento deve ser preenchido");
	}
	
	@Test
	public void testeDeCadastroDePessoaFisicaComCPFInvalidoTamanhoErrado() throws Exception {
		String message = this.mockMvc
				.perform(post("/pessoa/fisica/cadastrar").contentType(MediaType.APPLICATION_JSON_UTF8)
						.content("{\"nome\":\"Pessoa fisica 11\",\"documento\":\"123456789\",\"tipoPessoa\":\"PF\",\"carros\":[{\"modelo\":\"Modelo 13\",\"placa\":\"AAA1121\"}]}"))
				.andExpect(status().isBadRequest()).andReturn().getResolvedException().getMessage();

		assertThat(message).isEqualTo("Documento deve conter os 11 caracteres do CPF");
	}
	
	@Test
	public void testeDeCadastroDePessoaJuridicaComCNPJInvalidoTamanhoErrado() throws Exception {
		String message = this.mockMvc
				.perform(post("/pessoa/juridica/cadastrar").contentType(MediaType.APPLICATION_JSON_UTF8)
						.content("{\"nome\":\"Pessoa juridica 11\",\"documento\":\"123456789012\",\"tipoPessoa\":\"PJ\",\"carros\":[{\"modelo\":\"Modelo 14\",\"placa\":\"AAA1122\"}]}"))
				.andExpect(status().isBadRequest()).andReturn().getResolvedException().getMessage();

		assertThat(message).isEqualTo("Documento deve conter os 14 caracteres do CNPJ");
	}
	
	@Test
	public void testeDeCadastroDePessoaFisicaComCPFInvalidoPossuindoLetras() throws Exception {
		String message = this.mockMvc
				.perform(post("/pessoa/fisica/cadastrar").contentType(MediaType.APPLICATION_JSON_UTF8)
						.content("{\"nome\":\"Pessoa fisica 11\",\"documento\":\"123.456.789-01\",\"tipoPessoa\":\"PF\",\"carros\":[{\"modelo\":\"Modelo 13\",\"placa\":\"AAA1121\"}]}"))
				.andExpect(status().isBadRequest()).andReturn().getResolvedException().getMessage();

		assertThat(message).isEqualTo("Documento deve possuir apenas numeros");
	}
	
	@Test
	public void testeDeCadastroDePessoaJuridicaComCNPJInvalidoPossuindoLetras() throws Exception {
		String message = this.mockMvc
				.perform(post("/pessoa/juridica/cadastrar").contentType(MediaType.APPLICATION_JSON_UTF8)
						.content("{\"nome\":\"Pessoa juridica 11\",\"documento\":\"12.345.678/9012-34\",\"tipoPessoa\":\"PJ\",\"carros\":[{\"modelo\":\"Modelo 14\",\"placa\":\"AAA1122\"}]}"))
				.andExpect(status().isBadRequest()).andReturn().getResolvedException().getMessage();

		assertThat(message).isEqualTo("Documento deve possuir apenas numeros");
	}
	
	@Test
	public void testeDeCadastroDePessoaFisicaSemCampoTipoPessoa() throws Exception {
		String message = this.mockMvc
				.perform(post("/pessoa/fisica/cadastrar").contentType(MediaType.APPLICATION_JSON_UTF8)
						.content("{\"nome\":\"Pessoa fisica 11\",\"documento\":\"12345678921\",\"carros\":[{\"modelo\":\"Modelo 13\",\"placa\":\"AAA1121\"}]}"))
				.andExpect(status().isBadRequest()).andReturn().getResolvedException().getMessage();

		assertThat(message).isEqualTo("Tipo de pessoa deve ser preenchido");
	}
	
	@Test
	public void testeDeCadastroDePessoaJuridicaSemCampoTipoPessoa() throws Exception {
		String message = this.mockMvc
				.perform(post("/pessoa/juridica/cadastrar").contentType(MediaType.APPLICATION_JSON_UTF8)
						.content("{\"nome\":\"Pessoa juridica 11\",\"documento\":\"12345678901244\",\"carros\":[{\"modelo\":\"Modelo 14\",\"placa\":\"AAA1122\"}]}"))
				.andExpect(status().isBadRequest()).andReturn().getResolvedException().getMessage();

		assertThat(message).isEqualTo("Tipo de pessoa deve ser preenchido");
	}
	
	@Test
	public void testeDeCadastroDePessoaFisicaSemCampoNome() throws Exception {
		String message = this.mockMvc
				.perform(post("/pessoa/fisica/cadastrar").contentType(MediaType.APPLICATION_JSON_UTF8)
						.content("{	\"documento\":\"12345678921\",\"tipoPessoa\":\"PF\",\"carros\":[{\"modelo\":\"Modelo 13\",\"placa\":\"AAA1121\"}]}"))
				.andExpect(status().isBadRequest()).andReturn().getResolvedException().getMessage();

		assertThat(message).isEqualTo("Nome deve ser preenchido");
	}
	
	@Test
	public void testeDeCadastroDePessoaJuridicaSemCampoNome() throws Exception {
		String message = this.mockMvc
				.perform(post("/pessoa/juridica/cadastrar").contentType(MediaType.APPLICATION_JSON_UTF8)
						.content("{\"documento\":\"12345678901244\",\"tipoPessoa\":\"PJ\",\"carros\":[{\"modelo\":\"Modelo 14\",\"placa\":\"AAA1122\"}]}"))
				.andExpect(status().isBadRequest()).andReturn().getResolvedException().getMessage();

		assertThat(message).isEqualTo("Nome deve ser preenchido");
	}
	
	@Test
	public void testeDeCadastroDePessoaFisicaSemCarroVinculado() throws Exception {
		String message = this.mockMvc
				.perform(post("/pessoa/fisica/cadastrar").contentType(MediaType.APPLICATION_JSON_UTF8)
						.content("{\"nome\":\"Pessoa fisica 11\",\"documento\":\"12345678921\",\"tipoPessoa\":\"PF\"}"))
				.andExpect(status().isBadRequest()).andReturn().getResolvedException().getMessage();

		assertThat(message).isEqualTo("Pessoa deve possuir ao menos um carro para cadastro");
	}
	
	@Test
	public void testeDeCadastroDePessoaJuridicaSemCarroVinculado() throws Exception {
		String message = this.mockMvc
				.perform(post("/pessoa/juridica/cadastrar").contentType(MediaType.APPLICATION_JSON_UTF8)
						.content("{\"nome\":\"Pessoa juridica 11\",\"documento\":\"12345678901244\",\"tipoPessoa\":\"PJ\"}"))
				.andExpect(status().isBadRequest()).andReturn().getResolvedException().getMessage();

		assertThat(message).isEqualTo("Pessoa deve possuir ao menos um carro para cadastro");
	}
	
	@Test
	public void testeDeCadastroDePessoaFisicaComCarroSemModelo() throws Exception {
		String message = this.mockMvc
				.perform(post("/pessoa/fisica/cadastrar").contentType(MediaType.APPLICATION_JSON_UTF8)
						.content("{\"nome\":\"Pessoa fisica 11\",\"documento\":\"12345678921\",\"tipoPessoa\":\"PF\",\"carros\":[{\"placa\":\"AAA1121\"}]}"))
				.andExpect(status().isBadRequest()).andReturn().getResolvedException().getMessage();

		assertThat(message).isEqualTo("Carro sem campo modelo");
	}
	
	@Test
	public void testeDeCadastroDePessoaJuridicaComCarroSemModelo() throws Exception {
		String message = this.mockMvc
				.perform(post("/pessoa/juridica/cadastrar").contentType(MediaType.APPLICATION_JSON_UTF8)
						.content("{\"nome\":\"Pessoa juridica 11\",\"documento\":\"12345678901244\",\"tipoPessoa\":\"PJ\",\"carros\":[{\"placa\":\"AAA1122\"}]}"))
				.andExpect(status().isBadRequest()).andReturn().getResolvedException().getMessage();

		assertThat(message).isEqualTo("Carro sem campo modelo");
	}
	
	@Test
	public void testeDeCadastroDePessoaFisicaComCarroSemPlaca() throws Exception {
		String message = this.mockMvc
				.perform(post("/pessoa/fisica/cadastrar").contentType(MediaType.APPLICATION_JSON_UTF8)
						.content("{\"nome\":\"Pessoa fisica 11\",\"documento\":\"12345678921\",\"tipoPessoa\":\"PF\",\"carros\":[{\"modelo\":\"Modelo 13\"}]}"))
				.andExpect(status().isBadRequest()).andReturn().getResolvedException().getMessage();

		assertThat(message).isEqualTo("Carro sem campo placa");
	}
	
	@Test
	public void testeDeCadastroDePessoaJuridicaComCarroSemPlaca() throws Exception {
		String message = this.mockMvc
				.perform(post("/pessoa/juridica/cadastrar").contentType(MediaType.APPLICATION_JSON_UTF8)
						.content("{\"nome\":\"Pessoa juridica 11\",\"documento\":\"12345678901244\",\"tipoPessoa\":\"PJ\",\"carros\":[{\"modelo\":\"Modelo 14\"}]}"))
				.andExpect(status().isBadRequest()).andReturn().getResolvedException().getMessage();

		assertThat(message).isEqualTo("Carro sem campo placa");
	}
	
	@Test
	public void testeDeCadastroDePessoaFisicaComCarroComPlacaInvalida() throws Exception {
		String message = this.mockMvc
				.perform(post("/pessoa/fisica/cadastrar").contentType(MediaType.APPLICATION_JSON_UTF8)
						.content("{\"nome\":\"Pessoa fisica 11\",\"documento\":\"12345678921\",\"tipoPessoa\":\"PF\",\"carros\":[{\"modelo\":\"Modelo 13\",\"placa\":\"AAA-1121\"}]}"))
				.andExpect(status().isBadRequest()).andReturn().getResolvedException().getMessage();

		assertThat(message).isEqualTo("Placa fora de formato padronizado");
	}
	
	@Test
	public void testeDeCadastroDePessoaJuridicaComCarroComPlacaInvalida() throws Exception {
		String message = this.mockMvc
				.perform(post("/pessoa/juridica/cadastrar").contentType(MediaType.APPLICATION_JSON_UTF8)
						.content("{\"nome\":\"Pessoa juridica 11\",\"documento\":\"12345678901244\",\"tipoPessoa\":\"PJ\",\"carros\":[{\"modelo\":\"Modelo 14\",\"placa\":\"AAA-1122\"}]}"))
				.andExpect(status().isBadRequest()).andReturn().getResolvedException().getMessage();

		assertThat(message).isEqualTo("Placa fora de formato padronizado");
	}
	
	@Test
	public void testeDeCadastroDePessoaFisicaComCPFDuplicado() throws Exception {
		String message = this.mockMvc
				.perform(post("/pessoa/fisica/cadastrar").contentType(MediaType.APPLICATION_JSON_UTF8)
						.content("{\"nome\":\"Pessoa fisica 11\",\"documento\":\"12345678901\",\"tipoPessoa\":\"PF\",\"carros\":[{\"modelo\":\"Modelo 13\",\"placa\":\"AAA1121\"}]}"))
				.andExpect(status().isBadRequest()).andReturn().getResolvedException().getMessage();

		assertThat(message).isEqualTo("Registro de pessoa existente na base");
	}
	
	@Test
	public void testeDeCadastroDePessoaJuridicaComCNPJDuplicado() throws Exception {
		String message = this.mockMvc
				.perform(post("/pessoa/juridica/cadastrar").contentType(MediaType.APPLICATION_JSON_UTF8)
						.content("{\"nome\":\"Pessoa juridica 11\",\"documento\":\"12345678901234\",\"tipoPessoa\":\"PJ\",\"carros\":[{\"modelo\":\"Modelo 14\",\"placa\":\"AAA1122\"}]}"))
				.andExpect(status().isBadRequest()).andReturn().getResolvedException().getMessage();

		assertThat(message).isEqualTo("Registro de pessoa existente na base");
	}
	
	@Test
	public void testeDeCadastroDePessoaFisicaComCarroDuplicado() throws Exception {
		String message = this.mockMvc
				.perform(post("/pessoa/fisica/cadastrar").contentType(MediaType.APPLICATION_JSON_UTF8)
						.content("{\"nome\":\"Pessoa fisica 11\",\"documento\":\"12345678921\",\"tipoPessoa\":\"PF\",\"carros\":[{\"modelo\":\"Modelo 13\",\"placa\":\"AAA1111\"}]}"))
				.andExpect(status().isBadRequest()).andReturn().getResolvedException().getMessage();

		assertThat(message).isEqualTo("Registro de carro existente na base");
	}
	
	@Test
	public void testeDeCadastroDePessoaJuridicaComCarroDuplicado() throws Exception {
		String message = this.mockMvc
				.perform(post("/pessoa/juridica/cadastrar").contentType(MediaType.APPLICATION_JSON_UTF8)
						.content("{\"nome\":\"Pessoa juridica 11\",\"documento\":\"12345678901244\",\"tipoPessoa\":\"PJ\",\"carros\":[{\"modelo\":\"Modelo 14\",\"placa\":\"AAA1114\"}]}"))
				.andExpect(status().isBadRequest()).andReturn().getResolvedException().getMessage();

		assertThat(message).isEqualTo("Registro de carro existente na base");
	}
	
	
	
	@Test
	public void testeDeAtualizacaoDePessoaFisicaApenasNome() throws Exception {
		String jsonResposta = this.mockMvc
				.perform(post("/pessoa/fisica/atualizar?documento=12345678902").contentType(MediaType.APPLICATION_JSON_UTF8)
						.content("{\"nome\":\"Pessoa fisica 11\"}"))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		assertThat(jsonResposta).isEqualTo("{\"nome\":\"Pessoa fisica 11\",\"documento\":\"12345678902\",\"tipoPessoa\":\"PF\",\"carros\":[{\"modelo\":\"Modelo 2\",\"placa\":\"AAA1112\"},{\"modelo\":\"Modelo 3\",\"placa\":\"AAA1113\"}]}");
	}
	
	@Test
	public void testeDeAtualizacaoDePessoaJuridicaApenasNome() throws Exception {
		String jsonResposta = this.mockMvc
				.perform(post("/pessoa/juridica/atualizar?documento=12345678901235").contentType(MediaType.APPLICATION_JSON_UTF8)
						.content("{\"nome\":\"Pessoa juridica 11\"}"))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		assertThat(jsonResposta).isEqualTo("{\"nome\":\"Pessoa juridica 11\",\"documento\":\"12345678901235\",\"tipoPessoa\":\"PJ\",\"carros\":[{\"modelo\":\"Modelo 5\",\"placa\":\"AAA1115\"},{\"modelo\":\"Modelo 6\",\"placa\":\"AAA1116\"}]}");
	}
	
	@Test
	public void testeDeAtualizacaoDePessoaFisicaComNomeECarroAMenos() throws Exception {
		String jsonResposta = this.mockMvc
				.perform(post("/pessoa/fisica/atualizar?documento=12345678902").contentType(MediaType.APPLICATION_JSON_UTF8)
						.content("{\"nome\":\"Pessoa fisica 11\",\"carros\":[{\"modelo\":\"Modelo 2\",\"placa\":\"AAA1112\"}]}"))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		assertThat(jsonResposta).isEqualTo("{\"nome\":\"Pessoa fisica 11\",\"documento\":\"12345678902\",\"tipoPessoa\":\"PF\",\"carros\":[{\"modelo\":\"Modelo 2\",\"placa\":\"AAA1112\"}]}");
	}
	
	@Test
	public void testeDeAtualizacaoDePessoaJuridicaComNomeECarroAMenos() throws Exception {
		String jsonResposta = this.mockMvc
				.perform(post("/pessoa/juridica/atualizar?documento=12345678901235").contentType(MediaType.APPLICATION_JSON_UTF8)
						.content("{\"nome\":\"Pessoa juridica 11\",\"carros\":[{\"modelo\":\"Modelo 5\",\"placa\":\"AAA1115\"}]}"))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		assertThat(jsonResposta).isEqualTo("{\"nome\":\"Pessoa juridica 11\",\"documento\":\"12345678901235\",\"tipoPessoa\":\"PJ\",\"carros\":[{\"modelo\":\"Modelo 5\",\"placa\":\"AAA1115\"}]}");
	}
	
	@Test
	public void testeDeAtualizacaoDePessoaFisicaComCarroAMenos() throws Exception {
		String jsonResposta = this.mockMvc
				.perform(post("/pessoa/fisica/atualizar?documento=12345678902").contentType(MediaType.APPLICATION_JSON_UTF8)
						.content("{\"carros\":[{\"modelo\":\"Modelo 2\",\"placa\":\"AAA1112\"}]}"))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		assertThat(jsonResposta).isEqualTo("{\"nome\":\"Pessoa fisica 2\",\"documento\":\"12345678902\",\"tipoPessoa\":\"PF\",\"carros\":[{\"modelo\":\"Modelo 2\",\"placa\":\"AAA1112\"}]}");
	}
	
	@Test
	public void testeDeAtualizacaoDePessoaJuridicaComCarroAMenos() throws Exception {
		String jsonResposta = this.mockMvc
				.perform(post("/pessoa/juridica/atualizar?documento=12345678901235").contentType(MediaType.APPLICATION_JSON_UTF8)
						.content("{\"carros\":[{\"modelo\":\"Modelo 5\",\"placa\":\"AAA1115\"}]}"))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		assertThat(jsonResposta).isEqualTo("{\"nome\":\"Pessoa juridica 2\",\"documento\":\"12345678901235\",\"tipoPessoa\":\"PJ\",\"carros\":[{\"modelo\":\"Modelo 5\",\"placa\":\"AAA1115\"}]}");
	}
	
	@Test
	public void testeDeAtualizacaoDePessoaFisicaComCarroVinculadoAOutraPessoa() throws Exception {
		String mensagem = this.mockMvc
				.perform(post("/pessoa/fisica/atualizar?documento=12345678902").contentType(MediaType.APPLICATION_JSON_UTF8)
						.content("{\"carros\":[{\"modelo\":\"Modelo 1\",\"placa\":\"AAA1111\"}]}"))
				.andExpect(status().isBadRequest()).andReturn().getResolvedException().getMessage();

		assertThat(mensagem).isEqualTo("Carro cadastrado em nome de outra pessoa");
	}
	
	@Test
	public void testeDeAtualizacaoDePessoaJuridicaComCarroVinculadoAOutraPessoa() throws Exception {
		String mensagem = this.mockMvc
				.perform(post("/pessoa/juridica/atualizar?documento=12345678901235").contentType(MediaType.APPLICATION_JSON_UTF8)
						.content("{\"carros\":[{\"modelo\":\"Modelo 4\",\"placa\":\"AAA1114\"}]}"))
				.andExpect(status().isBadRequest()).andReturn().getResolvedException().getMessage();

		assertThat(mensagem).isEqualTo("Carro cadastrado em nome de outra pessoa");
	}
	
	@Test
	public void testeDeAtualizacaoDePessoaFisicaComNomeECarroAMenosEAtualizacaoDeDocumentoIgnorada() throws Exception {
		String jsonResposta = this.mockMvc
				.perform(post("/pessoa/fisica/atualizar?documento=12345678902").contentType(MediaType.APPLICATION_JSON_UTF8)
						.content("{\"nome\":\"Pessoa fisica 11\",\"documento\":\"12345678905\",\"carros\":[{\"modelo\":\"Modelo 2\",\"placa\":\"AAA1112\"}]}"))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		assertThat(jsonResposta).isEqualTo("{\"nome\":\"Pessoa fisica 11\",\"documento\":\"12345678902\",\"tipoPessoa\":\"PF\",\"carros\":[{\"modelo\":\"Modelo 2\",\"placa\":\"AAA1112\"}]}");
	}
	
	@Test
	public void testeDeAtualizacaoDePessoaJuridicaComNomeECarroAMenosEAtualizacaoDeDocumentoIgnorada() throws Exception {
		String jsonResposta = this.mockMvc
				.perform(post("/pessoa/juridica/atualizar?documento=12345678901235").contentType(MediaType.APPLICATION_JSON_UTF8)
						.content("{\"nome\":\"Pessoa juridica 11\",\"documento\":\"12345678901238\",\"carros\":[{\"modelo\":\"Modelo 5\",\"placa\":\"AAA1115\"}]}"))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		assertThat(jsonResposta).isEqualTo("{\"nome\":\"Pessoa juridica 11\",\"documento\":\"12345678901235\",\"tipoPessoa\":\"PJ\",\"carros\":[{\"modelo\":\"Modelo 5\",\"placa\":\"AAA1115\"}]}");
	}
	
	@Test
	public void testeDeAtualizacaoDePessoaFisicaComCarroAMais() throws Exception {
		String jsonResposta = this.mockMvc
				.perform(post("/pessoa/fisica/atualizar?documento=12345678902").contentType(MediaType.APPLICATION_JSON_UTF8)
						.content("{\"carros\":[{\"modelo\":\"Modelo 2\",\"placa\":\"AAA1112\"},{\"modelo\":\"Modelo 3\",\"placa\":\"AAA1113\"},{\"modelo\":\"Modelo 11\",\"placa\":\"AAA1121\"}]}"))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		assertThat(jsonResposta).isEqualTo("{\"nome\":\"Pessoa fisica 2\",\"documento\":\"12345678902\",\"tipoPessoa\":\"PF\",\"carros\":[{\"modelo\":\"Modelo 2\",\"placa\":\"AAA1112\"},{\"modelo\":\"Modelo 3\",\"placa\":\"AAA1113\"},{\"modelo\":\"Modelo 11\",\"placa\":\"AAA1121\"}]}");
	}
	
	@Test
	public void testeDeAtualizacaoDePessoaJuridicaComCarroAMais() throws Exception {
		String jsonResposta = this.mockMvc
				.perform(post("/pessoa/juridica/atualizar?documento=12345678901235").contentType(MediaType.APPLICATION_JSON_UTF8)
						.content("{\"carros\":[{\"modelo\":\"Modelo 5\",\"placa\":\"AAA1115\"},{\"modelo\":\"Modelo 6\",\"placa\":\"AAA1116\"},{\"modelo\":\"Modelo 12\",\"placa\":\"AAA1122\"}]}"))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		assertThat(jsonResposta).isEqualTo("{\"nome\":\"Pessoa juridica 2\",\"documento\":\"12345678901235\",\"tipoPessoa\":\"PJ\",\"carros\":[{\"modelo\":\"Modelo 5\",\"placa\":\"AAA1115\"},{\"modelo\":\"Modelo 6\",\"placa\":\"AAA1116\"},{\"modelo\":\"Modelo 12\",\"placa\":\"AAA1122\"}]}");
	}

}
