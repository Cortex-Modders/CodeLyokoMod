package matt.lyoko;

import matt.lyoko.blocks.BlockDigitalSea;
import matt.lyoko.blocks.BlockFlowingDigitalSea;
import matt.lyoko.blocks.BlockLyoko;
import matt.lyoko.blocks.BlockLyokoIce;
import matt.lyoko.blocks.BlockLyokoTower;
import matt.lyoko.blocks.BlockLyokoVirtual;
import matt.lyoko.blocks.BlockStationaryDigitalSea;
import matt.lyoko.blocks.BlockSuperCalc;
import matt.lyoko.blocks.BlockTowerBase;
import matt.lyoko.client.GuiHandler;
import matt.lyoko.entities.EntityEnergyField;
import matt.lyoko.entities.EntityFan;
import matt.lyoko.entities.EntityLaser;
import matt.lyoko.entities.EntityLaserArrow;
import matt.lyoko.entities.EntityLyokoRanged;
import matt.lyoko.entities.TileEntityDigitalSea;
import matt.lyoko.entities.TileEntitySuperCalc;
import matt.lyoko.entities.TileEntityVirtualBlock;
import matt.lyoko.entities.mobs.EntityBlok;
import matt.lyoko.entities.mobs.EntityMegaTank;
import matt.lyoko.entities.vehicles.EntityOverboard;
import matt.lyoko.entities.vehicles.EntitySkid;
import matt.lyoko.items.ArmorLyoko;
import matt.lyoko.items.ItemBlockEffect;
import matt.lyoko.items.ItemBlockVirtual;
import matt.lyoko.items.ItemDataFragment;
import matt.lyoko.items.ItemLyoko;
import matt.lyoko.items.ItemLyokoFuel;
import matt.lyoko.items.ItemLyokoRanged;
import matt.lyoko.items.ItemLyokoSword;
import matt.lyoko.items.ItemOverboard;
import matt.lyoko.items.ItemSkid;
import matt.lyoko.network.PacketHandler;
import matt.lyoko.world.BiomeGenBaseLyoko;
import matt.lyoko.world.BiomeGenCarthageSector;
import matt.lyoko.world.BiomeGenDesertSector;
import matt.lyoko.world.BiomeGenForestSector;
import matt.lyoko.world.BiomeGenMountainSector;
import matt.lyoko.world.BiomeGenPolarSector;
import matt.lyoko.world.LyokoCarthageSector;
import matt.lyoko.world.LyokoDesertSector;
import matt.lyoko.world.LyokoForestSector;
import matt.lyoko.world.LyokoMountainSector;
import matt.lyoko.world.LyokoPolarSector;
import matt.lyoko.world.WorldGenLyokoOre;
import matt.lyoko.world.WorldGenTower;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.EnumHelper;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid = "CodeLyoko", name="Code Lyoko", version="0.4.3-Beta")
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
	public static int Lyoko_Super_Calc;// = 1173;
	public static int Lyoko_Lead_Ore;// = 1174;
	public static int Lyoko_Uranium_Ore;
	
	public static int Weapon_Lyoko_1;// = 6081;
	public static int Weapon_Lyoko_2;// = 6082;
	public static int Weapon_Lyoko_3;// = 6083;
	public static int Weapon_Lyoko_4;// = 6084;
	public static int Weapon_Lyoko_5;// = 6085;
	public static int Weapon_Lyoko_6;// = 6086;
	public static int Item_Skid;// = 6087;
	public static int NOT_USED1;// = 6088;
	public static int NOT_USED2;// = 6089;
	public static int NOT_USED3;// = 6090;
	public static int NOT_USED4;// = 6091;
	public static int NOT_USED5;// = 6092;
	public static int NOT_USED6;// = 6093;
	public static int NOT_USED7;// = 6094;
	public static int NOT_USED8;// = 6095;
	public static int NOT_USED9;// = 6096;
	public static int Item_Lyoko_Uranium;// = 6097;
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
	public static int Data_Fragment;
	public static int Item_Lyoko_Uranium_Cell;
	public static int Item_Lyoko_Depleted_Uranium;
	public static int Item_Overboard;
	
	public static int Polar_Sector_ID;
	public static int Mountain_Sector_ID;
	public static int Forest_Sector_ID;
	public static int Desert_Sector_ID;
	public static int Carthage_Sector_ID;
	//public static int TEST_DIMENSION_ID_THINGY_THAT_IS_NOT_USED = DimensionManager.getNextFreeDimId();

	public static int SuperCalcRenderID;
	public static int SuperCalcTexture;
	static EnumToolMaterial toolLYOKO = EnumHelper.addToolMaterial("LYOKO", 3, 100, 14F, 30, 30);
	
	static EnumArmorMaterial armorLYOKO = EnumHelper.addArmorMaterial("LYOKO", 40, new int[] {5, 10, 8, 5}, 30);
	
	public static final Material LyokoTower = new Material(MapColor.ironColor);
	public static CreativeTabs LyokoTabs = new LyokoTab("LyokoTabs");
	public static final BiomeGenBase lyokomountain = ((BiomeGenBaseLyoko) (new BiomeGenMountainSector(9)).setColor(8421631)).setLyokoBiomeName("Mountain Sector");
	public static final BiomeGenBase lyokoforest = ((BiomeGenBaseLyoko) (new BiomeGenForestSector(10)).setColor(8421631)).setLyokoBiomeName("Forest Sector");
	public static final BiomeGenBase lyokodesert = ((BiomeGenBaseLyoko) (new BiomeGenDesertSector(11)).setColor(8421631)).setLyokoBiomeName("Desert Sector");
	public static final BiomeGenBase lyokopolar = ((BiomeGenBaseLyoko) (new BiomeGenPolarSector(12)).setColor(8421631)).setLyokoBiomeName("Polar Sector");
	public static final BiomeGenBase lyokocarthage = ((BiomeGenBaseLyoko) (new BiomeGenCarthageSector(13)).setColor(8421631)).setLyokoBiomeName("Carthage Sector");
	public static Item Katana;// = new ItemLyokoSword(Weapon_Lyoko_1, toolLYOKO).setUnlocalizedName("Katana").setIconIndex(0);
	public static Item Zweihander;// = new ItemLyokoSword(Weapon_Lyoko_2, toolLYOKO).setUnlocalizedName("Zweihander").setIconIndex(1);
	public static Item Fan;// = new ItemLyokoFan(Weapon_Lyoko_3).setUnlocalizedName("Fan").setIconIndex(2);
	public static Item EnergyField;// = new ItemLyokoEnergyField(Weapon_Lyoko_4).setUnlocalizedName("EnergyField").setIconIndex(3);
	public static Item Glove;// = new ItemGlove(Weapon_Lyoko_5).setUnlocalizedName("Glove").setIconIndex(4);
	public static Item LaserArrow;// = new ItemLyoko(Weapon_Lyoko_6).setUnlocalizedName("LaserArrow").setIconIndex(5);
	public static Item LyokoIngot;// = new ItemLyoko(Item_Lyoko_11).setUnlocalizedName("LyokoIngot").setIconIndex(16);
	public static Item LyokoLead;// = new ItemLyoko(Item_Lyoko_12).setUnlocalizedName("Lead210").setIconIndex(17);
	public static Item LyokoCell;// = new ItemLyoko(Item_Lyoko_13).setUnlocalizedName("Cell").setIconIndex(18);
	public static Item LyokoLeadCell;// = new ItemLyokoFuel(Item_Lyoko_14, 10000).setUnlocalizedName("Lead210Cell").setIconIndex(19);
	public static Item LyokoDepletedLeadCell;// = new ItemLyoko(Item_Lyoko_15).setUnlocalizedName("DepletedLead210Cell").setIconIndex(20);
	public static Item AelitaHelmet;// = new ArmorLyoko(Aelita_Armor_Helmet, armorLYOKO, 5, 0, "Aelita").setIconIndex(21).setUnlocalizedName("AelitaHelmet");
    public static Item AelitaChest;// = new ArmorLyoko(Aelita_Armor_Chest, armorLYOKO, 5, 1, "Aelita").setIconIndex(22).setUnlocalizedName("AelitaChest");
    public static Item AelitaLegs;// = new ArmorLyoko(Aelita_Armor_Pants, armorLYOKO, 5, 2, "Aelita").setIconIndex(23).setUnlocalizedName("AelitaPants");
    public static Item AelitaBoots;// = new ArmorLyoko(Aelita_Armor_Boots, armorLYOKO, 5, 3, "Aelita").setIconIndex(24).setUnlocalizedName("AelitaBoots");
    public static Item OddHelmet;// = new ArmorLyoko(Odd_Armor_Helmet, armorLYOKO, 6, 0, "Odd").setIconIndex(25).setUnlocalizedName("OddHelmet");
    public static Item OddChest;// = new ArmorLyoko(Odd_Armor_Chest, armorLYOKO, 6, 1, "Odd").setIconIndex(26).setUnlocalizedName("OddChest");
    public static Item OddLegs;// = new ArmorLyoko(Odd_Armor_Pants, armorLYOKO, 6, 2, "Odd").setIconIndex(27).setUnlocalizedName("OddPants");
    public static Item OddBoots;// = new ArmorLyoko(Odd_Armor_Boots, armorLYOKO, 6, 3, "Odd").setIconIndex(28).setUnlocalizedName("OddBoots");
    public static Item UlrichHelmet;// = new ArmorLyoko(Ulrich_Armor_Helmet, armorLYOKO, 7, 0, "Ulrich").setIconIndex(29).setUnlocalizedName("UlrichHelmet");
    public static Item UlrichChest;// = new ArmorLyoko(Ulrich_Armor_Chest, armorLYOKO, 7, 1, "Ulrich").setIconIndex(30).setUnlocalizedName("UlrichChest");
    public static Item UlrichLegs;// = new ArmorLyoko(Ulrich_Armor_Pants, armorLYOKO, 7, 2, "Ulrich").setIconIndex(31).setUnlocalizedName("UlrichPants");
    public static Item UlrichBoots;// = new ArmorLyoko(Ulrich_Armor_Boots, armorLYOKO, 7, 3, "Ulrich").setIconIndex(32).setUnlocalizedName("UlrichBoots");
    public static Item YumiHelmet;// = new ArmorLyoko(Yumi_Armor_Helmet, armorLYOKO, 8, 0, "Yumi").setIconIndex(33).setUnlocalizedName("YumiHelmet");
    public static Item YumiChest;// = new ArmorLyoko(Yumi_Armor_Chest, armorLYOKO, 8, 1, "Yumi").setIconIndex(34).setUnlocalizedName("YumiChest");
    public static Item YumiLegs;// = new ArmorLyoko(Yumi_Armor_Pants, armorLYOKO, 8, 2, "Yumi").setIconIndex(35).setUnlocalizedName("YumiPants");
    public static Item YumiBoots;// = new ArmorLyoko(Yumi_Armor_Boots, armorLYOKO, 8, 3, "Yumi").setIconIndex(36).setUnlocalizedName("YumiBoots");
    public static Item WilliamHelmet;// = new ArmorLyoko(William_Armor_Helmet, armorLYOKO, 9, 0, "William").setIconIndex(37).setUnlocalizedName("WilliamHelmet");
    public static Item WilliamChest;// = new ArmorLyoko(William_Armor_Chest, armorLYOKO, 9, 1, "William").setIconIndex(38).setUnlocalizedName("WilliamChest");
    public static Item WilliamLegs;// = new ArmorLyoko(William_Armor_Pants, armorLYOKO, 9, 2, "William").setIconIndex(39).setUnlocalizedName("WilliamPants");
    public static Item WilliamBoots;// = new ArmorLyoko(William_Armor_Boots, armorLYOKO, 9, 3, "William").setIconIndex(40).setUnlocalizedName("WilliamBoots");
    public static Item DataFragment;
    public static Item Uranium;
    public static Item LyokoUraniumCell;
    public static Item LyokoDepletedUraniumCell;
    public static Item Overboard;
    public static Item Skid;
	public static Block TowerBlock;// = new BlockLyoko(Lyoko_Tower, 0).setResistance(6000000F).setBlockUnbreakable().setLightValue(7F).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("TowerBlock");
	public static Block TowerBase;// = new BlockTowerBase(Lyoko_Tower_Base, 1, false).setResistance(6000000F).setBlockUnbreakable().setLightValue(7F).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("TowerBase");
	public static Block LyokoGrass;// = new BlockLyoko(Lyoko_Grass, 2).setResistance(6000000F).setBlockUnbreakable().setStepSound(Block.soundGrassFootstep).setUnlocalizedName("LyokoGrass");
	public static Block LyokoStone;// = new BlockLyoko(Lyoko_Stone, 3).setResistance(6000000F).setBlockUnbreakable().setStepSound(Block.soundStoneFootstep).setUnlocalizedName("LyokoStone");
	public static Block LyokoSand;// = new BlockLyoko(Lyoko_Sand, 4).setResistance(6000000F).setBlockUnbreakable().setStepSound(Block.soundSandFootstep).setUnlocalizedName("LyokoSand");
	public static Block LyokoIce;// = new BlockLyokoIce(Lyoko_Ice, 5, Material.glass, false).setResistance(6000000F).setBlockUnbreakable().setLightOpacity(3).setStepSound(Block.soundGlassFootstep).setUnlocalizedName("LyokoIce");
	public static Block LyokoLog;// = new BlockLyoko(Lyoko_Log, 6).setResistance(6000000F).setBlockUnbreakable().setStepSound(Block.soundWoodFootstep).setUnlocalizedName("LyokoLog");
	public static Block LyokoCarthage;// = new BlockLyoko(Lyoko_Carthage, 7).setResistance(6000000F).setBlockUnbreakable().setStepSound(Block.soundMetalFootstep).setUnlocalizedName("LyokoCarthage");
	public static Block LyokoOre;// = new BlockLyoko(Lyoko_Ore, 8).setHardness(10F).setResistance(20F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("LyokoOre");
	public static Block DigitalSeaBlock;// = new BlockDigitalSea(Lyoko_Sea_Block, 9).setResistance(6000000F).setBlockUnbreakable().setUnlocalizedName("DigitalSeaBlock");
	public static Block DigitalSeaFlowing;// = new BlockFlowingDigitalSea(Lyoko_Sea_Flowing, Material.water).setHardness(100F).setLightOpacity(3).setUnlocalizedName("DigitalSeaFlowing").setRequiresSelfNotify();
	public static Block DigitalSeaStill;// = new BlockStationaryDigitalSea(Lyoko_Sea_Still, Material.water).setHardness(100F).setLightOpacity(3).setUnlocalizedName("DigitalSeaStill").setRequiresSelfNotify();
	public static Block LeadOre;// = new BlockLyoko(Lyoko_Lead_Ore, 10).setHardness(10F).setResistance(20F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("LeadOre").setLightValue(10F);
	public static Block VirtualBlock;
	public static Block UraniumOre;
	public static Block LyokoPolarPortal;//  = new BlockLyoko(Lyoko_Polar_Portal, 12).setUnlocalizedName("Polar Portal");
	public static Block LyokoDesertPortal;// = new BlockLyoko(Lyoko_Desert_Portal, 13).setUnlocalizedName("Desert Portal");
	public static Block LyokoForestPortal;// = new BlockLyoko(Lyoko_Forest_Portal, 14).setUnlocalizedName("Forest Portal");
	public static Block LyokoMountainPortal;// = new BlockLyoko(Lyoko_Mountain_Portal, 15).setUnlocalizedName("Mountain Portal");
	public static Block LyokoCarthagePortal;//  = new BlockLyoko(Lyoko_Carthage_Portal, 16).setUnlocalizedName("Carthage Portal");
	public static Block SuperCalc;// = new BlockSuperCalc(Lyoko_Super_Calc).setHardness(20).setResistance(6000000).setUnlocalizedName("Super Computer").setRequiresSelfNotify();
	
	@SidedProxy(clientSide = "matt.lyoko.ClientProxy", serverSide = "matt.lyoko.CommonProxy")
	public static CommonProxy proxy; //This object will be populated with the class that you choose for the environment
	@Instance
	public static CodeLyoko instance;//the instance of the mod that will be defined, populated, and callable
	
    @Init
    public void CodeLyokoLoad(FMLInitializationEvent evt)
    {
    	Katana = new ItemLyokoSword(Weapon_Lyoko_1, toolLYOKO).setUnlocalizedName("Katana");
    	Zweihander = new ItemLyokoSword(Weapon_Lyoko_2, toolLYOKO).setUnlocalizedName("Zweihander");
    	Fan = new ItemLyokoRanged(Weapon_Lyoko_3, EntityFan.class, Fan, "fan").setUnlocalizedName("Fan");
    	EnergyField = new ItemLyokoRanged(Weapon_Lyoko_4, EntityEnergyField.class, EnergyField, "energyfield").setUnlocalizedName("EnergyField");
    	Glove = new ItemLyokoRanged(Weapon_Lyoko_5, EntityLaserArrow.class, Glove, "glove").setUnlocalizedName("Glove");
    	LaserArrow = new ItemLyoko(Weapon_Lyoko_6).setUnlocalizedName("LaserArrow");
    	LyokoIngot = new ItemLyoko(Item_Lyoko_11).setUnlocalizedName("LyokoIngot");
    	LyokoLead = new ItemLyoko(Item_Lyoko_12).setUnlocalizedName("Lead210");
    	LyokoCell = new ItemLyoko(Item_Lyoko_13).setUnlocalizedName("Cell");
    	LyokoLeadCell = new ItemLyokoFuel(Item_Lyoko_14, 1250, LyokoDepletedLeadCell).setUnlocalizedName("Lead210Cell");
    	LyokoDepletedLeadCell = new ItemLyoko(Item_Lyoko_15).setUnlocalizedName("DepletedLead210Cell");
    	AelitaHelmet = new ArmorLyoko(Aelita_Armor_Helmet, armorLYOKO, 5, 0, "aelita").setUnlocalizedName("AelitaHelmet");
        AelitaChest = new ArmorLyoko(Aelita_Armor_Chest, armorLYOKO, 5, 1, "aelita").setUnlocalizedName("AelitaChest");
        AelitaLegs = new ArmorLyoko(Aelita_Armor_Pants, armorLYOKO, 5, 2, "aelita").setUnlocalizedName("AelitaPants");
        AelitaBoots = new ArmorLyoko(Aelita_Armor_Boots, armorLYOKO, 5, 3, "aelita").setUnlocalizedName("AelitaBoots");
        OddHelmet = new ArmorLyoko(Odd_Armor_Helmet, armorLYOKO, 6, 0, "odd").setUnlocalizedName("OddHelmet");
        OddChest = new ArmorLyoko(Odd_Armor_Chest, armorLYOKO, 6, 1, "odd").setUnlocalizedName("OddChest");
        OddLegs = new ArmorLyoko(Odd_Armor_Pants, armorLYOKO, 6, 2, "odd").setUnlocalizedName("OddPants");
        OddBoots = new ArmorLyoko(Odd_Armor_Boots, armorLYOKO, 6, 3, "odd").setUnlocalizedName("OddBoots");
        UlrichHelmet = new ArmorLyoko(Ulrich_Armor_Helmet, armorLYOKO, 7, 0, "ulrich").setUnlocalizedName("UlrichHelmet");
        UlrichChest = new ArmorLyoko(Ulrich_Armor_Chest, armorLYOKO, 7, 1, "ulrich").setUnlocalizedName("UlrichChest");
        UlrichLegs = new ArmorLyoko(Ulrich_Armor_Pants, armorLYOKO, 7, 2, "ulrich").setUnlocalizedName("UlrichPants");
        UlrichBoots = new ArmorLyoko(Ulrich_Armor_Boots, armorLYOKO, 7, 3, "ulrich").setUnlocalizedName("UlrichBoots");
        YumiHelmet = new ArmorLyoko(Yumi_Armor_Helmet, armorLYOKO, 8, 0, "yumi").setUnlocalizedName("YumiHelmet");
        YumiChest = new ArmorLyoko(Yumi_Armor_Chest, armorLYOKO, 8, 1, "yumi").setUnlocalizedName("YumiChest");
        YumiLegs = new ArmorLyoko(Yumi_Armor_Pants, armorLYOKO, 8, 2, "yumi").setUnlocalizedName("YumiPants");
        YumiBoots = new ArmorLyoko(Yumi_Armor_Boots, armorLYOKO, 8, 3, "yumi").setUnlocalizedName("YumiBoots");
        WilliamHelmet = new ArmorLyoko(William_Armor_Helmet, armorLYOKO, 9, 0, "william").setUnlocalizedName("WilliamHelmet");
        WilliamChest = new ArmorLyoko(William_Armor_Chest, armorLYOKO, 9, 1, "william").setUnlocalizedName("WilliamChest");
        WilliamLegs = new ArmorLyoko(William_Armor_Pants, armorLYOKO, 9, 2, "william").setUnlocalizedName("WilliamPants");
        WilliamBoots = new ArmorLyoko(William_Armor_Boots, armorLYOKO, 9, 3, "william").setUnlocalizedName("WilliamBoots");
        DataFragment = new ItemDataFragment(Data_Fragment).setUnlocalizedName("DataFragment");
        Uranium = new ItemLyoko(Item_Lyoko_Uranium).setUnlocalizedName("Uranium");
    	LyokoUraniumCell = new ItemLyokoFuel(Item_Lyoko_Uranium_Cell, 5000, LyokoDepletedUraniumCell).setUnlocalizedName("UraniumCell");
    	LyokoDepletedUraniumCell = new ItemLyoko(Item_Lyoko_Depleted_Uranium).setUnlocalizedName("DepletedUraniumCell");
    	Overboard = new ItemOverboard(Item_Overboard).setUnlocalizedName("Overboard");
    	Skid = new ItemSkid(Item_Skid).setUnlocalizedName("Skidbladnir");
    	TowerBlock = new BlockLyokoTower(Lyoko_Tower).setResistance(6000000F).setBlockUnbreakable().setLightValue(7F).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("TowerBlock");
    	TowerBase = new BlockTowerBase(Lyoko_Tower_Base, "towerBase", false).setResistance(6000000F).setBlockUnbreakable().setLightValue(7F).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("TowerBase");
    	LyokoGrass = new BlockLyoko(Lyoko_Grass).setResistance(6000000F).setBlockUnbreakable().setStepSound(Block.soundGrassFootstep).setUnlocalizedName("LyokoGrass");
    	LyokoStone = new BlockLyoko(Lyoko_Stone).setResistance(6000000F).setBlockUnbreakable().setStepSound(Block.soundStoneFootstep).setUnlocalizedName("LyokoStone");
    	LyokoSand = new BlockLyoko(Lyoko_Sand).setResistance(6000000F).setBlockUnbreakable().setStepSound(Block.soundSandFootstep).setUnlocalizedName("LyokoSand");
    	LyokoIce = new BlockLyokoIce(Lyoko_Ice, "lyokoIce", Material.glass, false).setResistance(6000000F).setBlockUnbreakable().setLightOpacity(3).setStepSound(Block.soundGlassFootstep).setUnlocalizedName("LyokoIce");
    	LyokoLog = new BlockLyoko(Lyoko_Log).setResistance(6000000F).setBlockUnbreakable().setStepSound(Block.soundWoodFootstep).setUnlocalizedName("LyokoLog");
    	LyokoCarthage = new BlockLyoko(Lyoko_Carthage).setResistance(6000000F).setBlockUnbreakable().setStepSound(Block.soundMetalFootstep).setUnlocalizedName("LyokoCarthage");
    	LyokoOre = new BlockLyoko(Lyoko_Ore).setHardness(10F).setResistance(20F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("LyokoOre");
    	DigitalSeaBlock = new BlockDigitalSea(Lyoko_Sea_Block).setResistance(6000000F).setBlockUnbreakable().setUnlocalizedName("DigitalSeaBlock");
    	DigitalSeaFlowing = new BlockFlowingDigitalSea(Lyoko_Sea_Flowing, Material.water).setHardness(100F).setLightOpacity(3).setUnlocalizedName("DigitalSeaFlowing");
    	DigitalSeaStill = new BlockStationaryDigitalSea(Lyoko_Sea_Still, Material.water).setHardness(100F).setLightOpacity(3).setUnlocalizedName("DigitalSeaStill");
    	LeadOre = new BlockLyoko(Lyoko_Lead_Ore).setHardness(10F).setResistance(20F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("LeadOre").setLightValue(10F);
    	SuperCalc = new BlockSuperCalc(Lyoko_Super_Calc).setHardness(20).setResistance(6000000).setUnlocalizedName("Super Computer");
    	VirtualBlock = new BlockLyokoVirtual(Lyoko_Virtual_Block).setResistance(1.0F).setHardness(1.0F).setStepSound(Block.soundGlassFootstep).setUnlocalizedName("LyokoVirtualBlock");
    	UraniumOre = new BlockLyoko(Lyoko_Uranium_Ore).setHardness(10F).setResistance(20F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("UraniumOre").setLightValue(10F);
    	
    	LyokoPolarPortal  = new BlockLyoko(Lyoko_Polar_Portal).setUnlocalizedName("PolarPortal").setCreativeTab(null);
    	LyokoDesertPortal = new BlockLyoko(Lyoko_Desert_Portal).setUnlocalizedName("DesertPortal").setCreativeTab(null);
    	LyokoForestPortal = new BlockLyoko(Lyoko_Forest_Portal).setUnlocalizedName("ForestPortal").setCreativeTab(null);
    	LyokoMountainPortal = new BlockLyoko(Lyoko_Mountain_Portal).setUnlocalizedName("MountainPortal").setCreativeTab(null);
    	LyokoCarthagePortal  = new BlockLyoko(Lyoko_Carthage_Portal).setUnlocalizedName("CarthagePortal").setCreativeTab(null);
    	
    	//TODO Give mod owners special ability
    	//Matthew = Aelita
    	//Marq = Odd
    	//Andrew = Odd (Jeremy)
    	proxy.registerRenderInformation(); //You have to call the methods in your proxy class
    	proxy.registerServerTickHandler();
    	proxy.registerKeyBindingHandler();
    	proxy.registerOres();
    	proxy.registerFragmentRecipes();
    	
    	NetworkRegistry.instance().registerGuiHandler(this, new GuiHandler());
    	
    	GameRegistry.registerTileEntity(TileEntitySuperCalc.class, "teSuperCalc");
    	GameRegistry.registerTileEntity(TileEntityDigitalSea.class, "teDigitalSea");
    	GameRegistry.registerTileEntity(TileEntityVirtualBlock.class, "teVirtualBlock");
    	
    	GameRegistry.registerWorldGenerator(new WorldGenLyokoOre());
    	GameRegistry.registerWorldGenerator(new WorldGenTower());
    	
    	MinecraftForge.setBlockHarvestLevel(LyokoOre, "pickaxe", 2);
    	MinecraftForge.setBlockHarvestLevel(LeadOre, "pickaxe", 2);
    	
    	GameRegistry.registerBlock(SuperCalc, "Super Computer");
    	LanguageRegistry.addName(SuperCalc, "Super Computer");
    	GameRegistry.addRecipe(new ItemStack(SuperCalc, 1), new Object[] {
    		"###", "#*#", "###", Character.valueOf('#'), Block.blockGold, Character.valueOf('*'), Item.netherStar
    	});
    	
    	LanguageRegistry.addName(Overboard, "Overboard");
    	/*GameRegistry.addRecipe(new ItemStack(Overboard, 1), new Object[] {
    		"h*c", "$#$", "bml", Character.valueOf('*'), Item.boat, Character.valueOf('m'), Item.minecartEmpty, Character.valueOf('#'), Item.netherStar,
    		Character.valueOf('$'), DataFragment, Character.valueOf('h'), OddHelmet, Character.valueOf('c'), OddChest,
    		Character.valueOf('l'), OddLegs, Character.valueOf('b'), OddBoots
    	});*/
    	
    	LanguageRegistry.addName(Skid, "Skidbladnir");
    	/*GameRegistry.addRecipe(new ItemStack(Skid, 1), new Object[] {
    		"h*c", "$#$", "bml", Character.valueOf('*'), Item.boat, Character.valueOf('m'), Item.minecartEmpty, Character.valueOf('#'), Item.netherStar,
    		Character.valueOf('$'), DataFragment, Character.valueOf('h'), OddHelmet, Character.valueOf('c'), OddChest,
    		Character.valueOf('l'), OddLegs, Character.valueOf('b'), OddBoots
    	});*/
    	
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
    	
    	LanguageRegistry.addName(DataFragment, "Data Fragment");
    	
    	LanguageRegistry.addName(Uranium, "Uranium");
    	GameRegistry.addSmelting(UraniumOre.blockID, new ItemStack(Uranium, 1), 5F);
    	
    	LanguageRegistry.addName(LyokoUraniumCell, "Uranium Fuel Cell");
    	CraftingManager.getInstance().getRecipeList().add(new ShapelessOreRecipe(new ItemStack(LyokoUraniumCell),"ingotUranium",
    			LyokoCell));
    	CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(LyokoUraniumCell, 2),"*#*",
    			Character.valueOf('*'), LyokoDepletedUraniumCell, Character.valueOf('#'), "ingotUranium"));
    	
    	LanguageRegistry.addName(LyokoDepletedUraniumCell, "Depleted Uranium Fuel Cell");
    	
    	LanguageRegistry.addName(LyokoLead, "Lead Isotope 210");
    	GameRegistry.addSmelting(LeadOre.blockID, new ItemStack(LyokoLead, 1), 5F);
    	
    	LanguageRegistry.addName(LyokoCell, "Empty Fuel Cell");
    	GameRegistry.addRecipe(new ItemStack(LyokoCell, 16), new Object[] {
    		" # ", "#*#", " # ", Character.valueOf('*'), Block.glass, Character.valueOf('#'), Item.ingotIron
    	});
    	GameRegistry.addShapelessRecipe(new ItemStack(LyokoCell, 1), new Object[] {
    		LyokoDepletedLeadCell
    	});
    	GameRegistry.addShapelessRecipe(new ItemStack(LyokoCell, 1), new Object[] {
    		LyokoDepletedUraniumCell
    	});
    	
    	LanguageRegistry.addName(LyokoLeadCell, "Lead Isotope 210 Fuel Cell");
    	CraftingManager.getInstance().getRecipeList().add(new ShapelessOreRecipe(new ItemStack(LyokoLeadCell),"ingotRadioactiveLead",
    			LyokoCell));
    	CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(LyokoLeadCell, 2),"*#*",
    			Character.valueOf('*'), LyokoDepletedLeadCell, Character.valueOf('#'), "ingotRadioactiveLead"));
    	
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
    	
    	LanguageRegistry.addName(Katana, "Katana");
    	/*GameRegistry.addRecipe(new ItemStack(Katana, 1), new Object[] {
    		" * ", " * ", " # ", Character.valueOf('*'), LyokoIngot, Character.valueOf('#'), Item.ingotIron
    	});*/
    	
    	LanguageRegistry.addName(Zweihander, "Zweihander");
    	/*GameRegistry.addRecipe(new ItemStack(Zweihander, 1), new Object[] {
    		"***", "***", " # ", Character.valueOf('*'), LyokoIngot, Character.valueOf('#'), Item.ingotIron
    	});*/
    	
    	LanguageRegistry.addName(Fan, "Fan");
    	/*GameRegistry.addRecipe(new ItemStack(Fan, 1), new Object[] {
    		"#f ", "#*f", "###", Character.valueOf('*'), LyokoIngot, Character.valueOf('#'), Item.stick, Character.valueOf('f'), Item.feather
    	});*/
    	
    	LanguageRegistry.addName(EnergyField, "Energy Field");
    	/*GameRegistry.addRecipe(new ItemStack(EnergyField, 1), new Object[] {
    		" * ", "*#*", " * ", Character.valueOf('*'), LyokoIngot, Character.valueOf('#'), Item.netherStar
    	});*/
    	
    	LanguageRegistry.addName(Glove, "Laser Arrow Glove");
    	/*GameRegistry.addRecipe(new ItemStack(Glove, 1), new Object[] {
    		"###", "#a#", "#*#", Character.valueOf('*'), LyokoIngot, Character.valueOf('#'), Item.leather, Character.valueOf('a'), LaserArrow
    	});*/
    	
    	LanguageRegistry.addName(LaserArrow, "Laser Arrow");
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
    	
    	GameRegistry.registerBlock(LyokoOre, ItemBlockEffect.class, "Lyoko Ore");
    	LanguageRegistry.addName(LyokoOre, "Lyoko Ore");
    	
    	GameRegistry.registerBlock(DigitalSeaFlowing, "Digital Sea Flowing");
    	LanguageRegistry.addName(DigitalSeaFlowing, "Digital Sea Flowing");
    	
    	GameRegistry.registerBlock(DigitalSeaStill, "Digital Sea Still");
    	LanguageRegistry.addName(DigitalSeaStill, "Digital Sea Still");
    	
    	
    	//Lyoko Sectors Dimension Register
    	DimensionManager.registerProviderType(Polar_Sector_ID, LyokoPolarSector.class, true);
    	DimensionManager.registerProviderType(Mountain_Sector_ID, LyokoMountainSector.class, true);
    	DimensionManager.registerProviderType(Forest_Sector_ID, LyokoForestSector.class, true);
    	DimensionManager.registerProviderType(Desert_Sector_ID, LyokoDesertSector.class, true);
    	DimensionManager.registerProviderType(Carthage_Sector_ID, LyokoCarthageSector.class, true);
    	DimensionManager.registerDimension(Polar_Sector_ID, Polar_Sector_ID);
    	DimensionManager.registerDimension(Mountain_Sector_ID, Mountain_Sector_ID);
    	DimensionManager.registerDimension(Forest_Sector_ID, Forest_Sector_ID);
    	DimensionManager.registerDimension(Desert_Sector_ID, Desert_Sector_ID);
    	DimensionManager.registerDimension(Carthage_Sector_ID, Carthage_Sector_ID);

    	proxy.addChestLoot();
    	
    	//test recipes
    	//GameRegistry.addShapelessRecipe(new ItemStack(DigitalSeaBlock, 10), new Object[] {
    	//	Block.dirt
    	//});
    	
    	//register your entities here
    	//params are entClass, entName, ID, mod, trackingRange, updateFrequency, sendVelocityUpdates //just like before, just called
    	//differently
    	/*
    	//LanguageRegistry.instance().addStringLocalization("entity.Elephant.name", "en_US", "Elephant");
        //EntityRegistry.registerGlobalEntityID(EntityElephant.class, "Elephant", EntityRegistry.findGlobalUniqueEntityId());
    	 */
    	//last param is entity ID, must be unique
    	//seems that the vanilla entities now occupy most slots between 0-110'ish
    	//unsure on getUniqueGlobalID use for this on multi-player/servers
    	
    	/*
    	 * Moved Entities to CommonProxy.
    	 */
    	// Register all the entities.
    	proxy.registerEntities();
    	proxy.registerStrings();
    	
    	//EntityRegistry.addSpawn(matt.lyoko.entities.EntitySkid.class, 0, 0, 1, EnumCreatureType.creature, lyokocarthage);
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
		Lyoko_Uranium_Ore = config.getBlock("lyokoUraniumOre", 1172).getInt();
		Lyoko_Super_Calc = config.getBlock("lyokoSuperCalculator", 1173).getInt();
		Lyoko_Lead_Ore = config.getBlock("lyokoLeadOre", 1174).getInt();
		Weapon_Lyoko_1 = config.getItem("weaponLyoko1", 6081).getInt();
		Weapon_Lyoko_2 = config.getItem("weaponLyoko2", 6082).getInt();
		Weapon_Lyoko_3 = config.getItem("weaponLyoko3", 6083).getInt();
		Weapon_Lyoko_4 = config.getItem("weaponLyoko4", 6084).getInt();
		Weapon_Lyoko_5 = config.getItem("weaponLyoko5", 6085).getInt();
		Weapon_Lyoko_6 = config.getItem("weaponLyoko6", 6086).getInt();
		Item_Skid = config.getItem("itemLyoko1", 6087).getInt();
		NOT_USED1 = config.getItem("itemLyoko2", 6088).getInt();
		NOT_USED2 = config.getItem("itemLyoko3", 6089).getInt();
		NOT_USED3 = config.getItem("itemLyoko4", 6090).getInt();
		NOT_USED4 = config.getItem("itemLyoko5", 6091).getInt();
		NOT_USED5 = config.getItem("itemLyoko6", 6092).getInt();
		NOT_USED6 = config.getItem("itemLyoko7", 6093).getInt();
		NOT_USED7 = config.getItem("itemLyoko8", 6094).getInt();
		NOT_USED8 = config.getItem("itemLyoko9", 6095).getInt();
		NOT_USED9 = config.getItem("itemLyoko10", 6096).getInt();
		Item_Lyoko_Uranium = config.getItem("itemLyokoUranium", 6097).getInt();
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
		Data_Fragment = config.getItem("dataFragment", 6123).getInt();
		Item_Lyoko_Uranium_Cell = config.getItem("itemLyokoUraniumCell", 6124).getInt();
		Item_Lyoko_Depleted_Uranium = config.getItem("itemLyokoDepletedUranium", 6125).getInt();
		Item_Overboard = config.getItem("itemOverboard", 6126).getInt();
		
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
    
    public static DamageSource causeLyokoRangedDamage(EntityLyokoRanged par0EntityLaserArrow, Entity par1Entity)
    {
        return (new EntityDamageSourceIndirect("lyokoRanged", par0EntityLaserArrow, par1Entity)).setProjectile();
    }
    
    public static DamageSource causeLaserDamage(EntityLaser par0EntityLaser, Entity par1Entity)
    {
        return (new EntityDamageSourceIndirect("laser", par0EntityLaser, par1Entity)).setProjectile();
    }
}
