package team.aura_dev.aurasudo.platform.bungeecord.player;

import java.util.Optional;
import java.util.UUID;
import javax.annotation.Nonnull;
import lombok.NonNull;
import net.md_5.bungee.api.connection.PendingConnection;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import team.aura_dev.aurasudo.api.player.PlayerData;
import team.aura_dev.aurasudo.platform.common.player.PlayerManagerCommon;

public class PlayerManagerBungeeCord extends PlayerManagerCommon {
  @Override
  protected Optional<PlayerData> generatePlayerData(@Nonnull @NonNull UUID uuid) {
    return Optional.of(new PlayerDataBungeeCord(uuid));
  }

  @Override
  protected BasePlayerData nativePlayerToBasePlayerData(Object player)
      throws IllegalArgumentException {
    UUID uuid;
    String playerName;

    if (player instanceof BasePlayerData) {
      return (BasePlayerData) player;
    } else if (player instanceof ProxiedPlayer) {
      final ProxiedPlayer nativePlayer = (ProxiedPlayer) player;

      uuid = nativePlayer.getUniqueId();
      playerName = nativePlayer.getName();
    } else if (player instanceof PendingConnection) {
      final PendingConnection nativePlayer = (PendingConnection) player;

      uuid = nativePlayer.getUniqueId();
      playerName = nativePlayer.getName();
    } else {
      throw new IllegalArgumentException(
          "The passed player object ("
              + player
              + ") is not of type "
              + ProxiedPlayer.class.getName()
              + " or "
              + PendingConnection.class.getName());
    }

    return new BasePlayerData(uuid, playerName);
  }
}
