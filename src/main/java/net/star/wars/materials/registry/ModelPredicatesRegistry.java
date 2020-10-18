package net.star.wars.materials.registry;

import static net.star.wars.materials.StarWarsMaterials.MOD_ID;

import net.fabricmc.fabric.api.object.builder.v1.client.model.FabricModelPredicateProviderRegistry;
import net.minecraft.client.item.ModelPredicateProvider;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.star.wars.materials.LightsaberTest;

public class ModelPredicatesRegistry {

    public static void initialize() {
        // lightsaber
        FabricModelPredicateProviderRegistry.register(ItemRegistry.LIGHTSABER_TEST, new Identifier(MOD_ID, "lightsaber_on"), new ModelPredicateProvider() {

			@Override
			public float call(ItemStack stack, ClientWorld world, LivingEntity entity) {
				return LightsaberTest.isOn(stack) ? 1.0F : 0.0F;
			}
            
        });
        FabricModelPredicateProviderRegistry.register(ItemRegistry.LIGHTSABER_TEST, new Identifier(MOD_ID, "lightsaber_animation"), new ModelPredicateProvider() {
            float timer = 0.0f;

            @Override
            public float call(ItemStack stack, ClientWorld world, LivingEntity entity) {
                if (LightsaberTest.isOn(stack) == true) {
                    if (timer < 0.4f) {
                        timer += 0.1f;
                    }
                }

                if (LightsaberTest.isOn(stack) == false) {
                    if (timer > 0.0f) {
                        timer -= 0.1f;
                    }
                }
                System.out.println(LightsaberTest.isOn(stack));
                System.out.println(timer);
                return timer;

            }
        });
    }

}