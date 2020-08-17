package net.star.wars.materials.registry.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;
import net.star.wars.materials.registry.BlockRegistry;

@Environment(EnvType.CLIENT)
public class ClientBlockRegistry {
    public static void initialize(){
        BlockRenderLayerMap.INSTANCE.putBlock(BlockRegistry.ICE_CRYSTALS, RenderLayer.getTranslucent());
    }
}
