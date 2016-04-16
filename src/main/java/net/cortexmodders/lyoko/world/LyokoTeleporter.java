/*
 * Code Lyoko Mod for Minecraft ${version}
 * Copyright 2014 Cortex Modders, Matthew Warren, Jacob Rhoda, and other contributors.
 * Released under the MIT license http://opensource.org/licenses/MIT
 */

package net.cortexmodders.lyoko.world;

import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.play.server.*;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.MathHelper;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldServer;

import java.util.Iterator;

public class LyokoTeleporter
{
    @SuppressWarnings("rawtypes")
    public static void transferPlayerToDimension(EntityPlayerMP playerMP, int newDim, double xPos, double yPos, double zPos, float rotationYaw, float rotationPitch)
    {
        MinecraftServer mcServer = MinecraftServer.getServer();

        int j = playerMP.dimension;
        WorldServer worldserver = mcServer.worldServerForDimension(playerMP.dimension);
        playerMP.dimension = newDim;

        WorldServer worldserver1 = mcServer.worldServerForDimension(playerMP.dimension);

        playerMP.playerNetServerHandler.sendPacket(new S07PacketRespawn(playerMP.dimension, playerMP.worldObj.difficultySetting, playerMP.worldObj.getWorldInfo().getTerrainType(), playerMP.theItemInWorldManager.getGameType()));

        worldserver.removePlayerEntityDangerously(playerMP);
        playerMP.isDead = false;

        transferEntityToWorld(playerMP, j, worldserver, worldserver1);
        func_72375_a(playerMP, worldserver);

        playerMP.playerNetServerHandler.setPlayerLocation(xPos, yPos, zPos, rotationYaw, rotationPitch);

        playerMP.theItemInWorldManager.setWorld(worldserver1);

        updateTimeAndWeatherForPlayer(playerMP, worldserver1);
        syncPlayerInventory(playerMP);

        Iterator iterator = playerMP.getActivePotionEffects().iterator();

        while (iterator.hasNext()) {
            PotionEffect potioneffect = (PotionEffect) iterator.next();
            playerMP.playerNetServerHandler.sendPacket(new S1DPacketEntityEffect(playerMP.getEntityId(), potioneffect));
        }

        FMLCommonHandler.instance().firePlayerChangedDimensionEvent(playerMP, j, newDim);
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

        if (player.isEntityAlive()) {
            newWorldServer.spawnEntityInWorld(player);
            player.setLocationAndAngles(d0, player.posY, d1, player.rotationYaw, player.rotationPitch);
            newWorldServer.updateEntityWithOptionalForce(player, false);
        }

        currentWorldServer.theProfiler.endSection();

        player.setWorld(newWorldServer);
    }

    private static void updateTimeAndWeatherForPlayer(EntityPlayerMP par1EntityPlayerMP, WorldServer par2WorldServer)
    {
        par1EntityPlayerMP.playerNetServerHandler.sendPacket(new S03PacketTimeUpdate(par2WorldServer.getTotalWorldTime(), par2WorldServer.getWorldTime(), par2WorldServer.getGameRules().getGameRuleBooleanValue("doDaylightCycle")));

        if (par2WorldServer.isRaining()) {
            par1EntityPlayerMP.playerNetServerHandler.sendPacket(new S2BPacketChangeGameState(1, 0.0F));
            par1EntityPlayerMP.playerNetServerHandler.sendPacket(new S2BPacketChangeGameState(7, par2WorldServer.getRainStrength(1.0F)));
            par1EntityPlayerMP.playerNetServerHandler.sendPacket(new S2BPacketChangeGameState(8, par2WorldServer.getWeightedThunderStrength(1.0F)));
        }
    }

    private static void syncPlayerInventory(EntityPlayerMP par1EntityPlayerMP)
    {
        par1EntityPlayerMP.sendContainerToPlayer(par1EntityPlayerMP.inventoryContainer);
        par1EntityPlayerMP.setPlayerHealthUpdated();
        par1EntityPlayerMP.playerNetServerHandler.sendPacket(new S09PacketHeldItemChange(par1EntityPlayerMP.inventory.currentItem));
    }

    public static void func_72375_a(EntityPlayerMP par1EntityPlayerMP, WorldServer par2WorldServer)
    {
        WorldServer worldserver1 = par1EntityPlayerMP.getServerForPlayer();

        if (par2WorldServer != null)
            par2WorldServer.getPlayerManager().removePlayer(par1EntityPlayerMP);

        worldserver1.getPlayerManager().addPlayer(par1EntityPlayerMP);
        worldserver1.theChunkProviderServer.loadChunk((int) par1EntityPlayerMP.posX >> 4, (int) par1EntityPlayerMP.posZ >> 4);
    }
}