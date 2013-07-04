package matt.lyoko;

import matt.lyoko.entities.mobs.EntityBlok;
import matt.lyoko.entities.mobs.EntityMegaTank;
import matt.lyoko.entities.vehicles.EntityOverboard;
import matt.lyoko.entities.vehicles.EntitySkid;
import matt.lyoko.handlers.ClientTickHandler;
import matt.lyoko.handlers.EventHandler;
import matt.lyoko.handlers.ServerTickHandler;
import matt.lyoko.items.ModItems;
import matt.lyoko.lib.EntityIds;
import net.minecraft.block.Block;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.common.ChestGenHooks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.registry.*;
import cpw.mods.fml.relauncher.Side;

public class CommonProxy
{

	/**
	 * client side only register stuff...
	 */
	public void registerRenderInformation() 
	{
		//unused server side. -- see ClientProxy for implementation
	}
	
	public void registerTickHandlers()
	{
		TickRegistry.registerTickHandler(new ClientTickHandler(), Side.CLIENT);
		TickRegistry.registerTickHandler(new ServerTickHandler(), Side.SERVER);
	}
	
	public void registerKeyBindingHandler()
	{
		//unused server side
	}
	
	public void registerOreDictionaryOres()
	{
		OreDictionary.registerOre("ingotRadioactiveLead", ModItems.Lead);
		OreDictionary.registerOre("oreRadioactiveLead", CodeLyoko.LeadOre);
		OreDictionary.registerOre("ingotUranium", ModItems.Uranium);
		OreDictionary.registerOre("oreUranium", CodeLyoko.UraniumOre);
	}
	
	public void registerEventHandlers()
	{
    	EventHandler handler = new EventHandler();
    	MinecraftForge.EVENT_BUS.register(handler);
    	GameRegistry.registerPlayerTracker(handler);
	}
	
	public void addChestLoot()
    {
        ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(ModItems.QuantumOrb), 1, 1, 005));
        ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(ModItems.Lead), 1, 1, 005));
        ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(ModItems.Uranium), 1, 1, 005));
        ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(ModItems.AelitaHelmet), 1, 1, 005));
        ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(ModItems.AelitaChest), 1, 1, 005));
        ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(ModItems.AelitaLegs), 1, 1, 005));
        ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(ModItems.AelitaBoots), 1, 1, 005));
        ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(ModItems.OddHelmet), 1, 1, 005));
        ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(ModItems.OddChest), 1, 1, 005));
        ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(ModItems.OddLegs), 1, 1, 005));
        ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(ModItems.OddBoots), 1, 1, 005));
        ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(ModItems.UlrichHelmet), 1, 1, 005));
        ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(ModItems.UlrichChest), 1, 1, 005));
        ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(ModItems.UlrichLegs), 1, 1, 005));
        ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(ModItems.UlrichBoots), 1, 1, 005));
        ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(ModItems.YumiHelmet), 1, 1, 005));
        ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(ModItems.YumiChest), 1, 1, 005));
        ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(ModItems.YumiLegs), 1, 1, 005));
        ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(ModItems.YumiBoots), 1, 1, 005));
        ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(ModItems.WilliamHelmet), 1, 1, 005));
        ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(ModItems.WilliamChest), 1, 1, 005));
        ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(ModItems.WilliamLegs), 1, 1, 005));
        ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(ModItems.WilliamBoots), 1, 1, 005));
        ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(ModItems.QuantumContainmentCell), 1, 1, 005));
    }
	
	public void registerEntities() {
        // Monsters.
//        EntityRegistry.registerGlobalEntityID(EntityBlok.class, "Blok", EntityRegistry.findGlobalUniqueEntityId(), 0xe3b434, 0x000000);
//        EntityRegistry.registerGlobalEntityID(EntityMegaTank.class, "Megatank", EntityRegistry.findGlobalUniqueEntityId(), 0xe3b434, 0x000000);

        // Vehicles
        EntityRegistry.registerModEntity(EntitySkid.class, "Skidbladnir", EntityIds.SKID, CodeLyoko.instance, 50, 1, true);
        EntityRegistry.registerModEntity(EntityOverboard.class, "Overboard", EntityIds.OVERBOARD, CodeLyoko.instance, 50, 1, true);
        
        // Naturally spawn in Lyoko.
        EntityRegistry.addSpawn(matt.lyoko.entities.mobs.EntityBlok.class, 10, 3, 15, EnumCreatureType.monster, CodeLyoko.lyokocarthage, CodeLyoko.lyokoforest, CodeLyoko.lyokomountain, CodeLyoko.lyokopolar, CodeLyoko.lyokodesert);
        EntityRegistry.addSpawn(matt.lyoko.entities.mobs.EntityMegaTank.class, 10, 3, 15, EnumCreatureType.monster, CodeLyoko.lyokocarthage, CodeLyoko.lyokoforest, CodeLyoko.lyokomountain, CodeLyoko.lyokopolar, CodeLyoko.lyokodesert);
    }
    
    public void registerEntityNames() {
        // Monsters.
        LanguageRegistry.instance().addStringLocalization("entity.Blok.name", "en_US", "Blok");
        LanguageRegistry.instance().addStringLocalization("entity.Megatank.name", "en_US", "Megatank");
        
        // Vehicles.
        LanguageRegistry.instance().addStringLocalization("entity.Skidbladnir.name", "en_US", "Skidbladnir");
        LanguageRegistry.instance().addStringLocalization("entity.Overboard.name", "en_US", "Overboard");

    }
}