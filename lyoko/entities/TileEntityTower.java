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
		int shouldToggle;
		if(!isActivated)
		{
			shouldToggle = rand.nextInt(10000);
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
				shouldToggle = rand.nextInt(10000);
				if(shouldToggle == 0)
				{
					isActivated = false;
					owner = "none";
				}
			}
		}
		
		if(this.getBlockType() == CodeLyoko.TowerBlock)
		{
			//BlockLyokoTower blt = new BlockLyokoTower(CodeLyoko.TowerBlock.blockID, CodeLyoko.TowerBlock.blockIndexInTexture);
			if(isActivated)
			{
				if(owner == "xana")
				{
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

		}
		System.out.println(owner);
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
