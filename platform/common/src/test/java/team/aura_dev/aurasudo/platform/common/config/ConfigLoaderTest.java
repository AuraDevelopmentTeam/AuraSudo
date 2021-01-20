package team.aura_dev.aurasudo.platform.common.config;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import team.aura_dev.aurasudo.api.AuraSudoApi;
import team.aura_dev.aurasudo.platform.common.AuraSudoBase;
import team.aura_dev.aurasudo.platform.common.AuraSudoTest;

public class ConfigLoaderTest {
  @Test
  public void getBannerTest() {
    final AuraSudoBase plugin = new AuraSudoTest();
    final ConfigLoader loader = new ConfigLoader(plugin);

    assertEquals(
        "|                           __                                                                                          | #\n"
            + "|               /\\     _ _ (_     _| _    AuraSudo v"
            + AuraSudoApi.VERSION
            + repeat(' ', 54 - AuraSudoApi.VERSION.length())
            + "              | #\n"
            + "|              /--\\|_|| (_|__)|_|(_|(_)   Proudly running on Testing - Unittests (Here's some extra text)               | #\n"
            + "|                                                                                                                       | #\n",
        loader.getBanner());
  }

  private static String repeat(char character, int count) {
    final StringBuilder build = new StringBuilder(count);

    for (int i = 0; i < count; ++i) {
      build.append(character);
    }

    return build.toString();
  }
}
