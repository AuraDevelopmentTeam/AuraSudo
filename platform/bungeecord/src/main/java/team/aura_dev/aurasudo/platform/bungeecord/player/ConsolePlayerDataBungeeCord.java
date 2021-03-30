package team.aura_dev.aurasudo.platform.bungeecord.player;

import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.chat.ComponentSerializer;
import team.aura_dev.aurasudo.platform.common.player.ConsolePlayerDataCommon;

public class ConsolePlayerDataBungeeCord extends ConsolePlayerDataCommon {
  public static final ConsolePlayerDataBungeeCord INSTANCE = new ConsolePlayerDataBungeeCord();

  private final CommandSender console;

  protected ConsolePlayerDataBungeeCord() {
    console = ProxyServer.getInstance().getConsole();
  }

  @Override
  public void sendMessage(TextComponent message) {
    console.sendMessage(
        ComponentSerializer.parse(GsonComponentSerializer.gson().serialize(message)));
  }

  @Override
  protected Object getNativePlayer() {
    return console;
  }
}
