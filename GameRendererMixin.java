package dev.pulse.extra.mixin;
import dev.pulse.extra.Mods;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;
@Mixin(GameRenderer.class)
public class GameRendererMixin {
  @Inject(method="tiltViewWhenHurt", at=@At("HEAD"), cancellable=true)
  private void pe$hurt(MatrixStack m,float f,CallbackInfo ci){ if(Mods.HURT.on)ci.cancel(); }
  @Inject(method="getFov", at=@At("RETURN"), cancellable=true)
  private void pe$fov(Camera c,float f,boolean b,CallbackInfoReturnable<Float> cir){
    if(Math.abs(Mods.zoomCur-1f)>0.001f)cir.setReturnValue(cir.getReturnValue()/Mods.zoomCur);
  }
}
