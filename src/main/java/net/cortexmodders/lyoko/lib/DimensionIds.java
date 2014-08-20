/*
 * Code Lyoko Mod for Minecraft ${version}
 * Copyright 2014 Cortex Modders, Matthew Warren, Jacob Rhoda, and other contributors.
 * Released under the MIT license http://opensource.org/licenses/MIT
 */

package net.cortexmodders.lyoko.lib;

import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.config.Configuration;
import net.cortexmodders.lyoko.lib.ModProperties.ConfigCategories;

public class DimensionIds
{
//    ICE,
//    MOUNTAIN,
//    FOREST,
//    DESERT,
//    CARTHAGE,
//    DIGITALSEA,
//    CORTEX;
    /* Default IDs. */
    public static int ICE_DEFAULT = 3;
    public static int MOUNTAIN_DEFAULT = 4;
    public static int FOREST_DEFAULT = 5;
    public static int DESERT_DEFAULT = 6;
    // seven is skipped so the mod will be
    // inherently compatible with Twilight Forest
    public static int CARTHAGE_DEFAULT = 8;
    public static int DIGITALSEA_DEFAULT = 9;
    public static int CORTEX_DEFAULT = 10;
    
    /* IDs loaded from config. */
    public static int ICE;
    public static int MOUNTAIN;
    public static int FOREST;
    public static int DESERT;
    public static int CARTHAGE;
    public static int DIGITALSEA;
    public static int CORTEX;

    public static void registerIds(Configuration config) {
        ICE = config.get(ConfigCategories.DIMENSIONS.name(), "polarSectorID", DimensionIds.ICE_DEFAULT).getInt();
        MOUNTAIN = config.get(ConfigCategories.DIMENSIONS.name(), "mountainSectorID", DimensionIds.MOUNTAIN_DEFAULT).getInt();
        FOREST = config.get(ConfigCategories.DIMENSIONS.name(), "forestSectorID", DimensionIds.FOREST_DEFAULT).getInt();
        DESERT = config.get(ConfigCategories.DIMENSIONS.name(), "desertSectorID", DimensionIds.DESERT_DEFAULT).getInt();
        CARTHAGE = config.get(ConfigCategories.DIMENSIONS.name(), "carthageSectorID", DimensionIds.CARTHAGE_DEFAULT).getInt();
        DIGITALSEA = config.get(ConfigCategories.DIMENSIONS.name(), "digitalSeaSectorID", DimensionIds.DIGITALSEA_DEFAULT).getInt();
        CORTEX = config.get(ConfigCategories.DIMENSIONS.name(), "cortexSectorID", DimensionIds.CORTEX_DEFAULT).getInt();
    }

    public static String dimensionName(int id) {
        String name = null;
        if (id == ICE) {
            name = "ice";
        } else if (id == MOUNTAIN) {
            name = "mountain";
        } else if (id == FOREST) {
            name = "forest";
        } else if (id == DESERT) {
            name = "desert";
        } else if (id == CARTHAGE) {
            name = "carthage";
        } else if (id == DIGITALSEA) {
            name = "digitalsea";
        } else if (id == CORTEX) {
            name = "cortex";
        }

        return name;
    }

    public static int dimensionIdForName(String name) {
        int id = -2;
        if (name.equalsIgnoreCase("ice")) {
            id = ICE;
        } else if (name.equalsIgnoreCase("mountain")) {
            id = MOUNTAIN;
        } else if (name.equalsIgnoreCase( "forest")) {
            id = FOREST;
        } else if (name.equalsIgnoreCase("desert")) {
            id = DESERT;
        } else if (name.equalsIgnoreCase("carthage")) {
            id = CARTHAGE;
        } else if (name.equalsIgnoreCase("digitalsea")) {
            id = DIGITALSEA;
        } else if (name.equalsIgnoreCase("cortex")) {
            id = CORTEX;
        }
        return id;
    }
}
