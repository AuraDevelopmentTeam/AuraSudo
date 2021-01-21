package team.aura_dev.aurasudo.platform.sponge.command;

import java.util.ArrayList;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.text.Text;
import team.aura_dev.aurasudo.api.player.PlayerManager;
import team.aura_dev.aurasudo.platform.common.command.BaseCommand;
import team.aura_dev.aurasudo.platform.common.player.PlayerManagerCommon;

public class CommandExecutorSponge implements CommandExecutor {
  public static final Text ARGS = Text.of("args");

  protected final PlayerManagerCommon playerManager;
  protected final BaseCommand command;

  /**
   * Construct a new native command executor for the {@link BaseCommand}.
   *
   * @param playerManager The {@link PlayerManager} to convert native player objects into our player
   *     objects
   * @param command The underlying {@link BaseCommand}
   */
  public CommandExecutorSponge(PlayerManagerCommon playerManager, BaseCommand command) {
    this.playerManager = playerManager;
    this.command = command;
  }

  @Override
  public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
    // Sadly we don't know the alias being used, so we need to pass the base command
    command.execute(
        playerManager.fromNativePlayer(src),
        command.getBaseCommand(),
        new ArrayList(args.getAll(ARGS)));

    return CommandResult.success();
  }
}
