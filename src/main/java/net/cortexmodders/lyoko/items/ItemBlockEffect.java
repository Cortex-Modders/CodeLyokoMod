/*
 * Code Lyoko Mod for Minecraft v@VERSION
 * Copyright 2013 Cortex Modders, Matthew Warren, Jacob Rhoda, and
 * other contributors.
 * Released under the MIT license
 * http://opensource.org/licenses/MIT
 */

package net.cortexmodders.lyoko.items;

import java.util.List;

import net.cortexmodders.lyoko.blocks.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemBlockEffect extends ItemBlock
{
    public ItemBlockEffect(Block block)
    {
        super(block);
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean B)
    {
        if(stack.getItem().equals(Item.getItemFromBlock(ModBlocks.towerBase)))
        {
            list.add("hold shift when placing the block");
            list.add("to be able to walk through it.");
            list.add("otherwise, place it normally");
        }
    }
    
    @Override
    public void onUpdate(ItemStack stack, World world, Entity ent, int par4, boolean par5)
    {
        if(stack.getItem().equals(Item.getItemFromBlock(ModBlocks.leadOre)) || stack.getItem().equals(Item.getItemFromBlock(ModBlocks.uraniumOre)))
        {
            if(ent instanceof EntityPlayer)
            {
                if(!((EntityPlayer) ent).capabilities.isCreativeMode)
                {
                    ((EntityPlayer) ent).addPotionEffect(new PotionEffect(Potion.hunger.getId(), 500, 0));
                    ((EntityPlayer) ent).addPotionEffect(new PotionEffect(Potion.poison.getId(), 500, 0));
                }
            }
            else if(ent instanceof EntityLiving)
            {
                ((EntityLiving) ent).addPotionEffect(new PotionEffect(Potion.poison.getId(), 500, 0));
            }
        }
        else if(stack.getItem().equals(Item.getItemFromBlock(ModBlocks.quantumOre)))
        {
            if(ent instanceof EntityLiving)
            {
                ((EntityLiving) ent).clearActivePotions();
                ((EntityLiving) ent).addPotionEffect(new PotionEffect(Potion.regeneration.getId(), 500, 0));
            }
        }
    }
}