package amazons;

public class IllegalMoveException extends Exception {
    private String message ;

    /**
     * Constructs a new exception with the specified detail message.  The
     * cause is not initialized, and may subsequently be initialized by
     * a call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public IllegalMoveException(String message, String message1) {
        super(message);
        this.message = message1;
    }
}
