package matt.lyoko.entities.tileentity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import matt.lyoko.CodeLyoko;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.*;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class TileEntitySuperCalcConsole extends TileEntity
{
	public String sector = "";
	
	@Override
	public void updateEntity()
	{
		if(!sector.equals(""))
		{
			syncCable(worldObj, xCoord + 1, yCoord, zCoord);
			syncCable(worldObj, xCoord - 1, yCoord, zCoord);
			syncCable(worldObj, xCoord, yCoord + 1, zCoord);
			syncCable(worldObj, xCoord, yCoord - 1, zCoord);
			syncCable(worldObj, xCoord, yCoord, zCoord + 1);
			syncCable(worldObj, xCoord, yCoord, zCoord - 1);
			sector = "";
		}
	}
	
	public void syncCable(World world, int x, int y, int z)
	{
		if(world.getBlockId(x, y, z) == CodeLyoko.Cable.blockID && world.getBlockTileEntity(x, y, z) != null)
		{
			TileEntityCable cable = (TileEntityCable)world.getBlockTileEntity(x, y, z);
			if(cable != null && cable.getCoolDown() == 0 && cable.getSector().equals(""))
			{
				cable.resetCoolDown();
				cable.setSector(sector + "scc");
				world.notifyBlocksOfNeighborChange(x, y, z, CodeLyoko.Cable.blockID);
			}
		}
	}
	
	@Override
	public Packet getDescriptionPacket()
	{
		NBTTagCompound tag = new NBTTagCompound();
		this.writeToNBT(tag);
		return new Packet132TileEntityData(xCoord, yCoord, zCoord, 0, tag);
	}
	
	@Override
	public void onDataPacket(INetworkManager net, Packet132TileEntityData pkt)
	{
		NBTTagCompound tag = pkt.customParam1;
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