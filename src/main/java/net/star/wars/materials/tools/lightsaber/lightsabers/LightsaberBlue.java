package net.star.wars.materials.tools.lightsaber.lightsabers;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.star.wars.materials.tools.lightsaber.AbstractLightsaberItem;
import net.star.wars.materials.util.LightSaberUtil;

import java.util.List;

public class LightsaberBlue extends AbstractLightsaberItem {

    public LightsaberBlue(Settings settings, ToolMaterial material) {
        super(settings, material);
    }

    @Environment(EnvType.CLIENT)
    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(new TranslatableText("lightsaber.blue.tooltip"));
    }
    @Environment(EnvType.CLIENT)
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        LightSaberUtil.setLightsaberIndex(1);
        return super.use(world, player, hand);
    }
}
