package team.aura_dev.aurasudo.platform.velocity.listener;

import com.velocitypowered.api.event.PostOrder;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.connection.DisconnectEvent;
import com.velocitypowered.api.event.connection.LoginEvent;
import team.aura_dev.aurasudo.platform.common.AuraSudoBase;
import team.aura_dev.aurasudo.platform.common.player.PlayerManagerCommon;

public class PlayerEventListenerVelocity {
  private final PlayerManagerCommon playerManager;

  public PlayerEventListenerVelocity(AuraSudoBase plugin) {
    this.playerManager = plugin.getPlayerManager();
  }

  @Subscribe(order = PostOrder.EARLY)
  public void onPlayerJoinAsync(LoginEvent event) {
    playerManager.fromNativePlayer(event.getPlayer());
  }

  @Subscribe(order = PostOrder.LAST)
  public void onPlayerLeave(DisconnectEvent event) {
    playerManager.unloadPlayer(event.getPlayer().getUniqueId());
  }
}
