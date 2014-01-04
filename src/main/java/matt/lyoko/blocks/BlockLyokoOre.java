package matt.lyoko.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class BlockLyokoOre extends BlockLyoko
{

    private final OreTypes type;

    public BlockLyokoOre(OreTypes parType)
    {
        // Material.rock
        super(Material.field_151576_e);

        this.type = parType;
    }

    @Override
    // onEntityWalking
    public void func_149724_b(World par1World, int par2, int par3, int par4, Entity par5Entity)
    {
        if (this.type == OreTypes.LEAD || this.type == OreTypes.URANIUM)
        {
            if (par5Entity instanceof EntityLiving)
            {
                if (((EntityLiving) par5Entity).isPotionActive(Potion.regeneration.getId()))
                    ((EntityLiving) par5Entity).removePotionEffect(Potion.regeneration.getId());
                ((EntityLiving) par5Entity).addPotionEffect(new PotionEffect(Potion.poison.getId(), 500, 2));
                if (par5Entity instanceof EntityPlayer)
                    ((EntityPlayer) par5Entity).addPotionEffect(new PotionEffect(Potion.hunger.getId(), 500, 2));
            }
        }

        else if (this.type == OreTypes.QUANTUM)
            if (par5Entity instanceof EntityLiving)
            {
                ((EntityLiving) par5Entity).clearActivePotions();
                ((EntityLiving) par5Entity).addPotionEffect(new PotionEffect(Potion.regeneration.getId(), 500, 2));
            }
    }

    public static enum OreTypes
    {
        URANIUM, LEAD, QUANTUM
    }
}
