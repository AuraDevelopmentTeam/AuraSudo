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

  public abstract void execute(PlayerDataCommon player, String alias, List<String> arguments)
      throws CommandExecutionException;
}
