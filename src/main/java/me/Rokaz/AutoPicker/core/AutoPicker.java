package me.Rokaz.AutoPicker.core;

import com.google.common.collect.ImmutableMap;
import me.Rokaz.AutoPicker.core.cmd.CommandManager;
import me.Rokaz.AutoPicker.core.config.AutoPickerConfig;
import me.Rokaz.AutoPicker.core.config.unit.MessageConfig;
import me.Rokaz.AutoPicker.lib.datatypes.Message;
import org.bstats.bukkit.Metrics;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public class AutoPicker extends JavaPlugin {
    public static final String PLUGIN_FOLDER = "AutoPicker";
    public static final String UNIT_FOLDER = "Units";
    private static final HashMap<String, Message> MESSAGE_DEFAULTS = new HashMap<>(ImmutableMap.<String,Message>builder()
            .put(MessageConfig.NO_PERMISSION_MESSAGE_KEY,new Message(ChatColor.RED +"" + ChatColor.BOLD + "You don't have the permission to execute this command"))
            .put(MessageConfig.INVENTORY_FULL_MESSAGE_KEY,new Message(ChatColor.RED +"" + ChatColor.BOLD + "Your inventory is full")).build());
    public static CommandManager cm;
    public static MessageConfig mc;
    public static AutoPickerConfig apc;
    public static Metrics m;
    protected static int brokenBlocks = 0;
    public void onEnable() {
        cm = new CommandManager(this);
        registerConfigs();
        m = new Metrics(this,6878);
        m.addCustomChart(new Metrics.SingleLineChart("blocksBroken",() -> {
            int bb = brokenBlocks;
            brokenBlocks = 0;
            return brokenBlocks;
        }));
    }
    private void registerConfigs() {
        apc = new AutoPickerConfig();
        mc = new MessageConfig();
    }

}
