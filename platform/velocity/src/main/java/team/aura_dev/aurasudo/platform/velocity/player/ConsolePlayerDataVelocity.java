package team.aura_dev.aurasudo.platform.velocity.player;

import com.velocitypowered.api.proxy.ConsoleCommandSource;
import net.kyori.adventure.text.TextComponent;
import team.aura_dev.aurasudo.platform.common.player.ConsolePlayerDataCommon;
import team.aura_dev.aurasudo.platform.velocity.AuraSudoVelocity;

public class ConsolePlayerDataVelocity extends ConsolePlayerDataCommon {
  public static final ConsolePlayerDataVelocity INSTANCE = new ConsolePlayerDataVelocity();

  private final ConsoleCommandSource console =
      AuraSudoVelocity.getServer().getConsoleCommandSource();

  protected ConsolePlayerDataVelocity() {
    super();
  }

  @Override
  public void sendMessage(TextComponent message) {
    console.sendMessage(message);
  }

  @Override
  protected Object getNativePlayer() {
    return console;
  }
}
