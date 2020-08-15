package net.star.wars.materials.item;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.star.wars.materials.StarWarsMaterials;
import net.star.wars.materials.sounds.LightSaberHum;

public class LightsaberHilt extends Item {

    public LightsaberHilt(Settings settings) {
        super(settings);
    }
    @Environment(EnvType.CLIENT)
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        if (player.getStackInHand(hand).getItem() == StarWarsMaterials.LIGHTSABER_HILT) {
            player.inventory.removeStack(player.inventory.getSlotWithStack(player.getStackInHand(hand)));
            player.inventory.insertStack(new ItemStack(StarWarsMaterials.LIGHTSABER_ITEM));
            if(world.isClient) {
                player.playSound(StarWarsMaterials.LIGHTSABER_ON, 3.0f, 1.0f);
                MinecraftClient.getInstance().getSoundManager().play(new LightSaberHum(player, SoundCategory.AMBIENT));
            }
        }


        return new TypedActionResult<ItemStack>(ActionResult.SUCCESS, player.getStackInHand(hand));
    }
}
