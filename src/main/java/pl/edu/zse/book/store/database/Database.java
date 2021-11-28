package pl.edu.zse.book.store.database;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;
import pl.edu.zse.book.store.model.Book;
import pl.edu.zse.book.store.model.Order;
import pl.edu.zse.book.store.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Database {
    private final List<Book> books = new ArrayList<>();
    private final List<User> users = new ArrayList<>();
    private final List<Order> orders = new ArrayList<>();

    public Database() {
        this.books.add(new Book(
                1,
                "Stwórz grę w Unity, a nauczysz się programowania w C#! Pisanie kodu, które sprawia radość. Wydanie V",
                "Harrison Ferrone",
                55.20,
                "978-83-283-8144-5",
                10));

        this.books.add(new Book(
                2,
                "JavaScript. Techniki zaawansowane",
                "Tomasz Sochacki",
                43.92,
                "978-83-283-5640-5",
                10));

        this.books.add(new Book(
                3,
                "Strategia UX. Techniki tworzenia innowacyjnych rozwiązań cyfrowych. Wydanie II",
                "Jaime Levy",
                55.20,
                "978-83-283-8289-3",
                15));

        this.books.add(new Book(
                4,
                "Uczenie głębokie i sztuczna inteligencja. Interaktywny przewodnik ilustrowany",
                "Jon Krohn, Grant Beyleveld, Aglaé Bassens",
                64.35,
                "978-83-283-7914-5",
                20));

        this.users.add(new User(1, "admin", DigestUtils.md5Hex("admin")));
        this.users.add(new User(2, "user", DigestUtils.md5Hex("user")));
    }

    public List<Book> getBooks() {
        return books;
    }

    public List<User> getUsers() {
        return users;
    }

    public Optional<User> getUserByLogin(String login) {
        for(User user : this.users) {
            if(user.getLogin().equals(login)) {
                return Optional.of(user);
            }
        }

        return Optional.empty();
    }

    public Optional<Book> getBookById(int id) {
        for(Book book : this.books) {
            if(book.getId() == id) {
                return Optional.of(book);
            }
        }

        return Optional.empty();
    }

    public void addOrder(Order order) {
        this.orders.add(order);
    }

    public List<Order> getOrdersByUserId(int id) {
        List<Order> result = new ArrayList<>();
        for(Order order : this.orders) {
            if(order.getUser().getId() == id) {
                result.add(order);
            }
        }

        return result;
    }

    public void addUser(User user) {
        this.users.add(user);
    }
}
