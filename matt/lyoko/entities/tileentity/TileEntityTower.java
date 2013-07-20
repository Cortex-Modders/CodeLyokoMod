package matt.lyoko.entities.tileentity;

import matt.lyoko.blocks.ModBlocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;

public class TileEntityTower extends TileEntity
{
	public String owner = "none";
	private static String[] possibleOwners = {"none", "developer", "xana", "lyoko", "reset"};
	private int resetTicks = 12000;

	public void updateEntity()
	{
		if(owner.equals("reset"))
		{
			owner = "none";
			syncTower(xCoord+1, yCoord, zCoord, "reset");
			syncTower(xCoord-1, yCoord, zCoord, "reset");
			syncTower(xCoord, yCoord+1, zCoord, "reset");
			syncTower(xCoord, yCoord-1, zCoord, "reset");
			syncTower(xCoord, yCoord, zCoord+1, "reset");
			syncTower(xCoord, yCoord, zCoord-1, "reset");
			resetTicks = 12000;
		}
		else if(owner.equals("developer"))
		{
			syncTower(xCoord+1, yCoord, zCoord, "developer");
			syncTower(xCoord-1, yCoord, zCoord, "developer");
			syncTower(xCoord, yCoord+1, zCoord, "developer");
			syncTower(xCoord, yCoord-1, zCoord, "developer");
			syncTower(xCoord, yCoord, zCoord+1, "developer");
			syncTower(xCoord, yCoord, zCoord-1, "developer");
			resetTicks--;
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
		
		if(resetTicks <= 0)
		{
			owner = "reset";
			resetTicks = 12000;
		}
		
	}
	
	public static String[] getPossibleOwners()
	{
		return possibleOwners;
	}
	
	public void syncTower(int x, int y, int z, String newOwner)
	{
		if((worldObj.getBlockId(x, y, z) == ModBlocks.TowerBlock.blockID || worldObj.getBlockId(x, y, z) == ModBlocks.TowerBaseFake.blockID)
				&& worldObj.getBlockTileEntity(x, y, z) != null && ((TileEntityTower)worldObj.getBlockTileEntity(x, y, z)).owner != newOwner
				&& ownerValue(newOwner) > ownerValue(((TileEntityTower)worldObj.getBlockTileEntity(x, y, z)).owner))
		{
			if(!newOwner.equals("reset") || !((TileEntityTower)worldObj.getBlockTileEntity(x, y, z)).owner.equals("none"))
			{
				((TileEntityTower)worldObj.getBlockTileEntity(x, y, z)).owner = newOwner;
			}
		}
		else if((worldObj.getBlockId(x, y, z) == ModBlocks.TowerBlock.blockID || worldObj.getBlockId(x, y, z) == ModBlocks.TowerBaseFake.blockID)
				&& worldObj.getBlockTileEntity(x, y, z) != null && ((TileEntityTower)worldObj.getBlockTileEntity(x, y, z)).owner != newOwner
				&& newOwner.equals("none") && ((TileEntityTower)worldObj.getBlockTileEntity(x, y, z)).owner.equals("reset"))
		{
			((TileEntityTower)worldObj.getBlockTileEntity(x, y, z)).owner = newOwner;
		}
	}
	
	public int ownerValue(String newOwner)
	{
		if(newOwner == "reset")
		{
			return 100;
		}
		else if(newOwner == "developer")
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
		this.owner = tagCompound.getString("towerOwner");
		this.resetTicks = tagCompound.getInteger("resetTime");
	}
	
	@Override
	public void writeToNBT(NBTTagCompound tagCompound)
	{
		super.writeToNBT(tagCompound);
		tagCompound.setString("towerOwner", this.owner);
		tagCompound.setInteger("resetTime", this.resetTicks);
	}
}