package br.com.erudio.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.erudio.data.model.Book;
import br.com.erudio.data.parser.DozerParser;
import br.com.erudio.data.vo.BookVO;
import br.com.erudio.exception.ResourceNotFoundException;
import br.com.erudio.repositories.BookRepository;

@Service
public class BookService {

	@Autowired
	private BookRepository repository;
	
	private Book findEntityById(Integer id) throws ResourceNotFoundException {
		var book = repository.findById(id);
		if (book.isEmpty())
			throw new ResourceNotFoundException(String.format("Book with id = %d not found", id));
		return book.get();
	}
	
	public BookVO findById(Integer id) throws ResourceNotFoundException {
		return DozerParser.parseObject(findEntityById(id), BookVO.class);
	}
	public List<BookVO> findAll(){
		var books = repository.findAll();
		return DozerParser.parseListObjects(books, BookVO.class); 
	}
	
	public BookVO create(BookVO book) {
		var entity = DozerParser.parseObject(book, Book.class);
		var createdEntity = repository.save(entity);
		return DozerParser.parseObject(createdEntity, BookVO.class);
	}
	
	public BookVO update(BookVO book) throws ResourceNotFoundException {
		var entity = findEntityById(book.getKey());
		if (book.equalsEntity(entity))
			return book;//don't need update
		var parsedEntity = DozerParser.parseObject(book, Book.class);
		var savedEntity = repository.save(parsedEntity);
		return DozerParser.parseObject(savedEntity, BookVO.class);
	}
	
	public void delete(Integer id) throws ResourceNotFoundException {
		var book = findEntityById(id);
		repository.delete(book);
	}
	
}
