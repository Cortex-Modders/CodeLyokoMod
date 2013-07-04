package matt.lyoko.container;

import matt.lyoko.entities.tileentity.TileEntitySuperCalcConsole;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;

public class ContainerSuperCalcConsole extends Container
{
	protected TileEntitySuperCalcConsole tileEntity;
	
	public ContainerSuperCalcConsole(InventoryPlayer invPlayer, TileEntitySuperCalcConsole te)
	{
		tileEntity = te;
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer player)
	{
		return true;
	}
}