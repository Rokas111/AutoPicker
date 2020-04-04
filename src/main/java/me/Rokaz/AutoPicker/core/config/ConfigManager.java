package me.Rokaz.AutoPicker.core.config;

import me.Rokaz.AutoPicker.lib.config.IConfig;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class ConfigManager {
    private final List<IConfig> configs = new ArrayList<>();
    private Plugin pl;
    public ConfigManager(Plugin pl) {
        this.pl = pl;
    }
    public void registerConfig(IConfig c) {
        configs.add(c);
    }
    public void reloadConfigs() {
        configs.forEach(IConfig::reload);
    }
}
