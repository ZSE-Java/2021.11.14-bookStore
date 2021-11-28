package pl.edu.zse.book.store.services;

import pl.edu.zse.book.store.model.Order;

import java.util.List;

public interface IOrderService {
    public void confirmOrder();
    public List<Order> getOrdersForCurrentUser();
}
