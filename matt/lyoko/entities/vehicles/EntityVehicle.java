package matt.lyoko.entities.vehicles;

import matt.lyoko.items.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EntityVehicle extends Entity {

	protected String texture = "";
	
	private Item droppedItem = ModItems.DataFragment;
	
	public EntityVehicle(World par1World) {
		super(par1World);
		this.preventEntitySpawning = true;
		this.ignoreFrustumCheck = true;
	}
	
	public void setDroppedItem(Item item)
	{
		this.droppedItem = item;
	}

	@Override
	protected void entityInit()
	{
		this.dataWatcher.addObject(17, new Integer(0));
		this.dataWatcher.addObject(18, new Integer(1));
		this.dataWatcher.addObject(19, new Integer(0));
	}

	public void onUpdate() {
	
		super.onUpdate();
//		this.kill();
		
		// TEMPORARY
		if(this.posY>100.0D) this.kill();
	}

	public AxisAlignedBB getCollisionBox(Entity par1Entity)
	{
		return par1Entity.boundingBox;
	}

	public AxisAlignedBB getBoundingBox()
	{
		return this.boundingBox;
	}

	public boolean canBeCollidedWith() {
		return true;
	}

	public boolean canBePushed() {
		return true;
	}

	public EntityVehicle(World par1World, double x, double y, double z) {
		this(par1World);
		this.setPosition(x, y + (double)this.yOffset, z);
		this.motionX = 0.0D;
		this.motionY = 0.0D;
		this.motionZ = 0.0D;
		this.prevPosX = x;
		this.prevPosY = y;
		this.prevPosZ = z;
	}

	@SideOnly(Side.CLIENT)

	/**
	 * Returns the texture's file path as a String.
	 */
	public String getTexture()
	{
		return this.texture;
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound par1){}

	@Override
	public void writeEntityToNBT(NBTTagCompound par1) {}

	public boolean interact(EntityPlayer player)
	{
		if (this.riddenByEntity != null && this.riddenByEntity instanceof EntityPlayer && this.riddenByEntity != player && !player.isSneaking())
		{
			return true;
		}
		else
		{
			if (!this.worldObj.isRemote)
			{
				if(player.isSneaking()) {
					this.kill();
					this.dropItem(droppedItem.itemID, 1);
				}
				else {
					player.mountEntity(this);
					
				}
			}
			return true;
		}
	}
}
