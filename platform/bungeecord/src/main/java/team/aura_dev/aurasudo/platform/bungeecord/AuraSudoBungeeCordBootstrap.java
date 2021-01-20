package team.aura_dev.aurasudo.platform.bungeecord;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;
import team.aura_dev.aurasudo.platform.common.AuraSudoBaseBootstrap;
import team.aura_dev.aurasudo.platform.common.AuraSudoBootstrapper;

public class AuraSudoBungeeCordBootstrap extends Plugin {
  private AuraSudoBaseBootstrap bootstrappedPlugin;

  @Override
  public void onLoad() {
    final AuraSudoBootstrapper bootstrapper = new AuraSudoBootstrapper();
    bootstrapper.checkAndLoadSLF4JPlugin(getDataFolder().toPath().resolve("libs"), "bungeecord");
    bootstrapper.initializePlugin(this, ProxyServer.getInstance(), getDataFolder().toPath());

    bootstrappedPlugin = bootstrapper.getPlugin();
  }

  @Override
  public void onEnable() {
    bootstrappedPlugin.preInitPlugin();
    bootstrappedPlugin.initPlugin();
  }
}
