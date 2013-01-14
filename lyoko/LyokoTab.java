package matt.lyoko;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class LyokoTab extends CreativeTabs {

	public LyokoTab(String par2Str) {
		super(par2Str);
		
	}
	
    public int getTabIconItemIndex()
    {
    	return CodeLyoko.Lyoko_Log;
                           
    }

    public String getTranslatedTabLabel()
    {
    	return "Lyoko";
    }

}
