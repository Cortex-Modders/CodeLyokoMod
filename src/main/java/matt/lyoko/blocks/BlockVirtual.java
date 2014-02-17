/*
 * Code Lyoko Mod for Minecraft v@VERSION Copyright 2013 Cortex Modders, Matthew
 * Warren, Jacob Rhoda, and other contributors. Released under the MIT license
 * http://opensource.org/licenses/MIT
 */

package matt.lyoko.blocks;

import matt.lyoko.CodeLyoko;
import matt.lyoko.lib.DimensionIds;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockVirtual extends Block
{
    public BlockVirtual()
    {
        // material.iron
        super(Material.iron);
        // setCreativeTab
        this.setCreativeTab(CodeLyoko.LyokoTabs);
    }

    @Override
    // onBlockAdded
    public void onBlockAdded(World world, int x, int y, int z)
    {
        super.onBlockAdded(world, x, y, z);
        if (world.provider.dimensionId != DimensionIds.ICE && world.provider.dimensionId != DimensionIds.MOUNTAIN && world.provider.dimensionId != DimensionIds.FOREST && world.provider.dimensionId != DimensionIds.DESERT && world.provider.dimensionId != DimensionIds.CARTHAGE)
            // setBlock
            world.setBlockToAir(x, y, z);
    }

    public void onBlockPlacedBy(World world, int par2, int par3, int par4, EntityLivingBase ent)
    {
        if (ent instanceof EntityPlayer)
        {
            EntityPlayer entp = (EntityPlayer) ent;
            entp.setHealth(entp.getHealth() - 1);
        }
    }

    private IIcon virtualGrass;
    private IIcon virtualStone;
    private IIcon virtualSand;
    private IIcon virtualIce;
    private IIcon virtualCarthage;

    @Override
    // getBlockTextures
    public IIcon getIcon(IBlockAccess access, int x, int y, int z, int side)
    {
        if (access.getBiomeGenForCoords(x, y).equals(CodeLyoko.lyokoforest))
            return this.virtualGrass;
        else if (access.getBiomeGenForCoords(x, y).equals(CodeLyoko.lyokomountain))
            return this.virtualStone;
        else if (access.getBiomeGenForCoords(x, y).equals(CodeLyoko.lyokodesert))
            return this.virtualSand;
        else if (access.getBiomeGenForCoords(x, y).equals(CodeLyoko.lyokopolar))
            return this.virtualIce;
        else if (access.getBiomeGenForCoords(x, y).equals(CodeLyoko.lyokocarthage))
            return this.virtualCarthage;
        return this.virtualGrass;
    }

    @Override
    // registerIcons
    public void registerBlockIcons(IIconRegister par1IconRegister)
    {
        this.virtualGrass = this.blockIcon = par1IconRegister.registerIcon("lyoko:lyokograss");
        this.virtualStone = par1IconRegister.registerIcon("lyoko:lyokostone");
        this.virtualSand = par1IconRegister.registerIcon("lyoko:lyokosand");
        this.virtualIce = par1IconRegister.registerIcon("lyoko:lyokoice");
        this.virtualCarthage = par1IconRegister.registerIcon("lyoko:carthage");
    }
}
