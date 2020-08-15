package net.star.wars.materials.sounds;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.sound.MovingSoundInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.sound.SoundCategory;
import net.star.wars.materials.StarWarsMaterials;

@Environment(EnvType.CLIENT)
public class LightSaberHum extends MovingSoundInstance {
    private final PlayerEntity player;
	// hello this is acoo sad
    //i am a flat earther of course
    //but u know that
    public LightSaberHum(PlayerEntity playerIn, SoundCategory soundCategory) {
        super(StarWarsMaterials.LIGHTSABER_HUMMING, soundCategory);
        this.player = playerIn;
        this.repeat = true;
        this.repeatDelay = 0;
        this.volume = 0.1F;
        this.x = (float) playerIn.getX();
        this.y = (float) playerIn.getY();
        this.z = (float) playerIn.getZ();
    }


	public void tick() {
        Item heldItem = this.player.getMainHandStack().getItem();
        this.x = player.getX();
        this.y = player.getY();
        this.z = player.getZ();
        if (heldItem != StarWarsMaterials.LIGHTSABER_ITEM) {
            this.volume = 0;
        }
    }
}
