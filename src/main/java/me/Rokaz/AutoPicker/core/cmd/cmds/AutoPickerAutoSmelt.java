package me.Rokaz.AutoPicker.core.cmd.cmds;

import me.Rokaz.AutoPicker.core.AutoPicker;
import me.Rokaz.AutoPicker.core.config.unit.MessageConfig;
import me.Rokaz.AutoPicker.lib.cmd.Command;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class AutoPickerAutoSmelt extends Command {
    public AutoPickerAutoSmelt() {
        super("AutoPicker.autopickerautosmelt", "enable/disable AutoSmelt", Arrays.asList(""), "apautosmelt");
    }
    public void run(Player p, String enteredCmd, String[] args) {
        boolean current = AutoPicker.pl.getAutoPickerConfig().autoSmelt();
        AutoPicker.pl.getAutoPickerConfig().toggleAutoSmelt();
        if (!current) p.sendMessage(AutoPicker.pl.getMessageConfig().obtain(MessageConfig.SUCCESSFUL_ENABLE_AUTOSMELT,p).getMessage()); else p.sendMessage(AutoPicker.pl.getMessageConfig().obtain(MessageConfig.SUCCESSFUL_DISABLE_AUTOSMELT,p).getMessage());
    }
}
