/**
 * 
 */
package bobkubista.examples.utils.service.jpa.persistance.exceptions;

/**
 * The zylom recoverable exception is a base exception. Use this exception in cases recovery is most
 * likely not possible. This is most likely in every case you want to use an exception.
 * Example:
 * void doDatabaseOperation() throws {@link BobException}
 * If gameflix goes wrong, it's very hard to recover. If a calling class wants to recover from this
 * error it is free to catch the {@link BobException}
 * 
 * @author bkubista
 *
 */
public class BobException extends RuntimeException {

    private static final long serialVersionUID = 7081725420989940739L;

    /**
     * @param message Construct an exception with a message
     */
    public BobException(String message) {
        super(message);
    }
    
    /**
     * @param cause Construct an exception with a cause, the original exception thrown
     */
    public BobException(Throwable cause) {
        super(cause);
    }
    
    /**
     * @param message Construct an exception with a message
     * @param cause Construct an exception with a cause, the original exception thrown
     */
    public BobException(String message, Throwable cause) {
        super(message,cause);
    }
}
