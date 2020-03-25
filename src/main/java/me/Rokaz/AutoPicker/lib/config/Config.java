package me.Rokaz.AutoPicker.lib.config;

import lombok.Getter;
import me.Rokaz.AutoPicker.core.AutoPicker;
import me.Rokaz.AutoPicker.lib.config.file.FileSection;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public abstract class Config implements IConfig {
    private String name;
    @Getter private YamlConfiguration yaml;
    @Getter private FileSection section;
    public Config(String name,boolean setup) {
        this.name = name;
        this.section = new FileSection(AutoPicker.PLUGIN_FOLDER);
        reload();
        if (setup) {
            setup();
        }
    }
    public Config(String name, FileSection section,boolean setup) {
        this.name = name;
        this.section = section;
        reload();
        if (setup) {
            setup();
        }
    }
    public File getFile() {
        return new File(section.getFile().getPath() + "//" + name + ".yml");
    }
    public void setup() {
        boolean setup = true;
        if (!getFile().exists()) {
            try {
                getFile().createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            setup = false;
        }
        if (!setup) {
            getDefaults().addToConfig(this);
        }
    }
    public void save() {
        try {
            yaml.save(getFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void reload() {
        yaml = YamlConfiguration.loadConfiguration(getFile());
    }
}
