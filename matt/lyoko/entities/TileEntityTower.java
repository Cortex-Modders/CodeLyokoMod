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
	private String[] possibleOwners = {"none", "lyoko", "xana", "developer"};

	public void updateEntity()
	{
		if(!(owner.equals("none")))
		{
			boolean validOwner = false;
			
			for(int i = 0; i < possibleOwners.length; i++)
			{
				if(owner == possibleOwners[i])
				{
					validOwner = true;
				}
			}
			
			if(validOwner)
			{
				isActivated = true;
			}
		}
		else
		{
			isActivated = false;
		}
		
		if(owner.equals("developer"))
		{
			syncTower(xCoord+1, yCoord, zCoord, "developer");
			syncTower(xCoord-1, yCoord, zCoord, "developer");
			syncTower(xCoord, yCoord+1, zCoord, "developer");
			syncTower(xCoord, yCoord-1, zCoord, "developer");
			syncTower(xCoord, yCoord, zCoord+1, "developer");
			syncTower(xCoord, yCoord, zCoord-1, "developer");
		}
		else if(owner.equals("xana"))
		{
			syncTower(xCoord+1, yCoord, zCoord, "xana");
			syncTower(xCoord-1, yCoord, zCoord, "xana");
			syncTower(xCoord, yCoord+1, zCoord, "xana");
			syncTower(xCoord, yCoord-1, zCoord, "xana");
			syncTower(xCoord, yCoord, zCoord+1, "xana");
			syncTower(xCoord, yCoord, zCoord-1, "xana");
		}
		else if(owner.equals("lyoko"))
		{
			syncTower(xCoord+1, yCoord, zCoord, "lyoko");
			syncTower(xCoord-1, yCoord, zCoord, "lyoko");
			syncTower(xCoord, yCoord+1, zCoord, "lyoko");
			syncTower(xCoord, yCoord-1, zCoord, "lyoko");
			syncTower(xCoord, yCoord, zCoord+1, "lyoko");
			syncTower(xCoord, yCoord, zCoord-1, "lyoko");
		}
		else if(owner.equals("none"))
		{
			syncTower(xCoord+1, yCoord, zCoord, "none");
			syncTower(xCoord-1, yCoord, zCoord, "none");
			syncTower(xCoord, yCoord+1, zCoord, "none");
			syncTower(xCoord, yCoord-1, zCoord, "none");
			syncTower(xCoord, yCoord, zCoord+1, "none");
			syncTower(xCoord, yCoord, zCoord-1, "none");
		}
	}
	
	public void syncTower(int x, int y, int z, String newOwner)
	{
		if(worldObj.getBlockId(x, y, z) == CodeLyoko.TowerBlock.blockID && worldObj.getBlockTileEntity(x, y, z) != null &&
				((TileEntityTower)worldObj.getBlockTileEntity(x, y, z)).owner != newOwner &&
				ownerValue(newOwner) > ownerValue(((TileEntityTower)worldObj.getBlockTileEntity(x, y, z)).owner))
		{
			((TileEntityTower)worldObj.getBlockTileEntity(x, y, z)).owner = newOwner;
		}
	}
	
	public int ownerValue(String newOwner)
	{
		if(newOwner == "developer")
		{
			return 10;
		}
		else if(newOwner == "xana")
		{
			return 2;
		}
		else if(newOwner == "lyoko")
		{
			return 1;
		}
		else if(newOwner == "none")
		{
			return 0;
		}
		return -1;
	}
	
	@Override
    public Packet getDescriptionPacket()
	{
        Packet132TileEntityData packet = (Packet132TileEntityData) super.getDescriptionPacket();
        NBTTagCompound tag = packet != null ? packet.customParam1 : new NBTTagCompound();
        tag.setString("towerOwner", this.owner);
        
        return new Packet132TileEntityData(xCoord, yCoord, zCoord, 1, tag);
    }

    @Override
    public void onDataPacket(INetworkManager net, Packet132TileEntityData pkt)
    {
        NBTTagCompound tag = pkt.customParam1;
        this.owner = tag.getString("towerOwner");
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
