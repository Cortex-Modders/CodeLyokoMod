/*
 * Code Lyoko Mod for Minecraft v@VERSION Copyright 2013 Cortex Modders, Matthew
 * Warren, Jacob Rhoda, and other contributors. Released under the MIT license
 * http://opensource.org/licenses/MIT
 */

package matt.lyoko.blocks;

import matt.lyoko.CodeLyoko;
import matt.lyoko.client.ClientProxy;
import matt.lyoko.entities.tileentity.TileEntitySuperCalcConsole;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockSuperCalcConsole extends BlockContainer
{
    public BlockSuperCalcConsole()
    {
        // material.iron
        super(Material.field_151573_f);
        // setCreativeTab
        this.func_149647_a(CodeLyoko.LyokoTabs);
    }

    @Override
    // createNewTileEntity
    public TileEntity func_149915_a(World world, int metadata)
    {
        return new TileEntitySuperCalcConsole();
    }

    @Override
    // onBlockActiavted
    public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
    {
        TileEntity tileEntity = world.func_147438_o(x, y, z);
        if (tileEntity == null || player.isSneaking())
            return false;
        else
        {
            player.openGui(CodeLyoko.instance, 2, world, x, y, z);
            return true;
        }
    }

    @Override
    // onBlockPlacedBy
    public void func_149689_a(World par1World, int x, int y, int z, EntityLivingBase par5EntityLiving, ItemStack par6ItemStack)
    {
        super.func_149689_a(par1World, x, y, z, par5EntityLiving, par6ItemStack);
        int l = MathHelper.floor_double(par5EntityLiving.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;

        par1World.setBlockMetadataWithNotify(x, y, z, l, 2);
    }

    @Override
    // setBlockBoundsBasedOnState
    public void func_149719_a(IBlockAccess blockAccess, int x, int y, int z)
    {
        super.func_149719_a(blockAccess, x, y, z);
        int meta = blockAccess.getBlockMetadata(x, y, z);
        // setBlockBounds - func_149676_a
        if (meta == 0 || meta == 2)
            this.func_149676_a(-0.35F, -0.12F, 0.0F, 1.35F, 1.12F, 1.0F);
        else if (meta == 1 || meta == 3)
            this.func_149676_a(0.0F, -0.12F, -0.35F, 1.0F, 1.12F, 1.35F);
        else
            this.func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);

    }

    @Override
    // registerBlockIcons
    public void func_149651_a(IIconRegister par1IconRegister)
    {
        // blockIcon
        this.field_149761_L = par1IconRegister.registerIcon("lyoko:supercalcconsole");
    }

    @Override
    // getRenderType
    public int func_149645_b()
    {
        return ClientProxy.superCalcConsoleRenderId;
    }

    @Override
    // isOpaqueCube
    public boolean func_149662_c()
    {
        return false;
    }

    @Override
    // isBlockSolid
    public boolean func_149747_d(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
    {
        return true;
    }

    @Override
    // renderAsNormalBlock
    public boolean func_149686_d()
    {
        return false;
    }
}