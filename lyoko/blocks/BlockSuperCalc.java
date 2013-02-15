package matt.lyoko.blocks;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import matt.lyoko.*;
import matt.lyoko.entities.*;

public class BlockSuperCalc extends BlockContainer {
	
        public BlockSuperCalc (int id, int textureIndex) {
                super(id, Material.iron);
        		this.setCreativeTab(CodeLyoko.LyokoTabs);
        		this.blockIndexInTexture = textureIndex;
        		//this.setBlockBounds(-1.0F, 0F, -1.0F, 2.0F, 3.0F, 2.0F);
        }
        
        public boolean isMultiBlock(World world, int x, int y, int z)
        {
        	if(world.getBlockId(x, y+1, z) == CodeLyoko.SuperCalc.blockID
        			&& world.getBlockId(x, y+2, z) == CodeLyoko.SuperCalc.blockID
        			&& world.getBlockId(x+1, y, z+1) == CodeLyoko.SuperCalc.blockID
        			&& world.getBlockId(x+1, y, z) == CodeLyoko.SuperCalc.blockID
        			&& world.getBlockId(x+1, y, z-1) == CodeLyoko.SuperCalc.blockID
        			&& world.getBlockId(x, y, z+1) == CodeLyoko.SuperCalc.blockID
        			&& world.getBlockId(x, y, z) == CodeLyoko.SuperCalc.blockID
        			&& world.getBlockId(x, y, z-1) == CodeLyoko.SuperCalc.blockID
        			&& world.getBlockId(x-1, y, z+1) == CodeLyoko.SuperCalc.blockID
        			&& world.getBlockId(x-1, y, z) == CodeLyoko.SuperCalc.blockID
        			&& world.getBlockId(x-1, y, z-1) == CodeLyoko.SuperCalc.blockID)
        	{
        		return true;
        	}
        	return false;
        }
        
        @Override
        public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int idk, float what, float these, float are)
        {
        	TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
        	if (tileEntity == null || player.isSneaking())
        	{
        		return false;
        	}
        	else
        	{
        		for(int i = -1; i < 2; i++)
        		{
        			for(int j = -1; j < 2; j++)
        			{
        				if(this.isMultiBlock(world, x+i, y, z+j))
        				{
        					player.openGui(CodeLyoko.instance, 0, world, x+i, y, z+j);
        					return true;
        				}
        			}
        		}
        		if(this.isMultiBlock(world, x, y-1, z))
        		{
					player.openGui(CodeLyoko.instance, 0, world, x, y-1, z);
					return true;
				}
        		else if(this.isMultiBlock(world, x, y-2, z))
        		{
					player.openGui(CodeLyoko.instance, 0, world, x, y-2, z);
					return true;
				}
        		return false;
        	}
        }
        
        @Override
        public void breakBlock(World world, int x, int y, int z, int par5, int par6) {
                dropItems(world, x, y, z);
                super.breakBlock(world, x, y, z, par5, par6);
                /*world.setBlockWithNotify(x, y, z, 0);
    			world.setBlockWithNotify(x, y + 1, z, 0);
    			world.setBlockWithNotify(x, y + 2, z, 0);
    			world.setBlockWithNotify(x + 1, y, z + 1, 0);
    			world.setBlockWithNotify(x + 1, y, z, 0);
    			world.setBlockWithNotify(x + 1, y, z - 1, 0);
    			world.setBlockWithNotify(x, y, z + 1, 0);
    			world.setBlockWithNotify(x, y, z - 1, 0);
    			world.setBlockWithNotify(x - 1, y, z + 1, 0);
    			world.setBlockWithNotify(x - 1, y, z, 0);
    			world.setBlockWithNotify(x - 1, y, z - 1, 0);*/
        }
        
        //public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
        //{
        //    return AxisAlignedBB.getBoundingBox(par2 - 2, par3, par4 - 2, par2 + 3, par3 + 4, par4 + 3);
        //}
        /*
        public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
        {
        	par2 = 1;
        	par3 = 1;
        	par4 = 1;
            return AxisAlignedBB.getAABBPool().addOrModifyAABBInPool((double)par2 + this.minX, (double)par3 + this.minY, (double)par4 + this.minZ, (double)par2 + this.maxX, (double)par3 + this.maxY, (double)par4 + this.maxZ);
        }
        */
        /*@SideOnly(Side.CLIENT)
        
        public int idPicked(World par1World, int par2, int par3, int par4)
        {
            return CodeLyoko.ItemSuperCalc.itemID;
        }*/
        
        private void dropItems(World world, int x, int y, int z){
                Random rand = new Random();

                TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
                if (!(tileEntity instanceof IInventory)) {
                        return;
                }
                IInventory inventory = (IInventory) tileEntity;

                for (int i = 0; i < inventory.getSizeInventory(); i++) {
                        ItemStack item = inventory.getStackInSlot(i);

                        if (item != null && item.stackSize > 0) {
                                float rx = rand.nextFloat() * 0.8F + 0.1F;
                                float ry = rand.nextFloat() * 0.8F + 0.1F;
                                float rz = rand.nextFloat() * 0.8F + 0.1F;

                                EntityItem entityItem = new EntityItem(world,
                                                x + rx, y + ry, z + rz,
                                                new ItemStack(item.itemID, item.stackSize, item.getItemDamage()));

                                if (item.hasTagCompound()) {
                                        entityItem.func_92014_d().setTagCompound((NBTTagCompound) item.getTagCompound().copy());
                                }

                                float factor = 0.05F;
                                entityItem.motionX = rand.nextGaussian() * factor;
                                entityItem.motionY = rand.nextGaussian() * factor + 0.2F;
                                entityItem.motionZ = rand.nextGaussian() * factor;
                                world.spawnEntityInWorld(entityItem);
                                item.stackSize = 0;
                        }
                }
        }
        
        @Override
        public TileEntity createNewTileEntity(World world) {
        		return new TileEntitySuperCalc();
        }
        
        public String getTextureFile()
        {
        	return "/matt/lyoko/terrain/terrain.png";
        	//return "/matt/lyoko/terrain/computer.png";
        	//return "/matt/lyoko/terrain/SuperCalculator.png";
        }
    	
    	/*@Override
    	public int idDropped(int par1, Random par2Random, int par3)
        {
            return CodeLyoko.ItemSuperCalc.itemID;
        }

    	public boolean renderAsNormalBlock() {
    		return false;
    	}
    	
    	public int getRenderType()
        {
    		return CodeLyoko.SuperCalcRenderID;
        }
    	
    	public boolean isOpaqueCube()
        {
            return false;
        }*/
}