package cz.muni.fi.legomanager.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents JSON message with error.
 *
 * @author Štěpán Granát
 */
public class FormValidationResource extends ErrorResource {

    final static Logger logger = LoggerFactory.getLogger(FormValidationResource.class);

    private Map<String, String> fieldErrors;

    FormValidationResource(String code, String message, BindingResult bindingResult) {
        super(code, message);
        setFieldErrors(bindingResult);
    }

    public void setFieldErrors(BindingResult bindingResult) {
        this.fieldErrors = new HashMap<>();
        for(FieldError error : bindingResult.getFieldErrors()){
            fieldErrors.put(error.getField(), error.getDefaultMessage());
        }
    }

    public Map<String, String> getErrors() {
        return fieldErrors;
    }

    @Override
    public String toString() {
        return "ErrorResource{" +
                "code='" + getCode() + '\'' +
                ", message='" + getMessage() + '\'' +
                '}';
    }
}
