/*
 * Code Lyoko Mod for Minecraft v@VERSION Copyright 2013 Cortex Modders, Matthew
 * Warren, Jacob Rhoda, and other contributors. Released under the MIT license
 * http://opensource.org/licenses/MIT
 */

package matt.lyoko.blocks;

import java.util.Random;

import matt.lyoko.CodeLyoko;
import matt.lyoko.entities.tileentity.TileEntitySuperCalc;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockSuperCalc extends BlockContainer
{

    public BlockSuperCalc(int id)
    {
        super(id, Material.iron);
        this.setCreativeTab(CodeLyoko.LyokoTabs);
    }

    public static boolean isMultiBlock(World world, int x, int y, int z)
    {
        if (world.getBlockId(x, y + 1, z) == ModBlocks.SuperCalc.blockID && world.getBlockId(x, y + 2, z) == ModBlocks.SuperCalc.blockID && world.getBlockId(x + 1, y, z + 1) == ModBlocks.SuperCalc.blockID && world.getBlockId(x + 1, y, z) == ModBlocks.SuperCalc.blockID && world.getBlockId(x + 1, y, z - 1) == ModBlocks.SuperCalc.blockID && world.getBlockId(x, y, z + 1) == ModBlocks.SuperCalc.blockID && world.getBlockId(x, y, z) == ModBlocks.SuperCalc.blockID && world.getBlockId(x, y, z - 1) == ModBlocks.SuperCalc.blockID && world.getBlockId(x - 1, y, z + 1) == ModBlocks.SuperCalc.blockID && world.getBlockId(x - 1, y, z) == ModBlocks.SuperCalc.blockID && world.getBlockId(x - 1, y, z - 1) == ModBlocks.SuperCalc.blockID && checkNotConflicting(world, x, y, z))
            return true;
        return false;
    }

    public static boolean checkNotConflicting(World world, int x, int y, int z)
    {
        if (world.getBlockId(x + 1, y - 1, z + 1) != ModBlocks.SuperCalc.blockID && world.getBlockId(x + 1, y - 1, z) != ModBlocks.SuperCalc.blockID && world.getBlockId(x + 1, y - 1, z - 1) != ModBlocks.SuperCalc.blockID && world.getBlockId(x, y - 1, z + 1) != ModBlocks.SuperCalc.blockID && world.getBlockId(x, y - 1, z) != ModBlocks.SuperCalc.blockID && world.getBlockId(x, y - 1, z - 1) != ModBlocks.SuperCalc.blockID && world.getBlockId(x - 1, y - 1, z + 1) != ModBlocks.SuperCalc.blockID && world.getBlockId(x - 1, y - 1, z) != ModBlocks.SuperCalc.blockID && world.getBlockId(x - 1, y - 1, z - 1) != ModBlocks.SuperCalc.blockID && aroundBase(world, x, y, z) && aroundPillarLower(world, x, y, z) && aroundPillarUpper(world, x, y, z) && world.getBlockId(x, y + 3, z) != ModBlocks.SuperCalc.blockID)
            return true;
        return false;
    }

    public static boolean aroundBase(World world, int x, int y, int z)
    {
        if (world.getBlockId(x - 2, y, z + 1) != ModBlocks.SuperCalc.blockID && world.getBlockId(x - 2, y, z) != ModBlocks.SuperCalc.blockID && world.getBlockId(x - 2, y, z - 1) != ModBlocks.SuperCalc.blockID && world.getBlockId(x + 2, y, z + 1) != ModBlocks.SuperCalc.blockID && world.getBlockId(x + 2, y, z) != ModBlocks.SuperCalc.blockID && world.getBlockId(x + 2, y, z - 1) != ModBlocks.SuperCalc.blockID && world.getBlockId(x + 1, y, z + 2) != ModBlocks.SuperCalc.blockID && world.getBlockId(x + 1, y, z - 2) != ModBlocks.SuperCalc.blockID && world.getBlockId(x, y, z + 2) != ModBlocks.SuperCalc.blockID && world.getBlockId(x, y, z - 2) != ModBlocks.SuperCalc.blockID && world.getBlockId(x - 1, y, z + 2) != ModBlocks.SuperCalc.blockID && world.getBlockId(x - 1, y, z - 2) != ModBlocks.SuperCalc.blockID)
            return true;
        return false;
    }

    public static boolean aroundPillarLower(World world, int x, int y, int z)
    {
        if (world.getBlockId(x + 1, y + 1, z + 1) != ModBlocks.SuperCalc.blockID && world.getBlockId(x + 1, y + 1, z) != ModBlocks.SuperCalc.blockID && world.getBlockId(x + 1, y + 1, z - 1) != ModBlocks.SuperCalc.blockID && world.getBlockId(x, y + 1, z + 1) != ModBlocks.SuperCalc.blockID && world.getBlockId(x, y + 1, z - 1) != ModBlocks.SuperCalc.blockID && world.getBlockId(x - 1, y + 1, z + 1) != ModBlocks.SuperCalc.blockID && world.getBlockId(x - 1, y + 1, z) != ModBlocks.SuperCalc.blockID && world.getBlockId(x - 1, y + 1, z - 1) != ModBlocks.SuperCalc.blockID)
            return true;
        return false;
    }

    public static boolean aroundPillarUpper(World world, int x, int y, int z)
    {
        if (world.getBlockId(x + 1, y + 2, z) != ModBlocks.SuperCalc.blockID && world.getBlockId(x, y + 2, z + 1) != ModBlocks.SuperCalc.blockID && world.getBlockId(x, y + 2, z - 1) != ModBlocks.SuperCalc.blockID && world.getBlockId(x - 1, y + 2, z) != ModBlocks.SuperCalc.blockID)
            return true;
        return false;
    }

    private Icon side1;
    private Icon side2;
    private Icon side3;
    private Icon side4;
    private Icon side5;
    private Icon side6;
    private Icon side7;
    private Icon side8;
    private Icon side9;
    private Icon side10;
    private Icon side11;
    private Icon side12;
    private Icon topBottom;
    private Icon pillarUpper;
    private Icon pillarLower;

    @Override
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.side1 = this.blockIcon = par1IconRegister.registerIcon("lyoko:computer_1");
        this.side2 = par1IconRegister.registerIcon("lyoko:computer_2");
        this.side3 = par1IconRegister.registerIcon("lyoko:computer_3");
        this.side4 = par1IconRegister.registerIcon("lyoko:computer_4");
        this.side5 = par1IconRegister.registerIcon("lyoko:computer_5");
        this.side6 = par1IconRegister.registerIcon("lyoko:computer_6");
        this.side7 = par1IconRegister.registerIcon("lyoko:computer_7");
        this.side8 = par1IconRegister.registerIcon("lyoko:computer_8");
        this.side9 = par1IconRegister.registerIcon("lyoko:computer_9");
        this.side10 = par1IconRegister.registerIcon("lyoko:computer_10");
        this.side11 = par1IconRegister.registerIcon("lyoko:computer_11");
        this.side12 = par1IconRegister.registerIcon("lyoko:computer_12");
        this.topBottom = par1IconRegister.registerIcon("lyoko:computer_0");
        this.pillarUpper = par1IconRegister.registerIcon("lyoko:computer_13");
        this.pillarLower = par1IconRegister.registerIcon("lyoko:computer_14");
    }

    @Override
    public Icon getBlockTexture(IBlockAccess access, int x, int y, int z, int side)
    {
        // case 2: //right
        // case 3: //left
        // case 4: //front
        // case 5: //back
        World world = access.getBlockTileEntity(x, y, z).worldObj;
        if (side == 0 || side == 1)
            return this.topBottom;
        if (isMultiBlock(world, x + 1, y, z + 1))
            switch (side)
            {
                case 2:
                    return this.side12; // right
                case 4:
                    return this.side1; // front
            }
        else if (isMultiBlock(world, x + 1, y, z))
            switch (side)
            {
                case 4:
                    return this.side2; // front
            }
        else if (isMultiBlock(world, x + 1, y, z - 1))
            switch (side)
            {
                case 3:
                    return this.side4; // left
                case 4:
                    return this.side3; // front
            }
        else if (isMultiBlock(world, x, y, z + 1))
            switch (side)
            {
                case 2:
                    return this.side11; // right
            }
        else if (isMultiBlock(world, x, y, z - 1))
            switch (side)
            {
                case 3:
                    return this.side5; // left
            }
        else if (isMultiBlock(world, x - 1, y, z + 1))
            switch (side)
            {
                case 2:
                    return this.side10; // right
                case 5:
                    return this.side9; // back
            }
        else if (isMultiBlock(world, x - 1, y, z))
            switch (side)
            {
                case 5:
                    return this.side8; // back
            }
        else if (isMultiBlock(world, x - 1, y, z - 1))
            switch (side)
            {
                case 3:
                    return this.side6; // left
                case 5:
                    return this.side7; // back
            }
        else if (isMultiBlock(world, x, y - 1, z))
            return this.pillarLower;
        else if (isMultiBlock(world, x, y - 2, z))
            return this.pillarUpper;
        return this.side1;
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int idk, float what, float these, float are)
    {
        TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
        if (tileEntity == null || player.isSneaking())
            return false;
        else
        {
            for (int i = -1; i < 2; i++)
                for (int j = -1; j < 2; j++)
                    if (BlockSuperCalc.isMultiBlock(world, x + i, y, z + j))
                    {
                        player.openGui(CodeLyoko.instance, 0, world, x + i, y, z + j);
                        return true;
                    }
            if (BlockSuperCalc.isMultiBlock(world, x, y - 1, z))
            {
                player.openGui(CodeLyoko.instance, 0, world, x, y - 1, z);
                return true;
            } else if (BlockSuperCalc.isMultiBlock(world, x, y - 2, z))
            {
                player.openGui(CodeLyoko.instance, 0, world, x, y - 2, z);
                return true;
            }
            return false;
        }
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, int par5, int par6)
    {
        this.dropItems(world, x, y, z);
        super.breakBlock(world, x, y, z, par5, par6);
    }

    private void dropItems(World world, int x, int y, int z)
    {
        Random rand = new Random();

        TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
        if (!(tileEntity instanceof IInventory))
            return;
        IInventory inventory = (IInventory) tileEntity;

        for (int i = 0; i < inventory.getSizeInventory(); i++)
        {
            ItemStack item = inventory.getStackInSlot(i);

            if (item != null && item.stackSize > 0)
            {
                float rx = rand.nextFloat() * 0.8F + 0.1F;
                float ry = rand.nextFloat() * 0.8F + 0.1F;
                float rz = rand.nextFloat() * 0.8F + 0.1F;

                EntityItem entityItem = new EntityItem(world, x + rx, y + ry, z + rz, new ItemStack(item.itemID, item.stackSize, item.getItemDamage()));

                if (item.hasTagCompound())
                    entityItem.getEntityItem().setTagCompound((NBTTagCompound) item.getTagCompound().copy());

                float factor = 0.05F;
                entityItem.motionX = rand.nextGaussian() * factor;
                entityItem.motionY = rand.nextGaussian() * factor + 0.2F;
                entityItem.motionZ = rand.nextGaussian() * factor;
                world.spawnEntityInWorld(entityItem);
                item.stackSize = 0;
            }
        }
    }

    @Override
    public TileEntity createNewTileEntity(World world)
    {
        return new TileEntitySuperCalc();
    }
}