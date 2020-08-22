package net.star.wars.materials;

import net.fabricmc.api.ModInitializer;
import net.star.wars.materials.registry.BlockRegistry;
import net.star.wars.materials.registry.EntityRegistry;
import net.star.wars.materials.registry.ItemRegistry;
import net.star.wars.materials.registry.SoundRegistry;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class StarWarsMaterials implements ModInitializer
{
    public static final Logger LOGGER = LogManager.getLogger();

    public static final String MOD_ID = "starwarsmaterials";
    
    @Override
    public void onInitialize()
    {
        LOGGER.log(Level.DEBUG, "Initialized Star Wars Mod!");
        BlockRegistry.initalize();
        EntityRegistry.initialize();
        ItemRegistry.initialize();
        SoundRegistry.initialize();
    }
    
}