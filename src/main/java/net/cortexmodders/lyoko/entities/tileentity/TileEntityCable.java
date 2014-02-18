/*
 * Code Lyoko Mod for Minecraft v@VERSION
 * Copyright 2013 Cortex Modders, Matthew Warren, Jacob Rhoda, and
 * other contributors.
 * Released under the MIT license
 * http://opensource.org/licenses/MIT
 */

package net.cortexmodders.lyoko.entities.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;

public class TileEntityCable extends TileEntity
{
    private String sector = "";
    private int coolDown = 0;

    @Override
    public void updateEntity()
    {
        if (this.coolDown > 0)
            this.coolDown--;
        else if (this.coolDown < 0)
            this.coolDown = 0;

        if (this.coolDown == 1)
            this.setSector("");
    }

    public void setSector(String sector)
    {
        this.sector = sector;
    }

    public void setCoolDown(int newCoolDown)
    {
        this.coolDown = newCoolDown;
    }

    public String getSector()
    {
        return this.sector;
    }

    public int getCoolDown()
    {
        return this.coolDown;
    }

    public void resetCoolDown()
    {
        this.coolDown = 5;
    }
    
    @Override
    public AxisAlignedBB getRenderBoundingBox()
    {
    	return INFINITE_EXTENT_AABB;
    }

//    @Override
//    public Packet getDescriptionPacket()
//    {
//        NBTTagCompound tag = new NBTTagCompound();
//        this.writeToNBT(tag);
//        return new Packet132TileEntityData(this.xCoord, this.yCoord, this.zCoord, 0, tag);
//    }
//
//    @Override
//    public void onDataPacket(INetworkManager net, Packet132TileEntityData pkt)
//    {
//        NBTTagCompound tag = pkt.data;
//        this.readFromNBT(tag);
//    }

    @Override
    public void readFromNBT(NBTTagCompound tagCompound)
    {
        super.readFromNBT(tagCompound);
        this.coolDown = tagCompound.getInteger("cool");
        this.sector = tagCompound.getString("sector");
    }

    @Override
    public void writeToNBT(NBTTagCompound tagCompound)
    {
        super.writeToNBT(tagCompound);
        tagCompound.setInteger("cool", this.coolDown);
        tagCompound.setString("sector", this.sector);
    }
}
