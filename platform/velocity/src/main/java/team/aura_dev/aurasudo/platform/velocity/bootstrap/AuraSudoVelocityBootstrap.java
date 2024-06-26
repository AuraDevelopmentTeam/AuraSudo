package team.aura_dev.aurasudo.platform.velocity.bootstrap;

import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Dependency;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.plugin.annotation.DataDirectory;
import com.velocitypowered.api.proxy.ProxyServer;
import java.nio.file.Path;
import team.aura_dev.aurasudo.platform.common.bootstrap.AuraSudoBaseBootstrap;
import team.aura_dev.aurasudo.platform.common.bootstrap.AuraSudoBootstrapper;

@Plugin(
    id = AuraSudoBootstrapper.ID,
    name = AuraSudoBootstrapper.NAME,
    version = AuraSudoBootstrapper.VERSION,
    description = AuraSudoBootstrapper.DESCRIPTION,
    url = AuraSudoBootstrapper.URL,
    authors = {AuraSudoBootstrapper.AUTHOR},
    dependencies = @Dependency(id = "luckperms"))
public class AuraSudoVelocityBootstrap {
  private final AuraSudoBaseBootstrap bootstrappedPlugin;

  @Inject
  public AuraSudoVelocityBootstrap(ProxyServer server, @DataDirectory Path dataDir) {
    final AuraSudoBootstrapper bootstrapper = new AuraSudoVelocityBootstrapper();
    bootstrapper.initializePlugin(this, server, dataDir);

    bootstrappedPlugin = bootstrapper.getPlugin();
  }

  @Subscribe
  public void onProxyInitialization(ProxyInitializeEvent event) {
    bootstrappedPlugin.preInitPlugin();
    bootstrappedPlugin.initPlugin();
  }
}
