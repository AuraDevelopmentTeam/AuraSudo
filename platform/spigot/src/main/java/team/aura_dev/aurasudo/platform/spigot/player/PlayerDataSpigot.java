package team.aura_dev.aurasudo.platform.spigot.player;

import java.util.UUID;
import javax.annotation.Nonnull;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;
import net.md_5.bungee.chat.ComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import team.aura_dev.aurasudo.platform.common.player.PlayerDataCommon;

public class PlayerDataSpigot extends PlayerDataCommon {
  public PlayerDataSpigot(@Nonnull UUID uuid, @Nonnull String playerName) {
    super(uuid, playerName);
  }

  @Nonnull
  @Override
  public String getDisplayName() {
    return getNativePlayer().getDisplayName();
  }

  @Override
  public void sendMessage(TextComponent message) {
    // TODO: Replace with platform implementation as soon as a solution to the SNAPSHOT issue is
    // available
    getNativePlayer()
        .spigot()
        .sendMessage(ComponentSerializer.parse(GsonComponentSerializer.gson().serialize(message)));
  }

  @Override
  protected Player getNativePlayer() {
    return getPlayerFromUUID(uuid);
  }

  public static Player getPlayerFromUUID(UUID uuid) {
    return Bukkit.getPlayer(uuid);
  }
}
