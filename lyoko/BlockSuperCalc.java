package matt.lyoko;

import java.util.Random;

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

public class BlockSuperCalc extends BlockContainer {
	
        protected BlockSuperCalc (int id) {
                super(id, Material.iron);
        		this.setCreativeTab(CodeLyoko.LyokoTabs);
        		this.blockIndexInTexture = 0;
        		//this.blockIndexInTexture = 4;
        }

        @Override
        public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int idk, float what, float these, float are)
        {
        	TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
            if (tileEntity == null || player.isSneaking())
            {
            	return false;
            }
        //code to open gui explained later
        player.openGui(CodeLyoko.instance, 0, world, x, y, z);
                return true;
        }
        
        @Override
        public void breakBlock(World world, int x, int y, int z, int par5, int par6) {
                dropItems(world, x, y, z);
                super.breakBlock(world, x, y, z, par5, par6);
        }
        /*
        public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
        {
        	par2 = 1;
        	par3 = 1;
        	par4 = 1;
            return AxisAlignedBB.getAABBPool().addOrModifyAABBInPool((double)par2 + this.minX, (double)par3 + this.minY, (double)par4 + this.minZ, (double)par2 + this.maxX, (double)par3 + this.maxY, (double)par4 + this.maxZ);
        }
        */
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
        
        //private boolean cnte = false;
        
        @Override
        public TileEntity createNewTileEntity(World world) {
        	//if(cnte == true)
        	//{
        		return new TileEntitySuperCalc();
        	//}
        	//else
        	//{
        	//	return null;
        	//}
        }
        
        public String getTextureFile()
        {
        	return "/matt/lyoko/terrain/computer.png";
        	//return "/matt/lyoko/terrain/SuperCalculator.png";
        }
    	
    	//public boolean renderAsNormalBlock() {
    	//	return false;
    	//}
    	
    	public void updateTick(World par1World, int x, int y, int z, Random par5Random)
        {
    		if (par1World.getBlockId(x-1, y, z-1) == CodeLyoko.SuperCalc.blockID && par1World.getBlockId(x, y, z-1) == CodeLyoko.SuperCalc.blockID
    			&& par1World.getBlockId(x+1, y, z-1) == CodeLyoko.SuperCalc.blockID && par1World.getBlockId(x-1, y, z) == CodeLyoko.SuperCalc.blockID
    			&& par1World.getBlockId(x+1, y, z) == CodeLyoko.SuperCalc.blockID && par1World.getBlockId(x-1, y, z+1) == CodeLyoko.SuperCalc.blockID
    			&& par1World.getBlockId(x, y, z+1) == CodeLyoko.SuperCalc.blockID && par1World.getBlockId(x+1, y, z+1) == CodeLyoko.SuperCalc.blockID
    			&& par1World.getBlockId(x, y+1, z) == CodeLyoko.SuperCalc.blockID && par1World.getBlockId(x, y+2, z) == CodeLyoko.SuperCalc.blockID)
    		{
    			//cnte = true;
    			//this.createNewTileEntity(par1World);
    		}
        }
    	
    	//@Override
    	//public int idDropped(int par1, Random par2Random, int par3)
        //{
        //    return CodeLyoko.ItemSuperCalc.itemID;
        //}
    	
    	//public int getRenderType()
        //{
        //    return -1;
        //}
    	
    	//public boolean isOpaqueCube()
        //{
        //    return false;
        //}
}