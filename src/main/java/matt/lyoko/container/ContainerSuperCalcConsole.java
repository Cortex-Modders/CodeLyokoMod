/*
 * Code Lyoko Mod for Minecraft v@VERSION
 * Copyright 2013 Cortex Modders, Matthew Warren, Jacob Rhoda, and
 * other contributors.
 * Released under the MIT license
 * http://opensource.org/licenses/MIT
 */

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
        this.tileEntity = te;
    }

    @Override
    public boolean canInteractWith(EntityPlayer player)
    {
        return true;
    }
}
