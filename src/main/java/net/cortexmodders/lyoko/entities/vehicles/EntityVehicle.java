/*
 * Code Lyoko Mod for Minecraft v@VERSION
 * Copyright 2013 Cortex Modders, Matthew Warren, Jacob Rhoda, and
 * other contributors.
 * Released under the MIT license
 * http://opensource.org/licenses/MIT
 */

package net.cortexmodders.lyoko.entities.vehicles;

import net.cortexmodders.lyoko.items.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public abstract class EntityVehicle extends Entity
{

    public float hoverSpeed = 0.0F;// 0.0625F; // 1/16 is the speed
    private Item droppedItem = ModItems.DataFragment;

    public EntityVehicle(World par1World)
    {
        super(par1World);
        this.preventEntitySpawning = true;
        this.ignoreFrustumCheck = true;
        // this.hoverSpeed = (float)(Math.random() * Math.PI * 2.0D);
    }

    public EntityVehicle(World par1World, double x, double y, double z)
    {
        this(par1World);
        this.setPosition(x, y + this.yOffset, z);
        this.motionX = 0.0D;
        this.motionY = 0.0D;
        this.motionZ = 0.0D;
        this.prevPosX = x;
        this.prevPosY = y;
        this.prevPosZ = z;
    }

    public void setDroppedItem(Item item)
    {
        this.droppedItem = item;
    }

    public Item getDropeedItem()
    {
        return this.droppedItem;
    }

    @Override
    protected void entityInit()
    {
        this.dataWatcher.addObject(17, new Integer(0));
        this.dataWatcher.addObject(18, new Integer(1));
        this.dataWatcher.addObject(19, new Integer(0));
    }

    @Override
    public void onUpdate()
    {

        // TEMPORARY
        if (this.posY > 100.0D)
            this.kill();
    }

    @Override
    public AxisAlignedBB getCollisionBox(Entity par1Entity)
    {
        return par1Entity.boundingBox;
    }

    @Override
    public AxisAlignedBB getBoundingBox()
    {
        return this.boundingBox;
    }

    @Override
    public boolean canBeCollidedWith()
    {
        return true;
    }

    @Override
    public boolean canBePushed()
    {
        return true;
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound par1)
    {
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound par1)
    {
    }

    @Override
    public boolean interactFirst(EntityPlayer player)
    {
        if (this.riddenByEntity != null && this.riddenByEntity instanceof EntityPlayer && this.riddenByEntity != player && !player.isSneaking())
            return true;
        else
        {
            if (!this.worldObj.isRemote)
                if (player.isSneaking())
                {
                    this.kill();
                    this.dropItem(this.droppedItem, 1);
                } else
                    player.mountEntity(this);
            return true;
        }
    }

    @Override
    public ItemStack getPickedResult(MovingObjectPosition target)
    {
        return new ItemStack(this.droppedItem);
    }
}
