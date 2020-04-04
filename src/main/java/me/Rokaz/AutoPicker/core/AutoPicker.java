package me.Rokaz.AutoPicker.core;

import com.google.common.collect.ImmutableMap;
import me.Rokaz.AutoPicker.core.cmd.CommandManager;
import me.Rokaz.AutoPicker.core.cmd.cmds.AutoPickerActivate;
import me.Rokaz.AutoPicker.core.cmd.cmds.AutoPickerAutoSmelt;
import me.Rokaz.AutoPicker.core.cmd.cmds.AutoPickerReload;
import me.Rokaz.AutoPicker.core.config.AutoPickerConfig;
import me.Rokaz.AutoPicker.core.config.ConfigManager;
import me.Rokaz.AutoPicker.core.config.unit.MessageConfig;
import me.Rokaz.AutoPicker.lib.config.IConfig;
import me.Rokaz.AutoPicker.lib.datatypes.Message;
import me.Rokaz.AutoPicker.lib.metrics.Metrics;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AutoPicker extends JavaPlugin {
    public static final String PLUGIN_FOLDER = "AutoPicker";
    public static final String UNIT_FOLDER = "Units";
    private static final HashMap<String, Message> MESSAGE_DEFAULTS = new HashMap<>(ImmutableMap.<String,Message>builder()
            .put(MessageConfig.NO_PERMISSION_MESSAGE_KEY,new Message(ChatColor.RED +"" + ChatColor.BOLD + "You don't have the permission to execute this command"))
            .put(MessageConfig.INVENTORY_FULL_MESSAGE_KEY,new Message(ChatColor.RED +"" + ChatColor.BOLD + "Your inventory is full"))
            .put(MessageConfig.SUCCESSFUL_RELOAD_KEY,new Message(ChatColor.GREEN +"" + ChatColor.BOLD + "Successfully reloaded all plugin's configs"))
            .put(MessageConfig.SUCCESSFUL_ENABLE_AUTOPICKER,new Message(ChatColor.GREEN +"" + ChatColor.BOLD + "Successfully enabled autopicker for yourself"))
            .put(MessageConfig.SUCCESSFUL_DISABLE_AUTOPICKER,new Message(ChatColor.GREEN +"" + ChatColor.BOLD + "Successfully disabled autopicker for yourself"))
            .put(MessageConfig.SUCCESSFUL_ENABLE_AUTOSMELT,new Message(ChatColor.GREEN +"" + ChatColor.BOLD + "Successfully enabled AutoSmelt"))
            .put(MessageConfig.SUCCESSFUL_DISABLE_AUTOSMELT,new Message(ChatColor.GREEN +"" + ChatColor.BOLD + "Successfully disabled AutoSmelt")).build());
    public static CommandManager cm;
    public static ConfigManager cgm;
    public static MessageConfig mc;
    public static AutoPickerConfig apc;
    public static BreakRegistry br;
    public static Metrics m;
    static int brokenBlocks = 0;
    public void onEnable() {
        cm = new CommandManager(this);
        br = new BreakRegistry(this);
        cgm = new ConfigManager(this);
        registerConfigs();
        registerCommands();
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
        cgm.registerConfig(apc);
        cgm.registerConfig(mc);
        setupDefaults();
    }
    private void registerCommands() {
        cm.registerCommand(new AutoPickerReload());
        cm.registerCommand(new AutoPickerActivate());
        cm.registerCommand(new AutoPickerAutoSmelt());
    }
    private void setupDefaults() {
        setupMessageDefaults();
    }
    private void setupMessageDefaults() {
        MESSAGE_DEFAULTS.forEach((key,m) -> mc.addDefault(key,m));
        mc.setup();
    }

}
