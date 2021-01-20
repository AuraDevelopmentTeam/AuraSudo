package team.aura_dev.aurasudo.platform.common.config;

import java.util.Arrays;
import java.util.List;
import lombok.Getter;
import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

@ConfigSerializable
@Getter
public class Config {
  @Setting private General general = new General();

  @Setting private Commands command = new Commands();

  @ConfigSerializable
  @Getter
  public static class General {
    @Setting(comment = "Whether to show the colorful startup banner or not")
    private boolean bannerEnabled = true;
  }

  @ConfigSerializable
  @Getter
  public static class Commands {
    @Setting private Sudo sudo = new Sudo();

    @ConfigSerializable
    @Getter
    public static class Sudo {
      @Setting(comment = "A list of aliases for this command.")
      private List<String> aliases = Arrays.asList("sudo", "adminmode", "staffmode", "am", "sm");
    }
  }
}
