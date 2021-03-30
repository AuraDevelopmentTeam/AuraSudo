package team.aura_dev.aurasudo.platform.sponge.player;

import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.source.ConsoleSource;
import org.spongepowered.api.text.serializer.TextSerializers;
import team.aura_dev.aurasudo.platform.common.player.ConsolePlayerDataCommon;

public class ConsolePlayerDataSponge extends ConsolePlayerDataCommon {
  public static final ConsolePlayerDataSponge INSTANCE = new ConsolePlayerDataSponge();

  private final ConsoleSource console;

  protected ConsolePlayerDataSponge() {
    console = Sponge.getServer().getConsole();
  }

  @Override
  public void sendMessage(TextComponent message) {
    console.sendMessage(
        TextSerializers.JSON.deserialize(GsonComponentSerializer.gson().serialize(message)));
  }

  @Override
  protected Object getNativePlayer() {
    return console;
  }
}
