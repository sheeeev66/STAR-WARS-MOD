package net.star.wars.materials.registry;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.star.wars.materials.entities.porg.PorgEntity;

import static net.star.wars.materials.StarWarsMaterials.MOD_ID;

public class EntityRegistry {
    // porg
    public static final EntityType<PorgEntity> PORG = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(MOD_ID, "porg"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, PorgEntity::new).dimensions(EntityDimensions.fixed(0.75f, 0.75f)).build()
    );
    public static void initialize(){
        FabricDefaultAttributeRegistry.register(PORG, PorgEntity.createPorgAttributes());
    }
}
