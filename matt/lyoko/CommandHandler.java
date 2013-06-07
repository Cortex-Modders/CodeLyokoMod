package matt.lyoko;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;

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
		icommandsender.sendChatToPlayer("this command is not ready for use at this time");
		//icommandsender.getPlayerCoordinates().set(SCANNER_X, SCANNER_Y, SCANNER_Z);
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