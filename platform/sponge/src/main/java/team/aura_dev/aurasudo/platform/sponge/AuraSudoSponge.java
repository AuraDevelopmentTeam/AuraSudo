package team.aura_dev.aurasudo.platform.sponge;

import java.nio.file.Path;
import org.spongepowered.api.Platform;
import org.spongepowered.api.Sponge;
import team.aura_dev.aurasudo.api.AuraSudo;
import team.aura_dev.aurasudo.platform.common.AuraSudoBase;
import team.aura_dev.aurasudo.platform.common.player.PlayerManagerCommon;
import team.aura_dev.aurasudo.platform.sponge.listener.PlayerEventListenerSponge;
import team.aura_dev.aurasudo.platform.sponge.player.PlayerManagerSponge;
import team.aura_dev.lib.multiplatformcore.DependencyClassLoader;
import team.aura_dev.lib.multiplatformcore.download.DependencyList;

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
  public DependencyList getEarlyDependencies(DependencyList dependencyList) {
    // Super only adds CONFIGURATE_HOCON, which we have
    return dependencyList;
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
