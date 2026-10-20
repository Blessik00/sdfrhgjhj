package dev.pulse.extra.mixin;
import dev.pulse.extra.Mods;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
@Mixin(LivingEntity.class)
public class LivingMixin {
  @Inject(method="getHandSwingDuration", at=@At("RETURN"), cancellable=true)
  private void pe$swing(CallbackInfoReturnable<Integer> cir){
    if(Mods.SWING.on && (Object)this==MinecraftClient.getInstance().player)
      cir.setReturnValue(Math.max(1,Math.round(cir.getReturnValue()/Mods.SWING.g(0))));
  }
}
