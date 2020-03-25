package me.Rokaz.AutoPicker.core.config;

import me.Rokaz.AutoPicker.core.AutoPicker;
import me.Rokaz.AutoPicker.lib.config.Config;
import me.Rokaz.AutoPicker.lib.config.add.ConfigSection;
import me.Rokaz.AutoPicker.lib.config.file.FileSection;

public class AutoPickerConfig extends Config {
    public AutoPickerConfig() {
        super("config",new FileSection(AutoPicker.PLUGIN_FOLDER), true);
    }
    public ConfigSection getDefaults() {
        return new ConfigSection()
                .add("AutoPicker",true)
                .add("AutoSmelt",true);
    }
    public boolean isEnabled() {
        return getYaml().getBoolean("AutoPicker");
    }
    public boolean autoSmelt() {
        return getYaml().getBoolean("AutoSmelt");
    }
}
