package team.aura_dev.aurasudo.platform.common.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import lombok.Getter;
import team.aura_dev.aurasudo.platform.common.permission.Permission;
import team.aura_dev.aurasudo.platform.common.player.PlayerDataCommon;

public abstract class BaseCommand {
  public final Permission BASE;
  public final Permission COMMAND;

  @Getter protected final LinkedHashSet<String> aliases;

  public BaseCommand(Collection<String> aliases) {
    this(aliases.isEmpty() ? null : aliases.iterator().next(), aliases);
  }

  public BaseCommand(String baseCommand, String... aliases) {
    this(baseCommand, Arrays.asList(aliases));
  }

  public BaseCommand(String baseCommand, Collection<String> aliases) {
    this.BASE = new Permission(Permission.COMMAND, baseCommand);
    this.COMMAND = new Permission(BASE, "base");

    this.aliases = new LinkedHashSet<>();
    this.aliases.add(baseCommand);
    this.aliases.addAll(aliases);
  }

  public String getBaseCommand() {
    return aliases.iterator().next();
  }

  public List<String> getAliasesAsList() {
    return new ArrayList(aliases);
  }

  public String[] getAliasesAsArray() {
    return aliases.toArray(new String[] {});
  }

  public final boolean call(PlayerDataCommon player, String alias, String commandLine) {
    return call(player, alias, commandLine.split(" "));
  }

  public final boolean call(PlayerDataCommon player, String alias, String[] arguments) {
    return call(player, alias, Arrays.asList(arguments));
  }

  public final boolean call(PlayerDataCommon player, String alias, Collection<String> arguments) {
    try {
      final List<String> argumentList =
          (arguments instanceof List) ? ((List<String>) arguments) : new ArrayList<>(arguments);

      execute(player, alias, argumentList);
    } catch (CommandExecutionException e) {
      player.sendMessage(e.getMessageComponent());

      return false;
    }

    return true;
  }

  protected abstract void execute(PlayerDataCommon player, String alias, List<String> arguments)
      throws CommandExecutionException;
}
