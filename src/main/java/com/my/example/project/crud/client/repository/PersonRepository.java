/**
 * 
 */
package com.my.example.project.crud.client.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.my.example.project.crud.client.model.Car;
import com.my.example.project.crud.client.model.Person;

/**
 * @author Daniel Ferraz
 * @since 3 de set de 2017
 *
 */
@RepositoryRestResource
public interface PersonRepository extends CrudRepository<Person, Long>{

}
