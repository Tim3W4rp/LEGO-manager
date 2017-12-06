package cz.muni.fi.legomanager.exceptions;

/**
 * Invalid request exception.
 *
 * @author Martin Kuba makub@ics.muni.cz
 */
public class InvalidRequestException extends RuntimeException {
    public InvalidRequestException(String message) {
        super(message);
    }
}
