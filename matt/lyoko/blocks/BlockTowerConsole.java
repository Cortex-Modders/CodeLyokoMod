package matt.lyoko.blocks;

import matt.lyoko.CodeLyoko;
import matt.lyoko.entities.TileEntityTowerConsole;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.world.*;

public class BlockTowerConsole extends BlockContainer
{
    public BlockTowerConsole(int par1)
    {
        super(par1, Material.iron);
        this.setCreativeTab(CodeLyoko.LyokoTabs);
        this.setBlockBounds(-0.25F, 0.0F, 0.0F, 1.25F, 1.0F, 1.0F);
    }

    @Override
    public TileEntity createNewTileEntity(World world)
    {
        return new TileEntityTowerConsole();
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
    {
        return null;
    }

    // Set to transparent.
    @Override
    public boolean isOpaqueCube() {

        return false;
    }

    // Will not render actual block, just the tile entity.
    @Override
    public int getRenderType()
    {
        return -1;
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
    {
        TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
        if (tileEntity == null || player.isSneaking())
        {
            return false;
        }
        else
        {
            player.openGui(CodeLyoko.instance, 1, world, x, y, z);
            return true;
        }
    }

    public void onBlockPlacedBy(World par1World, int x, int y, int z, EntityLiving par5EntityLiving, ItemStack par6ItemStack)
    {
        int l = MathHelper.floor_double((double)(par5EntityLiving.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

        if (l == 0)
        {
            par1World.setBlockMetadataWithNotify(x, y, z, 2, 2);
            this.setBlockBounds(-0.25F, 0.0F, 0.0F, 1.25F, 1.0F, 1.0F);
        }

        if (l == 1)
        {
            par1World.setBlockMetadataWithNotify(x, y, z, 5, 2);
            this.setBlockBounds(0.0F, 0.0F, -0.25F, 1.0F, 1.0F, 1.25F);
        }

        if (l == 2)
        {
            par1World.setBlockMetadataWithNotify(x, y, z, 3, 2);
            this.setBlockBounds(-0.25F, 0.0F, 0.0F, 1.25F, 1.0F, 1.0F);
        }

        if (l == 3)
        {
            par1World.setBlockMetadataWithNotify(x, y, z, 4, 2);
            this.setBlockBounds(0.0F, 0.0F, -0.25F, 1.0F, 1.0F, 1.25F);
        }
    }
}