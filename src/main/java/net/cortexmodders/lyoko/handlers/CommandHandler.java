/*
 * Code Lyoko Mod for Minecraft v@VERSION
 * Copyright 2013 Cortex Modders, Matthew Warren, Jacob Rhoda, and
 * other contributors.
 * Released under the MIT license
 * http://opensource.org/licenses/MIT
 */

package net.cortexmodders.lyoko.handlers;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.ArrayList;
import java.util.List;

import net.cortexmodders.lyoko.CodeLyoko;
import net.cortexmodders.lyoko.entities.tileentity.TileEntityScanner;
import net.cortexmodders.lyoko.lib.PlayerInformation;
import net.cortexmodders.lyoko.world.LyokoTeleporter;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.packet.Packet250CustomPayload;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;

public class CommandHandler implements ICommand
{
    @SuppressWarnings("rawtypes")
	private List aliases;

    @SuppressWarnings({ "unchecked", "rawtypes" })
	public CommandHandler()
    {
        this.aliases = new ArrayList();
        this.aliases.add("devirt");
    }

    @Override
    public String getCommandName()
    {
        return "devirtualize";
    }

    @Override
    public String getCommandUsage(ICommandSender icommandsender)
    {
        return "devirtualize";
    }

    @SuppressWarnings("rawtypes")
	@Override
    public List getCommandAliases()
    {
        return this.aliases;
    }

    @Override
    public void processCommand(ICommandSender icommandsender, String[] astring)
    {
        if (icommandsender instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer) icommandsender;
            if(!CodeLyoko.entityInLyoko(player))
            {
                if(player.worldObj.isRemote)
                    player.addChatMessage("You cannot be devirtualized because you are not in lyoko");
                return;
            }

            PlayerInformation pi = PlayerInformation.forPlayer(player);

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

            Packet250CustomPayload packet = PacketDispatcher.getPacket("Devirt", bos.toByteArray());
            PacketDispatcher.sendPacketToServer(packet);
            PacketDispatcher.sendPacketToPlayer(packet, (Player) player);
        }
        // wait for ChatMessageComponent to get names put in.
        // icommandsender.func_110122_a(ChatMessageComponent.func_11066d("this command is not ready for use at this time"));
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender icommandsender)
    {
        return true;
    }

    @SuppressWarnings("rawtypes")
	@Override
    public List addTabCompletionOptions(ICommandSender icommandsender, String[] astring)
    {
        return null;
    }

    @Override
    public boolean isUsernameIndex(String[] astring, int i)
    {
        return false;
    }

    @Override
    public int compareTo(Object o)
    {
        return 0;
    }
}
