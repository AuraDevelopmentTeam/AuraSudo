package team.aura_dev.aurasudo.platform.nukkit.command;

import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.data.CommandData;
import team.aura_dev.aurasudo.api.player.PlayerManager;
import team.aura_dev.aurasudo.platform.common.command.BaseCommand;
import team.aura_dev.aurasudo.platform.common.player.PlayerManagerCommon;

public class CommandWrapperNukkit extends Command {
  protected final PlayerManagerCommon playerManager;
  protected final BaseCommand command;

  /**
   * Construct a new native command wrapper for the {@link BaseCommand}.
   *
   * @param playerManager The {@link PlayerManager} to convert native player objects into our player
   *     objects
   * @param command The underlying {@link BaseCommand}
   */
  public CommandWrapperNukkit(PlayerManagerCommon playerManager, BaseCommand command) {
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
    return command.call(playerManager.fromNativePlayer(sender), commandLabel, args);
  }
}
