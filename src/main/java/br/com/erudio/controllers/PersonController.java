package br.com.erudio.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.erudio.exception.ResourceNotExistsException;
import br.com.erudio.exception.UnsupportedConversionException;
import br.com.erudio.model.Person;
import br.com.erudio.services.PersonService;
import br.com.erudio.util.NumberUtil;

@RestController
@RequestMapping("/person")
public class PersonController {

	@Autowired
	private PersonService service;
	
	@RequestMapping(value="/{id}", 
			method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE  )
	public Person findById(@PathVariable("id") String id) throws UnsupportedConversionException, ResourceNotExistsException {
		return service.findById(NumberUtil.strToLong(id));
	}
	
	@RequestMapping( 
			method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE  )
	public List<Person> findAll() throws UnsupportedConversionException, ResourceNotExistsException {
		return service.findAll();
	}
	
	@RequestMapping(
				method = RequestMethod.POST,
				consumes = MediaType.APPLICATION_JSON_VALUE,
				produces = MediaType.APPLICATION_JSON_VALUE 
			)
	public Person create(@RequestBody Person person){
		return service.create(person);
	}
	
	@RequestMapping(
			
			method = RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE 
		)
	public Person update(@RequestBody Person person) throws ResourceNotExistsException{
		return service.update(person);
	}
	
	@RequestMapping(
			value="/{id}",
			method = RequestMethod.DELETE
		)
	public void delete(@PathVariable("id") String id) throws ResourceNotExistsException, UnsupportedConversionException{
		service.delete(NumberUtil.strToLong(id));
	}
}
