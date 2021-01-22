package team.aura_dev.aurasudo.platform.sponge.player;

import java.util.Optional;
import java.util.UUID;
import javax.annotation.Nonnull;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.Player;
import team.aura_dev.aurasudo.platform.common.player.PlayerDataCommon;

public class PlayerDataSponge extends PlayerDataCommon {
  public PlayerDataSponge(@Nonnull UUID uuid, @Nonnull String playerName) {
    super(uuid, playerName);
  }

  @Nonnull
  @Override
  public String getDisplayName() {
    return getPlayerFromUUID(uuid).get().getDisplayNameData().displayName().get().toString();
  }

  public static Optional<Player> getPlayerFromUUID(UUID uuid) {
    return Sponge.getServer().getPlayer(uuid);
  }
}
