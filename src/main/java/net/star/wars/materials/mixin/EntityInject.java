package net.star.wars.materials.mixin;


import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.star.wars.materials.registry.SoundRegistry;
import net.star.wars.materials.tools.lightsaber.AbstractLightsaberItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
@Environment(EnvType.CLIENT)
@Mixin(Entity.class)
public abstract class EntityInject {

    @Shadow public World world;

    @Shadow public abstract double getX();

    @Shadow public abstract double getY();

    @Shadow public abstract double getZ();

    //Will not work until sound is actually added
    // so we will use anvil sound
    @Inject(method = "playStepSound", at = @At("TAIL"))
    private final void playStepSound(BlockPos pos, BlockState state, CallbackInfo ci) {
        PlayerEntity playerEntity = this.world.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 3, true);
      //  System.out.println(playerEntity);
        if (playerEntity != null) {
            ItemStack stack = playerEntity.getStackInHand(Hand.MAIN_HAND);
          //  System.out.println(stack);
            if (stack.getItem() instanceof AbstractLightsaberItem){
          //      System.out.println("played Sound");
                playerEntity.playSound(SoundRegistry.LIGHTSABER_MOVE_ONE, 0.3f, 0.3f);
            }
        }
    }
    @Inject(method = "playSwimSound", at = @At("TAIL"))
    private final void playSwimSound(float volume, CallbackInfo ci){
        PlayerEntity playerEntity = this.world.getClosestPlayer(getX(), getY(), getZ(), 3, true);
    //    System.out.println(playerEntity);
        if (playerEntity != null) {
            ItemStack stack = playerEntity.getStackInHand(Hand.MAIN_HAND);
      //      System.out.println(stack);
            if (stack.getItem() instanceof AbstractLightsaberItem){
        //        System.out.println("played Sound");
                playerEntity.playSound(SoundRegistry.LIGHTSABER_MOVE_TWO, 0.3f, 0.3f);
            }
        }
    }

}
