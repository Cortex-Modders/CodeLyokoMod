package matt.lyoko.model.tileentity;

import matt.lyoko.entities.tileentity.TileEntityScanner;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelScanner extends ModelBase {
    // fields
    ModelRenderer base;
    ModelRenderer baseL;
    ModelRenderer baseB;
    ModelRenderer baseF;
    ModelRenderer baseR;
    ModelRenderer sideLcornerB;
    ModelRenderer sideRcornerB;
    ModelRenderer sideLcornerF;
    ModelRenderer sideRcornerF;
    ModelRenderer sideB;
    ModelRenderer sideL;
    ModelRenderer sideR;
    public ModelRenderer doorL;
    public ModelRenderer doorR;
    ModelRenderer top;
    ModelRenderer topL;
    ModelRenderer topF;
    ModelRenderer topB;
    ModelRenderer topR;

    public ModelScanner() {
        textureWidth = 128;
        textureHeight = 128;

        base = new ModelRenderer(this, 0, 28);
        base.addBox(-10F, -8F, -10F, 20, 8, 20);
        base.setRotationPoint(0F, 0F, 0F);
        base.setTextureSize(128, 128);
        base.mirror = true;
        setRotation(base, 0F, 0F, 0F);
        baseL = new ModelRenderer(this, 22, 46);
        baseL.addBox(-8F, -4F, 0F, 16, 8, 2);
        baseL.setRotationPoint(-12F, -4F, 0F);
        baseL.setTextureSize(128, 128);
        baseL.mirror = true;
        setRotation(baseL, 0F, 1.570796F, 0F);
        baseB = new ModelRenderer(this, 22, 46);
        baseB.addBox(-8F, -4F, 0F, 16, 8, 2);
        baseB.setRotationPoint(0F, -4F, 10F);
        baseB.setTextureSize(128, 128);
        baseB.mirror = true;
        setRotation(baseB, 0F, 0F, 0F);
        baseF = new ModelRenderer(this, 22, 46);
        baseF.addBox(-8F, -4F, -2F, 16, 8, 2);
        baseF.setRotationPoint(0F, -4F, -10F);
        baseF.setTextureSize(128, 128);
        baseF.mirror = true;
        setRotation(baseF, 0F, 0F, 0F);
        baseR = new ModelRenderer(this, 22, 46);
        baseR.addBox(-8F, -4F, 0F, 16, 8, 2);
        baseR.setRotationPoint(10F, -4F, 0F);
        baseR.setTextureSize(128, 128);
        baseR.mirror = true;
        setRotation(baseR, 0F, 1.570796F, 0F);
        sideLcornerB = new ModelRenderer(this, 0, 56);
        sideLcornerB.addBox(-1F, -32F, -1F, 2, 64, 2);
        sideLcornerB.setRotationPoint(-9F, -40F, 9F);
        sideLcornerB.setTextureSize(128, 128);
        sideLcornerB.mirror = true;
        setRotation(sideLcornerB, 0F, 0F, 0F);
        sideRcornerB = new ModelRenderer(this, 0, 56);
        sideRcornerB.addBox(-1F, -32F, -1F, 2, 64, 2);
        sideRcornerB.setRotationPoint(9F, -40F, 9F);
        sideRcornerB.setTextureSize(128, 128);
        sideRcornerB.mirror = true;
        setRotation(sideRcornerB, 0F, (float)Math.toRadians(90), 0F);
        sideLcornerF = new ModelRenderer(this, 0, 56);
        sideLcornerF.addBox(-1F, -32F, -1F, 2, 64, 2);
        sideLcornerF.setRotationPoint(-9F, -40F, -9F);
        sideLcornerF.setTextureSize(128, 128);
        sideLcornerF.mirror = true;
        setRotation(sideLcornerF, 0F, (float)Math.toRadians(-90), 0F);
        sideRcornerF = new ModelRenderer(this, 0, 56);
        sideRcornerF.addBox(-1F, -32F, -1F, 2, 64, 2);
        sideRcornerF.setRotationPoint(9F, -40F, -9F);
        sideRcornerF.setTextureSize(128, 128);
        sideRcornerF.mirror = true;
        setRotation(sideRcornerF, 0F, (float)Math.toRadians(90), 0F);
        sideB = new ModelRenderer(this, 80, 0);
        sideB.addBox(-8F, -32F, -1F, 16, 64, 2);
        sideB.setRotationPoint(0F, -40F, 11F);
        sideB.setTextureSize(128, 128);
        sideB.mirror = true;
        setRotation(sideB, 0F, (float)Math.toRadians(180), 0F);
        sideL = new ModelRenderer(this, 80, 0);
        sideL.addBox(-8F, -32F, 0F, 16, 64, 2);
        sideL.setRotationPoint(-12F, -40F, 0F);
        sideL.setTextureSize(128, 128);
        sideL.mirror = true;
        setRotation(sideL, 0F, 1.570796F, 0F);
        sideR = new ModelRenderer(this, 80, 0);
        sideR.addBox(-8F, -32F, 0F, 16, 64, 2);
        sideR.setRotationPoint(12F, -40F, 0F);
        sideR.setTextureSize(128, 128);
        sideR.mirror = true;
        setRotation(sideR, 0F, -1.570796F, 0F);
        doorL = new ModelRenderer(this, 8, 56);
        doorL.addBox(-1F, -32F, -8F, 2, 64, 8);
        doorL.setRotationPoint(-9F, -40F, 0F);
        doorL.setTextureSize(128, 128);
        doorL.mirror = true;
        setRotation(doorL, 0F, 0F, 0F);
        doorR = new ModelRenderer(this, 8, 56);
        doorR.addBox(-1F, -32F, -8F, 2, 64, 8);
        doorR.setRotationPoint(9F, -40F, 0F);
        doorR.setTextureSize(128, 128);
        doorR.mirror = true;
        setRotation(doorR, (float)Math.toRadians(180), (float)Math.toRadians(-180), 0F);
        top = new ModelRenderer(this, 0, 0);
        top.addBox(-10F, -8F, -10F, 20, 8, 20);
        top.setRotationPoint(0F, -72F, 0F);
        top.setTextureSize(128, 128);
        top.mirror = true;
        setRotation(top, 0F, 0F, 0F);
        topL = new ModelRenderer(this, 22, 18);
        topL.addBox(-8F, -4F, 0F, 16, 8, 2);
        topL.setRotationPoint(-12F, -76F, 0F);
        topL.setTextureSize(128, 128);
        topL.mirror = true;
        setRotation(topL, 0F, 1.570796F, 0F);
        topF = new ModelRenderer(this, 22, 18);
        topF.addBox(-8F, -4F, -2F, 16, 8, 2);
        topF.setRotationPoint(0F, -76F, -10F);
        topF.setTextureSize(128, 128);
        topF.mirror = true;
        setRotation(topF, 0F, 0F, 0F);
        topB = new ModelRenderer(this, 22, 18);
        topB.addBox(-8F, -4F, 0F, 16, 8, 2);
        topB.setRotationPoint(0F, -76F, 10F);
        topB.setTextureSize(128, 128);
        topB.mirror = true;
        setRotation(topB, 0F, 0F, 0F);
        topR = new ModelRenderer(this, 22, 18);
        topR.addBox(-8F, -4F, 0F, 16, 8, 2);
        topR.setRotationPoint(10F, -76F, 0F);
        topR.setTextureSize(128, 128);
        topR.mirror = true;
        setRotation(topR, 0F, 1.570796F, 0F);
    }

    public void render(TileEntityScanner entity, float f, float f1, float f2, float f3, float f4, float f5) {
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        base.render(f5);
        baseL.render(f5);
        baseB.render(f5);
        baseF.render(f5);
        baseR.render(f5);
        sideLcornerB.render(f5);
        sideRcornerB.render(f5);
        sideLcornerF.render(f5);
        sideRcornerF.render(f5);
        sideB.render(f5);
        sideL.render(f5);
        sideR.render(f5);
        doorL.render(f5);
        doorR.render(f5);
        top.render(f5);
        topL.render(f5);
        topF.render(f5);
        topB.render(f5);
        topR.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }


    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, TileEntityScanner entity) {
        /*
        doorL.setRotationPoint(entity.doorPosX, -40, entity.doorPosZ);
        doorR.setRotationPoint(-entity.doorPosX, -40, entity.doorPosZ);
        
        doorL.rotateAngleY = (float)Math.toRadians(entity.doorRotationYaw);
        // Right door default rotation is 180, so whatever left yaw is add the default rotation. 
        doorR.rotateAngleY = -(float)Math.toRadians(entity.doorRotationYaw + 180);
        */
    }
    /*
    public void openDoors() {
        
        rightDoorX = 9;
        rightDoorZ = 0;
        
        leftDoorX = -9;
        leftDoorZ = 0;
        
        doorR.rotateAngleY = (float) Math.toRadians(-180);
        doorL.rotateAngleY = 0;
    }
    
    public void closeDoors() {
        rightDoorX = 8;
        rightDoorZ = -10;
        
        leftDoorX = -8;
        leftDoorZ = -10;
        
        doorR.rotateAngleY = (float) doorYawClosed;
        doorL.rotateAngleY = (float) doorYawClosed;
    }
    */
}
