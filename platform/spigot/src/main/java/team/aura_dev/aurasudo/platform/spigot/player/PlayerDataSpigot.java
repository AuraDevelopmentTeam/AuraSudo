package team.aura_dev.aurasudo.platform.spigot.player;

import java.util.UUID;
import javax.annotation.Nonnull;
import lombok.NonNull;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import team.aura_dev.aurasudo.platform.common.player.PlayerDataCommon;

public class PlayerDataSpigot extends PlayerDataCommon {
  public PlayerDataSpigot(@NonNull UUID uuid) {
    super(uuid, getPlayerFromUUID(uuid).getName());
  }

  @Nonnull
  @Override
  public String getDisplayName() {
    return getPlayerFromUUID(uuid).getDisplayName();
  }

  private static Player getPlayerFromUUID(UUID uuid) {
    return Bukkit.getPlayer(uuid);
  }
}
