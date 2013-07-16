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
    
    public static void transferEntityToWorld(EntityPlayer player, int currentDim, WorldServer currentWorldServer, WorldServer newWorldServer)//, Teleporter teleporter)
    {
        WorldProvider pOld = currentWorldServer.provider;
        WorldProvider pNew = newWorldServer.provider;
        double moveFactor = pOld.getMovementFactor() / pNew.getMovementFactor();
        double d0 = player.posX * moveFactor;
        double d1 = player.posZ * moveFactor;
        double d3 = player.posX;
        double d4 = player.posY;
        double d5 = player.posZ;
        float f = player.rotationYaw;
        currentWorldServer.theProfiler.startSection("moving");
        
        currentWorldServer.theProfiler.endSection();
        
        currentWorldServer.theProfiler.startSection("placing");
        d0 = (double)MathHelper.clamp_int((int)d0, -29999872, 29999872);
        d1 = (double)MathHelper.clamp_int((int)d1, -29999872, 29999872);
        
        if (player.isEntityAlive())
        {
        	newWorldServer.spawnEntityInWorld(player);
        	player.setLocationAndAngles(d0, player.posY, d1, player.rotationYaw, player.rotationPitch);
        	newWorldServer.updateEntityWithOptionalForce(player, false);
        }
        
        currentWorldServer.theProfiler.endSection();
        
        player.setWorld(newWorldServer);
    }
    
    public static void transferPlayerToDimension(EntityPlayerMP par1EntityPlayerMP, int par2)
    {
        MinecraftServer minecraftServer = MinecraftServer.getServer();
        int j = par1EntityPlayerMP.dimension;
        WorldServer worldserver = minecraftServer.worldServerForDimension(par1EntityPlayerMP.dimension);
        par1EntityPlayerMP.dimension = par2;
        WorldServer worldserver1 = minecraftServer.worldServerForDimension(par1EntityPlayerMP.dimension);
        par1EntityPlayerMP.playerNetServerHandler.sendPacketToPlayer(new Packet9Respawn(par1EntityPlayerMP.dimension, (byte)par1EntityPlayerMP.worldObj.difficultySetting, worldserver1.getWorldInfo().getTerrainType(), worldserver1.getHeight(), par1EntityPlayerMP.theItemInWorldManager.getGameType()));
        worldserver.removePlayerEntityDangerously(par1EntityPlayerMP);
        par1EntityPlayerMP.isDead = false;
        transferEntityToWorld(par1EntityPlayerMP, j, worldserver, worldserver1);
        
        if (worldserver != null)
        {
        	worldserver.getPlayerManager().removePlayer(par1EntityPlayerMP);
        }
        worldserver1.getPlayerManager().addPlayer(par1EntityPlayerMP);
        worldserver1.theChunkProviderServer.loadChunk((int)par1EntityPlayerMP.posX >> 4, (int)par1EntityPlayerMP.posZ >> 4);
        
        par1EntityPlayerMP.playerNetServerHandler.setPlayerLocation(par1EntityPlayerMP.posX, par1EntityPlayerMP.posY, par1EntityPlayerMP.posZ, par1EntityPlayerMP.rotationYaw, par1EntityPlayerMP.rotationPitch);
        par1EntityPlayerMP.theItemInWorldManager.setWorld(worldserver1);
        updateTimeAndWeatherForPlayer(par1EntityPlayerMP, worldserver1);
        syncPlayerInventory(par1EntityPlayerMP);
        Iterator iterator = par1EntityPlayerMP.getActivePotionEffects().iterator();

        while (iterator.hasNext())
        {
            PotionEffect potioneffect = (PotionEffect)iterator.next();
            par1EntityPlayerMP.playerNetServerHandler.sendPacketToPlayer(new Packet41EntityEffect(par1EntityPlayerMP.entityId, potioneffect));
        }

        GameRegistry.onPlayerChangedDimension(par1EntityPlayerMP);
    }
    
    public static void updateTimeAndWeatherForPlayer(EntityPlayerMP par1EntityPlayerMP, WorldServer par2WorldServer)
    {
        par1EntityPlayerMP.playerNetServerHandler.sendPacketToPlayer(new Packet4UpdateTime(par2WorldServer.getTotalWorldTime(), par2WorldServer.getWorldTime(), par2WorldServer.getGameRules().getGameRuleBooleanValue("doDaylightCycle")));

        if (par2WorldServer.isRaining())
        {
            par1EntityPlayerMP.playerNetServerHandler.sendPacketToPlayer(new Packet70GameEvent(1, 0));
        }
    }
    
    public static void syncPlayerInventory(EntityPlayerMP par1EntityPlayerMP)
    {
        par1EntityPlayerMP.sendContainerToPlayer(par1EntityPlayerMP.inventoryContainer);
        par1EntityPlayerMP.setPlayerHealthUpdated();
        par1EntityPlayerMP.playerNetServerHandler.sendPacketToPlayer(new Packet16BlockItemSwitch(par1EntityPlayerMP.inventory.currentItem));
    }
}