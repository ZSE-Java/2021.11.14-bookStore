package pl.edu.zse.book.store.services;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.zse.book.store.database.Database;
import pl.edu.zse.book.store.model.User;
import pl.edu.zse.book.store.session.SessionObject;

import javax.annotation.Resource;
import java.util.Optional;

@Service
public class AuthenticationService {

    @Autowired
    Database database;

    @Resource
    SessionObject sessionObject;

    public void authenticate(String login, String password) {
        Optional<User> userBox = this.database.getUserByLogin(login);
        this.sessionObject.setLogged(userBox.isPresent() && userBox.get().getPassword().equals(DigestUtils.md5Hex(password)));
    }

    public void logout() {
        this.sessionObject.setLogged(false);
    }
}
