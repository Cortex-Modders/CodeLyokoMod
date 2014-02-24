/*
 * Code Lyoko Mod for Minecraft ${version}
 * Copyright 2014 Cortex Modders, Matthew Warren, Jacob Rhoda, and other contributors.
 * Released under the MIT license http://opensource.org/licenses/MIT
 */

package net.cortexmodders.lyoko.handler;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

import net.cortexmodders.lyoko.CodeLyoko;
import net.cortexmodders.lyoko.lib.PlayerInformation;
import net.cortexmodders.lyoko.tileentity.TileEntityScanner;
import net.cortexmodders.lyoko.world.LyokoTeleporter;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.PlayerTickEvent;

public class ServerTickHandler
{
    
    @SubscribeEvent
    private void onPlayerTick(PlayerTickEvent event)
    {
        EntityPlayer player = event.player;
        
        if(CodeLyoko.enableAdminPowers)
        {
            if(player.getGameProfile().getName().equals(CodeLyoko.getDevelopers()[0]))
            {
                player.capabilities.allowFlying = true;
                player.fallDistance = 0;
                player.sendPlayerAbilities();
            }
            else if(player.getGameProfile().getName().equals(CodeLyoko.getDevelopers()[1]))
            {
                player.addPotionEffect(new PotionEffect(Potion.jump.getId(), 20, 3));
                player.fallDistance = 0;
            }
            else if(player.getGameProfile().getName().equals(CodeLyoko.getDevelopers()[2]))
            {
                player.addPotionEffect(new PotionEffect(Potion.jump.getId(), 20, 3));
                player.fallDistance = 0;
            }
            else if(player.getGameProfile().getName().equals(CodeLyoko.getDevelopers()[3]))
            {
                if(player.isSprinting())
                {
                    player.addPotionEffect(new PotionEffect(Potion.moveSpeed.getId(), 20, 2));
                }
                player.fallDistance = 0;
            }
        }
        
        PlayerInformation pi = PlayerInformation.forPlayer(player);
        
        if(pi.getCoolDown() > 0)
            pi.decreaseCoolDown(1);
        
        if(pi.getLifePoints() <= 0 && CodeLyoko.entityInLyoko(player))
        {
            if(player instanceof EntityPlayerMP)
            {
                LyokoTeleporter.transferPlayerToDimension((EntityPlayerMP) player, pi.scannerDim);
                ((EntityPlayerMP) player).setPositionAndRotation(pi.getScannerPosX() + 0.5D, pi.getScannerPosY(), pi.getScannerPosZ() + 0.5D, pi.scannerYaw, 0.0F);
            }
            
            TileEntityScanner tile = (TileEntityScanner) player.worldObj.getTileEntity(pi.getScannerPosX(), pi.getScannerPosY() - 1, pi.getScannerPosZ());
            if(tile != null)
                tile.toggleAllDoors();
            
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            DataOutputStream outputStream = new DataOutputStream(bos);
            try
            {
                outputStream.writeDouble(pi.getScannerPosX() + 0.5D);
                outputStream.writeDouble(pi.getScannerPosY());
                outputStream.writeDouble(pi.getScannerPosZ() + 0.5D);
                outputStream.writeFloat(pi.scannerYaw);
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
            
            //TODO: update to new packet system
            //            Packet250CustomPayload packet = PacketDispatcher.getPacket("Devirt", bos.toByteArray());
            //            PacketDispatcher.sendPacketToServer(packet);
            //            PacketDispatcher.sendPacketToPlayer(packet, (Player) player);
            
            pi.setLifePoints(100);
            pi.setDirty();
        }
    }
}
