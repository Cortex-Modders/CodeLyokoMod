package matt.lyoko.container;

import matt.lyoko.entities.tileentity.TileEntityTowerConsole;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;

public class ContainerTowerConsole extends Container
{
	protected TileEntityTowerConsole tileEntity;
	
	public ContainerTowerConsole(InventoryPlayer invPlayer, TileEntityTowerConsole te)
	{
		tileEntity = te;
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer player)
	{
		return true;
	}
}