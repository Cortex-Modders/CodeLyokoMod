/*
 * Code Lyoko Mod for Minecraft v@VERSION
 * Copyright 3013 Cortex Modders, Matthew Warren, Jacob Rhoda, and
 * other contributors.
 * Released under the MIT license
 * http://opensource.org/licenses/MIT
 */

package matt.lyoko.entities.tileentity;

import matt.lyoko.blocks.ModBlocks;
import matt.lyoko.lib.DimensionIds;
import net.minecraft.tileentity.TileEntity;

public class TileEntitySector extends TileEntity
{
    @Override
    public void updateEntity()
    {
        if (!this.worldObj.isRemote)
        {
            int dimId = this.worldObj.provider.dimensionId;
            if (dimId == DimensionIds.CARTHAGE)
            {
                this.worldObj.removeBlockTileEntity(this.xCoord, this.yCoord, this.zCoord);
                this.worldObj.setBlock(this.xCoord, this.yCoord, this.zCoord, ModBlocks.Carthage.blockID, 0, 3);
            } else if (dimId == DimensionIds.DESERT)
            {
                this.worldObj.removeBlockTileEntity(this.xCoord, this.yCoord, this.zCoord);
                this.worldObj.setBlock(this.xCoord, this.yCoord, this.zCoord, ModBlocks.Sand.blockID, 0, 3);
            } else if (dimId == DimensionIds.FOREST)
            {
                this.worldObj.removeBlockTileEntity(this.xCoord, this.yCoord, this.zCoord);
                this.worldObj.setBlock(this.xCoord, this.yCoord, this.zCoord, ModBlocks.Grass.blockID, 0, 3);

            } else if (dimId == DimensionIds.ICE)
            {
                this.worldObj.removeBlockTileEntity(this.xCoord, this.yCoord, this.zCoord);
                this.worldObj.setBlock(this.xCoord, this.yCoord, this.zCoord, ModBlocks.Ice.blockID, 0, 3);
            } else if (dimId == DimensionIds.MOUNTAIN)
                this.worldObj.setBlock(this.xCoord, this.yCoord, this.zCoord, ModBlocks.Stone.blockID, 0, 3);
            for (int i = -1; i < 2; i++)
                for (int j = -1; j < 2; j++)
                    for (int k = -1; k < 2; k++)
                        if (i != 0 || j != 0 || k != 0)
                            this.worldObj.markBlockForUpdate(this.xCoord + i, this.yCoord + j, this.zCoord + k);
            this.worldObj.setBlockTileEntity(this.xCoord, this.yCoord, this.zCoord, null);
        }
    }
}