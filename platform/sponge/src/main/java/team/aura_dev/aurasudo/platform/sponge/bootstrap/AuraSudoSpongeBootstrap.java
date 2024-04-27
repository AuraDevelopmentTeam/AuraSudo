package team.aura_dev.aurasudo.platform.sponge.bootstrap;

import com.google.inject.Inject;
import java.nio.file.Path;
import org.spongepowered.api.config.ConfigDir;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.event.game.state.GamePreInitializationEvent;
import org.spongepowered.api.plugin.Dependency;
import org.spongepowered.api.plugin.Plugin;
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
public class AuraSudoSpongeBootstrap {
  private final AuraSudoBaseBootstrap bootstrappedPlugin;

  @Inject
  public AuraSudoSpongeBootstrap(@ConfigDir(sharedRoot = false) Path configDir) {
    final AuraSudoBootstrapper bootstrapper = new AuraSudoSpongeBootstrapper();
    bootstrapper.initializePlugin(this, configDir);

    bootstrappedPlugin = bootstrapper.getPlugin();
  }

  @Listener
  public void preInit(GamePreInitializationEvent event) {
    bootstrappedPlugin.preInitPlugin();
  }

  @Listener
  public void init(GameInitializationEvent event) {
    bootstrappedPlugin.initPlugin();
  }
}
