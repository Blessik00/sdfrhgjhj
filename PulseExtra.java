package dev.pulse.extra;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.entity.effect.*;
import org.lwjgl.glfw.GLFW;
public class PulseExtra implements ClientModInitializer {
  boolean fb;
  @Override public void onInitializeClient() {
    KeyBinding open = KeyBindingHelper.registerKeyBinding(new KeyBinding("Open Pulse Extra", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_RIGHT_SHIFT, "Pulse Extra"));
    KeyBinding zoom = KeyBindingHelper.registerKeyBinding(new KeyBinding("Zoom", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_C, "Pulse Extra"));
    ClientTickEvents.END_CLIENT_TICK.register(mc -> {
      while (open.wasPressed()) mc.setScreen(new ClickGui());
      var p = mc.player; if (p == null) return;
      float target = (Mods.ZOOM.on && zoom.isPressed()) ? Mods.ZOOM.g(0) : 1f;
      Mods.zoomCur += (target - Mods.zoomCur) * 0.35f;
      if (Mods.FULL.on) { fb = true; if (!p.hasStatusEffect(StatusEffects.NIGHT_VISION)) p.addStatusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, 1000, 0, false, false, false)); }
      else if (fb) { fb = false; p.removeStatusEffect(StatusEffects.NIGHT_VISION); }
      if (Mods.SPRINT.on && p.input.movementForward > 0 && !p.isSneaking() && !p.horizontalCollision && p.getHungerManager().getFoodLevel() > 6) p.setSprinting(true);
    });
  }
}
