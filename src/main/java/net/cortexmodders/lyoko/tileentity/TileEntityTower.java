/*
 * Code Lyoko Mod for Minecraft ${version}
 * Copyright 2014 Cortex Modders, Matthew Warren, Jacob Rhoda, and other contributors.
 * Released under the MIT license http://opensource.org/licenses/MIT
 */

package net.cortexmodders.lyoko.tileentity;

import net.cortexmodders.lyoko.blocks.ModBlocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityTower extends TileEntity
{
    public String owner = "none";
    private static String[] possibleOwners = { "none", "developer", "xana", "lyoko", "reset" };
    private int resetTicks = 12000;
    
    @Override
    public void updateEntity()
    {
        if (this.owner.equals("reset"))
        {
            this.owner = "none";
            this.syncTower(this.xCoord + 1, this.yCoord, this.zCoord, "reset");
            this.syncTower(this.xCoord - 1, this.yCoord, this.zCoord, "reset");
            this.syncTower(this.xCoord, this.yCoord + 1, this.zCoord, "reset");
            this.syncTower(this.xCoord, this.yCoord - 1, this.zCoord, "reset");
            this.syncTower(this.xCoord, this.yCoord, this.zCoord + 1, "reset");
            this.syncTower(this.xCoord, this.yCoord, this.zCoord - 1, "reset");
            this.resetTicks = 12000;
        }
        else if (this.owner.equals("developer"))
        {
            this.syncTower(this.xCoord + 1, this.yCoord, this.zCoord, "developer");
            this.syncTower(this.xCoord - 1, this.yCoord, this.zCoord, "developer");
            this.syncTower(this.xCoord, this.yCoord + 1, this.zCoord, "developer");
            this.syncTower(this.xCoord, this.yCoord - 1, this.zCoord, "developer");
            this.syncTower(this.xCoord, this.yCoord, this.zCoord + 1, "developer");
            this.syncTower(this.xCoord, this.yCoord, this.zCoord - 1, "developer");
            this.resetTicks--;
        }
        else if (this.owner.equals("xana"))
        {
            this.syncTower(this.xCoord + 1, this.yCoord, this.zCoord, "xana");
            this.syncTower(this.xCoord - 1, this.yCoord, this.zCoord, "xana");
            this.syncTower(this.xCoord, this.yCoord + 1, this.zCoord, "xana");
            this.syncTower(this.xCoord, this.yCoord - 1, this.zCoord, "xana");
            this.syncTower(this.xCoord, this.yCoord, this.zCoord + 1, "xana");
            this.syncTower(this.xCoord, this.yCoord, this.zCoord - 1, "xana");
        }
        else if (this.owner.equals("lyoko"))
        {
            this.syncTower(this.xCoord + 1, this.yCoord, this.zCoord, "lyoko");
            this.syncTower(this.xCoord - 1, this.yCoord, this.zCoord, "lyoko");
            this.syncTower(this.xCoord, this.yCoord + 1, this.zCoord, "lyoko");
            this.syncTower(this.xCoord, this.yCoord - 1, this.zCoord, "lyoko");
            this.syncTower(this.xCoord, this.yCoord, this.zCoord + 1, "lyoko");
            this.syncTower(this.xCoord, this.yCoord, this.zCoord - 1, "lyoko");
        }
        else if (this.owner.equals("none"))
        {
            this.syncTower(this.xCoord + 1, this.yCoord, this.zCoord, "none");
            this.syncTower(this.xCoord - 1, this.yCoord, this.zCoord, "none");
            this.syncTower(this.xCoord, this.yCoord + 1, this.zCoord, "none");
            this.syncTower(this.xCoord, this.yCoord - 1, this.zCoord, "none");
            this.syncTower(this.xCoord, this.yCoord, this.zCoord + 1, "none");
            this.syncTower(this.xCoord, this.yCoord, this.zCoord - 1, "none");
        }
        
        if (this.resetTicks <= 0)
        {
            this.owner = "reset";
            this.resetTicks = 12000;
        }
        
    }
    
    public static String[] getPossibleOwners()
    {
        return possibleOwners;
    }
    
    public void syncTower(int x, int y, int z, String newOwner)
    {
        if ((this.worldObj.getBlock(x, y, z) == ModBlocks.towerBlock || this.worldObj.getBlock(x, y, z) == ModBlocks.towerBase) && this.worldObj.getTileEntity(x, y, z) != null && ((TileEntityTower) this.worldObj.getTileEntity(x, y, z)).owner != newOwner && this.ownerValue(newOwner) > this.ownerValue(((TileEntityTower) this.worldObj.getTileEntity(x, y, z)).owner))
        {
            if (!newOwner.equals("reset") || !((TileEntityTower) this.worldObj.getTileEntity(x, y, z)).owner.equals("none"))
                ((TileEntityTower) this.worldObj.getTileEntity(x, y, z)).owner = newOwner;
        }
        else if ((this.worldObj.getBlock(x, y, z) == ModBlocks.towerBlock || this.worldObj.getBlock(x, y, z) == ModBlocks.towerBase) && this.worldObj.getTileEntity(x, y, z) != null && ((TileEntityTower) this.worldObj.getTileEntity(x, y, z)).owner != newOwner && newOwner.equals("none") && ((TileEntityTower) this.worldObj.getTileEntity(x, y, z)).owner.equals("reset"))
            ((TileEntityTower) this.worldObj.getTileEntity(x, y, z)).owner = newOwner;
    }
    
    public int ownerValue(String newOwner)
    {
        if (newOwner == "reset")
            return 100;
        else if (newOwner == "developer")
            return 10;
        else if (newOwner == "xana")
            return 2;
        else if (newOwner == "lyoko")
            return 1;
        else if (newOwner == "none")
            return 0;
        return -1;
    }
    
    @Override
    public Packet getDescriptionPacket()
    {
        NBTTagCompound tag = new NBTTagCompound();
        this.writeToNBT(tag);
        return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 0, tag);
    }
    
    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt)
    {
        NBTTagCompound tag = pkt.func_148857_g();
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
