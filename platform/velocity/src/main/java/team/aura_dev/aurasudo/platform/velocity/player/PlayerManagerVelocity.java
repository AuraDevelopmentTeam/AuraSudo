package team.aura_dev.aurasudo.platform.velocity.player;

import com.velocitypowered.api.proxy.ConsoleCommandSource;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.ProxyServer;
import com.velocitypowered.api.util.GameProfile;
import java.util.Optional;
import java.util.UUID;
import javax.annotation.Nonnull;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import team.aura_dev.aurasudo.platform.common.player.ConsolePlayerDataCommon;
import team.aura_dev.aurasudo.platform.common.player.PlayerDataCommon;
import team.aura_dev.aurasudo.platform.common.player.PlayerManagerCommon;

@RequiredArgsConstructor
public class PlayerManagerVelocity extends PlayerManagerCommon {
  private final ProxyServer server;

  @Override
  protected Optional<PlayerDataCommon> generatePlayerData(@Nonnull @NonNull UUID uuid) {
    return server.getPlayer(uuid).map(player -> new PlayerDataCommon(uuid, player.getUsername()));
  }

  @Nonnull
  @Override
  protected PlayerDataCommon generatePlayerData(@Nonnull @NonNull BasePlayerData basePlayerData) {
    return ConsolePlayerDataCommon.UUID.equals(basePlayerData.getUuid())
        ? ConsolePlayerDataVelocity.INSTANCE
        : new PlayerDataCommon(basePlayerData.getUuid(), basePlayerData.getPlayerName());
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
      playerName = nativePlayer.getUsername();
    } else if (player instanceof GameProfile) {
      final GameProfile nativePlayer = (GameProfile) player;

      uuid = nativePlayer.getId();
      playerName = nativePlayer.getName();
    } else if (player instanceof ConsoleCommandSource) {
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
              + ConsoleCommandSource.class.getName());
    }

    return new BasePlayerData(uuid, playerName);
  }
}
