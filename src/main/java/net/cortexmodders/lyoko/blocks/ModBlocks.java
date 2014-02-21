/*
 * Code Lyoko Mod for Minecraft v@VERSION Copyright 2013 Cortex Modders, Matthew
 * Warren, Jacob Rhoda, and other contributors. Released under the MIT license
 * http://opensource.org/licenses/MIT
 */

package net.cortexmodders.lyoko.blocks;

import net.cortexmodders.lyoko.CodeLyoko;
import net.cortexmodders.lyoko.fluids.ModFluids;
import net.cortexmodders.lyoko.items.ItemBlockEffect;
import net.cortexmodders.lyoko.items.ItemBlockTowerFloor;
import net.cortexmodders.lyoko.items.ItemBlockVirtual;
import net.cortexmodders.lyoko.lib.ModProperties;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class ModBlocks
{
    public static Block sectorBlock;
    public static Block towerBlock;
    public static Block towerBase;
    public static Block grass;
    public static Block stone;
    public static Block sand;
    public static Block ice;
    public static Block log;
    public static Block carthage;
    public static Block quantumOre;
    public static Block digitalSeaBlock;
    public static Block digitalSeaLiquid;
    public static Block leadOre;
    public static Block virtualBlock;
    public static Block uraniumOre;
    public static Block marabounta;
    public static Block towerConsole;
    public static Block towerFloor;
    public static Block cable;
    public static Block scanner;
    public static Block superCalcConsole;
    public static Block superCalc;
    public static Block holomap;

    /**
     * setBlockUnbreakable - setBlockUnbreakable
     * setResistance - setResistance
     * setHardness - setHardness
     * setBlockName - setUnlocalizedBlockName
     * setLightLevel - setLightValue
     * setLightOpacity - setLightOpacity
     */
    public static void init()
    {
        // Lyoko
        sectorBlock = new BlockSector().setResistance(1.0F).setHardness(1.0F).setStepSound(Block.soundTypeGlass).setBlockName("LyokoSectorBlock");

        towerBlock = new BlockTower().setBlockUnbreakable().setResistance(6000000.0F).setLightOpacity(7).setStepSound(Block.soundTypeMetal).setBlockName("TowerBlock");
        towerBase = new BlockTowerBase().setResistance(6000000F).setBlockUnbreakable().setLightOpacity(7).setStepSound(Block.soundTypeMetal).setBlockName("TowerBase");
        grass = new BlockLyokoTerrain(LyokoTerrainTypes.GRASS).setResistance(6000000F).setBlockUnbreakable().setStepSound(Block.soundTypeGrass).setBlockName("LyokoGrass").setBlockTextureName("lyoko:lyokograss");
        stone = new BlockLyokoTerrain(LyokoTerrainTypes.STONE).setResistance(6000000F).setBlockUnbreakable().setStepSound(Block.soundTypeStone).setBlockName("LyokoStone").setBlockTextureName("lyoko:lyokostone");
        sand = new BlockLyokoTerrain(LyokoTerrainTypes.SAND).setResistance(6000000F).setBlockUnbreakable().setStepSound(Block.soundTypeSand).setBlockName("LyokoSand").setBlockTextureName("lyoko:lyokosand");
        ice = new BlockLyokoIce("lyokoIce", Material.glass, false).setResistance(6000000F).setBlockUnbreakable().setLightOpacity(3).setStepSound(Block.soundTypeGlass).setBlockName("LyokoIce").setBlockTextureName("lyoko:lyokoice");

        log = new BlockLyokoTerrain(LyokoTerrainTypes.LOG).setResistance(6000000F).setBlockUnbreakable().setStepSound(Block.soundTypeWood).setBlockName("LyokoLog").setBlockTextureName("lyoko:lyokolog");
        carthage = new BlockLyoko().setResistance(6000000F).setBlockUnbreakable().setStepSound(Block.soundTypeMetal).setBlockName("LyokoCarthage").setBlockTextureName("lyoko:carthage");
        virtualBlock = new BlockVirtual().setResistance(1.0F).setHardness(1.0F).setStepSound(Block.soundTypeGlass).setBlockName("LyokoVirtualBlock");
        marabounta = new BlockMarabounta().setResistance(3.0F).setHardness(10.0F).setBlockName("MarabountaBlock");
        towerConsole = new BlockTowerConsole().setResistance(6000000F).setBlockUnbreakable().setLightOpacity(7).setStepSound(Block.soundTypeMetal).setBlockName("TowerConsole");
        towerFloor = new BlockTowerFloor().setResistance(6000000F).setBlockUnbreakable().setLightOpacity(7).setStepSound(Block.soundTypeMetal).setBlockName("TowerFloor");

        // Ore
        quantumOre = new BlockLyokoOre(BlockLyokoOre.OreTypes.QUANTUM).setHardness(10F).setResistance(20F).setStepSound(Block.soundTypeStone).setBlockName("LyokoOre").setBlockTextureName("lyoko:quantumore" + (!CodeLyoko.useHDTextures ? "_16_16" : ""));
        leadOre = new BlockLyokoOre(BlockLyokoOre.OreTypes.LEAD).setHardness(10F).setResistance(20F).setStepSound(Block.soundTypeStone).setBlockName("LeadOre").setLightOpacity(10).setBlockTextureName("lyoko:lead");
        uraniumOre = new BlockLyokoOre(BlockLyokoOre.OreTypes.URANIUM).setHardness(10F).setResistance(20.0F).setStepSound(Block.soundTypeStone).setBlockName("UraniumOre").setLightOpacity(10).setBlockTextureName("lyoko:uranium");

        quantumOre.setHarvestLevel("pickaxe", 2);
        leadOre.setHarvestLevel("pickaxe", 2);
        uraniumOre.setHarvestLevel("pickaxe", 2);

        // Other
        superCalc = new BlockSuperCalc().setHardness(20F).setResistance(10.0F).setBlockName("Super Computer");
        cable = new BlockCable().setResistance(4F).setHardness(1F).setStepSound(Block.soundTypeCloth).setBlockName("Cable");
        scanner = new BlockScanner().setHardness(20F).setResistance(0F).setBlockName("Scanner");
        superCalcConsole = new BlockSuperCalcConsole().setResistance(6000000F).setHardness(20.0F).setLightOpacity(7).setStepSound(Block.soundTypeMetal).setBlockName("SuperCalcConsole");
        holomap = new BlockHolomap().setResistance(6000000F).setHardness(10.0F).setLightOpacity(15).setStepSound(Block.soundTypeMetal).setBlockName("Holomap");

        // Digital Sea
        digitalSeaBlock = new BlockDigitalSea().setResistance(6000000F).setBlockUnbreakable().setBlockName("DigitalSeaBlock");
        digitalSeaLiquid = new BlockDigitalSeaLiquid(ModFluids.digitalSea, Material.water).setBlockName("DigitalSeaLiquid");

        registerBlocks();
        registerLanguage();
    }

    public static void registerBlocks()
    {

        GameRegistry.registerBlock(sectorBlock, "SectorBlock");

        GameRegistry.registerBlock(scanner, ItemBlock.class, "Scanner", ModProperties.MOD_ID);
        GameRegistry.registerBlock(cable, ItemBlock.class, "Cable", ModProperties.MOD_ID);
        GameRegistry.registerBlock(superCalcConsole, ItemBlock.class, "Super Computer Console", ModProperties.MOD_ID);
        GameRegistry.registerBlock(superCalc, ItemBlock.class, "Super Computer", ModProperties.MOD_ID);
        GameRegistry.registerBlock(towerFloor, ItemBlockTowerFloor.class, "Tower Floor", ModProperties.MOD_ID);
        GameRegistry.registerBlock(towerConsole, ItemBlock.class, "Tower Console", ModProperties.MOD_ID);
        GameRegistry.registerBlock(marabounta, ItemBlock.class, "Marabounta", ModProperties.MOD_ID);
        GameRegistry.registerBlock(virtualBlock, ItemBlockVirtual.class, "Virtual Block", ModProperties.MOD_ID);
        GameRegistry.registerBlock(leadOre, ItemBlockEffect.class, "Lead Isotope 210 Ore", ModProperties.MOD_ID);
        GameRegistry.registerBlock(uraniumOre, ItemBlockEffect.class, "Uranium Ore", ModProperties.MOD_ID);
        GameRegistry.registerBlock(towerBlock, ItemBlock.class, "Tower", ModProperties.MOD_ID);
        GameRegistry.registerBlock(towerBase, ItemBlockEffect.class, "Tower Base", ModProperties.MOD_ID);
        GameRegistry.registerBlock(grass, ItemBlock.class, "Lyoko Grass", ModProperties.MOD_ID);
        GameRegistry.registerBlock(stone, ItemBlock.class, "Lyoko Stone", ModProperties.MOD_ID);
        GameRegistry.registerBlock(sand, ItemBlock.class, "Lyoko Sand", ModProperties.MOD_ID);
        GameRegistry.registerBlock(ice, ItemBlock.class, "Lyoko Ice", ModProperties.MOD_ID);
        GameRegistry.registerBlock(log, ItemBlock.class, "Lyoko Log", ModProperties.MOD_ID);
        GameRegistry.registerBlock(digitalSeaBlock, ItemBlock.class, "Digital Sea Block", ModProperties.MOD_ID);
        GameRegistry.registerBlock(carthage, ItemBlock.class, "Carthage Block", ModProperties.MOD_ID);
        GameRegistry.registerBlock(quantumOre, ItemBlockEffect.class, "Quantum Ore", ModProperties.MOD_ID);
        GameRegistry.registerBlock(digitalSeaLiquid, ItemBlock.class, "Digital Sea Liquid", ModProperties.MOD_ID);
        GameRegistry.registerBlock(holomap, ItemBlock.class, "Holomap", ModProperties.MOD_ID);
    }

    public static void registerLanguage()
    {
        LanguageRegistry.addName(sectorBlock, "Sector Block");
        LanguageRegistry.addName(scanner, "Scanner");
        LanguageRegistry.addName(cable, "Cable");
        LanguageRegistry.addName(superCalcConsole, "Super Computer Console");
        LanguageRegistry.addName(superCalc, "Super Computer");
        LanguageRegistry.addName(towerFloor, "Tower Floor");
        LanguageRegistry.addName(towerConsole, "Tower Console");
        LanguageRegistry.addName(marabounta, "Marabounta");
        LanguageRegistry.addName(virtualBlock, "Virtual Block");
        LanguageRegistry.addName(leadOre, "Lead Isotope 210 Ore");
        LanguageRegistry.addName(uraniumOre, "Uranium Ore");
        LanguageRegistry.addName(towerBlock, "Tower");
        LanguageRegistry.addName(towerBase, "Tower Base");
        LanguageRegistry.addName(grass, "Lyoko Grass");
        LanguageRegistry.addName(stone, "Lyoko Stone");
        LanguageRegistry.addName(sand, "Lyoko Sand");
        LanguageRegistry.addName(ice, "Lyoko Ice");
        LanguageRegistry.addName(log, "Lyoko Log");
        LanguageRegistry.addName(digitalSeaBlock, "Digital Sea Block");
        LanguageRegistry.addName(carthage, "Carthage Block");
        LanguageRegistry.addName(quantumOre, "Quantum Ore");
        LanguageRegistry.addName(digitalSeaLiquid, "Digital Sea");
        LanguageRegistry.addName(holomap, "Holomap");
    }
}
