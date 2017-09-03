/**
 * 
 */
package com.my.example.project.crud.client.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.my.example.project.crud.client.model.Person;

/**
 * @author Daniel Ferraz
 * @since 3 de set de 2017
 *
 */
@RestController
@RequestMapping("/persons/{personId}")
public class PersonController {
	
	@RequestMapping(method=RequestMethod.PUT)
	public void updatePerson(@PathVariable Long personId, @RequestBody Person person) {
		
	}

}
