package matt.lyoko.container;

import net.minecraft.entity.player.*;
import net.minecraft.inventory.*;
import net.minecraft.item.*;
import matt.lyoko.entities.*;
import matt.lyoko.slots.*;

public class ContainerTowerConsole extends Container
{
	protected TileEntityTowerConsole tileEntity;
	
	public ContainerTowerConsole(InventoryPlayer inventoryPlayer, TileEntityTowerConsole te)
	{
		tileEntity = te;
		
		//commonly used vanilla code that adds the player's inventory
		bindPlayerInventory(inventoryPlayer);
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer player)
	{
		return true;
	}
	
	protected void bindPlayerInventory(InventoryPlayer inventoryPlayer) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++) {
				addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9, 8 + j * 18, 71 + i * 18));
			}
		}

		for (int i = 0; i < 9; i++) {
			addSlotToContainer(new Slot(inventoryPlayer, i, 8 + i * 18, 129));
		}
	}
}