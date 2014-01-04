/*
 * Code Lyoko Mod for Minecraft v@VERSION Copyright 2013 Cortex Modders, Matthew
 * Warren, Jacob Rhoda, and other contributors. Released under the MIT license
 * http://opensource.org/licenses/MIT
 */

package matt.lyoko.blocks;

import matt.lyoko.CodeLyoko;
import matt.lyoko.entities.tileentity.TileEntityCable;
import matt.lyoko.entities.tileentity.TileEntityHolomap;
import matt.lyoko.entities.tileentity.TileEntityScanner;
import matt.lyoko.entities.tileentity.TileEntitySuperCalc;
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
        // Material.cloth
        super(Material.field_151580_n);
        // setCreativeTab
        this.func_149647_a(CodeLyoko.LyokoTabs);
    }

    @Override
    // onNeighborBlockChange
    public void func_149695_a(World world, int x, int y, int z, Block neighborBlock)
    {
        // world.func_147438_o - world.getTileEntity
        if (world.func_147438_o(x, y, z) instanceof TileEntityCable)
        {
            TileEntityCable tile = (TileEntityCable) world.func_147438_o(x, y, z);
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

                // func_147459_d - notifyBlocksOfNeighborChange
                if (!test1.equals(test2))
                    world.func_147459_d(x, y, z, this);
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
        // func_147439_a - getBlock | func_147438_o - getTileEntity
        if (world.func_147439_a(x, y, z) instanceof BlockCable && world.func_147438_o(x, y, z) instanceof TileEntityCable)
        {
            TileEntityCable tile = (TileEntityCable) world.func_147438_o(x, y, z);
            if (tile != null && localCable.getCoolDown() == 0 && !tile.getSector().equals("") && localCable.getSector().equals(""))
            {
                localCable.resetCoolDown();
                localCable.setSector(tile.getSector());
            }
        }
    }

    public void syncBlock2(World world, int x, int y, int z, TileEntityCable localCable)
    {
        // func_147439_a - getBlock
        if (world.func_147439_a(x, y, z) instanceof BlockScanner && world.func_147438_o(x, y, z) instanceof TileEntityScanner)
        {
            TileEntityScanner tile = (TileEntityScanner) world.func_147438_o(x, y, z);
            if (tile != null && !localCable.getSector().equals("") && tile.sector == -1)
            {
                tile.sector = this.convertSectorToInt(localCable.getSector());
                // func_147471_g - markBlockForUpdate
                world.func_147471_g(x, y, z);
            }
        }
    }

    public void syncBlock3(World world, int x, int y, int z, TileEntityCable localCable)
    {
        if (world.func_147439_a(x, y, z) instanceof BlockSuperCalc && world.func_147438_o(x, y, z) instanceof TileEntitySuperCalc)
        {
            TileEntitySuperCalc tile = (TileEntitySuperCalc) world.func_147438_o(x, y, z);
            if (tile != null && !localCable.getSector().equals("") && tile.sector.equals(""))
            {
                tile.sector = localCable.getSector();
                // func_147471_g - markBlockForUpdate
                world.func_147471_g(x, y, z);
            }
        }
    }

    public void syncBlock4(World world, int x, int y, int z, TileEntityCable localCable)
    {
        if (world.func_147439_a(x, y, z) instanceof BlockHolomap && world.func_147438_o(x, y, z) instanceof TileEntityHolomap)
        {
            TileEntityHolomap tile = (TileEntityHolomap) world.func_147438_o(x, y, z);
            if (tile != null && !localCable.getSector().equals(""))
            {
                tile.sector = (byte) (this.convertSectorToInt(localCable.getSector()) + 1);
                // func_147471_g - markBlockForUpdate
                world.func_147471_g(x, y, z);
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
    // getRenderBlockPass
    public int func_149701_w()
    {
        return 1;
    }

    @Override
    // getRenderType
    public int func_149645_b()
    {
        return -1;
    }

    @Override
    // registerBlockIcons
    public void func_149651_a(IIconRegister par1IconRegister)
    {
        // blockIcon
        this.field_149761_L = par1IconRegister.registerIcon("lyoko:cable");
    }

    @Override
    // isOpaqueCube
    public boolean func_149662_c()
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
    // setBlockBoundsBasedOnState
    public void func_149719_a(IBlockAccess world, int x, int y, int z)
    {
        // func_149676_a - setBlockBounds
        this.func_149676_a(0.3125F, 0.3125F, 0.3125F, 0.6875F, 0.6875F, 0.6875F);

        /*
         * field_149759_B - blockMinX
         * field_149760_C - blockMinY
         * field_149754_D - blockMinZ
         * field_149755_E - blockMaxX
         * field_149756_F - blockMaxY
         * field_149757_G - blockMaxZ
         */
        float minx = (float) this.field_149759_B;
        float maxx = (float) this.field_149760_C;
        float miny = (float) this.field_149754_D;
        float maxy = (float) this.field_149755_E;
        float minz = (float) this.field_149756_F;
        float maxz = (float) this.field_149757_G;

        if (this.validBlock(world.func_147439_a(x - 1, y, z), 2))
            minx = 0;

        if (this.validBlock(world.func_147439_a(x + 1, y, z), 3))
            maxx = 1;

        if (this.validBlock(world.func_147439_a(x, y - 1, z), 0))
            miny = 0;

        if (this.validBlock(world.func_147439_a(x, y + 1, z), 1))
            maxy = 1;

        if (this.validBlock(world.func_147439_a(x, y, z - 1), 4))
            minz = 0;

        if (this.validBlock(world.func_147439_a(x, y, z + 1), 5))
            maxz = 1;

        this.func_149676_a(minx, miny, minz, maxx, maxy, maxz);
    }

    @Override
    // createNewTileEntity
    public TileEntity func_149915_a(World world, int metadata)
    {
        return new TileEntityCable();
    }
}