package br.com.erudio.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.erudio.exception.ResourceNotFoundException;
import br.com.erudio.exception.UnsupportedConversionException;
import br.com.erudio.model.Person;
import br.com.erudio.services.PersonService;

@RestController
@RequestMapping("/person")
public class PersonController {

	@Autowired
	private PersonService service;
	
	@GetMapping(value="/{id}")
	public Person findById(@PathVariable("id") long id) throws UnsupportedConversionException, ResourceNotFoundException {
		return service.findById(id);
	}
	
	@GetMapping
	public List<Person> findAll() throws UnsupportedConversionException, ResourceNotFoundException {
		return service.findAll();
	}
	
	@PostMapping
	public Person create(@RequestBody Person person){
		return service.create(person);
	}
	
	@PutMapping
	public Person update(@RequestBody Person person) throws ResourceNotFoundException{
		return service.update(person);
	}
	
	@DeleteMapping(value="/{id}")
	public void delete(@PathVariable("id") long id) throws ResourceNotFoundException, UnsupportedConversionException{
		service.delete(id);
	}
}
