package team.aura_dev.aurasudo.platform.common.command;

import java.util.List;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import team.aura_dev.aurasudo.platform.common.player.PlayerDataCommon;

public class SudoCommand extends BaseCommand {
  public SudoCommand(List<String> aliases) {
    super(aliases);
  }

  @Override
  public void execute(PlayerDataCommon player, String alias, List<String> arguments)
      throws CommandExecutionException {
    if (player.isConsole())
      throw new CommandExecutionException("This command can only be executed by players!");
    if (!player.hasPermission(COMMAND)) throw new PermissionException(COMMAND);

    if (player.getSudoLevel() == 0) {
      player.setSudoLevel(1);

      player.sendMessage(Component.text("Enabled sudo mode", NamedTextColor.GREEN));
    } else {
      player.setSudoLevel(0);

      player.sendMessage(Component.text("Disabled sudo mode", NamedTextColor.DARK_RED));
    }
  }
}
