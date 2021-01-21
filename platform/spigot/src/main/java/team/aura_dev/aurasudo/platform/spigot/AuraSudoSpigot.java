package team.aura_dev.aurasudo.platform.spigot;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Path;
import lombok.SneakyThrows;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.CommandMap;
import team.aura_dev.aurasudo.api.AuraSudo;
import team.aura_dev.aurasudo.platform.common.AuraSudoBase;
import team.aura_dev.aurasudo.platform.common.command.BaseCommand;
import team.aura_dev.aurasudo.platform.common.player.PlayerManagerCommon;
import team.aura_dev.aurasudo.platform.spigot.command.CommandWrapperSpigot;
import team.aura_dev.aurasudo.platform.spigot.listener.PlayerEventListenerSpigot;
import team.aura_dev.aurasudo.platform.spigot.player.PlayerManagerSpigot;
import team.aura_dev.lib.multiplatformcore.DependencyClassLoader;

public class AuraSudoSpigot extends AuraSudoBase {
  private final AuraSudoSpigotBootstrap plugin;
  private final Server server;

  private final Method getCommandMapMethod;

  public AuraSudoSpigot(
      DependencyClassLoader classLoader, AuraSudoSpigotBootstrap plugin, Path configDir)
      throws NoSuchMethodException {
    super(classLoader, configDir);

    this.plugin = plugin;
    this.server = plugin.getServer();

    getCommandMapMethod = server.getClass().getDeclaredMethod("getCommandMap");
    getCommandMapMethod.setAccessible(true);

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

  @SneakyThrows({InvocationTargetException.class, IllegalAccessException.class})
  @Override
  protected void registerCommand(BaseCommand command) {
    ((CommandMap) getCommandMapMethod.invoke(server))
        .register(ID, new CommandWrapperSpigot(playerManager, command));
  }
}
