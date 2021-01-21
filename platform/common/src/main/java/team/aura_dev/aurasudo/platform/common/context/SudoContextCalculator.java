package team.aura_dev.aurasudo.platform.common.context;

import lombok.RequiredArgsConstructor;
import net.luckperms.api.context.ContextCalculator;
import net.luckperms.api.context.ContextConsumer;
import net.luckperms.api.context.ContextSet;
import net.luckperms.api.context.ImmutableContextSet;
import org.checkerframework.checker.nullness.qual.NonNull;
import team.aura_dev.aurasudo.api.player.PlayerData;
import team.aura_dev.aurasudo.api.player.PlayerManager;

@RequiredArgsConstructor
public class SudoContextCalculator implements ContextCalculator<Object> {
  public static final String CONTEXT_SUDO = "sudo";

  protected final PlayerManager playerManager;
  protected final int maxSudoLevel;

  @Override
  public void calculate(@NonNull Object target, @NonNull ContextConsumer consumer) {
    final PlayerData data = playerManager.fromNativePlayer(target);

    if (data == null) return;

    consumer.accept(CONTEXT_SUDO, Integer.toString(data.getSudoLevel()));
  }

  @Override
  public ContextSet estimatePotentialContexts() {
    ImmutableContextSet.Builder builder = ImmutableContextSet.builder();

    for (int i = 0; i <= maxSudoLevel; i++) builder.add(CONTEXT_SUDO, Integer.toString(i));

    return builder.build();
  }
}
