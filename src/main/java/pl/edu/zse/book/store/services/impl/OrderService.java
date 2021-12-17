package pl.edu.zse.book.store.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.zse.book.store.database.*;
import pl.edu.zse.book.store.model.Book;
import pl.edu.zse.book.store.model.Order;
import pl.edu.zse.book.store.model.OrderPosition;
import pl.edu.zse.book.store.model.User;
import pl.edu.zse.book.store.services.IOrderService;
import pl.edu.zse.book.store.session.SessionObject;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements IOrderService {

    @Autowired
    IBookDAO bookDAO;

    @Autowired
    IOrderDAO orderDAO;

    @Resource
    SessionObject sessionObject;

    @Override
    public void confirmOrder() {
        Order order = new Order();
        order.setDateTime(LocalDateTime.now());
        order.setUser(sessionObject.getUser());
        order.setOrderPositions(
                new HashSet<>(
                        this.sessionObject
                .getCart().getOrderPositions()
                )
        );

        for(OrderPosition orderPosition : order.getOrderPositions()) {
            Optional<Book> bookBox =
                    this.bookDAO
                            .getBookById(orderPosition.getBook().getId());
            if(!bookBox.isPresent() ||
                    orderPosition.getQuantity() > bookBox.get().getQuantity()) {
                //błąd
                return;
            }
        }

        this.orderDAO.addOrder(order);

        for(OrderPosition orderPosition : order.getOrderPositions()) {
            Optional<Book> bookBox =
                    this.bookDAO
                            .getBookById(orderPosition.getBook().getId());
            bookBox.get().changeQuantity(-orderPosition.getQuantity());
            this.bookDAO.updateBook(bookBox.get());
        }

        this.sessionObject.getCart().setOrderPositions(new ArrayList<>());
    }

    @Override
    public List<Order> getOrdersForCurrentUser() {
        return this.orderDAO
                .getOrdersByUserId(this.sessionObject.getUser().getId());
    }
}
