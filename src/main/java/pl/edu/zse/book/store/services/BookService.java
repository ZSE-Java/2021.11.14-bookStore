package pl.edu.zse.book.store.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.zse.book.store.database.Database;
import pl.edu.zse.book.store.model.Book;
import java.util.List;

@Service
public class BookService {

    @Autowired
    Database database;

    public List<Book> getAllBooks() {
        return this.database.getBooks();
    }
}
