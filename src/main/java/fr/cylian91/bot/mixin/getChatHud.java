package fr.cylian91.bot.mixin;

import fr.cylian91.bot.ExampleMod;
import fr.cylian91.bot.config.Configuration;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.ChatHud;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static java.lang.Thread.sleep;

@Mixin(ChatHud.class)
public class getChatHud {
    @Inject(at = @At("HEAD"), method = "addMessage")
    public void getChatHud(Text message, CallbackInfo ci) throws InterruptedException   {
        String cmd;
        if (message.getString().contains("[Baritone]")) {
            cmd = message.getString().substring(11);
            sleep(100);
            MinecraftClient.getInstance().player.networkHandler.sendChatMessage(cmd);
            ExampleMod.LOGGER.info("Received chat message sent by GAME: {}", message.getString());
        }
    }
}
