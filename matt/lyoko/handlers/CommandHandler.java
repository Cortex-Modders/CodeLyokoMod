package matt.lyoko.handlers;

import java.util.ArrayList;
import java.util.List;

import matt.lyoko.lib.PlayerInformation;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;

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
			
			player.dimension = pi.scannerDim;
			player.setPositionAndRotation(pi.getScannerPosX(), pi.getScannerPosY(), pi.getScannerPosZ(), player.rotationYaw, player.rotationPitch);
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