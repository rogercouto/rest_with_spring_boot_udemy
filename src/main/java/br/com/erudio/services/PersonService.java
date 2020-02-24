package br.com.erudio.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.erudio.exception.ResourceNotExistsException;
import br.com.erudio.model.Person;

@Service
public class PersonService {

	private final List<Person> persons = new ArrayList<>();
	
	public PersonService() {
		//seeding test persons
		for (long l = 1; l <= 10; l++) {
			persons.add(mockPerson(l));
		}
	}
	
	public Person mockPerson(long id) {
		Person person = new Person();
		person.setId(id);
		person.setFirstName("Person");
		person.setLastName(String.format("Test Nº %d", person.getId()));
		person.setAddress("Test address");
		person.setGender("undefined");
		return person;
	}
	
	public Person findById(Long id) throws ResourceNotExistsException {
		Optional<Person> o = persons.stream().filter(p->p.getId().equals(id)).findFirst();
		if (o.isEmpty())
			throw new ResourceNotExistsException(String.format("Person with id=%d not exists",id));
		return o.get();
	}
	
	public List<Person> findAll(){
		return persons;
	}
	
	public Person create(Person person) {
		person.setId(nextId());
		persons.add(person);
		return person;
	}
	
	public long nextId() {
		Optional<Long> max = persons.stream().map(p->p.getId()).max(Long::compare);
		long nextId = 1;
		if (max.isPresent())
			nextId += max.get().longValue();
		return nextId;
	}
	
	public Person update(Person person) throws ResourceNotExistsException {
		Person lPerson = findById(person.getId());
		int index = persons.indexOf(lPerson);
		persons.set(index, person);
		return person;
	}
	
	public void delete(long id) throws ResourceNotExistsException {
		Person person = findById(id);
		int index = persons.indexOf(person);
		persons.remove(index);
	}
	
}
