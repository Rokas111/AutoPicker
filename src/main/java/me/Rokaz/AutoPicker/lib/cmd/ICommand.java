package me.Rokaz.AutoPicker.lib.cmd;

import org.bukkit.entity.Player;

import java.util.List;

public interface ICommand {
    List<String> getAliases();
    List<String> getUsages();
    String getPermission();
    String getDescription();
    void run(Player p, String enteredCmd, String[] args);
}
