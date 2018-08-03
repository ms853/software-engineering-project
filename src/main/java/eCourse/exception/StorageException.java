package ecourse.exception;

public class StorageException extends RuntimeException {

  private static final long serialVersionUID = 8499686099088948983L;
  private String exceptionMsg;

  public StorageException(String exceptionMsg) {
    super(exceptionMsg);
    this.exceptionMsg = exceptionMsg;
  }

  public StorageException(String exceptionMsg, Throwable cause) {
    super(exceptionMsg, cause);
    this.exceptionMsg = exceptionMsg;
  }

  public String getExceptionMsg() {
    return this.exceptionMsg;
  }

  public void setExceptionMsg(String exceptionMsg) {
    this.exceptionMsg = exceptionMsg;
  }
}
