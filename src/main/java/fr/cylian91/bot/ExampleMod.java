package fr.cylian91.bot;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.client.message.v1.ClientReceiveMessageEvents;
import fr.cylian91.bot.config.Configuration;
import net.minecraft.client.MinecraftClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static java.lang.Thread.sleep;

public class ExampleMod implements ModInitializer {

	public static final Logger LOGGER = LoggerFactory.getLogger("bot");
	public String cmd;

	@Override
	public void onInitialize() {

		ClientReceiveMessageEvents.CHAT.register((message, signedMessage, sender, params, receptionTimestamp) -> {
			if (message.getString().contains("!bot ") && !(message.getString().contains(MinecraftClient.getInstance().player.getName().getString()+">"))) {

				cmd = message.getString().substring(message.getString().lastIndexOf("!bot")+5);
				try {
					String scmd = cmd.substring(0,cmd.indexOf(" ")>0 ? cmd.indexOf(" ") : 0);
					LOGGER.info(scmd);
					if (Configuration.getConfig("banCmd").contains(scmd)){
						MinecraftClient.getInstance().player.networkHandler.sendChatMessage("Command or args \"{}\" is baned".replace("{}", cmd));
					} else MinecraftClient.getInstance().player.networkHandler.sendChatMessage(cmd);
				} catch (IOException e) {
					throw new RuntimeException(e);
				}

				LOGGER.info("Received chat message sent by {} at time {}: {}", sender == null ? "null" : sender.getName(), receptionTimestamp.toEpochMilli(), message.getString());
			}
		});
		ClientReceiveMessageEvents.GAME.register((message,overlay) -> {
			if (message.getString().contains("!bot ")) {

				cmd = message.getString().substring(message.getString().lastIndexOf("!bot")+5);
				try {
					String scmd = cmd.substring(0,cmd.indexOf(" ")>0 ? cmd.indexOf(" ") : 0);
					LOGGER.info(scmd);
					if (Configuration.getConfig("banCmd").contains(scmd)){
						MinecraftClient.getInstance().player.networkHandler.sendChatMessage("Command or args \"{}\" is baned".replace("{}", cmd));
					} else MinecraftClient.getInstance().player.networkHandler.sendChatMessage(cmd);
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}

			if (message.getString().contains("/login")) {
				LOGGER.info(message.getString());
				MinecraftClient.getInstance().player.networkHandler.sendCommand("login bot1234");
			}
		});
	}
}