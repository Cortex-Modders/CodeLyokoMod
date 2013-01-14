package matt.lyoko;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.*;
import net.minecraft.src.*;
import net.minecraft.world.World;
import net.minecraftforge.common.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.*;

public class ArmorLyoko extends ItemArmor implements IArmorTextureProvider
{

        public ArmorLyoko(int i, EnumArmorMaterial enumarmormaterial, int j, int k, String str)
        {
                super(i, enumarmormaterial, j, k);
                this.setCreativeTab(CreativeTabs.tabMisc);
                this.armorType = str;
        }
        
        private String armorType;
        
        public String getTextureFile()
        {
                return "/matt/lyoko/gui/items.png";
        }
        
        public void onUpdate(ItemStack stack, World world, EntityPlayer ent, int par4, boolean par5, PlayerCapabilities plc)
        {
        	if(ent.getArmorItemForSlot(0, 0) == CodeLyoko.AelitaBoots && ent.getArmorItemForSlot(1, 0) == CodeLyoko.AelitaHelmet && ent.getArmorItemForSlot(1, 0) == CodeLyoko.AelitaLegs && ent.getArmorItemForSlot(1, 0) == CodeLyoko.AelitaChest)
        	{
        		plc.allowFlying = true;
        	}
        	else if(ent.getArmorItemForSlot(0, 0) == CodeLyoko.OddBoots && ent.getArmorItemForSlot(1, 0) == CodeLyoko.OddHelmet && ent.getArmorItemForSlot(1, 0) == CodeLyoko.OddLegs && ent.getArmorItemForSlot(1, 0) == CodeLyoko.OddChest)
        	{
        		plc.allowFlying = true;
        	}
        }
        
        public String getArmorTextureFile(ItemStack itemstack)
        {
        	if(armorType == "Aelita")
        	{
                if(itemstack.itemID == CodeLyoko.AelitaHelmet.itemID || itemstack.itemID == CodeLyoko.AelitaChest.itemID || itemstack.itemID == CodeLyoko.AelitaBoots.itemID)
                {
                	return "/matt/lyoko/armor/aelita_1.png";
                }
                if(itemstack.itemID == CodeLyoko.AelitaLegs.itemID)
                {
                	return "/matt/lyoko/armor/aelita_2.png";
                }
                return "/matt/lyoko/armor/aelita_1.png";
        	}
        	else if(armorType == "Odd")
        	{
                if(itemstack.itemID == CodeLyoko.OddHelmet.itemID || itemstack.itemID == CodeLyoko.OddChest.itemID || itemstack.itemID == CodeLyoko.OddBoots.itemID)
                {
                	return "/matt/lyoko/armor/odd_1.png";
                }
                if(itemstack.itemID == CodeLyoko.OddLegs.itemID)
                {
                	return "/matt/lyoko/armor/odd_2.png";
                }
                return "/matt/lyoko/armor/odd_1.png";
        	}
        	else if(armorType == "Ulrich")
        	{
                if(itemstack.itemID == CodeLyoko.UlrichHelmet.itemID || itemstack.itemID == CodeLyoko.UlrichChest.itemID || itemstack.itemID == CodeLyoko.UlrichBoots.itemID)
                {
                	return "/matt/lyoko/armor/ulrich_1.png";
                }
                if(itemstack.itemID == CodeLyoko.UlrichLegs.itemID)
                {
                	return "/matt/lyoko/armor/ulrich_2.png";
                }
                return "/matt/lyoko/armor/ulrich_1.png";
        	}
        	else if(armorType == "Yumi")
        	{
                if(itemstack.itemID == CodeLyoko.YumiHelmet.itemID || itemstack.itemID == CodeLyoko.YumiChest.itemID || itemstack.itemID == CodeLyoko.YumiBoots.itemID)
                {
                	return "/matt/lyoko/armor/yumi_1.png";
                }
                if(itemstack.itemID == CodeLyoko.YumiLegs.itemID)
                {
                	return "/matt/lyoko/armor/yumi_2.png";
                }
                return "/matt/lyoko/armor/yumi_1.png";
        	}
        	else if(armorType == "William")
        	{
                if(itemstack.itemID == CodeLyoko.WilliamHelmet.itemID || itemstack.itemID == CodeLyoko.WilliamChest.itemID || itemstack.itemID == CodeLyoko.WilliamBoots.itemID)
                {
                	return "/matt/lyoko/armor/william_1.png";
                }
                if(itemstack.itemID == CodeLyoko.WilliamLegs.itemID)
                {
                	return "/matt/lyoko/armor/william_2.png";
                }
                return "/matt/lyoko/armor/william_1.png";
        	}
        	else
        	{
        		return "/matt/lyoko/armor/aelita_1.png";
        	}
        }
}