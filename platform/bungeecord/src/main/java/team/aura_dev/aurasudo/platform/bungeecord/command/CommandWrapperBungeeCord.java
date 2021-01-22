package team.aura_dev.aurasudo.platform.bungeecord.command;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;
import team.aura_dev.aurasudo.api.player.PlayerManager;
import team.aura_dev.aurasudo.platform.common.command.BaseCommand;
import team.aura_dev.aurasudo.platform.common.player.PlayerManagerCommon;

public class CommandWrapperBungeeCord extends Command {
  protected final PlayerManagerCommon playerManager;
  protected final BaseCommand command;

  /**
   * Construct a new native command wrapper for the {@link BaseCommand}.
   *
   * @param playerManager The {@link PlayerManager} to convert native player objects into our player
   *     objects
   * @param command The underlying {@link BaseCommand}
   */
  public CommandWrapperBungeeCord(PlayerManagerCommon playerManager, BaseCommand command) {
    super(command.getBaseCommand(), command.COMMAND.getPermission(), command.getAliasesAsArray());

    this.playerManager = playerManager;
    this.command = command;
  }

  @Override
  public void execute(CommandSender sender, String[] args) {
    // Sadly we don't know the alias being used, so we need to pass the base command
    command.call(playerManager.fromNativePlayer(sender), command.getBaseCommand(), args);
  }
}
