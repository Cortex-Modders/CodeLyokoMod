/*
 * Code Lyoko Mod for Minecraft ${version}
 * Copyright 2014 Cortex Modders, Matthew Warren, Jacob Rhoda, and other contributors.
 * Released under the MIT license http://opensource.org/licenses/MIT
 */

package net.cortexmodders.lyoko.client.model.tileentity;

import net.cortexmodders.lyoko.blocks.BlockScanner;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Scanner Model. Contains the model for each part of the scanner.
 * 
 * @author Jadar
 */
@SideOnly(Side.CLIENT)
public class ModelScanner extends ModelBase
{
    
    ModelScannerMiddle scannerMiddle;
    ModelScannerDoor scannerDoor;
    ModelScannerEnd scannerEnd;
    
    public ModelScanner()
    {
        this.textureWidth = 128;
        this.textureHeight = 128;
        
        this.scannerMiddle = new ModelScannerMiddle();
        this.scannerEnd = new ModelScannerEnd();
        this.scannerDoor = new ModelScannerDoor();
    }
    
    /**
     * Renders the scanner using the specified model type id with door angle and position. the id is
     * whatever you get from
     * {@link BlockScanner#getPositionInMultiBlock(IBlockAccess, int, int, int)}
     * 
     * @param entity
     *            null (this is for a {@link TileEntity})
     * @param doorAngle
     *            door angle in degrees
     * @param doorPosX
     *            door X coord
     * @param doorPosZ
     *            door Z coord
     * @param f3
     *            nothing
     * @param modelType
     *            model type.
     * @param scale
     *            scale, typically 1/16.
     */
    @Override
    public void render(Entity entity, float doorAngle, float doorPosX, float doorPosZ, float f3, float modelType, float scale)
    {
        if (modelType == 4 || modelType == 0)
            this.scannerEnd.render(entity, 0.0F, 0.0F, 0.0F, 0.0F, modelType, scale);
        
        this.scannerMiddle.render(entity, 0.0F, 0.0F, 0.0F, 0.0F, modelType, scale);
        this.scannerDoor.render(entity, doorAngle, doorPosX, doorPosZ, f3, modelType, scale);
    }
    
    /**
     * Utility method for setting model rotation angles.
     */
    protected static void setRotation(ModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
    
    private static class ModelScannerMiddle extends ModelBase
    {
        protected ModelRenderer sideLcornerB;
        protected ModelRenderer sideRcornerB;
        protected ModelRenderer sideLcornerF;
        protected ModelRenderer sideRcornerF;
        protected ModelRenderer sideB;
        protected ModelRenderer sideL;
        protected ModelRenderer sideR;
        
        public ModelScannerMiddle()
        {
            this.textureWidth = 128;
            this.textureHeight = 128;
            
            this.sideLcornerB = new ModelRenderer(this, 0, 56);
            this.sideLcornerB.addBox(-1F, -4F, -1F, 2, 16, 2);
            this.sideLcornerB.setRotationPoint(-9F, -12F, 9F);
            this.sideLcornerB.setTextureSize(128, 128);
            this.sideLcornerB.mirror = true;
            ModelScanner.setRotation(this.sideLcornerB, 0F, 0F, 0F);
            
            this.sideRcornerB = new ModelRenderer(this, 0, 56);
            this.sideRcornerB.addBox(-1F, -4F, -1F, 2, 16, 2);
            this.sideRcornerB.setRotationPoint(9F, -12F, 9F);
            this.sideRcornerB.setTextureSize(128, 128);
            this.sideRcornerB.mirror = true;
            ModelScanner.setRotation(this.sideRcornerB, 0F, 1.570796F, 0F);
            
            this.sideLcornerF = new ModelRenderer(this, 0, 56);
            this.sideLcornerF.addBox(-1F, -4F, -1F, 2, 16, 2);
            this.sideLcornerF.setRotationPoint(-9F, -12F, -9F);
            this.sideLcornerF.setTextureSize(128, 128);
            this.sideLcornerF.mirror = true;
            ModelScanner.setRotation(this.sideLcornerF, 0F, -1.570796F, 0F);
            
            this.sideRcornerF = new ModelRenderer(this, 0, 56);
            this.sideRcornerF.addBox(-1F, -4F, -1F, 2, 16, 2);
            this.sideRcornerF.setRotationPoint(9F, -12F, -9F);
            this.sideRcornerF.setTextureSize(128, 128);
            this.sideRcornerF.mirror = true;
            ModelScanner.setRotation(this.sideRcornerF, 0F, 3.141593F, 0F);
            
            this.sideB = new ModelRenderer(this, 80, 0);
            this.sideB.addBox(-8F, -4F, -1F, 16, 16, 2);
            this.sideB.setRotationPoint(0F, -12F, 11F);
            this.sideB.setTextureSize(128, 128);
            this.sideB.mirror = true;
            ModelScanner.setRotation(this.sideB, 0F, 3.141593F, 0F);
            
            this.sideL = new ModelRenderer(this, 80, 0);
            this.sideL.addBox(-8F, -4F, 0F, 16, 16, 2);
            this.sideL.setRotationPoint(-12F, -12F, 0F);
            this.sideL.setTextureSize(128, 128);
            this.sideL.mirror = true;
            ModelScanner.setRotation(this.sideL, 0F, 1.570796F, 0F);
            
            this.sideR = new ModelRenderer(this, 80, 0);
            this.sideR.addBox(-8F, -4F, 0F, 16, 16, 2);
            this.sideR.setRotationPoint(12F, -12F, 0F);
            this.sideR.setTextureSize(128, 128);
            this.sideR.mirror = true;
            ModelScanner.setRotation(this.sideR, 0F, -1.570796F, 0F);
        }
        
        @Override
        public void render(Entity entity, float f, float f1, float f2, float f3, float modelType, float scale)
        {
            GL11.glPushMatrix();
            {
                if (modelType == 0)
                    GL11.glTranslatef(0.0F, -0.5F, 0.0F);
                if (modelType == 0 || modelType == 4)
                    GL11.glScalef(1.0F, 0.5F, 1.0F);
                
                this.sideLcornerB.render(scale);
                this.sideRcornerB.render(scale);
                this.sideLcornerF.render(scale);
                this.sideRcornerF.render(scale);
                this.sideB.render(scale);
                this.sideL.render(scale);
                this.sideR.render(scale);
            }
            GL11.glPopMatrix();
        }
    }
    
    private static class ModelScannerDoor extends ModelBase
    {
        ModelRenderer doorL;
        ModelRenderer doorR;
        
        private static int DEFAULT_TEXTURE_OFFSET_X = 8;
        private static int DEFAULT_TEXTURE_OFFSET_Y = 56;
        
        public ModelScannerDoor()
        {
            this.textureWidth = 128;
            this.textureHeight = 128;
            
            this.doorL = new ModelRenderer(this, DEFAULT_TEXTURE_OFFSET_X, DEFAULT_TEXTURE_OFFSET_Y).addBox(-1F, -4F, -8F, 2, 16, 8).setTextureSize(128, 128);
            this.doorL.setRotationPoint(-9F, -12F, 0F);
            this.doorL.mirror = true;
            ModelScanner.setRotation(this.doorL, 0F, 0F, 0F);
            
            this.doorR = new ModelRenderer(this, DEFAULT_TEXTURE_OFFSET_X, DEFAULT_TEXTURE_OFFSET_Y).addBox(-1F, -4F, 0F, 2, 16, 8).setTextureSize(128, 128);
            this.doorR.setRotationPoint(9F, -12F, 0F);
            this.doorR.mirror = true;
            ModelScanner.setRotation(this.doorR, 0F, -3.141593F, 0F);
        }
        
        @Override
        public void render(Entity entity, float doorAngle, float doorPosX, float doorPosZ, float f3, float modelType, float scale)
        {
            this.setRotationAngles(doorAngle, doorPosX, doorPosZ, f3, modelType, scale, entity);
            
            if (modelType != -1)
            {
                int position = (int) modelType;
                position = 5 - position;
                
                this.doorL.setTextureOffset(DEFAULT_TEXTURE_OFFSET_X * position, DEFAULT_TEXTURE_OFFSET_Y * position);
                this.doorR.setTextureOffset(DEFAULT_TEXTURE_OFFSET_X * position, DEFAULT_TEXTURE_OFFSET_Y * position);
            }
            
            GL11.glPushMatrix();
            {
                if (modelType == 0)
                    GL11.glTranslatef(0.0F, -0.5F, 0.0F);
                if (modelType == 0 || modelType == 4)
                    GL11.glScalef(1.0F, 0.5F, 1.0F);
                
                this.doorL.render(scale);
                this.doorR.render(scale);
            }
            GL11.glPopMatrix();
        }
        
        @Override
        public void setRotationAngles(float doorAngle, float doorPosX, float doorPosZ, float f3, float f4, float f5, Entity entity)
        {
            this.doorL.rotateAngleY = (float) Math.toRadians(doorAngle);
            this.doorL.rotationPointX = doorPosX;
            this.doorL.rotationPointZ = doorPosZ;
            
            this.doorR.rotateAngleY = (float) -Math.toRadians(doorAngle + 180);
            this.doorR.rotationPointX = -doorPosX;
            this.doorR.rotationPointZ = doorPosZ;
        }
    }
    
    private static class ModelScannerEnd extends ModelBase
    {
        private ModelRenderer base;
        private ModelRenderer baseL;
        private ModelRenderer baseB;
        private ModelRenderer baseF;
        private ModelRenderer baseR;
        
        public ModelScannerEnd()
        {
            this.textureWidth = 128;
            this.textureHeight = 128;
            
            this.base = new ModelRenderer(this, 0, 28);
            this.base.addBox(-10F, -8F, -10F, 20, 8, 20);
            this.base.setRotationPoint(0F, 0F, 0F);
            this.base.setTextureSize(128, 128);
            this.base.mirror = true;
            ModelScanner.setRotation(this.base, 0F, 0F, 0F);
            
            this.baseL = new ModelRenderer(this, 28, 67);
            this.baseL.addBox(-8F, -8F, 0F, 16, 8, 2);
            this.baseL.setRotationPoint(-12F, 0F, 0F);
            this.baseL.setTextureSize(128, 128);
            this.baseL.mirror = true;
            ModelScanner.setRotation(this.baseL, 0F, 1.570796F, 0F);
            
            this.baseB = new ModelRenderer(this, 28, 67);
            this.baseB.addBox(-8F, -8F, 0F, 16, 8, 2);
            this.baseB.setRotationPoint(0F, 0F, 10F);
            this.baseB.setTextureSize(128, 128);
            this.baseB.mirror = true;
            ModelScanner.setRotation(this.baseB, 0F, 0F, 0F);
            
            this.baseF = new ModelRenderer(this, 28, 67);
            this.baseF.addBox(-8F, -8F, -2F, 16, 8, 2);
            this.baseF.setRotationPoint(0F, 0F, -10F);
            this.baseF.setTextureSize(128, 128);
            this.baseF.mirror = true;
            ModelScanner.setRotation(this.baseF, 0F, 0F, 0F);
            
            this.baseR = new ModelRenderer(this, 28, 67);
            this.baseR.addBox(-8F, -8F, 0F, 16, 8, 2);
            this.baseR.setRotationPoint(10F, 0F, 0F);
            this.baseR.setTextureSize(128, 128);
            this.baseR.mirror = true;
            ModelScanner.setRotation(this.baseR, 0F, 1.570796F, 0F);
        }
        
        @Override
        public void render(Entity entity, float f, float f1, float f2, float f3, float modelType, float scale)
        {
            
            GL11.glPushMatrix();
            {
                if (modelType == 4)
                {
                    GL11.glRotatef(180, 0.0F, 0.0F, 1.0F);
                    GL11.glTranslatef(0.0F, 1.0F, 0.0F);
                }
                
                this.base.render(scale);
                this.baseL.render(scale);
                this.baseB.render(scale);
                this.baseF.render(scale);
                this.baseR.render(scale);
            }
            GL11.glPopMatrix();
        }
    }
    
}
