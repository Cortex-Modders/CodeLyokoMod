/*
 * Code Lyoko Mod for Minecraft v@VERSION
 *
 * Copyright 2013 Cortex Modders, Matthew Warren, Jacob Rhoda, and
 * other contributors.
 * Released under the MIT license
 * http://opensource.org/licenses/MIT
 * 
 */

package net.cortexmodders.lyoko;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import net.cortexmodders.lyoko.blocks.ModBlocks;
import net.cortexmodders.lyoko.client.GuiHandler;
import net.cortexmodders.lyoko.client.LyokoTab;
import net.cortexmodders.lyoko.client.render.TileAnimator;
import net.cortexmodders.lyoko.entities.projectile.EntityLyokoRanged;
import net.cortexmodders.lyoko.fluids.ModFluids;
import net.cortexmodders.lyoko.handlers.CommandHandler;
import net.cortexmodders.lyoko.items.ModItems;
import net.cortexmodders.lyoko.lib.DimensionIds;
import net.cortexmodders.lyoko.lib.ModProperties;
import net.cortexmodders.lyoko.lib.Recipes;
import net.cortexmodders.lyoko.network.PacketHandler;
import net.cortexmodders.lyoko.proxy.CommonProxy;
import net.cortexmodders.lyoko.world.BiomeGenBaseLyoko;
import net.cortexmodders.lyoko.world.BiomeGenCarthageSector;
import net.cortexmodders.lyoko.world.BiomeGenDesertSector;
import net.cortexmodders.lyoko.world.BiomeGenForestSector;
import net.cortexmodders.lyoko.world.BiomeGenMountainSector;
import net.cortexmodders.lyoko.world.BiomeGenPolarSector;
import net.cortexmodders.lyoko.world.WorldGenLyokoOre;
import net.cortexmodders.lyoko.world.WorldGenTower;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
//import com.jadarstudios.developercapes.DevCapesUtil;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
//import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = ModProperties.MOD_ID, name = ModProperties.MOD_NAME, version = ModProperties.MOD_VERSION, useMetadata = true)
//@NetworkMod(clientSideRequired = true, serverSideRequired = false, channels = {"Console", "LifePoints", "Devirt", "ScannerDoors", "Vehicle"}, packetHandler = PacketHandler.class)
public class CodeLyoko
{
	private static String[] developers = {"986523714", "MoonMagick", "Wolfspirit1st", "JadarMC"};
	public static List<Item> debugTools = new ArrayList<Item>();
	
	public static HashSet<TileAnimator> animatorInstances = new HashSet<TileAnimator>();
	
	public static boolean enableAdminPowers;
	public static boolean useHDTextures;
	
	public static CreativeTabs LyokoTabs = new LyokoTab("LyokoTabs");
	
	public static BiomeGenBaseLyoko lyokomountain;
	public static BiomeGenBaseLyoko lyokoforest;
	public static BiomeGenBaseLyoko lyokodesert;
	public static BiomeGenBaseLyoko lyokopolar;
	public static BiomeGenBaseLyoko lyokocarthage;
	
	@SidedProxy(clientSide = ModProperties.CLIENT_PROXY, serverSide = ModProperties.COMMON_PROXY)
	public static CommonProxy proxy; //This object will be populated with the class that you choose for the environment
	@Instance
	public static CodeLyoko instance;//the instance of the mod that will be defined, populated, and callable
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent preevt)
	{
		Configuration config = new Configuration(preevt.getSuggestedConfigurationFile());
	    
		proxy.registerBlockIds(config);
		proxy.registerItemIds(config);
		proxy.registerDimensionIds(config);
		
        enableAdminPowers = config.get(ModProperties.ConfigCategories.OTHER.name(), "enableAdminPowers", false).getBoolean(false);
        useHDTextures = config.get(ModProperties.ConfigCategories.OTHER.name(), "useHDTextures", false).getBoolean(false);
        
		if(config.hasChanged())
		{
			config.save();
		}
	}
	
	@EventHandler
    public void init(FMLInitializationEvent evt)
    {
		//DevCapesUtil.addFileUrl("https://dl.dropboxusercontent.com/u/87762025/lyokocapes.txt");
		
		ModFluids.init();
		ModItems.init();
		ModBlocks.init();
		
		lyokomountain = (BiomeGenBaseLyoko) (new BiomeGenMountainSector(9)).setColor(8421631).setBiomeName("Mountain Sector");
		lyokoforest = (BiomeGenBaseLyoko) (new BiomeGenForestSector(10).setColor(8421631)).setBiomeName("Forest Sector");
		lyokodesert = (BiomeGenBaseLyoko) (new BiomeGenDesertSector(11)).setColor(8421631).setBiomeName("Desert Sector");
		lyokopolar = (BiomeGenBaseLyoko) (new BiomeGenPolarSector(12)).setColor(8421631).setBiomeName("Polar Sector");
		lyokocarthage = (BiomeGenBaseLyoko) (new BiomeGenCarthageSector(13)).setColor(8421631).setBiomeName("Carthage Sector");
		
		Recipes.registerBlockRecipes();
		Recipes.registerSmelting();
		Recipes.registerItemRecipes();
    	
    	proxy.registerEntities();
    	proxy.registerNames();
    	proxy.registerRenderInformation();
    	proxy.registerTickHandlers();
    	proxy.registerKeyBindingHandler();
    	proxy.registerOreDictionaryOres();
    	proxy.addChestLoot();
    	proxy.registerTileEntities();
    	proxy.registerDimensions();
    	
    	NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
    	
    	GameRegistry.registerWorldGenerator(new WorldGenLyokoOre(), 0);
    	GameRegistry.registerWorldGenerator(new WorldGenTower(), 0);
    	
    	//Block.setHarvestLevel(ModBlocks.QuantumOre, "pickaxe", 2);
    	//MinecraftForge.setBlockHarvestLevel(ModBlocks.LeadOre, "pickaxe", 2);
    	//MinecraftForge.setBlockHarvestLevel(ModBlocks.UraniumOre, "pickaxe", 2);
    	
    	PacketHandler.getInstance();
    }
    
	@EventHandler
    public void postInit(FMLPostInitializationEvent postevt)
    {
    	proxy.registerEventHandlers();
    }
    
    @EventHandler
	public void serverInit(FMLServerStartingEvent event)
	{
		event.registerServerCommand(new CommandHandler());
	}
    
    public static boolean entityInLyoko(Entity ent)
    {
    	if(ent != null)
    	{
    		return ent.dimension == DimensionIds.CARTHAGE || ent.dimension == DimensionIds.ICE
    				|| ent.dimension == DimensionIds.MOUNTAIN || ent.dimension == DimensionIds.FOREST
    				|| ent.dimension == DimensionIds.DESERT || ent.dimension == DimensionIds.CORTEX
    				|| ent.dimension == DimensionIds.DIGITALSEA;
    	}
    	return false;
    }
    
    public static String[] getDevelopers()
    {
    	return developers;
    }
    
    public static DamageSource causeLyokoRangedDamage(EntityLyokoRanged par0EntityLaserArrow, Entity par1Entity)
    {
        return (new EntityDamageSourceIndirect("arrow", par0EntityLaserArrow, par1Entity)).setProjectile();
    }
}
