package net.star.wars.materials;

import net.fabricmc.api.ModInitializer;
import net.star.wars.materials.registry.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class StarWarsMaterials implements ModInitializer
{
    public static final Logger LOGGER = LogManager.getLogger();

    public static final String MOD_ID = "starwarsmaterials";
    
    @Override
    public void onInitialize()
    {
        LOGGER.info("Initialized Star Wars Mod!");
        BlockRegistry.initalize();
        EntityRegistry.initialize();
        ItemRegistry.initialize();
        SoundRegistry.initialize();
        EventRegistry.initialize();
        ModelPredicatesRegistry.initialize();
    }
    
}