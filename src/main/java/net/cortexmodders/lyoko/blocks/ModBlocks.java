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
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class ModBlocks
{
    public static Block SectorBlock;
    public static Block TowerBlock;
    public static Block TowerBase;
    public static Block Grass;
    public static Block Stone;
    public static Block Sand;
    public static Block Ice;
    public static Block Log;
    public static Block Carthage;
    public static Block QuantumOre;
    public static Block DigitalSeaBlock;
    public static Block DigitalSeaLiquid;
    public static Block LeadOre;
    public static Block VirtualBlock;
    public static Block UraniumOre;
    public static Block Marabounta;
    public static Block TowerConsole;
    public static Block TowerFloor;
    public static Block Cable;
    public static Block Scanner;
    public static Block SuperCalcConsole;
    public static Block LyokoPolarPortal;
    public static Block LyokoDesertPortal;
    public static Block LyokoForestPortal;
    public static Block LyokoMountainPortal;
    public static Block LyokoCarthagePortal;
    public static Block SuperCalc;
    public static Block Holomap;

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
        SectorBlock = new BlockSector().setResistance(1.0F).setHardness(1.0F).setStepSound(Block.soundTypeGlass).setBlockName("LyokoSectorBlock");

        TowerBlock = new BlockTower().setBlockUnbreakable().setResistance(6000000.0F).setLightOpacity(7).setStepSound(Block.soundTypeMetal).setBlockName("TowerBlock");
        TowerBase = new BlockTowerBase().setResistance(6000000F).setBlockUnbreakable().setLightOpacity(7).setStepSound(Block.soundTypeMetal).setBlockName("TowerBase");
        Grass = new BlockLyokoTerrain(LyokoTerrainTypes.GRASS).setResistance(6000000F).setBlockUnbreakable().setStepSound(Block.soundTypeGrass).setBlockName("LyokoGrass").setBlockTextureName("lyoko:lyokograss");
        Stone = new BlockLyokoTerrain(LyokoTerrainTypes.STONE).setResistance(6000000F).setBlockUnbreakable().setStepSound(Block.soundTypeStone).setBlockName("LyokoStone").setBlockTextureName("lyoko:lyokostone");
        Sand = new BlockLyokoTerrain(LyokoTerrainTypes.SAND).setResistance(6000000F).setBlockUnbreakable().setStepSound(Block.soundTypeSand).setBlockName("LyokoSand").setBlockTextureName("lyoko:lyokosand");
        Ice = new BlockLyokoIce("lyokoIce", Material.glass, false).setResistance(6000000F).setBlockUnbreakable().setLightOpacity(3).setStepSound(Block.soundTypeGlass).setBlockName("LyokoIce").setBlockTextureName("lyoko:lyokoice");

        Log = new BlockLyokoTerrain(LyokoTerrainTypes.LOG).setResistance(6000000F).setBlockUnbreakable().setStepSound(Block.soundTypeWood).setBlockName("LyokoLog").setBlockTextureName("lyoko:lyokolog");
        Carthage = new BlockLyoko().setResistance(6000000F).setBlockUnbreakable().setStepSound(Block.soundTypeMetal).setBlockName("LyokoCarthage").setBlockTextureName("lyoko:carthage");
        VirtualBlock = new BlockVirtual().setResistance(1.0F).setHardness(1.0F).setStepSound(Block.soundTypeGlass).setBlockName("LyokoVirtualBlock");
        Marabounta = new BlockMarabounta().setResistance(3.0F).setHardness(10.0F).setBlockName("MarabountaBlock");
        TowerConsole = new BlockTowerConsole().setResistance(6000000F).setBlockUnbreakable().setLightOpacity(7).setStepSound(Block.soundTypeMetal).setBlockName("TowerConsole");
        TowerFloor = new BlockTowerFloor().setResistance(6000000F).setBlockUnbreakable().setLightOpacity(7).setStepSound(Block.soundTypeMetal).setBlockName("TowerFloor");

        // Ore
        QuantumOre = new BlockLyokoOre(BlockLyokoOre.OreTypes.QUANTUM).setHardness(10F).setResistance(20F).setStepSound(Block.soundTypeStone).setBlockName("LyokoOre").setBlockTextureName("lyoko:quantumore" + (!CodeLyoko.useHDTextures ? "_16_16" : ""));
        LeadOre = new BlockLyokoOre(BlockLyokoOre.OreTypes.LEAD).setHardness(10F).setResistance(20F).setStepSound(Block.soundTypeStone).setBlockName("LeadOre").setLightOpacity(10).setBlockTextureName("lyoko:lead");
        UraniumOre = new BlockLyokoOre(BlockLyokoOre.OreTypes.URANIUM).setHardness(10F).setResistance(20.0F).setStepSound(Block.soundTypeStone).setBlockName("UraniumOre").setLightOpacity(10).setBlockTextureName("lyoko:uranium");

        QuantumOre.setHarvestLevel("pickaxe", 2);
        LeadOre.setHarvestLevel("pickaxe", 2);
        UraniumOre.setHarvestLevel("pickaxe", 2);

        // Other
        SuperCalc = new BlockSuperCalc().setHardness(20F).setResistance(10.0F).setBlockName("Super Computer");
        Cable = new BlockCable().setResistance(4F).setHardness(1F).setStepSound(Block.soundTypeCloth).setBlockName("Cable");
        Scanner = new BlockScanner().setHardness(20F).setResistance(0F).setBlockName("Scanner");
        SuperCalcConsole = new BlockSuperCalcConsole().setResistance(6000000F).setHardness(20.0F).setLightOpacity(7).setStepSound(Block.soundTypeMetal).setBlockName("SuperCalcConsole");
        Holomap = new BlockHolomap().setResistance(6000000F).setHardness(10.0F).setLightOpacity(15).setStepSound(Block.soundTypeMetal).setBlockName("Holomap");

        // Digital Sea
        DigitalSeaBlock = new BlockDigitalSea().setResistance(6000000F).setBlockUnbreakable().setBlockName("DigitalSeaBlock");
        DigitalSeaLiquid = new BlockDigitalSeaLiquid(ModFluids.digitalSea, Material.water).setBlockName("DigitalSeaLiquid");

        // Portal (Unused)
        // LyokoPolarPortal = new
        // BlockLyoko(BlockIds.LYOKO_ICE_PORTAL).setBlockName("PolarPortal").setCreativeTab(null);
        // LyokoDesertPortal = new
        // BlockLyoko(BlockIds.LYOKO_DESERT_PORTAL).setBlockName("DesertPortal").setCreativeTab(null);
        // LyokoForestPortal = new
        // BlockLyoko(BlockIds.LYOKO_FOREST_PORTAL).setBlockName("ForestPortal").setCreativeTab(null);
        // LyokoMountainPortal = new
        // BlockLyoko(BlockIds.LYOKO_MOUNTAIN_PORTAL).setBlockName("MountainPortal").setCreativeTab(null);
        // LyokoCarthagePortal = new
        // BlockLyoko(BlockIds.LYOKO_CARTHAGE_PORTAL).setBlockName("CarthagePortal").setCreativeTab(null);

        registerBlocks();
        registerLanguage();
    }

    public static void registerBlocks()
    {

        GameRegistry.registerBlock(SectorBlock, "SectorBlock");

        GameRegistry.registerBlock(Scanner, "Scanner");
        GameRegistry.registerBlock(Cable, "Cable");
        GameRegistry.registerBlock(SuperCalcConsole, "Super Computer Console");
        GameRegistry.registerBlock(SuperCalc, "Super Computer");
        GameRegistry.registerBlock(TowerFloor, ItemBlockTowerFloor.class, "Tower Floor");
        GameRegistry.registerBlock(TowerConsole, "Tower Console");
        GameRegistry.registerBlock(Marabounta, "Marabounta");
        GameRegistry.registerBlock(VirtualBlock, ItemBlockVirtual.class, "Virtual Block");
        GameRegistry.registerBlock(LyokoCarthagePortal, "Carthage Portal");
        GameRegistry.registerBlock(LyokoPolarPortal, "LyokoPolarPortal");
        GameRegistry.registerBlock(LyokoDesertPortal, "Desert Portal");
        GameRegistry.registerBlock(LyokoForestPortal, "Forest Portal");
        GameRegistry.registerBlock(LyokoMountainPortal, "Mountain Portal");
        GameRegistry.registerBlock(LeadOre, ItemBlockEffect.class, "Lead Isotope 210 Ore");
        GameRegistry.registerBlock(UraniumOre, ItemBlockEffect.class, "Uranium Ore");
        GameRegistry.registerBlock(TowerBlock, "Tower");
        GameRegistry.registerBlock(TowerBase, ItemBlockEffect.class, "Tower Base");
        GameRegistry.registerBlock(Grass, "Lyoko Grass");
        GameRegistry.registerBlock(Stone, "Lyoko Stone");
        GameRegistry.registerBlock(Sand, "Lyoko Sand");
        GameRegistry.registerBlock(Ice, "Lyoko Ice");
        GameRegistry.registerBlock(Log, "Lyoko Log");
        GameRegistry.registerBlock(DigitalSeaBlock, "Digital Sea Block");
        GameRegistry.registerBlock(Carthage, "Carthage Block");
        GameRegistry.registerBlock(QuantumOre, ItemBlockEffect.class, "Quantum Ore");
        GameRegistry.registerBlock(DigitalSeaLiquid, "Digital Sea Liquid");
        GameRegistry.registerBlock(Holomap, "Holomap");
    }

    public static void registerLanguage()
    {
        LanguageRegistry.addName(SectorBlock, "Sector Block");
        LanguageRegistry.addName(Scanner, "Scanner");
        LanguageRegistry.addName(Cable, "Cable");
        LanguageRegistry.addName(SuperCalcConsole, "Super Computer Console");
        LanguageRegistry.addName(SuperCalc, "Super Computer");
        LanguageRegistry.addName(TowerFloor, "Tower Floor");
        LanguageRegistry.addName(TowerConsole, "Tower Console");
        LanguageRegistry.addName(Marabounta, "Marabounta");
        LanguageRegistry.addName(VirtualBlock, "Virtual Block");
        LanguageRegistry.addName(LyokoCarthagePortal, "Carthage Portal");
        LanguageRegistry.addName(LyokoPolarPortal, "Ice Portal");
        LanguageRegistry.addName(LyokoDesertPortal, "Desert Portal");
        LanguageRegistry.addName(LyokoForestPortal, "Forest Portal");
        LanguageRegistry.addName(LyokoMountainPortal, "Mountain Portal");
        LanguageRegistry.addName(LeadOre, "Lead Isotope 210 Ore");
        LanguageRegistry.addName(UraniumOre, "Uranium Ore");
        LanguageRegistry.addName(TowerBlock, "Tower");
        LanguageRegistry.addName(TowerBase, "Tower Base");
        LanguageRegistry.addName(Grass, "Lyoko Grass");
        LanguageRegistry.addName(Stone, "Lyoko Stone");
        LanguageRegistry.addName(Sand, "Lyoko Sand");
        LanguageRegistry.addName(Ice, "Lyoko Ice");
        LanguageRegistry.addName(Log, "Lyoko Log");
        LanguageRegistry.addName(DigitalSeaBlock, "Digital Sea Block");
        LanguageRegistry.addName(Carthage, "Carthage Block");
        LanguageRegistry.addName(QuantumOre, "Quantum Ore");
        LanguageRegistry.addName(DigitalSeaLiquid, "Digital Sea");
        LanguageRegistry.addName(Holomap, "Holomap");
    }
}
