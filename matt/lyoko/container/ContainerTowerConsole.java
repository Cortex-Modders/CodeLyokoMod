package matt.lyoko.container;

import net.minecraft.entity.player.*;
import net.minecraft.inventory.*;
import net.minecraft.item.*;
import matt.lyoko.entities.*;
import matt.lyoko.entities.tileentity.TileEntityTowerConsole;
import matt.lyoko.slots.*;

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