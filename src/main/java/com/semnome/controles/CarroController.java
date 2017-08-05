/**
 * 
 */
package com.semnome.controles;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.semnome.model.Carro;
import com.semnome.servicos.CarroService;

/**
 * @author Daniel Ferraz
 * @since 4 de ago de 2017
 *
 */
@RestController
public class CarroController {
	
	@Autowired
	private CarroService carroService;
	
	@RequestMapping(path="/")
	public String home() {
		return "Oi";
	}

	@RequestMapping(path="/grava")
	public String cadastra() {
		
		Carro carro = new Carro();
		
		carro.setModelo("Teste");
		carro.setPlaca("AAA8899");
		
		carroService.grava(carro);
		
		return "Sucesso";
	}
	
	@RequestMapping(path="/lista")
	public List<Carro> lista(){
		return carroService.listaTudo();
	}
	
}
