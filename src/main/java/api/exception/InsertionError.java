package api.exception;

public class InsertionError  extends RuntimeException {
    public InsertionError(String msg) {
        super(msg);
    }
}
