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

}
