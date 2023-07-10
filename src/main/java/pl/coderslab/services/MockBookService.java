package pl.coderslab.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;
import pl.coderslab.model.Book;

@Component
public class MockBookService {

  private final List<Book> listOfBooks;
  private static Long nextId;

  public MockBookService() {
    this.listOfBooks = new ArrayList<>();
    listOfBooks.addAll(createData());
    nextId = listOfBooks.size() + 1L;
  }

  // add book to the list
  public void addBook(Book book) {
    book.setId(nextId++);
    listOfBooks.add(book);
  }

  private List<Book> createData() {
    return Arrays.asList(
        new Book(1L, "9788324631766", "Thinking in Java", "Bruce Eckel", "Helion", "programming"),
        new Book(
            2L,
            "9788324627738",
            "Rusz glowa, Java.",
            "Sierra Kathy, Bates Bert",
            "Helion",
            "programming"),
        new Book(
            3L,
            "9780130819338",
            "Java 2. Podstawy",
            "Cay Horstmann, Gary Cornell",
            "Helion",
            "programming"),
        new Book(4L, "9780134685991", "Clean Code", "Robert C. Martin", "Helion", "programming"),
        new Book(
            5L, "9780134685992", "Clean Code2", "Robert C. Martin2", "Helion2", "programming2"));
  }

  // get list of all books
  public List<Book> getAllBooks() {
    return listOfBooks;
  }

  // get book by id
  public Optional<Book> getBookById(Long id) {
    return listOfBooks.stream().filter(e -> e.getId().equals(id)).findFirst();
  }

  // update book by id
  public void updateBookById(Long id, Book book) {
    listOfBooks.stream()
        .filter(e -> e.getId().equals(id))
        .findFirst()
        .ifPresent(
            e -> {
              e.setIsbn(book.getIsbn());
              e.setTitle(book.getTitle());
              e.setAuthor(book.getAuthor());
              e.setPublisher(book.getPublisher());
              e.setType(book.getType());
            });
  }

  // remove book by id
  public void deleteBookById(Long id) {
    listOfBooks.removeIf(e -> e.getId().equals(id));
  }

  // check if book field is exist
  public boolean isBookFieldHasValues(Book book) {
      return book.getIsbn() != null
              && !book.getIsbn().isEmpty()
              && book.getTitle() != null
              && !book.getTitle().isEmpty()
              && book.getAuthor() != null
              && !book.getAuthor().isEmpty()
              && book.getPublisher() != null
              && !book.getPublisher().isEmpty()
              && book.getType() != null
              && !book.getType().isEmpty();
  }
}
