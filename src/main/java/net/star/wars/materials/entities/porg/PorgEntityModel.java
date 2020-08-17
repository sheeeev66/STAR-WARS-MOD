package net.star.wars.materials.entities.porg;

import com.google.common.collect.ImmutableList;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.AnimalModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;

public class PorgEntityModel extends AnimalModel<PorgEntity> {

    private final ModelPart head;
    private final ModelPart torso;
    private final ModelPart rightLeg;
    private final ModelPart leftLeg;
    private final ModelPart rightWing;
    private final ModelPart leftWing;
    private final ModelPart beak;
        
    public PorgEntityModel() {
        textureWidth = 64;
        textureHeight = 32;

        head = new ModelPart(this);
        head.setPivot(0.0F, 13.1F, 0.7F);
        head.setTextureOffset(0, 0).addCuboid(-1.7F, -0.9F, -3.0F, 3.0F, 3.0F, 3.0F, 0.0F, false);
    
        torso = new ModelPart(this);
        torso.setPivot(0.0F, 16.0F, 0.0F);
        setRotationAngle(torso, 1.6057F, 0.0F, 0.0F);
        torso.setTextureOffset(0, 0).addCuboid(-2.1F, -2.0692F, -4.9639F, 4.0F, 4.0F, 6.0F, 0.0F, false);

        rightLeg = new ModelPart(this);
        rightLeg.setPivot(2.0F, 19.0F, 1.0F);
        setRotationAngle(rightLeg, 0.0F, -0.3491F, 0.0F);
        rightLeg.setTextureOffset(30, 0).addCuboid(-2.0F, 2.0F, -2.0F, 2.0F, 3.0F, 2.0F, 0.0F, false);

        leftLeg = new ModelPart(this);
        leftLeg.setPivot(-1.0F, 19.0F, 1.0F);
        setRotationAngle(leftLeg, 0.0F, 0.3491F, 0.0F);
        leftLeg.setTextureOffset(42, 0).addCuboid(-0.9145F, 2.0F, -2.2349F, 2.0F, 3.0F, 2.0F, 0.0F, false);
        
        rightWing = new ModelPart(this);
        rightWing.setPivot(3.1F, 13.0F, 1.1F);
        setRotationAngle(rightWing, 0.1222F, 0.0F, 0.0F);
        rightWing.setTextureOffset(50, 4).addCuboid(-1.0F, 1.8632F, -3.2363F, 1.0F, 4.0F, 3.0F, 0.0F, false);
    
        leftWing = new ModelPart(this);
        leftWing.setPivot(-3.1F, 13.0F, 1.1F);
        setRotationAngle(leftWing, 0.1222F, 0.0F, 0.0F);
        leftWing.setTextureOffset(0, 7).addCuboid(0.0F, 1.8632F, -3.1363F, 1.0F, 4.0F, 3.0F, 0.0F, false);
    
        beak = new ModelPart(this);
        beak.setPivot(0.0F, 15.0F, 0.7F);
        beak.setTextureOffset(24, 0).addCuboid(-1.0F, -1.7F, -3.3F, 2.0F, 1.0F, 0.0F, 0.0F, false);
    } 
        
    @Override
    public void render(MatrixStack matrixStack, VertexConsumer  buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
            
            head.render(matrixStack, buffer, packedLight, packedOverlay);
            torso.render(matrixStack, buffer, packedLight, packedOverlay);
            rightLeg.render(matrixStack, buffer, packedLight, packedOverlay);
            leftLeg.render(matrixStack, buffer, packedLight, packedOverlay);
            rightWing.render(matrixStack, buffer, packedLight, packedOverlay);
            leftWing.render(matrixStack, buffer, packedLight, packedOverlay);
            beak.render(matrixStack, buffer, packedLight, packedOverlay);
    }
    public void setRotationAngle(ModelPart bone, float x, float y, float z) {
            bone.pitch = x;
            bone.yaw = y;
            bone.roll = z;
    }

    @Override
    protected Iterable<ModelPart> getHeadParts() {
        return ImmutableList.of(this.head, this.beak);
    }

    @Override
    protected Iterable<ModelPart> getBodyParts() {
        return ImmutableList.of(this.torso, this.rightLeg, this.leftLeg, this.rightWing, this.leftWing);
    }

    @Override
    public void setAngles(PorgEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        this.head.pitch = headPitch * 0.017453292F;
        this.head.yaw = headYaw * 0.017453292F;
        this.beak.pitch = this.head.pitch;
        this.beak.yaw = this.head.yaw;
        this.torso.pitch = 1.5707964F;
        this.rightLeg.pitch = MathHelper.cos(limbAngle * 0.6662F) * 1.4F * limbDistance;
        this.leftLeg.pitch = MathHelper.cos(limbAngle * 0.6662F + 3.1415927F) * 1.4F * limbDistance;
        this.rightWing.roll = animationProgress;
        this.leftWing.roll = -animationProgress;
    }

}