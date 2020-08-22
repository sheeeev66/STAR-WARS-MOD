package net.star.wars.materials.registry;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;

import static net.star.wars.materials.StarWarsMaterials.MOD_ID;
public class GroupRegistry {
    /////////////// Groups ///////////////
    public static final ItemGroup ARMOR_GROUP = FabricItemGroupBuilder.build(
            new Identifier(MOD_ID, "general"),
            () -> new ItemStack(Items.DIAMOND_CHESTPLATE));

    public static final ItemGroup MATERIALS_GROUP = FabricItemGroupBuilder.create(
            new Identifier(MOD_ID, "materials"))
            .icon(() -> new ItemStack(ItemRegistry.BESKAR_INGOT))
            .appendItems(stacks ->
            {
                stacks.add(new ItemStack(ItemRegistry.BESKAR_INGOT));
                stacks.add(new ItemStack(BlockRegistry.BESKAR_ORE));
                stacks.add(new ItemStack(ItemRegistry.PLASTOID));
                stacks.add(new ItemStack(ItemRegistry.KYBER_CRYSTAL));
                stacks.add(new ItemStack(BlockRegistry.ICE_CRYSTALS));
            })
            .build();
    public static final ItemGroup Weapons_Group = FabricItemGroupBuilder.build(
            new Identifier(MOD_ID, "general"),
            () -> new ItemStack(ItemRegistry.LIGHTSABER_BLUE));

    public static final ItemGroup FOOD_GROUP = FabricItemGroupBuilder.create(
            new Identifier(MOD_ID, "food"))
            .icon(() -> new ItemStack(ItemRegistry.COOKED_PORG))
            .appendItems(stacks ->
            {
                stacks.add(new ItemStack(ItemRegistry.RAW_PORG));
                stacks.add(new ItemStack(ItemRegistry.COOKED_PORG));
            })
            .build();

    public static final ItemGroup ANIMALS_GROUP = FabricItemGroupBuilder.build(
            new Identifier(MOD_ID, "animals"),
            () -> new ItemStack(Items.SALMON_SPAWN_EGG));


}
