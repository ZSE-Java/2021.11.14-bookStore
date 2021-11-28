package pl.edu.zse.book.store.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.zse.book.store.database.Database;
import pl.edu.zse.book.store.database.IBookDAO;
import pl.edu.zse.book.store.model.Book;
import pl.edu.zse.book.store.services.IBookService;

import java.util.List;

@Service
public class BookService implements IBookService {

    @Autowired
    IBookDAO bookDAO;

    @Override
    public List<Book> getAllBooks() {
        return this.bookDAO.getAllBooks();
    }
}
