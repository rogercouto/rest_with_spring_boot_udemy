package br.com.erudio.converter;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.com.erudio.converter.mocks.MockPerson;
import br.com.erudio.data.converter.DozerConverter;
import br.com.erudio.data.model.Person;
import br.com.erudio.data.vo.PersonVO;

public class DozerConverterTest {

	private MockPerson mock;
	
	@Before
	public void setUp() {
		mock = new MockPerson();
	}
	
	@Test
	public void testPersonToVO() {
		Person p = mock.mockEntity(1);
		assertEquals("Person", p.getFirstName());
		assertEquals("Test Nº 1", p.getLastName());
		assertEquals("Some adress", p.getAddress());
		assertEquals("female", p.getGender());
		PersonVO vo = DozerConverter.parseObject(p, PersonVO.class);
		assertEquals("Person", vo.getFirstName());
		assertEquals("Test Nº 1", vo.getLastName());
		assertEquals("Some adress", vo.getAddress());
		assertEquals("female", vo.getGender());
	}
	
	@Test
	public void testVOToPerson() {
		PersonVO vo = mock.mockVO(2);
		assertEquals("VOPerson", vo.getFirstName());
		assertEquals("Test Nº 2", vo.getLastName());
		assertEquals("Some adress", vo.getAddress());
		assertEquals("male", vo.getGender());
		Person p = DozerConverter.parseObject(vo, Person.class);
		assertEquals("VOPerson", p.getFirstName());
		assertEquals("Test Nº 2", p.getLastName());
		assertEquals("Some adress", p.getAddress());
		assertEquals("male", p.getGender());
	}
	
	@Test
	public void testPersonsTOVOList() {
		List<Person> persons = mock.mockEntities(10);
		List<PersonVO> vos = DozerConverter.parseListObjects(persons, PersonVO.class);
		assertEquals(vos.size(), persons.size());
		for (int i = 0; i < persons.size(); i++) {
			Person p = persons.get(i);
			PersonVO vo = vos.get(i);
			assertEquals(p.getFirstName(), vo.getFirstName());
			assertEquals(p.getLastName(), vo.getLastName());
			assertEquals(p.getAddress(), vo.getAddress());
			assertEquals(p.getGender(), vo.getGender());
		}
	}
	
	@Test
	public void testVOsToPersonList() {
		List<PersonVO> vos = mock.mockVOs(10);
		List<Person> persons = DozerConverter.parseListObjects(vos, Person.class);
		assertEquals(persons.size(), vos.size());
		for (int i = 0; i < persons.size(); i++) {
			PersonVO vo = vos.get(i);
			Person p = persons.get(i);
			assertEquals(vo.getFirstName(), p.getFirstName());
			assertEquals(vo.getLastName(), p.getLastName());
			assertEquals(vo.getAddress(), p.getAddress());
			assertEquals(vo.getGender(), p.getGender());
		}
	}
	
	
}
