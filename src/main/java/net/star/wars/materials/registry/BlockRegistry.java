package net.star.wars.materials.registry;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.star.wars.materials.block.BeskarOre;
import net.star.wars.materials.block.IceCrystals;

import static net.star.wars.materials.StarWarsMaterials.MOD_ID;

public class BlockRegistry {
    public static final IceCrystals ICE_CRYSTALS = new IceCrystals();
    ///// Ores ////
    // Beskar
    public static final BeskarOre BESKAR_ORE = new BeskarOre();

    
    public static void initalize() {
        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "beskar_ore"), BESKAR_ORE);
        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "ice_crystal"), ICE_CRYSTALS);
    }
}
