package team.aura_dev.aurasudo.platform.spigot;

import org.bukkit.plugin.java.JavaPlugin;
import team.aura_dev.aurasudo.platform.common.AuraSudoBaseBootstrap;
import team.aura_dev.aurasudo.platform.common.AuraSudoBootstrapper;

public class AuraSudoSpigotBootstrap extends JavaPlugin {
  private final AuraSudoBaseBootstrap bootstrappedPlugin;

  public AuraSudoSpigotBootstrap() {
    final AuraSudoBootstrapper bootstrapper = new AuraSudoBootstrapper();
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
