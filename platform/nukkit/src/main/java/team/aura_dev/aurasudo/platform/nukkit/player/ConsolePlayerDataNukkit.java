package team.aura_dev.aurasudo.platform.nukkit.player;

import cn.nukkit.Server;
import cn.nukkit.command.ConsoleCommandSender;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import team.aura_dev.aurasudo.platform.common.player.ConsolePlayerDataCommon;

public class ConsolePlayerDataNukkit extends ConsolePlayerDataCommon {
  public static final ConsolePlayerDataNukkit INSTANCE = new ConsolePlayerDataNukkit();

  private final ConsoleCommandSender console;

  protected ConsolePlayerDataNukkit() {
    console = Server.getInstance().getConsoleSender();
  }

  @Override
  public void sendMessage(TextComponent message) {
    console.sendMessage(LegacyComponentSerializer.legacySection().serialize(message));
  }

  @Override
  protected Object getNativePlayer() {
    return console;
  }
}
