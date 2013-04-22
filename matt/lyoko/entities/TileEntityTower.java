package matt.lyoko.entities;

import java.util.Random;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;
import matt.lyoko.*;
import matt.lyoko.particles.LyokoParticleEffects;

public class TileEntityTower extends TileEntity
{

	public boolean isActivated = false;
	public String owner = "none";

	public void updateEntity()
	{
		/*if(owner == "xana")
		{
			LyokoParticleEffects.spawnParticle("xana", xCoord, yCoord, zCoord, 0.0D, 0.0D, 0.0D);
		}
		else if(owner == "lyoko")
		{
			LyokoParticleEffects.spawnParticle("lyoko", xCoord, yCoord, zCoord, 0.0D, 0.0D, 0.0D);
		}
		else if(owner == "none")
		{
			LyokoParticleEffects.spawnParticle("deactivated", xCoord, yCoord, zCoord, 0.0D, 0.0D, 0.0D);
		}*/
		System.out.println(owner + " " + this.worldObj.isRemote);
	}
	
	@Override
    public Packet getDescriptionPacket()
	{
        Packet132TileEntityData packet = (Packet132TileEntityData) super.getDescriptionPacket();
        NBTTagCompound tag = packet != null ? packet.customParam1 : new NBTTagCompound();

        //addInfoToNBT(tag);

        return new Packet132TileEntityData(xCoord, yCoord, zCoord, 1, tag);
    }

    @Override
    public void onDataPacket(INetworkManager net, Packet132TileEntityData pkt)
    {
        NBTTagCompound tag = pkt.customParam1;
        //loadInfoFromNBT(tag);
    }

	@Override
	public void readFromNBT(NBTTagCompound tagCompound)
	{
		super.readFromNBT(tagCompound);
		this.isActivated = tagCompound.getBoolean("isActive");
		this.owner = tagCompound.getString("towerOwner");
	}

	@Override
	public void writeToNBT(NBTTagCompound tagCompound)
	{
		super.writeToNBT(tagCompound);
		tagCompound.setBoolean("isActive", this.isActivated);
		tagCompound.setString("towerOwner", this.owner);
	}
}
