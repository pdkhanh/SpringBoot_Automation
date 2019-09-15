package automation.core.helper;

public class KatException extends RuntimeException {
  public KatException(String message) {
    super(message);
  }

  public KatException(String message, Throwable cause) {
    super(message, cause);
  }

  public KatException(Throwable cause) {
    super(cause);
  }

  public KatException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
