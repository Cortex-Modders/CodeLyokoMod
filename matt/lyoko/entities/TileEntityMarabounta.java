package matt.lyoko.entities;

import matt.lyoko.CodeLyoko;
import matt.lyoko.lib.BlockIds;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityMarabounta extends TileEntity
{
	public boolean shouldAttackPlayers = false;
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
				&& worldObj.getBlockTileEntity(x, y, z) instanceof TileEntityMarabounta)
		{
			if(shouldAttackPlayers)
			{
				((TileEntityMarabounta)worldObj.getBlockTileEntity(x, y, z)).shouldAttackPlayers = true;
			}
		}
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tagCompound)
	{
		super.readFromNBT(tagCompound);
		this.shouldAttackPlayers = tagCompound.getBoolean("attackPlayers");
		this.consumedBlock = tagCompound.getInteger("block");
	}

	@Override
	public void writeToNBT(NBTTagCompound tagCompound)
	{
		super.writeToNBT(tagCompound);
		tagCompound.setBoolean("attackPlayers", this.shouldAttackPlayers);
		tagCompound.setInteger("block", this.consumedBlock);
	}
}