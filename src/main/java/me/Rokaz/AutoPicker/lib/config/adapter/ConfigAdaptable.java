package me.Rokaz.AutoPicker.lib.config.adapter;

import me.Rokaz.AutoPicker.lib.config.add.ConfigSection;

public interface ConfigAdaptable {
    ConfigSection adapt(String name);
}
