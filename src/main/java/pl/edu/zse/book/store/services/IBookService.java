package pl.edu.zse.book.store.services;

import pl.edu.zse.book.store.model.Book;

import java.util.List;

public interface IBookService {
    List<Book> getAllBooks();
}
