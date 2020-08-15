package net.star.wars.materials.entities.porg;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;

public class PorgEntityModel extends EntityModel<PorgEntity> {

    private final ModelPart torso;
    private final ModelPart head;

    public PorgEntityModel() {
        torso = new ModelPart(this, 0, 0);
        torso.addCuboid(-2, -2, -3, 4.5F, 4.5F, 6.0F);
        torso.setPivot(0, 0, 0);

        head = new ModelPart(this, 0, 0);
        head.addCuboid(-1.7F, -2.9F, -2.0F, 3.4F, 3.0F, 3.0F);
        head.setPivot(0, 0, 0);
    }

    @Override
    public void setAngles(PorgEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
 
    }
 
    @Override
    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
        // translate model down
        matrices.translate(0, 1.125, 0);
 
        // render cube
        torso.render(matrices, vertices, light, overlay);
        head.render(matrices, vertices, light, overlay);
    }
}