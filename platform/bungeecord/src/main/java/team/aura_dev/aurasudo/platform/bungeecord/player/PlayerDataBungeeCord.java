package team.aura_dev.aurasudo.platform.bungeecord.player;

import java.util.UUID;
import javax.annotation.Nonnull;
import lombok.NonNull;
import net.md_5.bungee.api.ProxyServer;
import team.aura_dev.aurasudo.platform.common.player.PlayerDataCommon;

public class PlayerDataBungeeCord extends PlayerDataCommon {
  public PlayerDataBungeeCord(@NonNull UUID uuid) {
    super(uuid, ProxyServer.getInstance().getPlayer(uuid).getName());
  }

  @Nonnull
  @Override
  public String getDisplayName() {
    return ProxyServer.getInstance().getPlayer(uuid).getDisplayName();
  }
}
