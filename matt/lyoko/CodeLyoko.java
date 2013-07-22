package matt.lyoko;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.jadarstudios.developercapes.DevCapesUtil;

import matt.lyoko.blocks.*;
import matt.lyoko.client.GuiHandler;
import matt.lyoko.client.SoundHandler;
import matt.lyoko.entities.projectile.EntityLaser;
import matt.lyoko.entities.projectile.EntityLyokoRanged;
import matt.lyoko.entities.tileentity.*;
import matt.lyoko.fluids.ModFluids;
import matt.lyoko.handlers.CommandHandler;
import matt.lyoko.handlers.PacketHandler;
import matt.lyoko.items.*;
import matt.lyoko.lib.*;
import matt.lyoko.render.TileAnimator;
import matt.lyoko.world.*;
import net.minecraft.block.Block;
import net.minecraft.block.material.*;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.*;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.util.*;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.*;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.oredict.*;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.*;
import cpw.mods.fml.common.network.*;
import cpw.mods.fml.common.registry.*;

@Mod(modid = ModProperties.MOD_ID, name = ModProperties.MOD_NAME, version = ModProperties.MOD_VERSION, useMetadata = true)
@NetworkMod(clientSideRequired = true, serverSideRequired = false, channels = {"Code_Lyoko", "SuperCalcConsole", "LifePoints", "Devirt"}, packetHandler = PacketHandler.class)
public class CodeLyoko
{
	private static String[] developers = {"986523714", "MoonMagick", "Wolfspirit1st", "JadarMC"};
	public static List<Item> debugTools = new ArrayList();
	
	public static HashSet<TileAnimator> animatorInstances = new HashSet<TileAnimator>();
	
	public static int SuperCalcRenderID;
	public static int SuperCalcTexture;
	
	public static boolean enableAdminPowers;
	
	public static CreativeTabs LyokoTabs = new LyokoTab("LyokoTabs");
	public static final BiomeGenBase lyokomountain = ((BiomeGenBaseLyoko) (new BiomeGenMountainSector(9)).setColor(8421631)).setLyokoBiomeName("Mountain Sector");
	public static final BiomeGenBase lyokoforest = ((BiomeGenBaseLyoko) (new BiomeGenForestSector(10)).setColor(8421631)).setLyokoBiomeName("Forest Sector");
	public static final BiomeGenBase lyokodesert = ((BiomeGenBaseLyoko) (new BiomeGenDesertSector(11)).setColor(8421631)).setLyokoBiomeName("Desert Sector");
	public static final BiomeGenBase lyokopolar = ((BiomeGenBaseLyoko) (new BiomeGenPolarSector(12)).setColor(8421631)).setLyokoBiomeName("Polar Sector");
	public static final BiomeGenBase lyokocarthage = ((BiomeGenBaseLyoko) (new BiomeGenCarthageSector(13)).setColor(8421631)).setLyokoBiomeName("Carthage Sector");
	
	
	@SidedProxy(clientSide = ModProperties.CLIENT_PROXY, serverSide = ModProperties.COMMON_PROXY)
	public static CommonProxy proxy; //This object will be populated with the class that you choose for the environment
	@Instance
	public static CodeLyoko instance;//the instance of the mod that will be defined, populated, and callable
	
	@EventHandler
	public void CodeLyokoPreLoad(FMLPreInitializationEvent preevt)
	{
    	Configuration config = new Configuration(preevt.getSuggestedConfigurationFile());
		config.load();
		
		// Blocks
		BlockIds.LYOKO_TOWER = config.getBlock("lyokoTower", BlockIds.LYOKO_TOWER_DEFAULT).getInt();
		BlockIds.LYOKO_TOWER_BASE = config.getBlock("lyokoTowerBase", BlockIds.LYOKO_TOWER_BASE_DEFAULT).getInt();
		BlockIds.LYOKO_GRASS = config.getBlock("lyokoGrass", BlockIds.LYOKO_GRASS_DEFAULT).getInt();
		BlockIds.LYOKO_STONE = config.getBlock("lyokoStone", BlockIds.LYOKO_STONE_DEFAULT).getInt();
		BlockIds.LYOKO_SAND = config.getBlock("lyokoSand", BlockIds.LYOKO_SAND_DEFAULT).getInt();
		BlockIds.LYOKO_ICE = config.getBlock("lyokoIce", BlockIds.LYOKO_ICE_DEFAULT).getInt();
		BlockIds.LYOKO_LOG = config.getBlock("lyokoLog", BlockIds.LYOKO_LOG_DEFAULT).getInt();
		BlockIds.LYOKO_DIGITAL_SEA_BLOCK = config.getBlock("lyokoSeaBlock", BlockIds.LYOKO_DIGITAL_SEA_BLOCK_DEFAULT).getInt();
		BlockIds.LYOKO_DIGITAL_SEA_LIQUID = config.getBlock("lyokoSeaFlowing", BlockIds.LYOKO_DIGITAL_SEA_LIQUID_DEFAULT).getInt();
		BlockIds.CABLE = config.getBlock("cable", BlockIds.CABLE_DEFAULT).getInt();
		BlockIds.LYOKO_VIRTUAL_BLOCK = config.getBlock("lyokovirtualblock", BlockIds.LYOKO_VIRTUAL_BLOCK_DEFAULT).getInt();
		BlockIds.LYOKO_CARTHAGE = config.getBlock("lyokoCarthage", BlockIds.LYOKO_CARTHAGE_DEFAULT).getInt();
		BlockIds.LYOKO_ORE = config.getBlock("lyokoOre", BlockIds.LYOKO_ORE_DEFAULT).getInt();
		BlockIds.LYOKO_ICE_PORTAL = config.getBlock("lyokoPolarPortal", BlockIds.LYOKO_ICE_PORTAL_DEFAULT).getInt();
		BlockIds.LYOKO_DESERT_PORTAL = config.getBlock("lyokoDesertPortal", BlockIds.LYOKO_DESERT_PORTAL_DEFAULT).getInt();
		BlockIds.LYOKO_FOREST_PORTAL = config.getBlock("lyokoForestPortal", BlockIds.LYOKO_FOREST_PORTAL_DEFAULT).getInt();
		BlockIds.LYOKO_MOUNTAIN_PORTAL = config.getBlock("lyokoMountainPortal", BlockIds.LYOKO_MOUNTAIN_PORTAL_DEFAULT).getInt();
		BlockIds.LYOKO_CARTHAGE_PORTAL = config.getBlock("lyokoCarthagePortal", BlockIds.LYOKO_CARTHAGE_PORTAL_DEFAULT).getInt();
		BlockIds.LYOKO_URANIUM_ORE = config.getBlock("lyokoUraniumOre", BlockIds.LYOKO_URANIUM_ORE_DEFAULT).getInt();
		BlockIds.LYOKO_SUPER_CALC = config.getBlock("lyokoSuperCalculator", BlockIds.LYOKO_SUPER_CALC_DEFAULT).getInt();
		BlockIds.LYOKO_LEAD_ORE = config.getBlock("lyokoLeadOre", BlockIds.LYOKO_LEAD_ORE_DEFAULT).getInt();
		BlockIds.LYOKO_MARABOUNTA = config.getBlock("marabounta", BlockIds.LYOKO_MARABOUNTA_DEFAULT).getInt();
		BlockIds.TOWER_CONSOLE = config.getBlock("towerConsole", BlockIds.TOWER_CONSOLE_DEFAULT).getInt();
		BlockIds.TOWER_FLOOR = config.getBlock("towerWall", BlockIds.TOWER_FLOOR_DEFAULT).getInt();
		BlockIds.SCANNER = config.getBlock("scanner", BlockIds.SCANNER_DEFAULT).getInt();
		BlockIds.SUPER_CALC_CONSOLE = config.getBlock("superCalcConsole", BlockIds.SUPER_CALC_CONSOLE_DEFAULT).getInt();
		
		// Items
		ItemIds.WEAPON_LYOKO_1 = config.getItem("weaponLyoko1", ItemIds.WEAPON_LYOKO_1_DEFAULT).getInt();
		ItemIds.WEAPON_LYOKO_2 = config.getItem("weaponLyoko2", ItemIds.WEAPON_LYOKO_2_DEFAULT).getInt();
		ItemIds.WEAPON_LYOKO_3 = config.getItem("weaponLyoko3", ItemIds.WEAPON_LYOKO_3_DEFAULT).getInt();
		ItemIds.WEAPON_LYOKO_4 = config.getItem("weaponLyoko4", ItemIds.WEAPON_LYOKO_4_DEFAULT).getInt();
		ItemIds.WEAPON_LYOKO_5 = config.getItem("weaponLyoko5", ItemIds.WEAPON_LYOKO_5_DEFAULT).getInt();
		ItemIds.WEAPON_LYOKO_6 = config.getItem("weaponLyoko6", ItemIds.WEAPON_LYOKO_6_DEFAULT).getInt();
		ItemIds.ITEM_SKID = config.getItem("itemLyoko1", ItemIds.ITEM_SKID_DEFAULT).getInt();
		ItemIds.ITEM_OVERBOARD = config.getItem("itemOverboard", ItemIds.ITEM_OVERBOARD_DEFAULT).getInt();
		ItemIds.ITEM_QUANTUM_MATRIX = config.getItem("itemQuantumMatrix", ItemIds.ITEM_QUANTUM_MATRIX_DEFAULT).getInt();
		ItemIds.ITEM_QUANTUM_CONTAINMENT_CELL = config.getItem("itemQuantumContainmentCell", ItemIds.ITEM_QUANTUM_CONTAINMENT_CELL_DEFAULT).getInt();
		/*
		 * commented out until needed so that the config file isn't cluttered with unused stuff
		 */
		ItemIds.LASER_SHOOTER = config.getItem("laserShooter", ItemIds.LASER_SHOOTER_DEFAULT).getInt();
		//ItemIds.NOT_USED5 = config.getItem("itemLyoko5", ItemIds.NOT_USED5_DEFAULT).getInt();
		//ItemIds.NOT_USED6 = config.getItem("itemLyoko6", ItemIds.NOT_USED6_DEFAULT).getInt();
		//ItemIds.NOT_USED7 = config.getItem("itemLyoko7", ItemIds.NOT_USED7_DEFAULT).getInt();
		//ItemIds.NOT_USED8 = config.getItem("itemLyoko8", ItemIds.NOT_USED8_DEFAULT).getInt();
		//ItemIds.NOT_USED9 = config.getItem("itemLyoko9", ItemIds.NOT_USED9_DEFAULT).getInt();
		ItemIds.ITEM_LYOKO_URANIUM = config.getItem("itemLyokoUranium", ItemIds.ITEM_LYOKO_URANIUM_DEFAULT).getInt();
		ItemIds.ITEM_LYOKO_11 = config.getItem("itemLyoko11", ItemIds.ITEM_LYOKO_11_DEFAULT).getInt();
		ItemIds.ITEM_LYOKO_12 = config.getItem("itemLyoko12", ItemIds.ITEM_LYOKO_12_DEFAULT).getInt();
		ItemIds.ITEM_LYOKO_13 = config.getItem("itemLyoko13", ItemIds.ITEM_LYOKO_13_DEFAULT).getInt();
		ItemIds.ITEM_LYOKO_14 = config.getItem("itemLyoko14", ItemIds.ITEM_LYOKO_14_DEFAULT).getInt();
		ItemIds.ITEM_LYOKO_15 = config.getItem("itemLyoko15", ItemIds.ITEM_LYOKO_15_DEFAULT).getInt();
		ItemIds.AELITA_ARMOR_HELMET = config.getItem("aelitaArmorHelmet", ItemIds.AELITA_ARMOR_HELMET_DEFAULT).getInt();
		ItemIds.AELITA_ARMOR_CHEST = config.getItem("aelitaArmorChest", ItemIds.AELITA_ARMOR_CHEST_DEFAULT).getInt();
		ItemIds.AELITA_ARMOR_PANTS = config.getItem("aelitaArmorPants", ItemIds.AELITA_ARMOR_PANTS_DEFAULT).getInt();
		ItemIds.AELITA_ARMOR_BOOTS = config.getItem("aelitaArmorBoots", ItemIds.AELITA_ARMOR_BOOTS_DEFAULT).getInt();
		ItemIds.ODD_ARMOR_HELMET = config.getItem("oddArmorHelmet", ItemIds.ODD_ARMOR_HELMET_DEFAULT).getInt();
		ItemIds.ODD_ARMOR_CHEST = config.getItem("oddArmorChest", ItemIds.ODD_ARMOR_CHEST_DEFAULT).getInt();
		ItemIds.ODD_ARMOR_PANTS = config.getItem("oddArmorPants", ItemIds.ODD_ARMOR_PANTS_DEFAULT).getInt();
		ItemIds.ODD_ARMOR_BOOTS = config.getItem("oddArmorBoots", ItemIds.ODD_ARMOR_BOOTS_DEFAULT).getInt();
		ItemIds.ULRICH_ARMOR_HELMET = config.getItem("ulrichArmorHelmet", ItemIds.ULRICH_ARMOR_HELMET_DEFAULT).getInt();
		ItemIds.ULRICH_ARMOR_CHEST = config.getItem("ulrichArmorChest", ItemIds.ULRICH_ARMOR_CHEST_DEFAULT).getInt();
		ItemIds.ULRICH_ARMOR_PANTS = config.getItem("ulrichArmorPants", ItemIds.ULRICH_ARMOR_PANTS_DEFAULT).getInt();
		ItemIds.ULRICH_ARMOR_BOOTS = config.getItem("ulrichArmorBoots", ItemIds.ULRICH_ARMOR_BOOTS_DEFAULT).getInt();
		ItemIds.YUMI_ARMOR_HELMET = config.getItem("yumiArmorHelmet", ItemIds.YUMI_ARMOR_HELMET_DEFAULT).getInt();
		ItemIds.YUMI_ARMOR_CHEST = config.getItem("yumiArmorChest", ItemIds.YUMI_ARMOR_CHEST_DEFAULT).getInt();
		ItemIds.YUMI_ARMOR_PANTS = config.getItem("yumiArmorPants", ItemIds.YUMI_ARMOR_PANTS_DEFAULT).getInt();
		ItemIds.YUMI_ARMOR_BOOTS = config.getItem("yumiArmorBoots", ItemIds.YUMI_ARMOR_BOOTS_DEFAULT).getInt();
		ItemIds.WILLIAM_ARMOR_HELMET = config.getItem("williamArmorHelmet", ItemIds.WILLIAM_ARMOR_HELMET_DEFAULT).getInt();
		ItemIds.WILLIAM_ARMOR_CHEST = config.getItem("williamArmorChest", ItemIds.WILLIAM_ARMOR_CHEST_DEFAULT).getInt();
		ItemIds.WILLIAM_ARMOR_PANTS = config.getItem("williamArmorPants", ItemIds.WILLIAM_ARMOR_PANTS_DEFAULT).getInt();
		ItemIds.WILLIAM_ARMOR_BOOTS = config.getItem("williamArmorBoots", ItemIds.WILLIAM_ARMOR_BOOTS_DEFAULT).getInt();
		ItemIds.DATA_FRAGMENT = config.getItem("dataFragment", ItemIds.DATA_FRAGMENT_DEFAULT).getInt();
		ItemIds.ITEM_LYOKO_URANIUM_CELL = config.getItem("itemLyokoUraniumCell", ItemIds.ITEM_LYOKO_URANIUM_CELL_DEFAULT).getInt();
		ItemIds.ITEM_LYOKO_DEPLETED_URANIUM = config.getItem("itemLyokoDepletedUranium", ItemIds.ITEM_LYOKO_DEPLETED_URANIUM_DEFAULT).getInt();
		
        enableAdminPowers = config.get(Configuration.CATEGORY_GENERAL, "enableAdminPowers", false).getBoolean(false);
		
		DimensionIds.ICE = config.get(Configuration.CATEGORY_GENERAL, "polarSectorID", DimensionIds.ICE_DEFAULT).getInt();
		DimensionIds.MOUNTAIN = config.get(Configuration.CATEGORY_GENERAL, "mountainSectorID", DimensionIds.MOUNTAIN_DEFAULT).getInt();
		DimensionIds.FOREST = config.get(Configuration.CATEGORY_GENERAL, "forestSectorID", DimensionIds.FOREST_DEFAULT).getInt();
		DimensionIds.DESERT = config.get(Configuration.CATEGORY_GENERAL, "desertSectorID", DimensionIds.DESERT_DEFAULT).getInt();
		DimensionIds.CARTHAGE = config.get(Configuration.CATEGORY_GENERAL, "carthageSectorID", DimensionIds.CARTHAGE_DEFAULT).getInt();
		DimensionIds.DIGITALSEA = config.get(Configuration.CATEGORY_GENERAL, "digitalSeaSectorID", DimensionIds.DIGITALSEA_DEFAULT).getInt();
		DimensionIds.CORTEX = config.get(Configuration.CATEGORY_GENERAL, "cortexSectorID", DimensionIds.CORTEX_DEFAULT).getInt();
		
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
		
		Recipes.registerBlockRecipes();
		Recipes.registerSmelting();
		Recipes.registerItemRecipes();
    	
    	proxy.registerEntities();
    	proxy.registerEntityNames();
    	proxy.registerRenderInformation();
    	proxy.registerTickHandlers();
    	//proxy.registerKeyBindingHandler();
    	proxy.registerOreDictionaryOres();
    	proxy.addChestLoot();
    	
    	NetworkRegistry.instance().registerGuiHandler(this, new GuiHandler());
    	
    	GameRegistry.registerTileEntity(TileEntitySuperCalc.class, "teSuperCalc");
    	GameRegistry.registerTileEntity(TileEntityScanner.class, "teScanner");
    	GameRegistry.registerTileEntity(TileEntityTower.class, "teTower");
    	GameRegistry.registerTileEntity(TileEntityTowerConsole.class, "teTowerConsole");
    	GameRegistry.registerTileEntity(TileEntityMarabounta.class, "teMarabounta");
    	GameRegistry.registerTileEntity(TileEntitySuperCalcConsole.class, "teSuperCalcConsole");
    	GameRegistry.registerTileEntity(TileEntityCable.class, "teCable");
    	
    	GameRegistry.registerWorldGenerator(new WorldGenLyokoOre());
    	GameRegistry.registerWorldGenerator(new WorldGenTower());
    	
    	MinecraftForge.setBlockHarvestLevel(ModBlocks.QuantumOre, "pickaxe", 2);
    	MinecraftForge.setBlockHarvestLevel(ModBlocks.LeadOre, "pickaxe", 2);
    	MinecraftForge.setBlockHarvestLevel(ModBlocks.UraniumOre, "pickaxe", 2);
    	
    	//Lyoko Sectors Dimension Register
    	DimensionManager.registerProviderType(DimensionIds.ICE, LyokoPolarSector.class, true);
    	DimensionManager.registerProviderType(DimensionIds.MOUNTAIN, LyokoMountainSector.class, true);
    	DimensionManager.registerProviderType(DimensionIds.FOREST, LyokoForestSector.class, true);
    	DimensionManager.registerProviderType(DimensionIds.DESERT, LyokoDesertSector.class, true);
    	DimensionManager.registerProviderType(DimensionIds.CARTHAGE, LyokoCarthageSector.class, true);
    	DimensionManager.registerDimension(DimensionIds.ICE, DimensionIds.ICE);
    	DimensionManager.registerDimension(DimensionIds.MOUNTAIN, DimensionIds.MOUNTAIN);
    	DimensionManager.registerDimension(DimensionIds.FOREST, DimensionIds.FOREST);
    	DimensionManager.registerDimension(DimensionIds.DESERT, DimensionIds.DESERT);
    	DimensionManager.registerDimension(DimensionIds.CARTHAGE, DimensionIds.CARTHAGE);
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