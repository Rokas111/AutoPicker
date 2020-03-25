package me.Rokaz.AutoPicker.core.simulators;

import lombok.NoArgsConstructor;
import me.Rokaz.AutoPicker.lib.simulator.ISimulator;
import me.Rokaz.AutoPicker.lib.simulator.material.SilkTouchMaterial;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class SilkTouchSimulator implements ISimulator {
    public List<ItemStack> simulate(Player p, Block drop, int level) {
        List<ItemStack> items = new ArrayList<>();
        if (SilkTouchMaterial.isSupported(drop))  items.add(new ItemStack(drop.getType(),1));
        return items;
    }
}
