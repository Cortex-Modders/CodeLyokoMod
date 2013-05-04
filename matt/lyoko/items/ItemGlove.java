package matt.lyoko.items;

import matt.lyoko.entities.EntityLyokoRanged;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemGlove extends ItemLyokoRanged {

    public ItemGlove(int id, Class<? extends EntityLyokoRanged> c, Item item, String text)
    {
        super(id, c, item, text);
    }
    
//    @Override
//    public EnumAction getItemUseAction(ItemStack par1ItemStack)
//    {
//      return EnumAction.bow;
//    }
    
}
