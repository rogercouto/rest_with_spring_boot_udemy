package br.com.erudio.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.erudio.data.model.Person;
import br.com.erudio.data.parser.DozerParser;
import br.com.erudio.data.vo.PersonVO;
import br.com.erudio.exception.ResourceNotFoundException;
import br.com.erudio.repositories.PersonRepository;

@Service
public class PersonService {

	@Autowired
	private PersonRepository repository;
	
	
	public PersonVO create(PersonVO person) {
		Person entity = DozerParser.parseObject(person, Person.class);
		entity = repository.save(entity);
		return DozerParser.parseObject(entity, PersonVO.class);
	}
	
	public PersonVO update(PersonVO person) throws ResourceNotFoundException {
		Person entity = DozerParser.parseObject(person, Person.class);
		repository.findById(person.getKey()).orElseThrow(
				()-> new ResourceNotFoundException(
						String.format("Cannot update, person with id = %d not found", person.getKey())));
		entity = repository.save(entity);
		return DozerParser.parseObject(entity, PersonVO.class);
	}
	
	public void delete(long id) throws ResourceNotFoundException {
		PersonVO person = findById(id);
		Person entity = DozerParser.parseObject(person, Person.class);
		repository.delete(entity);
	}
	
	public PersonVO findById(Long id) throws ResourceNotFoundException {
		Person entity = repository.findById(id).orElseThrow(
				()-> new ResourceNotFoundException(
						String.format("Person not found with id = %d", id)));
		return DozerParser.parseObject(entity, PersonVO.class);
	}
	
	public List<PersonVO> findAll(){
		List<Person> persons = repository.findAll();
		return DozerParser.parseListObjects(persons, PersonVO.class);
	}
	
}
