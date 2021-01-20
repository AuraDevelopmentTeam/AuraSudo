package team.aura_dev.aurasudo.platform.sponge.player;

import java.util.UUID;
import javax.annotation.Nonnull;
import lombok.NonNull;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.Player;
import team.aura_dev.aurasudo.platform.common.player.PlayerDataCommon;

public class PlayerDataSponge extends PlayerDataCommon {
  public PlayerDataSponge(@NonNull UUID uuid) {
    super(uuid, getPlayerFromUUID(uuid).getName());
  }

  @Nonnull
  @Override
  public String getDisplayName() {
    return getPlayerFromUUID(uuid).getDisplayNameData().displayName().get().toString();
  }

  private static Player getPlayerFromUUID(UUID uuid) {
    return Sponge.getServer().getPlayer(uuid).get();
  }
}
