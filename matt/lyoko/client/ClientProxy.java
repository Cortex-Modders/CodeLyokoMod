package matt.lyoko.client;

import matt.lyoko.CommonProxy;
import matt.lyoko.entities.mobs.EntityBlok;
import matt.lyoko.entities.mobs.EntityMegaTank;
import matt.lyoko.entities.projectile.EntityEnergyField;
import matt.lyoko.entities.projectile.EntityFan;
import matt.lyoko.entities.projectile.EntityLaser;
import matt.lyoko.entities.projectile.EntityLaserArrow;
import matt.lyoko.entities.tileentity.TileEntityCable;
import matt.lyoko.entities.tileentity.TileEntityScanner;
import matt.lyoko.entities.tileentity.TileEntityTower;
import matt.lyoko.entities.tileentity.TileEntityTowerConsole;
import matt.lyoko.entities.vehicles.EntityOverboard;
import matt.lyoko.entities.vehicles.EntitySkid;
import matt.lyoko.handlers.KeyBindingHandler;
import matt.lyoko.items.ModItems;
import matt.lyoko.model.ModelLaser;
import matt.lyoko.model.mobs.ModelBlok;
import matt.lyoko.model.mobs.ModelTank;
import matt.lyoko.render.ItemRenderGlove;
import matt.lyoko.render.RenderEnergyField;
import matt.lyoko.render.RenderFan;
import matt.lyoko.render.RenderLaser;
import matt.lyoko.render.RenderLaserArrow;
import matt.lyoko.render.mobs.RenderBlok;
import matt.lyoko.render.mobs.RenderTank;
import matt.lyoko.render.tileentity.RenderCable;
import matt.lyoko.render.tileentity.RenderEntityTowerConsole;
import matt.lyoko.render.tileentity.RenderScanner;
import matt.lyoko.render.tileentity.RenderTower;
import matt.lyoko.render.vehicles.RenderOverboard;
import matt.lyoko.render.vehicles.RenderSkid;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;

import org.lwjgl.opengl.GL11;

import com.jadarstudios.api.developercapesapi.DeveloperCapesAPI;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy
{
	@Override
	public void registerRenderInformation() 
	{
		RenderingRegistry.addNewArmourRendererPrefix("/assets/lyoko/textures/armor/aelita_1");
		RenderingRegistry.addNewArmourRendererPrefix("/assets/lyoko/textures/armor/aelita_2");
		RenderingRegistry.addNewArmourRendererPrefix("/assets/lyoko/textures/armor/odd_1");
		RenderingRegistry.addNewArmourRendererPrefix("/assets/lyoko/textures/armor/odd_2");
		RenderingRegistry.addNewArmourRendererPrefix("/assets/lyoko/textures/armor/ulrich_1");
		RenderingRegistry.addNewArmourRendererPrefix("/assets/lyoko/textures/armor/ulrich_2");
		RenderingRegistry.addNewArmourRendererPrefix("/assets/lyoko/textures/armor/yumi_1");
		RenderingRegistry.addNewArmourRendererPrefix("/assets/lyoko/textures/armor/yumi_2");
		RenderingRegistry.addNewArmourRendererPrefix("/assets/lyoko/textures/armor/william_1");
		RenderingRegistry.addNewArmourRendererPrefix("/assets/lyoko/textures/armor/william_2");
		
		//RenderingRegistry.instance().registerEntityRenderingHandler(EntityTest.class, new RenderCatTest());
		RenderingRegistry.registerEntityRenderingHandler(EntityFan.class, new RenderFan(ModItems.Fan));
		RenderingRegistry.registerEntityRenderingHandler(EntityEnergyField.class, new RenderEnergyField(ModItems.EnergyField));
		RenderingRegistry.registerEntityRenderingHandler(EntityLaserArrow.class, new RenderLaserArrow());
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
		RenderingRegistry.registerEntityRenderingHandler(EntitySkid.class, new RenderSkid());
		RenderingRegistry.registerEntityRenderingHandler(EntityOverboard.class, new RenderOverboard());
		
		
		
		MinecraftForgeClient.registerItemRenderer(ModItems.Glove.itemID, (IItemRenderer)new ItemRenderGlove());
		
		//ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySuperCalc.class, new RenderSuperCalc());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTowerConsole.class, new RenderEntityTowerConsole());
	    ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTower.class, new RenderTower());
	    ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCable.class, new RenderCable());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityScanner.class, new RenderScanner());
	    
		// Init capes. make new file called lyokocapes.txt in dropbox, with the template of this: https://github.com/jadar/DeveloperCapesAPI/blob/master/SampleCape.txt
		DeveloperCapesAPI.getInstance().init("https://dl.dropbox.com/u/87762025/lyokocapes.txt");
	}
	
	public void registerKeyBindingHandler()
	{
		KeyBindingRegistry.registerKeyBinding(new KeyBindingHandler());
	}
	
	/**
	 * 
	 * called for things with alpha. thank you MachineMuse. :D
	 */
	public static void alphaOn() {
	    GL11.glPushAttrib(GL11.GL_COLOR_BUFFER_BIT);
	    GL11.glPushAttrib(GL11.GL_LIGHTING_BIT);
	    if(Minecraft.isFancyGraphicsEnabled()) {
	        GL11.glShadeModel(GL11.GL_SMOOTH);
	        GL11.glEnable(GL11.GL_BLEND);
	        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
	    }
	}
	
	public static void alphaOff() {
	    GL11.glPopAttrib();
	    GL11.glPopAttrib();
	}
}