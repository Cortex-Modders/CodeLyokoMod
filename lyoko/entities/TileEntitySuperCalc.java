package matt.lyoko.entities;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.lang.reflect.Field;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.ChunkCoordIntPair;
import matt.lyoko.*;
import matt.lyoko.items.*;
import net.minecraftforge.common.*;
import net.minecraftforge.common.ForgeChunkManager.Ticket;
import net.minecraftforge.common.ForgeChunkManager.Type;

public class TileEntitySuperCalc extends TileEntity implements IInventory {

        private ItemStack[] inv;
        public String selectedSector;
        public float timeLeft;
        //private Ticket ticket;

        public TileEntitySuperCalc(){
                inv = new ItemStack[3];
                selectedSector = "";
                timeLeft = 20.0F;
                
                //ticket = ForgeChunkManager.requestTicket(CodeLyoko.instance, worldObj, Type.NORMAL);
                //ticket.getModData().setInteger("SuperCalcX", xCoord);
                //ticket.getModData().setInteger("SuperCalcY", yCoord);
                //ticket.getModData().setInteger("SuperCalcZ", zCoord);
                //ForgeChunkManager.forceChunk(ticket, new ChunkCoordIntPair(xCoord, zCoord));
                //System.out.println("chunk loaded");
        }
        
        public String getRemainingTime()
        {
        	if(getStackInSlot(0) != null && getStackInSlot(0).getItem() instanceof ItemLyokoFuel)
        	{
        		if(timeLeft <= 0.0F)
        		{
        			timeLeft = 0.0F;
        			if(selectedSector != "")
        			{
        				if(getStackInSlot(1) != null && getStackInSlot(1) == new ItemStack(CodeLyoko.PortalItem, 1, 6)
        				&& getStackInSlot(2) == null)
        				{
        					if(selectedSector == "ice")
        					{
        						setInventorySlotContents(1, null);
        						setInventorySlotContents(2, new ItemStack(CodeLyoko.PortalItem, 1, 0));
        					}
        					else if(selectedSector == "forest")
        					{
        						setInventorySlotContents(1, null);
        						setInventorySlotContents(2, new ItemStack(CodeLyoko.PortalItem, 1, 1));
        					}
        					else if(selectedSector == "mountain")
        					{
        						setInventorySlotContents(1, null);
        						setInventorySlotContents(2, new ItemStack(CodeLyoko.PortalItem, 1, 2));
        					}
        					else if(selectedSector == "desert")
        					{
        						setInventorySlotContents(1, null);
        						setInventorySlotContents(2, new ItemStack(CodeLyoko.PortalItem, 1, 3));
        					}
        					else if(selectedSector == "carthage")
        					{
        						setInventorySlotContents(1, null);
        						setInventorySlotContents(2, new ItemStack(CodeLyoko.PortalItem, 1, 4));
        					}
        				}
        				selectedSector = "";
        				timeLeft = 20.0F;
        			}
        			return Float.toString(0.0F);
        		}
        		if(selectedSector == "")
        		{
        			timeLeft = 20.0F;
        			return Float.toString(20.0F);
        		}
        		timeLeft = timeLeft - 0.05F;
        	}
        	
        	return Float.toString(timeLeft);
        }
        
        @Override
        public int getSizeInventory() {
                return inv.length;
        }

        @Override
        public ItemStack getStackInSlot(int slot) {
                return inv[slot];
        }
        
        @Override
        public void setInventorySlotContents(int slot, ItemStack stack) {
                inv[slot] = stack;
                if (stack != null && stack.stackSize > getInventoryStackLimit()) {
                        stack.stackSize = getInventoryStackLimit();
                }
        }
        
        @Override
        public ItemStack decrStackSize(int slot, int amt) {
                ItemStack stack = getStackInSlot(slot);
                if (stack != null)
                {
                	if (stack.stackSize <= amt)
                    {
                    	setInventorySlotContents(slot, null);
                    }
                    else
                    {
                    	stack = stack.splitStack(amt);
                    	if (stack.stackSize == 0)
                    	{
                    		setInventorySlotContents(slot, null);
                    	}
                    }
                }
                return stack;
        }
        
        public boolean canUpdate()
        {
            return true;
        }
        
        public void updateEntity()
        {
        	int slot = 0;
        	ItemStack stack = getStackInSlot(slot);
        	
        	if(stack != null && stack.getItemDamage() == stack.getMaxDamage())
        	{
        		if(stack.getItem() instanceof ItemLyokoFuel && stack.getItem() == CodeLyoko.LyokoLeadCell)
        		{
        			setInventorySlotContents(slot, new ItemStack(CodeLyoko.LyokoDepletedLeadCell));
        		}
        		else
        		{
        			setInventorySlotContents(slot, null);
        		}
        	}
        	else if(stack != null && stack.getItemDamage() < stack.getMaxDamage())
        	{
        		setInventorySlotContents(slot, new ItemStack(stack.getItem(), 1, stack.getItemDamage() + 1));
        	}
        }

        @Override
        public ItemStack getStackInSlotOnClosing(int slot) {
                ItemStack stack = getStackInSlot(slot);
                if (stack != null) {
                        setInventorySlotContents(slot, null);
                }
                return stack;
        }
        
        @Override
        public int getInventoryStackLimit() {
                return 64;
        }

        @Override
        public boolean isUseableByPlayer(EntityPlayer player) {
                return worldObj.getBlockTileEntity(xCoord, yCoord, zCoord) == this &&
                player.getDistanceSq(xCoord + 0.5, yCoord + 0.5, zCoord + 0.5) < 64;
        }

        @Override
        public void openChest() {}

        @Override
        public void closeChest() {}
        
        @Override
        public void readFromNBT(NBTTagCompound tagCompound) {
                super.readFromNBT(tagCompound);
                NBTTagList tagList = tagCompound.getTagList("Inventory");
                for (int i = 0; i < tagList.tagCount(); i++) {
                        NBTTagCompound tag = (NBTTagCompound) tagList.tagAt(i);
                        byte slot = tag.getByte("Slot");
                        if (slot >= 0 && slot < inv.length) {
                                inv[slot] = ItemStack.loadItemStackFromNBT(tag);
                        }
                }
                this.timeLeft = tagCompound.getFloat("remainingTime");
                this.selectedSector = tagCompound.getString("sectorSelection");
        }

        @Override
        public void writeToNBT(NBTTagCompound tagCompound) {
                super.writeToNBT(tagCompound);
                tagCompound.setString("sectorSelection", this.selectedSector);
                tagCompound.setFloat("remainingTime", this.timeLeft);
                NBTTagList itemList = new NBTTagList();
                for (int i = 0; i < inv.length; i++) {
                        ItemStack stack = inv[i];
                        if (stack != null) {
                                NBTTagCompound tag = new NBTTagCompound();
                                tag.setByte("Slot", (byte) i);
                                stack.writeToNBT(tag);
                                itemList.appendTag(tag);
                        }
                }
                tagCompound.setTag("Inventory", itemList);
        }
        
        @Override
        public String getInvName()
        {
        	return "tileentitysupercalc";
        }
}