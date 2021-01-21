package team.aura_dev.aurasudo.platform.common.permission;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import team.aura_dev.aurasudo.api.AuraSudoApi;

@Data
@RequiredArgsConstructor
public class Permission {
  public static final Permission BASE = new Permission(AuraSudoApi.ID);
  public static final Permission COMMAND = new Permission(BASE, "command");

  protected final String permission;

  public Permission(Permission basePermission, String permission) {
    this(basePermission.getPermission() + '.' + permission);
  }
}
