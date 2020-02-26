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

import br.com.erudio.data.vo.PersonVO;
import br.com.erudio.exception.ResourceNotFoundException;
import br.com.erudio.exception.UnsupportedConversionException;
import br.com.erudio.services.PersonService;

@RestController
@RequestMapping("/person")
public class PersonController {

	@Autowired
	private PersonService service;
	
	@GetMapping(value="/{id}")
	public PersonVO findById(@PathVariable("id") long id) throws UnsupportedConversionException, ResourceNotFoundException {
		return service.findById(id);
	}
	
	@GetMapping
	public List<PersonVO> findAll() throws UnsupportedConversionException, ResourceNotFoundException {
		return service.findAll();
	}
	
	@PostMapping
	public PersonVO create(@RequestBody PersonVO person){
		return service.create(person);
	}
	
	@PutMapping
	public PersonVO update(@RequestBody PersonVO person) throws ResourceNotFoundException{
		return service.update(person);
	}
	
	@DeleteMapping(value="/{id}")
	public void delete(@PathVariable("id") long id) throws ResourceNotFoundException, UnsupportedConversionException{
		service.delete(id);
	}
}
