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
import matt.lyoko.blocks.BlockScanner;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;

public class TileEntityHolomap extends TileEntity
{
	public byte sector = 0;
	
	@Override
	public void updateEntity()
	{
		if(this.sector != 0)
		{
			for(int i = -1; i < 2; i++)
			{
				for(int j = -1; j < 2; j++)
				{
					if(i != 0 || j != 0)
					{
						if(BlockHolomap.isMultiBlock(worldObj, xCoord + i, yCoord, zCoord + j))
						{
							TileEntityHolomap core = (TileEntityHolomap)worldObj.getBlockTileEntity(xCoord + i, yCoord, zCoord + j);
							if(this.sector != core.sector && core.sector == 0)
							{
								core.sector = this.sector;
							}
							this.sector = 0;
						}
					}
				}
			}
		}
		
		if(BlockHolomap.isMultiBlock(worldObj, xCoord, yCoord, zCoord))
		{
			byte meta = (byte) worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
			if(meta == 0b1000)
			{
				byte metaSector = (byte) (meta & 0b111);
				if(metaSector != sector)
				{
					metaSector = sector;
					meta &= ~0b111;
					meta |= metaSector;
					worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, meta, 3);
				}
			}
		}
	}
	
	@Override
    public Packet getDescriptionPacket()
    {
        NBTTagCompound tag = new NBTTagCompound();
        this.writeToNBT(tag);
        return new Packet132TileEntityData(xCoord, yCoord, zCoord, 0, tag);
    }
	
    @Override
    public void onDataPacket(INetworkManager net, Packet132TileEntityData pkt)
    {
        NBTTagCompound tag = pkt.customParam1;
        this.readFromNBT(tag);
    }
    
    @Override
    public void readFromNBT(NBTTagCompound tagCompound)
    {
        super.readFromNBT(tagCompound);
        this.sector = tagCompound.getByte("sector");
    }
    
    @Override
    public void writeToNBT(NBTTagCompound tagCompound)
    {
        super.writeToNBT(tagCompound);
        tagCompound.setByte("sector", this.sector);
    }
}