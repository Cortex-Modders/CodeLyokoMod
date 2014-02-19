/*
 * Code Lyoko Mod for Minecraft v@VERSION
 * Copyright 2013 Cortex Modders, Matthew Warren, Jacob Rhoda, and
 * other contributors.
 * Released under the MIT license
 * http://opensource.org/licenses/MIT
 */

package net.cortexmodders.lyoko.entities.tileentity;

import net.cortexmodders.lyoko.blocks.BlockSuperCalc;
import net.cortexmodders.lyoko.blocks.ModBlocks;
import net.cortexmodders.lyoko.items.ItemLyokoFuel;
import net.cortexmodders.lyoko.items.ModItems;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidBase;
import net.minecraftforge.fluids.Fluid;

public class TileEntitySuperCalc extends TileEntity implements IInventory// ,
                                                                         // ISidedInventory
{
    private ItemStack[] inv;
    public float timeLeft;
    public String sector = "";
    public int flush = 20;
    private boolean isPowered;
    private float temperature;

    // private Ticket ticket;

    public TileEntitySuperCalc()
    {
        this.inv = new ItemStack[2];
        this.timeLeft = 100.0F;
        this.setPowered(false);
        this.temperature = 295.0F;

        // ticket = ForgeChunkManager.requestTicket(CodeLyoko.instance,
        // worldObj, Type.NORMAL);
        // ticket.getModData().setInteger("SuperCalcX", xCoord);
        // ticket.getModData().setInteger("SuperCalcY", yCoord);
        // ticket.getModData().setInteger("SuperCalcZ", zCoord);
        // ForgeChunkManager.forceChunk(ticket, new ChunkCoordIntPair(xCoord,
        // zCoord));
        // System.out.println("chunk loaded");
    }

    @Override
    public int getSizeInventory()
    {
        return this.inv.length;
    }

    @Override
    public ItemStack getStackInSlot(int slot)
    {
        return this.inv[slot];
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack stack)
    {
        this.inv[slot] = stack;
        if (stack != null && stack.stackSize > this.getInventoryStackLimit())
            stack.stackSize = this.getInventoryStackLimit();
    }

    @Override
    public ItemStack decrStackSize(int slot, int amt)
    {
        ItemStack stack = this.getStackInSlot(slot);
        if (stack != null)
            if (stack.stackSize <= amt)
                this.setInventorySlotContents(slot, null);
            else
            {
                stack = stack.splitStack(amt);
                if (stack.stackSize == 0)
                    this.setInventorySlotContents(slot, null);
            }
        return stack;
    }

    public void setPowered(boolean powered)
    {
        this.isPowered = powered;
    }

    public boolean isPowered()
    {
        return this.isPowered;
    }

    public float getTemperature()
    {
        return this.temperature;
    }

    public void resetSector(World world, int x, int y, int z)
    {
        ((TileEntitySuperCalc) world.getTileEntity(x + 1, y, z + 1)).sector = "";
        ((TileEntitySuperCalc) world.getTileEntity(x + 1, y, z)).sector = "";
        ((TileEntitySuperCalc) world.getTileEntity(x + 1, y, z - 1)).sector = "";
        ((TileEntitySuperCalc) world.getTileEntity(x, y, z + 1)).sector = "";
        ((TileEntitySuperCalc) world.getTileEntity(x, y, z)).sector = "";
        ((TileEntitySuperCalc) world.getTileEntity(x, y, z - 1)).sector = "";
        ((TileEntitySuperCalc) world.getTileEntity(x - 1, y, z + 1)).sector = "";
        ((TileEntitySuperCalc) world.getTileEntity(x - 1, y, z)).sector = "";
        ((TileEntitySuperCalc) world.getTileEntity(x - 1, y, z - 1)).sector = "";
        ((TileEntitySuperCalc) world.getTileEntity(x, y + 1, z)).sector = "";
        ((TileEntitySuperCalc) world.getTileEntity(x, y + 2, z)).sector = "";
    }

    public void syncCable(World world, int x, int y, int z)
    {
        if (world.getBlock(x, y, z) == ModBlocks.Cable && world.getTileEntity(x, y, z) != null)
        {
            TileEntityCable cable = (TileEntityCable) world.getTileEntity(x, y, z);
            if (cable != null && cable.getCoolDown() == 0 && cable.getSector().equals(""))
            {
                cable.resetCoolDown();
                cable.setSector(this.sector.substring(0, this.sector.length() - 3));
                world.notifyBlocksOfNeighborChange(x, y, z, ModBlocks.Cable);
            }
        }
    }

    public float getCoolant(World world, int x, int y, int z)
    {
        float coolant = 0.0F;
        for (int i = -1; i < 2; i++)
            for (int j = -1; j < 2; j++)
            {
                Block block = world.getBlock(x + i, y, z + j);
                if (block == Block.getBlockFromName("flowing_water") || block == Block.getBlockFromName("water"))
                    coolant += 0.2F;
                else if (block == Block.getBlockFromName("flowing_lava") || block == Block.getBlockFromName("lava"))
                    coolant -= 0.2F;
                else if (block != null && block instanceof BlockFluidBase)
                {
                    BlockFluidBase liquid = (BlockFluidBase) block;
                    Fluid fluid = liquid.getFluid();
                    if (fluid != null)
                    {
                        float fluidTemp = fluid.getTemperature(world, x + i, y, z + j);
                        if (fluidTemp < this.getTemperature())
                            coolant += (this.getTemperature() - fluidTemp) / 10;
                        else
                            coolant -= (fluidTemp - this.getTemperature()) / 10;
                    }
                }
            }
        return coolant;
    }

    @Override
    public void updateEntity()
    {
        if (!this.sector.equals("") && this.isPowered())
        {
            this.syncCable(this.worldObj, this.xCoord + 1, this.yCoord, this.zCoord);
            this.syncCable(this.worldObj, this.xCoord - 1, this.yCoord, this.zCoord);
            this.syncCable(this.worldObj, this.xCoord, this.yCoord + 1, this.zCoord);
            this.syncCable(this.worldObj, this.xCoord, this.yCoord - 1, this.zCoord);
            this.syncCable(this.worldObj, this.xCoord, this.yCoord, this.zCoord + 1);
            this.syncCable(this.worldObj, this.xCoord, this.yCoord, this.zCoord - 1);
        }

        if (BlockSuperCalc.isMultiBlock(this.worldObj, this.xCoord, this.yCoord, this.zCoord))
        {
            if (this.flush > 0)
                this.flush--;
            else if (this.flush < 0)
                this.flush = 0;
            else if (this.flush == 0)
            {
                this.resetSector(this.worldObj, this.xCoord, this.yCoord, this.zCoord);
                this.flush = 20;
                if (this.isPowered())
                    this.temperature += 1.0F;
                this.temperature -= this.getCoolant(this.worldObj, this.xCoord, this.yCoord - 1, this.zCoord);
                this.temperature = (int) ((this.temperature + 0.1F) * 5) / 5.0F;
                // 5933.15 is the boiling point of tungsten (aka highest known
                // boiling point)
                // ^^^ this is only used as a reference ^^^
                // if(temperature >= 5933.15F)
                if (this.temperature >= 2000.0F && !this.worldObj.isRemote)
                    this.worldObj.newExplosion(null, this.xCoord, this.yCoord, this.zCoord, 20, true, true);
                if (this.temperature < 0.0F)
                    this.temperature = 0.0F;
            }

            for (int i = -1; i < 2; i++)
                for (int k = -1; k < 2; k++)
                    for (int j = 0; j < 3; j++)
                        if (i != 0 || j != 0 || k != 0)
                            if (this.worldObj.getTileEntity(this.xCoord + i, this.yCoord + j, this.zCoord + k) instanceof TileEntitySuperCalc)
                            {
                                TileEntitySuperCalc slave = (TileEntitySuperCalc) this.worldObj.getTileEntity(this.xCoord + i, this.yCoord + j, this.zCoord + k);
                                {
                                    if (slave != null)
                                    {
                                        if (!slave.sector.equals(""))
                                        {
                                            if (this.sector.equals(""))
                                                this.sector = slave.sector;
                                            slave.sector = "";
                                        }
                                        if (slave.isPowered() != this.isPowered())
                                            slave.setPowered(this.isPowered());
                                    }

                                    if (slave != null && !this.sector.equals(""))
                                        slave.sector = this.sector;
                                }
                            }
        }

        int slot = 0;
        int slot2 = 1;

        ItemStack stack = this.getStackInSlot(slot);
        ItemStack stack2 = this.getStackInSlot(slot2);

        if (stack != null && stack.getItem() == ModItems.LaserArrow)
        {
            this.setInventorySlotContents(slot2, new ItemStack(ModItems.DataFragment, 64));
            this.setPowered(true);
        } else if (stack != null && stack.getItemDamage() == stack.getMaxDamage())
        {
            if (stack.getItem() instanceof ItemLyokoFuel && stack.getItem() == ModItems.LeadCell)
                this.setInventorySlotContents(slot, new ItemStack(ModItems.DepletedLeadCell));
            else if (stack.getItem() instanceof ItemLyokoFuel && stack.getItem() == ModItems.UraniumCell)
                this.setInventorySlotContents(slot, new ItemStack(ModItems.DepletedUraniumCell));
            this.setPowered(false);
        } else if (stack != null && stack.getItemDamage() < stack.getMaxDamage() && (stack2 != null && stack2.stackSize < 64 || stack2 == null))
        {
            this.setInventorySlotContents(slot, new ItemStack(stack.getItem(), 1, stack.getItemDamage() + 1));
            if (!this.isPowered())
                this.setPowered(true);
        } else if (stack == null)
            this.setPowered(false);

        if (this.timeLeft <= 0.0F)
        {
            if (stack2 == null)
                this.setInventorySlotContents(slot2, new ItemStack(ModItems.DataFragment));
            else if (stack2.stackSize < 64)
                stack2.stackSize++;
            this.timeLeft = 100.0F;
        } else if (this.timeLeft > 0.0F && (stack2 != null && stack2.stackSize < 64 || stack2 == null) && stack != null && stack.getItem() instanceof ItemLyokoFuel)
            this.timeLeft = this.timeLeft - 0.05F;
    }

    @Override
    public Packet getDescriptionPacket()
    {
        NBTTagCompound tag = new NBTTagCompound();
        this.writeToNBT(tag);
        return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 0, tag);
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt)
    {
        NBTTagCompound tag = pkt.func_148857_g();
        this.readFromNBT(tag);
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slot)
    {
        ItemStack stack = this.getStackInSlot(slot);
        if (stack != null)
            this.setInventorySlotContents(slot, null);
        return stack;
    }

    @Override
    public int getInventoryStackLimit()
    {
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player)
    {
        return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) == this && player.getDistanceSq(this.xCoord + 0.5, this.yCoord + 0.5, this.zCoord + 0.5) < 64;
    }

    @Override
    public void readFromNBT(NBTTagCompound tagCompound)
    {
        super.readFromNBT(tagCompound);
        NBTTagList tagList = tagCompound.getTagList("Inventory", 10);
        for (int i = 0; i < tagList.tagCount(); i++)
        {
            NBTTagCompound tag = tagList.getCompoundTagAt(i);
            byte slot = tag.getByte("Slot");
            if (slot >= 0 && slot < this.inv.length)
                this.inv[slot] = ItemStack.loadItemStackFromNBT(tag);
        }
        this.timeLeft = tagCompound.getFloat("remainingTime");
        this.sector = tagCompound.getString("sector");
        this.setPowered(tagCompound.getBoolean("isPowered"));
        this.temperature = tagCompound.getFloat("temperature");
    }

    @Override
    public void writeToNBT(NBTTagCompound tagCompound)
    {
        super.writeToNBT(tagCompound);
        tagCompound.setFloat("remainingTime", this.timeLeft);
        tagCompound.setString("sector", this.sector);
        tagCompound.setBoolean("isPowered", this.isPowered());
        tagCompound.setFloat("temperature", this.temperature);
        NBTTagList itemList = new NBTTagList();
        for (int i = 0; i < this.inv.length; i++)
        {
            ItemStack stack = this.inv[i];
            if (stack != null)
            {
                NBTTagCompound tag = new NBTTagCompound();
                tag.setByte("Slot", (byte) i);
                stack.writeToNBT(tag);
                itemList.appendTag(tag);
            }
        }
        tagCompound.setTag("Inventory", itemList);
    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack stack)
    {
        for (int i = -1; i < 2; i++)
            for (int k = -1; k < 2; k++)
                for (int j = -2; j < 1; j++)
                    if (BlockSuperCalc.isMultiBlock(this.worldObj, this.xCoord + i, this.yCoord + j, this.zCoord + k))
                        if (slot == 0 && stack != null && stack.getItem() instanceof ItemLyokoFuel)
                            return true;
        return false;
    }
    
	@Override
	public String getInventoryName()
	{
		return "tileentitysupercalc";
	}
	
	@Override
	public boolean hasCustomInventoryName()
	{
		return true;
	}
	
	@Override
	public void openInventory() {}
	
	@Override
	public void closeInventory() {}
	
    /*
     * @Override
     * public int[] getAccessibleSlotsFromSide(int side)
     * {
     * int[] slot = {1};
     * if(side == 0 || side == 1)
     * {
     * slot[0] = 0;
     * return slot;
     * }
     * return slot;
     * }
     * @Override
     * public boolean canInsertItem(int slot, ItemStack stack, int side)
     * {
     * return this.isStackValidForSlot(slot, stack);
     * }
     * @Override
     * public boolean canExtractItem(int slot, ItemStack stack, int side)
     * {
     * return slot == 1 && (side != 0 && side != 1);
     * }
     */
}
