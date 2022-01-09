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

        /*
        FabricModelPredicateProviderRegistry.register(ItemRegistry.LIGHTSABER_TEST, new Identifier(MOD_ID, "lightsaber_on"), new ModelPredicateProvider() {

			@Override
			public float call(ItemStack stack, ClientWorld world, LivingEntity entity) {
				return LightsaberTest.isOn(stack) ? 1.0F : 0.0F;
			}
            
        }); */

        FabricModelPredicateProviderRegistry.register(ItemRegistry.LIGHTSABER_TEST, new Identifier(MOD_ID, "lightsaber_animation_on"), new ModelPredicateProvider() {
            @Override
            public float call(ItemStack stack, ClientWorld world, LivingEntity entity) {
                float turnOn = 20.0F;
                return turnOn <= 20.0F ? turnOn >= 2 ? 2 : (turnOn - 1.0F) / 20.0F /* if first bool false->*/ : (turnOn - 1.0F) / 20.0F;
            }
        });

        /* FabricModelPredicateProviderRegistry.register(ItemRegistry.LIGHTSABER_TEST, new Identifier(MOD_ID, "lightsaber_animation_off"), new ModelPredicateProvider() {

            @Override
            public float call(ItemStack stack, ClientWorld world, LivingEntity entity) {
                if (LightsaberTest.bladeTimer(stack) >= 0.4F) {
                    LightsaberTest.setBladeTimer(stack, LightsaberTest.getBladeTimer(stack) - 0.01F);
                    LightsaberTest.setBladeTimer(stack, LightsaberTest.getBladeTimer(stack));
                }
                if (LightsaberTest.bladeTimer(stack) >= 0.3F) {
                    LightsaberTest.setBladeTimer(stack, LightsaberTest.getBladeTimer(stack) - 0.01F);
                    LightsaberTest.setBladeTimer(stack, LightsaberTest.getBladeTimer(stack));
                }
                if (LightsaberTest.bladeTimer(stack) >= 0.2F) {
                    LightsaberTest.setBladeTimer(stack, LightsaberTest.getBladeTimer(stack) - 0.01F);
                    LightsaberTest.setBladeTimer(stack, LightsaberTest.getBladeTimer(stack));
                }
                if (LightsaberTest.bladeTimer(stack) >= 0.1F) {
                    LightsaberTest.setBladeTimer(stack, LightsaberTest.getBladeTimer(stack) - 0.01F);
                    LightsaberTest.setBladeTimer(stack, LightsaberTest.getBladeTimer(stack));
                }
                if (LightsaberTest.bladeTimer(stack) != 0) {
                    LightsaberTest.setBladeTimer(stack, LightsaberTest.getBladeTimer(stack) * 0);
                    LightsaberTest.setBladeTimer(stack, LightsaberTest.getBladeTimer(stack));
                }
                System.out.println(LightsaberTest.bladeTimer(stack));
                return LightsaberTest.bladeTimer(stack);
            }
        }); */
    }

}