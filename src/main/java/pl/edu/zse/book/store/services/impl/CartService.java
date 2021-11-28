package pl.edu.zse.book.store.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.zse.book.store.database.Database;
import pl.edu.zse.book.store.database.IBookDAO;
import pl.edu.zse.book.store.model.Book;
import pl.edu.zse.book.store.model.OrderPosition;
import pl.edu.zse.book.store.services.ICartService;
import pl.edu.zse.book.store.session.SessionObject;

import javax.annotation.Resource;
import java.util.Optional;

@Service
public class CartService implements ICartService {

    @Autowired
    IBookDAO bookDAO;

    @Resource
    SessionObject sessionObject;

    @Override
    public void addBookToCart(int id) {
        Optional<Book> bookBox = this.bookDAO.getBookById(id);
        if(bookBox.isPresent()) {
            for(OrderPosition position :
                    this.sessionObject.getCart().getOrderPositions()) {
                if(position.getBook().getId() == id) {
                    position.increaseQuantity();
                    return;
                }
            }

            this.sessionObject.getCart()
                    .getOrderPositions()
                    .add(new OrderPosition(bookBox.get(), 1));
        }
    }
}
