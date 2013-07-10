package matt.lyoko.entities.tileentity;

import matt.lyoko.blocks.BlockScanner;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;

public class TileEntityScanner extends TileEntity
{
	public int sector = -1;
	
	@Override
	public void updateEntity()
	{
		if(this.sector != -1)
		{
			for(int i = -1; i < 2; i++)
			{
				for(int k = -1; k < 2; k++)
				{
					for(int j = -4; j < 1; j++)
					{
						if(i != 0 || j != 0 || k != 0)
						{
							if(BlockScanner.isMultiBlock(worldObj, xCoord + i, yCoord + j, zCoord + k))
							{
								TileEntityScanner core = (TileEntityScanner)worldObj.getBlockTileEntity(xCoord + i, yCoord + j, zCoord + k);
								if(this.sector != core.sector && core.sector == -1)
								{
									core.sector = this.sector;
								}
								this.sector = -1;
							}
						}
					}
				}
			}
		}
	}
	
	@Override
    public Packet getDescriptionPacket()
	{
        Packet132TileEntityData packet = (Packet132TileEntityData) super.getDescriptionPacket();
        NBTTagCompound tag = packet != null ? packet.customParam1 : new NBTTagCompound();
        tag.setInteger("sector", this.sector);
        
        return new Packet132TileEntityData(xCoord, yCoord, zCoord, 1, tag);
    }
	
    @Override
    public void onDataPacket(INetworkManager net, Packet132TileEntityData pkt)
    {
        NBTTagCompound tag = pkt.customParam1;
        this.sector = tag.getInteger("sector");
    }
	
	@Override
	public void readFromNBT(NBTTagCompound tagCompound)
	{
		super.readFromNBT(tagCompound);
		this.sector = tagCompound.getInteger("sector");
	}
	
	@Override
	public void writeToNBT(NBTTagCompound tagCompound)
	{
		super.writeToNBT(tagCompound);
        tagCompound.setInteger("sector", this.sector);
	}
}