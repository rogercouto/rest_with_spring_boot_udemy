package br.com.erudio.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.erudio.exception.ResourceNotFoundException;
import br.com.erudio.model.Person;
import br.com.erudio.repositories.PersonRepository;

@Service
public class PersonService {

	@Autowired
	private PersonRepository repository;
	
	
	public Person create(Person person) {
		return repository.save(person);
	}
	
	public Person update(Person person) throws ResourceNotFoundException {
		repository.findById(person.getId()).orElseThrow(
				()-> new ResourceNotFoundException(
						String.format("Cannot update, person with id = %d not found", person.getId())));
		return repository.save(person);
	}
	
	public void delete(long id) throws ResourceNotFoundException {
		Person person = findById(id);
		repository.delete(person);
	}
	
	public Person findById(Long id) throws ResourceNotFoundException {
		return repository.findById(id).orElseThrow(
				()-> new ResourceNotFoundException(
						String.format("Person not found with id = %d", id)));
	}
	
	public List<Person> findAll(){
		return repository.findAll();
	}
	
}
