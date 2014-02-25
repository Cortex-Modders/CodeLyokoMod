/*
 * Code Lyoko Mod for Minecraft ${version}
 * Copyright 2014 Cortex Modders, Matthew Warren, Jacob Rhoda, and other contributors.
 * Released under the MIT license http://opensource.org/licenses/MIT
 */

package net.cortexmodders.lyoko.lib;

import net.cortexmodders.lyoko.blocks.ModBlocks;
import net.cortexmodders.lyoko.items.ModItems;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import cpw.mods.fml.common.registry.GameRegistry;

public class Recipes
{
    public static void registerBlockRecipes()
    {
        GameRegistry.addRecipe(new ItemStack(ModBlocks.scanner, 1), new Object[] { "###", "#*#", "###", Character.valueOf('#'), Items.gold_ingot, Character.valueOf('*'), ModItems.quantumMatrix });
        GameRegistry.addRecipe(new ItemStack(ModBlocks.cable, 12), new Object[] { "###", "$*$", "###", Character.valueOf('#'), Blocks.wool, Character.valueOf('*'), Items.gold_ingot, Character.valueOf('$'), Items.redstone });
        GameRegistry.addRecipe(new ItemStack(ModBlocks.superComputer, 1), new Object[] { "#o#", "#*#", "#o#", Character.valueOf('#'), Items.gold_ingot, Character.valueOf('*'), ModItems.quantumMatrix, Character.valueOf('o'), Blocks.obsidian });
    }
    
    @SuppressWarnings("unchecked")
    public static void registerItemRecipes()
    {
        CraftingManager.getInstance().getRecipeList().add(new ShapelessOreRecipe(new ItemStack(ModItems.uraniumCell), "ingotUranium", ModItems.emptyCell));
        CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(ModItems.uraniumCell, 2), "*#*", Character.valueOf('*'), ModItems.depletedUraniumCell, Character.valueOf('#'), "ingotUranium"));
        CraftingManager.getInstance().getRecipeList().add(new ShapelessOreRecipe(new ItemStack(ModItems.leadCell), "ingotRadioactiveLead", ModItems.emptyCell));
        CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(ModItems.leadCell, 2), "*#*", Character.valueOf('*'), ModItems.depletedLeadCell, Character.valueOf('#'), "ingotRadioactiveLead"));
        CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(ModItems.emptyCell, 16), " # ", "#*#", " # ", Character.valueOf('*'), Blocks.glass, Character.valueOf('#'), "ingotTin"));
        
        /*
         * GameRegistry.addRecipe(new ItemStack(Overboard, 1), new Object[] {
         * "h*c", "$#$", "bml", Character.valueOf('*'), Item.boat,
         * Character.valueOf('m'), Item.minecartEmpty, Character.valueOf('#'),
         * Item.netherStar,
         * Character.valueOf('$'), DataFragment, Character.valueOf('h'),
         * OddHelmet, Character.valueOf('c'), OddChest,
         * Character.valueOf('l'), OddLegs, Character.valueOf('b'), OddBoots
         * });
         */
        /*
         * GameRegistry.addRecipe(new ItemStack(Skid, 1), new Object[] {
         * "h*c", "$#$", "bml", Character.valueOf('*'), Item.boat,
         * Character.valueOf('m'), Item.minecartEmpty, Character.valueOf('#'),
         * Item.netherStar,
         * Character.valueOf('$'), DataFragment, Character.valueOf('h'),
         * OddHelmet, Character.valueOf('c'), OddChest,
         * Character.valueOf('l'), OddLegs, Character.valueOf('b'), OddBoots
         * });
         */
        GameRegistry.addRecipe(new ItemStack(ModItems.quantumMatrix, 1), new Object[] { " * ", "*#*", " * ", Character.valueOf('*'), Items.diamond, Character.valueOf('#'), ModItems.quantumOrb });
        GameRegistry.addShapelessRecipe(new ItemStack(ModItems.quantumMatrix, 1), new Object[] { ModItems.quantumContainmentCell, ModItems.quantumOrb });
        GameRegistry.addRecipe(new ItemStack(ModItems.quantumContainmentCell, 1), new Object[] { " * ", "*#*", " * ", Character.valueOf('*'), Items.diamond, Character.valueOf('#'), Blocks.glass });
        GameRegistry.addRecipe(new ItemStack(ModItems.emptyCell, 16), new Object[] { " # ", "#*#", " # ", Character.valueOf('*'), Blocks.glass, Character.valueOf('#'), Items.iron_ingot });
        GameRegistry.addShapelessRecipe(new ItemStack(ModItems.emptyCell, 1), new Object[] { ModItems.depletedLeadCell });
        GameRegistry.addShapelessRecipe(new ItemStack(ModItems.emptyCell, 1), new Object[] { ModItems.depletedUraniumCell });
        GameRegistry.addRecipe(new ItemStack(ModItems.aelitaHelmet, 1), new Object[] { "***", "*#*", Character.valueOf('*'), ModItems.dataFragment, Character.valueOf('#'), new ItemStack(Items.dye, 1, 9) });
        GameRegistry.addRecipe(new ItemStack(ModItems.aelitaChest, 1), new Object[] { "*#*", "***", "***", Character.valueOf('*'), ModItems.dataFragment, Character.valueOf('#'), new ItemStack(Items.dye, 1, 9) });
        GameRegistry.addRecipe(new ItemStack(ModItems.aelitaLegs, 1), new Object[] { "***", "*#*", "* *", Character.valueOf('*'), ModItems.dataFragment, Character.valueOf('#'), new ItemStack(Items.dye, 1, 9) });
        GameRegistry.addRecipe(new ItemStack(ModItems.aelitaBoots, 1), new Object[] { "*#*", "* *", Character.valueOf('*'), ModItems.dataFragment, Character.valueOf('#'), new ItemStack(Items.dye, 1, 9) });
        GameRegistry.addRecipe(new ItemStack(ModItems.oddHelmet, 1), new Object[] { "***", "*#*", Character.valueOf('*'), ModItems.dataFragment, Character.valueOf('#'), new ItemStack(Items.dye, 1, 13) });
        GameRegistry.addRecipe(new ItemStack(ModItems.oddChest, 1), new Object[] { "*#*", "***", "***", Character.valueOf('*'), ModItems.dataFragment, Character.valueOf('#'), new ItemStack(Items.dye, 1, 13) });
        GameRegistry.addRecipe(new ItemStack(ModItems.oddLegs, 1), new Object[] { "***", "*#*", "* *", Character.valueOf('*'), ModItems.dataFragment, Character.valueOf('#'), new ItemStack(Items.dye, 1, 13) });
        GameRegistry.addRecipe(new ItemStack(ModItems.oddBoots, 1), new Object[] { "*#*", "* *", Character.valueOf('*'), ModItems.dataFragment, Character.valueOf('#'), new ItemStack(Items.dye, 1, 13) });
        GameRegistry.addRecipe(new ItemStack(ModItems.ulrichHelmet, 1), new Object[] { "***", "*#*", Character.valueOf('*'), ModItems.dataFragment, Character.valueOf('#'), new ItemStack(Items.dye, 1, 11) });
        GameRegistry.addRecipe(new ItemStack(ModItems.ulrichChest, 1), new Object[] { "*#*", "***", "***", Character.valueOf('*'), ModItems.dataFragment, Character.valueOf('#'), new ItemStack(Items.dye, 1, 11) });
        GameRegistry.addRecipe(new ItemStack(ModItems.ulrichLegs, 1), new Object[] { "***", "*#*", "* *", Character.valueOf('*'), ModItems.dataFragment, Character.valueOf('#'), new ItemStack(Items.dye, 1, 11) });
        GameRegistry.addRecipe(new ItemStack(ModItems.ulrichBoots, 1), new Object[] { "*#*", "* *", Character.valueOf('*'), ModItems.dataFragment, Character.valueOf('#'), new ItemStack(Items.dye, 1, 11) });
        GameRegistry.addRecipe(new ItemStack(ModItems.yumiHelmet, 1), new Object[] { "***", "*#*", Character.valueOf('*'), ModItems.dataFragment, Character.valueOf('#'), new ItemStack(Items.dye, 1, 5) });
        GameRegistry.addRecipe(new ItemStack(ModItems.yumiChest, 1), new Object[] { "*#*", "***", "***", Character.valueOf('*'), ModItems.dataFragment, Character.valueOf('#'), new ItemStack(Items.dye, 1, 5) });
        GameRegistry.addRecipe(new ItemStack(ModItems.yumiLegs, 1), new Object[] { "***", "*#*", "* *", Character.valueOf('*'), ModItems.dataFragment, Character.valueOf('#'), new ItemStack(Items.dye, 1, 5) });
        GameRegistry.addRecipe(new ItemStack(ModItems.yumiBoots, 1), new Object[] { "*#*", "* *", Character.valueOf('*'), ModItems.dataFragment, Character.valueOf('#'), new ItemStack(Items.dye, 1, 5) });
        GameRegistry.addRecipe(new ItemStack(ModItems.williamHelmet, 1), new Object[] { "***", "*#*", Character.valueOf('*'), ModItems.dataFragment, Character.valueOf('#'), new ItemStack(Items.dye, 1, 12) });
        GameRegistry.addRecipe(new ItemStack(ModItems.williamChest, 1), new Object[] { "*#*", "***", "***", Character.valueOf('*'), ModItems.dataFragment, Character.valueOf('#'), new ItemStack(Items.dye, 1, 12) });
        GameRegistry.addRecipe(new ItemStack(ModItems.williamLegs, 1), new Object[] { "***", "*#*", "* *", Character.valueOf('*'), ModItems.dataFragment, Character.valueOf('#'), new ItemStack(Items.dye, 1, 12) });
        GameRegistry.addRecipe(new ItemStack(ModItems.williamBoots, 1), new Object[] { "*#*", "* *", Character.valueOf('*'), ModItems.dataFragment, Character.valueOf('#'), new ItemStack(Items.dye, 1, 12) });
    }
    
    public static void registerSmelting()
    {
        GameRegistry.addSmelting(ModBlocks.uraniumOre, new ItemStack(ModItems.uranium, 1), 5F);
        GameRegistry.addSmelting(ModBlocks.leadOre, new ItemStack(ModItems.lead, 1), 5F);
        GameRegistry.addSmelting(ModBlocks.quantumOre, new ItemStack(ModItems.quantumOrb, 1), 5F);
    }
}
