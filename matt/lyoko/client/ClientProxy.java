package matt.lyoko.client;

import org.lwjgl.opengl.GL11;

import com.jadarstudios.api.developercapesapi.DeveloperCapesAPI;

import net.minecraftforge.client.*;
import cpw.mods.fml.client.registry.*;
import net.minecraft.client.Minecraft;
import matt.lyoko.CommonProxy;
import matt.lyoko.KeyBindingHandler;
import matt.lyoko.entities.mobs.*;
import matt.lyoko.entities.projectile.*;
import matt.lyoko.render.mobs.*;
import matt.lyoko.model.mobs.*;
import matt.lyoko.render.*;
import matt.lyoko.model.*;
import matt.lyoko.entities.tileentity.*;
import matt.lyoko.entities.vehicles.*;
import matt.lyoko.items.ModItems;
import matt.lyoko.render.tileentity.*;
import matt.lyoko.render.vehicles.*;

public class ClientProxy extends CommonProxy
{
	@Override
	public void registerRenderInformation() 
	{
		RenderingRegistry.addNewArmourRendererPrefix("/mods/lyoko/textures/armor/aelita_1");
		RenderingRegistry.addNewArmourRendererPrefix("/mods/lyoko/textures/armor/aelita_2");
		RenderingRegistry.addNewArmourRendererPrefix("/mods/lyoko/textures/armor/odd_1");
		RenderingRegistry.addNewArmourRendererPrefix("/mods/lyoko/textures/armor/odd_2");
		RenderingRegistry.addNewArmourRendererPrefix("/mods/lyoko/textures/armor/ulrich_1");
		RenderingRegistry.addNewArmourRendererPrefix("/mods/lyoko/textures/armor/ulrich_2");
		RenderingRegistry.addNewArmourRendererPrefix("/mods/lyoko/textures/armor/yumi_1");
		RenderingRegistry.addNewArmourRendererPrefix("/mods/lyoko/textures/armor/yumi_2");
		RenderingRegistry.addNewArmourRendererPrefix("/mods/lyoko/textures/armor/william_1");
		RenderingRegistry.addNewArmourRendererPrefix("/mods/lyoko/textures/armor/william_2");
		
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