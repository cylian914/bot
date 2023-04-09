package fr.cylian91.bot.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.Session;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;

import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Session.class)
public class ExampleMixin {
	@Inject(at = @At("HEAD"), method = "getUsername", cancellable = true)
	private void getUsername(CallbackInfoReturnable<String> cir) {
		cir.setReturnValue("cylian91_alt1");
	}
}

