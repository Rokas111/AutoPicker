package me.Rokaz.AutoPicker.core.config;

import me.Rokaz.AutoPicker.core.AutoPicker;
import me.Rokaz.AutoPicker.lib.config.Config;
import me.Rokaz.AutoPicker.lib.config.add.ConfigSection;
import me.Rokaz.AutoPicker.lib.config.file.FileSection;
import org.bukkit.ChatColor;
import org.bukkit.Sound;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class AutoPickerConfig extends Config {
    public AutoPickerConfig() {
        super("config",new FileSection(AutoPicker.PLUGIN_FOLDER), true);
    }
    public ConfigSection getDefaults() {
        return new ConfigSection()
                .add("AutoPicker",true)
                .add("AutoSmelt",true)
                .add("sounds.onMine","")
                .add("sounds.inventoryFull","")
                .add("itemAdditions.name","")
                .add("itemAdditions.lore", Collections.singletonList(""))
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
    public String getItemAdditionName() {
        return ChatColor.translateAlternateColorCodes('&',getYaml().getString("itemAdditions.name"));
    }
    public List<String> getItemAdditionLore() {
        return getYaml().getStringList("itemAdditions.lore").stream().map(line -> ChatColor.translateAlternateColorCodes('&',line)).collect(Collectors.toList());
    }
    public Sound getOnMineSound() {
        return !getYaml().getString("sounds.onMine").isEmpty()?Sound.valueOf(getYaml().getString("sounds.onMine")):null;
    }
    public Sound getInventoryFullSound() {
        return !getYaml().getString("sounds.inventoryFull").isEmpty()?Sound.valueOf(getYaml().getString("sounds.inventoryFull")):null;
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
