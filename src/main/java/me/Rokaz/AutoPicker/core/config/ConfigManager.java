package me.Rokaz.AutoPicker.core.config;

import lombok.NoArgsConstructor;
import me.Rokaz.AutoPicker.lib.config.IConfig;

import java.util.ArrayList;
import java.util.List;
@NoArgsConstructor
public class ConfigManager {
    private final List<IConfig> configs = new ArrayList<>();
    public void registerConfig(IConfig c) {
        configs.add(c);
    }
    public void reloadConfigs() {
        configs.forEach(IConfig::reload);
    }
}
