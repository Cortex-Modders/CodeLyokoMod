package matt.lyoko;

import java.util.Random;
import matt.lyoko.world.*;
import matt.lyoko.world.portals.*;
import matt.lyoko.blocks.*;
import matt.lyoko.client.GuiHandler;
import matt.lyoko.items.*;
import matt.lyoko.entities.*;
import net.minecraftforge.common.*;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.*;
import cpw.mods.fml.common.network.*;
import cpw.mods.fml.common.registry.*;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.*;
import net.minecraft.item.*;
import net.minecraft.src.*;
import net.minecraft.util.*;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.entity.player.*;

@Mod(modid = "CodeLyoko", name="Code Lyoko", version="0.3.2-Alpha")
@NetworkMod
(
clientSideRequired = true,
serverSideRequired = false,
channels = {"Code_Lyoko"},
packetHandler = PacketHandler.class
)
public class CodeLyoko
{
	public static int Lyoko_Tower;// = 1154;
	public static int Lyoko_Tower_Base;// = 1155;
	public static int Lyoko_Grass;// = 1156;
	public static int Lyoko_Stone;// = 1157;
	public static int Lyoko_Sand;// = 1158;
	public static int Lyoko_Ice;// = 1159;
	public static int Lyoko_Log;// = 1160;
	public static int Lyoko_Sea_Block;// = 1161;
	public static int Lyoko_Sea_Flowing;// = 1162;
	public static int Lyoko_Sea_Still;// = 1163;
	public static int Lyoko_Virtual_Block;// = 1164;
	public static int Lyoko_Carthage;// = 1165;
	public static int Lyoko_Ore;// = 1166;
	public static int Lyoko_Polar_Portal;// = 1167;
	public static int Lyoko_Desert_Portal;// = 1168;
	public static int Lyoko_Forest_Portal;// = 1169;
	public static int Lyoko_Mountain_Portal;// = 1170;
	public static int Lyoko_Carthage_Portal;// = 1171;
	public static int Lyoko_Over_Portal;// = 1172;
	public static int Lyoko_Super_Calc;// = 1173;
	public static int Lyoko_Lead_Ore;// = 1174;
	
	public static int Weapon_Lyoko_1;// = 6081;
	public static int Weapon_Lyoko_2;// = 6082;
	public static int Weapon_Lyoko_3;// = 6083;
	public static int Weapon_Lyoko_4;// = 6084;
	public static int Weapon_Lyoko_5;// = 6085;
	public static int Weapon_Lyoko_6;// = 6086;
	public static int Item_Lyoko_1;// = 6087;
	public static int Item_Lyoko_2;// = 6088;
	public static int Item_Lyoko_3;// = 6089;
	public static int Item_Lyoko_4;// = 6090;
	public static int Item_Lyoko_5;// = 6091;
	public static int Item_Lyoko_6;// = 6092;
	public static int Item_Lyoko_7;// = 6093;
	public static int Item_Lyoko_8;// = 6094;
	public static int Item_Lyoko_9;// = 6095;
	public static int Item_Lyoko_10;// = 6096;
	public static int Item_Skid;// = 6097;
	public static int Item_Lyoko_11;// = 6098;
	public static int Item_Lyoko_12;// = 6099;
	public static int Item_Lyoko_13;// = 6100;
	public static int Item_Lyoko_14;// = 6101;
	public static int Item_Lyoko_15;// = 6102;
	public static int Aelita_Armor_Helmet;// = 6103;
	public static int Aelita_Armor_Chest;// = 6104;
	public static int Aelita_Armor_Pants;// = 6105;
	public static int Aelita_Armor_Boots;// = 6106;
	public static int Odd_Armor_Helmet;// = 6107;
	public static int Odd_Armor_Chest;// = 6108;
	public static int Odd_Armor_Pants;// = 6109;
	public static int Odd_Armor_Boots;// = 6110;
	public static int Ulrich_Armor_Helmet;// = 6111;
	public static int Ulrich_Armor_Chest;// = 6112;
	public static int Ulrich_Armor_Pants;// = 6113;
	public static int Ulrich_Armor_Boots;// = 6114;
	public static int Yumi_Armor_Helmet;// = 6115;
	public static int Yumi_Armor_Chest;// = 6116;
	public static int Yumi_Armor_Pants;// = 6117;
	public static int Yumi_Armor_Boots;// = 6118;
	public static int William_Armor_Helmet;// = 6119;
	public static int William_Armor_Chest;// = 6120;
	public static int William_Armor_Pants;// = 6121;
	public static int William_Armor_Boots;// = 6122;
	
	public static int Polar_Sector_ID;
	public static int Mountain_Sector_ID;
	public static int Forest_Sector_ID;
	public static int Desert_Sector_ID;
	public static int Carthage_Sector_ID;

	public static int SuperCalcRenderID;
	public static int SuperCalcTexture;
	static EnumToolMaterial toolLYOKO = EnumHelper.addToolMaterial("LYOKO", 3, 300, 14F, 30, 30);
	
	static EnumArmorMaterial armorLYOKO = EnumHelper.addArmorMaterial("LYOKO", 40, new int[] {5, 10, 8, 5}, 30);
	
	public static final Material LyokoTower = new Material(MapColor.ironColor);
	public static CreativeTabs LyokoTabs = new LyokoTab("LyokoTabs");
	public static final BiomeGenBase lyokomountain = ((BiomeGenBaseLyoko) (new BiomeGenMountainSector(9)).setColor(8421631)).setLyokoBiomeName("Mountain Sector");
	public static final BiomeGenBase lyokoforest = ((BiomeGenBaseLyoko) (new BiomeGenForestSector(10)).setColor(8421631)).setLyokoBiomeName("Forest Sector");
	public static final BiomeGenBase lyokodesert = ((BiomeGenBaseLyoko) (new BiomeGenDesertSector(11)).setColor(8421631)).setLyokoBiomeName("Desert Sector");
	public static final BiomeGenBase lyokopolar = ((BiomeGenBaseLyoko) (new BiomeGenPolarSector(12)).setColor(8421631)).setLyokoBiomeName("Polar Sector");
	public static final BiomeGenBase lyokocarthage = ((BiomeGenBaseLyoko) (new BiomeGenCarthageSector(13)).setColor(8421631)).setLyokoBiomeName("Carthage Sector");
	public static Item Katana;// = new ItemLyokoSword(Weapon_Lyoko_1, toolLYOKO).setItemName("Katana").setIconIndex(0);
	public static Item Zweihander;// = new ItemLyokoSword(Weapon_Lyoko_2, toolLYOKO).setItemName("Zweihander").setIconIndex(1);
	public static Item Fan;// = new ItemLyokoFan(Weapon_Lyoko_3).setItemName("Fan").setIconIndex(2);
	public static Item EnergyField;// = new ItemLyokoEnergyField(Weapon_Lyoko_4).setItemName("EnergyField").setIconIndex(3);
	public static Item Glove;// = new ItemGlove(Weapon_Lyoko_5).setItemName("Glove").setIconIndex(4);
	public static Item LaserArrow;// = new ItemLyoko(Weapon_Lyoko_6).setItemName("LaserArrow").setIconIndex(5);
	public static Item KatanaFragment1;// = new ItemLyoko(Item_Lyoko_1).setItemName("KatanaFragment1").setIconIndex(6);
	public static Item KatanaFragment2;// = new ItemLyoko(Item_Lyoko_2).setItemName("KatanaFragment2").setIconIndex(7);
	public static Item ZweihanderFragment1;// = new ItemLyoko(Item_Lyoko_3).setItemName("ZweihanderFragment1").setIconIndex(8);
	public static Item ZweihanderFragment2;// = new ItemLyoko(Item_Lyoko_4).setItemName("ZweihanderFragment2").setIconIndex(9);
	public static Item FanFragment1;// = new ItemLyoko(Item_Lyoko_5).setItemName("FanFragment1").setIconIndex(10);
	public static Item FanFragment2;// = new ItemLyoko(Item_Lyoko_6).setItemName("FanFragment2").setIconIndex(11);
	public static Item EnergyFieldCore;// = new ItemLyoko(Item_Lyoko_7).setItemName("EnergyFieldCore").setIconIndex(12);
	public static Item EnergyFieldStarter;// = new ItemLyoko(Item_Lyoko_8).setItemName("EnergyFieldStarter").setIconIndex(13);
	public static Item GloveFragment1;// = new ItemLyoko(Item_Lyoko_9).setItemName("GloveFragment1").setIconIndex(14);
	public static Item GloveFragment2;// = new ItemLyoko(Item_Lyoko_10).setItemName("GloveFragment2").setIconIndex(15);
	public static Item LyokoIngot;// = new ItemLyoko(Item_Lyoko_11).setItemName("LyokoIngot").setIconIndex(16);
	public static Item LyokoLead;// = new ItemLyoko(Item_Lyoko_12).setItemName("Lead210").setIconIndex(17);
	public static Item LyokoCell;// = new ItemLyoko(Item_Lyoko_13).setItemName("Cell").setIconIndex(18);
	public static Item LyokoLeadCell;// = new ItemLyokoFuel(Item_Lyoko_14, 10000).setItemName("Lead210Cell").setIconIndex(19);
	public static Item LyokoDepletedLeadCell;// = new ItemLyoko(Item_Lyoko_15).setItemName("DepletedLead210Cell").setIconIndex(20);
	public static Item AelitaHelmet;// = new ArmorLyoko(Aelita_Armor_Helmet, armorLYOKO, 5, 0, "Aelita").setIconIndex(21).setItemName("AelitaHelmet");
    public static Item AelitaChest;// = new ArmorLyoko(Aelita_Armor_Chest, armorLYOKO, 5, 1, "Aelita").setIconIndex(22).setItemName("AelitaChest");
    public static Item AelitaLegs;// = new ArmorLyoko(Aelita_Armor_Pants, armorLYOKO, 5, 2, "Aelita").setIconIndex(23).setItemName("AelitaPants");
    public static Item AelitaBoots;// = new ArmorLyoko(Aelita_Armor_Boots, armorLYOKO, 5, 3, "Aelita").setIconIndex(24).setItemName("AelitaBoots");
    public static Item OddHelmet;// = new ArmorLyoko(Odd_Armor_Helmet, armorLYOKO, 6, 0, "Odd").setIconIndex(25).setItemName("OddHelmet");
    public static Item OddChest;// = new ArmorLyoko(Odd_Armor_Chest, armorLYOKO, 6, 1, "Odd").setIconIndex(26).setItemName("OddChest");
    public static Item OddLegs;// = new ArmorLyoko(Odd_Armor_Pants, armorLYOKO, 6, 2, "Odd").setIconIndex(27).setItemName("OddPants");
    public static Item OddBoots;// = new ArmorLyoko(Odd_Armor_Boots, armorLYOKO, 6, 3, "Odd").setIconIndex(28).setItemName("OddBoots");
    public static Item UlrichHelmet;// = new ArmorLyoko(Ulrich_Armor_Helmet, armorLYOKO, 7, 0, "Ulrich").setIconIndex(29).setItemName("UlrichHelmet");
    public static Item UlrichChest;// = new ArmorLyoko(Ulrich_Armor_Chest, armorLYOKO, 7, 1, "Ulrich").setIconIndex(30).setItemName("UlrichChest");
    public static Item UlrichLegs;// = new ArmorLyoko(Ulrich_Armor_Pants, armorLYOKO, 7, 2, "Ulrich").setIconIndex(31).setItemName("UlrichPants");
    public static Item UlrichBoots;// = new ArmorLyoko(Ulrich_Armor_Boots, armorLYOKO, 7, 3, "Ulrich").setIconIndex(32).setItemName("UlrichBoots");
    public static Item YumiHelmet;// = new ArmorLyoko(Yumi_Armor_Helmet, armorLYOKO, 8, 0, "Yumi").setIconIndex(33).setItemName("YumiHelmet");
    public static Item YumiChest;// = new ArmorLyoko(Yumi_Armor_Chest, armorLYOKO, 8, 1, "Yumi").setIconIndex(34).setItemName("YumiChest");
    public static Item YumiLegs;// = new ArmorLyoko(Yumi_Armor_Pants, armorLYOKO, 8, 2, "Yumi").setIconIndex(35).setItemName("YumiPants");
    public static Item YumiBoots;// = new ArmorLyoko(Yumi_Armor_Boots, armorLYOKO, 8, 3, "Yumi").setIconIndex(36).setItemName("YumiBoots");
    public static Item WilliamHelmet;// = new ArmorLyoko(William_Armor_Helmet, armorLYOKO, 9, 0, "William").setIconIndex(37).setItemName("WilliamHelmet");
    public static Item WilliamChest;// = new ArmorLyoko(William_Armor_Chest, armorLYOKO, 9, 1, "William").setIconIndex(38).setItemName("WilliamChest");
    public static Item WilliamLegs;// = new ArmorLyoko(William_Armor_Pants, armorLYOKO, 9, 2, "William").setIconIndex(39).setItemName("WilliamPants");
    public static Item WilliamBoots;// = new ArmorLyoko(William_Armor_Boots, armorLYOKO, 9, 3, "William").setIconIndex(40).setItemName("WilliamBoots");
    public static Item Skid;
	public static Block TowerBlock;// = new BlockLyoko(Lyoko_Tower, 0).setResistance(6000000F).setBlockUnbreakable().setLightValue(7F).setStepSound(Block.soundMetalFootstep).setBlockName("TowerBlock");
	public static Block TowerBase;// = new BlockTowerBase(Lyoko_Tower_Base, 1, false).setResistance(6000000F).setBlockUnbreakable().setLightValue(7F).setStepSound(Block.soundMetalFootstep).setBlockName("TowerBase");
	public static Block LyokoGrass;// = new BlockLyoko(Lyoko_Grass, 2).setResistance(6000000F).setBlockUnbreakable().setStepSound(Block.soundGrassFootstep).setBlockName("LyokoGrass");
	public static Block LyokoStone;// = new BlockLyoko(Lyoko_Stone, 3).setResistance(6000000F).setBlockUnbreakable().setStepSound(Block.soundStoneFootstep).setBlockName("LyokoStone");
	public static Block LyokoSand;// = new BlockLyoko(Lyoko_Sand, 4).setResistance(6000000F).setBlockUnbreakable().setStepSound(Block.soundSandFootstep).setBlockName("LyokoSand");
	public static Block LyokoIce;// = new BlockLyokoIce(Lyoko_Ice, 5, Material.glass, false).setResistance(6000000F).setBlockUnbreakable().setLightOpacity(3).setStepSound(Block.soundGlassFootstep).setBlockName("LyokoIce");
	public static Block LyokoLog;// = new BlockLyoko(Lyoko_Log, 6).setResistance(6000000F).setBlockUnbreakable().setStepSound(Block.soundWoodFootstep).setBlockName("LyokoLog");
	public static Block LyokoCarthage;// = new BlockLyoko(Lyoko_Carthage, 7).setResistance(6000000F).setBlockUnbreakable().setStepSound(Block.soundMetalFootstep).setBlockName("LyokoCarthage");
	public static Block LyokoOre;// = new BlockLyoko(Lyoko_Ore, 8).setHardness(10F).setResistance(20F).setStepSound(Block.soundStoneFootstep).setBlockName("LyokoOre");
	public static Block DigitalSeaBlock;// = new BlockDigitalSea(Lyoko_Sea_Block, 9).setResistance(6000000F).setBlockUnbreakable().setBlockName("DigitalSeaBlock");
	public static Block DigitalSeaFlowing;// = new BlockFlowingDigitalSea(Lyoko_Sea_Flowing, Material.water).setHardness(100F).setLightOpacity(3).setBlockName("DigitalSeaFlowing").setRequiresSelfNotify();
	public static Block DigitalSeaStill;// = new BlockStationaryDigitalSea(Lyoko_Sea_Still, Material.water).setHardness(100F).setLightOpacity(3).setBlockName("DigitalSeaStill").setRequiresSelfNotify();
	public static Block LeadOre;// = new BlockLyoko(Lyoko_Lead_Ore, 10).setHardness(10F).setResistance(20F).setStepSound(Block.soundStoneFootstep).setBlockName("LeadOre").setLightValue(10F);
	public static Block VirtualBlock;
	/*
	public static final Block LyokoPolarPortal  = new LyokoPolarPortal(Lyoko_Polar_Portal, 12).setBlockName("Polar Portal");
	public static final Block LyokoDesertPortal = new LyokoDesertPortal(Lyoko_Desert_Portal, 13).setBlockName("Desert Portal");
	public static final Block LyokoForestPortal = new LyokoForestPortal(Lyoko_Forest_Portal, 14).setBlockName("Forest Portal");
	public static final Block LyokoMountainPortal = new LyokoMountainPortal(Lyoko_Mountain_Portal, 15).setBlockName("Mountain Portal");
	public static final Block LyokoCarthagePortal  = new LyokoCarthagePortal(Lyoko_Carthage_Portal, 16).setBlockName("Carthage Portal");
	public static final Block LyokoOverPortal = new OverworldPortal(Lyoko_Over_Portal, 17).setBlockName("OverWorld Portal");
	*/
	public static Block SuperCalc;// = new BlockSuperCalc(Lyoko_Super_Calc).setHardness(20).setResistance(6000000).setBlockName("Super Computer").setRequiresSelfNotify();
	
	@SidedProxy(clientSide = "matt.lyoko.ClientProxy", serverSide = "matt.lyoko.CommonProxy")
	public static CommonProxy proxy; //This object will be populated with the class that you choose for the environment
	@Instance
	public static CodeLyoko instance;//the instance of the mod that will be defined, populated, and callable
	
	/*public void renderInvBlock(RenderBlocks var1, Block var2, int var3, int var4)
	{
		renderInvBlock(var1, var2, var3, var4);
		
		if (var2 == SuperCalc)
		{
			TileEntityRenderer.instance.renderTileEntityAt(new TileEntitySuperCalc(), 0.0D, 0.0D, 0.0D, 0.0F);
		}
	}*/
	
    @Init
    public void CodeLyokoLoad(FMLInitializationEvent evt)
    {
        SuperCalcRenderID = RenderingRegistry.getNextAvailableRenderId();
    	
    	Katana = new ItemLyokoSword(Weapon_Lyoko_1, toolLYOKO).setItemName("Katana").setIconIndex(0);
    	Zweihander = new ItemLyokoSword(Weapon_Lyoko_2, toolLYOKO).setItemName("Zweihander").setIconIndex(1);
    	Fan = new ItemLyokoFan(Weapon_Lyoko_3).setItemName("Fan").setIconIndex(2);
    	EnergyField = new ItemLyokoEnergyField(Weapon_Lyoko_4).setItemName("EnergyField").setIconIndex(3);
    	Glove = new ItemGlove(Weapon_Lyoko_5).setItemName("Glove").setIconIndex(4);
    	LaserArrow = new ItemLyoko(Weapon_Lyoko_6).setItemName("LaserArrow").setIconIndex(5);
    	KatanaFragment1 = new ItemLyoko(Item_Lyoko_1).setItemName("KatanaFragment1").setIconIndex(6);
    	KatanaFragment2 = new ItemLyoko(Item_Lyoko_2).setItemName("KatanaFragment2").setIconIndex(7);
    	ZweihanderFragment1 = new ItemLyoko(Item_Lyoko_3).setItemName("ZweihanderFragment1").setIconIndex(8);
    	ZweihanderFragment2 = new ItemLyoko(Item_Lyoko_4).setItemName("ZweihanderFragment2").setIconIndex(9);
    	FanFragment1 = new ItemLyoko(Item_Lyoko_5).setItemName("FanFragment1").setIconIndex(10);
    	FanFragment2 = new ItemLyoko(Item_Lyoko_6).setItemName("FanFragment2").setIconIndex(11);
    	EnergyFieldCore = new ItemLyoko(Item_Lyoko_7).setItemName("EnergyFieldCore").setIconIndex(12);
    	EnergyFieldStarter = new ItemLyoko(Item_Lyoko_8).setItemName("EnergyFieldStarter").setIconIndex(13);
    	GloveFragment1 = new ItemLyoko(Item_Lyoko_9).setItemName("GloveFragment1").setIconIndex(14);
    	GloveFragment2 = new ItemLyoko(Item_Lyoko_10).setItemName("GloveFragment2").setIconIndex(15);
    	LyokoIngot = new ItemLyoko(Item_Lyoko_11).setItemName("LyokoIngot").setIconIndex(16);
    	LyokoLead = new ItemLyoko(Item_Lyoko_12).setItemName("Lead210").setIconIndex(17);
    	LyokoCell = new ItemLyoko(Item_Lyoko_13).setItemName("Cell").setIconIndex(18);
    	LyokoLeadCell = new ItemLyokoFuel(Item_Lyoko_14, 10000, LyokoDepletedLeadCell).setItemName("Lead210Cell").setIconIndex(19);
    	LyokoDepletedLeadCell = new ItemLyoko(Item_Lyoko_15).setItemName("DepletedLead210Cell").setIconIndex(20);
    	AelitaHelmet = new ArmorLyoko(Aelita_Armor_Helmet, armorLYOKO, 5, 0, "aelita").setIconIndex(21).setItemName("AelitaHelmet");
        AelitaChest = new ArmorLyoko(Aelita_Armor_Chest, armorLYOKO, 5, 1, "aelita").setIconIndex(22).setItemName("AelitaChest");
        AelitaLegs = new ArmorLyoko(Aelita_Armor_Pants, armorLYOKO, 5, 2, "aelita").setIconIndex(23).setItemName("AelitaPants");
        AelitaBoots = new ArmorLyoko(Aelita_Armor_Boots, armorLYOKO, 5, 3, "aelita").setIconIndex(24).setItemName("AelitaBoots");
        OddHelmet = new ArmorLyoko(Odd_Armor_Helmet, armorLYOKO, 6, 0, "odd").setIconIndex(25).setItemName("OddHelmet");
        OddChest = new ArmorLyoko(Odd_Armor_Chest, armorLYOKO, 6, 1, "odd").setIconIndex(26).setItemName("OddChest");
        OddLegs = new ArmorLyoko(Odd_Armor_Pants, armorLYOKO, 6, 2, "odd").setIconIndex(27).setItemName("OddPants");
        OddBoots = new ArmorLyoko(Odd_Armor_Boots, armorLYOKO, 6, 3, "odd").setIconIndex(28).setItemName("OddBoots");
        UlrichHelmet = new ArmorLyoko(Ulrich_Armor_Helmet, armorLYOKO, 7, 0, "ulrich").setIconIndex(29).setItemName("UlrichHelmet");
        UlrichChest = new ArmorLyoko(Ulrich_Armor_Chest, armorLYOKO, 7, 1, "ulrich").setIconIndex(30).setItemName("UlrichChest");
        UlrichLegs = new ArmorLyoko(Ulrich_Armor_Pants, armorLYOKO, 7, 2, "ulrich").setIconIndex(31).setItemName("UlrichPants");
        UlrichBoots = new ArmorLyoko(Ulrich_Armor_Boots, armorLYOKO, 7, 3, "ulrich").setIconIndex(32).setItemName("UlrichBoots");
        YumiHelmet = new ArmorLyoko(Yumi_Armor_Helmet, armorLYOKO, 8, 0, "yumi").setIconIndex(33).setItemName("YumiHelmet");
        YumiChest = new ArmorLyoko(Yumi_Armor_Chest, armorLYOKO, 8, 1, "yumi").setIconIndex(34).setItemName("YumiChest");
        YumiLegs = new ArmorLyoko(Yumi_Armor_Pants, armorLYOKO, 8, 2, "yumi").setIconIndex(35).setItemName("YumiPants");
        YumiBoots = new ArmorLyoko(Yumi_Armor_Boots, armorLYOKO, 8, 3, "yumi").setIconIndex(36).setItemName("YumiBoots");
        WilliamHelmet = new ArmorLyoko(William_Armor_Helmet, armorLYOKO, 9, 0, "william").setIconIndex(37).setItemName("WilliamHelmet");
        WilliamChest = new ArmorLyoko(William_Armor_Chest, armorLYOKO, 9, 1, "william").setIconIndex(38).setItemName("WilliamChest");
        WilliamLegs = new ArmorLyoko(William_Armor_Pants, armorLYOKO, 9, 2, "william").setIconIndex(39).setItemName("WilliamPants");
        WilliamBoots = new ArmorLyoko(William_Armor_Boots, armorLYOKO, 9, 3, "william").setIconIndex(40).setItemName("WilliamBoots");
        Skid = new ItemLyoko(Item_Skid).setItemName("Skid").setIconIndex(41);
    	TowerBlock = new BlockLyokoTower(Lyoko_Tower, 0).setResistance(6000000F).setBlockUnbreakable().setLightValue(7F).setStepSound(Block.soundMetalFootstep).setBlockName("TowerBlock");
    	TowerBase = new BlockTowerBase(Lyoko_Tower_Base, 1, false).setResistance(6000000F).setBlockUnbreakable().setLightValue(7F).setStepSound(Block.soundMetalFootstep).setBlockName("TowerBase");
    	LyokoGrass = new BlockLyoko(Lyoko_Grass, 2).setResistance(6000000F).setBlockUnbreakable().setStepSound(Block.soundGrassFootstep).setBlockName("LyokoGrass");
    	LyokoStone = new BlockLyoko(Lyoko_Stone, 3).setResistance(6000000F).setBlockUnbreakable().setStepSound(Block.soundStoneFootstep).setBlockName("LyokoStone");
    	LyokoSand = new BlockLyoko(Lyoko_Sand, 4).setResistance(6000000F).setBlockUnbreakable().setStepSound(Block.soundSandFootstep).setBlockName("LyokoSand");
    	LyokoIce = new BlockLyokoIce(Lyoko_Ice, 5, Material.glass, false).setResistance(6000000F).setBlockUnbreakable().setLightOpacity(3).setStepSound(Block.soundGlassFootstep).setBlockName("LyokoIce");
    	LyokoLog = new BlockLyoko(Lyoko_Log, 6).setResistance(6000000F).setBlockUnbreakable().setStepSound(Block.soundWoodFootstep).setBlockName("LyokoLog");
    	LyokoCarthage = new BlockLyoko(Lyoko_Carthage, 7).setResistance(6000000F).setBlockUnbreakable().setStepSound(Block.soundMetalFootstep).setBlockName("LyokoCarthage");
    	LyokoOre = new BlockLyoko(Lyoko_Ore, 8).setHardness(10F).setResistance(20F).setStepSound(Block.soundStoneFootstep).setBlockName("LyokoOre");
    	DigitalSeaBlock = new BlockDigitalSea(Lyoko_Sea_Block, 9).setResistance(6000000F).setBlockUnbreakable().setBlockName("DigitalSeaBlock");
    	DigitalSeaFlowing = new BlockFlowingDigitalSea(Lyoko_Sea_Flowing, Material.water).setHardness(100F).setLightOpacity(3).setBlockName("DigitalSeaFlowing").setRequiresSelfNotify();
    	DigitalSeaStill = new BlockStationaryDigitalSea(Lyoko_Sea_Still, Material.water).setHardness(100F).setLightOpacity(3).setBlockName("DigitalSeaStill").setRequiresSelfNotify();
    	LeadOre = new BlockLyoko(Lyoko_Lead_Ore, 10).setHardness(10F).setResistance(20F).setStepSound(Block.soundStoneFootstep).setBlockName("LeadOre").setLightValue(10F);
    	SuperCalc = new BlockSuperCalc(Lyoko_Super_Calc, 16).setHardness(20).setResistance(6000000).setBlockName("Super Computer").setRequiresSelfNotify();
    	VirtualBlock = new BlockLyokoVirtual(Lyoko_Virtual_Block, 2).setResistance(1.0F).setHardness(1.0F).setStepSound(Block.soundGlassFootstep).setBlockName("LyokoVirtualBlock");
    	
    	//TODO Give mod owners special ability
    	//Matthew = Aelita
    	//Marq = Odd
    	//Andrew = Odd (Jeremy)
    	proxy.registerRenderInformation(); //You have to call the methods in your proxy class
    	proxy.registerServerTickHandler();
    	proxy.registerKeyBindingHandler();
    	
    	NetworkRegistry.instance().registerGuiHandler(this, new GuiHandler());
    	
    	//RenderSuperCalc render = new RenderSuperCalc();
    	//GameRegistry.registerTileEntity(TileEntitySuperCalc.class, "tesc");
    	
    	GameRegistry.registerTileEntity(TileEntitySuperCalc.class, "teSuperCalc");
    	GameRegistry.registerTileEntity(TileEntityDigitalSea.class, "teDigitalSea");
    	
    	GameRegistry.registerWorldGenerator(new WorldGenLyokoOre());
    	GameRegistry.registerWorldGenerator(new WorldGenTower());
    	
    	MinecraftForge.setBlockHarvestLevel(LyokoOre, "pickaxe", 2);
    	MinecraftForge.setBlockHarvestLevel(LeadOre, "pickaxe", 2);
    	
    	GameRegistry.registerBlock(SuperCalc, "Super Computer");
    	LanguageRegistry.addName(SuperCalc, "Super Computer");
    	GameRegistry.addRecipe(new ItemStack(SuperCalc, 1), new Object[] {
    		"###", "#*#", "###", Character.valueOf('#'), Block.blockGold, Character.valueOf('*'), Item.netherStar
    	});
    	
    	GameRegistry.registerBlock(VirtualBlock, "Virtual Block");
    	LanguageRegistry.addName(VirtualBlock, "Virtual Block");
    	
    	LanguageRegistry.addName(Skid, "Skidbladnir");
    	
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
    	GameRegistry.addRecipe(new ItemStack(AelitaHelmet, 1), new Object[] {
    		"***", "*#*", Character.valueOf('*'), LyokoIngot, Character.valueOf('#'), new ItemStack(Item.dyePowder, 1, 9)
    	});
    	GameRegistry.addRecipe(new ItemStack(AelitaChest, 1), new Object[] {
    		"*#*", "***", "***", Character.valueOf('*'), LyokoIngot, Character.valueOf('#'), new ItemStack(Item.dyePowder, 1, 9)
    	});
    	GameRegistry.addRecipe(new ItemStack(AelitaLegs, 1), new Object[] {
    		"***", "*#*", "* *", Character.valueOf('*'), LyokoIngot, Character.valueOf('#'), new ItemStack(Item.dyePowder, 1, 9)
    	});
    	GameRegistry.addRecipe(new ItemStack(AelitaBoots, 1), new Object[] {
    		"*#*", "* *", Character.valueOf('*'), LyokoIngot, Character.valueOf('#'), new ItemStack(Item.dyePowder, 1, 9)
    	});
    	
    	LanguageRegistry.addName(OddHelmet, "Odd's Helmet");
    	LanguageRegistry.addName(OddChest, "Odd's Chestplate");
    	LanguageRegistry.addName(OddLegs, "Odd's Leggings");
    	LanguageRegistry.addName(OddBoots, "Odd's Boots");
    	GameRegistry.addRecipe(new ItemStack(OddHelmet, 1), new Object[] {
    		"***", "*#*", Character.valueOf('*'), LyokoIngot, Character.valueOf('#'), new ItemStack(Item.dyePowder, 1, 13)
    	});
    	GameRegistry.addRecipe(new ItemStack(OddChest, 1), new Object[] {
    		"*#*", "***", "***", Character.valueOf('*'), LyokoIngot, Character.valueOf('#'), new ItemStack(Item.dyePowder, 1, 13)
    	});
    	GameRegistry.addRecipe(new ItemStack(OddLegs, 1), new Object[] {
    		"***", "*#*", "* *", Character.valueOf('*'), LyokoIngot, Character.valueOf('#'), new ItemStack(Item.dyePowder, 1, 13)
    	});
    	GameRegistry.addRecipe(new ItemStack(OddBoots, 1), new Object[] {
    		"*#*", "* *", Character.valueOf('*'), LyokoIngot, Character.valueOf('#'), new ItemStack(Item.dyePowder, 1, 13)
    	});
    	
    	LanguageRegistry.addName(UlrichHelmet, "Ulrich's Helmet");
    	LanguageRegistry.addName(UlrichChest, "Ulrich's Chestplate");
    	LanguageRegistry.addName(UlrichLegs, "Ulrich's Leggings");
    	LanguageRegistry.addName(UlrichBoots, "Ulrich's Boots");
    	GameRegistry.addRecipe(new ItemStack(UlrichHelmet, 1), new Object[] {
    		"***", "*#*", Character.valueOf('*'), LyokoIngot, Character.valueOf('#'), new ItemStack(Item.dyePowder, 1, 11)
    	});
    	GameRegistry.addRecipe(new ItemStack(UlrichChest, 1), new Object[] {
    		"*#*", "***", "***", Character.valueOf('*'), LyokoIngot, Character.valueOf('#'), new ItemStack(Item.dyePowder, 1, 11)
    	});
    	GameRegistry.addRecipe(new ItemStack(UlrichLegs, 1), new Object[] {
    		"***", "*#*", "* *", Character.valueOf('*'), LyokoIngot, Character.valueOf('#'), new ItemStack(Item.dyePowder, 1, 11)
    	});
    	GameRegistry.addRecipe(new ItemStack(UlrichBoots, 1), new Object[] {
    		"*#*", "* *", Character.valueOf('*'), LyokoIngot, Character.valueOf('#'), new ItemStack(Item.dyePowder, 1, 11)
    	});
    	
    	LanguageRegistry.addName(YumiHelmet, "Yumi's Helmet");
    	LanguageRegistry.addName(YumiChest, "Yumi's Chestplate");
    	LanguageRegistry.addName(YumiLegs, "Yumi's Leggings");
    	LanguageRegistry.addName(YumiBoots, "Yumi's Boots");
    	GameRegistry.addRecipe(new ItemStack(YumiHelmet, 1), new Object[] {
    		"***", "*#*", Character.valueOf('*'), LyokoIngot, Character.valueOf('#'), new ItemStack(Item.dyePowder, 1, 5)
    	});
    	GameRegistry.addRecipe(new ItemStack(YumiChest, 1), new Object[] {
    		"*#*", "***", "***", Character.valueOf('*'), LyokoIngot, Character.valueOf('#'), new ItemStack(Item.dyePowder, 1, 5)
    	});
    	GameRegistry.addRecipe(new ItemStack(YumiLegs, 1), new Object[] {
    		"***", "*#*", "* *", Character.valueOf('*'), LyokoIngot, Character.valueOf('#'), new ItemStack(Item.dyePowder, 1, 5)
    	});
    	GameRegistry.addRecipe(new ItemStack(YumiBoots, 1), new Object[] {
    		"*#*", "* *", Character.valueOf('*'), LyokoIngot, Character.valueOf('#'), new ItemStack(Item.dyePowder, 1, 5)
    	});
    	
    	LanguageRegistry.addName(WilliamHelmet, "William's Helmet");
    	LanguageRegistry.addName(WilliamChest, "William's Chestplate");
    	LanguageRegistry.addName(WilliamLegs, "William's Leggings");
    	LanguageRegistry.addName(WilliamBoots, "William's Boots");
    	GameRegistry.addRecipe(new ItemStack(WilliamHelmet, 1), new Object[] {
    		"***", "*#*", Character.valueOf('*'), LyokoIngot, Character.valueOf('#'), new ItemStack(Item.dyePowder, 1, 12)
    	});
    	GameRegistry.addRecipe(new ItemStack(WilliamChest, 1), new Object[] {
    		"*#*", "***", "***", Character.valueOf('*'), LyokoIngot, Character.valueOf('#'), new ItemStack(Item.dyePowder, 1, 12)
    	});
    	GameRegistry.addRecipe(new ItemStack(WilliamLegs, 1), new Object[] {
    		"***", "*#*", "* *", Character.valueOf('*'), LyokoIngot, Character.valueOf('#'), new ItemStack(Item.dyePowder, 1, 12)
    	});
    	GameRegistry.addRecipe(new ItemStack(WilliamBoots, 1), new Object[] {
    		"*#*", "* *", Character.valueOf('*'), LyokoIngot, Character.valueOf('#'), new ItemStack(Item.dyePowder, 1, 12)
    	});
    	
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
    		" * ", " * ", " # ", Character.valueOf('*'), LyokoIngot, Character.valueOf('#'), Item.ingotIron
    	});
    	
    	LanguageRegistry.addName(Zweihander, "Zweihander");
    	GameRegistry.addShapelessRecipe(new ItemStack(Zweihander, 1), new Object[] {
    		ZweihanderFragment1, ZweihanderFragment2
    	});
    	GameRegistry.addRecipe(new ItemStack(Zweihander, 1), new Object[] {
    		"***", "***", " # ", Character.valueOf('*'), LyokoIngot, Character.valueOf('#'), Item.ingotIron
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
    		"###", "#a#", "#*#", Character.valueOf('*'), LyokoIngot, Character.valueOf('#'), Item.leather, Character.valueOf('a'), LaserArrow
    	});
    	
    	LanguageRegistry.addName(LaserArrow, "Laser Arrow");
    	GameRegistry.addRecipe(new ItemStack(LaserArrow, 1), new Object[] {
    		"a*a", "*#*", "a*a", Character.valueOf('*'), LyokoIngot, Character.valueOf('#'), Item.netherStar, Character.valueOf('a'), Item.arrow
    	});
    	
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
    	
    	
    	//Lyoko Sectors Dimension Register
    	DimensionManager.registerProviderType(this.Polar_Sector_ID, LyokoPolarSector.class, true);
    	DimensionManager.registerProviderType(this.Mountain_Sector_ID, LyokoMountainSector.class, true);
    	DimensionManager.registerProviderType(this.Forest_Sector_ID, LyokoForestSector.class, true);
    	DimensionManager.registerProviderType(this.Desert_Sector_ID, LyokoDesertSector.class, true);
    	DimensionManager.registerProviderType(this.Carthage_Sector_ID, LyokoCarthageSector.class, true);
    	DimensionManager.registerDimension(this.Polar_Sector_ID, this.Polar_Sector_ID);
    	DimensionManager.registerDimension(this.Mountain_Sector_ID, this.Mountain_Sector_ID);
    	DimensionManager.registerDimension(this.Forest_Sector_ID, this.Forest_Sector_ID);
    	DimensionManager.registerDimension(this.Desert_Sector_ID, this.Desert_Sector_ID);
    	DimensionManager.registerDimension(this.Carthage_Sector_ID, this.Carthage_Sector_ID);
    	
    	
    	
    	
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
    	EntityRegistry.registerGlobalEntityID(EntitySkid.class, "Skidbladnir", ModLoader.getUniqueEntityId(), 0xe3b434, 0x000000);
    	LanguageRegistry.instance().addStringLocalization("entity.Skidbladnir.name", "en_US", "Skidbladnir");
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
    	
    	EntityRegistry.addSpawn(matt.lyoko.entities.EntityBlok.class, 10, 3, 15, EnumCreatureType.monster, lyokocarthage, lyokoforest, lyokomountain, lyokopolar, lyokodesert);
    	EntityRegistry.addSpawn(matt.lyoko.entities.EntityMegaTank.class, 10, 3, 15, EnumCreatureType.monster, lyokocarthage, lyokoforest, lyokomountain, lyokopolar, lyokodesert);
    	EntityRegistry.addSpawn(matt.lyoko.entities.EntitySkid.class, 0, 0, 1, EnumCreatureType.creature, lyokocarthage);
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
    	Configuration config = new Configuration(preevt.getSuggestedConfigurationFile());
		config.load();
		
		Lyoko_Tower = config.getBlock("lyokoTower", 1154).getInt();
		Lyoko_Tower_Base = config.getBlock("lyokoTowerBase", 1155).getInt();
		Lyoko_Grass = config.getBlock("lyokoGrass", 1156).getInt();
		Lyoko_Stone = config.getBlock("lyokoStone", 1157).getInt();
		Lyoko_Sand = config.getBlock("lyokoSand", 1158).getInt();
		Lyoko_Ice = config.getBlock("lyokoIce", 1159).getInt();
		Lyoko_Log = config.getBlock("lyokoLog", 1160).getInt();
		Lyoko_Sea_Block = config.getBlock("lyokoSeaBlock", 1161).getInt();
		Lyoko_Sea_Flowing = config.getBlock("lyokoSeaFlowing", 1162).getInt();
		Lyoko_Sea_Still = config.getBlock("lyokoSeaStill", 1163).getInt();
		Lyoko_Virtual_Block = config.getBlock("lyokovirtualblock", 1164).getInt();
		Lyoko_Carthage = config.getBlock("lyokoCarthage", 1165).getInt();
		Lyoko_Ore = config.getBlock("lyokoOre", 1166).getInt();
		Lyoko_Polar_Portal = config.getBlock("lyokoPolarPortal", 1167).getInt();
		Lyoko_Desert_Portal = config.getBlock("lyokoDesertPortal", 1168).getInt();
		Lyoko_Forest_Portal = config.getBlock("lyokoForestPortal", 1169).getInt();
		Lyoko_Mountain_Portal = config.getBlock("lyokoMountainPortal", 1170).getInt();
		Lyoko_Carthage_Portal = config.getBlock("lyokoCarthagePortal", 1171).getInt();
		Lyoko_Over_Portal = config.getBlock("lyokoOverworldPortal", 1172).getInt();
		Lyoko_Super_Calc = config.getBlock("lyokoSuperCalculator", 1173).getInt();
		Lyoko_Lead_Ore = config.getBlock("lyokoLeadOre", 1174).getInt();
		Weapon_Lyoko_1 = config.getItem("weaponLyoko1", 6081).getInt();
		Weapon_Lyoko_2 = config.getItem("weaponLyoko2", 6082).getInt();
		Weapon_Lyoko_3 = config.getItem("weaponLyoko3", 6083).getInt();
		Weapon_Lyoko_4 = config.getItem("weaponLyoko4", 6084).getInt();
		Weapon_Lyoko_5 = config.getItem("weaponLyoko5", 6085).getInt();
		Weapon_Lyoko_6 = config.getItem("weaponLyoko6", 6086).getInt();
		Item_Lyoko_1 = config.getItem("itemLyoko1", 6087).getInt();
		Item_Lyoko_2 = config.getItem("itemLyoko2", 6088).getInt();
		Item_Lyoko_3 = config.getItem("itemLyoko3", 6089).getInt();
		Item_Lyoko_4 = config.getItem("itemLyoko4", 6090).getInt();
		Item_Lyoko_5 = config.getItem("itemLyoko5", 6091).getInt();
		Item_Lyoko_6 = config.getItem("itemLyoko6", 6092).getInt();
		Item_Lyoko_7 = config.getItem("itemLyoko7", 6093).getInt();
		Item_Lyoko_8 = config.getItem("itemLyoko8", 6094).getInt();
		Item_Lyoko_9 = config.getItem("itemLyoko9", 6095).getInt();
		Item_Lyoko_10 = config.getItem("itemLyoko10", 6096).getInt();
		Item_Skid = config.getItem("itemSkid", 6097).getInt();
		Item_Lyoko_11 = config.getItem("itemLyoko11", 6098).getInt();
		Item_Lyoko_12 = config.getItem("itemLyoko12", 6099).getInt();
		Item_Lyoko_13 = config.getItem("itemLyoko13", 6100).getInt();
		Item_Lyoko_14 = config.getItem("itemLyoko14", 6101).getInt();
		Item_Lyoko_15 = config.getItem("itemLyoko15", 6102).getInt();
		Aelita_Armor_Helmet = config.getItem("aelitaArmorHelmet", 6103).getInt();
		Aelita_Armor_Chest = config.getItem("aelitaArmorChest", 6104).getInt();
		Aelita_Armor_Pants = config.getItem("aelitaArmorPants", 6105).getInt();
		Aelita_Armor_Boots = config.getItem("aelitaArmorBoots", 6106).getInt();
		Odd_Armor_Helmet = config.getItem("oddArmorHelmet", 6107).getInt();
		Odd_Armor_Chest = config.getItem("oddArmorChest", 6108).getInt();
		Odd_Armor_Pants = config.getItem("oddArmorPants", 6109).getInt();
		Odd_Armor_Boots = config.getItem("oddArmorBoots", 6110).getInt();
		Ulrich_Armor_Helmet = config.getItem("ulrichArmorHelmet", 6111).getInt();
		Ulrich_Armor_Chest = config.getItem("ulrichArmorChest", 6112).getInt();
		Ulrich_Armor_Pants = config.getItem("ulrichArmorPants", 6113).getInt();
		Ulrich_Armor_Boots = config.getItem("ulrichArmorBoots", 6114).getInt();
		Yumi_Armor_Helmet = config.getItem("yumiArmorHelmet", 6115).getInt();
		Yumi_Armor_Chest = config.getItem("yumiArmorChest", 6116).getInt();
		Yumi_Armor_Pants = config.getItem("yumiArmorPants", 6117).getInt();
		Yumi_Armor_Boots = config.getItem("yumiArmorBoots", 6118).getInt();
		William_Armor_Helmet = config.getItem("williamArmorHelmet", 6119).getInt();
		William_Armor_Chest = config.getItem("williamArmorChest", 6120).getInt();
		William_Armor_Pants = config.getItem("williamArmorPants", 6121).getInt();
		William_Armor_Boots = config.getItem("williamArmorBoots", 6122).getInt();
		
		/**
		 * taken from my other mod so I can add booleans if needed to the config file
		 */
        //canCraftMoney = config.get(Configuration.CATEGORY_GENERAL, "canCraftMoney", true).getBoolean(true);
		
		Polar_Sector_ID = config.get(Configuration.CATEGORY_GENERAL, "polarSectorID", 3).getInt();
		Mountain_Sector_ID = config.get(Configuration.CATEGORY_GENERAL, "mountainSectorID", 4).getInt();
		Forest_Sector_ID = config.get(Configuration.CATEGORY_GENERAL, "forestSectorID", 5).getInt();
		Desert_Sector_ID = config.get(Configuration.CATEGORY_GENERAL, "desertSectorID", 6).getInt();
		Carthage_Sector_ID = config.get(Configuration.CATEGORY_GENERAL, "carthageSectorID", 8).getInt();
		
		config.save();
	}
    
    @PostInit
    public void CodeLyokoPostLoad(FMLPostInitializationEvent postevt)
    {
    	
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
        addDungeonLoot(LaserArrow, 005, 1, 1);
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
