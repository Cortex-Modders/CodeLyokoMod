/*
 * Code Lyoko Mod for Minecraft v@VERSION
 *
 * Copyright 2013 Cortex Modders, Matthew Warren, Jacob Rhoda, and
 * other contributors.
 * Released under the MIT license
 * http://opensource.org/licenses/MIT
 * 
 */

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