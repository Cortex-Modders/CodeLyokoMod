/*
 * Code Lyoko Mod for Minecraft v@VERSION Copyright 2013 Cortex Modders, Matthew
 * Warren, Jacob Rhoda, and other contributors. Released under the MIT license
 * http://opensource.org/licenses/MIT
 */

package matt.lyoko.blocks;

import matt.lyoko.CodeLyoko;
import matt.lyoko.entities.tileentity.TileEntitySuperCalcConsole;
import matt.lyoko.proxy.ClientProxy;
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
        super(Material.iron);
        // setCreativeTab
        this.setCreativeTab(CodeLyoko.LyokoTabs);
    }

    @Override
    // createNewTileEntity
    public TileEntity createNewTileEntity(World world, int metadata)
    {
        return new TileEntitySuperCalcConsole();
    }

    @Override
    // onBlockActiavted
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
    {
        TileEntity tileEntity = world.getTileEntity(x, y, z);
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
    public void onBlockPlacedBy(World par1World, int x, int y, int z, EntityLivingBase par5EntityLiving, ItemStack par6ItemStack)
    {
        super.onBlockPlacedBy(par1World, x, y, z, par5EntityLiving, par6ItemStack);
        int l = MathHelper.floor_double(par5EntityLiving.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;

        par1World.setBlockMetadataWithNotify(x, y, z, l, 2);
    }

    @Override
    // setBlockBoundsBasedOnState
    public void setBlockBoundsBasedOnState(IBlockAccess blockAccess, int x, int y, int z)
    {
        super.setBlockBoundsBasedOnState(blockAccess, x, y, z);
        int meta = blockAccess.getBlockMetadata(x, y, z);
        // setBlockBounds - setBlockBounds
        if (meta == 0 || meta == 2)
            this.setBlockBounds(-0.35F, -0.12F, 0.0F, 1.35F, 1.12F, 1.0F);
        else if (meta == 1 || meta == 3)
            this.setBlockBounds(0.0F, -0.12F, -0.35F, 1.0F, 1.12F, 1.35F);
        else
            this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);

    }

    @Override
    // registerBlockIcons
    public void registerBlockIcons(IIconRegister par1IconRegister)
    {
        // blockIcon
        this.blockIcon = par1IconRegister.registerIcon("lyoko:supercalcconsole");
    }

    @Override
    // getRenderType
    public int getRenderType()
    {
        return ClientProxy.superCalcConsoleRenderId;
    }

    @Override
    // isOpaqueCube
    public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
    // isBlockSolid
    public boolean isBlockSolid(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
    {
        return true;
    }

    @Override
    // renderAsNormalBlock
    public boolean renderAsNormalBlock()
    {
        return false;
    }
}
