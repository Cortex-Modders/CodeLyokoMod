package matt.lyoko;

import java.util.Random;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.oredict.OreDictionary;

public class EntityJoinWorldHandler extends EntityJoinWorldEvent {

	public EntityJoinWorldHandler(Entity entity, World world) {
		super(entity, world);
	}
	
	public void onEntityJoinWorld(World world, EntityItem enti)
	{
		if(OreDictionary.getOreName(enti.func_92014_d().itemID) == "blockStone")//"LEAD ORE DICTIONARY NAME")
		{
			Random rand = new Random();
			int x = rand.nextInt();
			if(x == 0)
			{
				enti.func_92013_a(new ItemStack(CodeLyoko.LeadOre));
			}
		}
	}
}
