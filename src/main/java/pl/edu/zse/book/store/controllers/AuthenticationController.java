package pl.edu.zse.book.store.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.edu.zse.book.store.exceptions.ValidationException;
import pl.edu.zse.book.store.model.view.RegisterUser;
import pl.edu.zse.book.store.services.IAuthenticationService;
import pl.edu.zse.book.store.session.SessionObject;
import pl.edu.zse.book.store.validators.Validator;

import javax.annotation.Resource;


@Controller
public class AuthenticationController {

    @Autowired
    IAuthenticationService authenticationService;

    @Resource
    SessionObject sessionObject;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {
        if(this.sessionObject.isLogged()) {
            return "redirect:/main";
        } else {
            return "login";
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam String login, @RequestParam String password) {
        if(!this.sessionObject.isLogged()) {
            this.authenticationService.authenticate(login, password);
        }
        if(sessionObject.isLogged()) {
            return "redirect:/main";
        } else {
            return "redirect:/login";
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout() {
        this.authenticationService.logout();
        return "redirect:/main";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(Model model) {
        model.addAttribute("user", new RegisterUser());
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@ModelAttribute RegisterUser registerUser) {
        try {
            Validator.validateName(registerUser.getName());
            Validator.validateSurname(registerUser.getSurname());
            Validator.validateLogin(registerUser.getLogin());
            Validator.validatePassword(registerUser.getPassword());
        } catch (ValidationException e) {
            return "redirect:/register";
        }
        this.authenticationService.register(registerUser);
        return "redirect:/main";
    }
}
