package team.aura_dev.aurasudo.platform.bungeecord.player;

import java.util.Optional;
import java.util.UUID;
import javax.annotation.Nonnull;
import lombok.NonNull;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.PendingConnection;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import team.aura_dev.aurasudo.platform.common.player.ConsolePlayerDataCommon;
import team.aura_dev.aurasudo.platform.common.player.PlayerDataCommon;
import team.aura_dev.aurasudo.platform.common.player.PlayerManagerCommon;

public class PlayerManagerBungeeCord extends PlayerManagerCommon {
  @Override
  protected Optional<PlayerDataCommon> generatePlayerData(@Nonnull @NonNull UUID uuid) {
    return Optional.ofNullable(PlayerDataBungeeCord.getPlayerFromUUID(uuid))
        .map(player -> new PlayerDataBungeeCord(uuid, player.getName()));
  }

  @Nonnull
  @Override
  protected PlayerDataCommon generatePlayerData(@Nonnull @NonNull BasePlayerData basePlayerData) {
    return ConsolePlayerDataCommon.UUID.equals(basePlayerData.getUuid())
        ? ConsolePlayerDataBungeeCord.INSTANCE
        : new PlayerDataBungeeCord(basePlayerData.getUuid(), basePlayerData.getPlayerName());
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
    } else if (player instanceof CommandSender) {
      // CommandSender but not a player means this is the console account
      return BasePlayerData.CONSOLE;
    } else {
      throw new IllegalArgumentException(
          "The passed player object ("
              + player
              + ") is not of type "
              + ProxiedPlayer.class.getName()
              + ", "
              + PendingConnection.class.getName()
              + " or "
              + CommandSender.class.getName());
    }

    return new BasePlayerData(uuid, playerName);
  }
}
