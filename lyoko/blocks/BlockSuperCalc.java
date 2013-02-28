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
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import matt.lyoko.*;
import matt.lyoko.entities.*;

public class BlockSuperCalc extends BlockContainer {
	
        public BlockSuperCalc (int id, int textureIndex) {
                super(id, Material.iron);
        		this.setCreativeTab(CodeLyoko.LyokoTabs);
        		this.blockIndexInTexture = textureIndex;
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
        
        public int getBlockTexture(IBlockAccess access, int x, int y, int z, int side)
        {
        	//case 2: //right
        	//case 3: //left
        	//case 4: //front
        	//case 5: //back
        	World world = access.getBlockTileEntity(x, y, z).worldObj;
        	if(side == 0 || side == 1)
        	{
        		return 11;
        	}
        	if(isMultiBlock(world, x+1, y, z+1))
        	{
        		switch(side)
        		{
        		case 2: return 27; //right
        		case 4: return 16; //front
        		}
        	}
        	else if(isMultiBlock(world, x+1, y, z))
        	{
        		switch(side)
        		{
        		case 4: return 17; //front
        		}
        	}
        	else if(isMultiBlock(world, x+1, y, z-1))
        	{
        		switch(side)
        		{
        		case 3: return 19; //left
        		case 4: return 18; //front
        		}
        	}
        	else if(isMultiBlock(world, x, y, z+1))
        	{
        		switch(side)
        		{
        		case 2: return 26; //right
        		}
        	}
        	else if(isMultiBlock(world, x, y, z-1))
        	{
        		switch(side)
        		{
        		case 3: return 20; //left
        		}
        	}
        	else if(isMultiBlock(world, x-1, y, z+1))
        	{
        		switch(side)
        		{
        		case 2: return 25; //right
        		case 5: return 24; //back
        		}
        	}
        	else if(isMultiBlock(world, x-1, y, z))
        	{
        		switch(side)
        		{
        		case 5: return 23; //back
        		}
        	}
        	else if(isMultiBlock(world, x-1, y, z-1))
        	{
        		switch(side)
        		{
        		case 3: return 21; //left
        		case 5: return 22; //back
        		}
        	}
        	else if(isMultiBlock(world, x, y-1, z))
        	{
        		return 28;
        	}
        	else if(isMultiBlock(world, x, y-2, z))
        	{
        		return 12;
        	}
        	return this.blockIndexInTexture;
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
        }
        
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
                                        entityItem.getEntityItem().setTagCompound((NBTTagCompound) item.getTagCompound().copy());
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
        }
}