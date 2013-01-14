package matt.lyoko;

import java.util.Random;
import matt.lyoko.world.*;
import matt.lyoko.world.portals.*;
import net.minecraftforge.common.*;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.*;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.*;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.*;
import net.minecraft.src.*;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.entity.player.*;

@Mod(modid = "CodeLyoko", name="Code Lyoko", version="0.3.1-Alpha")
@NetworkMod
(
clientSideRequired = true,
serverSideRequired = false,
channels = {"Code_Lyoko"},
packetHandler = PacketHandler.class
)
public class CodeLyoko
{
	@MLProp public static int Lyoko_Tower = 1154;
	@MLProp public static int Lyoko_Tower_Base = 1155;
	@MLProp public static int Lyoko_Grass = 1156;
	@MLProp public static int Lyoko_Stone = 1157;
	@MLProp public static int Lyoko_Sand = 1158;
	@MLProp public static int Lyoko_Ice = 1159;
	@MLProp public static int Lyoko_Log = 1160;
	@MLProp public static int Lyoko_Sea_Block = 1161;
	@MLProp public static int Lyoko_Sea_Flowing = 1162;
	@MLProp public static int Lyoko_Sea_Still = 1163;
	@MLProp public static int Lyoko_Sea_Bucket = 1164;
	@MLProp public static int Lyoko_Carthage = 1165;
	@MLProp public static int Lyoko_Ore = 1166;
	@MLProp public static int Lyoko_Polar_Portal = 1167;
	@MLProp public static int Lyoko_Desert_Portal = 1168;
	@MLProp public static int Lyoko_Forest_Portal = 1169;
	@MLProp public static int Lyoko_Mountain_Portal = 1170;
	@MLProp public static int Lyoko_Carthage_Portal = 1171;
	@MLProp public static int Lyoko_Over_Portal = 1172;
	@MLProp public static int Lyoko_Super_Calc = 1173;
	@MLProp public static int Lyoko_Lead_Ore = 1174;
	
	@MLProp public static int Weapon_Lyoko_1 = 6081;
	@MLProp public static int Weapon_Lyoko_2 = 6082;
	@MLProp public static int Weapon_Lyoko_3 = 6083;
	@MLProp public static int Weapon_Lyoko_4 = 6084;
	@MLProp public static int Weapon_Lyoko_5 = 6085;
	@MLProp public static int Weapon_Lyoko_6 = 6086;
	@MLProp public static int Item_Lyoko_1 = 6087;
	@MLProp public static int Item_Lyoko_2 = 6088;
	@MLProp public static int Item_Lyoko_3 = 6089;
	@MLProp public static int Item_Lyoko_4 = 6090;
	@MLProp public static int Item_Lyoko_5 = 6091;
	@MLProp public static int Item_Lyoko_6 = 6092;
	@MLProp public static int Item_Lyoko_7 = 6093;
	@MLProp public static int Item_Lyoko_8 = 6094;
	@MLProp public static int Item_Lyoko_9 = 6095;
	@MLProp public static int Item_Lyoko_10 = 6096;
	@MLProp public static int Item_Lyoko_Super_Calc = 6097;
	@MLProp public static int Item_Lyoko_11 = 6098;
	@MLProp public static int Item_Lyoko_12 = 6099;
	@MLProp public static int Item_Lyoko_13 = 6100;
	@MLProp public static int Item_Lyoko_14 = 6101;
	@MLProp public static int Item_Lyoko_15 = 6102;
	@MLProp public static int Aelita_Armor_Helmet = 6103;
	@MLProp public static int Aelita_Armor_Chest = 6104;
	@MLProp public static int Aelita_Armor_Pants = 6105;
	@MLProp public static int Aelita_Armor_Boots = 6106;
	@MLProp public static int Odd_Armor_Helmet = 6107;
	@MLProp public static int Odd_Armor_Chest = 6108;
	@MLProp public static int Odd_Armor_Pants = 6109;
	@MLProp public static int Odd_Armor_Boots = 6110;
	@MLProp public static int Ulrich_Armor_Helmet = 6111;
	@MLProp public static int Ulrich_Armor_Chest = 6112;
	@MLProp public static int Ulrich_Armor_Pants = 6113;
	@MLProp public static int Ulrich_Armor_Boots = 6114;
	@MLProp public static int Yumi_Armor_Helmet = 6115;
	@MLProp public static int Yumi_Armor_Chest = 6116;
	@MLProp public static int Yumi_Armor_Pants = 6117;
	@MLProp public static int Yumi_Armor_Boots = 6118;
	@MLProp public static int William_Armor_Helmet = 6119;
	@MLProp public static int William_Armor_Chest = 6120;
	@MLProp public static int William_Armor_Pants = 6121;
	@MLProp public static int William_Armor_Boots = 6122;

	public static int SuperCalcModelID;
	static EnumToolMaterial toolLYOKO = EnumHelper.addToolMaterial("LYOKO", 3, 300, 14F, 30, 30);
	
	static EnumArmorMaterial armorLYOKO = EnumHelper.addArmorMaterial("LYOKO", 40, new int[] {5, 10, 8, 5}, 30);
	
	public static CreativeTabs LyokoTabs = new LyokoTab("LyokoTabs");
	public static int Carthage = 7;
	public static int Polar = 3;
	public static final BiomeGenBase lyokomountain = ((BiomeGenBaseLyoko) (new BiomeGenMountainSector(9)).setColor(8421631)).setLyokoBiomeName("Mountain Sector");
	public static final BiomeGenBase lyokoforest = ((BiomeGenBaseLyoko) (new BiomeGenForestSector(10)).setColor(8421631)).setLyokoBiomeName("Forest Sector");
	public static final BiomeGenBase lyokodesert = ((BiomeGenBaseLyoko) (new BiomeGenDesertSector(11)).setColor(8421631)).setLyokoBiomeName("Desert Sector");
	public static final BiomeGenBase lyokopolar = ((BiomeGenBaseLyoko) (new BiomeGenPolarSector(12)).setColor(8421631)).setLyokoBiomeName("Polar Sector");
	public static final BiomeGenBase lyokocarthage = ((BiomeGenBaseLyoko) (new BiomeGenCarthageSector(13)).setColor(8421631)).setLyokoBiomeName("Carthage Sector");
	public static final Item Katana = new ItemLyokoSword(Weapon_Lyoko_1, toolLYOKO).setItemName("Katana").setCreativeTab(CreativeTabs.tabMisc).setIconIndex(0);
	public static final Item Zweihander = new ItemLyokoSword(Weapon_Lyoko_2, toolLYOKO).setItemName("Zweihander").setCreativeTab(CreativeTabs.tabMisc).setIconIndex(1);
	public static final Item Fan = new ItemLyokoFan(Weapon_Lyoko_3).setItemName("Fan").setCreativeTab(CreativeTabs.tabMisc).setIconIndex(2);
	public static final Item EnergyField = new ItemLyokoEnergyField(Weapon_Lyoko_4).setItemName("EnergyField").setCreativeTab(CreativeTabs.tabMisc).setIconIndex(3);
	public static final Item Glove = new ItemGlove(Weapon_Lyoko_5).setItemName("Glove").setCreativeTab(CreativeTabs.tabMisc).setIconIndex(4);
	public static final Item LaserArrow = new ItemLyoko(Weapon_Lyoko_6).setItemName("LaserArrow").setCreativeTab(CreativeTabs.tabMisc).setIconIndex(5);
	public static final Item KatanaFragment1 = new ItemLyoko(Item_Lyoko_1).setItemName("KatanaFragment1").setCreativeTab(CreativeTabs.tabMisc).setIconIndex(6);
	public static final Item KatanaFragment2 = new ItemLyoko(Item_Lyoko_2).setItemName("KatanaFragment2").setCreativeTab(CreativeTabs.tabMisc).setIconIndex(7);
	public static final Item ZweihanderFragment1 = new ItemLyoko(Item_Lyoko_3).setItemName("ZweihanderFragment1").setCreativeTab(CreativeTabs.tabMisc).setIconIndex(8);
	public static final Item ZweihanderFragment2 = new ItemLyoko(Item_Lyoko_4).setItemName("ZweihanderFragment2").setCreativeTab(CreativeTabs.tabMisc).setIconIndex(9);
	public static final Item FanFragment1 = new ItemLyoko(Item_Lyoko_5).setItemName("FanFragment1").setCreativeTab(CreativeTabs.tabMisc).setIconIndex(10);
	public static final Item FanFragment2 = new ItemLyoko(Item_Lyoko_6).setItemName("FanFragment2").setCreativeTab(CreativeTabs.tabMisc).setIconIndex(11);
	public static final Item EnergyFieldCore = new ItemLyoko(Item_Lyoko_7).setItemName("EnergyFieldCore").setCreativeTab(CreativeTabs.tabMisc).setIconIndex(12);
	public static final Item EnergyFieldStarter = new ItemLyoko(Item_Lyoko_8).setItemName("EnergyFieldStarter").setCreativeTab(CreativeTabs.tabMisc).setIconIndex(13);
	public static final Item GloveFragment1 = new ItemLyoko(Item_Lyoko_9).setItemName("GloveFragment1").setCreativeTab(CreativeTabs.tabMisc).setIconIndex(14);
	public static final Item GloveFragment2 = new ItemLyoko(Item_Lyoko_10).setItemName("GloveFragment2").setCreativeTab(CreativeTabs.tabMisc).setIconIndex(15);
	public static final Item LyokoIngot = new ItemLyoko(Item_Lyoko_11).setItemName("LyokoIngot").setCreativeTab(CreativeTabs.tabMisc).setIconIndex(16);
	public static final Item LyokoLead = new ItemLyoko(Item_Lyoko_12).setItemName("Lead210").setCreativeTab(CreativeTabs.tabMisc).setIconIndex(17);
	public static final Item LyokoCell = new ItemLyoko(Item_Lyoko_13).setItemName("Cell").setCreativeTab(CreativeTabs.tabMisc).setIconIndex(18);
	public static final Item LyokoLeadCell = new ItemLyokoFuel(Item_Lyoko_14, 10000).setItemName("Lead210Cell").setCreativeTab(CreativeTabs.tabMisc).setIconIndex(19);
	public static final Item LyokoDepletedLeadCell = new ItemLyoko(Item_Lyoko_15).setItemName("DepletedLead210Cell").setCreativeTab(CreativeTabs.tabMisc).setIconIndex(20);
	
	public static final Item AelitaHelmet = new ArmorLyoko(Aelita_Armor_Helmet, armorLYOKO, 5, 0, "Aelita").setIconIndex(21).setItemName("AelitaHelmet");
    	public static final Item AelitaChest = new ArmorLyoko(Aelita_Armor_Chest, armorLYOKO, 5, 1, "Aelita").setIconIndex(22).setItemName("AelitaChest");
    	public static final Item AelitaLegs = new ArmorLyoko(Aelita_Armor_Pants, armorLYOKO, 5, 2, "Aelita").setIconIndex(23).setItemName("AelitaPants");
    	public static final Item AelitaBoots = new ArmorLyoko(Aelita_Armor_Boots, armorLYOKO, 5, 3, "Aelita").setIconIndex(24).setItemName("AelitaBoots");
    	public static final Item OddHelmet = new ArmorLyoko(Odd_Armor_Helmet, armorLYOKO, 6, 0, "Odd").setIconIndex(25).setItemName("OddHelmet");
    	public static final Item OddChest = new ArmorLyoko(Odd_Armor_Chest, armorLYOKO, 6, 1, "Odd").setIconIndex(26).setItemName("OddChest");
    	public static final Item OddLegs = new ArmorLyoko(Odd_Armor_Pants, armorLYOKO, 6, 2, "Odd").setIconIndex(27).setItemName("OddPants");
    	public static final Item OddBoots = new ArmorLyoko(Odd_Armor_Boots, armorLYOKO, 6, 3, "Odd").setIconIndex(28).setItemName("OddBoots");
    
    	public static final Item UlrichHelmet = new ArmorLyoko(Ulrich_Armor_Helmet, armorLYOKO, 7, 0, "Ulrich").setIconIndex(29).setItemName("UlrichHelmet");
    	public static final Item UlrichChest = new ArmorLyoko(Ulrich_Armor_Chest, armorLYOKO, 7, 1, "Ulrich").setIconIndex(30).setItemName("UlrichChest");
    	public static final Item UlrichLegs = new ArmorLyoko(Ulrich_Armor_Pants, armorLYOKO, 7, 2, "Ulrich").setIconIndex(31).setItemName("UlrichPants");
    	public static final Item UlrichBoots = new ArmorLyoko(Ulrich_Armor_Boots, armorLYOKO, 7, 3, "Ulrich").setIconIndex(32).setItemName("UlrichBoots");
    
    	public static final Item YumiHelmet = new ArmorLyoko(Yumi_Armor_Helmet, armorLYOKO, 8, 0, "Yumi").setIconIndex(33).setItemName("YumiHelmet");
    	public static final Item YumiChest = new ArmorLyoko(Yumi_Armor_Chest, armorLYOKO, 8, 1, "Yumi").setIconIndex(34).setItemName("YumiChest");
    	public static final Item YumiLegs = new ArmorLyoko(Yumi_Armor_Pants, armorLYOKO, 8, 2, "Yumi").setIconIndex(35).setItemName("YumiPants");
    	public static final Item YumiBoots = new ArmorLyoko(Yumi_Armor_Boots, armorLYOKO, 8, 3, "Yumi").setIconIndex(36).setItemName("YumiBoots");
    
    	public static final Item WilliamHelmet = new ArmorLyoko(William_Armor_Helmet, armorLYOKO, 9, 0, "William").setIconIndex(37).setItemName("WilliamHelmet");
    	public static final Item WilliamChest = new ArmorLyoko(William_Armor_Chest, armorLYOKO, 9, 1, "William").setIconIndex(38).setItemName("WilliamChest");
    	public static final Item WilliamLegs = new ArmorLyoko(William_Armor_Pants, armorLYOKO, 9, 2, "William").setIconIndex(39).setItemName("WilliamPants");
    	public static final Item WilliamBoots = new ArmorLyoko(William_Armor_Boots, armorLYOKO, 9, 3, "William").setIconIndex(40).setItemName("WilliamBoots");
	
	public static final Block TowerBlock = new BlockLyoko(Lyoko_Tower, 0).setResistance(6000000F).setBlockUnbreakable().setLightValue(7F).setStepSound(Block.soundMetalFootstep).setBlockName("TowerBlock");
	public static final Block TowerBase = new BlockTowerBase(Lyoko_Tower_Base, 1, false).setResistance(6000000F).setBlockUnbreakable().setLightValue(7F).setStepSound(Block.soundMetalFootstep).setBlockName("TowerBase");
	public static final Block LyokoGrass = new BlockLyoko(Lyoko_Grass, 2).setResistance(6000000F).setBlockUnbreakable().setStepSound(Block.soundGrassFootstep).setBlockName("TowerGrass");
	public static final Block LyokoStone = new BlockLyoko(Lyoko_Stone, 3).setResistance(6000000F).setBlockUnbreakable().setStepSound(Block.soundStoneFootstep).setBlockName("TowerStone");
	public static final Block LyokoSand = new BlockLyoko(Lyoko_Sand, 4).setResistance(6000000F).setBlockUnbreakable().setStepSound(Block.soundSandFootstep).setBlockName("TowerSand");
	public static final Block LyokoIce = new BlockLyokoIce(Lyoko_Ice, 5, Material.glass, false).setResistance(6000000F).setBlockUnbreakable().setLightOpacity(3).setStepSound(Block.soundGlassFootstep).setBlockName("TowerIce");
	public static final Block LyokoLog = new BlockLyoko(Lyoko_Log, 6).setResistance(6000000F).setBlockUnbreakable().setStepSound(Block.soundWoodFootstep).setBlockName("LyokoLog");
	public static final Block LyokoCarthage = new BlockLyoko(Lyoko_Carthage, 7).setResistance(6000000F).setBlockUnbreakable().setStepSound(Block.soundMetalFootstep).setBlockName("LyokoCarthage");
	public static final Block LyokoOre = new BlockLyoko(Lyoko_Ore, 8).setHardness(10F).setResistance(20F).setStepSound(Block.soundStoneFootstep).setBlockName("LyokoOre");
	public static final Block DigitalSeaBlock = new BlockDigitalSea(Lyoko_Sea_Block, 9).setResistance(6000000F).setBlockUnbreakable().setBlockName("DigitalSeaBlock").setCreativeTab(CreativeTabs.tabMisc);
	public static final Block DigitalSeaFlowing = new BlockFlowingDigitalSea(Lyoko_Sea_Flowing, Material.water).setHardness(100F).setLightOpacity(3).setBlockName("DigitalSeaFlowing").setRequiresSelfNotify();
	public static final Block DigitalSeaStill = new BlockStationaryDigitalSea(Lyoko_Sea_Still, Material.water).setHardness(100F).setLightOpacity(3).setBlockName("DigitalSeaStill").setRequiresSelfNotify();
	public static final Block LeadOre = new BlockLyoko(Lyoko_Lead_Ore, 10).setHardness(10F).setResistance(20F).setStepSound(Block.soundStoneFootstep).setBlockName("LeadOre").setLightValue(10F);
	//public static final Block LyokoPolarPortal  = new LyokoPolarPortal(Lyoko_Polar_Portal, 12).setBlockName("Polar Portal");
	//public static final Block LyokoDesertPortal = new LyokoDesertPortal(Lyoko_Desert_Portal, 13).setBlockName("Desert Portal");
	//public static final Block LyokoForestPortal = new LyokoForestPortal(Lyoko_Forest_Portal, 14).setBlockName("Forest Portal");
	//public static final Block LyokoMountainPortal = new LyokoMountainPortal(Lyoko_Mountain_Portal, 15).setBlockName("Mountain Portal");
	public static final Block LyokoCarthagePortal  = new LyokoCarthagePortal(Lyoko_Carthage_Portal, 16).setBlockName("Carthage Portal");
	public static final Block SuperCalc = new BlockSuperCalc(Lyoko_Super_Calc).setHardness(20).setResistance(6000000).setBlockName("Super Computer").setRequiresSelfNotify();
	//public static final Item ItemSuperCalc = new ItemLyoko(Item_Lyoko_Super_Calc).setItemName("Item Super Computer").setCreativeTab(CreativeTabs.tabMisc).setIconIndex(0).setTextureFile("/matt/lyoko/terrain/SuperCalculator.png");
	
	@SidedProxy(clientSide = "matt.lyoko.ClientProxy", serverSide = "matt.lyoko.CommonProxy")
	public static CommonProxy proxy; //This object will be populated with the class that you choose for the environment
	@Instance
	public static CodeLyoko instance;//the instance of the mod that will be defined, populated, and callable
	
    @Init
    public void CodeLyokoLoad(FMLInitializationEvent evt)
    {
    	//TODO Give mod owners special ability
    	//Matthew = Aelita
    	//Marq = Odd
    	//Andrew = Odd (Jeremy)
    	proxy.registerRenderInformation(); //You have to call the methods in your proxy class
    	
    	NetworkRegistry.instance().registerGuiHandler(this, new GuiHandler());
    	
    	//RenderSuperCalc render = new RenderSuperCalc();
    	//GameRegistry.registerTileEntity(TileEntitySuperCalc.class, "tesc");
    	
    	GameRegistry.registerWorldGenerator(new WorldGenLyokoOre());
    	
    	MinecraftForge.setBlockHarvestLevel(LyokoOre, "pickaxe", 2);
    	MinecraftForge.setBlockHarvestLevel(LeadOre, "pickaxe", 2);
    	
    	GameRegistry.registerBlock(SuperCalc, "Super Computer");
    	LanguageRegistry.addName(SuperCalc, "Super Computer");
    	//GameRegistry.addRecipe(new ItemStack(ItemSuperCalc, 1), new Object[] {
    	//	" * ", " * ", "***", Character.valueOf('*'), Block.glass
    	//});
    	
    	//Biomes
    	//GameRegistry.addBiome(lyokocarthage);
    	//GameRegistry.addBiome(lyokodesert);
    	/*
    	GameRegistry.registerBlock(LyokoCarthagePortal, "Carthage Portal");
    	LanguageRegistry.addName(LyokoCarthagePortal, "Carthage Portal");
    	
    	GameRegistry.registerBlock(LyokoOverPortal);
    	LanguageRegistry.addName(LyokoOverPortal, "OverWorld Portal");
    	
    	GameRegistry.registerBlock(LyokoPolarPortal);
    	LanguageRegistry.addName(LyokoPolarPortal, "Ice Portal");
    	
    	GameRegistry.registerBlock(LyokoDesertPortal);
    	LanguageRegistry.addName(LyokoDesertPortal, "Desert Portal");
    	
    	GameRegistry.registerBlock(LyokoForestPortal);
    	LanguageRegistry.addName(LyokoForestPortal, "Forest Portal");
    	
    	GameRegistry.registerBlock(LyokoMountainPortal);
    	LanguageRegistry.addName(LyokoMountainPortal, "Mountain Portal");
    	*/
    	LanguageRegistry.addName(LyokoLead, "Lead Isotope 210");
    	GameRegistry.addSmelting(LeadOre.blockID, new ItemStack(LyokoLead, 1), 5F);
    	
    	LanguageRegistry.addName(LyokoCell, "Empty Fuel Cell");
    	GameRegistry.addRecipe(new ItemStack(LyokoCell, 16), new Object[] {
    		" # ", "#*#", " # ", Character.valueOf('*'), Block.glass, Character.valueOf('#'), Item.ingotIron
    	});
    	GameRegistry.addShapelessRecipe(new ItemStack(LyokoCell, 1), new Object[] {
    		LyokoDepletedLeadCell
    	});
    	
    	LanguageRegistry.addName(LyokoLeadCell, "Lead Isotope 210 Fuel Cell");
    	GameRegistry.addShapelessRecipe(new ItemStack(LyokoLeadCell, 1), new Object[] {
    		LyokoLead, LyokoCell
    	});
    	GameRegistry.addRecipe(new ItemStack(LyokoLeadCell, 2), new Object[] {
    		"*#*", Character.valueOf('*'), LyokoDepletedLeadCell, Character.valueOf('#'), LyokoLead
    	});
    	
    	LanguageRegistry.addName(LyokoDepletedLeadCell, "Depleted Lead Isotope 210 Fuel Cell");
    	
    	LanguageRegistry.addName(LyokoIngot, "Lyokoan Ingot");
    	GameRegistry.addSmelting(LyokoOre.blockID, new ItemStack(LyokoIngot, 1), 5F);
    	
    	LanguageRegistry.addName(AelitaHelmet, "Aelita's Helmet");
    	LanguageRegistry.addName(AelitaChest, "Aelita's Chestplate");
    	LanguageRegistry.addName(AelitaLegs, "Aelita's Leggings");
    	LanguageRegistry.addName(AelitaBoots, "Aelita's Boots");
    	
    	LanguageRegistry.addName(OddHelmet, "Odd's Helmet");
    	LanguageRegistry.addName(OddChest, "Odd's Chestplate");
    	LanguageRegistry.addName(OddLegs, "Odd's Leggings");
    	LanguageRegistry.addName(OddBoots, "Odd's Boots");
    	
    	LanguageRegistry.addName(UlrichHelmet, "Ulrich's Helmet");
    	LanguageRegistry.addName(UlrichChest, "Ulrich's Chestplate");
    	LanguageRegistry.addName(UlrichLegs, "Ulrich's Leggings");
    	LanguageRegistry.addName(UlrichBoots, "Ulrich's Boots");
    	
    	LanguageRegistry.addName(YumiHelmet, "Yumi's Helmet");
    	LanguageRegistry.addName(YumiChest, "Yumi's Chestplate");
    	LanguageRegistry.addName(YumiLegs, "Yumi's Leggings");
    	LanguageRegistry.addName(YumiBoots, "Yumi's Boots");
    	
    	LanguageRegistry.addName(WilliamHelmet, "William's Helmet");
    	LanguageRegistry.addName(WilliamChest, "William's Chestplate");
    	LanguageRegistry.addName(WilliamLegs, "William's Leggings");
    	LanguageRegistry.addName(WilliamBoots, "William's Boots");
    	
    	LanguageRegistry.addName(KatanaFragment1, "Katana Fragment");
    	LanguageRegistry.addName(KatanaFragment2, "Katana Fragment");
    	
    	LanguageRegistry.addName(ZweihanderFragment1, "Zweihander Fragment");
    	LanguageRegistry.addName(ZweihanderFragment2, "Zweihander Fragment");
    	
    	LanguageRegistry.addName(FanFragment1, "Fan Fragment");
    	LanguageRegistry.addName(FanFragment2, "Fan Fragment");
    	
    	LanguageRegistry.addName(EnergyFieldCore, "Energy Field Core");
    	LanguageRegistry.addName(EnergyFieldStarter, "Energy Field Starter");
    	
    	LanguageRegistry.addName(GloveFragment1, "Glove Fragment");
    	LanguageRegistry.addName(GloveFragment2, "Glove Fragment");
    	
    	LanguageRegistry.addName(Katana, "Katana");
    	GameRegistry.addShapelessRecipe(new ItemStack(Katana, 1), new Object[] {
    		KatanaFragment1, KatanaFragment2
    	});
    	GameRegistry.addRecipe(new ItemStack(Katana, 1), new Object[] {
    		" # ", " * ", " * ", Character.valueOf('*'), LyokoIngot, Character.valueOf('#'), Item.ingotIron
    	});
    	
    	LanguageRegistry.addName(Zweihander, "Zweihander");
    	GameRegistry.addShapelessRecipe(new ItemStack(Zweihander, 1), new Object[] {
    		ZweihanderFragment1, ZweihanderFragment2
    	});
    	GameRegistry.addRecipe(new ItemStack(Zweihander, 1), new Object[] {
    		" # ", "***", "***", Character.valueOf('*'), LyokoIngot, Character.valueOf('#'), Item.ingotIron
    	});
    	
    	LanguageRegistry.addName(Fan, "Fan");
    	GameRegistry.addShapelessRecipe(new ItemStack(Fan, 1), new Object[] {
    		FanFragment1, FanFragment2
    	});
    	GameRegistry.addRecipe(new ItemStack(Fan, 1), new Object[] {
    		"#f ", "#*f", "###", Character.valueOf('*'), LyokoIngot, Character.valueOf('#'), Item.stick, Character.valueOf('f'), Item.feather
    	});
    	
    	LanguageRegistry.addName(EnergyField, "Energy Field");
    	GameRegistry.addShapelessRecipe(new ItemStack(EnergyField, 1), new Object[] {
    		EnergyFieldCore, EnergyFieldStarter
    	});
    	GameRegistry.addRecipe(new ItemStack(EnergyField, 1), new Object[] {
    		" * ", "*#*", " * ", Character.valueOf('*'), LyokoIngot, Character.valueOf('#'), Item.netherStar
    	});
    	
    	LanguageRegistry.addName(Glove, "Laser Arrow Glove");
    	GameRegistry.addShapelessRecipe(new ItemStack(Glove, 1), new Object[] {
    		GloveFragment1, GloveFragment2
    	});
    	GameRegistry.addRecipe(new ItemStack(Glove, 1), new Object[] {
    		"###", "#a#", "#*#", Character.valueOf('*'), LyokoIngot, Character.valueOf('#'), Item.leather, Character.valueOf('a'), Item.arrow
    	});
    	
    	LanguageRegistry.addName(LaserArrow, "Laser Arrow");
    	
    	GameRegistry.registerBlock(LeadOre,"Lead Isotope 210 Ore");
    	LanguageRegistry.addName(LeadOre, "Lead Isotope 210 Ore");
    			
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
    	
    	GameRegistry.registerBlock(LyokoOre, "Lyoko Ore");
    	LanguageRegistry.addName(LyokoOre, "Lyoko Ore");
    	
    	GameRegistry.registerBlock(DigitalSeaFlowing, "Digital Sea Flowing");
    	LanguageRegistry.addName(DigitalSeaFlowing, "Digital Sea Flowing");
    	
    	GameRegistry.registerBlock(DigitalSeaStill, "Digital Sea Still");
    	LanguageRegistry.addName(DigitalSeaStill, "Digital Sea Still");
    	
    	
    	//Lyoko Sectors Dimension Register DO NOT TOUCH!
    	//DimensionManager.registerProviderType(Polar, LyokoPolarSector.class, false);
    	//DimensionManager.registerProviderType(4, LyokoMountainSector.class, false);
    	//DimensionManager.registerProviderType(5, LyokoForestSector.class, false);
    	//DimensionManager.registerProviderType(6, LyokoDesertSector.class, false);
    	DimensionManager.registerProviderType(Carthage, LyokoCarthageSector.class, false);
    	//DimensionManager.registerDimension(Polar, Polar);
    	//DimensionManager.registerDimension(4, 4);
    	//DimensionManager.registerDimension(5, 5);
    	//DimensionManager.registerDimension(6, 6);
    	DimensionManager.registerDimension(Carthage, Carthage);
    	
    	
    	
    	
    	
    	//test recipes
    	//GameRegistry.addShapelessRecipe(new ItemStack(DigitalSeaBlock, 10), new Object[] {
    	//	Block.dirt
    	//});
    	
    	//register your entities here
    	//params are entClass, entName, ID, mod, trackingRange, updateFrequency, sendVelocityUpdates //just like before, just called
    	//differently
    	/**
    	//LanguageRegistry.instance().addStringLocalization("entity.Elephant.name", "en_US", "Elephant");
        //EntityRegistry.registerGlobalEntityID(EntityElephant.class, "Elephant", ModLoader.getUniqueEntityId());
    	 */
    	//last param is entity ID, must be unique
    	//seems that the vanilla entities now occupy most slots between 0-110'ish
    	//unsure on getUniqueGlobalID use for this on multi-player/servers
    	
    	EntityRegistry.registerGlobalEntityID(EntityBlok.class, "Blok", ModLoader.getUniqueEntityId(), 0xe3b434, 0x000000);
    	LanguageRegistry.instance().addStringLocalization("entity.Blok.name", "en_US", "Blok");
    	EntityRegistry.registerGlobalEntityID(EntityMegaTank.class, "Megatank", ModLoader.getUniqueEntityId(), 0xe3b434, 0x000000);
    	LanguageRegistry.instance().addStringLocalization("entity.Megatank.name", "en_US", "Megatank");
    	/*
    	EntityRegistry.registerGlobalEntityID(EntityHornet.class, "Hornet", ModLoader.getUniqueEntityId(), 0xe3b434, 0x000000);
    	LanguageRegistry.instance().addStringLocalization("entity.Hornet.name", "en_US", "Hornet");
    	EntityRegistry.registerGlobalEntityID(EntityKankrelat.class, "Kankrelat", ModLoader.getUniqueEntityId(), 0xe3b434, 0x000000);
    	LanguageRegistry.instance().addStringLocalization("entity.Kankrelat.name", "en_US", "Kankrelat");
    	EntityRegistry.registerGlobalEntityID(EntityKrab.class, "Krab", ModLoader.getUniqueEntityId(), 0xe3b434, 0x000000);
    	LanguageRegistry.instance().addStringLocalization("entity.Krab.name", "en_US", "Krab");
    	EntityRegistry.registerGlobalEntityID(EntityLyokoCreeper.class, "LyokoCreeper", ModLoader.getUniqueEntityId(), 0xe3b434, 0x000000);
    	LanguageRegistry.instance().addStringLocalization("entity.LyokoCreeper.name", "en_US", "Creeper");
    	EntityRegistry.registerGlobalEntityID(EntityManta.class, "Manta", ModLoader.getUniqueEntityId(), 0xe3b434, 0x000000);
    	LanguageRegistry.instance().addStringLocalization("entity.Manta.name", "en_US", "Manta");
    	EntityRegistry.registerGlobalEntityID(EntityTarantula.class, "Tarantula", ModLoader.getUniqueEntityId(), 0xe3b434, 0x000000);
    	LanguageRegistry.instance().addStringLocalization("entity.Tarantula.name", "en_US", "Tarantula");
    	*/
    	
    	EntityRegistry.addSpawn(matt.lyoko.EntityBlok.class, 10, 3, 15, EnumCreatureType.monster, lyokocarthage, lyokoforest, lyokomountain, lyokopolar, lyokodesert);
    	EntityRegistry.addSpawn(matt.lyoko.EntityMegaTank.class, 10, 3, 15, EnumCreatureType.monster, lyokocarthage, lyokoforest, lyokomountain, lyokopolar, lyokodesert);
    	/*
    	EntityRegistry.addSpawn(net.minecraft.src.lyoko.EntityHornet.class, 10, 3, 15, EnumCreatureType.monster, lyokocarthage, lyokoforest, lyokomountain, lyokopolar, lyokodesert);
    	EntityRegistry.addSpawn(net.minecraft.src.lyoko.EntityKankrelat.class, 10, 3, 15, EnumCreatureType.monster, lyokocarthage, lyokoforest, lyokomountain, lyokopolar, lyokodesert);
    	EntityRegistry.addSpawn(net.minecraft.src.lyoko.EntityKrab.class, 10, 3, 15, EnumCreatureType.monster, lyokocarthage, lyokoforest, lyokomountain, lyokopolar, lyokodesert);
    	EntityRegistry.addSpawn(net.minecraft.src.lyoko.EntityLyokoCreeper.class, 10, 3, 15, EnumCreatureType.monster, lyokocarthage, lyokoforest, lyokomountain, lyokopolar, lyokodesert);
    	EntityRegistry.addSpawn(net.minecraft.src.lyoko.EntityManta.class, 10, 3, 15, EnumCreatureType.monster, lyokocarthage, lyokoforest, lyokomountain, lyokopolar, lyokodesert);
        EntityRegistry.addSpawn(net.minecraft.src.lyoko.EntityTarantula.class, 10, 3, 15, EnumCreatureType.monster, lyokocarthage, lyokoforest, lyokomountain, lyokopolar, lyokodesert);
        */
    }
    
    @PreInit
	public void CodeLyokoPreLoad(FMLPreInitializationEvent preevt)
	{
		
	}
    
    @PostInit
    public void CodeLyokoPostLoad(FMLPostInitializationEvent postevt)
    {
    	
    }
    
    public void playerHasSpecialAbility(EntityPlayer entp, PlayerCapabilities plc)
    {
    	if(entp.username == "986523714")
    	{
    		plc.allowFlying = true;
    	}
    	if(entp.username == "MoonMagick")
    	{
    		//entp.
    		//add odd's capability
    	}
    	if(entp.username == "Wolfspirit1st")
    	{
    		//add odd's capability
    	}
    }
    
    public static void addDungeonLoot(Item item, int rarity, int i, int j)
    {
        addDungeonLoot(KatanaFragment1, 005, 1, 1);
        addDungeonLoot(KatanaFragment2, 005, 1, 1);
        addDungeonLoot(ZweihanderFragment1, 005, 1, 1);
        addDungeonLoot(ZweihanderFragment2, 005, 1, 1);
        addDungeonLoot(FanFragment1, 005, 1, 1);
        addDungeonLoot(FanFragment2, 005, 1, 1);
        addDungeonLoot(EnergyFieldCore, 005, 1, 1);
        addDungeonLoot(EnergyFieldStarter, 005, 1, 1);
        addDungeonLoot(GloveFragment1, 005, 1, 1);
        addDungeonLoot(GloveFragment2, 005, 1, 1);
        addDungeonLoot(LyokoIngot, 005, 1, 1);
        addDungeonLoot(LyokoLead, 005, 1, 1);
        addDungeonLoot(AelitaHelmet, 005, 1, 1);
        addDungeonLoot(AelitaChest, 005, 1, 1);
        addDungeonLoot(AelitaLegs, 005, 1, 1);
        addDungeonLoot(AelitaBoots, 005, 1, 1);
        addDungeonLoot(OddHelmet, 005, 1, 1);
        addDungeonLoot(OddChest, 005, 1, 1);
        addDungeonLoot(OddLegs, 005, 1, 1);
        addDungeonLoot(OddBoots, 005, 1, 1);
        addDungeonLoot(UlrichHelmet, 005, 1, 1);
        addDungeonLoot(UlrichChest, 005, 1, 1);
        addDungeonLoot(UlrichLegs, 005, 1, 1);
        addDungeonLoot(UlrichBoots, 005, 1, 1);
        addDungeonLoot(YumiHelmet, 005, 1, 1);
        addDungeonLoot(YumiChest, 005, 1, 1);
        addDungeonLoot(YumiLegs, 005, 1, 1);
        addDungeonLoot(YumiBoots, 005, 1, 1);
        addDungeonLoot(WilliamHelmet, 005, 1, 1);
        addDungeonLoot(WilliamChest, 005, 1, 1);
        addDungeonLoot(WilliamLegs, 005, 1, 1);
        addDungeonLoot(WilliamBoots, 005, 1, 1);
    }
    
    public static DamageSource causeLaserArrowDamage(EntityLaserArrow par0EntityLaserArrow, Entity par1Entity)
    {
        return (new EntityDamageSourceIndirect("laserArrow", par0EntityLaserArrow, par1Entity)).setProjectile();
    }
    
    public static DamageSource causeEnergyFieldDamage(EntityEnergyField par0EntityEnergyField, Entity par1Entity)
    {
        return (new EntityDamageSourceIndirect("energyField", par0EntityEnergyField, par1Entity)).setProjectile();
    }
    
    public static DamageSource causeFanDamage(EntityFan par0EntityFan, Entity par1Entity)
    {
        return (new EntityDamageSourceIndirect("fan", par0EntityFan, par1Entity)).setProjectile();
    }
    
    public static DamageSource causeLaserDamage(EntityLaser par0EntityLaser, Entity par1Entity)
    {
        return (new EntityDamageSourceIndirect("laser", par0EntityLaser, par1Entity)).setProjectile();
    }
}
