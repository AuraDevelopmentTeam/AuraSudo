package team.aura_dev.aurasudo.platform.spigot;

import java.nio.file.Path;
import org.bukkit.Bukkit;
import team.aura_dev.aurasudo.api.AuraSudo;
import team.aura_dev.aurasudo.platform.common.AuraSudoBase;
import team.aura_dev.aurasudo.platform.common.player.PlayerManagerCommon;
import team.aura_dev.aurasudo.platform.spigot.listener.PlayerEventListenerSpigot;
import team.aura_dev.aurasudo.platform.spigot.player.PlayerManagerSpigot;
import team.aura_dev.lib.multiplatformcore.DependencyClassLoader;

public class AuraSudoSpigot extends AuraSudoBase {
  private final AuraSudoSpigotBootstrap plugin;

  public AuraSudoSpigot(
      DependencyClassLoader classLoader, AuraSudoSpigotBootstrap plugin, Path configDir) {
    super(classLoader, configDir);

    this.plugin = plugin;

    // Instance is initialized
    AuraSudo.setApi(this);
  }

  @Override
  public String getBasePlatform() {
    return "Spigot";
  }

  @Override
  public String getPlatformVariant() {
    return Bukkit.getName();
  }

  @Override
  protected PlayerManagerCommon generatePlayerManager() {
    return new PlayerManagerSpigot();
  }

  @Override
  protected void registerEventListeners() {
    Bukkit.getPluginManager().registerEvents(new PlayerEventListenerSpigot(this), plugin);
  }
}
