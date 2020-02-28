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

import br.com.erudio.data.vo.BookVO;
import br.com.erudio.exception.ResourceNotFoundException;
import br.com.erudio.services.BookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Book endpoint")
@RestController
@RequestMapping("/api/book")
public class BookController {

	@Autowired
	private BookService service;
	
	private void setLinks(BookVO book){
		try {
			book.add(linkTo(methodOn(BookController.class).findById(book.getKey())).withSelfRel());
			book.add(linkTo(methodOn(BookController.class).findAll()).withRel("all"));
		}catch(ResourceNotFoundException e){
		}
	}
	
	@ApiOperation(value = "Find one book by its id")
	@GetMapping(value="/{id}", produces = {"application/json", "application/xml", "application/x-yaml"})
	public BookVO findById(@PathVariable("id") int id) throws ResourceNotFoundException {
		var book = service.findById(id);
		setLinks(book);
		return book;
	}
	
	@ApiOperation(value = "Find all books")
	@GetMapping(produces = {"application/json", "application/xml", "application/x-yaml"})
	public List<BookVO> findAll() {
		var books = service.findAll();
		books.forEach(book->{
			setLinks(book);
		});
		return books;
	}
	
	@ApiOperation(value = "Insert a new book")
	@PostMapping(produces = {"application/json", "application/xml", "application/x-yaml"},
			consumes = {"application/json", "application/xml", "application/x-yaml"})
	public BookVO create(@RequestBody BookVO book) {
		BookVO newBook = service.create(book);
		setLinks(newBook);
		return newBook;
	}
	
	@ApiOperation(value = "Update book")
	@PutMapping(produces = {"application/json", "application/xml", "application/x-yaml"},
			consumes = {"application/json", "application/xml", "application/x-yaml"})
	public BookVO update(@RequestBody BookVO book) throws ResourceNotFoundException {
		var updatedBook = service.update(book);
		setLinks(updatedBook);
		return updatedBook;
	}
	
	@ApiOperation(value = "Delete book by its id")
	@DeleteMapping(value="/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") int id) throws ResourceNotFoundException{
		service.delete(id);
		return ResponseEntity.ok().build();
	}
	
	
}
