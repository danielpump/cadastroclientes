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
	 * @param placa Placa para a consulta
	 * @return Veiculo com a placa ou null caso não seja encontrado
	 */
	Carro findByPlaca(String placa);
	
	/**
	 * Calcula a quantidade de placas em determinado status para o determinado tipo de pessoa
	 * 
	 * @param status Status a ser consultado
	 * @param tipoPessoa Tipo de pessoa a ser consultado
	 * @return Quantidade no status por pessoa
	 */
	@Query("SELECT COUNT(*) FROM Carro carro inner join carro.pessoa pessoa where carro.status=?1 and pessoa.tipoPessoa = ?2")
	Long countByStatusAndTipoPessoa(String status, String tipoPessoa);

}
