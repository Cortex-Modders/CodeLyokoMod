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

import com.jadarstudios.developercapes.DevCapesUtil;

import matt.lyoko.blocks.*;
import matt.lyoko.client.GuiHandler;
import matt.lyoko.client.render.TileAnimator;
import matt.lyoko.entities.projectile.EntityLyokoRanged;
import matt.lyoko.entities.tileentity.*;
import matt.lyoko.fluids.ModFluids;
import matt.lyoko.handlers.CommandHandler;
import matt.lyoko.handlers.PacketHandler;
import matt.lyoko.items.*;
import matt.lyoko.lib.*;
import matt.lyoko.world.*;
import net.minecraft.block.material.*;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraftforge.common.*;
import net.minecraftforge.oredict.*;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.*;
import cpw.mods.fml.common.network.*;
import cpw.mods.fml.common.registry.*;

@Mod(modid = ModProperties.MOD_ID, name = ModProperties.MOD_NAME, version = ModProperties.MOD_VERSION, useMetadata = true)
@NetworkMod(clientSideRequired = true, serverSideRequired = false, channels = {"Console", "LifePoints", "Devirt", "ScannerDoors"}, packetHandler = PacketHandler.class)
public class CodeLyoko
{
	private static String[] developers = {"986523714", "MoonMagick", "Wolfspirit1st", "JadarMC"};
	public static List<Item> debugTools = new ArrayList<Item>();
	
	public static HashSet<TileAnimator> animatorInstances = new HashSet<TileAnimator>();
	
	public static boolean enableAdminPowers;
	
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
	public void CodeLyokoPreLoad(FMLPreInitializationEvent preevt)
	{
    	Configuration config = new Configuration(preevt.getSuggestedConfigurationFile());
		config.load();
		
		proxy.registerBlockIds(config);
		proxy.registerItemIds(config);
		proxy.registerDimensionIds(config);
		
        enableAdminPowers = config.get(Configuration.CATEGORY_GENERAL, "enableAdminPowers", false).getBoolean(false);
        
		if(config.hasChanged())
		{
			config.save();
		}
	}
	
	@EventHandler
    public void CodeLyokoLoad(FMLInitializationEvent evt)
    {
		DevCapesUtil.getInstance().addFileUrl("https://dl.dropboxusercontent.com/u/87762025/lyokocapes.txt");
		
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
    	//proxy.registerKeyBindingHandler();
    	proxy.registerOreDictionaryOres();
    	proxy.addChestLoot();
    	proxy.registerTileEntities();
    	proxy.registerDimensions();
    	
    	NetworkRegistry.instance().registerGuiHandler(this, new GuiHandler());
    	
    	GameRegistry.registerWorldGenerator(new WorldGenLyokoOre());
    	GameRegistry.registerWorldGenerator(new WorldGenTower());
    	
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
    		if(ent.dimension == DimensionIds.CARTHAGE || ent.dimension == DimensionIds.ICE
    				|| ent.dimension == DimensionIds.MOUNTAIN || ent.dimension == DimensionIds.FOREST
    				|| ent.dimension == DimensionIds.DESERT || ent.dimension == DimensionIds.CORTEX
    				|| ent.dimension == DimensionIds.DIGITALSEA)
    		{
    			return true;
    		}
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