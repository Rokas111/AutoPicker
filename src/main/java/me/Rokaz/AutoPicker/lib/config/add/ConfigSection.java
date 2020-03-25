package me.Rokaz.AutoPicker.lib.config.add;

import lombok.Getter;
import lombok.NoArgsConstructor;
import me.Rokaz.AutoPicker.lib.config.IConfig;

import java.util.HashMap;

@NoArgsConstructor
public class ConfigSection {
    @Getter private final HashMap<String,Object> keys = new HashMap<>();
    public ConfigSection add(String key, Object value) {
        keys.put(key, value);
        return this;
    }
    public ConfigSection remove(String key) {
        keys.remove(key);
        return this;
    }
    public void addToConfig(IConfig c, String path) {
        keys.keySet().forEach(key -> {
            if (keys.get(key) instanceof ConfigSection) {
                ((ConfigSection)keys.get(key)).addToConfig(c,path+"."+key);
            } else {
                c.getYaml().set(path+"."+key,keys.get(key));
            }
        });
        c.save();
    }
    public void addToConfig(IConfig c) {
        keys.keySet().forEach(key -> {
            if (keys.get(key) instanceof ConfigSection) {
                ((ConfigSection)keys.get(key)).addToConfig(c,key);
            } else {
                c.getYaml().set(key,keys.get(key));
            }
        });
        c.save();
    }
}
