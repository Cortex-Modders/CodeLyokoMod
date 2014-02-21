/*
 * Code Lyoko Mod for Minecraft v@VERSION
 * Copyright 2013 Cortex Modders, Matthew Warren, Jacob Rhoda, and
 * other contributors.
 * Released under the MIT license
 * http://opensource.org/licenses/MIT
 */

package net.cortexmodders.lyoko.tileentity;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

import net.cortexmodders.lyoko.blocks.BlockScanner;
import net.cortexmodders.lyoko.lib.PlayerInformation;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Vec3;

public class TileEntityScanner extends TileEntity
{
    public int sector = -1;

    // Doors are open by default.
    public boolean doorsOpen = true;
    public float doorRotationYaw = 0;
    
    // Position for left door. Right door just inverts left door. Y sould never change, so it is not used.
    public float doorPosX = -9;
    public float doorPosZ = 0;

    // Constants.
    private final float closedScannerYaw = -90F;
    private final float openScannerYaw = 0F;

    private final float closedScannerX = -8F;
    private final float openScannerX = -9F;

    private final float closedScannerZ = -11F;
    private final float openScannerZ = 0F;

    @Override
    public void updateEntity()
    {
        if (this.sector != -1)
            if (BlockScanner.isMultiBlock(this.worldObj, this.xCoord, this.yCoord, this.zCoord))
            {
                int yOffset = BlockScanner.getPositionInMultiBlock(this.worldObj, this.xCoord, this.yCoord, this.zCoord);
                if (yOffset > 0)
                {
                    TileEntityScanner core = (TileEntityScanner) this.worldObj.getTileEntity(this.xCoord, this.yCoord - yOffset, this.zCoord);
                    if (this.sector != core.sector && core.sector == -1)
                        core.sector = this.sector;
                    this.sector = -1;
                }
            }

        // Open doors!
        // If doors are set to open, but have not rendered as fully open.
        if (this.doorsOpen & this.doorRotationYaw <= this.openScannerYaw)
        {
            this.doorRotationYaw += 5.75;
            if (this.doorRotationYaw > this.openScannerYaw)
                this.doorRotationYaw = this.openScannerYaw;

            if (this.doorPosZ < this.openScannerZ)
            {
                this.doorPosZ += 0.75;
                if (this.doorPosZ >= this.openScannerZ)
                {
                    this.doorPosZ = this.openScannerZ;
                    this.doorPosX = this.openScannerX;
                }
            }
        }
        // Close doors!
        else if (!this.doorsOpen & this.doorRotationYaw >= this.closedScannerYaw)
        {
            this.doorRotationYaw -= 5.75;
            if (this.doorRotationYaw < this.closedScannerYaw)
                this.doorRotationYaw = this.closedScannerYaw;

            if (this.doorPosZ > this.closedScannerZ)
            {
                this.doorPosZ -= 0.75;
                if (this.doorPosZ <= this.closedScannerZ)
                {
                    this.doorPosZ = this.closedScannerZ;
                    this.doorPosX = this.closedScannerX;
                }
            }
        }
    }

    /**
     * 
     * Toggles doors of this scanner
     * 
     * @return
     */
    public void toggleDoors()
    {
        this.doorsOpen = !this.doorsOpen;
    }

    public void toggleAllDoors()
    {
        Vec3[] array = BlockScanner.getBlockCoordsInMultiBlock(this.worldObj, this.xCoord, this.yCoord, this.zCoord);

        this.toggleDoors();

        for (Vec3 coords : array)
        {
            TileEntityScanner scanner = (TileEntityScanner) this.worldObj.getTileEntity((int) coords.xCoord, (int) coords.yCoord, (int) coords.zCoord);
            scanner.doorsOpen = this.doorsOpen;

            ByteArrayOutputStream bos = new ByteArrayOutputStream(13);
            DataOutputStream outputStream = new DataOutputStream(bos);
            try
            {
                outputStream.writeBoolean(this.doorsOpen);
                outputStream.writeInt((int) coords.xCoord);
                outputStream.writeInt((int) coords.yCoord);
                outputStream.writeInt((int) coords.zCoord);
            } catch (Exception ex)
            {
                ex.printStackTrace();
            }

//            Packet250CustomPayload packet = new Packet250CustomPayload();
//            packet.channel = "ScannerDoors";
//            packet.data = bos.toByteArray();
//            packet.length = bos.size();
//
//            PacketDispatcher.sendPacketToAllPlayers(packet);
        }
    }

    public void setPlayerDevirtYaw(PlayerInformation pi)
    {
        switch (this.worldObj.getBlockMetadata(this.xCoord, this.yCoord, this.zCoord))
        {
            case 0:
                pi.scannerYaw = 180;
                break;
            case 1:
                pi.scannerYaw = 270;
                break;
            case 2:
                pi.scannerYaw = 0;
                break;
            case 3:
                pi.scannerYaw = 90;
                break;
        }
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
        this.sector = tagCompound.getInteger("sector");
        this.doorsOpen = tagCompound.getBoolean("doorsOpen");
    }

    @Override
    public void writeToNBT(NBTTagCompound tagCompound)
    {
        super.writeToNBT(tagCompound);
        tagCompound.setInteger("sector", this.sector);
        tagCompound.setBoolean("doorsOpen", this.doorsOpen);
    }
}
