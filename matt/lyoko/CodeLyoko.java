package matt.lyoko;

import java.util.HashSet;
import matt.lyoko.blocks.*;
import matt.lyoko.client.GuiHandler;
import matt.lyoko.client.SoundHandler;
import matt.lyoko.entities.projectile.EntityLaser;
import matt.lyoko.entities.projectile.EntityLyokoRanged;
import matt.lyoko.entities.tileentity.*;
import matt.lyoko.items.*;
import matt.lyoko.lib.*;
import matt.lyoko.network.PacketHandler;
import matt.lyoko.render.TileAnimator;
import matt.lyoko.world.*;
import net.minecraft.block.Block;
import net.minecraft.block.material.*;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.*;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.util.*;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.*;
import net.minecraftforge.oredict.*;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.*;
import cpw.mods.fml.common.network.*;
import cpw.mods.fml.common.registry.*;

@Mod(modid = ModProperties.MOD_ID, name = ModProperties.MOD_NAME, version = ModProperties.MOD_VERSION, useMetadata = true)
@NetworkMod(clientSideRequired = true, serverSideRequired = false, channels = {"Code_Lyoko", "SuperCalcConsole"}, packetHandler = PacketHandler.class)
public class CodeLyoko
{
	private static String[] developers = {"986523714", "MoonMagick", "Wolfspirit1st", "JadarMC"};
	
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
	public static Block TowerBlock;// = new BlockLyoko(Lyoko_Tower, 0).setResistance(6000000F).setBlockUnbreakable().setLightValue(7F).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("TowerBlock");
	public static Block TowerBase;// = new BlockTowerBase(Lyoko_Tower_Base, 1, false).setResistance(6000000F).setBlockUnbreakable().setLightValue(7F).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("TowerBase");
	public static Block LyokoGrass;// = new BlockLyoko(Lyoko_Grass, 2).setResistance(6000000F).setBlockUnbreakable().setStepSound(Block.soundGrassFootstep).setUnlocalizedName("LyokoGrass");
	public static Block LyokoStone;// = new BlockLyoko(Lyoko_Stone, 3).setResistance(6000000F).setBlockUnbreakable().setStepSound(Block.soundStoneFootstep).setUnlocalizedName("LyokoStone");
	public static Block LyokoSand;// = new BlockLyoko(Lyoko_Sand, 4).setResistance(6000000F).setBlockUnbreakable().setStepSound(Block.soundSandFootstep).setUnlocalizedName("LyokoSand");
	public static Block LyokoIce;// = new BlockLyokoIce(Lyoko_Ice, 5, Material.glass, false).setResistance(6000000F).setBlockUnbreakable().setLightOpacity(3).setStepSound(Block.soundGlassFootstep).setUnlocalizedName("LyokoIce");
	public static Block LyokoLog;// = new BlockLyoko(Lyoko_Log, 6).setResistance(6000000F).setBlockUnbreakable().setStepSound(Block.soundWoodFootstep).setUnlocalizedName("LyokoLog");
	public static Block LyokoCarthage;// = new BlockLyoko(Lyoko_Carthage, 7).setResistance(6000000F).setBlockUnbreakable().setStepSound(Block.soundMetalFootstep).setUnlocalizedName("LyokoCarthage");
	public static Block QuantumOre;// = new BlockLyoko(Lyoko_Ore, 8).setHardness(10F).setResistance(20F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("LyokoOre");
	public static Block DigitalSeaBlock;// = new BlockDigitalSea(Lyoko_Sea_Block, 9).setResistance(6000000F).setBlockUnbreakable().setUnlocalizedName("DigitalSeaBlock");
	public static Block DigitalSeaFlowing;// = new BlockFlowingDigitalSea(Lyoko_Sea_Flowing, Material.water).setHardness(100F).setLightOpacity(3).setUnlocalizedName("DigitalSeaFlowing").setRequiresSelfNotify();
	public static Block DigitalSeaStill;// = new BlockStationaryDigitalSea(Lyoko_Sea_Still, Material.water).setHardness(100F).setLightOpacity(3).setUnlocalizedName("DigitalSeaStill").setRequiresSelfNotify();
	public static Block LeadOre;// = new BlockLyoko(Lyoko_Lead_Ore, 10).setHardness(10F).setResistance(20F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("LeadOre").setLightValue(10F);
	public static Block VirtualBlock;
	public static Block UraniumOre;
	public static Block Marabounta;
	public static Block TowerConsole;
	public static Block TowerFloor;
	public static Block Cable;
	public static Block Scanner;
	public static Block SuperCalcConsole;
	public static Block LyokoPolarPortal;//  = new BlockLyoko(Lyoko_Polar_Portal, 12).setUnlocalizedName("Polar Portal");
	public static Block LyokoDesertPortal;// = new BlockLyoko(Lyoko_Desert_Portal, 13).setUnlocalizedName("Desert Portal");
	public static Block LyokoForestPortal;// = new BlockLyoko(Lyoko_Forest_Portal, 14).setUnlocalizedName("Forest Portal");
	public static Block LyokoMountainPortal;// = new BlockLyoko(Lyoko_Mountain_Portal, 15).setUnlocalizedName("Mountain Portal");
	public static Block LyokoCarthagePortal;//  = new BlockLyoko(Lyoko_Carthage_Portal, 16).setUnlocalizedName("Carthage Portal");
	public static Block SuperCalc;// = new BlockSuperCalc(Lyoko_Super_Calc).setHardness(20).setResistance(6000000).setUnlocalizedName("Super Computer").setRequiresSelfNotify();
	
	@SidedProxy(clientSide = ModProperties.CLIENT_PROXY, serverSide = ModProperties.COMMON_PROXY)
	public static CommonProxy proxy; //This object will be populated with the class that you choose for the environment
	@Instance
	public static CodeLyoko instance;//the instance of the mod that will be defined, populated, and callable
	
	@PreInit
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
		BlockIds.LYOKO_SEA_BLOCK = config.getBlock("lyokoSeaBlock", BlockIds.LYOKO_SEA_BLOCK_DEFAULT).getInt();
		BlockIds.LYOKO_SEA_FLOWING = config.getBlock("lyokoSeaFlowing", BlockIds.LYOKO_SEA_FLOWING_DEFAULT).getInt();
		BlockIds.LYOKO_SEA_STILL = config.getBlock("lyokoSeaStill", BlockIds.LYOKO_SEA_STILL_DEFAULT).getInt();
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
		BlockIds.CABLE = config.getBlock("cable", BlockIds.CABLE_DEFAULT).getInt();
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
		//ItemIds.NOT_USED4 = config.getItem("itemLyoko4", ItemIds.NOT_USED4_DEFAULT).getInt();
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
		
		config.save();
		
		
		MinecraftForge.EVENT_BUS.register(new SoundHandler());
	}
	
    @Init
    public void CodeLyokoLoad(FMLInitializationEvent evt)
    {
    	ModItems.init();
        
        TowerBlock = new BlockLyokoTower(BlockIds.LYOKO_TOWER).setResistance(6000000F).setBlockUnbreakable().setLightValue(7F).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("TowerBlock");
    	TowerBase = new BlockTowerBase(BlockIds.LYOKO_TOWER_BASE, "towerBase", false).setResistance(6000000F).setBlockUnbreakable().setLightValue(7F).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("TowerBase");
    	LyokoGrass = new BlockLyoko(BlockIds.LYOKO_GRASS).setResistance(6000000F).setBlockUnbreakable().setStepSound(Block.soundGrassFootstep).setUnlocalizedName("LyokoGrass");
    	LyokoStone = new BlockLyoko(BlockIds.LYOKO_STONE).setResistance(6000000F).setBlockUnbreakable().setStepSound(Block.soundStoneFootstep).setUnlocalizedName("LyokoStone");
    	LyokoSand = new BlockLyoko(BlockIds.LYOKO_SAND).setResistance(6000000F).setBlockUnbreakable().setStepSound(Block.soundSandFootstep).setUnlocalizedName("LyokoSand");
    	LyokoIce = new BlockLyokoIce(BlockIds.LYOKO_ICE, "lyokoIce", Material.glass, false).setResistance(6000000F).setBlockUnbreakable().setLightOpacity(3).setStepSound(Block.soundGlassFootstep).setUnlocalizedName("LyokoIce");
    	LyokoLog = new BlockLyoko(BlockIds.LYOKO_LOG).setResistance(6000000F).setBlockUnbreakable().setStepSound(Block.soundWoodFootstep).setUnlocalizedName("LyokoLog");
    	LyokoCarthage = new BlockLyoko(BlockIds.LYOKO_CARTHAGE).setResistance(6000000F).setBlockUnbreakable().setStepSound(Block.soundMetalFootstep).setUnlocalizedName("LyokoCarthage");
    	QuantumOre = new BlockLyoko(BlockIds.LYOKO_ORE).setHardness(10F).setResistance(20F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("LyokoOre");
    	DigitalSeaBlock = new BlockDigitalSea(BlockIds.LYOKO_SEA_BLOCK).setResistance(6000000F).setBlockUnbreakable().setUnlocalizedName("DigitalSeaBlock");
    	DigitalSeaFlowing = new BlockFlowingDigitalSea(BlockIds.LYOKO_SEA_FLOWING, Material.water).setHardness(100F).setLightOpacity(3).setUnlocalizedName("DigitalSeaFlowing");
    	DigitalSeaStill = new BlockStationaryDigitalSea(BlockIds.LYOKO_SEA_STILL, Material.water).setHardness(100F).setLightOpacity(3).setUnlocalizedName("DigitalSeaStill");
    	LeadOre = new BlockLyoko(BlockIds.LYOKO_LEAD_ORE).setHardness(10F).setResistance(20F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("LeadOre").setLightValue(10F);
    	SuperCalc = new BlockSuperCalc(BlockIds.LYOKO_SUPER_CALC).setHardness(20F).setResistance(600F).setUnlocalizedName("Super Computer");
    	VirtualBlock = new BlockLyokoVirtual(BlockIds.LYOKO_VIRTUAL_BLOCK).setResistance(1.0F).setHardness(1.0F).setStepSound(Block.soundGlassFootstep).setUnlocalizedName("LyokoVirtualBlock");
    	UraniumOre = new BlockLyoko(BlockIds.LYOKO_URANIUM_ORE).setHardness(10F).setResistance(20.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("UraniumOre").setLightValue(10F);
    	Marabounta = new BlockMarabounta(BlockIds.LYOKO_MARABOUNTA).setResistance(3.0F).setHardness(10.0F).setUnlocalizedName("MarabountaBlock");
    	TowerConsole = new BlockTowerConsole(BlockIds.TOWER_CONSOLE).setResistance(6000000F).setBlockUnbreakable().setLightValue(7F).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("TowerConsole");
    	TowerFloor = new BlockTowerFloor(BlockIds.TOWER_FLOOR).setResistance(6000000F).setBlockUnbreakable().setLightValue(7F).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("TowerWall");
    	Cable = new BlockCable(BlockIds.CABLE).setResistance(4F).setHardness(1F).setStepSound(Block.soundClothFootstep).setUnlocalizedName("Cable");
    	Scanner = new BlockScanner(BlockIds.SCANNER).setHardness(20F).setResistance(0F).setUnlocalizedName("Scanner");
    	SuperCalcConsole = new BlockSuperCalcConsole(BlockIds.SUPER_CALC_CONSOLE).setResistance(6000000F).setHardness(20.0F).setLightValue(7.0F).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("SuperCalcConsole");
    	
    	LyokoPolarPortal  = new BlockLyoko(BlockIds.LYOKO_ICE_PORTAL).setUnlocalizedName("PolarPortal").setCreativeTab(null);
    	LyokoDesertPortal = new BlockLyoko(BlockIds.LYOKO_DESERT_PORTAL).setUnlocalizedName("DesertPortal").setCreativeTab(null);
    	LyokoForestPortal = new BlockLyoko(BlockIds.LYOKO_FOREST_PORTAL).setUnlocalizedName("ForestPortal").setCreativeTab(null);
    	LyokoMountainPortal = new BlockLyoko(BlockIds.LYOKO_MOUNTAIN_PORTAL).setUnlocalizedName("MountainPortal").setCreativeTab(null);
    	LyokoCarthagePortal  = new BlockLyoko(BlockIds.LYOKO_CARTHAGE_PORTAL).setUnlocalizedName("CarthagePortal").setCreativeTab(null);
    	
    	proxy.registerRenderInformation(); //You have to call the methods in your proxy class
    	proxy.registerTickHandlers();
    	proxy.registerKeyBindingHandler();
    	proxy.registerOreDictionaryOres();
    	
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
    	
    	MinecraftForge.setBlockHarvestLevel(QuantumOre, "pickaxe", 2);
    	MinecraftForge.setBlockHarvestLevel(LeadOre, "pickaxe", 2);
    	MinecraftForge.setBlockHarvestLevel(UraniumOre, "pickaxe", 2);
    	
    	GameRegistry.registerBlock(Scanner, "Scanner");
    	LanguageRegistry.addName(Scanner, "Scanner");
    	GameRegistry.addRecipe(new ItemStack(Scanner, 1), new Object[] {
    		"###", "#*#", "###", Character.valueOf('#'), Item.ingotGold, Character.valueOf('*'), ModItems.QuantumMatrix, Character.valueOf('o'), Block.obsidian
    	});
    	
    	GameRegistry.registerBlock(Cable, "Cable");
    	LanguageRegistry.addName(Cable, "Cable");
    	GameRegistry.addRecipe(new ItemStack(Cable, 12), new Object[] {
    		"###", "$*$", "###", Character.valueOf('#'), Block.cloth, Character.valueOf('*'), ModItems.DataFragment/*Copper*/, Character.valueOf('$'), Item.redstone
    	});
    	
    	GameRegistry.registerBlock(SuperCalcConsole, "Super Computer Console");
    	LanguageRegistry.addName(SuperCalcConsole, "Super Computer Console");
    	
    	GameRegistry.registerBlock(SuperCalc, "Super Computer");
    	LanguageRegistry.addName(SuperCalc, "Super Computer");
    	GameRegistry.addRecipe(new ItemStack(SuperCalc, 1), new Object[] {
    		"#o#", "#*#", "#o#", Character.valueOf('#'), Item.ingotGold, Character.valueOf('*'), ModItems.QuantumMatrix, Character.valueOf('o'), Block.obsidian
    	});
    	
    	LanguageRegistry.addName(ModItems.Overboard, "Overboard");
    	/*GameRegistry.addRecipe(new ItemStack(Overboard, 1), new Object[] {
    		"h*c", "$#$", "bml", Character.valueOf('*'), Item.boat, Character.valueOf('m'), Item.minecartEmpty, Character.valueOf('#'), Item.netherStar,
    		Character.valueOf('$'), DataFragment, Character.valueOf('h'), OddHelmet, Character.valueOf('c'), OddChest,
    		Character.valueOf('l'), OddLegs, Character.valueOf('b'), OddBoots
    	});*/
    	
    	LanguageRegistry.addName(ModItems.Skid, "Skidbladnir");
    	/*GameRegistry.addRecipe(new ItemStack(Skid, 1), new Object[] {
    		"h*c", "$#$", "bml", Character.valueOf('*'), Item.boat, Character.valueOf('m'), Item.minecartEmpty, Character.valueOf('#'), Item.netherStar,
    		Character.valueOf('$'), DataFragment, Character.valueOf('h'), OddHelmet, Character.valueOf('c'), OddChest,
    		Character.valueOf('l'), OddLegs, Character.valueOf('b'), OddBoots
    	});*/
    	
    	LanguageRegistry.addName(ModItems.QuantumMatrix, "Quantum Matrix");
    	GameRegistry.addRecipe(new ItemStack(ModItems.QuantumMatrix, 1), new Object[] {
    		" * ", "*#*", " * ", Character.valueOf('*'), Item.diamond, Character.valueOf('#'), ModItems.QuantumOrb
    	});
    	GameRegistry.addShapelessRecipe(new ItemStack(ModItems.QuantumMatrix, 1), new Object[] {
    	    ModItems.QuantumContainmentCell, ModItems.QuantumOrb
    	});
    	
    	LanguageRegistry.addName(ModItems.QuantumContainmentCell, "Quantum Containment Cell");
    	GameRegistry.addRecipe(new ItemStack(ModItems.QuantumContainmentCell, 1), new Object[] {
    		" * ", "*#*", " * ", Character.valueOf('*'), Item.diamond, Character.valueOf('#'), Block.glass
    	});
    	
    	GameRegistry.registerBlock(TowerFloor, ItemBlockTowerFloor.class, "Tower Floor");
    	LanguageRegistry.addName(TowerFloor, "Tower Floor");
    	
    	GameRegistry.registerBlock(TowerConsole, "Tower Console");
    	LanguageRegistry.addName(TowerConsole, "Tower Console");
    	
    	GameRegistry.registerBlock(Marabounta, "Marabounta");
    	LanguageRegistry.addName(Marabounta, "Marabounta");
    	
    	GameRegistry.registerBlock(VirtualBlock, ItemBlockVirtual.class, "Virtual Block");
    	LanguageRegistry.addName(VirtualBlock, "Virtual Block");
    	
    	//Biomes
    	//GameRegistry.addBiome(lyokocarthage);
    	//GameRegistry.addBiome(lyokodesert);
    	GameRegistry.registerBlock(LyokoCarthagePortal, "Carthage Portal");
    	LanguageRegistry.addName(LyokoCarthagePortal, "Carthage Portal");
    	
    	GameRegistry.registerBlock(LyokoPolarPortal, "LyokoPolarPortal");
    	LanguageRegistry.addName(LyokoPolarPortal, "Ice Portal");
    	
    	GameRegistry.registerBlock(LyokoDesertPortal, "Desert Portal");
    	LanguageRegistry.addName(LyokoDesertPortal, "Desert Portal");
    	
    	GameRegistry.registerBlock(LyokoForestPortal, "Forest Portal");
    	LanguageRegistry.addName(LyokoForestPortal, "Forest Portal");
    	
    	GameRegistry.registerBlock(LyokoMountainPortal, "Mountain Portal");
    	LanguageRegistry.addName(LyokoMountainPortal, "Mountain Portal");
    	
    	LanguageRegistry.addName(ModItems.DataFragment, "Data Fragment");
    	
    	LanguageRegistry.addName(ModItems.Uranium, "Uranium");
    	GameRegistry.addSmelting(UraniumOre.blockID, new ItemStack(ModItems.Uranium, 1), 5F);
    	
    	LanguageRegistry.addName(ModItems.LyokoUraniumCell, "Uranium Fuel Cell");
    	CraftingManager.getInstance().getRecipeList().add(new ShapelessOreRecipe(new ItemStack(ModItems.LyokoUraniumCell),"ingotUranium",
    	        ModItems.LyokoCell));
    	CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(ModItems.LyokoUraniumCell, 2),"*#*",
    			Character.valueOf('*'), ModItems.LyokoDepletedUraniumCell, Character.valueOf('#'), "ingotUranium"));
    	
    	LanguageRegistry.addName(ModItems.LyokoDepletedUraniumCell, "Depleted Uranium Fuel Cell");
    	
    	LanguageRegistry.addName(ModItems.LyokoLead, "Lead Isotope 210");
    	GameRegistry.addSmelting(LeadOre.blockID, new ItemStack(ModItems.LyokoLead, 1), 5F);
    	
    	LanguageRegistry.addName(ModItems.LyokoCell, "Empty Fuel Cell");
    	GameRegistry.addRecipe(new ItemStack(ModItems.LyokoCell, 16), new Object[] {
    		" # ", "#*#", " # ", Character.valueOf('*'), Block.glass, Character.valueOf('#'), Item.ingotIron
    	});
    	GameRegistry.addShapelessRecipe(new ItemStack(ModItems.LyokoCell, 1), new Object[] {
    	    ModItems.LyokoDepletedLeadCell
    	});
    	GameRegistry.addShapelessRecipe(new ItemStack(ModItems.LyokoCell, 1), new Object[] {
    	    ModItems.LyokoDepletedUraniumCell
    	});
    	
    	LanguageRegistry.addName(ModItems.LyokoLeadCell, "Lead Isotope 210 Fuel Cell");
    	CraftingManager.getInstance().getRecipeList().add(new ShapelessOreRecipe(new ItemStack(ModItems.LyokoLeadCell),"ingotRadioactiveLead",
    	        ModItems.LyokoCell));
    	CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(ModItems.LyokoLeadCell, 2),"*#*",
    			Character.valueOf('*'), ModItems.LyokoDepletedLeadCell, Character.valueOf('#'), "ingotRadioactiveLead"));
    	
    	LanguageRegistry.addName(ModItems.LyokoDepletedLeadCell, "Depleted Lead Isotope 210 Fuel Cell");
    	
    	LanguageRegistry.addName(ModItems.QuantumOrb, "Quantum Orb");
    	GameRegistry.addSmelting(QuantumOre.blockID, new ItemStack(ModItems.QuantumOrb, 1), 5F);
    	
    	LanguageRegistry.addName(ModItems.AelitaHelmet, "Aelita's Helmet");
    	LanguageRegistry.addName(ModItems.AelitaChest, "Aelita's Chestplate");
    	LanguageRegistry.addName(ModItems.AelitaLegs, "Aelita's Leggings");
    	LanguageRegistry.addName(ModItems.AelitaBoots, "Aelita's Boots");
    	GameRegistry.addRecipe(new ItemStack(ModItems.AelitaHelmet, 1), new Object[] {
    		"***", "*#*", Character.valueOf('*'), ModItems.DataFragment, Character.valueOf('#'), new ItemStack(Item.dyePowder, 1, 9)
    	});
    	GameRegistry.addRecipe(new ItemStack(ModItems.AelitaChest, 1), new Object[] {
    		"*#*", "***", "***", Character.valueOf('*'), ModItems.DataFragment, Character.valueOf('#'), new ItemStack(Item.dyePowder, 1, 9)
    	});
    	GameRegistry.addRecipe(new ItemStack(ModItems.AelitaLegs, 1), new Object[] {
    		"***", "*#*", "* *", Character.valueOf('*'), ModItems.DataFragment, Character.valueOf('#'), new ItemStack(Item.dyePowder, 1, 9)
    	});
    	GameRegistry.addRecipe(new ItemStack(ModItems.AelitaBoots, 1), new Object[] {
    		"*#*", "* *", Character.valueOf('*'), ModItems.DataFragment, Character.valueOf('#'), new ItemStack(Item.dyePowder, 1, 9)
    	});
    	
    	LanguageRegistry.addName(ModItems.OddHelmet, "Odd's Helmet");
    	LanguageRegistry.addName(ModItems.OddChest, "Odd's Chestplate");
    	LanguageRegistry.addName(ModItems.OddLegs, "Odd's Leggings");
    	LanguageRegistry.addName(ModItems.OddBoots, "Odd's Boots");
    	GameRegistry.addRecipe(new ItemStack(ModItems.OddHelmet, 1), new Object[] {
    		"***", "*#*", Character.valueOf('*'), ModItems.DataFragment, Character.valueOf('#'), new ItemStack(Item.dyePowder, 1, 13)
    	});
    	GameRegistry.addRecipe(new ItemStack(ModItems.OddChest, 1), new Object[] {
    		"*#*", "***", "***", Character.valueOf('*'), ModItems.DataFragment, Character.valueOf('#'), new ItemStack(Item.dyePowder, 1, 13)
    	});
    	GameRegistry.addRecipe(new ItemStack(ModItems.OddLegs, 1), new Object[] {
    		"***", "*#*", "* *", Character.valueOf('*'), ModItems.DataFragment, Character.valueOf('#'), new ItemStack(Item.dyePowder, 1, 13)
    	});
    	GameRegistry.addRecipe(new ItemStack(ModItems.OddBoots, 1), new Object[] {
    		"*#*", "* *", Character.valueOf('*'), ModItems.DataFragment, Character.valueOf('#'), new ItemStack(Item.dyePowder, 1, 13)
    	});
    	
    	LanguageRegistry.addName(ModItems.UlrichHelmet, "Ulrich's Helmet");
    	LanguageRegistry.addName(ModItems.UlrichChest, "Ulrich's Chestplate");
    	LanguageRegistry.addName(ModItems.UlrichLegs, "Ulrich's Leggings");
    	LanguageRegistry.addName(ModItems.UlrichBoots, "Ulrich's Boots");
    	GameRegistry.addRecipe(new ItemStack(ModItems.UlrichHelmet, 1), new Object[] {
    		"***", "*#*", Character.valueOf('*'), ModItems.DataFragment, Character.valueOf('#'), new ItemStack(Item.dyePowder, 1, 11)
    	});
    	GameRegistry.addRecipe(new ItemStack(ModItems.UlrichChest, 1), new Object[] {
    		"*#*", "***", "***", Character.valueOf('*'), ModItems.DataFragment, Character.valueOf('#'), new ItemStack(Item.dyePowder, 1, 11)
    	});
    	GameRegistry.addRecipe(new ItemStack(ModItems.UlrichLegs, 1), new Object[] {
    		"***", "*#*", "* *", Character.valueOf('*'), ModItems.DataFragment, Character.valueOf('#'), new ItemStack(Item.dyePowder, 1, 11)
    	});
    	GameRegistry.addRecipe(new ItemStack(ModItems.UlrichBoots, 1), new Object[] {
    		"*#*", "* *", Character.valueOf('*'), ModItems.DataFragment, Character.valueOf('#'), new ItemStack(Item.dyePowder, 1, 11)
    	});
    	
    	LanguageRegistry.addName(ModItems.YumiHelmet, "Yumi's Helmet");
    	LanguageRegistry.addName(ModItems.YumiChest, "Yumi's Chestplate");
    	LanguageRegistry.addName(ModItems.YumiLegs, "Yumi's Leggings");
    	LanguageRegistry.addName(ModItems.YumiBoots, "Yumi's Boots");
    	GameRegistry.addRecipe(new ItemStack(ModItems.YumiHelmet, 1), new Object[] {
    		"***", "*#*", Character.valueOf('*'), ModItems.DataFragment, Character.valueOf('#'), new ItemStack(Item.dyePowder, 1, 5)
    	});
    	GameRegistry.addRecipe(new ItemStack(ModItems.YumiChest, 1), new Object[] {
    		"*#*", "***", "***", Character.valueOf('*'), ModItems.DataFragment, Character.valueOf('#'), new ItemStack(Item.dyePowder, 1, 5)
    	});
    	GameRegistry.addRecipe(new ItemStack(ModItems.YumiLegs, 1), new Object[] {
    		"***", "*#*", "* *", Character.valueOf('*'), ModItems.DataFragment, Character.valueOf('#'), new ItemStack(Item.dyePowder, 1, 5)
    	});
    	GameRegistry.addRecipe(new ItemStack(ModItems.YumiBoots, 1), new Object[] {
    		"*#*", "* *", Character.valueOf('*'), ModItems.DataFragment, Character.valueOf('#'), new ItemStack(Item.dyePowder, 1, 5)
    	});
    	
    	LanguageRegistry.addName(ModItems.WilliamHelmet, "William's Helmet");
    	LanguageRegistry.addName(ModItems.WilliamChest, "William's Chestplate");
    	LanguageRegistry.addName(ModItems.WilliamLegs, "William's Leggings");
    	LanguageRegistry.addName(ModItems.WilliamBoots, "William's Boots");
    	GameRegistry.addRecipe(new ItemStack(ModItems.WilliamHelmet, 1), new Object[] {
    		"***", "*#*", Character.valueOf('*'), ModItems.DataFragment, Character.valueOf('#'), new ItemStack(Item.dyePowder, 1, 12)
    	});
    	GameRegistry.addRecipe(new ItemStack(ModItems.WilliamChest, 1), new Object[] {
    		"*#*", "***", "***", Character.valueOf('*'), ModItems.DataFragment, Character.valueOf('#'), new ItemStack(Item.dyePowder, 1, 12)
    	});
    	GameRegistry.addRecipe(new ItemStack(ModItems.WilliamLegs, 1), new Object[] {
    		"***", "*#*", "* *", Character.valueOf('*'), ModItems.DataFragment, Character.valueOf('#'), new ItemStack(Item.dyePowder, 1, 12)
    	});
    	GameRegistry.addRecipe(new ItemStack(ModItems.WilliamBoots, 1), new Object[] {
    		"*#*", "* *", Character.valueOf('*'), ModItems.DataFragment, Character.valueOf('#'), new ItemStack(Item.dyePowder, 1, 12)
    	});
    	
    	LanguageRegistry.addName(ModItems.Katana, "Katana");
    	/*GameRegistry.addRecipe(new ItemStack(Katana, 1), new Object[] {
    		" * ", " * ", " # ", Character.valueOf('*'), LyokoIngot, Character.valueOf('#'), Item.ingotIron
    	});*/
    	
    	LanguageRegistry.addName(ModItems.Zweihander, "Zweihander");
    	/*GameRegistry.addRecipe(new ItemStack(Zweihander, 1), new Object[] {
    		"***", "***", " # ", Character.valueOf('*'), LyokoIngot, Character.valueOf('#'), Item.ingotIron
    	});*/
    	
    	LanguageRegistry.addName(ModItems.Fan, "Fan");
    	/*GameRegistry.addRecipe(new ItemStack(Fan, 1), new Object[] {
    		"#f ", "#*f", "###", Character.valueOf('*'), LyokoIngot, Character.valueOf('#'), Item.stick, Character.valueOf('f'), Item.feather
    	});*/
    	
    	LanguageRegistry.addName(ModItems.EnergyField, "Energy Field");
    	/*GameRegistry.addRecipe(new ItemStack(EnergyField, 1), new Object[] {
    		" * ", "*#*", " * ", Character.valueOf('*'), LyokoIngot, Character.valueOf('#'), Item.netherStar
    	});*/
    	
    	LanguageRegistry.addName(ModItems.Glove, "Laser Arrow Glove");
    	/*GameRegistry.addRecipe(new ItemStack(Glove, 1), new Object[] {
    		"###", "#a#", "#*#", Character.valueOf('*'), LyokoIngot, Character.valueOf('#'), Item.leather, Character.valueOf('a'), LaserArrow
    	});*/
    	
    	LanguageRegistry.addName(ModItems.LaserArrow, "Laser Arrow");
    	/*GameRegistry.addRecipe(new ItemStack(LaserArrow, 1), new Object[] {
    		"a*a", "*#*", "a*a", Character.valueOf('*'), LyokoIngot, Character.valueOf('#'), Item.netherStar, Character.valueOf('a'), Item.arrow
    	});*/
    	
    	GameRegistry.registerBlock(LeadOre, ItemBlockEffect.class,"Lead Isotope 210 Ore");
    	LanguageRegistry.addName(LeadOre, "Lead Isotope 210 Ore");
    	
    	GameRegistry.registerBlock(UraniumOre, ItemBlockEffect.class,"Uranium Ore");
    	LanguageRegistry.addName(UraniumOre, "Uranium Ore");
    			
    	GameRegistry.registerBlock(TowerBlock,"Tower");
    	LanguageRegistry.addName(TowerBlock, "Tower");
    	
    	GameRegistry.registerBlock(TowerBase, "Tower Base");
    	LanguageRegistry.addName(TowerBase, "Tower Base");
    	
    	GameRegistry.registerBlock(LyokoGrass, "Lyoko Grass");
    	LanguageRegistry.addName(LyokoGrass, "Lyoko Grass");
    	
    	GameRegistry.registerBlock(LyokoStone, "Lyoko Stone");
    	LanguageRegistry.addName(LyokoStone, "Lyoko Stone");
    	
    	GameRegistry.registerBlock(LyokoSand, "Lyoko Sand");
    	LanguageRegistry.addName(LyokoSand, "Lyoko Sand");
    	
    	GameRegistry.registerBlock(LyokoIce, "Lyoko Ice");
    	LanguageRegistry.addName(LyokoIce, "Lyoko Ice");
    	
    	GameRegistry.registerBlock(LyokoLog, "Lyoko Log");
    	LanguageRegistry.addName(LyokoLog, "Lyoko Log");
    	
    	GameRegistry.registerBlock(DigitalSeaBlock, "Digital Sea Block");
    	LanguageRegistry.addName(DigitalSeaBlock, "Digital Sea Block");
    	
    	GameRegistry.registerBlock(LyokoCarthage, "Carthage Block");
    	LanguageRegistry.addName(LyokoCarthage, "Carthage Block");
    	
    	GameRegistry.registerBlock(QuantumOre, ItemBlockEffect.class, "Quantum Ore");
    	LanguageRegistry.addName(QuantumOre, "Quantum Ore");
    	
    	GameRegistry.registerBlock(DigitalSeaFlowing, "Digital Sea Flowing");
    	LanguageRegistry.addName(DigitalSeaFlowing, "Digital Sea Flowing");
    	
    	GameRegistry.registerBlock(DigitalSeaStill, "Digital Sea Still");
    	LanguageRegistry.addName(DigitalSeaStill, "Digital Sea Still");
    	
    	
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

    	proxy.addChestLoot();
    	proxy.registerEntities();
    	proxy.registerEntityNames();
    }
    
    @PostInit
    public void CodeLyokoPostLoad(FMLPostInitializationEvent postevt)
    {
    	
    }
    
    public static String[] getDevelopers()
    {
    	return developers;
    }
    
    public static DamageSource causeLyokoRangedDamage(EntityLyokoRanged par0EntityLaserArrow, Entity par1Entity)
    {
        return (new EntityDamageSourceIndirect("lyokoRanged", par0EntityLaserArrow, par1Entity)).setProjectile();
    }
    
    public static DamageSource causeLaserDamage(EntityLaser par0EntityLaser, Entity par1Entity)
    {
        return (new EntityDamageSourceIndirect("laser", par0EntityLaser, par1Entity)).setProjectile();
    }
}