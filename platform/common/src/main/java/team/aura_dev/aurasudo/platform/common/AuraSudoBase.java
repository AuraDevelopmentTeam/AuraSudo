package team.aura_dev.aurasudo.platform.common;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import lombok.Getter;
import lombok.SneakyThrows;
import net.luckperms.api.LuckPermsProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import team.aura_dev.aurasudo.api.AuraSudoApi;
import team.aura_dev.aurasudo.platform.common.command.BaseCommand;
import team.aura_dev.aurasudo.platform.common.command.SudoCommand;
import team.aura_dev.aurasudo.platform.common.config.ConfigLoader;
import team.aura_dev.aurasudo.platform.common.context.SudoContextCalculator;
import team.aura_dev.aurasudo.platform.common.dependency.RuntimeDependencies;
import team.aura_dev.aurasudo.platform.common.player.PlayerManagerCommon;
import team.aura_dev.lib.multiplatformcore.DependencyClassLoader;
import team.aura_dev.lib.multiplatformcore.dependency.RuntimeDependency;
import team.aura_dev.lib.multiplatformcore.download.DependencyDownloader;
import team.aura_dev.lib.multiplatformcore.download.DependencyList;

@SuppressFBWarnings(
    value = {"JLM_JSR166_UTILCONCURRENT_MONITORENTER", "RCN_REDUNDANT_NULLCHECK_OF_NONNULL_VALUE"},
    justification = "Code is generated by lombok which means I don't have any influence on it.")
public abstract class AuraSudoBase implements AuraSudoApi, AuraSudoBaseBootstrap {
  public static final Logger logger = LoggerFactory.getLogger(NAME);

  @Getter private static AuraSudoBase instance = null;

  @Getter protected PlayerManagerCommon playerManager;

  @Getter protected final DependencyClassLoader classLoader;
  @Getter protected final Path configDir;
  @Getter protected final Path libsDir;
  @Getter protected final DependencyDownloader dependencyDownloader;

  @Getter(lazy = true)
  private final List<String> asciiBanner = generateAsciiBanner();

  protected ConfigLoader configLoader;

  protected AuraSudoBase(DependencyClassLoader classLoader, Path configDir) {
    if (instance != null) {
      throw new IllegalStateException("AuraBan has already been initialized!");
    }

    instance = this;

    this.classLoader = classLoader;
    this.configDir = configDir;
    this.libsDir = configDir.resolve("libs");
    this.dependencyDownloader = new DependencyDownloader(classLoader, libsDir);
  }

  public abstract String getBasePlatform();

  public abstract String getPlatformVariant();

  public String getFullPlatform() {
    return getBasePlatform() + " - " + getPlatformVariant();
  }

  public Path getConfigFile() {
    return getConfigDir().resolve(ID + ".conf");
  }

  public DependencyList getEarlyDependencies(DependencyList dependencyList) {
    // We need Configurate for the config
    dependencyList.add(RuntimeDependencies.CONFIGURATE_HOCON);

    // Don't load platform dependencies at all
    getPlatformDependencies().forEach(dependencyList::deny);

    return dependencyList;
  }

  public DependencyList getDependencies(DependencyList dependencyList) {
    // We need caffeine as a loading cache in several classes
    dependencyList.add(RuntimeDependencies.CAFFEINE);
    // For basic text parsing
    dependencyList.addIfClassMissing(
        RuntimeDependencies.ADVENTURE_LEGACY, "net.kyori.adventure.text.TextComponent");

    // Don't load platform dependencies at all
    getPlatformDependencies().forEach(dependencyList::deny);

    return dependencyList;
  }

  /**
   * A list of dependencies that are on the platform and therefore need not be loaded.<br>
   * By default this list is empty.
   *
   * @return A list of dependencies present on the platform
   */
  public Collection<RuntimeDependency> getPlatformDependencies() {
    return Collections.emptyList();
  }

  protected abstract PlayerManagerCommon generatePlayerManager();

  protected abstract void registerEventListeners();

  protected abstract void registerCommand(BaseCommand command);

  // ============================================================================================
  // Actual plugin functionality starts here
  // ============================================================================================
  @SneakyThrows
  public final void preInitPlugin() {
    logger.info("Preinitializing " + NAME + " Version " + VERSION);

    if (VERSION.contains("SNAPSHOT")) {
      logger.warn("WARNING! This is a snapshot version!");
      logger.warn("Use at your own risk!");
    } else if (VERSION.contains("DEV")) {
      logger.info("This is a unreleased development version!");
      logger.info("Things might not work properly!");
    }

    logger.info("Downloading early dependencies");
    dependencyDownloader.downloadAndInjectInClasspath(getEarlyDependencies(new DependencyList()));

    configLoader = new ConfigLoader(this);
    configLoader.loadConfig();
  }

  @SneakyThrows
  public final void initPlugin() {
    if (configLoader.getConfig().getGeneral().getBannerEnabled()) {
      // Get logger without name to nicely print the banner
      final Logger bannerLogger = LoggerFactory.getLogger("");

      // Print ASCII banner
      getAsciiBanner().forEach(bannerLogger::info);
    }

    logger.info("Initializing " + NAME + " Version " + VERSION);

    logger.info("Downloading dependencies");
    dependencyDownloader.downloadAndInjectInClasspath(getDependencies(new DependencyList()));

    this.playerManager = generatePlayerManager();

    logger.info("Registering Event Listeners");
    registerEventListeners();

    logger.info("Registering Context with LuckPerms");
    LuckPermsProvider.get()
        .getContextManager()
        .registerCalculator(new SudoContextCalculator(playerManager, getMaxSudoLevel()));

    logger.info("Registering Context with LuckPerms");
    registerCommand(new SudoCommand());

    // TODO
  }

  @Override
  public int getMaxSudoLevel() {
    // TODO: Read from config
    return 2;
  }

  // Private helper methods
  // Generated with http://www.patorjk.com/software/taag/#p=display&f=Straight&t=AuraSudo
  private List<String> generateAsciiBanner() {
    return Arrays.asList(
        "  §b            §e __",
        "  §b /\\     _ _ §e(_     _| _    §bAuraSudo §ev" + VERSION,
        "  §b/--\\|_|| (_|§e__)|_|(_|(_)   §8Proudly running on " + getFullPlatform(),
        "");
  }
}
