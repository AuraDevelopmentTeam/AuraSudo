package team.aura_dev.aurasudo.platform.common.dependency;

import lombok.experimental.UtilityClass;
import team.aura_dev.lib.multiplatformcore.dependency.RuntimeDependency;
import team.aura_dev.lib.multiplatformcore.dependency.RuntimeDependency.Maven;

@UtilityClass
public class RuntimeDependencies {
  ////////////////////////////////////////////////////////
  // Additional repos
  ////////////////////////////////////////////////////////
  //  private static final Maven OSS_SNAPSHOTS =
  //      new Maven("https://oss.sonatype.org/content/repositories/snapshots");

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
          .exclusion("org.codehaus.mojo:animal-sniffer-annotations")
          .build();

  ////////////////////////////////////////////////////////
  // Adventure (text library)
  ////////////////////////////////////////////////////////
  public static final RuntimeDependency ADVENTURE_JSON =
      RuntimeDependency.builder(
              "net.kyori",
              "adventure-text-serializer-gson",
              "4.4.0",
              "6913a7722334447e9edce6a55c94c4ef",
              "0c1cf5b0a0c2e25675a1fb91d089be44dee45e5d")
          .transitive()
          .exclusion("org.jetbrains:annotations")
          .build();
  public static final RuntimeDependency ADVENTURE_LEGACY =
      RuntimeDependency.builder(
              "net.kyori",
              "adventure-text-serializer-legacy",
              "4.4.0",
              "91d46a5acfbcc03e1e00165978640088",
              "1733a77bbdf535351bcfe96a0c42b3a89f87e872")
          .transitive()
          .exclusion("org.jetbrains:annotations")
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
