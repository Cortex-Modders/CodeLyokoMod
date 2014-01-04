/*
 * Code Lyoko Mod for Minecraft v@VERSION
 * Copyright 2013 Cortex Modders, Matthew Warren, Jacob Rhoda, and
 * other contributors.
 * Released under the MIT license
 * http://opensource.org/licenses/MIT
 */

package matt.lyoko.entities.tileentity;

import matt.lyoko.blocks.LyokoTerrainTypes;
import matt.lyoko.blocks.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;

public class TileEntityMarabounta extends TileEntity
{
    public Block consumedBlock = Blocks.air;
    
    // xCoord = field_145851_c
    // yCoord = field_145848_d
    // zCoord = field_145849_e
    // worldObj = field_145850_b
    // getBlockTileEntity = func_147438_o
    // setBlockTileEntity = func_147455_a

    @Override
    			//updateEntity
    public void func_145845_h()
    {
        this.syncMarabounta(this.field_145851_c + 1, this.field_145848_d, this.field_145849_e);
        this.syncMarabounta(this.field_145851_c - 1, this.field_145848_d, this.field_145849_e);
        this.syncMarabounta(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e);
        this.syncMarabounta(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e);
        this.syncMarabounta(this.field_145851_c, this.field_145848_d, this.field_145849_e + 1);
        this.syncMarabounta(this.field_145851_c, this.field_145848_d, this.field_145849_e - 1);
    }

    public void syncMarabounta(int x, int y, int z)
    {
        if (this.field_145850_b.func_147439_a(x, y, z) == ModBlocks.Marabounta && this.field_145850_b.func_147438_o(x, y, z) != null && this.field_145850_b.getBlockMetadata(x, y, z) == 0)
            if (this.field_145850_b.getBlockMetadata(this.field_145851_c, this.field_145848_d, this.field_145849_e) == 1)
            {
                TileEntityMarabounta temp = (TileEntityMarabounta) this.field_145850_b.func_147438_o(x, y, z);
                this.field_145850_b.setBlockMetadataWithNotify(x, y, z, 1, 2);
                this.field_145850_b.func_147455_a(x, y, z, temp);
            }
    }

    @Override
    public Packet getDescriptionPacket()
    {
        NBTTagCompound tag = new NBTTagCompound();
        this.func_145841_b(tag);
        return new Packet132TileEntityData(this.field_145851_c, this.field_145848_d, this.field_145849_e, 0, tag);
    }

    @Override
    public void onDataPacket(INetworkManager net, Packet132TileEntityData pkt)
    {
        NBTTagCompound tag = pkt.data;
        this.func_145839_a(tag);
    }

    @Override
    			//readFromNBT
    public void func_145839_a(NBTTagCompound tagCompound)
    {
        super.func_145839_a(tagCompound);
        this.consumedBlock = tagCompound.getInteger("block");
    }

    @Override
    			//writeToNBT
    public void func_145841_b(NBTTagCompound tagCompound)
    {
        super.func_145841_b(tagCompound);
        tagCompound.setInteger("block", this.consumedBlock);
    }
}