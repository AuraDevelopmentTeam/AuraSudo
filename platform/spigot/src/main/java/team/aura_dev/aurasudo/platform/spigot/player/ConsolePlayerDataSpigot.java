package team.aura_dev.aurasudo.platform.spigot.player;

import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;
import net.md_5.bungee.chat.ComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender.Spigot;
import team.aura_dev.aurasudo.platform.common.player.ConsolePlayerDataCommon;

public class ConsolePlayerDataSpigot extends ConsolePlayerDataCommon {
  public static final ConsolePlayerDataSpigot INSTANCE = new ConsolePlayerDataSpigot();

  private final Spigot console;

  protected ConsolePlayerDataSpigot() {
    console = Bukkit.getConsoleSender().spigot();
  }

  @Override
  public void sendMessage(TextComponent message) {
    console.sendMessage(
        ComponentSerializer.parse(GsonComponentSerializer.gson().serialize(message)));
  }
}
