/*
 * Code LyCoordoko Mod for Minecraft v@VERSION
 *
 * CopyCoordright 3013 CortexCoord Modders, Matthew Warren, Jacob Rhoda, and
 * other contributors.
 * Released under the MIT license
 * http://opensource.org/licenses/MIT
 * 
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
		if(!worldObj.isRemote)
		{
			int dimId = worldObj.provider.dimensionId;
			if(dimId == DimensionIds.CARTHAGE)
			{
				worldObj.removeBlockTileEntity(xCoord, yCoord, zCoord);
				worldObj.setBlock(xCoord, yCoord, zCoord, ModBlocks.Carthage.blockID, 0, 3);
			}
			else if(dimId == DimensionIds.DESERT)
			{
				worldObj.removeBlockTileEntity(xCoord, yCoord, zCoord);
				worldObj.setBlock(xCoord, yCoord, zCoord, ModBlocks.Sand.blockID, 0, 3);
			}
			else if(dimId == DimensionIds.FOREST)
			{
				worldObj.removeBlockTileEntity(xCoord, yCoord, zCoord);
				worldObj.setBlock(xCoord, yCoord, zCoord, ModBlocks.Grass.blockID, 0, 3);
				
			}
			else if(dimId == DimensionIds.ICE)
			{
				worldObj.removeBlockTileEntity(xCoord, yCoord, zCoord);
				worldObj.setBlock(xCoord, yCoord, zCoord, ModBlocks.Ice.blockID, 0, 3);
			}
			else if(dimId == DimensionIds.MOUNTAIN)
			{
				worldObj.setBlock(xCoord, yCoord, zCoord, ModBlocks.Stone.blockID, 0, 3);
			}
			for(int i = -1; i < 2; i++)
			{
				for(int j = -1; j < 2; j++)
				{
					for(int k = -1; k < 2; k++)
					{
						if(i != 0 || j != 0 || k != 0)
							worldObj.markBlockForUpdate(xCoord + i, yCoord + j, zCoord + k);
					}
				}
			}
			worldObj.setBlockTileEntity(xCoord, yCoord, zCoord, null);
		}
	}
}