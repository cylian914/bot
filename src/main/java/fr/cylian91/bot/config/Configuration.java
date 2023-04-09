package fr.cylian91.bot.config;


import com.google.gson.Gson;
import fr.cylian91.bot.ExampleMod;
import net.fabricmc.loader.api.FabricLoader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public final class Configuration
{

    private static final Path configFolder = Path.of(FabricLoader.getInstance().getConfigDir() + "/bot");
    private static ArrayList<String> list = new ArrayList<>();
    public static ArrayList<String> getConfig(String configName) throws IOException {
        ExampleMod.LOGGER.info(String.valueOf(configFolder));
        try {
            Path configFile = configFolder.resolve(configName + ".json");
            Files.createDirectories(configFolder);
            if (Files.exists(configFile)) {
                File config = new File(configFile.toString());
                Scanner configRead = new Scanner(config);
                while (configRead.hasNextLine()) {
                    list.add(configRead.nextLine());
                }
                return list;
            } else {
                Files.createFile(configFile);
            }

        } catch (Exception e) {

            return null;
        }
        return null;
    }
    public static void writeConfig(String configName, String data) throws IOException {
        try {
            Path configFile = configFolder.resolve(configName + ".json");
            Files.createDirectories(configFolder);
            if (!Files.exists(configFile)) Files.createFile(configFile);
            FileWriter config = new FileWriter(configFile.toString());
            if (!getConfig(configName).contains(data)) {
                config.write(data);
                config.close();
            }
        } catch (Exception e){
            return;
        }
    }
}