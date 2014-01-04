/*
 * Code Lyoko Mod for Minecraft v@VERSION Copyright 2013 Cortex Modders, Matthew
 * Warren, Jacob Rhoda, and other contributors. Released under the MIT license
 * http://opensource.org/licenses/MIT
 */

package matt.lyoko.blocks;

import java.util.Random;

import matt.lyoko.CodeLyoko;
import matt.lyoko.entities.mobs.EntityLyoko;
import matt.lyoko.entities.tileentity.TileEntityMarabounta;
import matt.lyoko.entities.vehicles.EntityVehicle;
import matt.lyoko.lib.LyokoDamageSource;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockMarabounta extends BlockContainer
{
    public BlockMarabounta(int par1)
    {
        super(par1, Material.iron);
        this.setCreativeTab(CodeLyoko.LyokoTabs);
        this.setTickRandomly(true);
    }

    private Icon normalTexture;
    private Icon evilTexture;

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.normalTexture = par1IconRegister.registerIcon("lyoko:marabounta" + (!CodeLyoko.useHDTextures ? "_16_16" : ""));
        this.evilTexture = par1IconRegister.registerIcon("lyoko:evilmarabounta" + (!CodeLyoko.useHDTextures ? "_16_16" : ""));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Icon getIcon(int side, int meta)
    {
        switch (meta)
        {
            case 0:
                return this.normalTexture;
            case 1:
                return this.evilTexture;
            default:
                return this.normalTexture;
        }
    }

    // @Override
    // public int idDropped(int par1, Random par1Random, int par2)
    // {
    // return 0;
    // }

    @Override
    public boolean removeBlockByPlayer(World world, EntityPlayer player, int x, int y, int z)
    {
        TileEntityMarabounta tem = (TileEntityMarabounta) world.getBlockTileEntity(x, y, z);

        if (tem.consumedBlock == 0)
            return world.setBlockToAir(x, y, z);

        return world.setBlock(x, y, z, tem.consumedBlock);
    }

    @Override
    public void onBlockAdded(World world, int x, int y, int z)
    {
        super.onBlockAdded(world, x, y, z);
    }

    @Override
    public void updateTick(World world, int x, int y, int z, Random rand)
    {
        this.convertLyokoBlocks(world, x + 1, y, z);
        this.convertLyokoBlocks(world, x - 1, y, z);
        this.convertLyokoBlocks(world, x, y + 1, z);
        this.convertLyokoBlocks(world, x, y - 1, z);
        this.convertLyokoBlocks(world, x, y, z + 1);
        this.convertLyokoBlocks(world, x, y, z - 1);
    }

    public void convertLyokoBlocks(World world, int x, int y, int z)
    {
        TileEntityMarabounta tem;

        if (world.getBlockId(x, y, z) == ModBlocks.Log.blockID)
        {
            world.setBlock(x, y, z, this.blockID);
            tem = (TileEntityMarabounta) world.getBlockTileEntity(x, y, z);
            tem.consumedBlock = ModBlocks.Log.blockID;
        } else if (world.getBlockId(x, y, z) == ModBlocks.Ice.blockID)
        {
            world.setBlock(x, y, z, this.blockID);
            tem = (TileEntityMarabounta) world.getBlockTileEntity(x, y, z);
            tem.consumedBlock = ModBlocks.Ice.blockID;
        } else if (world.getBlockId(x, y, z) == ModBlocks.Grass.blockID)
        {
            world.setBlock(x, y, z, this.blockID);
            tem = (TileEntityMarabounta) world.getBlockTileEntity(x, y, z);
            tem.consumedBlock = ModBlocks.Grass.blockID;
        } else if (world.getBlockId(x, y, z) == ModBlocks.Sand.blockID)
        {
            world.setBlock(x, y, z, this.blockID);
            tem = (TileEntityMarabounta) world.getBlockTileEntity(x, y, z);
            tem.consumedBlock = ModBlocks.Sand.blockID;
        } else if (world.getBlockId(x, y, z) == ModBlocks.Stone.blockID)
        {
            world.setBlock(x, y, z, this.blockID);
            tem = (TileEntityMarabounta) world.getBlockTileEntity(x, y, z);
            tem.consumedBlock = ModBlocks.Stone.blockID;
        } else if (world.getBlockId(x, y, z) == ModBlocks.Carthage.blockID)
        {
            world.setBlock(x, y, z, this.blockID);
            tem = (TileEntityMarabounta) world.getBlockTileEntity(x, y, z);
            tem.consumedBlock = ModBlocks.Carthage.blockID;
        } else if (world.getBlockId(x, y, z) == ModBlocks.VirtualBlock.blockID)
        {
            world.setBlock(x, y, z, this.blockID);
            tem = (TileEntityMarabounta) world.getBlockTileEntity(x, y, z);
            tem.consumedBlock = ModBlocks.VirtualBlock.blockID;
        }
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
    {
        float f = 0.125F;
        return AxisAlignedBB.getBoundingBox(par2 - 1 + f, par3 - 1 + f, par4 - 1 + f, par2 + 1 - f, par3 + 1 - f, par4 + 1 - f);
    }

    @Override
    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity ent)
    {
        world.getBlockTileEntity(x, y, z);
        if (ent instanceof EntityLyoko)
            ent.attackEntityFrom(LyokoDamageSource.marabounta, 100);
        else if (ent instanceof EntityVehicle && world.getBlockMetadata(x, y, z) == 1)
            ((EntityVehicle) ent).setDead();
        else if (ent instanceof EntityPlayer && world.getBlockMetadata(x, y, z) == 1)
            if (!((EntityPlayer) ent).capabilities.isCreativeMode)
                ent.attackEntityFrom(LyokoDamageSource.marabounta, 9);
    }

    @Override
    public void onBlockClicked(World world, int x, int y, int z, EntityPlayer player)
    {
        super.onBlockClicked(world, x, y, z, player);
        TileEntityMarabounta temp = (TileEntityMarabounta) world.getBlockTileEntity(x, y, z);
        world.setBlockMetadataWithNotify(x, y, z, 1, 2);
        world.setBlockTileEntity(x, y, z, temp);
    }

    @Override
    // createNewTileEntity
    public TileEntity func_149915_a(World world, int metadata)
    {
        return new TileEntityMarabounta();
    }
}