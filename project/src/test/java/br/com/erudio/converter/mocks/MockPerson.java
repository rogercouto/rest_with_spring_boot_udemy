package br.com.erudio.converter.mocks;

import java.util.ArrayList;
import java.util.List;

import br.com.erudio.data.model.Person;
import br.com.erudio.data.vo.PersonVO;
import br.com.erudio.util.NumberUtil;

public class MockPerson {

	public Person mockEntity(long id) {
		Person person = new Person();
		person.setId(id);
		person.setFirstName("Person");
		person.setLastName(String.format("Test Nº %d", id));
		person.setAddress("Some adress");
		person.setGender(NumberUtil.isPair(id) ? "male" : "female");
		return person;
	}
	
	public PersonVO mockVO(long id) {
		PersonVO vo = new PersonVO();
		vo.setKey(id);
		vo.setFirstName("VOPerson");
		vo.setLastName(String.format("Test Nº %d", id));
		vo.setAddress("Some adress");
		vo.setGender(NumberUtil.isPair(id) ? "male" : "female");
		return vo;
	}
	
	public Person mockEntity() {
		return mockEntity(1);
	}
	
	public PersonVO mockVO() {
		return mockVO(1);
	}
	
	public List<Person> mockEntities(int size){
		List<Person> list = new ArrayList<>();
		for (int i = 1; i <= size; i++) {
			list.add(mockEntity(i));
		}
		return list;
	}
	
	public List<PersonVO> mockVOs(int size){
		List<PersonVO> list = new ArrayList<>();
		for (int i = 1; i <= size; i++) {
			list.add(mockVO(i));
		}
		return list;
	}
	
}
