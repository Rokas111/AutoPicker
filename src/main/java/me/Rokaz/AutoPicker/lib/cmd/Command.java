package me.Rokaz.AutoPicker.lib.cmd;

import lombok.Getter;
import me.Rokaz.AutoPicker.core.AutoPicker;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public abstract class Command extends org.bukkit.command.Command implements ICommand {
    @Getter private String permission;
    @Getter private String description;
    @Getter private List<String> usages;
    @Getter private List<String> cmdAliases;
    public Command(String permission,String description,List<String> usages,String... cmdAliases) {
        super(cmdAliases[0]);
        this.permission = permission;
        this.description = description;
        this.usages = usages;
        this.cmdAliases = Arrays.asList(cmdAliases);
        super.description = description;
    }
    @Override
    public boolean execute(CommandSender s, String alias, String[] args) {
        AutoPicker.cm.runCommand((Player) s,this,alias,args);
        return true;
    }
}
