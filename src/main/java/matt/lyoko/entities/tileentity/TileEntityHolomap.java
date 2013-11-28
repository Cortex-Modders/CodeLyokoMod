/*
 * Code Lyoko Mod for Minecraft v@VERSION
 * Copyright 2013 Cortex Modders, Matthew Warren, Jacob Rhoda, and
 * other contributors.
 * Released under the MIT license
 * http://opensource.org/licenses/MIT
 */

package matt.lyoko.entities.tileentity;

import matt.lyoko.blocks.BlockHolomap;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;

public class TileEntityHolomap extends TileEntity
{
    public byte sector = -1;

    @Override
    public void updateEntity()
    {
        if (this.sector != -1)
            for (int i = -1; i < 2; i++)
                for (int j = -1; j < 2; j++)
                    if (i != 0 || j != 0)
                        if (BlockHolomap.isMultiBlock(this.worldObj, this.xCoord + i, this.yCoord, this.zCoord + j) && !this.worldObj.isRemote)
                        {
                            TileEntityHolomap core = (TileEntityHolomap) this.worldObj.getBlockTileEntity(this.xCoord + i, this.yCoord, this.zCoord + j);
                            if (this.sector != core.sector)
                                core.sector = this.sector;
                            this.sector = -1;
                        }

        if (BlockHolomap.isMultiBlock(this.worldObj, this.xCoord, this.yCoord, this.zCoord) && !this.worldObj.isRemote)
        {
            // System.out.println(getBlockMetadata());
            byte meta = (byte) this.worldObj.getBlockMetadata(this.xCoord, this.yCoord, this.zCoord);
            if ((meta & 8) == 8)
            {
                byte metaSector = (byte) (meta & 7);
                if (metaSector != this.sector)
                {
                    metaSector = this.sector;
                    meta &= ~7;
                    meta |= metaSector;
                    this.worldObj.setBlockMetadataWithNotify(this.xCoord, this.yCoord, this.zCoord, meta, 3);
                }
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
        this.sector = tagCompound.getByte("sector");
    }

    @Override
    public void writeToNBT(NBTTagCompound tagCompound)
    {
        super.writeToNBT(tagCompound);
        tagCompound.setByte("sector", this.sector);
    }

    @Override
    public AxisAlignedBB getRenderBoundingBox()
    {
        return TileEntity.INFINITE_EXTENT_AABB;
    }
}