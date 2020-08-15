package net.star.wars.materials.tools;

import java.util.List;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sound.SoundCategory;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.star.wars.materials.StarWarsMaterials;
import net.star.wars.materials.sounds.LightSaberHum;

public class LightsaberItem extends Item {

    public LightsaberItem(Settings settings) {
        super(settings);
    }

    boolean lightsaberOn;
    
    @Environment(EnvType.CLIENT)
    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);
    }

    @Environment(EnvType.CLIENT)
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        // nbt
        ItemStack stack = playerEntity.getMainHandStack();
        CompoundTag tag = stack.getOrCreateTag();
        lightsaberOn = tag.getBoolean("on");

        playerEntity.playSound(
            lightsaberOn ? StarWarsMaterials.LIGHTSABER_ON : StarWarsMaterials.LIGHTSABER_OFF,
            1.0f, 1.0f
        );
        
        lightsaberOn =  !lightsaberOn;
        
        tag.putBoolean("on", lightsaberOn); 

        

        if (lightsaberOn = true) {
            if (hand == Hand.MAIN_HAND) {
                if (!world.isClient){
                    playerEntity.playSound(StarWarsMaterials.LIGHTSABER_HUMMING, 3.0f, 1.0f);
                }
                
                if (world.isClient()) {
                    MinecraftClient.getInstance().getSoundManager().play(new LightSaberHum(playerEntity, SoundCategory.AMBIENT));
                    MinecraftClient.getInstance().getMusicTracker().stop();
                }

            }
        }
        
        return new TypedActionResult<ItemStack>(ActionResult.SUCCESS, playerEntity.getStackInHand(hand));
    }
}
