package net.star.wars.materials.registry;

import net.fabricmc.fabric.api.object.builder.v1.client.model.FabricModelPredicateProviderRegistry;
import net.minecraft.client.item.ModelPredicateProvider;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.star.wars.materials.LightsaberTest;

import static net.star.wars.materials.StarWarsMaterials.MOD_ID;

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

            @Override
            public float call(ItemStack stack, ClientWorld world, LivingEntity entity) {
                return  MathHelper.clamp(LightsaberTest.getBladeTimer(stack) / 1.0f, 0.0F, 1.0F);
            }
            
        });
    }
    
}