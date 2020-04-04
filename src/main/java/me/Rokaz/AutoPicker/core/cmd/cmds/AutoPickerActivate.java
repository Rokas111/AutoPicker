package me.Rokaz.AutoPicker.core.cmd.cmds;

import me.Rokaz.AutoPicker.core.AutoPicker;
import me.Rokaz.AutoPicker.core.BreakRegistry;
import me.Rokaz.AutoPicker.core.config.unit.MessageConfig;
import me.Rokaz.AutoPicker.lib.cmd.Command;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class AutoPickerActivate extends Command {
    public AutoPickerActivate() {
        super("AutoPicker.autopickeractivate", "enable/disable the autopicker for yourself", Arrays.asList(""), "apactivate");
    }
    public void run(Player p, String enteredCmd, String[] args) {
        boolean current = ((!BreakRegistry.getAutoPickerPlayers().isEmpty()&& BreakRegistry.getAutoPickerPlayers().containsKey(p)) || AutoPicker.apc.isEnabled()) && ((BreakRegistry.getAutoPickerPlayers().isEmpty() || !BreakRegistry.getAutoPickerPlayers().containsKey(p))||BreakRegistry.getAutoPickerPlayers().get(p));
        BreakRegistry.setAutoPickerPlayer(p);
        if (!current) p.sendMessage(AutoPicker.mc.obtain(MessageConfig.SUCCESSFUL_ENABLE_AUTOPICKER,p).getMessage()); else p.sendMessage(AutoPicker.mc.obtain(MessageConfig.SUCCESSFUL_DISABLE_AUTOPICKER,p).getMessage());
    }

}
