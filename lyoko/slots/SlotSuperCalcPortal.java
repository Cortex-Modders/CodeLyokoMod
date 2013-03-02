package matt.lyoko.slots;

import cpw.mods.fml.relauncher.*;
import matt.lyoko.CodeLyoko;
import matt.lyoko.container.ContainerSuperCalc;
import matt.lyoko.items.*;
import net.minecraft.block.Block;
import net.minecraft.inventory.*;
import net.minecraft.item.*;

public class SlotSuperCalcPortal extends Slot
{
    /**
     * The parent class of this clot, ContainerPlayer, SlotArmor is a Anon inner class.
     */
    final ContainerSuperCalc parent;

    public SlotSuperCalcPortal(ContainerSuperCalc par1ContainerPlayer, IInventory par2IInventory, int par3, int par4, int par5)
    {
        super(par2IInventory, par3, par4, par5);
        this.parent = par1ContainerPlayer;
    }

    /**
     * Returns the maximum stack size for a given slot (usually the same as getInventoryStackLimit(), but 1 in the case
     * of armor slots)
     */
    public int getSlotStackLimit()
    {
        return 1;
    }

    /**
     * Check if the stack is a valid item for this slot. Always true beside for the armor slots.
     */
    public boolean isItemValid(ItemStack stack)
    {
        return stack.getItem() instanceof ItemPortal && stack.getItemDamage() != 6;
    }
}
