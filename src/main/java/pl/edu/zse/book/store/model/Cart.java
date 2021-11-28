package pl.edu.zse.book.store.model;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    List<OrderPosition> orderPositions = new ArrayList<>();

    public List<OrderPosition> getOrderPositions() {
        return orderPositions;
    }

    public void setOrderPositions(List<OrderPosition> orderPositions) {
        this.orderPositions = orderPositions;
    }

    public double getSum() {
        double result = 0;
        for(OrderPosition position : this.orderPositions) {
            result += position.getQuantity() * position.getBook().getPrice();
        }

        return Math.round(result*100)/100.0;
    }
}
