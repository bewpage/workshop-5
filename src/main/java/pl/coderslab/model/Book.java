package pl.coderslab.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Book {
  private final Long id;
  private final String isbn;
  private final String title;
  private final String author;
  private final String publisher;
  private final String type;
}
