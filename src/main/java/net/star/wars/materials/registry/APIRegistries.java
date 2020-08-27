package net.star.wars.materials.registry;

import net.fabricmc.fabric.api.object.builder.v1.client.model.FabricModelPredicateProviderRegistry;
import net.minecraft.client.item.ModelPredicateProvider;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

import static net.star.wars.materials.StarWarsMaterials.MOD_ID;

public class APIRegistries {
    public static void initialize(){
        FabricModelPredicateProviderRegistry.register(ItemRegistry.LIGHTSABER_ITEM, new Identifier(MOD_ID, "test"), new ModelPredicateProvider() {
            @Override
            public float call(ItemStack itemStack, ClientWorld world, LivingEntity livingEntity) {
                //your code here
                if (livingEntity == null) {
                    return 0.0F;
                } else {
                    return livingEntity.getActiveItem() != itemStack ? 0.0F : (float)(itemStack.getMaxUseTime() - livingEntity.getItemUseTimeLeft()) / 20.0F;
                }
            }
        });
    }
}
