/*
 * Code Lyoko Mod for Minecraft v@VERSION
 *
 * Copyright 2013 Cortex Modders, Matthew Warren, Jacob Rhoda, and
 * other contributors.
 * Released under the MIT license
 * http://opensource.org/licenses/MIT
 * 
 */

package matt.lyoko;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import matt.lyoko.blocks.ModBlocks;
import matt.lyoko.client.GuiHandler;
import matt.lyoko.client.render.TileAnimator;
import matt.lyoko.entities.projectile.EntityLyokoRanged;
import matt.lyoko.fluids.ModFluids;
import matt.lyoko.handlers.CommandHandler;
import matt.lyoko.items.ModItems;
import matt.lyoko.lib.ModProperties.ConfigCategories;
import matt.lyoko.lib.DimensionIds;
import matt.lyoko.lib.ModProperties;
import matt.lyoko.lib.Recipes;
import matt.lyoko.world.BiomeGenBaseLyoko;
import matt.lyoko.world.BiomeGenCarthageSector;
import matt.lyoko.world.BiomeGenDesertSector;
import matt.lyoko.world.BiomeGenForestSector;
import matt.lyoko.world.BiomeGenMountainSector;
import matt.lyoko.world.BiomeGenPolarSector;
import matt.lyoko.world.WorldGenLyokoOre;
import matt.lyoko.world.WorldGenTower;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.Configuration;
//import com.jadarstudios.developercapes.DevCapesUtil;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
//import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;

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
	
	private Configuration modConfig;
	
	@SidedProxy(clientSide = ModProperties.CLIENT_PROXY, serverSide = ModProperties.COMMON_PROXY)
	public static CommonProxy proxy; //This object will be populated with the class that you choose for the environment
	@Instance
	public static CodeLyoko instance;//the instance of the mod that will be defined, populated, and callable
	
	@EventHandler
	public void CodeLyokoPreLoad(FMLPreInitializationEvent preevt)
	{
	    modConfig = proxy.initConfig(preevt.getSuggestedConfigurationFile());
	    
		proxy.registerBlockIds(modConfig);
		proxy.registerItemIds(modConfig);
		proxy.registerDimensionIds(modConfig);
		
        enableAdminPowers = modConfig.get(ModProperties.ConfigCategories.OTHER.name(), "enableAdminPowers", false).getBoolean(false);
        useHDTextures = modConfig.get(ModProperties.ConfigCategories.OTHER.name(), "useHDTextures", false).getBoolean(false);
        
		if(modConfig.hasChanged())
		{
		    modConfig.save();
		}
	}
	
	@EventHandler
    public void CodeLyokoLoad(FMLInitializationEvent evt)
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
    	
    	MinecraftForge.setBlockHarvestLevel(ModBlocks.QuantumOre, "pickaxe", 2);
    	MinecraftForge.setBlockHarvestLevel(ModBlocks.LeadOre, "pickaxe", 2);
    	MinecraftForge.setBlockHarvestLevel(ModBlocks.UraniumOre, "pickaxe", 2);
    }
    
	@EventHandler
    public void CodeLyokoPostLoad(FMLPostInitializationEvent postevt)
    {
    	proxy.registerEventHandlers();
    }
    
    @EventHandler
	public void serverLoad(FMLServerStartingEvent event)
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