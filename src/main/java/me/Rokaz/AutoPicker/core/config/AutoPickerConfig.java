package me.Rokaz.AutoPicker.core.config;

import me.Rokaz.AutoPicker.core.AutoPicker;
import me.Rokaz.AutoPicker.lib.config.Config;
import me.Rokaz.AutoPicker.lib.config.add.ConfigSection;
import me.Rokaz.AutoPicker.lib.config.file.FileSection;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AutoPickerConfig extends Config {
    public AutoPickerConfig() {
        super("config",new FileSection(AutoPicker.PLUGIN_FOLDER), true);
    }
    public ConfigSection getDefaults() {
        return new ConfigSection()
                .add("AutoPicker",true)
                .add("AutoSmelt",true)
                .add("disabled_worlds", Collections.singletonList(""));
    }
    public boolean isEnabled() {
        return getYaml().getBoolean("AutoPicker");
    }
    public boolean autoSmelt() {
        return getYaml().getBoolean("AutoSmelt");
    }
    public List<String> getDisabledWorld() {
        return getYaml().getStringList("disabled_worlds");
    }
    public void toggleAutoSmelt() {
        getYaml().set("AutoSmelt", !autoSmelt());
        try {
            getYaml().save(getFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
