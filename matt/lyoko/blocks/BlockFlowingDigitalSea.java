package matt.lyoko.blocks;

import java.util.Random;

import matt.lyoko.CodeLyoko;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFlowing;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.liquids.ILiquid;

public class BlockFlowingDigitalSea extends BlockFlowing implements ILiquid {

	int numAdjacentSources = 0;
	boolean isOptimalFlowDirection[] = new boolean[4];
	int flowCost[] = new int[4];

	public BlockFlowingDigitalSea(int i, Material material) {
		super(i, material);
		this.setCreativeTab(CodeLyoko.LyokoTabs);
		setHardness(100F);
		setLightOpacity(3);
	}
	
	public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity par5Entity)
    {
        par5Entity.attackEntityFrom(DamageSource.generic, 100);
    }
	
	public int colorMultiplier(IBlockAccess iblockaccess, int i, int j, int k)
    {
    	return 0x0000FF;
    }

	@Override
	public int getRenderType() {
		return 4;
	}
	
	private void updateFlow(World world, int par2, int par3, int par4)
    {
        int l = world.getBlockMetadata(par2, par3, par4);
        world.setBlock(par2, par3, par4, this.blockID + 1, l, 2);
    }

	@Override
	public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
        int l = this.getFlowDecay(par1World, par2, par3, par4);
        byte b0 = 1;

        boolean flag = true;
        int i1;

        if (l > 0)
        {
            byte b1 = -100;
            this.numAdjacentSources = 0;
            int j1 = this.getSmallestFlowDecay(par1World, par2 - 1, par3, par4, b1);
            j1 = this.getSmallestFlowDecay(par1World, par2 + 1, par3, par4, j1);
            j1 = this.getSmallestFlowDecay(par1World, par2, par3, par4 - 1, j1);
            j1 = this.getSmallestFlowDecay(par1World, par2, par3, par4 + 1, j1);
            i1 = j1 + b0;

            if (i1 >= 8 || j1 < 0)
            {
                i1 = -1;
            }

            if (this.getFlowDecay(par1World, par2, par3 + 1, par4) >= 0)
            {
                int k1 = this.getFlowDecay(par1World, par2, par3 + 1, par4);

                if (k1 >= 8)
                {
                    i1 = k1;
                }
                else
                {
                    i1 = k1 + 8;
                }
            }

            if (this.numAdjacentSources >= 2 && this.blockMaterial == Material.water)
            {
                if (par1World.getBlockMaterial(par2, par3 - 1, par4).isSolid())
                {
                    i1 = 0;
                }
                else if (par1World.getBlockMaterial(par2, par3 - 1, par4) == this.blockMaterial && par1World.getBlockMetadata(par2, par3 - 1, par4) == 0)
                {
                    i1 = 0;
                }
            }
            
            if (i1 == l)
            {
                if (flag)
                {
                    this.updateFlow(par1World, par2, par3, par4);
                }
            }
            else
            {
                l = i1;

                if (i1 < 0)
                {
                    par1World.setBlockToAir(par2, par3, par4);
                }
                else
                {
                    par1World.setBlockMetadataWithNotify(par2, par3, par4, i1, 2);
                    par1World.scheduleBlockUpdate(par2, par3, par4, this.blockID, this.tickRate(par1World));
                    par1World.notifyBlocksOfNeighborChange(par2, par3, par4, this.blockID);
                }
            }
        }
        else
        {
            this.updateFlow(par1World, par2, par3, par4);
        }

        if (this.liquidCanDisplaceBlock(par1World, par2, par3 - 1, par4))
        {
            if (l >= 8)
            {
                this.flowIntoBlock(par1World, par2, par3 - 1, par4, l);
            }
            else
            {
                this.flowIntoBlock(par1World, par2, par3 - 1, par4, l + 8);
            }
        }
        else if (l >= 0 && (l == 0 || this.blockBlocksFlow(par1World, par2, par3 - 1, par4)))
        {
            boolean[] aboolean = this.getOptimalFlowDirections(par1World, par2, par3, par4);
            i1 = l + b0;

            if (l >= 8)
            {
                i1 = 1;
            }

            if (i1 >= 8)
            {
                return;
            }

            if (aboolean[0])
            {
                this.flowIntoBlock(par1World, par2 - 1, par3, par4, i1);
            }

            if (aboolean[1])
            {
                this.flowIntoBlock(par1World, par2 + 1, par3, par4, i1);
            }

            if (aboolean[2])
            {
                this.flowIntoBlock(par1World, par2, par3, par4 - 1, i1);
            }

            if (aboolean[3])
            {
                this.flowIntoBlock(par1World, par2, par3, par4 + 1, i1);
            }
        }
    }

	private void flowIntoBlock(World par1World, int par2, int par3, int par4, int par5)
    {
        if (this.liquidCanDisplaceBlock(par1World, par2, par3, par4))
        {
            int i1 = par1World.getBlockId(par2, par3, par4);

            if (i1 > 0)
            {
                if (this.blockMaterial == Material.lava)
                {
                    this.triggerLavaMixEffects(par1World, par2, par3, par4);
                }
                else
                {
                    Block.blocksList[i1].dropBlockAsItem(par1World, par2, par3, par4, par1World.getBlockMetadata(par2, par3, par4), 0);
                }
            }

            par1World.setBlock(par2, par3, par4, this.blockID, par5, 3);
        }
    }

	private int calculateFlowCost(World world, int i, int j, int k, int l, int i1) {
		int j1 = 1000;
		for (int k1 = 0; k1 < 4; k1++) {
			if (k1 == 0 && i1 == 1 || k1 == 1 && i1 == 0 || k1 == 2 && i1 == 3 || k1 == 3 && i1 == 2) {
				continue;
			}
			int l1 = i;
			int i2 = j;
			int j2 = k;
			if (k1 == 0) {
				l1--;
			}
			if (k1 == 1) {
				l1++;
			}
			if (k1 == 2) {
				j2--;
			}
			if (k1 == 3) {
				j2++;
			}
			if (blockBlocksFlow(world, l1, i2, j2) || world.getBlockMaterial(l1, i2, j2) == blockMaterial && world.getBlockMetadata(l1, i2, j2) == 0) {
				continue;
			}
			if (!blockBlocksFlow(world, l1, i2 - 1, j2))
				return l;
			if (l >= 4) {
				continue;
			}
			int k2 = calculateFlowCost(world, l1, i2, j2, l + 1, k1);
			if (k2 < j1) {
				j1 = k2;
			}
		}

		return j1;
	}

	private boolean[] getOptimalFlowDirections(World world, int i, int j, int k) {
		for (int l = 0; l < 4; l++) {
			flowCost[l] = 1000;
			int j1 = i;
			int i2 = j;
			int j2 = k;
			if (l == 0) {
				j1--;
			}
			if (l == 1) {
				j1++;
			}
			if (l == 2) {
				j2--;
			}
			if (l == 3) {
				j2++;
			}
			if (blockBlocksFlow(world, j1, i2, j2) || world.getBlockMaterial(j1, i2, j2) == blockMaterial && world.getBlockMetadata(j1, i2, j2) == 0) {
				continue;
			}
			if (!blockBlocksFlow(world, j1, i2 - 1, j2)) {
				flowCost[l] = 0;
			} else {
				flowCost[l] = calculateFlowCost(world, j1, i2, j2, 1, l);
			}
		}

		int i1 = flowCost[0];
		for (int k1 = 1; k1 < 4; k1++) {
			if (flowCost[k1] < i1) {
				i1 = flowCost[k1];
			}
		}

		for (int l1 = 0; l1 < 4; l1++) {
			isOptimalFlowDirection[l1] = flowCost[l1] == i1;
		}

		return isOptimalFlowDirection;
	}

	private boolean blockBlocksFlow(World world, int i, int j, int k) {
		int l = world.getBlockId(i, j, k);
		if (l == Block.doorWood.blockID || l == Block.doorIron.blockID || l == Block.signPost.blockID || l == Block.ladder.blockID || l == Block.reed.blockID)
			return true;
		if (l == 0)
			return false;
		Material material = Block.blocksList[l].blockMaterial;
		return material.isSolid();
	}

	protected int getSmallestFlowDecay(World world, int i, int j, int k, int l) {
		int i1 = getFlowDecay(world, i, j, k);
		if (i1 < 0)
			return l;
		if (i1 >= 8) {
			i1 = 0;
		}
		return l >= 0 && i1 >= l ? l : i1;
	}

	private boolean liquidCanDisplaceBlock(World world, int i, int j, int k) {
		Material material = world.getBlockMaterial(i, j, k);
		if (material == blockMaterial)
			return false;
		else
			return !blockBlocksFlow(world, i, j, k);
	}

	@Override
	public void onBlockAdded(World world, int i, int j, int k) {
		super.onBlockAdded(world, i, j, k);
		if (world.getBlockId(i, j, k) == blockID) {
			world.scheduleBlockUpdate(i, j, k, blockID, tickRate(world));
		}
	}

	@Override
	public int stillLiquidId() {
		return CodeLyoko.DigitalSeaStill.blockID;
	}

	@Override
	public boolean isMetaSensitive() {
		return false;
	}

	@Override
	public int stillLiquidMeta() {
		return 0;
	}

	@Override
	public boolean isBlockReplaceable(World world, int i, int j, int k) {
		return true;
	}

}