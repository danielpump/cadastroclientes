/**
 * 
 */
package com.semnome.servicos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.semnome.model.Carro;
import com.semnome.repostorios.CarroRepository;

/**
 * @author Daniel Ferraz
 * @since 4 de ago de 2017
 *
 */
@Service
public class CarroService {

	@Autowired
	private CarroRepository carroRepository;

	public void grava(Carro carro) {
		carroRepository.save(carro);
	}

	public List<Carro> listaTudo() {
		
		ArrayList<Carro> carros = new ArrayList<>();		
		for (Carro carro : carroRepository.findAll()) {
			carros.add(carro);
		}		
		return carros; 
	}

}
