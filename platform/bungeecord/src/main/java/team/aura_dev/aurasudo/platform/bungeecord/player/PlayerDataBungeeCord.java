package team.aura_dev.aurasudo.platform.bungeecord.player;

import java.util.UUID;
import javax.annotation.Nonnull;
import lombok.NonNull;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import team.aura_dev.aurasudo.platform.common.player.PlayerDataCommon;

public class PlayerDataBungeeCord extends PlayerDataCommon {
  public PlayerDataBungeeCord(@NonNull UUID uuid) {
    super(uuid, getPlayerFromUUID(uuid).getName());
  }

  @Nonnull
  @Override
  public String getDisplayName() {
    return getPlayerFromUUID(uuid).getDisplayName();
  }

  private static ProxiedPlayer getPlayerFromUUID(UUID uuid) {
    return ProxyServer.getInstance().getPlayer(uuid);
  }
}
