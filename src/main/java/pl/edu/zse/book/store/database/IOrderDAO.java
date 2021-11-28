package pl.edu.zse.book.store.database;

import pl.edu.zse.book.store.model.Order;

import java.util.List;

public interface IOrderDAO {
    void addOrder(Order order);
    List<Order> getOrdersByUserId(int id);
}
