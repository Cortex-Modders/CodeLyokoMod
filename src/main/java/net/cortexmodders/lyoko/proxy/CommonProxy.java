/*
 * Code Lyoko Mod for Minecraft ${version}
 * Copyright 2014 Cortex Modders, Matthew Warren, Jacob Rhoda, and other contributors.
 * Released under the MIT license http://opensource.org/licenses/MIT
 */

package net.cortexmodders.lyoko.proxy;

import net.cortexmodders.lyoko.CodeLyoko;
import net.cortexmodders.lyoko.blocks.ModBlocks;
import net.cortexmodders.lyoko.entities.mob.EntityBlok;
import net.cortexmodders.lyoko.entities.mob.EntityMegaTank;
import net.cortexmodders.lyoko.entities.mob.EntityXanafiedMob;
import net.cortexmodders.lyoko.entities.projectile.EntityEnergyField;
import net.cortexmodders.lyoko.entities.projectile.EntityFan;
import net.cortexmodders.lyoko.entities.projectile.EntityLaser;
import net.cortexmodders.lyoko.entities.projectile.EntityLaserArrow;
import net.cortexmodders.lyoko.entities.vehicle.EntityOverboard;
import net.cortexmodders.lyoko.entities.vehicle.EntitySkid;
import net.cortexmodders.lyoko.fluids.ModFluids;
import net.cortexmodders.lyoko.handler.EventHandler;
import net.cortexmodders.lyoko.handler.ServerTickHandler;
import net.cortexmodders.lyoko.items.ModItems;
import net.cortexmodders.lyoko.lib.DimensionIds;
import net.cortexmodders.lyoko.lib.EntityIds;
import net.cortexmodders.lyoko.tileentity.TileEntityCable;
import net.cortexmodders.lyoko.tileentity.TileEntityHolomap;
import net.cortexmodders.lyoko.tileentity.TileEntityMarabounta;
import net.cortexmodders.lyoko.tileentity.TileEntityScanner;
import net.cortexmodders.lyoko.tileentity.TileEntitySuperCalc;
import net.cortexmodders.lyoko.tileentity.TileEntitySuperCalcConsole;
import net.cortexmodders.lyoko.tileentity.TileEntityTower;
import net.cortexmodders.lyoko.tileentity.TileEntityTowerConsole;
import net.cortexmodders.lyoko.world.*;
import net.cortexmodders.lyoko.world.provider.WorldProviderDesertSector;
import net.cortexmodders.lyoko.world.provider.WorldProviderIceSector;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.common.ChestGenHooks;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

public class CommonProxy
{
    
    public void registerRenderInformation()
    {
    }
    
    public void registerKeyBindingHandler()
    {
    }
    
    public void registerOreDictionaryOres()
    {
        OreDictionary.registerOre("ingotRadioactiveLead", ModItems.lead);
        OreDictionary.registerOre("oreRadioactiveLead", ModBlocks.leadOre);
        OreDictionary.registerOre("ingotUranium", ModItems.uranium);
        OreDictionary.registerOre("oreUranium", ModBlocks.uraniumOre);
    }
    
    public void registerEventHandlers()
    {
        EventHandler handler = new EventHandler();
        
        MinecraftForge.EVENT_BUS.register(handler);
        MinecraftForge.EVENT_BUS.register(new ModFluids());
        MinecraftForge.EVENT_BUS.register(new ServerTickHandler());
        
        // GameRegistry.registerPlayerTracker(handler);
    }

    public void registerClientEventHandlers() { //stub
    }
    
    public void addChestLoot()
    {
        ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(ModItems.quantumOrb), 1, 1, 005));
        ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(ModItems.lead), 1, 1, 005));
        ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(ModItems.uranium), 1, 1, 005));
        ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(ModItems.aelitaHelmet), 1, 1, 005));
        ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(ModItems.aelitaChest), 1, 1, 005));
        ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(ModItems.aelitaLegs), 1, 1, 005));
        ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(ModItems.aelitaBoots), 1, 1, 005));
        ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(ModItems.oddHelmet), 1, 1, 005));
        ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(ModItems.oddChest), 1, 1, 005));
        ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(ModItems.oddLegs), 1, 1, 005));
        ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(ModItems.oddBoots), 1, 1, 005));
        ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(ModItems.ulrichHelmet), 1, 1, 005));
        ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(ModItems.ulrichChest), 1, 1, 005));
        ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(ModItems.ulrichLegs), 1, 1, 005));
        ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(ModItems.ulrichBoots), 1, 1, 005));
        ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(ModItems.yumiHelmet), 1, 1, 005));
        ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(ModItems.yumiChest), 1, 1, 005));
        ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(ModItems.yumiLegs), 1, 1, 005));
        ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(ModItems.yumiBoots), 1, 1, 005));
        ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(ModItems.williamHelmet), 1, 1, 005));
        ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(ModItems.williamChest), 1, 1, 005));
        ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(ModItems.williamLegs), 1, 1, 005));
        ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(ModItems.williamBoots), 1, 1, 005));
        ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(ModItems.quantumContainmentCell), 1, 1, 005));
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
        EntityRegistry.addSpawn(net.cortexmodders.lyoko.entities.mob.EntityBlok.class, 10, 3, 15, EnumCreatureType.monster, CodeLyoko.lyokocarthage, CodeLyoko.lyokoforest, CodeLyoko.lyokomountain, CodeLyoko.lyokopolar, CodeLyoko.lyokodesert);
        EntityRegistry.addSpawn(net.cortexmodders.lyoko.entities.mob.EntityMegaTank.class, 10, 3, 15, EnumCreatureType.monster, CodeLyoko.lyokocarthage, CodeLyoko.lyokoforest, CodeLyoko.lyokomountain, CodeLyoko.lyokopolar, CodeLyoko.lyokodesert);
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
        DimensionManager.registerProviderType(DimensionIds.ICE, WorldProviderIceSector.class, true);
        DimensionManager.registerProviderType(DimensionIds.MOUNTAIN, LyokoMountainSector.class, true);
        DimensionManager.registerProviderType(DimensionIds.FOREST, LyokoForestSector.class, true);
        DimensionManager.registerProviderType(DimensionIds.DESERT, WorldProviderDesertSector.class, true);
        DimensionManager.registerProviderType(DimensionIds.CARTHAGE, LyokoCarthageSector.class, true);
        
        // Dimensions
        DimensionManager.registerDimension(DimensionIds.ICE, DimensionIds.ICE);
        DimensionManager.registerDimension(DimensionIds.MOUNTAIN, DimensionIds.MOUNTAIN);
        DimensionManager.registerDimension(DimensionIds.FOREST, DimensionIds.FOREST);
        DimensionManager.registerDimension(DimensionIds.DESERT, DimensionIds.DESERT);
        DimensionManager.registerDimension(DimensionIds.CARTHAGE, DimensionIds.CARTHAGE);
    }
    
//    public void registerDimensionIds(Configuration config)
//    {
//        DimensionIds.ICE = config.get(ConfigCategories.DIMENSIONS.name(), "polarSectorID", DimensionIds.ICE_DEFAULT).getInt();
//        DimensionIds.MOUNTAIN = config.get(ConfigCategories.DIMENSIONS.name(), "mountainSectorID", DimensionIds.MOUNTAIN_DEFAULT).getInt();
//        DimensionIds.FOREST = config.get(ConfigCategories.DIMENSIONS.name(), "forestSectorID", DimensionIds.FOREST_DEFAULT).getInt();
//        DimensionIds.DESERT = config.get(ConfigCategories.DIMENSIONS.name(), "desertSectorID", DimensionIds.DESERT_DEFAULT).getInt();
//        DimensionIds.CARTHAGE = config.get(ConfigCategories.DIMENSIONS.name(), "carthageSectorID", DimensionIds.CARTHAGE_DEFAULT).getInt();
//        DimensionIds.DIGITALSEA = config.get(ConfigCategories.DIMENSIONS.name(), "digitalSeaSectorID", DimensionIds.DIGITALSEA_DEFAULT).getInt();
//        DimensionIds.CORTEX = config.get(ConfigCategories.DIMENSIONS.name(), "cortexSectorID", DimensionIds.CORTEX_DEFAULT).getInt();
//    }
    
    public void registerCapes()
    {
    }
}
