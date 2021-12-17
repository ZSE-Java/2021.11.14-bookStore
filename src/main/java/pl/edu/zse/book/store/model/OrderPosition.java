package pl.edu.zse.book.store.model;

import javax.persistence.*;

@Entity(name = "torderposition")
public class OrderPosition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private Book book;
    private int quantity;

    public OrderPosition(Book book, int quantity) {
        this.book = book;
        this.quantity = quantity;
    }

    public OrderPosition() {
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void increaseQuantity() {
        this.quantity++;
    }
}
