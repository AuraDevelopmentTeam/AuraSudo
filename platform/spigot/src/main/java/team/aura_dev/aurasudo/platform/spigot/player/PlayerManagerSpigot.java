package team.aura_dev.aurasudo.platform.spigot.player;

import java.util.Optional;
import java.util.UUID;
import javax.annotation.Nonnull;
import lombok.NonNull;
import org.bukkit.entity.Player;
import team.aura_dev.aurasudo.api.player.PlayerData;
import team.aura_dev.aurasudo.platform.common.player.PlayerManagerCommon;

public class PlayerManagerSpigot extends PlayerManagerCommon {
  @Override
  protected Optional<PlayerData> generatePlayerData(@Nonnull @NonNull UUID uuid) {
    return Optional.of(new PlayerDataSpigot(uuid));
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
    } else {
      throw new IllegalArgumentException(
          "The passed player object (" + player + ") is not of type " + Player.class.getName());
    }

    return new BasePlayerData(uuid, playerName);
  }
}
