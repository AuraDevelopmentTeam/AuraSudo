package team.aura_dev.aurasudo.platform.sponge.player;

import java.util.Optional;
import java.util.UUID;
import javax.annotation.Nonnull;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.serializer.TextSerializers;
import team.aura_dev.aurasudo.platform.common.player.PlayerDataCommon;

public class PlayerDataSponge extends PlayerDataCommon {
  public PlayerDataSponge(@Nonnull UUID uuid, @Nonnull String playerName) {
    super(uuid, playerName);
  }

  @Nonnull
  @Override
  public String getDisplayName() {
    return getNativePlayer().getDisplayNameData().displayName().get().toString();
  }

  @Override
  public void sendMessage(TextComponent message) {
    getNativePlayer()
        .sendMessage(
            TextSerializers.JSON.deserialize(GsonComponentSerializer.gson().serialize(message)));
  }

  @Override
  protected Player getNativePlayer() {
    return getPlayerFromUUID(uuid).get();
  }

  public static Optional<Player> getPlayerFromUUID(UUID uuid) {
    return Sponge.getServer().getPlayer(uuid);
  }
}
