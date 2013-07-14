package matt.lyoko.handlers;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.ArrayList;
import java.util.List;

import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;

import matt.lyoko.lib.DimensionIds;
import matt.lyoko.lib.PlayerInformation;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.packet.Packet250CustomPayload;

public class CommandHandler implements ICommand
{
	private List aliases;
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
	
	@Override
	public List getCommandAliases()
	{
		return this.aliases;
	}
	
	@Override
	public void processCommand(ICommandSender icommandsender, String[] astring)
	{
		if(icommandsender instanceof EntityPlayer)
		{
			EntityPlayer player = ((EntityPlayer)icommandsender);
			player.addChatMessage("this command is not ready for use at this time");
			
			PlayerInformation pi = PlayerInformation.forPlayer(player);
			
			//player.dimension = pi.scannerDim;
			//player.travelToDimension(pi.scannerDim);
			DimensionIds.teleportToDimension(player, pi.scannerDim);
			player.setPositionAndRotation(pi.getScannerPosX() + 0.5D, pi.getScannerPosY(), pi.getScannerPosZ() + 0.5D, pi.scannerYaw, 0.0F);
			
			ByteArrayOutputStream bos = new ByteArrayOutputStream(32);
		    DataOutputStream outputStream = new DataOutputStream(bos);
		    try
		    {
		    	//outputStream.writeInt(player.dimension);
		    	outputStream.writeDouble(player.posX);
		    	outputStream.writeDouble(player.posY);
		    	outputStream.writeDouble(player.posZ);
		    	outputStream.writeFloat(player.rotationYaw);
		    }
		    catch (Exception ex)
		    {
		    	ex.printStackTrace();
		    }
		    
		    Packet250CustomPayload packet = new Packet250CustomPayload();
		    packet.channel = "Devirt";
		    packet.data = bos.toByteArray();
		    packet.length = bos.size();
		    
		    PacketDispatcher.sendPacketToPlayer(packet,(Player) player);
		}
		// wait for ChatMessageComponent to get names put in.
		//		icommandsender.func_110122_a(ChatMessageComponent.func_11066d("this command is not ready for use at this time"));
	}
	
	@Override
	public boolean canCommandSenderUseCommand(ICommandSender icommandsender)
	{
		return true;
	}
	
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