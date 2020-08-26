package net.star.wars.materials.mixin;


import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.star.wars.materials.registry.ItemRegistry;
import net.star.wars.materials.tools.lightsaber.AbstractLightsaberItem;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity {


    @Shadow @Final public PlayerInventory inventory;

    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "tick", at = @At("TAIL"))
    private void tick(CallbackInfo ci){
        int slot = this.inventory.selectedSlot;
        if (!(this.inventory.getStack(slot).getItem() instanceof AbstractLightsaberItem)){
            for (int i = 0; i < 9; i++ ){
                if (this.inventory.getStack(i).getItem() instanceof AbstractLightsaberItem){
                    this.inventory.removeStack(i);
                    this.inventory.insertStack(i, new ItemStack(ItemRegistry.LIGHTSABER_HANDLE));
                }
            }
        }
    }







}
