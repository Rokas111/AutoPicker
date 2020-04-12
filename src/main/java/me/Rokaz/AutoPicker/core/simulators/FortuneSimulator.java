package me.Rokaz.AutoPicker.core.simulators;

import me.Rokaz.AutoPicker.lib.legacy.Classes;
import me.Rokaz.AutoPicker.lib.simulator.ISimulator;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class FortuneSimulator implements ISimulator {
    public List<ItemStack> simulate(Player p, Block drop, int level) {
        List<ItemStack> items = new ArrayList<>(drop.getDrops(p.getItemInHand()));
        Random r = new Random();
        return items.stream().map(item -> {
            int bonus = new Random().nextInt(level + 2) - 1;
            if (bonus < 0) {
                bonus = 0;
            }
            item.setAmount(bonus == 0?item.getAmount():item.getAmount() * bonus);
            return item;
        }).collect(Collectors.toList());
    }
}
