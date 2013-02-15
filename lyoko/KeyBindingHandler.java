package matt.lyoko;

import java.util.EnumSet;
import net.minecraft.client.settings.KeyBinding;
import cpw.mods.fml.client.registry.KeyBindingRegistry.KeyHandler;
import cpw.mods.fml.common.TickType;

public class KeyBindingHandler extends KeyHandler
{
	public KeyBindingHandler(KeyBinding[] keyBindings)
	{
		super(keyBindings);
	}
	
	@Override
	public String getLabel()
	{
		return null;
	}
	
	@Override
	public void keyDown(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd, boolean isRepeat)
	{
		
	}
	
	@Override
	public void keyUp(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd)
	{
		
	}
	
	@Override
	public EnumSet<TickType> ticks()
	{
		return null;
	}
}