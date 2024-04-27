package team.aura_dev.aurasudo.platform.nukkit.bootstrap;

import cn.nukkit.plugin.PluginBase;
import team.aura_dev.aurasudo.platform.common.bootstrap.AuraSudoBaseBootstrap;
import team.aura_dev.aurasudo.platform.common.bootstrap.AuraSudoBootstrapper;

public class AuraSudoNukkitBootstrap extends PluginBase {
  private AuraSudoBaseBootstrap bootstrappedPlugin;

  @Override
  public void onLoad() {
    final AuraSudoBootstrapper bootstrapper = new AuraSudoNukkitBootstrapper();
    bootstrapper.checkAndLoadSLF4JPlugin(getDataFolder().toPath().resolve("libs"), "nukkit");
    bootstrapper.initializePlugin(this, getServer(), getDataFolder().toPath());

    bootstrappedPlugin = bootstrapper.getPlugin();
  }

  @Override
  public void onEnable() {
    bootstrappedPlugin.preInitPlugin();
    bootstrappedPlugin.initPlugin();
  }
}
