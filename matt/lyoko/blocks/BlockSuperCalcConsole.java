package matt.lyoko.blocks;

import matt.lyoko.CodeLyoko;
import matt.lyoko.entities.tileentity.TileEntitySuperCalcConsole;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockSuperCalcConsole extends BlockContainer
{
    public BlockSuperCalcConsole(int par1)
    {
        super(par1, Material.iron);
        this.setCreativeTab(CodeLyoko.LyokoTabs);
    }
    
    @Override
    public TileEntity createNewTileEntity(World world)
    {
        return new TileEntitySuperCalcConsole();
    }
    
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
    {
        TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
        if (tileEntity == null || player.isSneaking())
        {
            return false;
        }
        else
        {
            player.openGui(CodeLyoko.instance, 2, world, x, y, z);
            return true;
        }
    }
}