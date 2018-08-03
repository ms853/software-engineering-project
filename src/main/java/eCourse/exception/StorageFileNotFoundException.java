package ecourse.exception;

public class StorageFileNotFoundException extends StorageException {

  private static final long serialVersionUID = 4757498447389991262L;

  public StorageFileNotFoundException(String message) {
    super(message);
  }

  public StorageFileNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }
}
