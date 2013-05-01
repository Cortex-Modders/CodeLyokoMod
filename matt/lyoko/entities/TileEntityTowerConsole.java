package matt.lyoko.entities;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet130UpdateSign;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;

public class TileEntityTowerConsole extends TileEntity
{
	public String owner = "";
	private TileEntityTower[] tetArray = new TileEntityTower[6];
	
	@Override
	public void updateEntity()
	{
		for(int i = 0; i < tetArray.length; i++)
		{
			if(tetArray[i] == null)
			{
				switch(i)
				{
				case 0:
					tetArray[i] = (TileEntityTower) worldObj.getBlockTileEntity(xCoord+1, yCoord, zCoord);
					break;
				case 1:
					tetArray[i] = (TileEntityTower) worldObj.getBlockTileEntity(xCoord-1, yCoord, zCoord);
					break;
				case 2:
					tetArray[i] = (TileEntityTower) worldObj.getBlockTileEntity(xCoord, yCoord+1, zCoord);
					break;
				case 3:
					tetArray[i] = (TileEntityTower) worldObj.getBlockTileEntity(xCoord, yCoord-1, zCoord);
					break;
				case 4:
					tetArray[i] = (TileEntityTower) worldObj.getBlockTileEntity(xCoord, yCoord, zCoord+1);
					break;
				case 5:
					tetArray[i] = (TileEntityTower) worldObj.getBlockTileEntity(xCoord, yCoord, zCoord-1);
					break;
				}
			}
		}
		
		if(!owner.equals(""))
		{
			for(int i = 0; i < TileEntityTower.getPossibleOwners().length; i++)
			{
				if(owner.equals(TileEntityTower.getPossibleOwners()[i]))
				{
					for(int x = 0; x < tetArray.length; x++)
					{
						if(tetArray[x] != null)
						{
							tetArray[x].owner = owner;
						}
					}
				}
			}
		}
		
		System.out.println(owner);
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
		this.owner = tagCompound.getString("towerOwner");
	}

	@Override
	public void writeToNBT(NBTTagCompound tagCompound)
	{
		super.writeToNBT(tagCompound);
		tagCompound.setString("towerOwner", this.owner);
	}
}