/*
 * Code Lyoko Mod for Minecraft ${version}
 * Copyright 2014 Cortex Modders, Matthew Warren, Jacob Rhoda, and other contributors.
 * Released under the MIT license http://opensource.org/licenses/MIT
 */

package net.cortexmodders.lyoko.items;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemBlockTowerFloor extends ItemBlock
{
    public ItemBlockTowerFloor(Block block)
    {
		super(block);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean B)
    {
        list.add("Shift right click when placing");
        list.add("the block to make the platform");
        list.add("send the player down, otherwise");
        list.add("just place it normally.");
    }

    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int blockFace, float par8, float par9, float par10)
    {
        Block block = world.getBlock(x, y, z);

        if (block == Block.getBlockFromName("snow") && (world.getBlockMetadata(x, y, z) & 7) < 1)
            blockFace = 1;
        else if (block != Block.getBlockFromName("vine") && block != Block.getBlockFromName("tallgrass") && block != Block.getBlockFromName("deadbush") && (block == null || !block.isReplaceable(world, x, y, z)))
        {
            if (blockFace == 0)
                --y;

            if (blockFace == 1)
                ++y;

            if (blockFace == 2)
                --z;

            if (blockFace == 3)
                ++z;

            if (blockFace == 4)
                --x;

            if (blockFace == 5)
                ++x;
        }

        if (stack.stackSize == 0)
            return false;
        else if (!player.canPlayerEdit(x, y, z, blockFace, stack))
            return false;
        else if (y == 255 && this.field_150939_a.getMaterial().isSolid())
            return false;
        else if (world.canPlaceEntityOnSide(this.field_150939_a, x, y, z, false, blockFace, player, stack))
        {
            int j1 = this.getMetadata(stack.getItemDamage());
            int k1 = this.field_150939_a.onBlockPlaced(world, x, y, z, blockFace, par8, par9, par10, j1);

            if (this.placeBlockAt(stack, player, world, x, y, z, blockFace, par8, par9, par10, k1))
            {
                world.playSoundEffect(x + 0.5F, y + 0.5F, z + 0.5F, this.field_150939_a.stepSound.func_150496_b(), (this.field_150939_a.stepSound.getVolume() + 1.0F) / 2.0F, this.field_150939_a.stepSound.getPitch() * 0.8F);
                --stack.stackSize;
            }
            if (player.isSneaking())
                world.setBlockMetadataWithNotify(x, y, z, 1, 2);

            return true;
        } else
            return false;
    }
}
