package team.aura_dev.aurasudo.platform.velocity.command;

import com.velocitypowered.api.command.RawCommand;
import team.aura_dev.aurasudo.api.player.PlayerManager;
import team.aura_dev.aurasudo.platform.common.command.BaseCommand;
import team.aura_dev.aurasudo.platform.common.player.PlayerManagerCommon;

public class CommandWrapperVelocity implements RawCommand {
  protected final PlayerManagerCommon playerManager;
  protected final BaseCommand command;

  /**
   * Construct a new native command wrapper for the {@link BaseCommand}.
   *
   * @param playerManager The {@link PlayerManager} to convert native player objects into our player
   *     objects
   * @param command The underlying {@link BaseCommand}
   */
  public CommandWrapperVelocity(PlayerManagerCommon playerManager, BaseCommand command) {
    this.playerManager = playerManager;
    this.command = command;
  }

  @Override
  public void execute(Invocation invocation) {
    // Sadly we don't know the alias being used, so we need to pass the base command
    command.call(
        playerManager.fromNativePlayer(invocation.source()),
        invocation.alias(),
        invocation.arguments());
  }
}
