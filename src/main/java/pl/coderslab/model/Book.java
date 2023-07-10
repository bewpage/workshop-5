package pl.coderslab.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Book {
  private Long id;
  private String isbn;
  private String title;
  private String author;
  private String publisher;
  private String type;
}
