package team.aura_dev.aurasudo.platform.nukkit.command;

import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.data.CommandData;
import java.util.Arrays;
import team.aura_dev.aurasudo.api.player.PlayerManager;
import team.aura_dev.aurasudo.platform.common.command.BaseCommand;

public class CommandWrapperNukkit extends Command {
  protected final PlayerManager playerManager;
  protected final BaseCommand command;

  /**
   * Construct a new native command wrapper for the {@link BaseCommand}.
   *
   * @param playerManager The {@link PlayerManager} to convert native player objects into our player
   *     objects
   * @param command The underlying {@link BaseCommand}
   */
  public CommandWrapperNukkit(PlayerManager playerManager, BaseCommand command) {
    super(
        CommandData.builder(command.getBaseCommand())
            .addPermission(command.COMMAND.getPermission())
            .setAliases(command.getAliasesAsArray())
            .build());

    this.playerManager = playerManager;
    this.command = command;
  }

  @Override
  public boolean execute(CommandSender sender, String commandLabel, String[] args) {
    command.execute(playerManager.fromNativePlayer(sender), commandLabel, Arrays.asList(args));

    return true;
  }
}
