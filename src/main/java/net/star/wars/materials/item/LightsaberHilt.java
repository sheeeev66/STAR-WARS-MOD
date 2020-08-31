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
import net.star.wars.materials.registry.ItemRegistry;
import net.star.wars.materials.registry.SoundRegistry;
import net.star.wars.materials.sounds.LightSaberHum;
import net.star.wars.materials.tools.lightsaber.AbstractLightsaberItem;
import net.star.wars.materials.util.LightSaberUtil;

public class LightsaberHilt extends Item {
    private AbstractLightsaberItem item;

    public LightsaberHilt(Settings settings) {
        super(settings);
    }

    @Environment(EnvType.CLIENT) 
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        if (hand == Hand.MAIN_HAND) {
            int slot = player.inventory.selectedSlot;
            player.inventory.removeStack(slot);
            switch (LightSaberUtil.getLightsaberIndex()){
                case 0:
                    item = ItemRegistry.LIGHTSABER_ITEM;
                    break;
                case 1:
                    item = ItemRegistry.LIGHTSABER_BLUE;
                    break;
            }
            player.inventory.insertStack(slot, new ItemStack(item));
            player.playSound(SoundRegistry.LIGHTSABER_ON, 3.0f, 1.0f);
            if (world.isClient) {
                MinecraftClient.getInstance().getSoundManager().play(new LightSaberHum(player, SoundCategory.AMBIENT));
                MinecraftClient.getInstance().getMusicTracker().stop();
            }
        }
        return new TypedActionResult<>(ActionResult.SUCCESS, player.getStackInHand(hand));
    }
}
