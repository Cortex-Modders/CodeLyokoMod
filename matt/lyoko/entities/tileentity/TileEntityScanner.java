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
	
	public boolean doorsOpen = true;
	
	@Override
	public void updateEntity()
	{
		if(this.sector != -1)
		{
			for(int i = -4; i < 0; i++)
			{
				if(BlockScanner.isMultiBlock(worldObj, xCoord, yCoord + i, zCoord))
				{
					TileEntityScanner core = (TileEntityScanner)worldObj.getBlockTileEntity(xCoord, yCoord + i, zCoord);
					if(this.sector != core.sector && core.sector == -1)
					{
						core.sector = this.sector;
					}
					this.sector = -1;
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
    
    public void toggleDoors() {
        
    }
	
    public void toggleDoors(boolean p) {
        //this.
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