package be.esi.devir5.exception;

/**
 * Exception lanc&eacute;e par les acc&eacute;s db
 */
public class BankEscapeDbException extends BankEscapeException {

    /**
     * Creates a new instance of <code>BibliothequeDBException</code> without detail message.
     */
    public BankEscapeDbException() {
    }


    /**
     * Constructs an instance of <code>BibliothequeDBException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public BankEscapeDbException(String msg) {
        super(msg);
    }
}
