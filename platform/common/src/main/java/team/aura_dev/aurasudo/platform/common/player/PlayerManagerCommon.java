package team.aura_dev.aurasudo.platform.common.player;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;
import lombok.Data;
import lombok.NonNull;
import team.aura_dev.aurasudo.api.player.PlayerData;
import team.aura_dev.aurasudo.api.player.PlayerManager;

public abstract class PlayerManagerCommon implements PlayerManager {
  protected final LoadingCache<UUID, Optional<PlayerData>> playerCache;

  protected PlayerManagerCommon() {
    playerCache =
        Caffeine.newBuilder()
            .expireAfterAccess(10, TimeUnit.MINUTES)
            .build(this::generatePlayerData);
  }

  protected abstract Optional<PlayerData> generatePlayerData(@Nonnull @NonNull UUID uuid);

  @Nonnull
  protected abstract PlayerData generatePlayerData(@Nonnull @NonNull BasePlayerData basePlayerData);

  @SuppressFBWarnings(
      value = "NP_NULL_ON_SOME_PATH_FROM_RETURN_VALUE",
      justification = "SpotBugs is incorrect in this case")
  @Override
  public Optional<PlayerData> getPlayerData(@Nonnull @NonNull UUID uuid) {
    return playerCache.get(uuid);
  }

  @Override
  public PlayerData fromNativePlayer(@Nonnull @NonNull Object player)
      throws IllegalArgumentException {
    final BasePlayerData playerData = nativePlayerToBasePlayerData(player);
    final UUID uuid = playerData.getUuid();
    Optional<PlayerData> data = playerCache.getIfPresent(uuid);

    if ((data != null) && data.isPresent()) return data.get();

    data = Optional.of(generatePlayerData(playerData));
    playerCache.put(uuid, data);

    return data.get();
  }

  /**
   * This method removes the specified player from the cache
   *
   * @param uuid The {@link UUID} of the player to remove
   */
  public void unloadPlayer(@Nonnull @NonNull UUID uuid) {
    playerCache.invalidate(uuid);
  }

  /**
   * This method removes the specified player from the cache
   *
   * @param player The player to remove
   */
  public void unloadPlayer(@Nonnull @NonNull PlayerData player) {
    unloadPlayer(player.getUuid());
  }

  /**
   * Collects the {@link UUID} and player name of a native player
   *
   * @param player The native player object to convert
   * @return A {@link BasePlayerData} object that contains the data from the native player.
   * @throws IllegalArgumentException when the object is not a native player object.
   */
  protected abstract BasePlayerData nativePlayerToBasePlayerData(@Nonnull Object player)
      throws IllegalArgumentException;

  @Data
  public static class BasePlayerData {
    @NonNull private final UUID uuid;
    @NonNull private final String playerName;
  }
}
