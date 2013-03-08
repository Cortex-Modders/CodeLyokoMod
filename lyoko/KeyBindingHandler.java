package matt.lyoko;

import java.util.EnumSet;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.settings.KeyBinding;
import cpw.mods.fml.client.registry.KeyBindingRegistry.KeyHandler;
import cpw.mods.fml.common.TickType;

public class KeyBindingHandler extends KeyHandler
{
	static KeyBinding myBinding = new KeyBinding("MyBind", Keyboard.KEY_L);

    public KeyBindingHandler() {
            //the first value is an array of KeyBindings, the second is whether or not the call 
            //keyDown should repeat as long as the key is down
            super(new KeyBinding[]{myBinding}, new boolean[]{false});
    }
	
	@Override
	public String getLabel()
	{
		return "myKeyBindingHandler";
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
		return EnumSet.of(TickType.CLIENT);
	}
}