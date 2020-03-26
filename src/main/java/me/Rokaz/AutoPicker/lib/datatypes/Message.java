package me.Rokaz.AutoPicker.lib.datatypes;

import lombok.Getter;
import me.Rokaz.AutoPicker.lib.config.adapter.ConfigAdaptable;
import me.Rokaz.AutoPicker.lib.config.add.ConfigSection;
import org.bukkit.ChatColor;

public class Message implements ConfigAdaptable {
    @Getter
    private String message;
    public Message(String message) {
        this.message = message;
        translate();
    }
    private void translate() {
        this.message = ChatColor.translateAlternateColorCodes('&',message);
    }
    public ConfigSection adapt(String name) {
        return new ConfigSection().add(name,message.replaceAll("§","&"));
    }
}
