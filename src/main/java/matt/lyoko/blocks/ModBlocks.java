/*
 * Code Lyoko Mod for Minecraft v@VERSION Copyright 2013 Cortex Modders, Matthew
 * Warren, Jacob Rhoda, and other contributors. Released under the MIT license
 * http://opensource.org/licenses/MIT
 */

package matt.lyoko.blocks;

import matt.lyoko.fluids.ModFluids;
import matt.lyoko.items.ItemBlockEffect;
import matt.lyoko.items.ItemBlockTowerFloor;
import matt.lyoko.items.ItemBlockVirtual;
import matt.lyoko.lib.BlockIds;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class ModBlocks
{
    public static Block SectorBlock;

    public static Block TowerBlock;// = new BlockLyoko(Lyoko_Tower,
                                   // 0).setResistance(6000000F).setBlockUnbreakable().setLightValue(7F).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("TowerBlock");
    public static Block TowerBase;// = new BlockTowerBase(Lyoko_Tower_Base, 1,
                                  // false).setResistance(6000000F).setBlockUnbreakable().setLightValue(7F).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("TowerBase");
    public static Block Grass;// = new BlockLyoko(Lyoko_Grass,
                              // 2).setResistance(6000000F).setBlockUnbreakable().setStepSound(Block.soundGrassFootstep).setUnlocalizedName("LyokoGrass");
    public static Block Stone;// = new BlockLyoko(Lyoko_Stone,
                              // 3).setResistance(6000000F).setBlockUnbreakable().setStepSound(Block.soundStoneFootstep).setUnlocalizedName("LyokoStone");
    public static Block Sand;// = new BlockLyoko(Lyoko_Sand,
                             // 4).setResistance(6000000F).setBlockUnbreakable().setStepSound(Block.soundSandFootstep).setUnlocalizedName("LyokoSand");
    public static Block Ice;// = new BlockLyokoIce(Lyoko_Ice, 5, Material.glass,
                            // false).setResistance(6000000F).setBlockUnbreakable().setLightOpacity(3).setStepSound(Block.soundGlassFootstep).setUnlocalizedName("LyokoIce");
    public static Block Log;// = new BlockLyoko(Lyoko_Log,
                            // 6).setResistance(6000000F).setBlockUnbreakable().setStepSound(Block.soundWoodFootstep).setUnlocalizedName("LyokoLog");
    public static Block Carthage;// = new BlockLyoko(Lyoko_Carthage,
                                 // 7).setResistance(6000000F).setBlockUnbreakable().setStepSound(Block.soundMetalFootstep).setUnlocalizedName("LyokoCarthage");
    public static Block QuantumOre;// = new BlockLyoko(Lyoko_Ore,
                                   // 8).setHardness(10F).setResistance(20F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("LyokoOre");
    public static Block DigitalSeaBlock;// = new
                                        // BlockDigitalSea(Lyoko_Sea_Block,
                                        // 9).setResistance(6000000F).setBlockUnbreakable().setUnlocalizedName("DigitalSeaBlock");
    public static Block DigitalSeaLiquid;// = new
                                         // BlockFlowingDigitalSea(Lyoko_Sea_Flowing,
                                         // Material.water).setHardness(100F).setLightOpacity(3).setUnlocalizedName("DigitalSeaFlowing").setRequiresSelfNotify();
    public static Block LeadOre;// = new BlockLyoko(Lyoko_Lead_Ore,
                                // 10).setHardness(10F).setResistance(20F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("LeadOre").setLightValue(10F);
    public static Block VirtualBlock;
    public static Block UraniumOre;
    public static Block Marabounta;
    public static Block TowerConsole;
    public static Block TowerFloor;
    public static Block Cable;
    public static Block Scanner;
    public static Block SuperCalcConsole;
    public static Block LyokoPolarPortal;// = new BlockLyoko(Lyoko_Polar_Portal,
                                         // 12).setUnlocalizedName("Polar Portal");
    public static Block LyokoDesertPortal;// = new
                                          // BlockLyoko(Lyoko_Desert_Portal,
                                          // 13).setUnlocalizedName("Desert Portal");
    public static Block LyokoForestPortal;// = new
                                          // BlockLyoko(Lyoko_Forest_Portal,
                                          // 14).setUnlocalizedName("Forest Portal");
    public static Block LyokoMountainPortal;// = new
                                            // BlockLyoko(Lyoko_Mountain_Portal,
                                            // 15).setUnlocalizedName("Mountain Portal");
    public static Block LyokoCarthagePortal;// = new
                                            // BlockLyoko(Lyoko_Carthage_Portal,
                                            // 16).setUnlocalizedName("Carthage Portal");
    public static Block SuperCalc;// = new
                                  // BlockSuperCalc(Lyoko_Super_Calc).setHardness(20).setResistance(6000000).setUnlocalizedName("Super Computer").setRequiresSelfNotify();
    public static Block Holomap;

    public static void init()
    {
        // Lyoko
        SectorBlock = new BlockSector(BlockIds.SECTOR_BLOCK).setResistance(1.0F).setHardness(1.0F).setStepSound(Block.soundGlassFootstep).setUnlocalizedName("LyokoSectorBlock");

        TowerBlock = new BlockTower(BlockIds.LYOKO_TOWER).setResistance(6000000F).setBlockUnbreakable().setLightValue(7F).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("TowerBlock");
        TowerBase = new BlockTowerBase(BlockIds.LYOKO_TOWER_BASE).setResistance(6000000F).setBlockUnbreakable().setLightValue(7F).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("TowerBase");
        Grass = new BlockLyoko(BlockIds.LYOKO_GRASS).setResistance(6000000F).setBlockUnbreakable().setStepSound(Block.soundGrassFootstep).setUnlocalizedName("LyokoGrass");
        Stone = new BlockLyoko(BlockIds.LYOKO_STONE).setResistance(6000000F).setBlockUnbreakable().setStepSound(Block.soundStoneFootstep).setUnlocalizedName("LyokoStone");
        Sand = new BlockLyoko(BlockIds.LYOKO_SAND).setResistance(6000000F).setBlockUnbreakable().setStepSound(Block.soundSandFootstep).setUnlocalizedName("LyokoSand");
        Ice = new BlockLyokoIce(BlockIds.LYOKO_ICE, "lyokoIce", Material.glass, false).setResistance(6000000F).setBlockUnbreakable().setLightOpacity(3).setStepSound(Block.soundGlassFootstep).setUnlocalizedName("LyokoIce");
        Log = new BlockLyoko(BlockIds.LYOKO_LOG).setResistance(6000000F).setBlockUnbreakable().setStepSound(Block.soundWoodFootstep).setUnlocalizedName("LyokoLog");
        Carthage = new BlockLyoko(BlockIds.LYOKO_CARTHAGE).setResistance(6000000F).setBlockUnbreakable().setStepSound(Block.soundMetalFootstep).setUnlocalizedName("LyokoCarthage");
        VirtualBlock = new BlockVirtual(BlockIds.LYOKO_VIRTUAL_BLOCK).setResistance(1.0F).setHardness(1.0F).setStepSound(Block.soundGlassFootstep).setUnlocalizedName("LyokoVirtualBlock");
        Marabounta = new BlockMarabounta(BlockIds.LYOKO_MARABOUNTA).setResistance(3.0F).setHardness(10.0F).setUnlocalizedName("MarabountaBlock");
        TowerConsole = new BlockTowerConsole(BlockIds.TOWER_CONSOLE).setResistance(6000000F).setBlockUnbreakable().setLightValue(7F).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("TowerConsole");
        TowerFloor = new BlockTowerFloor(BlockIds.TOWER_FLOOR).setResistance(6000000F).setBlockUnbreakable().setLightValue(7F).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("TowerFloor");

        // Ore
        QuantumOre = new BlockLyoko(BlockIds.LYOKO_ORE).setHardness(10F).setResistance(20F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("LyokoOre");
        LeadOre = new BlockLyoko(BlockIds.LYOKO_LEAD_ORE).setHardness(10F).setResistance(20F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("LeadOre").setLightValue(10F);
        UraniumOre = new BlockLyoko(BlockIds.LYOKO_URANIUM_ORE).setHardness(10F).setResistance(20.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("UraniumOre").setLightValue(10F);

        // Other
        SuperCalc = new BlockSuperCalc(BlockIds.LYOKO_SUPER_CALC).setHardness(20F).setResistance(10.0F).setUnlocalizedName("Super Computer");
        Cable = new BlockCable(BlockIds.CABLE).setResistance(4F).setHardness(1F).setStepSound(Block.soundClothFootstep).setUnlocalizedName("Cable");
        Scanner = new BlockScanner(BlockIds.SCANNER).setHardness(20F).setResistance(0F).setUnlocalizedName("Scanner");
        SuperCalcConsole = new BlockSuperCalcConsole(BlockIds.SUPER_CALC_CONSOLE).setResistance(6000000F).setHardness(20.0F).setLightValue(7.0F).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("SuperCalcConsole");
        Holomap = new BlockHolomap(BlockIds.HOLOMAP).setResistance(6000000F).setHardness(10.0F).setLightValue(15.0F).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("Holomap");

        // Digital Sea
        DigitalSeaBlock = new BlockDigitalSea(BlockIds.LYOKO_DIGITAL_SEA_BLOCK).setResistance(6000000F).setBlockUnbreakable().setUnlocalizedName("DigitalSeaBlock");
        DigitalSeaLiquid = new BlockDigitalSeaLiquid(BlockIds.LYOKO_DIGITAL_SEA_LIQUID, ModFluids.digitalSea, Material.water).setUnlocalizedName("DigitalSeaLiquid");

        // Portal (Unused)
        LyokoPolarPortal = new BlockLyoko(BlockIds.LYOKO_ICE_PORTAL).setUnlocalizedName("PolarPortal").setCreativeTab(null);
        LyokoDesertPortal = new BlockLyoko(BlockIds.LYOKO_DESERT_PORTAL).setUnlocalizedName("DesertPortal").setCreativeTab(null);
        LyokoForestPortal = new BlockLyoko(BlockIds.LYOKO_FOREST_PORTAL).setUnlocalizedName("ForestPortal").setCreativeTab(null);
        LyokoMountainPortal = new BlockLyoko(BlockIds.LYOKO_MOUNTAIN_PORTAL).setUnlocalizedName("MountainPortal").setCreativeTab(null);
        LyokoCarthagePortal = new BlockLyoko(BlockIds.LYOKO_CARTHAGE_PORTAL).setUnlocalizedName("CarthagePortal").setCreativeTab(null);

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