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
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;

public class TileEntityHolomap extends TileEntity
{
    public byte sector = -1;

    @Override
    public void func_145845_h()
    {
        if (this.sector != -1)
            for (int i = -1; i < 2; i++)
                for (int j = -1; j < 2; j++)
                    if (i != 0 || j != 0)
                        if (BlockHolomap.isMultiBlock(this.field_145850_b, this.field_145851_c + i, this.field_145848_d, this.field_145849_e + j) && !this.field_145850_b.isRemote)
                        {
                            TileEntityHolomap core = (TileEntityHolomap) this.field_145850_b.func_147438_o(this.field_145851_c + i, this.field_145848_d, this.field_145849_e + j);
                            if (this.sector != core.sector)
                                core.sector = this.sector;
                            this.sector = -1;
                        }

        if (BlockHolomap.isMultiBlock(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e) && !this.field_145850_b.isRemote)
        {
            // System.out.println(getBlockMetadata());
            byte meta = (byte) this.field_145850_b.getBlockMetadata(this.field_145851_c, this.field_145848_d, this.field_145849_e);
            if ((meta & 8) == 8)
            {
                byte metaSector = (byte) (meta & 7);
                if (metaSector != this.sector)
                {
                    metaSector = this.sector;
                    meta &= ~7;
                    meta |= metaSector;
                    this.field_145850_b.setBlockMetadataWithNotify(this.field_145851_c, this.field_145848_d, this.field_145849_e, meta, 3);
                }
            }
        }
    }

//    @Override
//    public Packet getDescriptionPacket()
//    {
//        NBTTagCompound tag = new NBTTagCompound();
//        this.writeToNBT(tag);
//        return new Packet132TileEntityData(this.field_145851_c, this.field_145848_d, this.field_145849_e, 0, tag);
//    }
//
//    @Override
//    public void onDataPacket(INetworkManager net, Packet132TileEntityData pkt)
//    {
//        NBTTagCompound tag = pkt.data;
//        this.readFromNBT(tag);
//    }

    @Override
    public void func_145839_a(NBTTagCompound tagCompound)
    {
        super.func_145839_a(tagCompound);
        this.sector = tagCompound.getByte("sector");
    }

    @Override
    public void func_145841_b(NBTTagCompound tagCompound)
    {
        super.func_145841_b(tagCompound);
        tagCompound.setByte("sector", this.sector);
    }

    @Override
    public AxisAlignedBB getRenderBoundingBox()
    {
        return TileEntity.INFINITE_EXTENT_AABB;
    }
}