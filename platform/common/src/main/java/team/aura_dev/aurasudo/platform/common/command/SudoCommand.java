package team.aura_dev.aurasudo.platform.common.command;

import java.util.List;
import team.aura_dev.aurasudo.platform.common.player.PlayerDataCommon;

public class SudoCommand extends BaseCommand {
  public SudoCommand() {
    super("sudo");
  }

  @Override
  public void execute(PlayerDataCommon player, String alias, List<String> arguments)
      throws CommandExecutionException {
    if (player.isConsole()) throw new CommandExecutionException("Only players");
    if (!player.hasPermission(COMMAND)) throw new PermissionException(COMMAND);

    // Toggle
    player.setSudoLevel((player.getSudoLevel() + 1) % 2);
  }
}
