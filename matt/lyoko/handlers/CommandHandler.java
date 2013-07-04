package matt.lyoko.handlers;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatMessageComponent;

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
			//player.setPositionAndRotation(SCANNER_X, SCANNER_Y, SCANNER_Z, player.rotationYaw, player.rotationPitch);
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