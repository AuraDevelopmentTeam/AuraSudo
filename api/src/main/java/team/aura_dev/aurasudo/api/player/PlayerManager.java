package team.aura_dev.aurasudo.api.player;

import java.util.Optional;
import java.util.UUID;
import javax.annotation.Nonnull;

public interface PlayerManager {
  /**
   * Gets the {@link PlayerData} object of the player associated with the passed {@link UUID}.<br>
   * This also does load offline players.
   *
   * @param uuid The UUID used to identify the player.<br>
   *     Must not be <code>null</code>
   * @return the data of the player associated with the passed UUID wrapped in an {@link Optional}.
   *     Or an empty Optional if the player does not exist.
   */
  public Optional<? extends PlayerData> getPlayerData(@Nonnull UUID uuid);

  /**
   * Does the same as {@link #getPlayerData(UUID)} but returns the {@link PlayerData} object
   * directly or <code>null</code> if it doesn't exist.
   *
   * @param uuid The {@link UUID} used to identify the player.<br>
   *     Must not be <code>null</code>
   * @return the data of the player associated with the passed UUID. Or <code>null</code> if the
   *     player does not exist.
   * @see #getPlayerData(UUID)
   */
  public default PlayerData getPlayerDataUnsafe(@Nonnull UUID uuid) {
    return getPlayerData(uuid).orElse(null);
  }

  /**
   * Converts a native player instance into a {@link PlayerData} object.
   *
   * @param player The native player object to convert
   * @return A {@link PlayerData} object constructed from the passed native player object.
   * @throws IllegalArgumentException when the object is not a native player object.
   */
  public PlayerData fromNativePlayer(@Nonnull Object player) throws IllegalArgumentException;
}
