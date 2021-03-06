package team.aura_dev.aurasudo.platform.sponge.player;

import java.util.Optional;
import java.util.UUID;
import javax.annotation.Nonnull;
import lombok.NonNull;
import org.spongepowered.api.command.source.ConsoleSource;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.profile.GameProfile;
import team.aura_dev.aurasudo.platform.common.player.ConsolePlayerDataCommon;
import team.aura_dev.aurasudo.platform.common.player.PlayerDataCommon;
import team.aura_dev.aurasudo.platform.common.player.PlayerManagerCommon;

public class PlayerManagerSponge extends PlayerManagerCommon {
  @Override
  protected Optional<PlayerDataCommon> generatePlayerData(@Nonnull @NonNull UUID uuid) {
    return PlayerDataSponge.getPlayerFromUUID(uuid)
        .map(player -> new PlayerDataSponge(uuid, player.getName()));
  }

  @Nonnull
  @Override
  protected PlayerDataCommon generatePlayerData(@Nonnull @NonNull BasePlayerData basePlayerData) {
    return ConsolePlayerDataCommon.UUID.equals(basePlayerData.getUuid())
        ? ConsolePlayerDataSponge.INSTANCE
        : new PlayerDataSponge(basePlayerData.getUuid(), basePlayerData.getPlayerName());
  }

  @Override
  protected BasePlayerData nativePlayerToBasePlayerData(Object player)
      throws IllegalArgumentException {
    UUID uuid;
    String playerName;

    if (player instanceof BasePlayerData) {
      return (BasePlayerData) player;
    } else if (player instanceof Player) {
      final Player nativePlayer = (Player) player;

      uuid = nativePlayer.getUniqueId();
      playerName = nativePlayer.getName();
    } else if (player instanceof GameProfile) {
      final GameProfile nativePlayer = (GameProfile) player;

      uuid = nativePlayer.getUniqueId();
      playerName = nativePlayer.getName().get();
    } else if (player instanceof ConsoleSource) {
      return BasePlayerData.CONSOLE;
    } else {
      throw new IllegalArgumentException(
          "The passed player object ("
              + player
              + ") is not of type "
              + Player.class.getName()
              + ", "
              + GameProfile.class.getName()
              + " or "
              + ConsoleSource.class.getName());
    }

    return new BasePlayerData(uuid, playerName);
  }
}
