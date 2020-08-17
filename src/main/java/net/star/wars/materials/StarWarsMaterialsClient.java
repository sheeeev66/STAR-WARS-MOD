package net.star.wars.materials;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.star.wars.materials.entities.porg.PorgEntityRender;
import net.star.wars.materials.registry.EntityRegistry;
import net.star.wars.materials.registry.client.ClientBlockRegistry;


@Environment(EnvType.CLIENT)
public class StarWarsMaterialsClient implements ClientModInitializer {
 
    @Override
    public void onInitializeClient() {
        /*
         * Registers our Cube Entity's renderer, which provides a model and texture for the entity.
         *
         * Entity Renderers can also manipulate the model before it renders based on entity context (EndermanEntityRenderer#render).
        */
        EntityRendererRegistry.INSTANCE.register(EntityRegistry.PORG, (dispatcher, context) -> new PorgEntityRender(dispatcher));

        // for ice crystals block transperency
        ClientBlockRegistry.initialize();
        
    }
}