/*
 * Code Lyoko Mod for Minecraft v@VERSION Copyright 2013 Cortex Modders, Matthew
 * Warren, Jacob Rhoda, and other contributors. Released under the MIT license
 * http://opensource.org/licenses/MIT
 */

package matt.lyoko.blocks;

import matt.lyoko.CodeLyoko;
import matt.lyoko.entities.tileentity.TileEntityTowerConsole;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockTowerConsole extends BlockContainer
{
    public BlockTowerConsole()
    {
        // material.iron
        super(Material.field_151573_f);
        // setCreativeTab
        this.func_149647_a(CodeLyoko.LyokoTabs);
    }

    @Override
    // registerBlockIcons
    public void func_149651_a(IIconRegister par1IconRegister)
    {
        this.field_149761_L = par1IconRegister.registerIcon("lyoko:towerconsole");
    }

    @Override
    // createNewTileEntity
    public TileEntity func_149915_a(World world, int metadata)
    {
        return new TileEntityTowerConsole();
    }

    @Override
    // getCollisionBoundingBoxFromPool
    public AxisAlignedBB func_149668_a(World par1World, int par2, int par3, int par4)
    {
        return null;
    }

    // Set to transparent.
    @Override
    // isOpaqueCube
    public boolean func_149662_c()
    {

        return false;
    }

    // Will not render actual block, just the tile entity.
    @Override
    // getRenderType
    public int func_149645_b()
    {
        return -1;
    }

    @Override
    // onBlockActivated
    public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
    {
        TileEntity tileEntity = world.func_147438_o(x, y, z);
        if (tileEntity == null || player.isSneaking())
            return false;
        else
        {
            player.openGui(CodeLyoko.instance, 1, world, x, y, z);
            return true;
        }
    }

    @Override
    // setBlockBoundsBasedOnState
    public void func_149719_a(IBlockAccess blockAccess, int x, int y, int z)
    {
        super.func_149719_a(blockAccess, x, y, z);
        int meta = blockAccess.getBlockMetadata(x, y, z);

        if (meta == 0 || meta == 2)
            this.func_149676_a(-0.25F, 0.0F, 0.0F, 1.25F, 1.0F, 1.0F);
        else if (meta == 1 || meta == 3)
            this.func_149676_a(0.0F, 0.0F, -0.25F, 1.0F, 1.0F, 1.25F);
        else
            this.func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);

    }

    @Override
    // onBlockPlacedBy
    public void func_149689_a(World par1World, int x, int y, int z, EntityLivingBase par5EntityLiving, ItemStack par6ItemStack)
    {
        super.func_149689_a(par1World, x, y, z, par5EntityLiving, par6ItemStack);
        int l = MathHelper.floor_double(par5EntityLiving.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;

        if (l == 0)
            par1World.setBlockMetadataWithNotify(x, y, z, 0, 2);

        if (l == 1)
            par1World.setBlockMetadataWithNotify(x, y, z, 1, 2);

        if (l == 2)
            par1World.setBlockMetadataWithNotify(x, y, z, 2, 2);

        if (l == 3)
            par1World.setBlockMetadataWithNotify(x, y, z, 3, 2);
    }
}