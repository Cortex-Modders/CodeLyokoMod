/*
 * Code Lyoko Mod for Minecraft v@VERSION
 * Copyright 2013 Cortex Modders, Matthew Warren, Jacob Rhoda, and
 * other contributors.
 * Released under the MIT license
 * http://opensource.org/licenses/MIT
 */

package matt.lyoko.items;

import java.util.List;

import matt.lyoko.blocks.ModBlocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemBlockEffect extends ItemBlock
{
    public ItemBlockEffect(int par1)
    {
        super(par1);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean B)
    {
        if (stack.itemID == ModBlocks.TowerBase.blockID)
        {
            list.add("hold shift when placing the block");
            list.add("to be able to walk through it.");
            list.add("otherwise, place it normally");
        }
    }

    @Override
    public void onUpdate(ItemStack stack, World world, Entity ent, int par4, boolean par5)
    {
        if (stack.itemID == ModBlocks.LeadOre.blockID || stack.itemID == ModBlocks.UraniumOre.blockID)
        {
            if (ent instanceof EntityPlayer)
            {
                if (!((EntityPlayer) ent).capabilities.isCreativeMode)
                {
                    ((EntityPlayer) ent).addPotionEffect(new PotionEffect(Potion.hunger.getId(), 500, 0));
                    ((EntityPlayer) ent).addPotionEffect(new PotionEffect(Potion.poison.getId(), 500, 0));
                }
            } else if (ent instanceof EntityLiving)
                ((EntityLiving) ent).addPotionEffect(new PotionEffect(Potion.poison.getId(), 500, 0));
        } else if (stack.itemID == ModBlocks.QuantumOre.blockID)
            if (ent instanceof EntityLiving)
            {
                ((EntityLiving) ent).clearActivePotions();
                ((EntityLiving) ent).addPotionEffect(new PotionEffect(Potion.regeneration.getId(), 500, 0));
            }
    }
}
