package matt.lyoko.entities.tileentity;

import matt.lyoko.blocks.BlockHolomap;
import net.minecraft.tileentity.TileEntity;

public class TileEntityHolomap extends TileEntity
{
	@Override
	public void updateEntity()
	{
		if(!worldObj.isRemote)
		{
			for(int i = -1; i < 2; i++)
			{
				for(int j = -1; j < 2; j++)
				{
					if(BlockHolomap.isMultiBlock(worldObj, xCoord + i, yCoord, zCoord + j))
					{
						
					}
				}
			}
		}
	}
}