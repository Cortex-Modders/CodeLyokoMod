package matt.lyoko.entities.tileentity;

import cpw.mods.fml.relauncher.*;
import java.util.*;

import matt.lyoko.CodeLyoko;
import net.minecraft.block.Block;
import net.minecraft.entity.*;
import net.minecraft.nbt.*;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.*;
import net.minecraft.tileentity.*;
import net.minecraft.util.*;
import net.minecraft.world.World;

public class TileEntityCable extends TileEntity
{
	private String sector = "";
	private int coolDown = 0;
	
	public void updateEntity()
	{
		if(coolDown > 0)
		{
			coolDown--;
		}
		else if(coolDown < 0)
		{
			coolDown = 0;
		}
		
		if(!sector.equals(""))
		{
			if(coolDown == 0)
			{
				sendData(this.xCoord + 1, this.yCoord, this.zCoord, sector);
				sendData(this.xCoord - 1, this.yCoord, this.zCoord, sector);
				sendData(this.xCoord, this.yCoord + 1, this.zCoord, sector);
				sendData(this.xCoord, this.yCoord - 1, this.zCoord, sector);
				sendData(this.xCoord, this.yCoord, this.zCoord + 1, sector);
				sendData(this.xCoord, this.yCoord, this.zCoord - 1, sector);
				coolDown = 100;
				System.out.println(sector + " " + xCoord + " " + yCoord + " " + zCoord);
			}
			this.sector = "";
		}
	}
	
	private void sendData(int x, int y, int z, String sector)
	{
		if(worldObj.getBlockId(x, y, z) == CodeLyoko.Cable.blockID && worldObj.getBlockTileEntity(x, y, x) != null)
		{
			TileEntityCable tileCable = (TileEntityCable)worldObj.getBlockTileEntity(x, y, z);
			tileCable.setSector(sector);
			worldObj.markBlockForUpdate(x, y, z);
		}
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
        tag.setString("sector", this.sector);
        
        return new Packet132TileEntityData(xCoord, yCoord, zCoord, 1, tag);
    }
	
    @Override
    public void onDataPacket(INetworkManager net, Packet132TileEntityData pkt)
    {
        NBTTagCompound tag = pkt.customParam1;
        this.sector = tag.getString("sector");
    }
	
	@Override
	public void readFromNBT(NBTTagCompound tagCompound)
	{
		super.readFromNBT(tagCompound);
		this.coolDown = tagCompound.getInteger("cool");
		this.sector = tagCompound.getString("sector");
	}
	
	@Override
	public void writeToNBT(NBTTagCompound tagCompound)
	{
		super.writeToNBT(tagCompound);
        tagCompound.setInteger("cool", this.coolDown);
		tagCompound.setString("sector", this.sector);
	}
}