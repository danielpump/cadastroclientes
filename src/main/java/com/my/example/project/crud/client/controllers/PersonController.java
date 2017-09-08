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
@RequestMapping("/persons")
public class PersonController {
	
	@RequestMapping(method=RequestMethod.PUT, path="/{personId}")
	public void updatePerson(@PathVariable Long personId, @RequestBody Person person) {
		
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public void registerPerson(@RequestBody Person person) {
		
	}
	
	@RequestMapping(method=RequestMethod.GET, path="/{personId}")
	public void loadPerson(@PathVariable Long personId) {
		
	}
	
	@RequestMapping(method=RequestMethod.DELETE, path="/{personId}")
	public void deletePerson(@PathVariable Long personId) {
		
	}

}
