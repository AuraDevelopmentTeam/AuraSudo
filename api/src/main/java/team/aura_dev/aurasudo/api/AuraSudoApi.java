package team.aura_dev.aurasudo.api;

import team.aura_dev.aurasudo.api.player.PlayerManager;

public interface AuraSudoApi {
  public static final String ID = "@id@";
  public static final String NAME = "@name@";
  public static final String VERSION = "@version@";
  public static final String DESCRIPTION = "@description@";
  public static final String URL = "https://github.com/AuraDevelopmentTeam/AuraSudo";
  public static final String AUTHOR = "The_BrainStone";

  public PlayerManager getPlayerManager();

  public int getMaxSudoLevel();
}
