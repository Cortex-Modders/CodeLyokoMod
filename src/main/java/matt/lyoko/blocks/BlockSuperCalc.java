/*
 * Code Lyoko Mod for Minecraft v@VERSION Copyright 2013 Cortex Modders, Matthew
 * Warren, Jacob Rhoda, and other contributors. Released under the MIT license
 * http://opensource.org/licenses/MIT
 */

package matt.lyoko.blocks;

import java.util.Random;

import matt.lyoko.CodeLyoko;
import matt.lyoko.entities.tileentity.TileEntitySuperCalc;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockSuperCalc extends BlockContainer
{

    public BlockSuperCalc()
    {
        //Material.iron
        super(Material.field_151573_f);
        //setCreativeTab
        this.func_149647_a(CodeLyoko.LyokoTabs);
    }

    public static boolean isMultiBlock(World world, int x, int y, int z)
    {
        if (world.func_147439_a(x, y + 1, z) instanceof BlockSuperCalc && world.func_147439_a(x, y + 2, z) instanceof BlockSuperCalc && world.func_147439_a(x + 1, y, z + 1) instanceof BlockSuperCalc && world.func_147439_a(x + 1, y, z) instanceof BlockSuperCalc && world.func_147439_a(x + 1, y, z - 1) instanceof BlockSuperCalc && world.func_147439_a(x, y, z + 1) instanceof BlockSuperCalc && world.func_147439_a(x, y, z) instanceof BlockSuperCalc && world.func_147439_a(x, y, z - 1) instanceof BlockSuperCalc && world.func_147439_a(x - 1, y, z + 1) instanceof BlockSuperCalc && world.func_147439_a(x - 1, y, z) instanceof BlockSuperCalc && world.func_147439_a(x - 1, y, z - 1) instanceof BlockSuperCalc && checkNotConflicting(world, x, y, z))
            return true;
        return false;
    }

    public static boolean checkNotConflicting(World world, int x, int y, int z)
    {
        if (!(world.func_147439_a(x + 1, y - 1, z + 1) instanceof BlockSuperCalc) && !(world.func_147439_a(x + 1, y - 1, z) instanceof BlockSuperCalc) && !(world.func_147439_a(x + 1, y - 1, z - 1) instanceof BlockSuperCalc) && !(world.func_147439_a(x, y - 1, z + 1) instanceof BlockSuperCalc) && !(world.func_147439_a(x, y - 1, z) instanceof BlockSuperCalc) && !(world.func_147439_a(x, y - 1, z - 1) instanceof BlockSuperCalc) && !(world.func_147439_a(x - 1, y - 1, z + 1) instanceof BlockSuperCalc) && !(world.func_147439_a(x - 1, y - 1, z) instanceof BlockSuperCalc) && !(world.func_147439_a(x - 1, y - 1, z - 1) instanceof BlockSuperCalc) && aroundBase(world, x, y, z) && aroundPillarLower(world, x, y, z) && aroundPillarUpper(world, x, y, z) && !(world.func_147439_a(x, y + 3, z) instanceof BlockSuperCalc))
            return true;
        return false;
    }

    public static boolean aroundBase(World world, int x, int y, int z)
    {
        if (!(world.func_147439_a(x - 2, y, z + 1) instanceof BlockSuperCalc) && !(world.func_147439_a(x - 2, y, z) instanceof BlockSuperCalc) && !(world.func_147439_a(x - 2, y, z - 1) instanceof BlockSuperCalc) && !(world.func_147439_a(x + 2, y, z + 1) instanceof BlockSuperCalc) && !(world.func_147439_a(x + 2, y, z) instanceof BlockSuperCalc) && !(world.func_147439_a(x + 2, y, z - 1) instanceof BlockSuperCalc) && !(world.func_147439_a(x + 1, y, z + 2) instanceof BlockSuperCalc) && !(world.func_147439_a(x + 1, y, z - 2) instanceof BlockSuperCalc) && !(world.func_147439_a(x, y, z + 2) instanceof BlockSuperCalc) && !(world.func_147439_a(x, y, z - 2) instanceof BlockSuperCalc) && !(world.func_147439_a(x - 1, y, z + 2) instanceof BlockSuperCalc) && !(world.func_147439_a(x - 1, y, z - 2) instanceof BlockSuperCalc))
            return true;
        return false;
    }

    public static boolean aroundPillarLower(World world, int x, int y, int z)
    {
        if (!(world.func_147439_a(x + 1, y + 1, z + 1) instanceof BlockSuperCalc) && !(world.func_147439_a(x + 1, y + 1, z) instanceof BlockSuperCalc) && !(world.func_147439_a(x + 1, y + 1, z - 1) instanceof BlockSuperCalc) && !(world.func_147439_a(x, y + 1, z + 1) instanceof BlockSuperCalc) && !(world.func_147439_a(x, y + 1, z - 1) instanceof BlockSuperCalc) && !(world.func_147439_a(x - 1, y + 1, z + 1) instanceof BlockSuperCalc) && !(world.func_147439_a(x - 1, y + 1, z) instanceof BlockSuperCalc) && !(world.func_147439_a(x - 1, y + 1, z - 1) instanceof BlockSuperCalc))
            return true;
        return false;
    }

    public static boolean aroundPillarUpper(World world, int x, int y, int z)
    {
        if (!(world.func_147439_a(x + 1, y + 2, z) instanceof BlockSuperCalc) && !(world.func_147439_a(x, y + 2, z + 1) instanceof BlockSuperCalc) && !(world.func_147439_a(x, y + 2, z - 1) instanceof BlockSuperCalc) && !(world.func_147439_a(x - 1, y + 2, z) instanceof BlockSuperCalc))
            return true;
        return false;
    }

    private IIcon side1;
    private IIcon side2;
    private IIcon side3;
    private IIcon side4;
    private IIcon side5;
    private IIcon side6;
    private IIcon side7;
    private IIcon side8;
    private IIcon side9;
    private IIcon side10;
    private IIcon side11;
    private IIcon side12;
    private IIcon topBottom;
    private IIcon pillarUpper;
    private IIcon pillarLower;

    @Override
    //registerIcons
    public void func_149651_a(IIconRegister par1IconRegister)
    {
        // field_149761_L - blockIcon
        this.side1 = this.field_149761_L = par1IconRegister.registerIcon("lyoko:computer_1");
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
    public IIcon func_149673_e(IBlockAccess access, int x, int y, int z, int side)
    {
        // case 2: //right
        // case 3: //left
        // case 4: //front
        // case 5: //back
        //getTileEntity | getWorldObj
        World world = access.func_147438_o(x, y, z).func_145831_w();
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
    //onBlockActivated
    public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int idk, float what, float these, float are)
    {
        TileEntity tileEntity = world.func_147438_o(x, y, z);
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
    //breakBlock
    public void func_149749_a(World world, int x, int y, int z, Block block, int metadata)
    {
        this.dropItems(world, x, y, z);
        super.func_149749_a(world, x, y, z, block, metadata);
    }

    private void dropItems(World world, int x, int y, int z)
    {
        Random rand = new Random();

        TileEntity tileEntity = world.func_147438_o(x, y, z);
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

                EntityItem entityItem = new EntityItem(world, x + rx, y + ry, z + rz, new ItemStack(item.getItem(), item.stackSize, item.getItemDamage()));

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
    //createNewTileEntity
    public TileEntity func_149915_a(World world, int metadata)
    {
        return new TileEntitySuperCalc();
    }
}