package team.aura_dev.aurasudo.platform.common.dependency;

import lombok.experimental.UtilityClass;
import team.aura_dev.lib.multiplatformcore.dependency.RuntimeDependency;
import team.aura_dev.lib.multiplatformcore.dependency.RuntimeDependency.Maven;

@UtilityClass
public class RuntimeDependencies {
  ////////////////////////////////////////////////////////
  // Config
  ////////////////////////////////////////////////////////
  public static final RuntimeDependency CONFIGURATE_HOCON =
      RuntimeDependency.builder(
              "org.spongepowered",
              "configurate-hocon",
              "3.6.1",
              "6395403afce7b9bbf4e26ef74c13da9a",
              "e3f199dbd91de753a70f63606f530fdb8644bbd5")
          .maven(Maven.SPONGE)
          .transitive()
          .exclusion("com.google.code.findbugs:jsr305")
          .exclusion("com.google.errorprone:error_prone_annotations")
          .exclusion("com.google.j2objc:j2objc-annotations")
          // org.codehaus.mojo gets relocated. That's why we need to make sure we don't have a
          // literal "org.codehaus.mojo" in any strings
          .exclusion("org.Codehaus.mojo:animal-sniffer-annotations".toLowerCase())
          .build();

  ////////////////////////////////////////////////////////
  // Utilities
  ////////////////////////////////////////////////////////
  public static final RuntimeDependency CAFFEINE =
      RuntimeDependency.builder(
              "com.github.ben-manes.caffeine",
              "caffeine",
              "2.8.0",
              "d6dbff7e409b1c2ad88930e2c220ea13",
              "6000774d7f8412ced005a704188ced78beeed2bb")
          .build();
}
