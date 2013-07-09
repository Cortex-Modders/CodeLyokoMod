package matt.lyoko.entities.projectile;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.List;

import matt.lyoko.CodeLyoko;
import matt.lyoko.lib.PlayerInformation;
import net.minecraft.block.Block;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.enchantment.EnchantmentThorns;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.network.packet.Packet70GameEvent;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EntityLaser extends EntityLyokoRanged
{
    public EntityLaser(World par1World, EntityLivingBase par2EntityLiving, EntityLivingBase par3EntityLiving, float par4, float par5)
    {
		super(par1World, par2EntityLiving, par3EntityLiving, par4, par5);
	}
    
	public void onUpdate()
    {
        super.onUpdate();
        
        if(!worldObj.isRemote && CodeLyoko.entityInLyoko(this))
        {
        	this.setDamage(0.0D);
        }
        else
        {
        	this.setDamage(2.0D);
        }
    }
    
    public void onCollideWithPlayer(EntityPlayer player)
    {
    	super.onCollideWithPlayer(player);
    	
        if(!player.capabilities.isCreativeMode && !player.worldObj.isRemote)
        {
        	this.playSound("random.pop", 0.2F, ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
        	PlayerInformation pi = PlayerInformation.forPlayer(player);
        	pi.decreaseLifePoints(10);
        	
        	if(pi.dirty)
        	{
        		ByteArrayOutputStream bos = new ByteArrayOutputStream(4);
            	DataOutputStream outputStream = new DataOutputStream(bos);
            	try
            	{
            		outputStream.writeInt(pi.getLifePoints());
            	}
            	catch (Exception ex)
            	{
            		ex.printStackTrace();
            	}
            	
            	Packet250CustomPayload packet = new Packet250CustomPayload();
            	packet.channel = "LifePoints";
            	packet.data = bos.toByteArray();
            	packet.length = bos.size();
            	
            	PacketDispatcher.sendPacketToPlayer(packet,(Player) player);
        	}
        }
    }
}