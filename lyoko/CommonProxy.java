package matt.lyoko;

import net.minecraft.block.Block;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

public class CommonProxy implements IGuiHandler
{

	/**
	 * client side only register stuff...
	 */
	public void registerRenderInformation() 
	{
		//unused server side. -- see ClientProxy for implementation
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		return null;
	}
	
	public void registerServerTickHandler()
	{
		TickRegistry.registerTickHandler(new ServerTickHandler(), Side.SERVER);
	}
	
	public void registerKeyBindingHandler()
	{
		KeyBinding[] keybind = new KeyBinding[]{};
		KeyBindingRegistry.registerKeyBinding(new KeyBindingHandler(keybind));
	}
	
	public void registerOres()
	{
		OreDictionary.registerOre("ingotRadioactiveLead", CodeLyoko.LyokoLead);
		OreDictionary.registerOre("oreRadioactiveLead", CodeLyoko.LeadOre);
	}
	
	public void registerFragmentRecipes()
	{
		GameRegistry.addShapelessRecipe(new ItemStack(Block.wood, 1), new Object[] {
    		CodeLyoko.DataFragment
    	});
		GameRegistry.addShapelessRecipe(new ItemStack(Block.cobblestone, 1), new Object[] {
    		CodeLyoko.DataFragment, CodeLyoko.DataFragment
    	});
		GameRegistry.addShapelessRecipe(new ItemStack(Item.coal, 1), new Object[] {
    		CodeLyoko.DataFragment, CodeLyoko.DataFragment, CodeLyoko.DataFragment
    	});
		GameRegistry.addShapelessRecipe(new ItemStack(Item.ingotIron, 1), new Object[] {
    		CodeLyoko.DataFragment, CodeLyoko.DataFragment, CodeLyoko.DataFragment, CodeLyoko.DataFragment
    	});
		GameRegistry.addShapelessRecipe(new ItemStack(Item.beefCooked, 1), new Object[] {
    		CodeLyoko.DataFragment, CodeLyoko.DataFragment, CodeLyoko.DataFragment, CodeLyoko.DataFragment, CodeLyoko.DataFragment
    	});
		GameRegistry.addShapelessRecipe(new ItemStack(Item.expBottle, 1), new Object[] {
    		CodeLyoko.DataFragment, CodeLyoko.DataFragment, CodeLyoko.DataFragment, CodeLyoko.DataFragment, CodeLyoko.DataFragment,
    		CodeLyoko.DataFragment
    	});
		GameRegistry.addShapelessRecipe(new ItemStack(Item.diamond, 1), new Object[] {
    		CodeLyoko.DataFragment, CodeLyoko.DataFragment, CodeLyoko.DataFragment, CodeLyoko.DataFragment, CodeLyoko.DataFragment,
    		CodeLyoko.DataFragment, CodeLyoko.DataFragment
    	});
		GameRegistry.addShapelessRecipe(new ItemStack(Item.emerald, 1), new Object[] {
    		CodeLyoko.DataFragment, CodeLyoko.DataFragment, CodeLyoko.DataFragment, CodeLyoko.DataFragment, CodeLyoko.DataFragment,
    		CodeLyoko.DataFragment, CodeLyoko.DataFragment, CodeLyoko.DataFragment
    	});
		GameRegistry.addShapelessRecipe(new ItemStack(Item.netherStar, 1), new Object[] {
    		CodeLyoko.DataFragment, CodeLyoko.DataFragment, CodeLyoko.DataFragment, CodeLyoko.DataFragment, CodeLyoko.DataFragment,
    		CodeLyoko.DataFragment, CodeLyoko.DataFragment, CodeLyoko.DataFragment, CodeLyoko.DataFragment
    	});
	}
}