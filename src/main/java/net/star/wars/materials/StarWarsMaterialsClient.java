package net.star.wars.materials;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.minecraft.client.render.RenderLayer;
import net.star.wars.materials.entities.porg.PorgEntityRender;


@Environment(EnvType.CLIENT)
public class StarWarsMaterialsClient implements ClientModInitializer {
 
    @Override
    public void onInitializeClient() {
        /*
         * Registers our Cube Entity's renderer, which provides a model and texture for the entity.
         *
         * Entity Renderers can also manipulate the model before it renders based on entity context (EndermanEntityRenderer#render).
        */
        EntityRendererRegistry.INSTANCE.register(StarWarsMaterials.PORG, (dispatcher, context) -> {
            return new PorgEntityRender(dispatcher);
        });

        // for ice crystals block transperency
        BlockRenderLayerMap.INSTANCE.putBlock(StarWarsMaterials.ICE_CRYSTALS, RenderLayer.getCutout());
        
    }
}