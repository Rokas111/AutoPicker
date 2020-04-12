package me.Rokaz.AutoPicker.core;

import me.Rokaz.AutoPicker.core.config.unit.MessageConfig;
import me.Rokaz.AutoPicker.core.simulators.FortuneSimulator;
import me.Rokaz.AutoPicker.core.simulators.SilkTouchSimulator;
import me.Rokaz.AutoPicker.core.utils.AnnotationProcessor;
import me.Rokaz.AutoPicker.lib.legacy.SinceVersion;
import me.Rokaz.AutoPicker.lib.legacy.Version;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

public class BreakRegistry implements Listener {
    private final static String AUTO_SMELT_PERMISSION = "AutoPicker.autosmelt";
    private final static List<String> TOOLS_NAMES = Arrays.asList("SHEARS","HOE","AXE","PICKAXE","SHOVEL");
    private final static HashMap<Player,Boolean> AUTOPICKER_PLAYERS = new HashMap<>();
    public BreakRegistry(Plugin pl) {
        pl.getServer().getPluginManager().registerEvents(this,pl);
    }
    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        Player p = e.getPlayer();
        ItemStack item = p.getItemInHand();
        if (!e.isCancelled() && (AUTOPICKER_PLAYERS.containsKey(p)|| AutoPicker.apc.isEnabled()) && (!AUTOPICKER_PLAYERS.containsKey(p) || AUTOPICKER_PLAYERS.get(p)) && p.getGameMode() == GameMode.SURVIVAL && AutoPicker.apc.getDisabledWorld().parallelStream().noneMatch(world -> p.getWorld().getName().equals(world))) {
            List<ItemStack> items;
            if (item.hasItemMeta() && item.getItemMeta().hasEnchant(Enchantment.SILK_TOUCH) && !new SilkTouchSimulator().simulate(p,e.getBlock(),item.getItemMeta().getEnchantLevel(Enchantment.SILK_TOUCH)).isEmpty()) {
                items = new SilkTouchSimulator().simulate(p,e.getBlock(),item.getItemMeta().getEnchantLevel(Enchantment.SILK_TOUCH));
            } else  {
                 items = new ArrayList<>(e.getBlock().getDrops(p.getItemInHand()));
                if (item.hasItemMeta() && item.getItemMeta().hasEnchant(Enchantment.LOOT_BONUS_BLOCKS)) items = new FortuneSimulator().simulate(p,e.getBlock(),item.getItemMeta().getEnchantLevel(Enchantment.LOOT_BONUS_BLOCKS));
                items = checkItems(items,e.getBlock(),p,false);
            }
            items = checkItems(items,e.getBlock(),p,true);
            boolean full = false;
            for (ItemStack i : items) {
                if (!p.getInventory().addItem(i).isEmpty()) full = true;
            }
            if (full) p.sendMessage(AutoPicker.mc.obtain(MessageConfig.INVENTORY_FULL_MESSAGE_KEY,p).getMessage());
            AutoPicker.brokenBlocks += 1;
            e.getBlock().getDrops().clear();
            e.getBlock().getLocation().getBlock().setType(Material.AIR);
            p.giveExp(e.getExpToDrop());
            if (TOOLS_NAMES.stream().anyMatch(name -> item.getType().name().endsWith(name))) item.setDurability((short)1);
            p.getInventory().setItemInHand(item);
        }
    }

    @AutoPickerCheck(acceptedMaterials = {"GOLD_ORE","IRON_ORE"},checkWithSilkTouch = false)
    public static List<ItemStack> checkSmelt(List<ItemStack> items, Block b, Player p) {
        if (AutoPicker.apc.autoSmelt() && p.hasPermission(AUTO_SMELT_PERMISSION)) {
            items = items.stream().peek(item -> item.setType(Material.valueOf(item.getType().name().split("_")[0] + "_INGOT"))).collect(Collectors.toList());
        }
        return items;
    }
    @AutoPickerCheck(acceptedBlockMaterial = "WHEAT",checkWithSilkTouch = true)
    public static List<ItemStack> checkWheat(List<ItemStack> items,Block b,Player p) {
        if (b.getData() == 7) items.add(new ItemStack(Material.WHEAT,1));
        return items;
    }
    @AutoPickerCheck(checkWithSilkTouch = true)
    public static List<ItemStack> checkAdditions(List<ItemStack> items,Block b,Player p) {
        if (!AutoPicker.apc.getItemAdditionName().isEmpty()) items = items.stream().peek(item -> {
            ItemMeta m = item.getItemMeta();
            m.setDisplayName(AutoPicker.apc.getItemAdditionName());
            item.setItemMeta(m);
        }).collect(Collectors.toList());
        if (!AutoPicker.apc.getItemAdditionLore().isEmpty()) items = items.stream().peek(item -> {
            ItemMeta m = item.getItemMeta();
            m.setLore(AutoPicker.apc.getItemAdditionLore());
            item.setItemMeta(m);
        }).collect(Collectors.toList());
        return items;
    }
    private static List<ItemStack> checkItems(List<ItemStack> items,Block b,Player p,boolean checkWithSilkTouch) {
        for (Method m: AnnotationProcessor.getAnnotatedMethods(BreakRegistry.class,AutoPickerCheck.class)) {
            try {
                AutoPickerCheck apc = m.getAnnotation(AutoPickerCheck.class);
                if (apc.checkWithSilkTouch() != checkWithSilkTouch) continue;
                if (!apc.acceptedBlockMaterial().equals("AIR") && apc.acceptedBlockMaterial().equals(b.getType().name().replace("LEGACY_",""))) continue;
                if (!Arrays.asList(apc.acceptedMaterials()).isEmpty() &&items.parallelStream().noneMatch(item -> Arrays.asList(apc.acceptedMaterials()).stream().anyMatch(mat -> item.getType().name().replace("LEGACY_","").equals(mat)))) continue;
                items = (List<ItemStack>) m.invoke(BreakRegistry.class,items,b,p);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return items;
    }
    public static void setAutoPickerPlayer(Player p) {
        AUTOPICKER_PLAYERS.put(p, AUTOPICKER_PLAYERS.containsKey(p)?!AUTOPICKER_PLAYERS.get(p):!AutoPicker.apc.isEnabled());
    }
    public static HashMap<Player,Boolean> getAutoPickerPlayers() {
        return AUTOPICKER_PLAYERS;
    }
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface AutoPickerCheck {
        String[] acceptedMaterials() default {};
        String acceptedBlockMaterial() default "AIR";
        boolean checkWithSilkTouch();
    }
}
