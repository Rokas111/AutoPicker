package me.Rokaz.AutoPicker.lib.config.unit;

import me.Rokaz.AutoPicker.lib.config.Config;
import me.Rokaz.AutoPicker.lib.config.adapter.ConfigAdaptable;
import me.Rokaz.AutoPicker.lib.config.add.ConfigSection;
import me.Rokaz.AutoPicker.lib.config.file.FileSection;

import java.util.ArrayList;
import java.util.List;

public abstract class UnitConfig<T extends ConfigAdaptable> extends Config implements IUnitConfig<T> {
    private final List<ConfigSection> sections = new ArrayList<>();
    public UnitConfig(String name, FileSection s) {
        super(name,s,false);
    }
    public ConfigSection getDefaults() {
        ConfigSection c = new ConfigSection();
        sections.forEach(section -> section.getKeys().forEach(c::add));
        return c;
    }
    public IUnitConfig addDefault(String name,T datatype) {
        sections.add(datatype.adapt(name));
        return this;
    }
}
