package team.aura_dev.aurasudo.platform.common.config;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.reflect.TypeToken;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.regex.Pattern;
import lombok.Getter;
import lombok.NonNull;
import ninja.leaping.configurate.ConfigurationOptions;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.hocon.HoconConfigurationLoader;
import ninja.leaping.configurate.loader.ConfigurationLoader;
import ninja.leaping.configurate.loader.HeaderMode;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;
import team.aura_dev.aurasudo.platform.common.AuraSudoBase;

/** This class is required because it references Configurate classes, which are loaded later. */
public class ConfigLoader {
  private final AuraSudoBase plugin;
  private ConfigurationLoader<CommentedConfigurationNode> loader;

  @Getter @NonNull private Config config;

  public ConfigLoader(AuraSudoBase plugin) {
    this.plugin = plugin;
    this.loader = getConfigLoader();
  }

  private ConfigurationLoader<CommentedConfigurationNode> getConfigLoader() {
    return HoconConfigurationLoader.builder()
        .setDefaultOptions(
            ConfigurationOptions.defaults()
                .setHeader(
                    "######################################################################################################################### #\n"
                        + "+-----------------------------------------------------------------------------------------------------------------------+ #\n"
                        + getBanner()
                        + "|                                                                                                                       | #\n"
                        + "|  Source Code: https://github.com/AuraDevelopmentTeam/AuraSudo/                                                        | #\n"
                        + "|  Bug Reports: https://github.com/AuraDevelopmentTeam/AuraSudo/issues                                                  | #\n"
                        + "|  Wiki:        https://aura-dev.team/documentation/AuraSudo                                                            | #\n"
                        + "|                                                                                                                       | #\n"
                        + "|  New options ARE added to this file automatically. Removing a setting will have it be regenerated with its default    | #\n"
                        + "|  value. This config format (HOCON: https://github.com/lightbend/config/blob/master/HOCON.md) is very lenient, so      | #\n"
                        + "|  so don't worry about messing up the formatting or anything. After every reload the config will be nice and           | #\n"
                        + "|  formatted. You should be able to find existing highlighter settings for your favorite text editor, so you can have   | #\n"
                        + "|  nice syntax highlighing like for other config formats like YAML, JSON or TOML.                                       | #\n"
                        + "+-----------------------------------------------------------------------------------------------------------------------+ #\n"
                        + "######################################################################################################################### #"))
        .setHeaderMode(HeaderMode.PRESET)
        // These two are commented out because those are the defaults anyways. Additionally
        // SpongeForge relocates HOCON classes, so we can't reference them directly anyways.
        //
        // .setRenderOptions(ConfigRenderOptions.defaults().setOriginComments(false).setJson(false))
        // .setParseOptions(ConfigParseOptions.defaults())
        .setPath(plugin.getConfigFile())
        .build();
  }

  /**
   * This method takes the plugin banner, removes the color codes, pads it left and right with
   * spaces and lastly adds the banner box ASCII art borders to it, so it fits in the block up
   * above.<br>
   * This is done so that we can have the dynamic banner in the config.
   *
   * <p>While this sounds a fair bit simpler than it actually is this method is quite long and
   * complex.<br>
   * Feel free to try to improve it if it bothers you.
   *
   * @return A version of the plugin banner suitable for the config.
   */
  @VisibleForTesting
  String getBanner() {
    // Constants
    final int lineLength = 115;
    final char[] spaces = new char[lineLength];
    Arrays.fill(spaces, ' ');
    final Pattern colorCode = Pattern.compile("§[0-9a-fk-or]", Pattern.CASE_INSENSITIVE);

    // Variables
    List<String> bannerLines = new ArrayList<>(plugin.getAsciiBanner());
    int maxLength = 0;
    String result;
    StringBuilder builder;

    // First pass:
    // Remove color codes
    for (int i = 0; i < bannerLines.size(); ++i) {
      result = colorCode.matcher(bannerLines.get(i)).replaceAll("");
      bannerLines.set(i, result);

      if (maxLength < result.length()) {
        maxLength = result.length();
      }
    }

    // The banner starts with two spaces, let's use this to compensate
    maxLength += 2;

    // Store these values, so we don't have to calculate them again
    final int remainingLength = lineLength - maxLength;
    final int paddingBefore = remainingLength / 2;
    final int paddingAfter = remainingLength - paddingBefore;

    // Second pass:
    // Add space paddings
    for (int i = 0; i < bannerLines.size(); ++i) {
      result = bannerLines.get(i);
      builder =
          new StringBuilder(lineLength)
              .append(spaces, 0, paddingBefore)
              .append(result)
              .append(spaces, 0, maxLength - result.length())
              .append(spaces, 0, paddingAfter);

      bannerLines.set(i, builder.toString());
    }

    // Join the string
    final StringJoiner joiner = new StringJoiner("  | #\n|  ", "|  ", "  | #\n");

    for (final String line : bannerLines) {
      joiner.add(line);
    }

    return joiner.toString();
  }

  public void loadConfig() throws IOException, ObjectMappingException {
    final TypeToken<Config> configToken = TypeToken.of(Config.class);

    AuraSudoBase.logger.debug("Loading config...");

    CommentedConfigurationNode node = loader.load();

    config = node.<Config>getValue(configToken, Config::new);

    AuraSudoBase.logger.debug("Saving/Formatting config...");
    node.setValue(configToken, config);
    loader.save(node);
  }
}
