package net.star.wars.materials;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.star.wars.materials.entities.porg.PorgEntityRender;
import net.star.wars.materials.sounds.LightSaberHum;
import net.fabricmc.fabric.api.network.*;
import net.minecraft.client.MinecraftClient;
import net.minecraft.sound.SoundCategory;

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
        
        ClientSidePacketRegistry.INSTANCE.register(StarWarsMaterials.LIGHTSABER_HUM_PACKET_ID,
        (packetContext, attachedData) -> {
            LightSaberHum hum = new LightSaberHum(StarWarsMaterials.LIGHTSABER_HUMMING_ID, SoundCategory.NEUTRAL);
            MinecraftClient.getInstance().getSoundManager().play(hum);
        });
    }
}