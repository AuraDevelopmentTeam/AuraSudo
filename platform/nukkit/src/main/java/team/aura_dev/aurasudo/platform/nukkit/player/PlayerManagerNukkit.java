package team.aura_dev.aurasudo.platform.nukkit.player;

import cn.nukkit.player.Player;
import java.util.Optional;
import java.util.UUID;
import javax.annotation.Nonnull;
import lombok.NonNull;
import team.aura_dev.aurasudo.platform.common.player.PlayerDataCommon;
import team.aura_dev.aurasudo.platform.common.player.PlayerManagerCommon;

public class PlayerManagerNukkit extends PlayerManagerCommon {
  @Override
  protected Optional<PlayerDataCommon> generatePlayerData(@Nonnull @NonNull UUID uuid) {
    return PlayerDataNukkit.getPlayerFromUUID(uuid)
        .map(player -> new PlayerDataNukkit(uuid, player.getName()));
  }

  @Nonnull
  @Override
  protected PlayerDataCommon generatePlayerData(@Nonnull @NonNull BasePlayerData basePlayerData) {
    return new PlayerDataNukkit(basePlayerData.getUuid(), basePlayerData.getPlayerName());
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
