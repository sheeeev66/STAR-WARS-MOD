package net.star.wars.materials.sounds;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.sound.MovingSoundInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.sound.SoundCategory;
import net.star.wars.materials.registry.SoundRegistry;
import net.star.wars.materials.tools.lightsaber.AbstractLightsaberItem;

@Environment(EnvType.CLIENT)
public class LightSaberHum extends MovingSoundInstance {
    private final PlayerEntity player;
    // please help me! How do I call this class in @link LightsaberItem?
    //call MinecraftClient.getInstance().getSoundManager().play(); MinecraftClient.getInstance().getMusicTracker().stop(); and make a constructor and pass a playerentity and a soundcategory
    //also we are calling this sound in @link LightsaberHilt, not lightsaber item


    public LightSaberHum(PlayerEntity playerIn, SoundCategory soundCategory) {
        super(SoundRegistry.LIGHTSABER_HUMMING, soundCategory);
        this.player = playerIn;
        this.repeat = true;
        this.repeatDelay = 0;
        this.volume = 0.7F;
        this.x = (float) playerIn.getX();
        this.y = (float) playerIn.getY();
        this.z = (float) playerIn.getZ();

    }


	public void tick() {
        Item heldItem = this.player.getMainHandStack().getItem();
        this.x = player.getX();
        this.y = player.getY();
        this.z = player.getZ();
        if (!(heldItem instanceof AbstractLightsaberItem)) {
            this.volume = 0;
        }
    }
}
