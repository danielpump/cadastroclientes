/**
 * 
 */
package com.cadastra.cliente.repostorios;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cadastra.cliente.modelo.Carro;

/**
 * Repositório para o acesso aos dados de ações para um {@link Carro}
 * 
 * @author Daniel Ferraz
 * @since 4 de ago de 2017
 *
 */
public interface CarroRepositorio extends CrudRepository<Carro, Long> {
	
	/**
	 * Busca um carro pela placa
	 * 
	 * @param placa
	 * @return
	 */
	Carro findByPlaca(String placa);
	
	@Query("SELECT COUNT(*) FROM Carro carro inner join carro.pessoa pessoa where carro.status=?1 and pessoa.tipoPessoa = ?2")
	Long countByStatusAndTipoPessoa(String status, String tipoPessoa);

}
