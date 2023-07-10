package pl.coderslab.controllers;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.model.Book;
import pl.coderslab.services.MockBookService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookController {

  private final MockBookService mockBookService;

  @GetMapping("")
  public List<Book> getBookList() {
    log.info("request came to /books GET");
    return mockBookService.getAllBooks();
  }

  // TODO: add functionality to add new book
  // need to create form in html
  @PostMapping(
      value = "",
      consumes = {MediaType.APPLICATION_JSON_VALUE},
      produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<Book> addBook(@RequestBody Book book) {
    log.info("request came to /books POST " + book);
    // check if book is valid and all fields except id are filled
    if (mockBookService.isBookFieldHasValues(book)) {
      return ResponseEntity.badRequest().build();
    }
    // add new book to the list
    log.info("book added to the list " + book);
    mockBookService.addBook(book);
    return ResponseEntity.ok(book);
  }

  // get book by id
  @GetMapping("/{id}")
  public ResponseEntity<Book> getBookById(@PathVariable Long id) {
    log.info("request came to /books/" + id + " GET");
    // check if book with given id exists
    if (mockBookService.getBookById(id).isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(mockBookService.getBookById(id).get());
  }

  // update book by id
  @PutMapping(
      value = "/{id}",
      consumes = {MediaType.APPLICATION_JSON_VALUE},
      produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<Book> updateBookById(@PathVariable Long id, @RequestBody Book book) {
    // check if book with given id exists
    if (mockBookService.getBookById(id).isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    // check if book is valid and all fields except id are filled
    if (mockBookService.isBookFieldHasValues(book)) {
      return ResponseEntity.badRequest().build();
    }
    // update book with given id
    log.info("book updated in the list " + book);
    mockBookService.updateBookById(id, book);
    // get updated book
    book = mockBookService.getBookById(id).get();
    return ResponseEntity.ok(book);
  }

  // delete book by id
  @DeleteMapping("/{id}")
  public ResponseEntity<Book> deleteBookById(@PathVariable Long id) {
    log.info("request came to /books/" + id + " DELETE");
    // check if book with given id exists
    if (mockBookService.getBookById(id).isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    // delete book with given id
    log.info("book deleted from the list " + mockBookService.getBookById(id).get());
    mockBookService.deleteBookById(id);
    return ResponseEntity.ok().build();
  }
}
