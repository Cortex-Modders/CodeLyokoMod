package matt.lyoko.entities.tileentity;

import matt.lyoko.CodeLyoko;
import matt.lyoko.lib.BlockIds;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;

public class TileEntityMarabounta extends TileEntity
{
	public int consumedBlock = 0;
	
	public void updateEntity()
	{
		syncMarabounta(xCoord+1, yCoord, zCoord);
		syncMarabounta(xCoord-1, yCoord, zCoord);
		syncMarabounta(xCoord, yCoord+1, zCoord);
		syncMarabounta(xCoord, yCoord-1, zCoord);
		syncMarabounta(xCoord, yCoord, zCoord+1);
		syncMarabounta(xCoord, yCoord, zCoord-1);
	}
	
	public void syncMarabounta(int x, int y, int z)
	{
		if(worldObj.getBlockId(x, y, z) == CodeLyoko.Marabounta.blockID && worldObj.getBlockTileEntity(x, y, z) != null
				&& worldObj.getBlockMetadata(x, y, z) == 0)
		{
			if(worldObj.getBlockMetadata(xCoord, yCoord, zCoord) == 1)
			{
				TileEntityMarabounta temp = ((TileEntityMarabounta)worldObj.getBlockTileEntity(x, y, z));
				worldObj.setBlockMetadataWithNotify(x, y, z, 1, 2);
				worldObj.setBlockTileEntity(x, y, z, temp);
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
		this.consumedBlock = tagCompound.getInteger("block");
	}

	@Override
	public void writeToNBT(NBTTagCompound tagCompound)
	{
		super.writeToNBT(tagCompound);
		tagCompound.setInteger("block", this.consumedBlock);
	}
}