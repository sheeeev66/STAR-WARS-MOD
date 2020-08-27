package net.star.wars.materials;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
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

    public static boolean isOn(ItemStack stack) {
        CompoundTag tag = stack.getOrCreateTag();
        return tag.getBoolean("lightsaber_on");
    }

    public static void setOn(ItemStack stack, boolean isOn) {
        CompoundTag tag = stack.getOrCreateTag();
        tag.putBoolean("lightsaber_on", isOn);
    }

    public static float getBladeTimer(ItemStack stack) {
        CompoundTag tag = stack.getOrCreateTag();
        return tag.getFloat("blade_timer");
    }

    public static void setBladeTimer(ItemStack stack, float bladeTimer) {
        CompoundTag tag = stack.getOrCreateTag();
        tag.putFloat("blade_timer", bladeTimer);
    }
    @Environment(EnvType.CLIENT)
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);
        boolean lightsaberOn = isOn(stack);
        float bladeTimer = getBladeTimer(stack);

        setOn(stack, !lightsaberOn);

        // play sound on/off
        player.playSound(lightsaberOn ? SoundRegistry.LIGHTSABER_ON : SoundRegistry.LIGHTSABER_OFF, 1.0f, 1.0f);

        // use boolean operators
        if (world.isClient && lightsaberOn) {
            MinecraftClient client = MinecraftClient.getInstance();
            client.getSoundManager().play(new LightSaberHum(player, SoundCategory.AMBIENT));
            client.getMusicTracker().stop();
        }

        /*
        if (isOn(stack) == true) {
            while (bladeTimer < 0.2f) {
                bladeTimer += 0.1f;
                setBladeTimer(stack, bladeTimer);
            }
        } else {
            while (bladeTimer > 0) {
                bladeTimer -= 0.1f;
                setBladeTimer(stack, bladeTimer);
            }
        }
        */
        

        if (bladeTimer == 0.0f) {
            System.out.println("0");
        }

        if (bladeTimer == 0.1f) {
            System.out.println("1");
        }

        if (bladeTimer == 0.2f) {
            System.out.println("2");
        }


        return new TypedActionResult<ItemStack>(ActionResult.SUCCESS ,player.getStackInHand(hand));
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        float bladeTimer = getBladeTimer(stack);

        if (isOn(stack) == true) {
            if (bladeTimer < 0.2f) {
                bladeTimer += 0.1f;
                setBladeTimer(stack, bladeTimer);
            }
        } else {
            if (bladeTimer > 0) {
                bladeTimer -= 0.1f;
                setBladeTimer(stack, bladeTimer);
            }
        }
        
    }
}