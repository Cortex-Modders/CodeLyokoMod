/*
 * Code Lyoko Mod for Minecraft v@VERSION Copyright 2013 Cortex Modders, Matthew
 * Warren, Jacob Rhoda, and other contributors. Released under the MIT license
 * http://opensource.org/licenses/MIT
 */

package matt.lyoko.blocks;

import java.util.Random;

import matt.lyoko.entities.tileentity.TileEntitySector;
import matt.lyoko.lib.DimensionIds;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockSector extends Block
{
    public BlockSector(int par1)
    {
        super(par1, Material.iron);
    }

    @Override
    public void updateTick(World world, int x, int y, int z, Random rand)
    {
        super.updateTick(world, x, y, z, rand);
        if(!world.isRemote)
        {
            int dimId = world.provider.dimensionId;
            if (dimId == DimensionIds.CARTHAGE)
            {
                world.removeBlockTileEntity(x, y, z);
                world.setBlock(x, y, z, ModBlocks.Carthage.blockID, 0, 3);
            }
            else if (dimId == DimensionIds.DESERT)
            {
                world.removeBlockTileEntity(x, y, z);
                world.setBlock(x, y, z, ModBlocks.Sand.blockID, 0, 3);
            }
            else if (dimId == DimensionIds.FOREST)
            {
                world.removeBlockTileEntity(x, y, z);
                world.setBlock(x, y, z, ModBlocks.Grass.blockID, 0, 3);

            }
            else if (dimId == DimensionIds.ICE)
            {
                world.removeBlockTileEntity(x, y, z);
                world.setBlock(x, y, z, ModBlocks.Ice.blockID, 0, 3);
            }
            else if (dimId == DimensionIds.MOUNTAIN)
            {
                world.setBlock(x, y, z, ModBlocks.Stone.blockID, 0, 3);
            }
            
            for (int i = -1; i < 2; i++)
            {
                for (int j = -1; j < 2; j++)
                {
                    for (int k = -1; k < 2; k++)
                    {
                        if (i != 0 || j != 0 || k != 0)
                        {
                            world.markBlockForUpdate(x + i, y + j, z + k);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onEntityWalking(World world, int x, int y, int z, Entity ent)
    {
        this.updateTick(world, x, y, z, null);
    }
}
