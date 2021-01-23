package team.aura_dev.aurasudo.platform.common.command;

import java.io.PrintWriter;
import java.io.StringWriter;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;

public class CommandExecutionException extends Exception {
  private final TextComponent message;

  public CommandExecutionException() {
    this("Unknown error");
  }

  public CommandExecutionException(String message) {
    super(message);

    this.message = Component.text(message, NamedTextColor.DARK_RED);
  }

  public CommandExecutionException(String message, Throwable cause) {
    super(message, cause);

    this.message =
        Component.text(message, NamedTextColor.DARK_RED)
            .append(Component.newline())
            .append(
                Component.text(cause.getMessage())
                    .hoverEvent(Component.text(getStackTrace(cause)).asHoverEvent()));
  }

  public CommandExecutionException(Throwable cause) {
    this("Unknown error", cause);
  }

  public TextComponent getMessageComponent() {
    return message;
  }

  protected String getStackTrace(Throwable cause) {
    StringWriter sw = new StringWriter();
    PrintWriter pw = new PrintWriter(sw);
    cause.printStackTrace(pw);

    return pw.toString();
  }
}
