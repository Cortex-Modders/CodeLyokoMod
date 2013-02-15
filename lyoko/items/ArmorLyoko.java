package matt.lyoko.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.*;
import net.minecraft.src.*;
import net.minecraft.world.World;
import net.minecraftforge.common.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.*;
import matt.lyoko.*;

public class ArmorLyoko extends ItemArmor implements IArmorTextureProvider
{

        public ArmorLyoko(int i, EnumArmorMaterial enumarmormaterial, int j, int k, String str)
        {
                super(i, enumarmormaterial, j, k);
                this.setCreativeTab(CodeLyoko.LyokoTabs);
                this.armorType = str;
                this.setNoRepair();
        }
        
        private String armorType;
        
        public String getTextureFile()
        {
                return "/matt/lyoko/gui/items.png";
        }
        
        public String getArmorTextureFile(ItemStack itemstack)
        {
        	if(itemstack.itemID == CodeLyoko.AelitaHelmet.itemID || itemstack.itemID == CodeLyoko.AelitaChest.itemID || itemstack.itemID == CodeLyoko.AelitaBoots.itemID
        			|| itemstack.itemID == CodeLyoko.OddHelmet.itemID || itemstack.itemID == CodeLyoko.OddChest.itemID || itemstack.itemID == CodeLyoko.OddBoots.itemID
        			|| itemstack.itemID == CodeLyoko.UlrichHelmet.itemID || itemstack.itemID == CodeLyoko.UlrichChest.itemID || itemstack.itemID == CodeLyoko.UlrichBoots.itemID
        			|| itemstack.itemID == CodeLyoko.YumiHelmet.itemID || itemstack.itemID == CodeLyoko.YumiChest.itemID || itemstack.itemID == CodeLyoko.YumiBoots.itemID
        			|| itemstack.itemID == CodeLyoko.WilliamHelmet.itemID || itemstack.itemID == CodeLyoko.WilliamChest.itemID || itemstack.itemID == CodeLyoko.WilliamBoots.itemID)
            {
            	return "/matt/lyoko/armor/" + this.armorType + "_1.png";
            }
            if(itemstack.itemID == CodeLyoko.AelitaLegs.itemID || itemstack.itemID == CodeLyoko.OddLegs.itemID
            		|| itemstack.itemID == CodeLyoko.UlrichLegs.itemID || itemstack.itemID == CodeLyoko.YumiLegs.itemID
            		|| itemstack.itemID == CodeLyoko.WilliamLegs.itemID)
            {
            	return "/matt/lyoko/armor/" + this.armorType + "_2.png";
            }
            return "/matt/lyoko/armor/" + this.armorType + "_1.png";
        }
}