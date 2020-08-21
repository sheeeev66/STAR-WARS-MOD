package net.star.wars.materials.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.star.wars.materials.registry.SoundRegistry;
import net.star.wars.materials.tools.AbstractLightsaberItem;

@Environment(EnvType.CLIENT)
@Mixin(Entity.class)
public class EntityInject {

    @Shadow public World world;
    //Will not work until sound is actually added
    // so we will use anvil sound
    @Inject(method = "playStepSound", at = @At("TAIL"))
    private final void playStepSound(BlockPos pos, BlockState state, CallbackInfo ci) {
        PlayerEntity playerEntity = this.world.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 10, false);
        System.out.println(playerEntity);
        if (playerEntity != null) {
            ItemStack stack = playerEntity.getStackInHand(Hand.MAIN_HAND);

            System.out.println(stack);
            if (stack.getItem() instanceof AbstractLightsaberItem){
                System.out.println("played Sound");
                playerEntity.playSound(SoundRegistry.LIGHTSABER_MOVE_FOUR /* can you make it choose between 1 - 4? */ , 1f, 1f);
            }
        }
    }
}
