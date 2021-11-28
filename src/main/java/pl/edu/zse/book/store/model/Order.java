package pl.edu.zse.book.store.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private int id;
    private LocalDateTime dateTime;
    List<OrderPosition> orderPositions = new ArrayList<>();
    private User user;

    public Order(LocalDateTime dateTime,
                 List<OrderPosition> orderPositions, User user) {
        this.dateTime = dateTime;
        this.orderPositions = orderPositions;
        this.user = user;
    }

    public Order() {
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public List<OrderPosition> getOrderPositions() {
        return orderPositions;
    }

    public void setOrderPositions(List<OrderPosition> orderPositions) {
        this.orderPositions = orderPositions;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getSum() {
        double result = 0;
        for(OrderPosition position : this.orderPositions) {
            result += position.getQuantity() * position.getBook().getPrice();
        }

        return Math.round(result*100)/100.0;
    }
}
