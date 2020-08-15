package net.star.wars.materials.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;

public class BeskarOre extends Block{
    

	public BeskarOre() {
        super(FabricBlockSettings.of(Material.METAL).strength(4, 12));
    }

}
