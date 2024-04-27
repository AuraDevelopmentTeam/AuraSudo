package team.aura_dev.aurasudo.platform.common.bootstrap;

import java.nio.file.Path;
import java.util.Arrays;
import team.aura_dev.lib.multiplatformcore.bootstrap.MultiProjectSLF4JBootstrapper;

public class AuraSudoBootstrapper extends MultiProjectSLF4JBootstrapper<AuraSudoBaseBootstrap> {
  public static final String ID = "@id@";
  public static final String NAME = "@name@";
  public static final String VERSION = "@version@";
  public static final String DESCRIPTION = "@description@";
  public static final String URL = "https://github.com/AuraDevelopmentTeam/AuraSudo";
  public static final String AUTHOR = "The_BrainStone";

  public AuraSudoBootstrapper() {
    super(AuraSudoBaseBootstrap.class);
  }

  @Override
  protected String[] getExcludedPackages() {
    String[] packages =
        new String[] {
          "@group@.api", // API package. The API isn't platform specific
          AuraSudoBootstrapper.class.getPackage().getName(), // Path for the common platform
          getPackageName() + ".bootstrap", // Path for the running platform
        };

    System.out.println(Arrays.toString(packages));

    return packages;
  }

  /**
   * Checks if SLF4J is present and loads it if not.<br>
   * {@code slf4jVersion} defaults to @slf4jVersion@
   *
   * @param libsPath Where to unpack the jar files to
   * @param type Which type of the slf4j-plugin-xxx to use
   * @see #checkAndLoadSLF4J(Path, String, String)
   */
  public void checkAndLoadSLF4JPlugin(Path libsPath, String type) {
    checkAndLoadSLF4J(libsPath, "@slf4jVersion@", "plugin-" + type + "-@slf4jPluginVersion@");
  }
}
