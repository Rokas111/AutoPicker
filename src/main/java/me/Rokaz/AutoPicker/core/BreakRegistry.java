package me.Rokaz.AutoPicker.core;

import me.Rokaz.AutoPicker.core.config.unit.MessageConfig;
import me.Rokaz.AutoPicker.core.simulators.FortuneSimulator;
import me.Rokaz.AutoPicker.core.simulators.SilkTouchSimulator;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class BreakRegistry implements Listener {
    private final static List<Material> SMELTABLES = Arrays.asList(Material.GOLD_ORE,Material.IRON_ORE);
    public BreakRegistry(Plugin pl) {
        pl.getServer().getPluginManager().registerEvents(this,pl);
    }
    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        Player p = e.getPlayer();
        ItemStack item = p.getItemInHand();
        if (!e.isCancelled() && AutoPicker.apc.isEnabled() && p.getGameMode() == GameMode.SURVIVAL) {
            List<ItemStack> items;
            if (item.hasItemMeta() && item.getItemMeta().hasEnchant(Enchantment.SILK_TOUCH) && !new SilkTouchSimulator().simulate(p,e.getBlock(),item.getItemMeta().getEnchantLevel(Enchantment.SILK_TOUCH)).isEmpty()) {
                items = new SilkTouchSimulator().simulate(p,e.getBlock(),item.getItemMeta().getEnchantLevel(Enchantment.SILK_TOUCH));
            } else  {
                items = new ArrayList<>(e.getBlock().getDrops(p.getItemInHand()));
                if (item.hasItemMeta() && item.getItemMeta().hasEnchant(Enchantment.LOOT_BONUS_BLOCKS)) items = new FortuneSimulator().simulate(p,e.getBlock(),item.getItemMeta().getEnchantLevel(Enchantment.LOOT_BONUS_BLOCKS));
                items = items.stream().map(BreakRegistry::attemptSmelt).collect(Collectors.toList());
            }
            boolean full = false;
            for (ItemStack i : items) {
                if (!p.getInventory().addItem(i).isEmpty()) full = true;
            }
            if (full) p.sendMessage(AutoPicker.mc.obtain(MessageConfig.INVENTORY_FULL_MESSAGE_KEY,p).getMessage());
            AutoPicker.brokenBlocks += 1;
            e.setCancelled(true);
            e.getBlock().getLocation().getBlock().setType(Material.AIR);
            p.updateInventory();
        }
    }
    public static ItemStack attemptSmelt(ItemStack item) {
        if (AutoPicker.apc.autoSmelt() && SMELTABLES.contains(item.getType())) item.setType(Material.valueOf(item.getType().name().split("_")[0] + "_INGOT"));
        return item;
    }
}
