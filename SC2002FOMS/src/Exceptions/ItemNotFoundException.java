/**
 * The {@code ItemNotFoundException} class represents an exception that is thrown when an item is not found.
 * It extends the {@code Exception} class, allowing this exception to be caught and handled specifically.
 *
 * @param message The message that details the cause of the exception.
 */
package Exceptions;

public class ItemNotFoundException extends Exception {
    public ItemNotFoundException(String message) {
        super(message);
    }
}
