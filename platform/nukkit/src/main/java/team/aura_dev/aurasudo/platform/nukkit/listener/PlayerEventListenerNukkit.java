package team.aura_dev.aurasudo.platform.nukkit.listener;

import cn.nukkit.event.EventHandler;
import cn.nukkit.event.EventPriority;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerAsyncPreLoginEvent;
import cn.nukkit.event.player.PlayerQuitEvent;
import team.aura_dev.aurasudo.platform.common.AuraSudoBase;
import team.aura_dev.aurasudo.platform.common.player.PlayerManagerCommon;

public class PlayerEventListenerNukkit implements Listener {
  private final PlayerManagerCommon playerManager;

  public PlayerEventListenerNukkit(AuraSudoBase plugin) {
    this.playerManager = plugin.getPlayerManager();
  }

  @EventHandler(priority = EventPriority.LOW)
  public void onPlayerJoinAsync(PlayerAsyncPreLoginEvent event) {
    playerManager.fromNativePlayer(event.getPlayer());
  }

  @EventHandler(priority = EventPriority.MONITOR)
  public void onPlayerLeave(PlayerQuitEvent event) {
    playerManager.unloadPlayer(event.getPlayer().getServerId());
  }
}
