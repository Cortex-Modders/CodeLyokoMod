package matt.lyoko.entities;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet130UpdateSign;
import net.minecraft.tileentity.TileEntity;

public class TileEntityTowerConsole extends TileEntity
{
	public String owner = "";
}