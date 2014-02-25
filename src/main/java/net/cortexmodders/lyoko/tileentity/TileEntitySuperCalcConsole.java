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
import net.minecraft.world.World;

public class TileEntitySuperCalcConsole extends TileEntity
{
    public String sector = "";
    
    @Override
    public void updateEntity()
    {
        if (!this.sector.equals(""))
        {
            this.syncCable(this.worldObj, this.xCoord + 1, this.yCoord, this.zCoord);
            this.syncCable(this.worldObj, this.xCoord - 1, this.yCoord, this.zCoord);
            this.syncCable(this.worldObj, this.xCoord, this.yCoord + 1, this.zCoord);
            this.syncCable(this.worldObj, this.xCoord, this.yCoord - 1, this.zCoord);
            this.syncCable(this.worldObj, this.xCoord, this.yCoord, this.zCoord + 1);
            this.syncCable(this.worldObj, this.xCoord, this.yCoord, this.zCoord - 1);
            this.sector = "";
        }
    }
    
    public void syncCable(World world, int x, int y, int z)
    {
        if (world.getBlock(x, y, z) == ModBlocks.cable && world.getTileEntity(x, y, z) != null)
        {
            TileEntityCable cable = (TileEntityCable) world.getTileEntity(x, y, z);
            if (cable != null && cable.getCoolDown() == 0 && cable.getSector().equals(""))
            {
                cable.resetCoolDown();
                cable.setSector(this.sector + "scc");
                world.notifyBlocksOfNeighborChange(x, y, z, ModBlocks.cable);
            }
        }
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
        this.sector = tagCompound.getString("sector");
    }
    
    @Override
    public void writeToNBT(NBTTagCompound tagCompound)
    {
        super.writeToNBT(tagCompound);
        tagCompound.setString("sector", this.sector);
    }
}
