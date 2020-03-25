package me.Rokaz.AutoPicker.lib.config.unit;

import me.Rokaz.AutoPicker.lib.config.IConfig;
import me.Rokaz.AutoPicker.lib.config.adapter.ConfigAdaptable;
import org.bukkit.entity.Player;

public interface IUnitConfig<T extends ConfigAdaptable> extends IConfig {
    IUnitConfig addDefault(String name, T datatype);
    T obtain(String name, Player p);
}
