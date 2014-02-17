/*
 * Code Lyoko Mod for Minecraft v@VERSION
 * Copyright 2013 Cortex Modders, Matthew Warren, Jacob Rhoda, and
 * other contributors.
 * Released under the MIT license
 * http://opensource.org/licenses/MIT
 */

package matt.lyoko.lib;

import matt.lyoko.blocks.ModBlocks;
import matt.lyoko.items.ModItems;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import cpw.mods.fml.common.registry.GameRegistry;

public class Recipes
{
    public static void registerBlockRecipes()
    {
        GameRegistry.addRecipe(new ItemStack(ModBlocks.Scanner, 1), new Object[] { "###", "#*#", "###", Character.valueOf('#'), Item.ingotGold, Character.valueOf('*'), ModItems.QuantumMatrix });
        GameRegistry.addRecipe(new ItemStack(ModBlocks.Cable, 12), new Object[] { "###", "$*$", "###", Character.valueOf('#'), Block.cloth, Character.valueOf('*'), Item.ingotGold, Character.valueOf('$'), Item.redstone });
        GameRegistry.addRecipe(new ItemStack(ModBlocks.SuperCalc, 1), new Object[] { "#o#", "#*#", "#o#", Character.valueOf('#'), Item.ingotGold, Character.valueOf('*'), ModItems.QuantumMatrix, Character.valueOf('o'), Block.obsidian });
    }

    @SuppressWarnings("unchecked")
	public static void registerItemRecipes()
    {
        CraftingManager.getInstance().getRecipeList().add(new ShapelessOreRecipe(new ItemStack(ModItems.UraniumCell), "ingotUranium", ModItems.EmptyCell));
        CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(ModItems.UraniumCell, 2), "*#*", Character.valueOf('*'), ModItems.DepletedUraniumCell, Character.valueOf('#'), "ingotUranium"));
        CraftingManager.getInstance().getRecipeList().add(new ShapelessOreRecipe(new ItemStack(ModItems.LeadCell), "ingotRadioactiveLead", ModItems.EmptyCell));
        CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(ModItems.LeadCell, 2), "*#*", Character.valueOf('*'), ModItems.DepletedLeadCell, Character.valueOf('#'), "ingotRadioactiveLead"));
        CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(ModItems.EmptyCell, 16), " # ", "#*#", " # ", Character.valueOf('*'), Block.glass, Character.valueOf('#'), "ingotTin"));

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
        GameRegistry.addRecipe(new ItemStack(ModItems.QuantumMatrix, 1), new Object[] { " * ", "*#*", " * ", Character.valueOf('*'), Item.diamond, Character.valueOf('#'), ModItems.QuantumOrb });
        GameRegistry.addShapelessRecipe(new ItemStack(ModItems.QuantumMatrix, 1), new Object[] { ModItems.QuantumContainmentCell, ModItems.QuantumOrb });
        GameRegistry.addRecipe(new ItemStack(ModItems.QuantumContainmentCell, 1), new Object[] { " * ", "*#*", " * ", Character.valueOf('*'), Item.diamond, Character.valueOf('#'), Block.glass });
        GameRegistry.addRecipe(new ItemStack(ModItems.EmptyCell, 16), new Object[] { " # ", "#*#", " # ", Character.valueOf('*'), Block.glass, Character.valueOf('#'), Item.ingotIron });
        GameRegistry.addShapelessRecipe(new ItemStack(ModItems.EmptyCell, 1), new Object[] { ModItems.DepletedLeadCell });
        GameRegistry.addShapelessRecipe(new ItemStack(ModItems.EmptyCell, 1), new Object[] { ModItems.DepletedUraniumCell });
        GameRegistry.addRecipe(new ItemStack(ModItems.AelitaHelmet, 1), new Object[] { "***", "*#*", Character.valueOf('*'), ModItems.DataFragment, Character.valueOf('#'), new ItemStack(Item.dyePowder, 1, 9) });
        GameRegistry.addRecipe(new ItemStack(ModItems.AelitaChest, 1), new Object[] { "*#*", "***", "***", Character.valueOf('*'), ModItems.DataFragment, Character.valueOf('#'), new ItemStack(Item.dyePowder, 1, 9) });
        GameRegistry.addRecipe(new ItemStack(ModItems.AelitaLegs, 1), new Object[] { "***", "*#*", "* *", Character.valueOf('*'), ModItems.DataFragment, Character.valueOf('#'), new ItemStack(Item.dyePowder, 1, 9) });
        GameRegistry.addRecipe(new ItemStack(ModItems.AelitaBoots, 1), new Object[] { "*#*", "* *", Character.valueOf('*'), ModItems.DataFragment, Character.valueOf('#'), new ItemStack(Item.dyePowder, 1, 9) });
        GameRegistry.addRecipe(new ItemStack(ModItems.OddHelmet, 1), new Object[] { "***", "*#*", Character.valueOf('*'), ModItems.DataFragment, Character.valueOf('#'), new ItemStack(Item.dyePowder, 1, 13) });
        GameRegistry.addRecipe(new ItemStack(ModItems.OddChest, 1), new Object[] { "*#*", "***", "***", Character.valueOf('*'), ModItems.DataFragment, Character.valueOf('#'), new ItemStack(Item.dyePowder, 1, 13) });
        GameRegistry.addRecipe(new ItemStack(ModItems.OddLegs, 1), new Object[] { "***", "*#*", "* *", Character.valueOf('*'), ModItems.DataFragment, Character.valueOf('#'), new ItemStack(Item.dyePowder, 1, 13) });
        GameRegistry.addRecipe(new ItemStack(ModItems.OddBoots, 1), new Object[] { "*#*", "* *", Character.valueOf('*'), ModItems.DataFragment, Character.valueOf('#'), new ItemStack(Item.dyePowder, 1, 13) });
        GameRegistry.addRecipe(new ItemStack(ModItems.UlrichHelmet, 1), new Object[] { "***", "*#*", Character.valueOf('*'), ModItems.DataFragment, Character.valueOf('#'), new ItemStack(Item.dyePowder, 1, 11) });
        GameRegistry.addRecipe(new ItemStack(ModItems.UlrichChest, 1), new Object[] { "*#*", "***", "***", Character.valueOf('*'), ModItems.DataFragment, Character.valueOf('#'), new ItemStack(Item.dyePowder, 1, 11) });
        GameRegistry.addRecipe(new ItemStack(ModItems.UlrichLegs, 1), new Object[] { "***", "*#*", "* *", Character.valueOf('*'), ModItems.DataFragment, Character.valueOf('#'), new ItemStack(Item.dyePowder, 1, 11) });
        GameRegistry.addRecipe(new ItemStack(ModItems.UlrichBoots, 1), new Object[] { "*#*", "* *", Character.valueOf('*'), ModItems.DataFragment, Character.valueOf('#'), new ItemStack(Item.dyePowder, 1, 11) });
        GameRegistry.addRecipe(new ItemStack(ModItems.YumiHelmet, 1), new Object[] { "***", "*#*", Character.valueOf('*'), ModItems.DataFragment, Character.valueOf('#'), new ItemStack(Item.dyePowder, 1, 5) });
        GameRegistry.addRecipe(new ItemStack(ModItems.YumiChest, 1), new Object[] { "*#*", "***", "***", Character.valueOf('*'), ModItems.DataFragment, Character.valueOf('#'), new ItemStack(Item.dyePowder, 1, 5) });
        GameRegistry.addRecipe(new ItemStack(ModItems.YumiLegs, 1), new Object[] { "***", "*#*", "* *", Character.valueOf('*'), ModItems.DataFragment, Character.valueOf('#'), new ItemStack(Item.dyePowder, 1, 5) });
        GameRegistry.addRecipe(new ItemStack(ModItems.YumiBoots, 1), new Object[] { "*#*", "* *", Character.valueOf('*'), ModItems.DataFragment, Character.valueOf('#'), new ItemStack(Item.dyePowder, 1, 5) });
        GameRegistry.addRecipe(new ItemStack(ModItems.WilliamHelmet, 1), new Object[] { "***", "*#*", Character.valueOf('*'), ModItems.DataFragment, Character.valueOf('#'), new ItemStack(Item.dyePowder, 1, 12) });
        GameRegistry.addRecipe(new ItemStack(ModItems.WilliamChest, 1), new Object[] { "*#*", "***", "***", Character.valueOf('*'), ModItems.DataFragment, Character.valueOf('#'), new ItemStack(Item.dyePowder, 1, 12) });
        GameRegistry.addRecipe(new ItemStack(ModItems.WilliamLegs, 1), new Object[] { "***", "*#*", "* *", Character.valueOf('*'), ModItems.DataFragment, Character.valueOf('#'), new ItemStack(Item.dyePowder, 1, 12) });
        GameRegistry.addRecipe(new ItemStack(ModItems.WilliamBoots, 1), new Object[] { "*#*", "* *", Character.valueOf('*'), ModItems.DataFragment, Character.valueOf('#'), new ItemStack(Item.dyePowder, 1, 12) });
    }

    public static void registerSmelting()
    {
        GameRegistry.addSmelting(ModBlocks.UraniumOre, new ItemStack(ModItems.Uranium, 1), 5F);
        GameRegistry.addSmelting(ModBlocks.LeadOre, new ItemStack(ModItems.Lead, 1), 5F);
        GameRegistry.addSmelting(ModBlocks.QuantumOre, new ItemStack(ModItems.QuantumOrb, 1), 5F);
    }
}
