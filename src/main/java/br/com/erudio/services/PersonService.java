package br.com.erudio.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.erudio.data.converter.DozerConverter;
import br.com.erudio.data.model.Person;
import br.com.erudio.data.vo.PersonVO;
import br.com.erudio.exception.ResourceNotFoundException;
import br.com.erudio.repositories.PersonRepository;

@Service
public class PersonService {

	@Autowired
	private PersonRepository repository;
	
	
	public PersonVO create(PersonVO person) {
		Person entity = DozerConverter.parseObject(person, Person.class);
		entity = repository.save(entity);
		return DozerConverter.parseObject(entity, PersonVO.class);
	}
	
	public PersonVO update(PersonVO person) throws ResourceNotFoundException {
		Person entity = DozerConverter.parseObject(person, Person.class);
		repository.findById(person.getId()).orElseThrow(
				()-> new ResourceNotFoundException(
						String.format("Cannot update, person with id = %d not found", person.getId())));
		entity = repository.save(entity);
		return DozerConverter.parseObject(entity, PersonVO.class);
	}
	
	public void delete(long id) throws ResourceNotFoundException {
		PersonVO person = findById(id);
		Person entity = DozerConverter.parseObject(person, Person.class);
		repository.delete(entity);
	}
	
	public PersonVO findById(Long id) throws ResourceNotFoundException {
		Person entity = repository.findById(id).orElseThrow(
				()-> new ResourceNotFoundException(
						String.format("Person not found with id = %d", id)));
		return DozerConverter.parseObject(entity, PersonVO.class);
	}
	
	public List<PersonVO> findAll(){
		List<Person> persons = repository.findAll();
		return DozerConverter.parseListObjects(persons, PersonVO.class);
	}
	
}
