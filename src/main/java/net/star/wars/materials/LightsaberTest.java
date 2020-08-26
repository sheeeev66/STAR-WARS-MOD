package net.star.wars.materials;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.star.wars.materials.registry.SoundRegistry;
import net.star.wars.materials.sounds.LightSaberHum;

public class LightsaberTest extends Item {

    public LightsaberTest(Settings settings) {
        super(settings);
    }

    boolean lightsaberOn;

    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        /////// nbt
        // on/off
        ItemStack stack = player.getStackInHand(hand);
        CompoundTag tag = stack.getOrCreateTag();
        lightsaberOn = tag.getBoolean("lihgtsaber_on");
        
        // play sound on/off
        player.playSound(
            lightsaberOn ? SoundRegistry.LIGHTSABER_ON : SoundRegistry.LIGHTSABER_OFF,
                1.0f, 1.0f
        );

        if (lightsaberOn) {
            MinecraftClient.getInstance().getSoundManager().play(new LightSaberHum(player, SoundCategory.AMBIENT));
        }

        // togle
        lightsaberOn = !lightsaberOn;
        tag.putBoolean("lihgtsaber_on", lightsaberOn);


        return new TypedActionResult<ItemStack>(ActionResult.SUCCESS, player.getStackInHand(hand));
    }
}