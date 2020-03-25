package me.Rokaz.AutoPicker.core.cmd;

import me.Rokaz.AutoPicker.core.AutoPicker;
import me.Rokaz.AutoPicker.core.config.unit.MessageConfig;
import me.Rokaz.AutoPicker.lib.cmd.Command;
import me.Rokaz.AutoPicker.lib.cmd.ICommand;
import org.bukkit.command.CommandMap;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class CommandManager {
    private Plugin pl;
    private List<Command> commands;
    public CommandManager(Plugin pl) {
        this.pl = pl;
        this.commands = new ArrayList<>();
    }
    public void runCommand(Player p, ICommand cmd, String enteredCmd, String[] args) {
        if (!p.getPlayer().hasPermission(cmd.getPermission())) {
            p.sendMessage(AutoPicker.mc.obtain(MessageConfig.NO_PERMISSION_MESSAGE_KEY,p).getMessage());
            return;
        }
        cmd.run(p,enteredCmd, args);
    }
    public void registerCommand(Command cmd) {
        commands.add(cmd);
        try {
            final Field f = pl.getServer().getClass().getDeclaredField("commandMap");
            f.setAccessible(true);
            ((CommandMap) f.get(pl.getServer())).register(cmd.getCmdAliases().get(0),cmd);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    public List<Command> getCommands() {
        return this.commands;
    }
}
