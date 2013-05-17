package matt.lyoko.entities.tileentity;

import cpw.mods.fml.relauncher.*;
import java.util.*;

import matt.lyoko.CodeLyoko;
import net.minecraft.entity.*;
import net.minecraft.nbt.*;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.*;
import net.minecraft.tileentity.*;
import net.minecraft.util.*;
import net.minecraft.world.World;

public class TileEntityCable extends TileEntity
{
	private int[] sourceLocation = {this.xCoord, this.yCoord, this.zCoord};
	private String sector = "";
	
	public void updateEntity()
	{
		if(!sector.equals(""))
		{
			sendData(this.xCoord + 1, this.yCoord, this.zCoord, sector);
			sendData(this.xCoord - 1, this.yCoord, this.zCoord, sector);
			sendData(this.xCoord, this.yCoord + 1, this.zCoord, sector);
			sendData(this.xCoord, this.yCoord - 1, this.zCoord, sector);
			sendData(this.xCoord, this.yCoord, this.zCoord + 1, sector);
			sendData(this.xCoord, this.yCoord, this.zCoord - 1, sector);
			System.out.println(this.sector + " " + this.sourceLocation[0] + " " + this.sourceLocation[1] + " " + this.sourceLocation[2]);
			this.sector = "";
		}
	}
	
	private void sendData(int x, int y, int z, String sector)
	{
		if(x != sourceLocation[0] || y != sourceLocation[1] || z != sourceLocation[2])
		{
			if(worldObj.getBlockId(x, y, z) == CodeLyoko.Cable.blockID && worldObj.getBlockTileEntity(x, y, x) != null)
			{
				TileEntityCable tileCable = (TileEntityCable)worldObj.getBlockTileEntity(x, y, z);
				tileCable.setSourceLocation(xCoord, yCoord, zCoord);
				tileCable.setSector(sector);
			}
		}
		worldObj.markBlockForUpdate(x, y, z);
	}
	
	public void setSourceLocation(int x, int y, int z)
	{
		sourceLocation[0] = x;
		sourceLocation[1] = y;
		sourceLocation[2] = z;
	}
	
	public void setSector(String sector)
	{
		this.sector = sector;
	}
	
	@Override
    public Packet getDescriptionPacket()
	{
        Packet132TileEntityData packet = (Packet132TileEntityData) super.getDescriptionPacket();
        NBTTagCompound tag = packet != null ? packet.customParam1 : new NBTTagCompound();
        tag.setIntArray("source", this.sourceLocation);
        tag.setString("sector", this.sector);
        
        return new Packet132TileEntityData(xCoord, yCoord, zCoord, 1, tag);
    }
	
    @Override
    public void onDataPacket(INetworkManager net, Packet132TileEntityData pkt)
    {
        NBTTagCompound tag = pkt.customParam1;
        this.sourceLocation = tag.getIntArray("source");
        this.sector = tag.getString("sector");
    }
	
	@Override
	public void readFromNBT(NBTTagCompound tagCompound)
	{
		super.readFromNBT(tagCompound);
		this.sourceLocation = tagCompound.getIntArray("source");
		this.sector = tagCompound.getString("sector");
	}
	
	@Override
	public void writeToNBT(NBTTagCompound tagCompound)
	{
		super.writeToNBT(tagCompound);
		tagCompound.setIntArray("source", this.sourceLocation);
		tagCompound.setString("sector", this.sector);
	}
}