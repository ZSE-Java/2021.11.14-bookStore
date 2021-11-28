package pl.edu.zse.book.store.exceptions;

public class ValidationException extends RuntimeException {
    private String validationInfo;

    public ValidationException(String validationInfo) {
        this.validationInfo = validationInfo;
    }

    public String getValidationInfo() {
        return validationInfo;
    }
}
