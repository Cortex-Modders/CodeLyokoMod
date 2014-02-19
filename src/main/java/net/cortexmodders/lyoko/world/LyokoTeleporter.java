/*
 * Code Lyoko Mod for Minecraft v@VERSION
 * Copyright 2013 Cortex Modders, Matthew Warren, Jacob Rhoda, and
 * other contributors.
 * Released under the MIT license
 * http://opensource.org/licenses/MIT
 */

package net.cortexmodders.lyoko.world;

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
    @SuppressWarnings("rawtypes")
	public static void transferPlayerToDimension(EntityPlayerMP playerMP, int newDim)
    {
        MinecraftServer minecraftServer = MinecraftServer.getServer();
        int j = playerMP.dimension;
        WorldServer worldserver = minecraftServer.worldServerForDimension(playerMP.dimension);
        playerMP.dimension = newDim;
        WorldServer worldserver1 = minecraftServer.worldServerForDimension(playerMP.dimension);
        playerMP.playerNetServerHandler.sendPacketToPlayer(new Packet9Respawn(playerMP.dimension, playerMP.worldObj.difficultySetting, worldserver1.getWorldInfo().getTerrainType(), worldserver1.getHeight(), playerMP.theItemInWorldManager.getGameType()));
        //worldserver.removePlayerEntityDangerously(playerMP);
        playerMP.isDead = false;
        transferEntityToWorld(playerMP, j, worldserver, worldserver1);

        if(worldserver != null)
            worldserver.getPlayerManager().removePlayer(playerMP);
        worldserver1.getPlayerManager().addPlayer(playerMP);
        worldserver1.theChunkProviderServer.loadChunk((int) playerMP.posX >> 4, (int) playerMP.posZ >> 4);
        								// setPlayerLocation
        playerMP.playerNetServerHandler.setPlayerLocation(playerMP.posX, playerMP.posY, playerMP.posZ, playerMP.rotationYaw, playerMP.rotationPitch);
        playerMP.theItemInWorldManager.setWorld(worldserver1);
        updateTimeAndWeatherForPlayer(playerMP, worldserver1);
        syncPlayerInventory(playerMP);
        Iterator iterator = playerMP.getActivePotionEffects().iterator();

        while (iterator.hasNext())
        {
            PotionEffect potioneffect = (PotionEffect) iterator.next();
            playerMP.playerNetServerHandler.sendPacket(new Packet41EntityEffect(playerMP.entityId, potioneffect));
        }

        GameRegistry.onPlayerChangedDimension(playerMP);
    }

    private static void transferEntityToWorld(EntityPlayer player, int currentDim, WorldServer currentWorldServer, WorldServer newWorldServer)
    {
        WorldProvider pOld = currentWorldServer.provider;
        WorldProvider pNew = newWorldServer.provider;
        double moveFactor = pOld.getMovementFactor() / pNew.getMovementFactor();
        double d0 = player.posX * moveFactor;
        double d1 = player.posZ * moveFactor;
        currentWorldServer.theProfiler.startSection("moving");

        currentWorldServer.theProfiler.endSection();

        currentWorldServer.theProfiler.startSection("placing");
        d0 = MathHelper.clamp_int((int) d0, -29999872, 29999872);
        d1 = MathHelper.clamp_int((int) d1, -29999872, 29999872);

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
            par1EntityPlayerMP.playerNetServerHandler.sendPacketToPlayer(new Packet70GameEvent(1, 0));
    }

    private static void syncPlayerInventory(EntityPlayerMP par1EntityPlayerMP)
    {
        par1EntityPlayerMP.sendContainerToPlayer(par1EntityPlayerMP.inventoryContainer);
        par1EntityPlayerMP.setPlayerHealthUpdated();
        par1EntityPlayerMP.playerNetServerHandler.sendPacketToPlayer(new Packet16BlockItemSwitch(par1EntityPlayerMP.inventory.currentItem));
    }
}
