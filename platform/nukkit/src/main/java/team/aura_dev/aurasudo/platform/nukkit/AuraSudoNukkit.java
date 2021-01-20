package team.aura_dev.aurasudo.platform.nukkit;

import cn.nukkit.Server;
import java.nio.file.Path;
import team.aura_dev.aurasudo.api.AuraSudo;
import team.aura_dev.aurasudo.platform.common.AuraSudoBase;
import team.aura_dev.aurasudo.platform.common.player.PlayerManagerCommon;
import team.aura_dev.aurasudo.platform.nukkit.listener.PlayerEventListenerNukkit;
import team.aura_dev.aurasudo.platform.nukkit.player.PlayerManagerNukkit;
import team.aura_dev.lib.multiplatformcore.DependencyClassLoader;

public class AuraSudoNukkit extends AuraSudoBase {
  private final AuraSudoNukkitBootstrap plugin;
  private final Server server;

  public AuraSudoNukkit(
      DependencyClassLoader classLoader,
      AuraSudoNukkitBootstrap plugin,
      Server server,
      Path configDir) {
    super(classLoader, configDir);

    this.plugin = plugin;
    this.server = server;

    // Instance is initialized
    AuraSudo.setApi(this);
  }

  @Override
  public String getBasePlatform() {
    return "Nukkit";
  }

  @Override
  public String getPlatformVariant() {
    return server.getName();
  }

  @Override
  protected PlayerManagerCommon generatePlayerManager() {
    return new PlayerManagerNukkit();
  }

  @Override
  protected void registerEventListeners() {
    server.getPluginManager().registerEvents(new PlayerEventListenerNukkit(this), plugin);
  }
}
