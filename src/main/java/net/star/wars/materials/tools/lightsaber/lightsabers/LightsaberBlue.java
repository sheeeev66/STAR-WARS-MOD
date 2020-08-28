package net.star.wars.materials.tools.lightsaber.lightsabers;

import net.minecraft.item.ToolMaterial;
import net.star.wars.materials.tools.lightsaber.AbstractLightsaberItem;

public class LightsaberBlue extends AbstractLightsaberItem {

    public LightsaberBlue(Settings settings, ToolMaterial material) {
        super(settings, material);
    }

    @Override
    public int getLightsaberIndex() {
        return 1;
    }
}
