/*
 * Code Lyoko Mod for Minecraft v@VERSION Copyright 2013 Cortex Modders, Matthew
 * Warren, Jacob Rhoda, and other contributors. Released under the MIT license
 * http://opensource.org/licenses/MIT
 */

package matt.lyoko.blocks;

import matt.lyoko.CodeLyoko;
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
     * func_149722_s - setBlockUnbreakable
     * func_149752_b - setResistance
     * func_149711_c - setHardness
     * func_149663_c - setUnlocalizedBlockName
     * func_149715_a - setLightValue
     * func_149713_g - setLightOpacity
     */
    public static void init()
    {
        // Lyoko
        SectorBlock = new BlockSector().func_149752_b(1.0F).func_149711_c(1.0F).func_149672_a(Block.field_149778_k).func_149663_c("LyokoSectorBlock");

        TowerBlock = new BlockTower().func_149722_s().func_149752_b(6000000.0F).func_149713_g(7).func_149672_a(Block.field_149777_j).func_149663_c("TowerBlock");
        TowerBase = new BlockTowerBase().func_149752_b(6000000F).func_149722_s().func_149713_g(7).func_149672_a(Block.field_149777_j).func_149663_c("TowerBase");
        Grass = new BlockLyoko().func_149752_b(6000000F).func_149722_s().func_149672_a(Block.field_149779_h).func_149663_c("LyokoGrass").func_149658_d("lyoko:lyokograss");
        Stone = new BlockLyoko().func_149752_b(6000000F).func_149722_s().func_149672_a(Block.field_149769_e).func_149663_c("LyokoStone").func_149658_d("lyoko:lyokostone");
        Sand = new BlockLyoko().func_149752_b(6000000F).func_149722_s().func_149672_a(Block.field_149776_m).func_149663_c("LyokoSand").func_149658_d("lyoko:lyokosand");
        Ice = new BlockLyokoIce("lyokoIce", Material.field_151592_s, false).func_149752_b(6000000F).func_149722_s().func_149713_g(3).func_149672_a(Block.field_149778_k).func_149663_c("LyokoIce").func_149658_d("lyoko:lyokoice");
        Log = new BlockLyoko().func_149752_b(6000000F).func_149722_s().func_149672_a(Block.field_149766_f).func_149663_c("LyokoLog").func_149658_d("lyoko:lyokolog");
        Carthage = new BlockLyoko().func_149752_b(6000000F).func_149722_s().func_149672_a(Block.field_149777_j).func_149663_c("LyokoCarthage").func_149658_d("lyoko:carthage");
        VirtualBlock = new BlockVirtual().func_149752_b(1.0F).func_149711_c(1.0F).func_149672_a(Block.field_149778_k).func_149663_c("LyokoVirtualBlock");
        Marabounta = new BlockMarabounta().func_149752_b(3.0F).func_149711_c(10.0F).func_149663_c("MarabountaBlock");
        TowerConsole = new BlockTowerConsole().func_149752_b(6000000F).func_149722_s().func_149713_g(7).func_149672_a(Block.field_149777_j).func_149663_c("TowerConsole");
        TowerFloor = new BlockTowerFloor().func_149752_b(6000000F).func_149722_s().func_149713_g(7).func_149672_a(Block.field_149777_j).func_149663_c("TowerFloor");

        // Ore
        QuantumOre = new BlockLyokoOre(BlockLyokoOre.OreTypes.QUANTUM).func_149711_c(10F).func_149752_b(20F).func_149672_a(Block.field_149769_e).func_149663_c("LyokoOre").func_149658_d("lyoko:quantumore" + (!CodeLyoko.useHDTextures ? "_16_16" : ""));
        LeadOre = new BlockLyokoOre(BlockLyokoOre.OreTypes.LEAD).func_149711_c(10F).func_149752_b(20F).func_149672_a(Block.field_149769_e).func_149663_c("LeadOre").func_149713_g(10).func_149658_d("lyoko:lead");
        UraniumOre = new BlockLyokoOre(BlockLyokoOre.OreTypes.URANIUM).func_149711_c(10F).func_149752_b(20.0F).func_149672_a(Block.field_149769_e).func_149663_c("UraniumOre").func_149713_g(10).func_149658_d("lyoko:uranium");

        QuantumOre.setHarvestLevel("pickaxe", 2);
        LeadOre.setHarvestLevel("pickaxe", 2);
        UraniumOre.setHarvestLevel("pickaxe", 2);
        
        // Other
        SuperCalc = new BlockSuperCalc().func_149711_c(20F).func_149752_b(10.0F).func_149663_c("Super Computer");
        Cable = new BlockCable().func_149752_b(4F).func_149711_c(1F).func_149672_a(Block.field_149775_l).func_149663_c("Cable");
        Scanner = new BlockScanner().func_149711_c(20F).func_149752_b(0F).func_149663_c("Scanner");
        SuperCalcConsole = new BlockSuperCalcConsole(BlockIds.SUPER_CALC_CONSOLE).func_149752_b(6000000F).func_149711_c(20.0F).func_149713_g(7).func_149672_a(Block.field_149777_j).func_149663_c("SuperCalcConsole");
        Holomap = new BlockHolomap().func_149752_b(6000000F).func_149711_c(10.0F).func_149713_g(15).func_149672_a(Block.field_149777_j).func_149663_c("Holomap");

        // Digital Sea
        DigitalSeaBlock = new BlockDigitalSea().func_149752_b(6000000F).func_149722_s().func_149663_c("DigitalSeaBlock");
        DigitalSeaLiquid = new BlockDigitalSeaLiquid(ModFluids.digitalSea, Material.field_151586_h).func_149663_c("DigitalSeaLiquid");

        // Portal (Unused)
        //LyokoPolarPortal = new BlockLyoko(BlockIds.LYOKO_ICE_PORTAL).func_149663_c("PolarPortal").setCreativeTab(null);
        //LyokoDesertPortal = new BlockLyoko(BlockIds.LYOKO_DESERT_PORTAL).func_149663_c("DesertPortal").setCreativeTab(null);
        //LyokoForestPortal = new BlockLyoko(BlockIds.LYOKO_FOREST_PORTAL).func_149663_c("ForestPortal").setCreativeTab(null);
        //LyokoMountainPortal = new BlockLyoko(BlockIds.LYOKO_MOUNTAIN_PORTAL).func_149663_c("MountainPortal").setCreativeTab(null);
        //LyokoCarthagePortal = new BlockLyoko(BlockIds.LYOKO_CARTHAGE_PORTAL).func_149663_c("CarthagePortal").setCreativeTab(null);

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