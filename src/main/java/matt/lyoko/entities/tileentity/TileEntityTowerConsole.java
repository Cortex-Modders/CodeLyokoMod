/*
 * Code Lyoko Mod for Minecraft v@VERSION
 * Copyright 2013 Cortex Modders, Matthew Warren, Jacob Rhoda, and
 * other contributors.
 * Released under the MIT license
 * http://opensource.org/licenses/MIT
 */

package matt.lyoko.entities.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;

public class TileEntityTowerConsole extends TileEntity
{
    public String owner = "";
    private TileEntityTower[][][] tetArray = new TileEntityTower[9][9][9];

    @Override
    public void updateEntity()
    {
        for (int x = -4; x < 5; x++)
            for (int y = -4; y < 5; y++)
                for (int z = -4; z < 5; z++)
                    if (this.worldObj.getBlockTileEntity(x + this.xCoord, y + this.yCoord, z + this.zCoord) instanceof TileEntityTower)
                        this.tetArray[x + 4][y + 4][z + 4] = (TileEntityTower) this.worldObj.getBlockTileEntity(x + this.xCoord, y + this.yCoord, z + this.zCoord);

        if (!this.owner.equals(""))
        {
            if (this.owner.equals("reset"))
                this.owner = "not valid";
            else if (this.owner.equals("lyoko"))
                this.owner = "reset";
            else if (this.owner.equals("player"))
                this.owner = "lyoko";
            else if (this.owner.equals("developer") && this.worldObj.isRemote)
                for (int i = 0; i < this.worldObj.playerEntities.size(); i++)
                {
                    EntityPlayer player = (EntityPlayer) this.worldObj.playerEntities.get(i);
                    player.addChatMessage("A tower has been activated at: " + this.xCoord + ", " + this.yCoord + ", " + this.zCoord + ", in dimension: " + this.worldObj.provider.dimensionId);
                    player.addChatMessage("Automatic deactivation will occur in 10 minutes");
                }
            for (int i = 0; i < TileEntityTower.getPossibleOwners().length; i++)
                if (this.owner.equals(TileEntityTower.getPossibleOwners()[i]))
                {
                    for (int x = 0; x < 9; x++)
                        for (int y = 0; y < 9; y++)
                            for (int z = 0; z < 9; z++)
                                if (this.tetArray[x][y][z] != null && !this.tetArray[x][y][z].owner.equals(this.owner))
                                {
                                    this.tetArray[x][y][z].owner = this.owner;
                                    this.tetArray[x][y][z].getWorldObj().markBlockForUpdate(this.tetArray[x][y][z].xCoord, this.tetArray[x][y][z].yCoord, this.tetArray[x][y][z].zCoord);
                                }
                    this.owner = "";
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
        this.owner = tagCompound.getString("towerOwner");
    }

    @Override
    public void writeToNBT(NBTTagCompound tagCompound)
    {
        super.writeToNBT(tagCompound);
        tagCompound.setString("towerOwner", this.owner);
    }
}
