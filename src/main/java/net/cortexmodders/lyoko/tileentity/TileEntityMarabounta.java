/*
 * Code Lyoko Mod for Minecraft ${version}
 * Copyright 2014 Cortex Modders, Matthew Warren, Jacob Rhoda, and other contributors.
 * Released under the MIT license http://opensource.org/licenses/MIT
 */

package net.cortexmodders.lyoko.tileentity;

import net.cortexmodders.lyoko.blocks.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.GameRegistry.UniqueIdentifier;

public class TileEntityMarabounta extends TileEntity
{
    public Block consumedBlock = Blocks.air;
    
    // xCoord = xCoord
    // yCoord = yCoord
    // zCoord = zCoord
    // worldObj = worldObj
    // getBlockTileEntity = getTileEntity
    // setBlockTileEntity = setTileEntity

    @Override
    			//updateEntity
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
        if (this.worldObj.getBlock(x, y, z) == ModBlocks.marabounta && this.worldObj.getTileEntity(x, y, z) != null && this.worldObj.getBlockMetadata(x, y, z) == 0)
            if (this.worldObj.getBlockMetadata(this.xCoord, this.yCoord, this.zCoord) == 1)
            {
                TileEntityMarabounta temp = (TileEntityMarabounta) this.worldObj.getTileEntity(x, y, z);
                this.worldObj.setBlockMetadataWithNotify(x, y, z, 1, 2);
                this.worldObj.setTileEntity(x, y, z, temp);
            }
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
    //readFromNBT
    public void readFromNBT(NBTTagCompound tagCompound)
    {
        super.readFromNBT(tagCompound);
        String[] block = tagCompound.getString("blockString").split(":");
        if(block.length > 0 && block.length <= 2)
            this.consumedBlock = GameRegistry.findBlock(block[0], block[1]);
    }

    @Override
    //writeToNBT
    public void writeToNBT(NBTTagCompound tagCompound)
    {
        super.writeToNBT(tagCompound);
        UniqueIdentifier block = GameRegistry.findUniqueIdentifierFor(this.consumedBlock);
        tagCompound.setString("blockString", String.format("%s:%s", block.modId, block.name));
    }
}
