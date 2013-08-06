/*
 * Code Lyoko Mod for Minecraft v@VERSION
 *
 * Copyright 2013 Cortex Modders, Matthew Warren, Jacob Rhoda, and
 * other contributors.
 * Released under the MIT license
 * http://opensource.org/licenses/MIT
 * 
 */

package matt.lyoko.lib;

import java.util.Iterator;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.packet.Packet16BlockItemSwitch;
import net.minecraft.network.packet.Packet41EntityEffect;
import net.minecraft.network.packet.Packet4UpdateTime;
import net.minecraft.network.packet.Packet70GameEvent;
import net.minecraft.network.packet.Packet9Respawn;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldServer;

public class DimensionIds {

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
}