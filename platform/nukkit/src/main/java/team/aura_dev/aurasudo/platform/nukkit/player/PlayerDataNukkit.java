package team.aura_dev.aurasudo.platform.nukkit.player;

import cn.nukkit.Server;
import cn.nukkit.player.Player;
import java.util.UUID;
import javax.annotation.Nonnull;
import team.aura_dev.aurasudo.platform.common.player.PlayerDataCommon;

public class PlayerDataNukkit extends PlayerDataCommon {
  public PlayerDataNukkit(@Nonnull UUID uuid) {
    super(uuid, getPlayerFromUUID(uuid).getName());
  }

  @Nonnull
  @Override
  public String getDisplayName() {
    return getPlayerFromUUID(uuid).getDisplayName();
  }

  private static Player getPlayerFromUUID(UUID uuid) {
    return Server.getInstance().getPlayer(uuid).get();
  }
}
