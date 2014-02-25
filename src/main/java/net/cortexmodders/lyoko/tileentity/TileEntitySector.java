/*
 * Code Lyoko Mod for Minecraft ${version}
 * Copyright 2014 Cortex Modders, Matthew Warren, Jacob Rhoda, and other contributors.
 * Released under the MIT license http://opensource.org/licenses/MIT
 */

package net.cortexmodders.lyoko.tileentity;

import net.cortexmodders.lyoko.blocks.ModBlocks;
import net.cortexmodders.lyoko.lib.DimensionIds;
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
                this.worldObj.removeTileEntity(this.xCoord, this.yCoord, this.zCoord);
                this.worldObj.setBlock(this.xCoord, this.yCoord, this.zCoord, ModBlocks.carthage, 0, 3);
            }
            else if (dimId == DimensionIds.DESERT)
            {
                this.worldObj.removeTileEntity(this.xCoord, this.yCoord, this.zCoord);
                this.worldObj.setBlock(this.xCoord, this.yCoord, this.zCoord, ModBlocks.sand, 0, 3);
            }
            else if (dimId == DimensionIds.FOREST)
            {
                this.worldObj.removeTileEntity(this.xCoord, this.yCoord, this.zCoord);
                this.worldObj.setBlock(this.xCoord, this.yCoord, this.zCoord, ModBlocks.grass, 0, 3);
                
            }
            else if (dimId == DimensionIds.ICE)
            {
                this.worldObj.removeTileEntity(this.xCoord, this.yCoord, this.zCoord);
                this.worldObj.setBlock(this.xCoord, this.yCoord, this.zCoord, ModBlocks.ice, 0, 3);
            }
            else if (dimId == DimensionIds.MOUNTAIN)
            {
                this.worldObj.removeTileEntity(this.xCoord, this.yCoord, this.zCoord);
                this.worldObj.setBlock(this.xCoord, this.yCoord, this.zCoord, ModBlocks.stone, 0, 3);
            }
            for (int i = -1; i < 2; i++)
                for (int j = -1; j < 2; j++)
                    for (int k = -1; k < 2; k++)
                        if (i != 0 || j != 0 || k != 0)
                            this.worldObj.markBlockForUpdate(this.xCoord + i, this.yCoord + j, this.zCoord + k);
        }
    }
}
