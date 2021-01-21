package team.aura_dev.aurasudo.platform.velocity;

import com.velocitypowered.api.proxy.ProxyServer;
import java.nio.file.Path;
import team.aura_dev.aurasudo.api.AuraSudo;
import team.aura_dev.aurasudo.platform.common.AuraSudoBase;
import team.aura_dev.aurasudo.platform.common.command.BaseCommand;
import team.aura_dev.aurasudo.platform.common.player.PlayerManagerCommon;
import team.aura_dev.aurasudo.platform.velocity.command.CommandWrapperVelocity;
import team.aura_dev.aurasudo.platform.velocity.listener.PlayerEventListenerVelocity;
import team.aura_dev.aurasudo.platform.velocity.player.PlayerManagerVelocity;
import team.aura_dev.lib.multiplatformcore.DependencyClassLoader;

public class AuraSudoVelocity extends AuraSudoBase {
  private final AuraSudoVelocityBootstrap plugin;
  private final ProxyServer server;

  public AuraSudoVelocity(
      DependencyClassLoader classLoader,
      AuraSudoVelocityBootstrap plugin,
      ProxyServer server,
      Path configDir) {
    super(classLoader, configDir);

    this.plugin = plugin;
    this.server = server;

    // Instance is initialized
    AuraSudo.setApi(this);
  }

  @Override
  public String getBasePlatform() {
    return "Velocity";
  }

  @Override
  public String getPlatformVariant() {
    return server.getVersion().getName();
  }

  @Override
  protected PlayerManagerCommon generatePlayerManager() {
    return new PlayerManagerVelocity(server);
  }

  @Override
  protected void registerEventListeners() {
    server.getEventManager().register(plugin, new PlayerEventListenerVelocity(this));
  }

  @Override
  protected void registerCommand(BaseCommand command) {
    server
        .getCommandManager()
        .register(
            command.getBaseCommand(),
            new CommandWrapperVelocity(playerManager, command),
            command.getAliasesAsArray());
  }
}
