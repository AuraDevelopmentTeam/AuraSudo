package team.aura_dev.aurasudo.platform.spigot.player;

import java.util.UUID;
import javax.annotation.Nonnull;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import team.aura_dev.aurasudo.platform.common.player.PlayerDataCommon;

public class PlayerDataSpigot extends PlayerDataCommon {
  public PlayerDataSpigot(@Nonnull UUID uuid, @Nonnull String playerName) {
    super(uuid, playerName);
  }

  @Nonnull
  @Override
  public String getDisplayName() {
    return getPlayerFromUUID(uuid).getDisplayName();
  }

  public static Player getPlayerFromUUID(UUID uuid) {
    return Bukkit.getPlayer(uuid);
  }
}
