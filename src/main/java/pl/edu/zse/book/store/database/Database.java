package pl.edu.zse.book.store.database;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;
import pl.edu.zse.book.store.model.Book;
import pl.edu.zse.book.store.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class Database {
    private final List<Book> books = new ArrayList<>();
    private final List<User> users = new ArrayList<>();

    public Database() {
        this.books.add(new Book(
                "Stwórz grę w Unity, a nauczysz się programowania w C#! Pisanie kodu, które sprawia radość. Wydanie V",
                "Harrison Ferrone",
                55.20,
                "978-83-283-8144-5"));

        this.books.add(new Book(
                "JavaScript. Techniki zaawansowane",
                "Tomasz Sochacki",
                43.92,
                "978-83-283-5640-5"));

        this.books.add(new Book(
                "Strategia UX. Techniki tworzenia innowacyjnych rozwiązań cyfrowych. Wydanie II",
                "Jaime Levy",
                55.20,
                "978-83-283-8289-3"));

        this.books.add(new Book(
                "Uczenie głębokie i sztuczna inteligencja. Interaktywny przewodnik ilustrowany",
                "Jon Krohn, Grant Beyleveld, Aglaé Bassens",
                64.35,
                "978-83-283-7914-5"));

        this.users.add(new User("admin", DigestUtils.md5Hex("admin")));
        this.users.add(new User("user", DigestUtils.md5Hex("user")));
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
}
