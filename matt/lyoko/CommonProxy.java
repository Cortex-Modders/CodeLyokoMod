package matt.lyoko;

import net.minecraft.block.Block;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;
import net.minecraftforge.common.ChestGenHooks;
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
		
	}
	
	public void registerOres()
	{
		OreDictionary.registerOre("ingotRadioactiveLead", CodeLyoko.LyokoLead);
		OreDictionary.registerOre("oreRadioactiveLead", CodeLyoko.LeadOre);
		OreDictionary.registerOre("ingotUranium", CodeLyoko.Uranium);
		OreDictionary.registerOre("oreUranium", CodeLyoko.UraniumOre);
	}
	
	public void registerFragmentRecipes()
	{
		GameRegistry.addRecipe(new ItemStack(CodeLyoko.LyokoPolarPortal), new Object[] {
    		"###", "#*#", "###", Character.valueOf('#'), CodeLyoko.DataFragment, Character.valueOf('*'), Block.ice
    	});
		GameRegistry.addRecipe(new ItemStack(CodeLyoko.LyokoMountainPortal), new Object[] {
    		"###", "#*#", "###", Character.valueOf('#'), CodeLyoko.DataFragment, Character.valueOf('*'), Block.stone
    	});
		GameRegistry.addRecipe(new ItemStack(CodeLyoko.LyokoForestPortal), new Object[] {
    		"###", "#*#", "###", Character.valueOf('#'), CodeLyoko.DataFragment, Character.valueOf('*'), Block.grass
    	});
		GameRegistry.addRecipe(new ItemStack(CodeLyoko.LyokoDesertPortal), new Object[] {
    		"###", "#*#", "###", Character.valueOf('#'), CodeLyoko.DataFragment, Character.valueOf('*'), new ItemStack(Block.sandStone, 1, 1)
    	});
		GameRegistry.addRecipe(new ItemStack(CodeLyoko.LyokoCarthagePortal), new Object[] {
    		"###", "#*#", "###", Character.valueOf('#'), CodeLyoko.DataFragment, Character.valueOf('*'), Block.beacon
    	});
	}
	
	public static void addChestLoot()
    {
        ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(CodeLyoko.LyokoIngot), 1, 1, 005));
        ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(CodeLyoko.LyokoLead), 1, 1, 005));
        ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(CodeLyoko.Uranium), 1, 1, 005));
        ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(CodeLyoko.AelitaHelmet), 1, 1, 005));
        ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(CodeLyoko.AelitaChest), 1, 1, 005));
        ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(CodeLyoko.AelitaLegs), 1, 1, 005));
        ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(CodeLyoko.AelitaBoots), 1, 1, 005));
        ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(CodeLyoko.OddHelmet), 1, 1, 005));
        ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(CodeLyoko.OddChest), 1, 1, 005));
        ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(CodeLyoko.OddLegs), 1, 1, 005));
        ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(CodeLyoko.OddBoots), 1, 1, 005));
        ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(CodeLyoko.UlrichHelmet), 1, 1, 005));
        ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(CodeLyoko.UlrichChest), 1, 1, 005));
        ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(CodeLyoko.UlrichLegs), 1, 1, 005));
        ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(CodeLyoko.UlrichBoots), 1, 1, 005));
        ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(CodeLyoko.YumiHelmet), 1, 1, 005));
        ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(CodeLyoko.YumiChest), 1, 1, 005));
        ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(CodeLyoko.YumiLegs), 1, 1, 005));
        ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(CodeLyoko.YumiBoots), 1, 1, 005));
        ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(CodeLyoko.WilliamHelmet), 1, 1, 005));
        ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(CodeLyoko.WilliamChest), 1, 1, 005));
        ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(CodeLyoko.WilliamLegs), 1, 1, 005));
        ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(CodeLyoko.WilliamBoots), 1, 1, 005));
    }
}