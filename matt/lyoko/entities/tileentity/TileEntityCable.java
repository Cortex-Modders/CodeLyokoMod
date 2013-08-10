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

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;

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
		
		if(coolDown == 1)
		{
			setSector("");
		}
	}
	
	public void setSector(String sector)
	{
		this.sector = sector;
	}
	
	public void setCoolDown(int newCoolDown)
	{
		coolDown = newCoolDown;
	}
	
	public String getSector()
	{
		return this.sector;
	}
	
	public int getCoolDown()
	{
		return this.coolDown;
	}
	
	public void resetCoolDown()
	{
		this.coolDown = 5;
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