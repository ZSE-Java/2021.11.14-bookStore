package pl.edu.zse.book.store.services.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.zse.book.store.database.Database;
import pl.edu.zse.book.store.model.User;
import pl.edu.zse.book.store.model.view.RegisterUser;
import pl.edu.zse.book.store.services.IAuthenticationService;
import pl.edu.zse.book.store.session.SessionObject;

import javax.annotation.Resource;
import java.util.Optional;

public class AuthenticationService implements IAuthenticationService {

    @Autowired
    Database database;

    @Resource
    SessionObject sessionObject;

    @Override
    public void authenticate(String login, String password) {
        Optional<User> userBox = this.database.getUserByLogin(login);
        if(userBox.isPresent() &&
                userBox.get().getPassword()
                .equals(DigestUtils.md5Hex(password))) {
            this.sessionObject.setUser(userBox.get());
        }
    }

    @Override
    public void logout() {
        this.sessionObject.setUser(null);
    }

    @Override
    public void register(RegisterUser registerUser) {

    }
}
