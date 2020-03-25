package me.Rokaz.AutoPicker.core.simulators;

import me.Rokaz.AutoPicker.lib.legacy.Classes;
import me.Rokaz.AutoPicker.lib.simulator.ISimulator;
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
        List<ItemStack> items = new ArrayList<>(drop.getDrops(p.getInventory().getItemInMainHand()));
        Random r = new Random();
        return items.stream().map(item -> {
            try {
                Object block = Classes.getCBukkitClass("util.CraftMagicNumbers").getMethod("getBlock", Block.class).invoke(Classes.getCBukkitClass("util.CraftMagicNumbers"), drop);
                item.setAmount((int)block.getClass().getMethod("getDropCount",Integer.TYPE,Random.class).invoke(block,level,r));
            } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                e.printStackTrace();
            }
            return item;
        }).collect(Collectors.toList());
    }
}
