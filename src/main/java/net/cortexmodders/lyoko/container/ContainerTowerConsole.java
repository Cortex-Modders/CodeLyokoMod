/*
 * Code Lyoko Mod for Minecraft ${version}
 * Copyright 2014 Cortex Modders, Matthew Warren, Jacob Rhoda, and other contributors.
 * Released under the MIT license http://opensource.org/licenses/MIT
 */

package net.cortexmodders.lyoko.container;

import net.cortexmodders.lyoko.tileentity.TileEntityTowerConsole;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;

public class ContainerTowerConsole extends Container
{
    protected TileEntityTowerConsole tileEntity;
    
    public ContainerTowerConsole(InventoryPlayer invPlayer, TileEntityTowerConsole te)
    {
        this.tileEntity = te;
    }
    
    @Override
    public boolean canInteractWith(EntityPlayer player)
    {
        return true;
    }
}
