package me.Rokaz.AutoPicker.core.cmd.cmds;

import me.Rokaz.AutoPicker.core.AutoPicker;
import me.Rokaz.AutoPicker.core.config.unit.MessageConfig;
import me.Rokaz.AutoPicker.lib.cmd.Command;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class AutoPickerReload extends Command {
    public AutoPickerReload() {
        super("AutoPicker.autopickerreload", "Reloads all AutoPicker configs", Arrays.asList(""), "apreload");
    }
    public void run(Player p, String enteredCmd, String[] args) {
        AutoPicker.cgm.reloadConfigs();
        p.sendMessage(AutoPicker.mc.obtain(MessageConfig.SUCCESSFUL_RELOAD_KEY,p).getMessage());
    }
}
