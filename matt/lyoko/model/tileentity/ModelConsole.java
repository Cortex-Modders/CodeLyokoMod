package matt.lyoko.model.tileentity;

import matt.lyoko.entities.TileEntityTowerConsole;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelConsole extends ModelBase {
    // fields
    ModelRenderer consolePlane;

    public ModelConsole() {
        textureWidth = 64;
        textureHeight = 32;

        consolePlane = new ModelRenderer(this, 0, 0);
        consolePlane.addBox(-12F, -8F, 0F, 24, 16, 0);
        consolePlane.setRotationPoint(0F, 0F, 0F);
        consolePlane.setTextureSize(64, 32);
        consolePlane.mirror = true;
        setRotation(consolePlane, -0.5235988F, 0F, 0F);
    }

    public void render(TileEntityTowerConsole entity, float x, float y, float z, float yaw, float pitch, float scale) {
        consolePlane.render(scale);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    }

}
