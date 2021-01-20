package team.aura_dev.aurasudo.api.player;

import java.util.UUID;
import javax.annotation.Nonnull;

/**
 * Simple class to represent players in a platform independent way.
 *
 * <p>It is also used to get and set the sudo state
 */
public interface PlayerData {
  @Nonnull
  public UUID getUuid();

  @Nonnull
  public String getPlayerName();

  @Nonnull
  public String getDisplayName();

  public boolean getSudoActive();

  public void setSudoActive(boolean active);
}
