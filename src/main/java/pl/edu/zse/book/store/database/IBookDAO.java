package pl.edu.zse.book.store.database;

import pl.edu.zse.book.store.model.Book;

import java.util.List;
import java.util.Optional;

public interface IBookDAO {
    List<Book> getAllBooks();
    Optional<Book> getBookById(int id);
    void updateBook(Book book);
}
