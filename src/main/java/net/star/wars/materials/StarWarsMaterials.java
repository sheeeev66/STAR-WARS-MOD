package net.star.wars.materials;

import net.fabricmc.api.ModInitializer;
import net.star.wars.materials.registry.BlockRegistry;
import net.star.wars.materials.registry.EntityRegistry;
import net.star.wars.materials.registry.ItemRegistry;
import net.star.wars.materials.registry.SoundRegistry;


public class StarWarsMaterials implements ModInitializer
{   
    public static final String MOD_ID = "starwarsmaterials";
    
    @Override
    public void onInitialize()
    {
        BlockRegistry.initalize();
        EntityRegistry.initialize();
        ItemRegistry.initialize();
        SoundRegistry.initialize();


    }
    
}