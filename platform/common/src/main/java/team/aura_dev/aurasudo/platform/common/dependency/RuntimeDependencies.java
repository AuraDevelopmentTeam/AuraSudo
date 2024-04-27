package team.aura_dev.aurasudo.platform.common.dependency;

import lombok.experimental.UtilityClass;
import team.aura_dev.lib.multiplatformcore.dependency.RuntimeDependency;

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
              "3.7.3",
              "c5679b4202b226fbe07f84dfb300a8d0",
              "630e0562bd9b809428b55742aabe382f0347211f")
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
              "771fee269487d47767b3e7ceffd90ebe",
              "32491aff8ebf684e2a8cf28c60295a44410cf15a")
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
