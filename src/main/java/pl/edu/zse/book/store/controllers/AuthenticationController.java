package pl.edu.zse.book.store.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.edu.zse.book.store.services.AuthenticationService;
import pl.edu.zse.book.store.session.SessionObject;

import javax.annotation.Resource;


@Controller
public class AuthenticationController {

    @Autowired
    AuthenticationService authenticationService;

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
}
