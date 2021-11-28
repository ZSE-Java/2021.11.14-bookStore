package pl.edu.zse.book.store.services;

import pl.edu.zse.book.store.model.view.RegisterUser;

public interface IAuthenticationService {
    void authenticate(String login, String password);
    void logout();
    void register(RegisterUser registerUser);
}
