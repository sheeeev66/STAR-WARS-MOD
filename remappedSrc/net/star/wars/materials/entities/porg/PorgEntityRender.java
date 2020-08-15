package net.star.wars.materials.entities.porg;

import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;
import net.star.wars.materials.StarWarsMaterials;

public class PorgEntityRender extends MobEntityRenderer<PorgEntity, PorgEntityModel> {
 
    public PorgEntityRender(EntityRenderDispatcher entityRenderDispatcher) {
        super(entityRenderDispatcher, new PorgEntityModel(), 0.5f);
    }
 
    @Override
    public Identifier getTexture(PorgEntity entity) {
        return new Identifier(StarWarsMaterials.MOD_ID, "textures/entity/porg/porg.png");
    }
}