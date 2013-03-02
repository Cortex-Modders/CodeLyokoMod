package matt.lyoko.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import matt.lyoko.entities.*;
import matt.lyoko.slots.*;

public class ContainerSuperCalc extends Container {

        protected TileEntitySuperCalc tileEntity;
        private SlotSuperCalcFuel superCalcSlot;

        public ContainerSuperCalc (InventoryPlayer inventoryPlayer, TileEntitySuperCalc te){
                tileEntity = te;
                
                //the Slot constructor takes the IInventory and the slot number in that it binds to
                //and the x-y coordinates it resides on-screen
                addSlotToContainer(new SlotSuperCalcFuel(this, tileEntity, 0, 58, 20));
                addSlotToContainer(new SlotSuperCalc(this, tileEntity, 1, 192, 45));
                addSlotToContainer(new SlotSuperCalcPortal(this, tileEntity, 2, 192, 80));

                //commonly used vanilla code that adds the player's inventory
                bindPlayerInventory(inventoryPlayer);
        }
        
        @Override
        public boolean canInteractWith(EntityPlayer player) {
                return tileEntity.isUseableByPlayer(player);
        }


        protected void bindPlayerInventory(InventoryPlayer inventoryPlayer) {
                for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 9; j++) {
                                addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9,
                                                8 + j * 18, 118 + i * 18));
                        }
                }

                for (int i = 0; i < 9; i++) {
                        addSlotToContainer(new Slot(inventoryPlayer, i, 8 + i * 18, 176));
                }
        }
        
        @Override
        public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2)
        {
        	/*
            ItemStack var3 = null;
            Slot var4 = (Slot)this.inventorySlots.get(par2);

            if (var4 != null && var4.getHasStack())
            {
                ItemStack var5 = var4.getStack();
                var3 = var5.copy();

                if (par2 == 0)
                {
                    if (!this.mergeItemStack(var5, 1, 37, true))
                    {
                        return null;
                    }

                    var4.onSlotChange(var5, var3);
                }
                else if (!this.superCalcSlot.getHasStack() && this.superCalcSlot.isItemValid(var5) && var5.stackSize == 1)
                {
                    if (!this.mergeItemStack(var5, 0, 1, false))
                    {
                        return null;
                    }
                }
                else if (par2 >= 1 && par2 < 28)
                {
                    if (!this.mergeItemStack(var5, 28, 37, false))
                    {
                        return null;
                    }
                }
                else if (par2 >= 28 && par2 < 37)
                {
                    if (!this.mergeItemStack(var5, 1, 28, false))
                    {
                        return null;
                    }
                }
                else if (!this.mergeItemStack(var5, 1, 37, false))
                {
                    return null;
                }

                if (var5.stackSize == 0)
                {
                    var4.putStack((ItemStack)null);
                }
                else
                {
                    var4.onSlotChanged();
                }

                if (var5.stackSize == var3.stackSize)
                {
                    return null;
                }
                //if(var3 != null && var3.getItemDamage() == -1)
                //{
                //	var3 = (new ItemStack(CodeLyoko.LyokoDepletedLeadCell));
                //}

                var4.onPickupFromSlot(par1EntityPlayer, var5);
            }
            
            return var3;
            */
        	return null;
        }
}