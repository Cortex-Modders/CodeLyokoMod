/*
 * Code Lyoko Mod for Minecraft ${version}
 * Copyright 2014 Cortex Modders, Matthew Warren, Jacob Rhoda, and other contributors.
 * Released under the MIT license http://opensource.org/licenses/MIT
 */

package net.cortexmodders.lyoko.items;

import net.cortexmodders.lyoko.blocks.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemBlockVirtual extends ItemBlock
{
    public ItemBlockVirtual(Block block)
    {
        super(block);
    }
    
    @Override
    public void onUpdate(ItemStack stack, World world, Entity ent, int slot, boolean par5)
    {
        if (ent instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer) ent;
            
            if (player.getCurrentArmor(4) != null && player.getCurrentArmor(3) != null && player.getCurrentArmor(2) != null && player.getCurrentArmor(1) != null)
            {
                ItemStack helmet = player.getCurrentArmor(4);
                ItemStack chest = player.getCurrentArmor(3);
                ItemStack legs = player.getCurrentArmor(2);
                ItemStack boots = player.getCurrentArmor(1);
                if (helmet.getItem() == ModItems.aelitaHelmet && chest.getItem() == ModItems.aelitaChest && legs.getItem() == ModItems.aelitaLegs && boots.getItem() == ModItems.aelitaBoots)
                {
                    if (stack.getItem() != Item.getItemFromBlock(ModBlocks.virtualBlock))
                        player.inventory.setInventorySlotContents(slot, null);
                }
                else
                    player.inventory.setInventorySlotContents(slot, null);
            }
            else if ((player.getCurrentArmor(4) == null || player.getCurrentArmor(3) == null || player.getCurrentArmor(2) == null || player.getCurrentArmor(1) == null) && player.capabilities.isCreativeMode == false)
                player.inventory.setInventorySlotContents(slot, null);
        }
    }
}
