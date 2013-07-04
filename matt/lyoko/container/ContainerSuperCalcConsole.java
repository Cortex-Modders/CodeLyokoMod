package matt.lyoko.container;

import net.minecraft.entity.player.*;
import net.minecraft.inventory.*;
import net.minecraft.item.*;
import matt.lyoko.entities.*;
import matt.lyoko.entities.tileentity.TileEntitySuperCalcConsole;
import matt.lyoko.slots.*;

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