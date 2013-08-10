/*
 * Code Lyoko Mod for Minecraft v@VERSION
 *
 * Copyright 2013 Cortex Modders, Matthew Warren, Jacob Rhoda, and
 * other contributors.
 * Released under the MIT license
 * http://opensource.org/licenses/MIT
 * 
 */

package matt.lyoko.slots;

import matt.lyoko.container.ContainerSuperCalc;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotSuperCalc extends Slot
{
    /** The container this slot belongs to. */
    final ContainerSuperCalc supercalc;

    public SlotSuperCalc(ContainerSuperCalc par1ContainerSuperCalc, IInventory par2IInventory, int par3, int par4, int par5)
    {
        super(par2IInventory, par3, par4, par5);
        this.supercalc = par1ContainerSuperCalc;
    }
    
    public boolean isItemValid(ItemStack stack)
    {
    	return false;
    }
}
