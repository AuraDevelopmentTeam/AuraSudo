package team.aura_dev.aurasudo.platform.velocity.player;

import com.velocitypowered.api.proxy.Player;
import java.util.Optional;
import java.util.UUID;
import lombok.NonNull;
import net.kyori.adventure.text.TextComponent;
import team.aura_dev.aurasudo.platform.common.player.PlayerDataCommon;
import team.aura_dev.aurasudo.platform.velocity.AuraSudoVelocity;

public class PlayerDataVelocity extends PlayerDataCommon {
  public PlayerDataVelocity(@NonNull UUID uuid, @NonNull String playerName) {
    super(uuid, playerName);
  }

  @Override
  public void sendMessage(TextComponent message) {
    getNativePlayer().sendMessage(message);
  }

  @Override
  protected Player getNativePlayer() {
    return getPlayerFromUUID(uuid).get();
  }

  public static Optional<Player> getPlayerFromUUID(UUID uuid) {
    return AuraSudoVelocity.getServer().getPlayer(uuid);
  }
}
