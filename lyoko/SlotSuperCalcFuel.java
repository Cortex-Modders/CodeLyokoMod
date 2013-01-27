package matt.lyoko;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

class SlotSuperCalcFuel extends Slot
{
    /** The beacon this slot belongs to. */
    final ContainerSuperCalc supercalc;

    public SlotSuperCalcFuel(ContainerSuperCalc par1ContainerSuperCalc, IInventory par2IInventory, int par3, int par4, int par5)
    {
        super(par2IInventory, par3, par4, par5);
        this.supercalc = par1ContainerSuperCalc;
    }

    /**
     * Check if the stack is a valid item for this slot. Always true beside for the armor slots.
     */
    public boolean isItemValid(ItemStack par1ItemStack)
    {
    	if(par1ItemStack.getItem() instanceof ItemLyokoFuel)
    	{
    		return true;
    	}
    	else
    	{
    		return false;
    	}
        //return par1ItemStack == null ? false : par1ItemStack.itemID == CodeLyoko.LyokoLeadCell.itemID || par1ItemStack.itemID == CodeLyoko.LyokoDepletedLeadCell.itemID;
    }

    /**
     * Returns the maximum stack size for a given slot (usually the same as getInventoryStackLimit(), but 1 in the case
     * of armor slots)
     */
    public int getSlotStackLimit()
    {
        return 1;
    }
}
