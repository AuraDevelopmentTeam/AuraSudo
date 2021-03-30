package team.aura_dev.aurasudo.platform.bungeecord.player;

import java.util.UUID;
import javax.annotation.Nonnull;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.chat.ComponentSerializer;
import team.aura_dev.aurasudo.platform.common.player.PlayerDataCommon;

public class PlayerDataBungeeCord extends PlayerDataCommon {
  public PlayerDataBungeeCord(@Nonnull UUID uuid, @Nonnull String playerName) {
    super(uuid, playerName);
  }

  @Nonnull
  @Override
  public String getDisplayName() {
    return getNativePlayer().getDisplayName();
  }

  @Override
  public void sendMessage(TextComponent message) {
    // TODO: Replace with platform implementation as soon as a solution to the SNAPSHOT issue is
    // available
    getNativePlayer()
        .sendMessage(ComponentSerializer.parse(GsonComponentSerializer.gson().serialize(message)));
  }

  @Override
  protected ProxiedPlayer getNativePlayer() {
    return getPlayerFromUUID(uuid);
  }

  public static ProxiedPlayer getPlayerFromUUID(UUID uuid) {
    return ProxyServer.getInstance().getPlayer(uuid);
  }
}
