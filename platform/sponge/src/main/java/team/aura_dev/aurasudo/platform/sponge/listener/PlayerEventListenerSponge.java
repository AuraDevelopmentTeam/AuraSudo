package team.aura_dev.aurasudo.platform.sponge.listener;

import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.Order;
import org.spongepowered.api.event.network.ClientConnectionEvent;
import team.aura_dev.aurasudo.platform.common.AuraSudoBase;
import team.aura_dev.aurasudo.platform.common.player.PlayerManagerCommon;

public class PlayerEventListenerSponge {
  private final PlayerManagerCommon playerManager;

  public PlayerEventListenerSponge(AuraSudoBase plugin) {
    this.playerManager = plugin.getPlayerManager();
  }

  @Listener(order = Order.EARLY)
  public void onPlayerJoinAsync(ClientConnectionEvent.Auth event) {
    playerManager.fromNativePlayer(event.getProfile());
  }

  @Listener(order = Order.LAST)
  public void onPlayerLeave(ClientConnectionEvent.Disconnect event) {
    playerManager.unloadPlayer(event.getTargetEntity().getUniqueId());
  }
}
