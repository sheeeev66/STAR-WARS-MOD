package net.star.wars.materials.mixin;


import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.WindowEventHandler;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.snooper.SnooperListener;
import net.minecraft.util.thread.ReentrantThreadExecutor;
import net.star.wars.materials.registry.SoundRegistry;
import net.star.wars.materials.tools.lightsaber.AbstractLightsaberItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(MinecraftClient.class)
public abstract class MinecraftClientMixin extends ReentrantThreadExecutor<Runnable> implements SnooperListener, WindowEventHandler {
    private SoundEvent event;
        private SoundEvent event2;

    @Shadow 
    public ClientPlayerEntity player;


    @Shadow  public HitResult crosshairTarget;

    public MinecraftClientMixin(String string) {
        super(string);
    }

    //this just plays sounds when we leftclick the lightsaber
    //it will play different sounds depending if player hit a block, entity, or it didnt hit anything
    @Environment(EnvType.CLIENT)
    @Inject(method = "doAttack", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/ClientPlayerEntity;swingHand(Lnet/minecraft/util/Hand;)V"))
    private void mixin2(CallbackInfo ci){
        if (this.player.getStackInHand(Hand.MAIN_HAND).getItem() instanceof AbstractLightsaberItem ) {

            if (this.crosshairTarget.getType().equals(HitResult.Type.MISS)) {
                Random random = new Random();
                int rand = random.nextInt(4);
                switch (rand) {
                    case 1:
                        event2 = SoundRegistry.LIGHTSABER_MOVE_TWO;
                    case 2:
                        event2 = SoundRegistry.LIGHTSABER_MOVE_THREE;
                    case 3:
                        event2 = SoundRegistry.LIGHTSABER_MOVE_FOUR;
                    default:
                        event2 = SoundRegistry.LIGHTSABER_MOVE_ONE;
                }
                this.player.playSound(event2, 0.7f, 0.7f);
                System.out.println(event2);
            }
            if (this.crosshairTarget.getType().equals(HitResult.Type.BLOCK) || this.crosshairTarget.getType().equals(HitResult.Type.ENTITY)){
                Random random = new Random();
                int rand = random.nextInt(4);
                switch (rand) {
                    case 1:
                        event = SoundRegistry.LIGHTSABER_MOVE_TWO;
                    case 2:
                        event = SoundRegistry.LIGHTSABER_MOVE_THREE;
                    case 3:
                        event = SoundRegistry.LIGHTSABER_MOVE_FOUR;
                    default:
                        event = SoundRegistry.LIGHTSABER_MOVE_ONE;
                }
                this.player.playSound(event, 0.7f, 0.7f);
                System.out.println(event);
            }
        }
    }

}
