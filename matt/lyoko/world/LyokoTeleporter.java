/*
 * Code Lyoko Mod for Minecraft v@VERSION
 *
 * Copyright 2013 Cortex Modders, Matthew Warren, Jacob Rhoda, and
 * other contributors.
 * Released under the MIT license
 * http://opensource.org/licenses/MIT
 * 
 */

package matt.lyoko.world;

import java.util.Iterator;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.packet.Packet16BlockItemSwitch;
import net.minecraft.network.packet.Packet41EntityEffect;
import net.minecraft.network.packet.Packet4UpdateTime;
import net.minecraft.network.packet.Packet70GameEvent;
import net.minecraft.network.packet.Packet9Respawn;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.MathHelper;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldServer;
import cpw.mods.fml.common.registry.GameRegistry;

public class LyokoTeleporter
{
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
    
	private static void transferEntityToWorld(EntityPlayer player, int currentDim, WorldServer currentWorldServer, WorldServer newWorldServer)//, Teleporter teleporter)
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
    
    private static void updateTimeAndWeatherForPlayer(EntityPlayerMP par1EntityPlayerMP, WorldServer par2WorldServer)
    {
        par1EntityPlayerMP.playerNetServerHandler.sendPacketToPlayer(new Packet4UpdateTime(par2WorldServer.getTotalWorldTime(), par2WorldServer.getWorldTime(), par2WorldServer.getGameRules().getGameRuleBooleanValue("doDaylightCycle")));
        
        if (par2WorldServer.isRaining())
        {
            par1EntityPlayerMP.playerNetServerHandler.sendPacketToPlayer(new Packet70GameEvent(1, 0));
        }
    }
    
    private static void syncPlayerInventory(EntityPlayerMP par1EntityPlayerMP)
    {
        par1EntityPlayerMP.sendContainerToPlayer(par1EntityPlayerMP.inventoryContainer);
        par1EntityPlayerMP.setPlayerHealthUpdated();
        par1EntityPlayerMP.playerNetServerHandler.sendPacketToPlayer(new Packet16BlockItemSwitch(par1EntityPlayerMP.inventory.currentItem));
    }
}