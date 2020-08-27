package net.star.wars.materials.mixin;

import net.fabricmc.fabric.mixin.object.builder.ModelPredicateProviderRegistrySpecificAccessor;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.util.Identifier;
import net.star.wars.materials.registry.ItemRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ModelPredicateProviderRegistry.class)
public abstract class ModelPredicateProviderRegistryMixin {

    boolean lihgtsaberOn;
    
    @Inject(method="<clinit>",at=@At("TAIL"))
    private void clinit(CallbackInfo ci) {
        ModelPredicateProviderRegistrySpecificAccessor.callRegister(ItemRegistry.LIGHTSABER_ITEM, new Identifier("stage_one"), (itemStack, clientWorld, livingEntity) -> {
            
            return livingEntity != null && livingEntity.isUsingItem() && livingEntity.getActiveItem() == itemStack ? 1.0F : 0.0F;
        });
    }
}