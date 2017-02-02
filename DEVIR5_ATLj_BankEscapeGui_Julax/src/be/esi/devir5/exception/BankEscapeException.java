package be.esi.devir5.exception;

/**
 *
 * @author pikirby45
 */
public class BankEscapeException extends Exception {

    /**
     * Creates a new instance of <code>BankEscapeException</code> without detail
     * message.
     */
    public BankEscapeException() {
    }

    /**
     * Constructs an instance of <code>BankEscapeException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public BankEscapeException(String msg) {
        super(msg);
    }
}
