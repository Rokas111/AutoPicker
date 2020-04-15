package me.Rokaz.AutoPicker.core;

import com.google.common.collect.ImmutableMap;
import lombok.Getter;
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
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
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
    public static AutoPicker pl;
    @Getter private CommandManager commandManager;
    @Getter private ConfigManager configManager;
    @Getter private MessageConfig messageConfig;
    @Getter private AutoPickerConfig autoPickerConfig;
    @Getter private BreakRegistry breakRegistry;
    private Metrics m;
    public void onEnable() {
        pl = this;
        commandManager = new CommandManager();
        breakRegistry = new BreakRegistry();
        configManager = new ConfigManager();
        loadConfigs();
        loadCommands();
        m = new Metrics(this,6878);
    }
    public void onDisable() {
        pl = null;
    }
    private void loadConfigs() {
        autoPickerConfig = new AutoPickerConfig();
        messageConfig = new MessageConfig();
        configManager.registerConfig(autoPickerConfig);
        configManager.registerConfig(messageConfig);
        setupDefaults();
    }
    private void loadCommands() {
        commandManager.registerCommand(new AutoPickerReload());
        commandManager.registerCommand(new AutoPickerActivate());
        commandManager.registerCommand(new AutoPickerAutoSmelt());
    }
    private void setupDefaults() {
        setupMessageDefaults();
    }
    private void setupMessageDefaults() {
        MESSAGE_DEFAULTS.forEach((key,m) -> messageConfig.addDefault(key,m));
        messageConfig.setup();
    }

}
