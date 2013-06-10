package matt.lyoko.entities.tileentity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import matt.lyoko.CodeLyoko;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.*;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class TileEntityTowerConsole extends TileEntity
{
	public String owner = "";
	private TileEntityTower[][][] tetArray = new TileEntityTower[9][9][9];
	
	@Override
	public void updateEntity()
	{
		for(int x = -4; x < 5; x++)
		{
			for(int y = -4; y < 5; y++)
			{
				for(int z = -4; z < 5; z++)
				{
					if(worldObj.getBlockTileEntity(x+xCoord, y+yCoord, z+zCoord) instanceof TileEntityTower)
					{
						tetArray[x+4][y+4][z+4] = (TileEntityTower) worldObj.getBlockTileEntity(x+xCoord, y+yCoord, z+zCoord);
					}
				}
			}
		}
		
		if(!owner.equals(""))
		{
			if(owner.equals("reset"))
			{
				owner = "not valid";
			}
			else if(owner.equals("lyoko"))
			{
				owner = "reset";
			}
			else if(owner.equals("player"))
			{
				owner = "lyoko";
			}
			else if(owner.equals("developer") && worldObj.isRemote)
			{
				for(int i = 0; i < worldObj.playerEntities.size(); i++)
				{
					EntityPlayer player = (EntityPlayer) worldObj.playerEntities.get(i);
					player.sendChatToPlayer("A tower has been activated at: " + xCoord + ", " + yCoord + ", " + zCoord + ", in dimension: " + worldObj.provider.dimensionId);
    				player.sendChatToPlayer("Automatic deactivation will occur in 10 minutes");
				}
			}
			for(int i = 0; i < TileEntityTower.getPossibleOwners().length; i++)
			{
				if(owner.equals(TileEntityTower.getPossibleOwners()[i]))
				{
					for(int x = 0; x < 9; x++)
					{
						for(int y = 0; y < 9; y++)
						{
							for(int z = 0; z < 9; z++)
							{
								if(tetArray[x][y][z] != null && !tetArray[x][y][z].owner.equals(owner))
								{
									tetArray[x][y][z].owner = owner;
									tetArray[x][y][z].worldObj.markBlockForUpdate(tetArray[x][y][z].xCoord, tetArray[x][y][z].yCoord, tetArray[x][y][z].zCoord);
								}
							}
						}
					}
					owner = "";
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