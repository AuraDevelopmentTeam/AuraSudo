package team.aura_dev.aurasudo.platform.nukkit.player;

import cn.nukkit.Server;
import cn.nukkit.player.Player;
import java.util.Optional;
import java.util.UUID;
import javax.annotation.Nonnull;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import team.aura_dev.aurasudo.platform.common.player.PlayerDataCommon;

public class PlayerDataNukkit extends PlayerDataCommon {
  public PlayerDataNukkit(@Nonnull UUID uuid, @Nonnull String playerName) {
    super(uuid, playerName);
  }

  @Nonnull
  @Override
  public String getDisplayName() {
    return getNativePlayer().getDisplayName();
  }

  @Override
  public void sendMessage(TextComponent message) {
    getNativePlayer().sendMessage(LegacyComponentSerializer.legacySection().serialize(message));
  }

  @Override
  protected Player getNativePlayer() {
    return getPlayerFromUUID(uuid).get();
  }

  public static Optional<Player> getPlayerFromUUID(UUID uuid) {
    return Server.getInstance().getPlayer(uuid);
  }
}
