package pl.edu.zse.book.store.database;

import pl.edu.zse.book.store.model.User;

import java.util.List;
import java.util.Optional;

public interface IUserDAO {
    List<User> getUsers();
    Optional<User> getUserByLogin(String login);
    void addUser(User user);
    User getUserById(int id);
}
