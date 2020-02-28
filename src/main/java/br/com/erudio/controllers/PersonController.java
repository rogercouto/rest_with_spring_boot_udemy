package br.com.erudio.controllers;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/api/person")
public class PersonController {

	@Autowired
	private PersonService service;
	
	@GetMapping(value="/{id}", produces = {"application/json", "application/xml", "application/x-yaml"})
	public PersonVO findById(@PathVariable("id") long id) throws UnsupportedConversionException, ResourceNotFoundException {
		PersonVO p = service.findById(id);
		p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel());
		p.add(linkTo(methodOn(PersonController.class).findAll()).withRel("all"));
		return p;
	}
	
	@GetMapping(produces = {"application/json", "application/xml", "application/x-yaml"})
	public List<PersonVO> findAll() {
		List<PersonVO> list = service.findAll();
		list.forEach(p->{
			try {
				p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel());
			}catch(ResourceNotFoundException | UnsupportedConversionException e){
			}
		});
		return list;
	}
	
	@PostMapping(produces = { "application/json", "application/xml", "application/x-yaml" }, 
			consumes = { "application/json", "application/xml", "application/x-yaml" })
	public PersonVO create(@RequestBody PersonVO person){
		PersonVO p = service.create(person);
		try {
			p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel());
			p.add(linkTo(methodOn(PersonController.class).findAll()).withRel("all"));
		} catch (UnsupportedConversionException | ResourceNotFoundException e) {
		}
		return p;
	}
	
	@PutMapping(produces = { "application/json", "application/xml", "application/x-yaml" }, 
			consumes = { "application/json", "application/xml", "application/x-yaml" })
	public PersonVO update(@RequestBody PersonVO person) throws ResourceNotFoundException{
		PersonVO p = service.update(person);
		try {
			p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel());
			p.add(linkTo(methodOn(PersonController.class).findAll()).withRel("all"));
		} catch (UnsupportedConversionException e) {
			e.printStackTrace();
		}
		return p;
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") long id) throws ResourceNotFoundException, UnsupportedConversionException{
		service.delete(id);
		return ResponseEntity.ok().build();
	}
}
