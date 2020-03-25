package me.Rokaz.AutoPicker.lib.simulator;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public interface ISimulator {
    List<ItemStack> simulate(Player p, Block block, int level);
}
