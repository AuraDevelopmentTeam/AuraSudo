package team.aura_dev.aurasudo.platform.common.command;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;
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
        Component.empty()
            .append(Component.text(message, NamedTextColor.DARK_RED))
            .append(Component.newline())
            .append(
                Component.text(cause.getClass().getName() + ": " + cause.getMessage())
                    .hoverEvent(Component.text(getStackTrace(cause)).asHoverEvent()));
  }

  public CommandExecutionException(Throwable cause) {
    this("Unexpected Exception:", cause);
  }

  public TextComponent getMessageComponent() {
    return message;
  }

  protected String getStackTrace(Throwable cause) {
    StringWriter sw = new StringWriter();
    PrintWriter pw = new PrintWriter(sw);
    cause.printStackTrace(pw);

    String[] splitStackTrace = sw.toString().replace("\t", "  ").trim().split("\r\n|\r|\n", 21);
    Stream<String> output = Arrays.stream(splitStackTrace);

    if (splitStackTrace.length == 21) {
      // Too many lines. Cut them off. Max 20 lines in the final string
      output = Stream.concat(output.limit(19), Stream.of("..."));
    }

    return output.collect(Collectors.joining("\n"));
  }
}
