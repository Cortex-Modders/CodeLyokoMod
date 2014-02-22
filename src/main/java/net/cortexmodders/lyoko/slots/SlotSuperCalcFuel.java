/*
 * Code Lyoko Mod for Minecraft ${version}
 * Copyright 2014 Cortex Modders, Matthew Warren, Jacob Rhoda, and other contributors.
 * Released under the MIT license http://opensource.org/licenses/MIT
 */

package net.cortexmodders.lyoko.slots;

import net.cortexmodders.lyoko.container.ContainerSuperCalc;
import net.cortexmodders.lyoko.items.ItemLyokoFuel;
import net.cortexmodders.lyoko.items.ModItems;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotSuperCalcFuel extends Slot
{
    /** The container this slot belongs to. */
    final ContainerSuperCalc supercalc;

    public SlotSuperCalcFuel(ContainerSuperCalc par1ContainerSuperCalc, IInventory par2IInventory, int par3, int par4, int par5)
    {
        super(par2IInventory, par3, par4, par5);
        this.supercalc = par1ContainerSuperCalc;
    }

    /**
     * Check if the stack is a valid item for this slot. Always true beside for
     * the armor slots.
     */
    @Override
    public boolean isItemValid(ItemStack stack)
    {
        if (stack.getItem() instanceof ItemLyokoFuel || stack.getItem() == ModItems.laserArrow)
            return true;
        return false;
    }

    /**
     * Returns the maximum stack size for a given slot (usually the same as
     * getInventoryStackLimit(), but 1 in the case
     * of armor slots)
     */
    @Override
    public int getSlotStackLimit()
    {
        return 1;
    }
}
