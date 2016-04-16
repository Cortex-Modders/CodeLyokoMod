/*
 * Code Lyoko Mod for Minecraft ${version}
 * Copyright 2014 Cortex Modders, Matthew Warren, Jacob Rhoda, and other contributors.
 * Released under the MIT license http://opensource.org/licenses/MIT
 */
package net.cortexmodders.lyoko.blocks;

import net.cortexmodders.lyoko.CodeLyoko;
import net.cortexmodders.lyoko.proxy.ClientProxy;
import net.cortexmodders.lyoko.tileentity.TileEntityHolomap;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.List;

public class BlockHolomap extends BlockContainer
{
    public BlockHolomap()
    {
        // material.iron
        super(Material.iron);
        // setCreativeTab
        this.setCreativeTab(CodeLyoko.LyokoTabs);
    }

    public static boolean isMultiBlock(IBlockAccess access, int x, int y, int z)
    {
        // getBlock - getBlock
        if (access.getBlock(x - 1, y, z - 1) instanceof BlockHolomap && access.getBlock(x - 1, y, z) instanceof BlockHolomap && access.getBlock(x - 1, y, z + 1) instanceof BlockHolomap && access.getBlock(x, y, z - 1) instanceof BlockHolomap && access.getBlock(x, y, z) instanceof BlockHolomap && access.getBlock(x, y, z + 1) instanceof BlockHolomap && access.getBlock(x + 1, y, z - 1) instanceof BlockHolomap && access.getBlock(x + 1, y, z) instanceof BlockHolomap && access.getBlock(x + 1, y, z + 1) instanceof BlockHolomap && clearOnSides(access, x, y, z))
            return true;
        return false;
    }

    public static boolean clearOnSides(IBlockAccess access, int x, int y, int z)
    {
        for (int i = -2; i < 3; i++)
            for (int j = -2; j < 3; j++)
                if (i == -2 || i == 2 || j == -2 || j == 2)
                    // getBlock - getBlock
                    if (access.getBlock(x + i, y, z + j) instanceof BlockHolomap)
                        return false;
        return true;
    }

    @Override
    // onBlockPlaced
    public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int meta)
    {
        meta = 8;
        return meta;
    }

    @Override
    // breakBlock
    public void breakBlock(World world, int x, int y, int z, Block block, int meta)
    {
        super.breakBlock(world, x, y, z, block, meta);
        if (isMultiBlock(world, x, y, z)) {

        }
    }

    @Override
    // setBlocKBoundsBasedOnState
    public void setBlockBoundsBasedOnState(IBlockAccess access, int x, int y, int z)
    {
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, access.getBlockMetadata(x, y, z) < 8 ? 1.0F : isMultiBlock(access, x, y, z) ? 1.0F : 0.5F, 1.0F);
    }

    @Override
    public boolean removedByPlayer(World world, EntityPlayer player, int x, int y, int z)
    {
        if (!world.isRemote)
            for (int i = -1; i < 2; i++)
                for (int j = -1; j < 2; j++)
                    if (isMultiBlock(world, x + i, y, z + j))
                        for (int k = -1; k < 2; k++)
                            for (int l = -1; l < 2; l++)
                                world.setBlockMetadataWithNotify(x + k + i, y, z + l + j, 8, 3);
        return super.removedByPlayer(world, player, x, y, z);
    }

    @Override
    public void onBlockExploded(World world, int x, int y, int z, Explosion explosion)
    {
        if (!world.isRemote)
            for (int i = -1; i < 2; i++)
                for (int j = -1; j < 2; j++)
                    if (isMultiBlock(world, x + i, y, z + j))
                        for (int k = -1; k < 2; k++)
                            for (int l = -1; l < 2; l++)
                                world.setBlockMetadataWithNotify(x + k + i, y, z + l + j, 8, 3);
        super.onBlockExploded(world, x, y, z, explosion);
    }

    @Override
    // damageDropped
    public int damageDropped(int par1)
    {
        return 8;
    }

    @Override
    // onBlockAdded
    public void onBlockAdded(World world, int x, int y, int z)
    {
        super.onBlockAdded(world, x, y, z);
        if (!world.isRemote)
            for (int i = -1; i < 2; i++)
                for (int j = -1; j < 2; j++)
                    if (isMultiBlock(world, x + i, y, z + j))
                        this.setMeta(world, x + i, y, z + j);
    }

    public void setMeta(World world, int x, int y, int z)
    {
        world.setBlockMetadataWithNotify(x + 1, y, z + 1, 0, 3);
        world.setBlockMetadataWithNotify(x + 1, y, z - 1, 1, 3);
        world.setBlockMetadataWithNotify(x - 1, y, z - 1, 2, 3);
        world.setBlockMetadataWithNotify(x - 1, y, z + 1, 3, 3);
        world.setBlockMetadataWithNotify(x, y, z + 1, 4, 3);
        world.setBlockMetadataWithNotify(x + 1, y, z, 5, 3);
        world.setBlockMetadataWithNotify(x, y, z - 1, 6, 3);
        world.setBlockMetadataWithNotify(x - 1, y, z, 7, 3);
        world.setBlockMetadataWithNotify(x, y, z, 8, 3);
    }

    @Override
    // registerIcons
    public void registerBlockIcons(IIconRegister register)
    {
        // blockIcon
        this.blockIcon = register.registerIcon("lyoko:holomap");
    }

    @Override
    // createNewTileEntity
    public TileEntity createNewTileEntity(World world, int metadata)
    {
        return new TileEntityHolomap();
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    // getSubBlocks
    public void getSubBlocks(Item par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        par3List.add(new ItemStack(ModBlocks.holomap, 1, 8));
    }

    @Override
    // getRenderBlockPass
    public int getRenderBlockPass()
    {
        return 0;
    }

    @Override
    // getRenderType
    public int getRenderType()
    {
        return ClientProxy.holomapRenderId;
    }

    @Override
    // isOpaqueCube
    public boolean isOpaqueCube()
    {
        return false;
    }
}
