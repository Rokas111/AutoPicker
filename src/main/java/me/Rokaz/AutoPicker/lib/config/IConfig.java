package me.Rokaz.AutoPicker.lib.config;

import me.Rokaz.AutoPicker.lib.config.add.ConfigSection;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public interface IConfig {
    File getFile();
    YamlConfiguration getYaml();
    void setup();
    ConfigSection getDefaults();
    void save();
    void reload();
}
