package team.aura_dev.aurasudo.platform.bungeecord;

import java.nio.file.Path;
import net.md_5.bungee.api.ProxyServer;
import team.aura_dev.aurasudo.api.AuraSudo;
import team.aura_dev.aurasudo.platform.bungeecord.command.CommandWrapperBungeeCord;
import team.aura_dev.aurasudo.platform.bungeecord.listener.PlayerEventListenerBungeeCord;
import team.aura_dev.aurasudo.platform.bungeecord.player.PlayerManagerBungeeCord;
import team.aura_dev.aurasudo.platform.common.AuraSudoBase;
import team.aura_dev.aurasudo.platform.common.command.BaseCommand;
import team.aura_dev.aurasudo.platform.common.dependency.RuntimeDependencies;
import team.aura_dev.aurasudo.platform.common.player.PlayerManagerCommon;
import team.aura_dev.lib.multiplatformcore.DependencyClassLoader;
import team.aura_dev.lib.multiplatformcore.download.DependencyList;

public class AuraSudoBungeeCord extends AuraSudoBase {
  private final AuraSudoBungeeCordBootstrap plugin;
  private final ProxyServer server;

  public AuraSudoBungeeCord(
      DependencyClassLoader classLoader,
      AuraSudoBungeeCordBootstrap plugin,
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
    return "BungeeCord";
  }

  @Override
  public String getPlatformVariant() {
    return server.getName();
  }

  @Override
  public DependencyList getDependencies(DependencyList dependencyList) {
    dependencyList.addIfClassMissing(
        RuntimeDependencies.ADVENTURE_JSON,
        "net.kyori.adventure.text.serializer.gson.GsonComponentSerializer");

    return super.getDependencies(dependencyList);
  }

  @Override
  protected PlayerManagerCommon generatePlayerManager() {
    return new PlayerManagerBungeeCord();
  }

  @Override
  protected void registerEventListeners() {
    server.getPluginManager().registerListener(plugin, new PlayerEventListenerBungeeCord(this));
  }

  @Override
  protected void registerCommand(BaseCommand command) {
    server
        .getPluginManager()
        .registerCommand(plugin, new CommandWrapperBungeeCord(playerManager, command));
  }
}
