/*
 * Code Lyoko Mod for Minecraft ${version}
 * Copyright 2014 Cortex Modders, Matthew Warren, Jacob Rhoda, and other contributors.
 * Released under the MIT license http://opensource.org/licenses/MIT
 */
package net.cortexmodders.lyoko.blocks;

import net.cortexmodders.lyoko.CodeLyoko;
import net.cortexmodders.lyoko.tileentity.TileEntityCable;
import net.cortexmodders.lyoko.tileentity.TileEntityHolomap;
import net.cortexmodders.lyoko.tileentity.TileEntityScanner;
import net.cortexmodders.lyoko.tileentity.TileEntitySuperCalc;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockCable extends BlockContainer
{
    public BlockCable()
    {
        super(Material.cloth);
        this.setCreativeTab(CodeLyoko.LyokoTabs);
    }
    
    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block neighborBlock)
    {
        if (world.getTileEntity(x, y, z) instanceof TileEntityCable)
        {
            TileEntityCable tile = (TileEntityCable) world.getTileEntity(x, y, z);
            if (neighborBlock instanceof BlockCable && tile != null)
            {
                String test1 = tile.getSector();
                this.syncBlock(world, x + 1, y, z, tile);
                this.syncBlock(world, x - 1, y, z, tile);
                this.syncBlock(world, x, y + 1, z, tile);
                this.syncBlock(world, x, y - 1, z, tile);
                this.syncBlock(world, x, y, z + 1, tile);
                this.syncBlock(world, x, y, z - 1, tile);
                String test2 = tile.getSector();
                
                if (!test1.equals(test2))
                    world.notifyBlocksOfNeighborChange(x, y, z, this);
                this.syncBlock2(world, x + 1, y, z, tile);
                this.syncBlock2(world, x - 1, y, z, tile);
                this.syncBlock2(world, x, y + 1, z, tile);
                this.syncBlock2(world, x, y - 1, z, tile);
                this.syncBlock2(world, x, y, z + 1, tile);
                this.syncBlock2(world, x, y, z - 1, tile);
                this.syncBlock3(world, x + 1, y, z, tile);
                this.syncBlock3(world, x - 1, y, z, tile);
                this.syncBlock3(world, x, y + 1, z, tile);
                this.syncBlock3(world, x, y - 1, z, tile);
                this.syncBlock3(world, x, y, z + 1, tile);
                this.syncBlock3(world, x, y, z - 1, tile);
                this.syncBlock4(world, x + 1, y, z, tile);
                this.syncBlock4(world, x - 1, y, z, tile);
                this.syncBlock4(world, x, y + 1, z, tile);
                this.syncBlock4(world, x, y - 1, z, tile);
                this.syncBlock4(world, x, y, z + 1, tile);
                this.syncBlock4(world, x, y, z - 1, tile);
            }
        }
    }
    
    public void syncBlock(World world, int x, int y, int z, TileEntityCable localCable)
    {
        if (world.getBlock(x, y, z) instanceof BlockCable && world.getTileEntity(x, y, z) instanceof TileEntityCable)
        {
            TileEntityCable tile = (TileEntityCable) world.getTileEntity(x, y, z);
            if (tile != null && localCable.getCoolDown() == 0 && !tile.getSector().equals("") && localCable.getSector().equals(""))
            {
                localCable.resetCoolDown();
                localCable.setSector(tile.getSector());
            }
        }
    }
    
    public void syncBlock2(World world, int x, int y, int z, TileEntityCable localCable)
    {
        if (world.getBlock(x, y, z) instanceof BlockScanner && world.getTileEntity(x, y, z) instanceof TileEntityScanner)
        {
            TileEntityScanner tile = (TileEntityScanner) world.getTileEntity(x, y, z);
            if (tile != null && !localCable.getSector().equals("") && tile.sector == -1)
            {
                tile.sector = this.convertSectorToInt(localCable.getSector());
                world.markBlockForUpdate(x, y, z);
            }
        }
    }
    
    public void syncBlock3(World world, int x, int y, int z, TileEntityCable localCable)
    {
        if (world.getBlock(x, y, z) instanceof BlockSuperCalc && world.getTileEntity(x, y, z) instanceof TileEntitySuperCalc)
        {
            TileEntitySuperCalc tile = (TileEntitySuperCalc) world.getTileEntity(x, y, z);
            if (tile != null && !localCable.getSector().equals("") && tile.sector.equals(""))
            {
                tile.sector = localCable.getSector();
                world.markBlockForUpdate(x, y, z);
            }
        }
    }
    
    public void syncBlock4(World world, int x, int y, int z, TileEntityCable localCable)
    {
        if (world.getBlock(x, y, z) instanceof BlockHolomap && world.getTileEntity(x, y, z) instanceof TileEntityHolomap)
        {
            TileEntityHolomap tile = (TileEntityHolomap) world.getTileEntity(x, y, z);
            if (tile != null && !localCable.getSector().equals(""))
            {
                tile.sector = (byte) (this.convertSectorToInt(localCable.getSector()) + 1);
                world.markBlockForUpdate(x, y, z);
            }
        }
    }
    
    public int convertSectorToInt(String sector)
    {
        if (sector.toLowerCase().equals("polar") || sector.toLowerCase().equals("ice"))
            return 0;
        else if (sector.toLowerCase().equals("desert"))
            return 1;
        else if (sector.toLowerCase().equals("forest"))
            return 2;
        else if (sector.toLowerCase().equals("mountain"))
            return 3;
        else if (sector.toLowerCase().equals("carthage"))
            return 4;
        return -1;
    }
    
    /*
     * public String convertIntToSector(int sector) { switch(sector) { case 0:
     * return "polar"; case 1: return "desert"; case 2: return "forest"; case 3:
     * return "mountain"; case 4: return "carthage"; default: return ""; } }
     * @Override public boolean onBlockActivated(World world, int x, int y, int
     * z, EntityPlayer player, int par6, float par7, float par8, float par9) {
     * if(!world.isRemote) { Random rand = new Random(); String temp =
     * convertIntToSector(rand.nextInt(5));
     * ((TileEntityCable)world.getBlockTileEntity(x, y, z)).setSector(temp);
     * ((TileEntityCable)world.getBlockTileEntity(x, y, z)).resetCoolDown();
     * world.markBlockForUpdate(x, y, z); world.notifyBlocksOfNeighborChange(x,
     * y, z, this.blockID); return true; } return false; }
     */
    
    @Override
    public int getRenderBlockPass()
    {
        return 1;
    }
    
    @Override
    public int getRenderType()
    {
        return -1;
    }
    
    @Override
    public void registerBlockIcons(IIconRegister par1IconRegister)
    {
        this.blockIcon = par1IconRegister.registerIcon("lyoko:cable");
    }
    
    @Override
    public boolean isOpaqueCube()
    {
        return false;
        
    }
    
    private boolean validBlock(Block block, int side)
    {
        if (block instanceof BlockCable || block instanceof BlockSuperCalcConsole)
            return true;
        else if (block instanceof BlockSuperCalc && (side == 1 || side == 0))
            return true;
        else if (block instanceof BlockHolomap && side != 0)
            return true;
        else if (block instanceof BlockScanner && (side == 0 || side == 1))
            return true;
        return false;
    }
    
    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z)
    {
        this.setBlockBounds(0.3125F, 0.3125F, 0.3125F, 0.6875F, 0.6875F, 0.6875F);
        
        float minx = (float) this.minX;
        float maxx = (float) this.maxX;
        float miny = (float) this.minY;
        float maxy = (float) this.maxY;
        float minz = (float) this.minZ;
        float maxz = (float) this.maxZ;
        
        if (this.validBlock(world.getBlock(x - 1, y, z), 2))
            minx = 0;
        
        if (this.validBlock(world.getBlock(x + 1, y, z), 3))
            maxx = 1;
        
        if (this.validBlock(world.getBlock(x, y - 1, z), 0))
            miny = 0;
        
        if (this.validBlock(world.getBlock(x, y + 1, z), 1))
            maxy = 1;
        
        if (this.validBlock(world.getBlock(x, y, z - 1), 4))
            minz = 0;
        
        if (this.validBlock(world.getBlock(x, y, z + 1), 5))
            maxz = 1;
        
        this.setBlockBounds(minx, miny, minz, maxx, maxy, maxz);
    }
    
    @Override
    public TileEntity createNewTileEntity(World world, int metadata)
    {
        return new TileEntityCable();
    }
}
