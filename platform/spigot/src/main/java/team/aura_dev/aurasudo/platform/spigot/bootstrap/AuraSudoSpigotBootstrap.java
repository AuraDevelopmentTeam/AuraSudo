package team.aura_dev.aurasudo.platform.spigot.bootstrap;

import org.bukkit.plugin.java.JavaPlugin;
import team.aura_dev.aurasudo.platform.common.bootstrap.AuraSudoBaseBootstrap;
import team.aura_dev.aurasudo.platform.common.bootstrap.AuraSudoBootstrapper;

public class AuraSudoSpigotBootstrap extends JavaPlugin {
  private final AuraSudoBaseBootstrap bootstrappedPlugin;

  public AuraSudoSpigotBootstrap() {
    final AuraSudoBootstrapper bootstrapper = new AuraSudoSpigotBootstrapper();
    bootstrapper.checkAndLoadSLF4JPlugin(getDataFolder().toPath().resolve("libs"), "spigot");
    bootstrapper.initializePlugin(this, getDataFolder().toPath());

    bootstrappedPlugin = bootstrapper.getPlugin();
  }

  @Override
  public void onEnable() {
    bootstrappedPlugin.preInitPlugin();
    bootstrappedPlugin.initPlugin();
  }
}
