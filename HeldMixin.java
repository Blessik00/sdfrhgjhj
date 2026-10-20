package dev.pulse.extra.mixin;
import dev.pulse.extra.Mods;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.math.RotationAxis;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
@Mixin(HeldItemRenderer.class)
public class HeldMixin {
  @Inject(method="renderFirstPersonItem", at=@At("HEAD"))
  private void pe$vm(AbstractClientPlayerEntity p,float tp,float pitch,Hand hand,float sp,ItemStack it,float eq,MatrixStack m,VertexConsumerProvider v,int l,CallbackInfo ci){
    if(!Mods.VIEW.on)return;
    float side=hand==Hand.MAIN_HAND?1f:-1f; var s=Mods.VIEW;
    m.translate(s.g(0)*side,s.g(1),s.g(2));
    m.scale(s.g(3),s.g(3),s.g(3));
    m.multiply(RotationAxis.POSITIVE_X.rotationDegrees(s.g(4)));
    m.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(s.g(5)*side));
    m.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(s.g(6)*side));
  }
}
