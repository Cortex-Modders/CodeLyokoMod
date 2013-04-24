package matt.lyoko.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelGlove extends ModelBase {
    // fields
    ModelRenderer arm;
    ModelRenderer barrel;
    ModelRenderer barrel2;
    ModelRenderer barrel3;
    ModelRenderer palm;
    ModelRenderer finger1;
    ModelRenderer finger1mid;
    ModelRenderer finger1tip;
    ModelRenderer finger2;
    ModelRenderer finger2mid;
    ModelRenderer finger2tip;
    ModelRenderer finger3;
    ModelRenderer finger3mid;
    ModelRenderer finger3tip;

    public ModelGlove() {
        textureWidth = 64;
        textureHeight = 32;

        arm = new ModelRenderer(this, 0, 12);
        arm.addBox(-2F, 0F, 0F, 4, 3, 7);
        arm.setRotationPoint(0F, 0F, 0F);
        arm.setTextureSize(64, 32);
        arm.mirror = true;
        setRotation(arm, 0F, 0F, 0F);
       
        barrel = new ModelRenderer(this, 16, 8);
        barrel.addBox(-0.5F, -1F, -1F, 1, 1, 2);
        barrel.setRotationPoint(1.5F, 1.2F, -1.5F);
        barrel.setTextureSize(64, 32);
        barrel.mirror = true;
        setRotation(barrel, 0F, 0F, 0F);
        barrel2 = new ModelRenderer(this, 16, 8);
        barrel2.addBox(-0.5F, -1F, -1F, 1, 1, 2);
        barrel2.setRotationPoint(0F, 1.2F, -1.5F);
        barrel2.setTextureSize(64, 32);
        barrel2.mirror = true;
        setRotation(barrel2, 0F, 0F, 0F);
        barrel3 = new ModelRenderer(this, 16, 8);
        barrel3.addBox(-0.5F, -1F, -1F, 1, 1, 2);
        barrel3.setRotationPoint(-1.5F, 1.2F, -1.5F);
        barrel3.setTextureSize(64, 32);
        barrel3.mirror = true;
        setRotation(barrel3, 0F, 0F, 0F);
       
        palm = new ModelRenderer(this, 0, 7);
        palm.addBox(-2.5F, -1F, -3F, 5, 2, 3);
        palm.setRotationPoint(0F, 2F, 0F);
        palm.setTextureSize(64, 32);
        palm.mirror = true;
        setRotation(palm, 0F, 0F, 0F);
        
        finger1 = new ModelRenderer(this, 0, 4);
        finger1.mirror = true;
        finger1.addBox(-0.5F, 0F, -2F, 1, 1, 2);
        finger1.setRotationPoint(1.5F, 1.5F, -3F);
        finger1.setTextureSize(64, 32);
        finger1.mirror = true;
        setRotation(finger1, 0.1570796F, 0F, 0F);
        finger1.mirror = false;
        finger1mid = new ModelRenderer(this, 0, 2);
        finger1mid.addBox(-0.5F, -0.5F, -1.5F, 1, 1, 1);
        finger1mid.setRotationPoint(1.5F, 2.2F, -4.4F);
        finger1mid.setTextureSize(64, 32);
        finger1mid.mirror = true;
        setRotation(finger1mid, 0.2478368F, 0F, 0F);
        finger1tip = new ModelRenderer(this, 0, 0);
        finger1tip.addBox(-0.5F, -0.5F, -1F, 1, 1, 1);
        finger1tip.setRotationPoint(1.5F, 2.5F, -5.6F);
        finger1tip.setTextureSize(64, 32);
        finger1tip.mirror = true;
        setRotation(finger1tip, 0.418879F, 0F, 0F);
        
        finger2 = new ModelRenderer(this, 0, 4);
        finger2.addBox(-0.5F, 0F, -2F, 1, 1, 2);
        finger2.setRotationPoint(0F, 1.5F, -3F);
        finger2.setTextureSize(64, 32);
        finger2.mirror = true;
        setRotation(finger2, 0.1570796F, 0F, 0F);
        finger2mid = new ModelRenderer(this, 0, 2);
        finger2mid.addBox(-0.5F, -0.5F, -1.5F, 1, 1, 1);
        finger2mid.setRotationPoint(0F, 2.2F, -4.4F);
        finger2mid.setTextureSize(64, 32);
        finger2mid.mirror = true;
        setRotation(finger2mid, 0.2478368F, 0F, 0F);
        finger2tip = new ModelRenderer(this, 0, 0);
        finger2tip.addBox(-0.5F, -0.5F, -1F, 1, 1, 1);
        finger2tip.setRotationPoint(0F, 2.5F, -5.6F);
        finger2tip.setTextureSize(64, 32);
        finger2tip.mirror = true;
        setRotation(finger2tip, 0.418879F, 0F, 0F);
       
        finger3 = new ModelRenderer(this, 0, 4);
        finger3.addBox(-0.5F, 0F, -2F, 1, 1, 2);
        finger3.setRotationPoint(-1.5F, 1.5F, -3F);
        finger3.setTextureSize(64, 32);
        finger3.mirror = true;
        setRotation(finger3, 0.1570796F, 0F, 0F);
        finger3mid = new ModelRenderer(this, 0, 2);
        finger3mid.addBox(-0.5F, -0.5F, -1.5F, 1, 1, 1);
        finger3mid.setRotationPoint(-1.5F, 2.2F, -4.4F);
        finger3mid.setTextureSize(64, 32);
        finger3mid.mirror = true;
        finger3.addChild(finger3mid);
        setRotation(finger3mid, 0.2478368F, 0F, 0F);
        finger3tip = new ModelRenderer(this, 0, 0);
        finger3tip.addBox(-0.5F, -0.5F, -1F, 1, 1, 1);
        finger3tip.setRotationPoint(-1.5F, 2.5F, -5.6F);
        finger3tip.setTextureSize(64, 32);
        finger3tip.mirror = true;
        finger3mid.addChild(finger3tip);
        setRotation(finger3tip, 0.418879F, 0F, 0F);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5, boolean isFirstPerson) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        arm.render(f5);
        barrel.render(f5);
        barrel2.render(f5);
        barrel3.render(f5);
        palm.render(f5);
        finger1.render(f5);
        finger1mid.render(f5);
        finger1tip.render(f5);
        finger2.render(f5);
        finger2mid.render(f5);
        finger2tip.render(f5);
        finger3.render(f5);
//        finger3mid.render(f5);
//        finger3tip.render(f5);
        if(isFirstPerson) {
            // left thumb
        }
        else {
            // right thumb
        }
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity ent) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, ent);
    }

    public void clenchHand() {
        finger1.rotateAngleX = 0.418879F;
        finger1mid.rotationPointX = 0.6667158F;
        finger1tip.rotateAngleX = 1.0855948F;

        finger2.rotateAngleX = 0.418879F;
        finger2mid.rotateAngleX = 0.6667158F;
        finger2tip.rotateAngleX = 1.0855948F;
        
        finger3.rotateAngleX = 0.418879F;
        finger3mid.rotateAngleX = 0.6667158F;
        finger3tip.rotateAngleX = 1.0855948F;
    }
    
    public void unclenchHand() {
        finger1.rotateAngleX = 0.1570796F;
        finger1mid.rotateAngleX = 0.2478368F;
        finger1tip.rotateAngleX = 0.418879F;
        
        finger2.rotateAngleX = 0.1570796F;
        finger2mid.rotateAngleX = 0.2478368F;
        finger2tip.rotateAngleX = 0.418879F;
        
        finger3.rotateAngleX = 0.1570796F;
        finger3mid.rotateAngleX = 0.2478368F;
        finger3tip.rotateAngleX = 0.418879F;
    }
}
