/*
 * Code Lyoko Mod for Minecraft v@VERSION Copyright 2013 Cortex Modders, Matthew
 * Warren, Jacob Rhoda, and other contributors. Released under the MIT license
 * http://opensource.org/licenses/MIT
 */

package matt.lyoko.blocks;

import matt.lyoko.CodeLyoko;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockTowerFloor extends Block
{
    public BlockTowerFloor(int par1)
    {
        super(par1, Material.iron);
        this.setCreativeTab(CodeLyoko.LyokoTabs);
    }

    public boolean isMultiBlock(IBlockAccess access, int x, int y, int z)
    {
        if (access.getBlockId(x + 1, y, z + 1) == ModBlocks.TowerFloor.blockID && access.getBlockId(x + 1, y, z) == ModBlocks.TowerFloor.blockID && access.getBlockId(x + 1, y, z - 1) == ModBlocks.TowerFloor.blockID && access.getBlockId(x, y, z + 1) == ModBlocks.TowerFloor.blockID && access.getBlockId(x, y, z) == ModBlocks.TowerFloor.blockID && access.getBlockId(x, y, z - 1) == ModBlocks.TowerFloor.blockID && access.getBlockId(x - 1, y, z + 1) == ModBlocks.TowerFloor.blockID && access.getBlockId(x - 1, y, z) == ModBlocks.TowerFloor.blockID && access.getBlockId(x - 1, y, z - 1) == ModBlocks.TowerFloor.blockID && access.getBlockId(x + 2, y, z) == ModBlocks.TowerFloor.blockID && access.getBlockId(x + 3, y, z) == ModBlocks.TowerFloor.blockID && access.getBlockMetadata(x, y, z) == 0)
            return true;
        else if (access.getBlockId(x + 1, y, z + 1) == ModBlocks.TowerFloor.blockID && access.getBlockId(x + 1, y, z) == ModBlocks.TowerFloor.blockID && access.getBlockId(x + 1, y, z - 1) == ModBlocks.TowerFloor.blockID && access.getBlockId(x, y, z + 1) == ModBlocks.TowerFloor.blockID && access.getBlockId(x, y, z) == ModBlocks.TowerFloor.blockID && access.getBlockId(x, y, z - 1) == ModBlocks.TowerFloor.blockID && access.getBlockId(x - 1, y, z + 1) == ModBlocks.TowerFloor.blockID && access.getBlockId(x - 1, y, z) == ModBlocks.TowerFloor.blockID && access.getBlockId(x - 1, y, z - 1) == ModBlocks.TowerFloor.blockID && access.getBlockMetadata(x, y, z) == 1)
            return true;
        return false;
    }

    @Override
    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity ent)
    {
        int distance = y + 2;
        if (world.getBlockMetadata(x, y, z) == 0 && this.isMultiBlock(world, x, y, z))
        {
            for (int i = y + 1; i < 256; i++)
                if (world.getBlockId(x, i, z) == ModBlocks.TowerFloor.blockID)
                {
                    distance = i + 1;
                    break;
                }
            ent.setLocationAndAngles(x + 1.75, distance, z + 0.5, 90, ent.rotationPitch);
            if (!ent.worldObj.isRemote)
                ent.setVelocity(0.0D, 0.0D, 0.0D);
        } else if (world.getBlockMetadata(x, y, z) == 1 && this.isMultiBlock(world, x, y, z))
        {
            for (int i = 0; i < y; i++)
                if (world.getBlockId(x, i, z) == ModBlocks.TowerFloor.blockID)
                {
                    distance = i + 1;
                    break;
                }
            ent.setLocationAndAngles(x + 1.75, distance, z + 0.5, 90, ent.rotationPitch);
            if (!ent.worldObj.isRemote)
                ent.setVelocity(0.0D, 0.0D, 0.0D);
        }
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
    {
        float f = 0.125F;
        return AxisAlignedBB.getBoundingBox(par2 - 1, par3 - 1, par4 - 1, par2 + 1, par3 + 1 - f, par4 + 1);
    }

    private Icon tr;
    private Icon tc;
    private Icon tl;
    private Icon cr;
    private Icon cc;
    private Icon cl;
    private Icon cl2;
    private Icon br;
    private Icon bc;
    private Icon bl;
    private Icon bottomSide;
    private Icon extension;

    private Icon[][] topIcons = new Icon[3][3];

    @Override
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.tr = par1IconRegister.registerIcon("lyoko:tr");
        this.tc = par1IconRegister.registerIcon("lyoko:tc");
        this.tl = par1IconRegister.registerIcon("lyoko:tl");
        this.cr = par1IconRegister.registerIcon("lyoko:cr");
        this.cc = this.blockIcon = par1IconRegister.registerIcon("lyoko:cc");
        this.cl = par1IconRegister.registerIcon("lyoko:cl");
        this.cl2 = par1IconRegister.registerIcon("lyoko:cl2");
        this.br = par1IconRegister.registerIcon("lyoko:br");
        this.bc = par1IconRegister.registerIcon("lyoko:bc");
        this.bl = par1IconRegister.registerIcon("lyoko:bl");
        this.bottomSide = par1IconRegister.registerIcon("lyoko:computer_0");
        this.extension = par1IconRegister.registerIcon("lyoko:extension");

        this.topIcons[0][0] = this.tr;
        this.topIcons[0][1] = this.tc;
        this.topIcons[0][2] = this.tl;
        this.topIcons[1][0] = this.cr;
        this.topIcons[1][1] = this.cc;
        this.topIcons[1][2] = this.cl;
        this.topIcons[2][0] = this.br;
        this.topIcons[2][1] = this.bc;
        this.topIcons[2][2] = this.bl;
    }

    @Override
    public Icon getBlockTexture(IBlockAccess access, int x, int y, int z, int side)
    {
        if (side == 1)
        {
            for (int i = 0; i < 3; i++)
                for (int j = 0; j < 3; j++)
                    if (this.isMultiBlock(access, x - i + 1, y, z - j + 1))
                    {
                        if (j == 1 && i == 2)
                            if (access.getBlockId(x + 1, y, z) == ModBlocks.TowerFloor.blockID && access.getBlockId(x + 2, y, z) == ModBlocks.TowerFloor.blockID && access.getBlockMetadata(x - 1, y, z) == 0)
                                return this.cl2;
                        return this.topIcons[j][i];
                    }
            if (this.isMultiBlock(access, x - 2, y, z) || this.isMultiBlock(access, x - 3, y, z))
                return this.extension;
        }
        return this.bottomSide;
    }
}