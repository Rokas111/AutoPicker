package me.Rokaz.AutoPicker.core;

import me.Rokaz.AutoPicker.core.config.unit.MessageConfig;
import me.Rokaz.AutoPicker.core.simulators.FortuneSimulator;
import me.Rokaz.AutoPicker.core.simulators.SilkTouchSimulator;
import me.Rokaz.AutoPicker.core.utils.AnnotationProcessor;
import me.Rokaz.AutoPicker.lib.legacy.SinceVersion;
import me.Rokaz.AutoPicker.lib.legacy.Version;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.Crops;
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
    public BreakRegistry() {
        AutoPicker.pl.getServer().getPluginManager().registerEvents(this,AutoPicker.pl);
    }
    @EventHandler(priority = EventPriority.MONITOR)
    public void onBreak(BlockBreakEvent e) {
        Player p = e.getPlayer();
        ItemStack item = p.getItemInHand();
        if (!e.isCancelled() && (AUTOPICKER_PLAYERS.containsKey(p)|| AutoPicker.pl.getAutoPickerConfig().isEnabled()) && (!AUTOPICKER_PLAYERS.containsKey(p) || AUTOPICKER_PLAYERS.get(p)) && p.getGameMode() == GameMode.SURVIVAL && AutoPicker.pl.getAutoPickerConfig().getDisabledWorld().parallelStream().noneMatch(world -> p.getWorld().getName().equals(world))) {
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
            if (full) {
                p.sendMessage(AutoPicker.pl.getMessageConfig().obtain(MessageConfig.INVENTORY_FULL_MESSAGE_KEY,p).getMessage());
                if (AutoPicker.pl.getAutoPickerConfig().getInventoryFullSound() != null) p.playSound(p.getLocation(),AutoPicker.pl.getAutoPickerConfig().getInventoryFullSound(),1.0F,1.0F);
            }
            e.getBlock().getDrops().clear();
            e.getBlock().getLocation().getBlock().setType(Material.AIR);
            p.giveExp(e.getExpToDrop());
            if (AutoPicker.pl.getAutoPickerConfig().getOnMineSound() != null) p.playSound(p.getLocation(),AutoPicker.pl.getAutoPickerConfig().getOnMineSound(),1.0F,1.0F);
            if (TOOLS_NAMES.stream().anyMatch(name -> item.getType().name().endsWith(name))) {
                item.setDurability((short)1);
                p.setItemInHand(item);
            }
        }
    }

    @AutoPickerCheck(acceptedMaterials = {"GOLD_ORE","IRON_ORE"},checkWithSilkTouch = false)
    public static List<ItemStack> checkSmelt(List<ItemStack> items, Block b, Player p) {
        if (AutoPicker.pl.getAutoPickerConfig().autoSmelt() && p.hasPermission(AUTO_SMELT_PERMISSION)) {
            items = items.stream().peek(item -> item.setType(Material.valueOf(item.getType().name().split("_")[0] + "_INGOT"))).collect(Collectors.toList());
        }
        return items;
    }
    @AutoPickerCheck(checkWithSilkTouch = true)
    public static List<ItemStack> checkAdditions(List<ItemStack> items,Block b,Player p) {
        if (!AutoPicker.pl.getAutoPickerConfig().getItemAdditionName().isEmpty()) items = items.stream().peek(item -> {
            ItemMeta m = item.getItemMeta();
            m.setDisplayName(AutoPicker.pl.getAutoPickerConfig().getItemAdditionName());
            item.setItemMeta(m);
        }).collect(Collectors.toList());
        if (!AutoPicker.pl.getAutoPickerConfig().getItemAdditionLore().isEmpty()) items = items.stream().peek(item -> {
            ItemMeta m = item.getItemMeta();
            m.setLore(AutoPicker.pl.getAutoPickerConfig().getItemAdditionLore());
            item.setItemMeta(m);
        }).collect(Collectors.toList());
        return items;
    }
    @AutoPickerCheck(checkWithSilkTouch = true,acceptedBlockMaterials = {"POTATO","CARROT"},versions = {Version.MC1_8_R3,Version.MC1_9_R1,Version.MC1_9_R2,Version.MC1_10_R1,Version.MC1_11_R1,Version.MC1_12_R1})
    public static List<ItemStack> checkCropDrops(List<ItemStack> items,Block b,Player p) {
        items.stream().filter(item -> item.getType().name().replace("LEGACY_","").equals("CARROT") || item.getType().name().replace("LEGACY_","").equals("POTATO")).peek(item -> item.setAmount(2 + new Random().nextInt(2))).collect(Collectors.toList());
        return items;
    }
    @AutoPickerCheck(checkWithSilkTouch = true,acceptedBlockMaterials = {"CROPS"},versions = {Version.MC1_8_R3,Version.MC1_9_R1,Version.MC1_9_R2,Version.MC1_10_R1,Version.MC1_11_R1,Version.MC1_12_R1})
    public static List<ItemStack> checkWheatDrops(List<ItemStack> items,Block b,Player p) {
        items.add(new ItemStack(Material.valueOf("SEEDS"),1));
        return items;
    }
    private static List<ItemStack> checkItems(List<ItemStack> items,Block b,Player p,boolean checkWithSilkTouch) {
        for (Method m: AnnotationProcessor.getAnnotatedMethods(BreakRegistry.class,AutoPickerCheck.class)) {
            try {
                AutoPickerCheck apc = m.getAnnotation(AutoPickerCheck.class);
                if (apc.checkWithSilkTouch() != checkWithSilkTouch) continue;
                if (apc.versions().length != 0 && Arrays.asList(apc.versions()).stream().noneMatch(version -> version.getVersionId() == Version.getVersion().getVersionId())) continue;
                if (!Arrays.asList(apc.acceptedBlockMaterials()).isEmpty() && Arrays.asList(apc.acceptedBlockMaterials()).stream().noneMatch(material -> material.equals(b.getType().name().replace("LEGACY_","")))) continue;
                if (!Arrays.asList(apc.acceptedMaterials()).isEmpty() &&items.parallelStream().noneMatch(item -> Arrays.asList(apc.acceptedMaterials()).stream().anyMatch(mat -> item.getType().name().replace("LEGACY_","").equals(mat)))) continue;
                items = (List<ItemStack>) m.invoke(BreakRegistry.class,items,b,p);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return items;
    }
    public static void setAutoPickerPlayer(Player p) {
        AUTOPICKER_PLAYERS.put(p, AUTOPICKER_PLAYERS.containsKey(p)?!AUTOPICKER_PLAYERS.get(p):!AutoPicker.pl.getAutoPickerConfig().isEnabled());
    }
    public static HashMap<Player,Boolean> getAutoPickerPlayers() {
        return AUTOPICKER_PLAYERS;
    }
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface AutoPickerCheck {
        String[] acceptedMaterials() default {};
        String[] acceptedBlockMaterials() default {};
        boolean checkWithSilkTouch();
        Version[] versions() default {};
    }
}
