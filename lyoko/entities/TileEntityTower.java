package matt.lyoko.entities;

import java.util.Random;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import matt.lyoko.*;

public class TileEntityTower extends TileEntity
{

	public boolean isActivated = false;
	public String owner = "none";

	public void updateEntity()
	{
		Random rand = new Random();
		int shouldToggle = rand.nextInt(2000);
		if(!isActivated)
		{
			if(shouldToggle == 0)
			{
				isActivated = true;
				owner = "xana";
			}
		}
		
		if(isActivated)
		{
			if(owner == "xana")
			{
				if(shouldToggle == 1)
				{
					isActivated = false;
					owner = "none";
				}
				//blt.activeXana(this.worldObj, this.xCoord, this.yCoord, this.zCoord);
			}
			else if(owner == "lyoko")
			{
				//display green particle effect
			}
			else if(owner == "franz")
			{
				//display white particle effect
			}
		}
		else if(!isActivated)
		{
			if(owner == "none")
			{
				//display blue particle effect
			}
		}
		System.out.println(owner + " at " + this.xCoord + " "+ this.yCoord + " "+ this.zCoord);
	}

	@Override
	public void readFromNBT(NBTTagCompound tagCompound) {
		super.readFromNBT(tagCompound);
		this.isActivated = tagCompound.getBoolean("isActive");
		this.owner = tagCompound.getString("towerOwner");
	}

	@Override
	public void writeToNBT(NBTTagCompound tagCompound) {
		super.writeToNBT(tagCompound);
		tagCompound.setBoolean("isActive", this.isActivated);
		tagCompound.setString("towerOwner", this.owner);
	}
}
