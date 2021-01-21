package team.aura_dev.aurasudo.platform.common.command;

public class CommandExecutionException extends RuntimeException {
  public CommandExecutionException() {}

  public CommandExecutionException(String message) {
    super(message);
  }

  public CommandExecutionException(String message, Throwable cause) {
    super(message, cause);
  }

  public CommandExecutionException(Throwable cause) {
    super(cause);
  }
}
