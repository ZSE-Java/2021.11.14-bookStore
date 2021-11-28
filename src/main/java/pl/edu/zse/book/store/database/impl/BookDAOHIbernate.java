package pl.edu.zse.book.store.database.impl;

import pl.edu.zse.book.store.database.IBookDAO;
import pl.edu.zse.book.store.model.Book;

import java.util.List;
import java.util.Optional;

public class BookDAOHIbernate implements IBookDAO {
    @Override
    public List<Book> getAllBooks() {
        return null;
    }

    @Override
    public Optional<Book> getBookById(int id) {
        return Optional.empty();
    }
}
