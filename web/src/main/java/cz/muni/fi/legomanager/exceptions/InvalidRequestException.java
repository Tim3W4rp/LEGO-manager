package cz.muni.fi.legomanager.exceptions;

/**
 * Invalid request exception.
 *
 * @author Štěpán Granát
 */
public class InvalidRequestException extends RuntimeException {
    public InvalidRequestException(String message) {
        super(message);
    }
}
