package matt.lyoko;

import com.jadarstudios.api.DeveloperCapesAPI.DeveloperCapesAPI;

import net.minecraftforge.client.MinecraftForgeClient;
import cpw.mods.fml.client.registry.*;
import cpw.mods.fml.common.Mod.Instance;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.item.ItemStack;
import net.minecraft.src.*;
import matt.lyoko.entities.mobs.*;
import matt.lyoko.render.mobs.*;
import matt.lyoko.model.mobs.*;
import matt.lyoko.entities.*;
import matt.lyoko.render.*;
import matt.lyoko.model.*;
import matt.lyoko.entities.vehicles.*;
import matt.lyoko.render.vehicles.*;
import matt.lyoko.model.vehicles.*;

public class ClientProxy extends CommonProxy
{
	@Override
	public void registerRenderInformation() 
	{  
		MinecraftForgeClient.preloadTexture("/matt/lyoko/gui/items.png");
		MinecraftForgeClient.preloadTexture("/matt/lyoko/terrain/terrain.png");
		MinecraftForgeClient.preloadTexture("/matt/lyoko/terrain/computer.png");
		
		RenderingRegistry.addNewArmourRendererPrefix("/matt/lyoko/armor/aelita_1");
		RenderingRegistry.addNewArmourRendererPrefix("/matt/lyoko/armor/aelita_2");
		RenderingRegistry.addNewArmourRendererPrefix("/matt/lyoko/armor/odd_1");
		RenderingRegistry.addNewArmourRendererPrefix("/matt/lyoko/armor/odd_2");
		RenderingRegistry.addNewArmourRendererPrefix("/matt/lyoko/armor/ulrich_1");
		RenderingRegistry.addNewArmourRendererPrefix("/matt/lyoko/armor/ulrich_2");
		RenderingRegistry.addNewArmourRendererPrefix("/matt/lyoko/armor/yumi_1");
		RenderingRegistry.addNewArmourRendererPrefix("/matt/lyoko/armor/yumi_2");
		RenderingRegistry.addNewArmourRendererPrefix("/matt/lyoko/armor/william_1");
		RenderingRegistry.addNewArmourRendererPrefix("/matt/lyoko/armor/william_2");
		
		//RenderingRegistry.instance().registerEntityRenderingHandler(EntityTest.class, new RenderCatTest());
		RenderingRegistry.registerEntityRenderingHandler(EntityFan.class, new RenderFan());
		RenderingRegistry.registerEntityRenderingHandler(EntityEnergyField.class, new RenderEnergyField());
		RenderingRegistry.registerEntityRenderingHandler(EntityLaserArrow.class, new RenderLaserArrow(5));
		RenderingRegistry.registerEntityRenderingHandler(EntityBlok.class, new RenderBlok(new ModelBlok(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityMegaTank.class, new RenderTank(new ModelTank(), 0.5F));
		/*
		RenderingRegistry.registerEntityRenderingHandler(EntityHornet.class, new RenderHornet(new ModelHornet(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityKankrelat.class, new RenderKankrelat(new ModelKankrelat(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityKrab.class, new RenderKrab(new ModelKrab(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityLyokoCreeper.class, new RenderLyokoCreeper(new ModelLyokoCreeper(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityManta.class, new RenderManta(new ModelManta(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityTarantula.class, new RenderTarantula(new ModelTarantula(), 0.5F));
		*/
		RenderingRegistry.registerEntityRenderingHandler(EntityLaser.class, new RenderLaser(new ModelLaser(), 0.5F));
		
		// Vehicles
		RenderingRegistry.registerEntityRenderingHandler(EntitySkid.class, new RenderSkid(new ModelSkid(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityOverboard.class, new RenderOverboard(new ModelOverboard(), 0.5F));
		
		//ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySuperCalc.class, new RenderSuperCalc());
		
		// Init capes. make new file called lyokocapes.txt in dropbox, with the template of this: https://github.com/jadar/DeveloperCapesAPI/blob/master/SampleCape.txt
		DeveloperCapesAPI.init("https://dl.dropbox.com/u/87762025/lyokocapes.txt");
	}
	
	public void registerKeyBindingHandler()
	{
		KeyBindingRegistry.registerKeyBinding(new KeyBindingHandler());
	}

	//override any other methods that need to be handled differently client side.

}