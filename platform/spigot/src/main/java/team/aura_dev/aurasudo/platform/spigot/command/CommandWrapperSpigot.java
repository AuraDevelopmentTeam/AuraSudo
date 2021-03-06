package team.aura_dev.aurasudo.platform.spigot.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import team.aura_dev.aurasudo.api.player.PlayerManager;
import team.aura_dev.aurasudo.platform.common.command.BaseCommand;
import team.aura_dev.aurasudo.platform.common.player.PlayerManagerCommon;

public class CommandWrapperSpigot extends Command {
  protected final PlayerManagerCommon playerManager;
  protected final BaseCommand command;

  /**
   * Construct a new native command wrapper for the {@link BaseCommand}.
   *
   * @param playerManager The {@link PlayerManager} to convert native player objects into our player
   *     objects
   * @param command The underlying {@link BaseCommand}
   */
  public CommandWrapperSpigot(PlayerManagerCommon playerManager, BaseCommand command) {
    super(command.getBaseCommand(), "", "", command.getAliasesAsList());
    setPermission(command.COMMAND.getPermission());

    this.playerManager = playerManager;
    this.command = command;
  }

  @Override
  public boolean execute(CommandSender sender, String commandLabel, String[] args) {
    return command.call(playerManager.fromNativePlayer(sender), commandLabel, args);
  }
}
