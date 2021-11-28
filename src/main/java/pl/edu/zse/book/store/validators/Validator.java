package pl.edu.zse.book.store.validators;

import pl.edu.zse.book.store.exceptions.ValidationException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    public static void validateLogin(String login) {
        Pattern pattern = Pattern.compile("[A-Za-z0-9]{5}.*");
        Matcher matcher = pattern.matcher(login);
        if(!matcher.matches()) {
            throw new ValidationException("Login must have minimum 5 chars !");
        }
    }

    public static void validatePassword(String password) {
        Pattern pattern = Pattern.compile("[A-Za-z0-9]{5}.*");
        Matcher matcher = pattern.matcher(password);
        if(!matcher.matches()) {
            throw new ValidationException("Login must have minimum 5 chars !");
        }
    }

    public static void validateName(String name) {
        Pattern pattern = Pattern.compile("[A-Z]{1}[a-z]+");
        Matcher matcher = pattern.matcher(name);
        if(!matcher.matches()) {
            throw new ValidationException("Login must have minimum 5 chars !");
        }
    }

    public static void validateSurname(String surname) {
        Pattern pattern = Pattern.compile("[A-Z]{1}[a-z]+");
        Matcher matcher = pattern.matcher(surname);
        if(!matcher.matches()) {
            throw new ValidationException("Login must have minimum 5 chars !");
        }
    }
}
