/*
 * Code Lyoko Mod for Minecraft v@VERSION Copyright 2013 Cortex Modders, Matthew
 * Warren, Jacob Rhoda, and other contributors. Released under the MIT license
 * http://opensource.org/licenses/MIT
 */

package matt.lyoko.blocks;

import java.util.Random;

import matt.lyoko.CodeLyoko;
import matt.lyoko.entities.tileentity.TileEntityTower;
import matt.lyoko.items.ModItems;
import matt.lyoko.particles.LyokoParticleEffects;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockTower extends BlockContainer
{
    public BlockTower()
    {
        // Material.iron
        super(Material.field_151573_f);
        // setCreativeTab
        this.func_149647_a(CodeLyoko.LyokoTabs);
    }

    IIcon inside;

    @Override
    // registerBlockIcons
    public void func_149651_a(IIconRegister par1IconRegister)
    {
        // blockIcon
        this.field_149761_L = par1IconRegister.registerIcon("lyoko:tower");
        this.inside = par1IconRegister.registerIcon("lyoko:computer_0");
    }

    @Override
    public TileEntity func_149915_a(World var1, int metadata)
    {
        return new TileEntityTower();
    }

    @SideOnly(Side.CLIENT)
    @Override
    // getIcon
    public IIcon func_149691_a(int side, int meta)
    {
        if (side == 2 && meta == 0)
            return this.inside;
        else if (side == 3 && meta == 2)
            return this.inside;
        else if (side == 4 && meta == 3)
            return this.inside;
        else if (side == 5 && meta == 1)
            return this.inside;
        return this.field_149761_L;
    }

    @Override
    public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
    {
        // func_147438_o - getTileEntity
        TileEntityTower tet = (TileEntityTower) world.func_147438_o(x, y, z);

        if (!world.isRemote)
            if (player.getHeldItem() != null && player.getHeldItem().getItem() == ModItems.LaserArrow)
            {
                tet.owner = "reset";
                // func_147471_g - markBlockForUpdate
                world.func_147471_g(x, y, z);
                return true;
            }
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    // randomDisplayTick
    public void func_149734_b(World world, int x, int y, int z, Random random)
    {
        // func_147438_o - getTileEntity
        TileEntityTower tet = (TileEntityTower) world.func_147438_o(x, y, z);

        int meta = world.getBlockMetadata(x, y, z);

        Random worldRand = world.rand;
        // 1/16th of a block - one 'pixel'
        double pixelWidth = 0.0625D;

        for (int side = 0; side < 6; ++side)
        {
            double x2 = x + worldRand.nextFloat();
            double y2 = y + worldRand.nextFloat();
            double z2 = z + worldRand.nextFloat();

            if (side == 0 && !world.func_147439_a(x, y + 1, z).func_149662_c())
                y2 = y + 1 + pixelWidth;

            if (side == 1 && !world.func_147439_a(x, y - 1, z).func_149662_c())
                y2 = y + 0 - pixelWidth;

            if (side == 2 && !world.func_147439_a(x, y, z + 1).func_149662_c() && meta != 2)
                z2 = z + 1 + pixelWidth;

            if (side == 3 && !world.func_147439_a(x, y, z - 1).func_149662_c() && meta != 0)
                z2 = z + 0 - pixelWidth;

            if (side == 4 && !world.func_147439_a(x + 1, y, z).func_149662_c() && meta != 1)
                x2 = x + 1 + pixelWidth;

            if (side == 5 && !world.func_147439_a(x - 1, y, z).func_149662_c() && meta != 3)
                x2 = x + 0 - pixelWidth;

            if (x2 < x || x2 > x + 1 || y2 < 0.0D || y2 > y + 1 || z2 < z || z2 > z + 1)
                if (tet.owner.equals("xana"))
                    LyokoParticleEffects.spawnParticle("xana", x2, y2, z2, 0.0D, 0.0D, 0.0D);
                else if (tet.owner.equals("lyoko"))
                    LyokoParticleEffects.spawnParticle("lyoko", x2, y2, z2, 0.0D, 0.0D, 0.0D);
                else if (tet.owner.equals("none"))
                    LyokoParticleEffects.spawnParticle("deactivated", x2, y2, z2, 0.0D, 0.0D, 0.0D);
                else if (tet.owner.equals("developer"))
                    LyokoParticleEffects.spawnParticle("dev", x2, y2, z2, 0.0D, 0.0D, 0.0D);
        }
    }

    @Override
    // onBlockPlacedBy
    public void func_149689_a(World par1World, int x, int y, int z, EntityLivingBase par5EntityLiving, ItemStack par6ItemStack)
    {
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