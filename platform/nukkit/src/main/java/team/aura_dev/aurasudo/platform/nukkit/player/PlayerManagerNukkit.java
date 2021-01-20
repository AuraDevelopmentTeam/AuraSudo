package team.aura_dev.aurasudo.platform.nukkit.player;

import cn.nukkit.player.Player;
import java.util.Optional;
import java.util.UUID;
import javax.annotation.Nonnull;
import lombok.NonNull;
import team.aura_dev.aurasudo.api.player.PlayerData;
import team.aura_dev.aurasudo.platform.common.player.PlayerManagerCommon;

public class PlayerManagerNukkit extends PlayerManagerCommon {
  @Override
  protected Optional<PlayerData> generatePlayerData(@Nonnull @NonNull UUID uuid) {
    return Optional.of(new PlayerDataNukkit(uuid));
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

      uuid = nativePlayer.getServerId();
      playerName = nativePlayer.getName();
    } else {
      throw new IllegalArgumentException(
          "The passed player object (" + player + ") is not of type " + Player.class.getName());
    }

    return new BasePlayerData(uuid, playerName);
  }
}
