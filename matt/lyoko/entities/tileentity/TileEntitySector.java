/*
 * Code LyCoordoko Mod for Minecraft v@VERSION
 *
 * CopyCoordright 2013 CortexCoord Modders, Matthew Warren, Jacob Rhoda, and
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
		int dimId = worldObj.provider.dimensionId;
		if(dimId == DimensionIds.CARTHAGE)
		{
			worldObj.removeBlockTileEntity(xCoord, yCoord, zCoord);
			worldObj.setBlock(xCoord, yCoord, zCoord, ModBlocks.Carthage.blockID);
		}
		else if(dimId == DimensionIds.DESERT)
		{
			worldObj.removeBlockTileEntity(xCoord, yCoord, zCoord);
			worldObj.setBlock(xCoord, yCoord, zCoord, ModBlocks.Sand.blockID);
		}
		else if(dimId == DimensionIds.FOREST)
		{
			worldObj.removeBlockTileEntity(xCoord, yCoord, zCoord);
			worldObj.setBlock(xCoord, yCoord, zCoord, ModBlocks.Grass.blockID);
		}
		else if(dimId == DimensionIds.ICE)
		{
			worldObj.removeBlockTileEntity(xCoord, yCoord, zCoord);
			worldObj.setBlock(xCoord, yCoord, zCoord, ModBlocks.Ice.blockID);
		}
		else if(dimId == DimensionIds.MOUNTAIN)
		{
			worldObj.setBlock(xCoord, yCoord, zCoord, ModBlocks.Stone.blockID);
		}
	}
}