/*
 * Code Lyoko Mod for Minecraft ${version}
 * Copyright 2014 Cortex Modders, Matthew Warren, Jacob Rhoda, and other contributors.
 * Released under the MIT license http://opensource.org/licenses/MIT
 */

package net.cortexmodders.lyoko.particle;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.world.World;

public class LyokoParticleEffects
{
    private static Minecraft mc = Minecraft.getMinecraft();
    private static World theWorld = mc.theWorld;
    
    public static EntityFX spawnParticle(String particleName, double par2, double par4, double par6, double par8, double par10, double par12)
    {
        if (mc != null && mc.renderViewEntity != null && mc.effectRenderer != null)
        {
            int var14 = mc.gameSettings.particleSetting;
            
            if (var14 == 1 && theWorld.rand.nextInt(3) == 0)
                var14 = 2;
            
            double var15 = mc.renderViewEntity.posX - par2;
            double var17 = mc.renderViewEntity.posY - par4;
            double var19 = mc.renderViewEntity.posZ - par6;
            EntityFX var21 = null;
            double var22 = 16.0D;
            
            if (var15 * var15 + var17 * var17 + var19 * var19 > var22 * var22)
                return null;
            else if (var14 > 1)
                return null;
            else
            {
                if (particleName.equals("xana"))
                    var21 = new EntityXanaFX(theWorld, par2, par4, par6, (float) par8, (float) par10, (float) par12);
                else if (particleName.equals("lyoko"))
                    var21 = new EntityLyokoFX(theWorld, par2, par4, par6, (float) par8, (float) par10, (float) par12);
                else if (particleName.equals("deactivated"))
                    var21 = new EntityNeutralFX(theWorld, par2, par4, par6, (float) par8, (float) par10, (float) par12);
                else if (particleName.equals("dev"))
                    var21 = new EntityDeveloperFX(theWorld, par2, par4, par6, (float) par8, (float) par10, (float) par12);
                
                mc.effectRenderer.addEffect(var21);
                return var21;
            }
        }
        
        return null;
    }
}
