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

public class TileEntityMarabounta extends TileEntity
{
    public int consumedBlock = 0;

    @Override
    public void updateEntity()
    {
        this.syncMarabounta(this.xCoord + 1, this.yCoord, this.zCoord);
        this.syncMarabounta(this.xCoord - 1, this.yCoord, this.zCoord);
        this.syncMarabounta(this.xCoord, this.yCoord + 1, this.zCoord);
        this.syncMarabounta(this.xCoord, this.yCoord - 1, this.zCoord);
        this.syncMarabounta(this.xCoord, this.yCoord, this.zCoord + 1);
        this.syncMarabounta(this.xCoord, this.yCoord, this.zCoord - 1);
    }

    public void syncMarabounta(int x, int y, int z)
    {
        if (this.worldObj.getBlockId(x, y, z) == ModBlocks.Marabounta.blockID && this.worldObj.getBlockTileEntity(x, y, z) != null && this.worldObj.getBlockMetadata(x, y, z) == 0)
            if (this.worldObj.getBlockMetadata(this.xCoord, this.yCoord, this.zCoord) == 1)
            {
                TileEntityMarabounta temp = (TileEntityMarabounta) this.worldObj.getBlockTileEntity(x, y, z);
                this.worldObj.setBlockMetadataWithNotify(x, y, z, 1, 2);
                this.worldObj.setBlockTileEntity(x, y, z, temp);
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
        this.consumedBlock = tagCompound.getInteger("block");
    }

    @Override
    public void writeToNBT(NBTTagCompound tagCompound)
    {
        super.writeToNBT(tagCompound);
        tagCompound.setInteger("block", this.consumedBlock);
    }
}