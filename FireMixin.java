package dev.pulse.extra.mixin;
import dev.pulse.extra.Mods;
import net.minecraft.client.gui.hud.InGameOverlayRenderer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
@Mixin(InGameOverlayRenderer.class)
public class FireMixin {
  @Inject(method="renderFireOverlay", at=@At("HEAD"))
  private static void pe$a(MatrixStack m,VertexConsumerProvider v,CallbackInfo ci){ if(Mods.FIRE.on)m.translate(0,-Mods.FIRE.g(0),0); }
  @Inject(method="renderFireOverlay", at=@At("RETURN"))
  private static void pe$b(MatrixStack m,VertexConsumerProvider v,CallbackInfo ci){ if(Mods.FIRE.on)m.translate(0,Mods.FIRE.g(0),0); }
}
