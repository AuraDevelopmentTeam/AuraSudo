package team.aura_dev.aurasudo.platform.velocity;

import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.plugin.annotation.DataDirectory;
import com.velocitypowered.api.proxy.ProxyServer;
import java.nio.file.Path;
import team.aura_dev.aurasudo.platform.common.AuraSudoBaseBootstrap;
import team.aura_dev.aurasudo.platform.common.AuraSudoBootstrapper;

@Plugin(
    id = AuraSudoBootstrapper.ID,
    name = AuraSudoBootstrapper.NAME,
    version = AuraSudoBootstrapper.VERSION,
    description = AuraSudoBootstrapper.DESCRIPTION,
    url = AuraSudoBootstrapper.URL,
    authors = {AuraSudoBootstrapper.AUTHOR})
public class AuraSudoVelocityBootstrap {
  private final AuraSudoBaseBootstrap bootstrappedPlugin;

  @Inject
  public AuraSudoVelocityBootstrap(ProxyServer server, @DataDirectory Path dataDir) {
    final AuraSudoBootstrapper bootstrapper = new AuraSudoBootstrapper();
    bootstrapper.initializePlugin(this, server, dataDir);

    bootstrappedPlugin = bootstrapper.getPlugin();
  }

  @Subscribe
  public void onProxyInitialization(ProxyInitializeEvent event) {
    bootstrappedPlugin.preInitPlugin();
    bootstrappedPlugin.initPlugin();
  }
}
