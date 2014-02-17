/*
 * Code Lyoko Mod for Minecraft v@VERSION
 * Copyright 2013 Cortex Modders, Matthew Warren, Jacob Rhoda, and
 * other contributors.
 * Released under the MIT license
 * http://opensource.org/licenses/MIT
 */

package matt.lyoko.entities.tileentity;

import matt.lyoko.blocks.ModBlocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
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
        if (world.getBlock(x, y, z) == ModBlocks.Cable && world.getTileEntity(x, y, z) != null)
        {
            TileEntityCable cable = (TileEntityCable) world.getTileEntity(x, y, z);
            if (cable != null && cable.getCoolDown() == 0 && cable.getSector().equals(""))
            {
                cable.resetCoolDown();
                cable.setSector(this.sector + "scc");
                world.notifyBlocksOfNeighborChange(x, y, z, ModBlocks.Cable.blockID);
            }
        }
    }

    @Override
    public Packet getDescriptionPacket()
    {
        NBTTagCompound tag = new NBTTagCompound();
        this.writeToNBT(tag);
        return new Packet132TileEntityData(this.xCoord, this.yCoord, this.zCoord, 0, tag);
    }

    @Override
    public void onDataPacket(INetworkManager net, Packet132TileEntityData pkt)
    {
        NBTTagCompound tag = pkt.data;
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
