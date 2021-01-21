package team.aura_dev.aurasudo.platform.sponge;

import java.nio.file.Path;
import java.util.Collection;
import java.util.Collections;
import org.spongepowered.api.Platform;
import org.spongepowered.api.Sponge;
import team.aura_dev.aurasudo.api.AuraSudo;
import team.aura_dev.aurasudo.platform.common.AuraSudoBase;
import team.aura_dev.aurasudo.platform.common.dependency.RuntimeDependencies;
import team.aura_dev.aurasudo.platform.common.player.PlayerManagerCommon;
import team.aura_dev.aurasudo.platform.sponge.listener.PlayerEventListenerSponge;
import team.aura_dev.aurasudo.platform.sponge.player.PlayerManagerSponge;
import team.aura_dev.lib.multiplatformcore.DependencyClassLoader;
import team.aura_dev.lib.multiplatformcore.dependency.RuntimeDependency;

public class AuraSudoSponge extends AuraSudoBase {
  private final AuraSudoSpongeBootstrap plugin;

  public AuraSudoSponge(
      DependencyClassLoader classLoader, AuraSudoSpongeBootstrap plugin, Path configDir) {
    super(classLoader, configDir);

    this.plugin = plugin;

    // Instance is initialized
    AuraSudo.setApi(this);
  }

  @Override
  public String getBasePlatform() {
    return "Sponge";
  }

  @Override
  public String getPlatformVariant() {
    return Sponge.getPlatform().getContainer(Platform.Component.IMPLEMENTATION).getName();
  }

  @Override
  public Collection<RuntimeDependency> getPlatformDependencies() {
    // Caffeine is outdated, else we could remove it as well
    return Collections.singleton(RuntimeDependencies.CONFIGURATE_HOCON);
  }

  @Override
  protected PlayerManagerCommon generatePlayerManager() {
    return new PlayerManagerSponge();
  }

  @Override
  protected void registerEventListeners() {
    Sponge.getEventManager().registerListeners(plugin, new PlayerEventListenerSponge(this));
  }
}
