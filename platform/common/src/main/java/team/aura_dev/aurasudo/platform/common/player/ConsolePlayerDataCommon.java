package team.aura_dev.aurasudo.platform.common.player;

import java.util.UUID;
import team.aura_dev.aurasudo.platform.common.permission.Permission;

public abstract class ConsolePlayerDataCommon extends PlayerDataCommon {
  public static final UUID UUID = new UUID(0, 0);
  public static final String NAME = "Console";

  public ConsolePlayerDataCommon() {
    super(UUID, NAME);
  }

  @Override
  public final boolean isConsole() {
    return true;
  }

  @Override
  public boolean hasPermission(Permission permission) {
    return true;
  }

  @Deprecated
  @Override
  public final int getSudoLevel() throws UnsupportedOperationException {
    throw new UnsupportedOperationException(
        "Getting the sudo level for the console is unsupported");
  }

  @Deprecated
  @Override
  public final void setSudoLevel(int sudoLevel) throws UnsupportedOperationException {
    throw new UnsupportedOperationException(
        "Setting the sudo level for the console is unsupported");
  }
}
