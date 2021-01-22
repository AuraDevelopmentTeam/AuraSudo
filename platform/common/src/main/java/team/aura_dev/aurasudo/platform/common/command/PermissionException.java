package team.aura_dev.aurasudo.platform.common.command;

import javax.annotation.Nullable;
import lombok.Getter;
import team.aura_dev.aurasudo.platform.common.permission.Permission;

public class PermissionException extends CommandExecutionException {
  @Getter protected final Permission permission;

  public PermissionException() {
    this(null);
  }

  public PermissionException(@Nullable Permission permission) {
    this.permission = permission;
  }

  @Override
  public String getMessageComponent() {
    return "No permissions ;(";
  }
}
