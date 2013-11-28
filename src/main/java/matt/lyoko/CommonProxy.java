/*
 * Code Lyoko Mod for Minecraft v@VERSION
 * Copyright 2013 Cortex Modders, Matthew Warren, Jacob Rhoda, and
 * other contributors.
 * Released under the MIT license
 * http://opensource.org/licenses/MIT
 */

package matt.lyoko;

import matt.lyoko.blocks.ModBlocks;
import matt.lyoko.entities.mobs.EntityBlok;
import matt.lyoko.entities.mobs.EntityMegaTank;
import matt.lyoko.entities.mobs.EntityXanafiedMob;
import matt.lyoko.entities.projectile.EntityEnergyField;
import matt.lyoko.entities.projectile.EntityFan;
import matt.lyoko.entities.projectile.EntityLaser;
import matt.lyoko.entities.projectile.EntityLaserArrow;
import matt.lyoko.entities.tileentity.TileEntityCable;
import matt.lyoko.entities.tileentity.TileEntityHolomap;
import matt.lyoko.entities.tileentity.TileEntityMarabounta;
import matt.lyoko.entities.tileentity.TileEntityScanner;
import matt.lyoko.entities.tileentity.TileEntitySuperCalc;
import matt.lyoko.entities.tileentity.TileEntitySuperCalcConsole;
import matt.lyoko.entities.tileentity.TileEntityTower;
import matt.lyoko.entities.tileentity.TileEntityTowerConsole;
import matt.lyoko.entities.vehicles.EntityOverboard;
import matt.lyoko.entities.vehicles.EntitySkid;
import matt.lyoko.fluids.ModFluids;
import matt.lyoko.handlers.ClientTickHandler;
import matt.lyoko.handlers.EventHandler;
import matt.lyoko.handlers.ServerTickHandler;
import matt.lyoko.handlers.SoundHandler;
import matt.lyoko.items.ModItems;
import matt.lyoko.lib.BlockIds;
import matt.lyoko.lib.DimensionIds;
import matt.lyoko.lib.EntityIds;
import matt.lyoko.lib.ItemIds;
import matt.lyoko.world.LyokoCarthageSector;
import matt.lyoko.world.LyokoDesertSector;
import matt.lyoko.world.LyokoForestSector;
import matt.lyoko.world.LyokoMountainSector;
import matt.lyoko.world.LyokoPolarSector;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.common.ChestGenHooks;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

public class CommonProxy
{
    public void registerRenderInformation()
    {
        // unused server side. -- see ClientProxy for implementation
    }

    public void registerTickHandlers()
    {
        TickRegistry.registerTickHandler(new ClientTickHandler(), Side.CLIENT);
        TickRegistry.registerTickHandler(new ServerTickHandler(), Side.SERVER);
    }

    public void registerKeyBindingHandler()
    {
        // unused server side
    }

    public void registerOreDictionaryOres()
    {
        OreDictionary.registerOre("ingotRadioactiveLead", ModItems.Lead);
        OreDictionary.registerOre("oreRadioactiveLead", ModBlocks.LeadOre);
        OreDictionary.registerOre("ingotUranium", ModItems.Uranium);
        OreDictionary.registerOre("oreUranium", ModBlocks.UraniumOre);
    }

    public void registerEventHandlers()
    {
        EventHandler handler = new EventHandler();
        MinecraftForge.EVENT_BUS.register(handler);
        GameRegistry.registerPlayerTracker(handler);
        MinecraftForge.EVENT_BUS.register(new SoundHandler());
        MinecraftForge.EVENT_BUS.register(new ModFluids());
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

    public void registerEntities()
    {
        // Specters
        EntityRegistry.registerGlobalEntityID(EntityXanafiedMob.class, "XanafiedMob", EntityRegistry.findGlobalUniqueEntityId());

        // Monsters.
        EntityRegistry.registerGlobalEntityID(EntityBlok.class, "Blok", EntityRegistry.findGlobalUniqueEntityId(), 0xe3b434, 0x000000);
        EntityRegistry.registerGlobalEntityID(EntityMegaTank.class, "Megatank", EntityRegistry.findGlobalUniqueEntityId(), 0xe3b434, 0x000000);

        // Vehicles
        EntityRegistry.registerModEntity(EntitySkid.class, "Skidbladnir", EntityIds.SKID, CodeLyoko.instance, 50, 1, true);
        EntityRegistry.registerModEntity(EntityOverboard.class, "Overboard", EntityIds.OVERBOARD, CodeLyoko.instance, 50, 1, true);

        // Projectiles
        EntityRegistry.registerModEntity(EntityLaser.class, "Laser", EntityIds.LASER, CodeLyoko.instance, 50, 1, true);
        EntityRegistry.registerModEntity(EntityLaserArrow.class, "LaserArrow", EntityIds.LASERARROW, CodeLyoko.instance, 50, 1, true);
        EntityRegistry.registerModEntity(EntityFan.class, "Fan", EntityIds.FAN, CodeLyoko.instance, 50, 1, true);
        EntityRegistry.registerModEntity(EntityEnergyField.class, "EnergyField", EntityIds.ENERGYFIELD, CodeLyoko.instance, 50, 1, true);

        // Naturally spawn in Lyoko.
        EntityRegistry.addSpawn(matt.lyoko.entities.mobs.EntityBlok.class, 10, 3, 15, EnumCreatureType.monster, CodeLyoko.lyokocarthage, CodeLyoko.lyokoforest, CodeLyoko.lyokomountain, CodeLyoko.lyokopolar, CodeLyoko.lyokodesert);
        EntityRegistry.addSpawn(matt.lyoko.entities.mobs.EntityMegaTank.class, 10, 3, 15, EnumCreatureType.monster, CodeLyoko.lyokocarthage, CodeLyoko.lyokoforest, CodeLyoko.lyokomountain, CodeLyoko.lyokopolar, CodeLyoko.lyokodesert);
    }

    public void registerTileEntities()
    {
        GameRegistry.registerTileEntity(TileEntitySuperCalc.class, "teSuperCalc");
        GameRegistry.registerTileEntity(TileEntityScanner.class, "teScanner");
        GameRegistry.registerTileEntity(TileEntityTower.class, "teTower");
        GameRegistry.registerTileEntity(TileEntityTowerConsole.class, "teTowerConsole");
        GameRegistry.registerTileEntity(TileEntityMarabounta.class, "teMarabounta");
        GameRegistry.registerTileEntity(TileEntitySuperCalcConsole.class, "teSuperCalcConsole");
        GameRegistry.registerTileEntity(TileEntityCable.class, "teCable");
        GameRegistry.registerTileEntity(TileEntityHolomap.class, "teHolomap");
    }

    public void registerDimensions()
    {
        // Dimension providers
        DimensionManager.registerProviderType(DimensionIds.ICE, LyokoPolarSector.class, true);
        DimensionManager.registerProviderType(DimensionIds.MOUNTAIN, LyokoMountainSector.class, true);
        DimensionManager.registerProviderType(DimensionIds.FOREST, LyokoForestSector.class, true);
        DimensionManager.registerProviderType(DimensionIds.DESERT, LyokoDesertSector.class, true);
        DimensionManager.registerProviderType(DimensionIds.CARTHAGE, LyokoCarthageSector.class, true);

        // Dimensions
        DimensionManager.registerDimension(DimensionIds.ICE, DimensionIds.ICE);
        DimensionManager.registerDimension(DimensionIds.MOUNTAIN, DimensionIds.MOUNTAIN);
        DimensionManager.registerDimension(DimensionIds.FOREST, DimensionIds.FOREST);
        DimensionManager.registerDimension(DimensionIds.DESERT, DimensionIds.DESERT);
        DimensionManager.registerDimension(DimensionIds.CARTHAGE, DimensionIds.CARTHAGE);
    }

    public void registerNames()
    {
        // Specters
        LanguageRegistry.instance().addStringLocalization("entity.XanafiedMob.name", "en_US", "Xanafied Mob");

        // Monsters.
        LanguageRegistry.instance().addStringLocalization("entity.Blok.name", "en_US", "Blok");
        LanguageRegistry.instance().addStringLocalization("entity.Megatank.name", "en_US", "Megatank");

        // Vehicles.
        LanguageRegistry.instance().addStringLocalization("entity.Skidbladnir.name", "en_US", "Skidbladnir");
        LanguageRegistry.instance().addStringLocalization("entity.Overboard.name", "en_US", "Overboard");

        // Damage sources
        LanguageRegistry.instance().addStringLocalization("death.attack.CodeLyoko.digitalSea", "%1$s fell into the digital sea");
        LanguageRegistry.instance().addStringLocalization("death.attack.CodeLyoko.digitalSea.player", "%1$s fell into the digital sea while fighting %2$s");
        LanguageRegistry.instance().addStringLocalization("death.attack.CodeLyoko.marabounta", "%1$s was absorbed by the marabounta");
        LanguageRegistry.instance().addStringLocalization("death.attack.CodeLyoko.marabounta.player", "%1$s was absorbed by the marabounta while fighting %2$s");
    }

    public void registerBlockIds(Configuration config)
    {
        config.addCustomCategoryComment(Configuration.CATEGORY_BLOCK, "This id MUST be under 255 or the game will have problems.");
        BlockIds.SECTOR_BLOCK = config.getBlock("sectorBlock", BlockIds.SECTOR_BLOCK_DEFAULT).getInt();

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
        BlockIds.HOLOMAP = config.getBlock("holomap", BlockIds.HOLOMAP_DEFAULT).getInt();
    }

    public void registerItemIds(Configuration config)
    {
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
        ItemIds.LASER_SHOOTER = config.getItem("laserShooter", ItemIds.LASER_SHOOTER_DEFAULT).getInt();
        /*
         * commented out until needed so that the config file isn't cluttered
         * with unused stuff
         */
        // ItemIds.NOT_USED5 = config.getItem("itemLyoko5",
        // ItemIds.NOT_USED5_DEFAULT).getInt();
        // ItemIds.NOT_USED6 = config.getItem("itemLyoko6",
        // ItemIds.NOT_USED6_DEFAULT).getInt();
        // ItemIds.NOT_USED7 = config.getItem("itemLyoko7",
        // ItemIds.NOT_USED7_DEFAULT).getInt();
        // ItemIds.NOT_USED8 = config.getItem("itemLyoko8",
        // ItemIds.NOT_USED8_DEFAULT).getInt();
        // ItemIds.NOT_USED9 = config.getItem("itemLyoko9",
        // ItemIds.NOT_USED9_DEFAULT).getInt();
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
    }

    public void registerDimensionIds(Configuration config)
    {
        DimensionIds.ICE = config.get(Configuration.CATEGORY_GENERAL, "polarSectorID", DimensionIds.ICE_DEFAULT).getInt();
        DimensionIds.MOUNTAIN = config.get(Configuration.CATEGORY_GENERAL, "mountainSectorID", DimensionIds.MOUNTAIN_DEFAULT).getInt();
        DimensionIds.FOREST = config.get(Configuration.CATEGORY_GENERAL, "forestSectorID", DimensionIds.FOREST_DEFAULT).getInt();
        DimensionIds.DESERT = config.get(Configuration.CATEGORY_GENERAL, "desertSectorID", DimensionIds.DESERT_DEFAULT).getInt();
        DimensionIds.CARTHAGE = config.get(Configuration.CATEGORY_GENERAL, "carthageSectorID", DimensionIds.CARTHAGE_DEFAULT).getInt();
        DimensionIds.DIGITALSEA = config.get(Configuration.CATEGORY_GENERAL, "digitalSeaSectorID", DimensionIds.DIGITALSEA_DEFAULT).getInt();
        DimensionIds.CORTEX = config.get(Configuration.CATEGORY_GENERAL, "cortexSectorID", DimensionIds.CORTEX_DEFAULT).getInt();
    }
}