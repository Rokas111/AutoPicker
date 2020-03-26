package me.Rokaz.AutoPicker.lib.simulator.material;

import me.Rokaz.AutoPicker.lib.legacy.SinceVersion;
import me.Rokaz.AutoPicker.lib.legacy.Version;
import org.bukkit.Material;
import org.bukkit.block.Block;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum SilkTouchMaterial {
    BEEHIVE(new SinceVersion(Version.MC1_15_R1,"BEEHIVE")),
    BEE_NEST(new SinceVersion(Version.MC1_15_R1,"BEE_NEST")),
    BLUE_ICE(new SinceVersion(Version.MC1_13_R1,"BLUE_ICE")),
    BOOKSHELF(new SinceVersion(Version.MC1_8_R3,"BOOKSHELF")),
    CAMPFIRE(new SinceVersion(Version.MC1_15_R1,"CAMPFIRE")),
    CLAY(new SinceVersion(Version.MC1_8_R3,"CLAY")),
    COAL_ORE(new SinceVersion(Version.MC1_8_R3,"COAL_ORE")),
    BRAIN_CORAL_BLOCK(new SinceVersion(Version.MC1_13_R1,"BRAIN_CORAL_BLOCK")),
    BUBBLE_CORAL_BLOCK(new SinceVersion(Version.MC1_13_R1,"BUBBLE_CORAL_BLOCK")),
    FIRE_CORAL_BLOCK(new SinceVersion(Version.MC1_13_R1,"FIRE_CORAL_BLOCK")),
    HORN_CORAL_BLOCK(new SinceVersion(Version.MC1_13_R1,"HORN_CORAL_BLOCK")),
    TUBE_CORAL_BLOCK(new SinceVersion(Version.MC1_13_R1,"TUBE_CORAL_BLOCK")),
    BRAIN_CORAL(new SinceVersion(Version.MC1_13_R1,"BRAIN_CORAL")),
    BUBBLE_CORAL(new SinceVersion(Version.MC1_13_R1,"BUBBLE_CORAL")),
    FIRE_CORAL(new SinceVersion(Version.MC1_13_R1,"FIRE_CORAL")),
    HORN_CORAL(new SinceVersion(Version.MC1_13_R1,"HORN_CORAL")),
    TUBE_CORAL(new SinceVersion(Version.MC1_13_R1,"TUBE_CORAL")),
    BRAIN_CORAL_FAN(new SinceVersion(Version.MC1_13_R1,"BRAIN_CORAL_FAN")),
    BUBBLE_CORAL_FAN(new SinceVersion(Version.MC1_13_R1,"BUBBLE_CORAL_FAN")),
    FIRE_CORAL_FAN(new SinceVersion(Version.MC1_13_R1,"FIRE_CORAL_FAN")),
    HORN_CORAL_FAN(new SinceVersion(Version.MC1_13_R1,"HORN_CORAL_FAN")),
    TUBE_CORAL_FAN(new SinceVersion(Version.MC1_13_R1,"TUBE_CORAL_FAN")),
    DIAMOND_ORE(new SinceVersion(Version.MC1_8_R3,"DIAMOND_ORE")),
    EMERALD_ORE(new SinceVersion(Version.MC1_8_R3,"EMERALD_ORE")),
    ENDER_CHEST(new SinceVersion(Version.MC1_8_R3,"ENDER_CHEST")),
    GLASS(new SinceVersion(Version.MC1_8_R3,"GLASS")),
    GLASS_PANE(new SinceVersion(Version.MC1_8_R3,"THIN_GLASS"),new SinceVersion(Version.MC1_13_R1,"GLASS_PANE")),
    STAINED_GLASS_PANE(new SinceVersion(Version.MC1_8_R3,"STAINED_GLASS_PANE")),
    STAINED_GLASS(new SinceVersion(Version.MC1_8_R3,"STAINED_GLASS")),
    GLOWSTONE(new SinceVersion(Version.MC1_8_R3,"GLOWSTONE")),
    GRASS(new SinceVersion(Version.MC1_8_R3,"GRASS"),new SinceVersion(Version.MC1_13_R1,"GRASS_BLOCK")),
    GRAVEL(new SinceVersion(Version.MC1_8_R3,"GRAVEL")),
    ICE(new SinceVersion(Version.MC1_8_R3,"ICE")),
    LAPIS_ORE(new SinceVersion(Version.MC1_8_R3,"LAPIS_ORE")),
    LEAVES(new SinceVersion(Version.MC1_8_R3,"LEAVES")),
    LEAVES_2(new SinceVersion(Version.MC1_8_R3,"LEAVES_2")),
    MELON(new SinceVersion(Version.MC1_8_R3,"MELON")),
    MYCEL(new SinceVersion(Version.MC1_8_R3,"MYCEL"),new SinceVersion(Version.MC1_13_R1,"MYCELIUM")),
    QUARTZ_ORE(new SinceVersion(Version.MC1_8_R3,"QUARTZ_ORE"),new SinceVersion(Version.MC1_13_R1,"NETHER_QUARTZ_ORE")),
    BROWN_MUSHROOM(new SinceVersion(Version.MC1_8_R3,"HUGE_MUSHROOM_1"),new SinceVersion(Version.MC1_13_R1,"BROWN_MUSHROOM_BLOCK")),
    RED_MUSHROOM(new SinceVersion(Version.MC1_8_R3,"HUGE_MUSHROOM_2"),new SinceVersion(Version.MC1_13_R1,"RED_MUSHROOM_BLOCK")),
    PACKED_ICE(new SinceVersion(Version.MC1_8_R3,"PACKED_ICE")),
    REDSTONE_ORE(new SinceVersion(Version.MC1_8_R3,"REDSTONE_ORE")),
    SEA_LANTERN(new SinceVersion(Version.MC1_8_R3,"SEA_LANTERN")),
    SNOW(new SinceVersion(Version.MC1_8_R3,"SNOW")),
    SNOW_BLOCK(new SinceVersion(Version.MC1_8_R3,"SNOW_BLOCK")),
    STONE(new SinceVersion(Version.MC1_8_R3,"STONE")),
    TURTLE_EGG(new SinceVersion(Version.MC1_13_R1,"TURTLE_EGG"));
    private List<SinceVersion> sinces;
    private SilkTouchMaterial(SinceVersion... sinces) {
        this.sinces = Arrays.stream(sinces).filter(since -> since.getVersion().getVersionId() <= Version.getVersion().getVersionId()).collect(Collectors.toList());
    }
    public Material getMaterial() {
        if (sinces.isEmpty()) return null;
        SinceVersion s = null;
        for (SinceVersion since: sinces) {
            if (s == null || s.getVersion().getVersionId() < since.getVersion().getVersionId()) {
                s = since;
            }
        }
        return Material.getMaterial(s.getOutput());
    }
    public static boolean isSupported(Block b) {
        return Arrays.asList(SilkTouchMaterial.values()).stream().anyMatch(material -> material.getMaterial() != null && material.getMaterial().equals(b.getType()));
    }
}
