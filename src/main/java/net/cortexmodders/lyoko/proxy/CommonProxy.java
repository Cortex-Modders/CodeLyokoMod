/*
 * Code Lyoko Mod for Minecraft v@VERSION
 * Copyright 2013 Cortex Modders, Matthew Warren, Jacob Rhoda, and
 * other contributors.
 * Released under the MIT license
 * http://opensource.org/licenses/MIT
 */

package net.cortexmodders.lyoko.proxy;

import net.cortexmodders.lyoko.CodeLyoko;
import net.cortexmodders.lyoko.blocks.ModBlocks;
import net.cortexmodders.lyoko.entities.mobs.EntityBlok;
import net.cortexmodders.lyoko.entities.mobs.EntityMegaTank;
import net.cortexmodders.lyoko.entities.mobs.EntityXanafiedMob;
import net.cortexmodders.lyoko.entities.projectile.EntityEnergyField;
import net.cortexmodders.lyoko.entities.projectile.EntityFan;
import net.cortexmodders.lyoko.entities.projectile.EntityLaser;
import net.cortexmodders.lyoko.entities.projectile.EntityLaserArrow;
import net.cortexmodders.lyoko.entities.vehicles.EntityOverboard;
import net.cortexmodders.lyoko.entities.vehicles.EntitySkid;
import net.cortexmodders.lyoko.fluids.ModFluids;
import net.cortexmodders.lyoko.handlers.ClientTickHandler;
import net.cortexmodders.lyoko.handlers.EventHandler;
import net.cortexmodders.lyoko.handlers.ServerTickHandler;
import net.cortexmodders.lyoko.handlers.SoundHandler;
import net.cortexmodders.lyoko.items.ModItems;
import net.cortexmodders.lyoko.lib.DimensionIds;
import net.cortexmodders.lyoko.lib.EntityIds;
import net.cortexmodders.lyoko.lib.ModProperties.ConfigCategories;
import net.cortexmodders.lyoko.tileentity.TileEntityCable;
import net.cortexmodders.lyoko.tileentity.TileEntityHolomap;
import net.cortexmodders.lyoko.tileentity.TileEntityMarabounta;
import net.cortexmodders.lyoko.tileentity.TileEntityScanner;
import net.cortexmodders.lyoko.tileentity.TileEntitySuperCalc;
import net.cortexmodders.lyoko.tileentity.TileEntitySuperCalcConsole;
import net.cortexmodders.lyoko.tileentity.TileEntityTower;
import net.cortexmodders.lyoko.tileentity.TileEntityTowerConsole;
import net.cortexmodders.lyoko.world.LyokoCarthageSector;
import net.cortexmodders.lyoko.world.LyokoDesertSector;
import net.cortexmodders.lyoko.world.LyokoForestSector;
import net.cortexmodders.lyoko.world.LyokoMountainSector;
import net.cortexmodders.lyoko.world.LyokoPolarSector;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.common.ChestGenHooks;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

public class CommonProxy
{
    
    public void registerRenderInformation()
    {
        // unused server side. -- see ClientProxy for implementation
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
        MinecraftForge.EVENT_BUS.register(new SoundHandler());
        MinecraftForge.EVENT_BUS.register(new ModFluids());
        
        MinecraftForge.EVENT_BUS.register(new ServerTickHandler());
        
        //GameRegistry.registerPlayerTracker(handler);
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
        EntityRegistry.addSpawn(net.cortexmodders.lyoko.entities.mobs.EntityBlok.class, 10, 3, 15, EnumCreatureType.monster, CodeLyoko.lyokocarthage, CodeLyoko.lyokoforest, CodeLyoko.lyokomountain, CodeLyoko.lyokopolar, CodeLyoko.lyokodesert);
        EntityRegistry.addSpawn(net.cortexmodders.lyoko.entities.mobs.EntityMegaTank.class, 10, 3, 15, EnumCreatureType.monster, CodeLyoko.lyokocarthage, CodeLyoko.lyokoforest, CodeLyoko.lyokomountain, CodeLyoko.lyokopolar, CodeLyoko.lyokodesert);
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

    public void registerDimensionIds(Configuration config)
    {
        DimensionIds.ICE = config.get(ConfigCategories.DIMENSIONS.name(), "polarSectorID", DimensionIds.ICE_DEFAULT).getInt();
        DimensionIds.MOUNTAIN = config.get(ConfigCategories.DIMENSIONS.name(), "mountainSectorID", DimensionIds.MOUNTAIN_DEFAULT).getInt();
        DimensionIds.FOREST = config.get(ConfigCategories.DIMENSIONS.name(), "forestSectorID", DimensionIds.FOREST_DEFAULT).getInt();
        DimensionIds.DESERT = config.get(ConfigCategories.DIMENSIONS.name(), "desertSectorID", DimensionIds.DESERT_DEFAULT).getInt();
        DimensionIds.CARTHAGE = config.get(ConfigCategories.DIMENSIONS.name(), "carthageSectorID", DimensionIds.CARTHAGE_DEFAULT).getInt();
        DimensionIds.DIGITALSEA = config.get(ConfigCategories.DIMENSIONS.name(), "digitalSeaSectorID", DimensionIds.DIGITALSEA_DEFAULT).getInt();
        DimensionIds.CORTEX = config.get(ConfigCategories.DIMENSIONS.name(), "cortexSectorID", DimensionIds.CORTEX_DEFAULT).getInt();
    }

    public void registerCapes() {}
}
