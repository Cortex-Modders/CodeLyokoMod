package matt.lyoko.client;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;
import matt.lyoko.container.*;
import matt.lyoko.entities.*;
import matt.lyoko.entities.tileentity.*;

public class GuiHandler implements IGuiHandler {
	//returns an instance of the Container you made earlier
	@Override
	public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z)
	{
		TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
		
		if(tileEntity instanceof TileEntitySuperCalc)
		{
			return new ContainerSuperCalc(player.inventory, (TileEntitySuperCalc) tileEntity);
		}
		else if(tileEntity instanceof TileEntityTowerConsole)
		{
			return new ContainerTowerConsole(player.inventory, (TileEntityTowerConsole) tileEntity);
		}
		else if(tileEntity instanceof TileEntitySuperCalcConsole)
		{
			return new ContainerSuperCalcConsole(player.inventory, (TileEntitySuperCalcConsole) tileEntity);
		}
		
		return null;
	}
	
	//returns an instance of the Gui you made earlier
	@Override
	public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z)
	{
		TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
		
		if(tileEntity instanceof TileEntitySuperCalc)
		{
			return new GuiSuperCalc(player.inventory, (TileEntitySuperCalc) tileEntity);
		}
		else if(tileEntity instanceof TileEntityTowerConsole)
		{
			return new GuiTowerConsole(player.inventory, (TileEntityTowerConsole) tileEntity);
		}
		else if(tileEntity instanceof TileEntitySuperCalcConsole)
		{
			return new GuiSuperCalcConsole(player.inventory, (TileEntitySuperCalcConsole) tileEntity);
		}
		
		return null;
	}
}