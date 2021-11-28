package pl.edu.zse.book.store.database;

import pl.edu.zse.book.store.model.OrderPosition;

import java.util.List;

public interface IOrderPositionDAO {
    void addOrderPosition(OrderPosition orderPosition, int orderId);
    List<OrderPosition> getAllPositionsByOrderId(int orderId);
}
