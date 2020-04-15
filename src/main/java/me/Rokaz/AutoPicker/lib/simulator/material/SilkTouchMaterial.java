package me.Rokaz.AutoPicker.lib.simulator.material;

import me.Rokaz.AutoPicker.lib.legacy.SinceVersion;
import me.Rokaz.AutoPicker.lib.legacy.Version;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public enum SilkTouchMaterial {
    BEEHIVE(new SinceVersion(Version.MC1_15_R1,"BEEHIVE.0")),
    BEE_NEST(new SinceVersion(Version.MC1_15_R1,"BEE_NEST.0")),
    BLUE_ICE(new SinceVersion(Version.MC1_13_R1,"BLUE_ICE.0")),
    BOOKSHELF(new SinceVersion(Version.MC1_8_R3,"BOOKSHELF.0")),
    CAMPFIRE(new SinceVersion(Version.MC1_15_R1,"CAMPFIRE.0")),
    CLAY(new SinceVersion(Version.MC1_8_R3,"CLAY.0")),
    COAL_ORE(new SinceVersion(Version.MC1_8_R3,"COAL_ORE.0")),
    BRAIN_CORAL_BLOCK(new SinceVersion(Version.MC1_13_R1,"BRAIN_CORAL_BLOCK.0")),
    BUBBLE_CORAL_BLOCK(new SinceVersion(Version.MC1_13_R1,"BUBBLE_CORAL_BLOCK.0")),
    FIRE_CORAL_BLOCK(new SinceVersion(Version.MC1_13_R1,"FIRE_CORAL_BLOCK.0")),
    HORN_CORAL_BLOCK(new SinceVersion(Version.MC1_13_R1,"HORN_CORAL_BLOCK.0")),
    TUBE_CORAL_BLOCK(new SinceVersion(Version.MC1_13_R1,"TUBE_CORAL_BLOCK.0")),
    BRAIN_CORAL(new SinceVersion(Version.MC1_13_R1,"BRAIN_CORAL.0")),
    BUBBLE_CORAL(new SinceVersion(Version.MC1_13_R1,"BUBBLE_CORAL.0")),
    FIRE_CORAL(new SinceVersion(Version.MC1_13_R1,"FIRE_CORAL.0")),
    HORN_CORAL(new SinceVersion(Version.MC1_13_R1,"HORN_CORAL.0")),
    TUBE_CORAL(new SinceVersion(Version.MC1_13_R1,"TUBE_CORAL.0")),
    BRAIN_CORAL_FAN(new SinceVersion(Version.MC1_13_R1,"BRAIN_CORAL_FAN.0")),
    BUBBLE_CORAL_FAN(new SinceVersion(Version.MC1_13_R1,"BUBBLE_CORAL_FAN.0")),
    FIRE_CORAL_FAN(new SinceVersion(Version.MC1_13_R1,"FIRE_CORAL_FAN.0")),
    HORN_CORAL_FAN(new SinceVersion(Version.MC1_13_R1,"HORN_CORAL_FAN.0")),
    TUBE_CORAL_FAN(new SinceVersion(Version.MC1_13_R1,"TUBE_CORAL_FAN.0")),
    DIAMOND_ORE(new SinceVersion(Version.MC1_8_R3,"DIAMOND_ORE.0")),
    EMERALD_ORE(new SinceVersion(Version.MC1_8_R3,"EMERALD_ORE.0")),
    ENDER_CHEST(new SinceVersion(Version.MC1_8_R3,"ENDER_CHEST.0")),
    GLASS(new SinceVersion(Version.MC1_8_R3,"GLASS.0")),
    GLASS_PANE(new SinceVersion(Version.MC1_8_R3,"THIN_GLASS.0"),new SinceVersion(Version.MC1_13_R1,"GLASS_PANE.0")),
    STAINED_GLASS_PANE(new SinceVersion(Version.MC1_8_R3,"STAINED_GLASS_PANE.0"),new SinceVersion(Version.MC1_13_R1,"LEGACY_STAINED_GLASS_PANE.0")),
    STAINED_GLASS(new SinceVersion(Version.MC1_8_R3,"STAINED_GLASS.0"),new SinceVersion(Version.MC1_13_R1,"LEGACY_STAINED_GLASS.0")),
    GLOWSTONE(new SinceVersion(Version.MC1_8_R3,"GLOWSTONE.0")),
    GRASS(new SinceVersion(Version.MC1_8_R3,"GRASS.0"),new SinceVersion(Version.MC1_13_R1,"GRASS_BLOCK.0")),
    GRAVEL(new SinceVersion(Version.MC1_8_R3,"GRAVEL.0")),
    GRASS_PATH(new SinceVersion(Version.MC1_9_R1,"GRASS.1"),new SinceVersion(Version.MC1_13_R1,"GRASS_PATH.0")),
    PODZOL(new SinceVersion(Version.MC1_8_R3,"DIRT.2"),new SinceVersion(Version.MC1_13_R1,"PODZOL.0")),
    ICE(new SinceVersion(Version.MC1_8_R3,"ICE.0")),
    LAPIS_ORE(new SinceVersion(Version.MC1_8_R3,"LAPIS_ORE.0")),
    LEAVES(new SinceVersion(Version.MC1_8_R3,"LEAVES.0"),new SinceVersion(Version.MC1_13_R1,"LEGACY_LEAVES.0")),
    LEAVES_2(new SinceVersion(Version.MC1_8_R3,"LEAVES_2.0"),new SinceVersion(Version.MC1_13_R1,"LEGACY_LEAVES_2.0")),
    MELON(new SinceVersion(Version.MC1_8_R3,"MELON.0")),
    MYCEL(new SinceVersion(Version.MC1_8_R3,"MYCEL.0"),new SinceVersion(Version.MC1_13_R1,"MYCELIUM.0")),
    QUARTZ_ORE(new SinceVersion(Version.MC1_8_R3,"QUARTZ_ORE.0"),new SinceVersion(Version.MC1_13_R1,"NETHER_QUARTZ_ORE.0")),
    BROWN_MUSHROOM(new SinceVersion(Version.MC1_8_R3,"HUGE_MUSHROOM_1.0"),new SinceVersion(Version.MC1_13_R1,"BROWN_MUSHROOM_BLOCK.0")),
    RED_MUSHROOM(new SinceVersion(Version.MC1_8_R3,"HUGE_MUSHROOM_2.0"),new SinceVersion(Version.MC1_13_R1,"RED_MUSHROOM_BLOCK.0")),
    PACKED_ICE(new SinceVersion(Version.MC1_8_R3,"PACKED_ICE.0"),new SinceVersion(Version.MC1_13_R1,"LEGACY_PACKED_ICE.0")),
    REDSTONE_ORE(new SinceVersion(Version.MC1_8_R3,"REDSTONE_ORE.0")),
    SEA_LANTERN(new SinceVersion(Version.MC1_8_R3,"SEA_LANTERN.0")),
    SNOW(new SinceVersion(Version.MC1_8_R3,"SNOW.0")),
    SNOW_BLOCK(new SinceVersion(Version.MC1_8_R3,"SNOW_BLOCK.0")),
    STONE(new SinceVersion(Version.MC1_8_R3,"STONE.0")),
    TURTLE_EGG(new SinceVersion(Version.MC1_13_R1,"TURTLE_EGG.0"));
    private List<SinceVersion> sinces;
    private SilkTouchMaterial(SinceVersion... sinces) {
        this.sinces = Arrays.stream(sinces).filter(since -> since.getVersion().getVersionId() <= Version.getVersion().getVersionId()).collect(Collectors.toList());
    }
    public List<SinceVersion> getSinces() {
        return Collections.unmodifiableList(this.sinces);
    }
    public static boolean isSupported(Block b) {
        return Arrays.asList(SilkTouchMaterial.values()).stream().anyMatch(material -> {
            SinceVersion s = null;
            for (SinceVersion since: material.getSinces()) {
                if (s == null || s.getVersion().getVersionId() < since.getVersion().getVersionId()) {
                    s = since;
                }
            }
            if (s == null) return false;
            return b.getType().name().equals(s.getOutput().split("\\.")[0]) && Integer.parseInt(s.getOutput().split("\\.")[1]) == b.getState().getData().getData();
        });
    }
}
