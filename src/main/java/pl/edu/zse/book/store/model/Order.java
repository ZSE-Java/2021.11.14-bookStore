package pl.edu.zse.book.store.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity(name = "torder")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDateTime dateTime;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    Set<OrderPosition> orderPositions = new HashSet<>();
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    public Order(LocalDateTime dateTime,
                 Set<OrderPosition> orderPositions, User user) {
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

    public Set<OrderPosition> getOrderPositions() {
        return orderPositions;
    }

    public void setOrderPositions(Set<OrderPosition> orderPositions) {
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
