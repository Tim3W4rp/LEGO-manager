package cz.muni.fi.legomanager.exceptions;

import org.springframework.validation.BindingResult;

public class FormException extends RuntimeException {
    private BindingResult result;
    public FormException(String message, BindingResult result) {
        super(message);
        this.result = result;
    }

    public BindingResult getResult() {
        return result;
    }
}
