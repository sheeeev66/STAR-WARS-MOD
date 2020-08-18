package net.star.wars.materials.registry;

import static net.star.wars.materials.StarWarsMaterials.MOD_ID;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.BlockItem;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;
import net.star.wars.materials.armor.BaseArmor;
import net.star.wars.materials.item.BeskarIngot;
import net.star.wars.materials.item.KyberCrystalsColors.BlueKyberCrystal;
import net.star.wars.materials.item.KyberCrystalsColors.GreenKyberCrystal;
import net.star.wars.materials.item.CookedPorg;
import net.star.wars.materials.item.DeathSticks;
import net.star.wars.materials.item.KyberCrystal;
import net.star.wars.materials.item.LightsaberHilt;
import net.star.wars.materials.item.Plastoid;
import net.star.wars.materials.item.RawPorg;
import net.star.wars.materials.tools.AbstractLightsaberItem;
import net.star.wars.materials.tools.LightsaberBlue;

public class ItemRegistry {
    public static final BeskarIngot BESKAR_INGOT = new BeskarIngot(new Item.Settings());
    public static final Plastoid PLASTOID = new Plastoid(new Item.Settings());

    // kyber crystals
    public static final KyberCrystal KYBER_CRYSTAL =  new KyberCrystal(new Item.Settings());

    public static final BlueKyberCrystal BLUE_KYBER_CRYSTAL =  new BlueKyberCrystal(new Item.Settings());
    public static final GreenKyberCrystal GREEN_KYBER_CRYSTAL =  new GreenKyberCrystal(new Item.Settings());

    // Food
    public static final RawPorg RAW_PORG = new RawPorg(new Item.Settings().group(ItemGroup.FOOD)
            .food(new FoodComponent.Builder().hunger(2).saturationModifier(1.4f).meat().build()));
    public static final CookedPorg COOKED_PORG = new CookedPorg(new Item.Settings().group(ItemGroup.FOOD)
            .food(new FoodComponent.Builder().hunger(5).saturationModifier(4.6f).meat().build()));

    public static final DeathSticks DEATH_STICKS = new DeathSticks(new Item.Settings()
            .food(new FoodComponent.Builder().alwaysEdible().statusEffect(new StatusEffectInstance(StatusEffects.NAUSEA), 20*60*2).build()));

    // Lightsabers
    public static final LightsaberHilt LIGHTSABER_HILT = new LightsaberHilt(new Item.Settings().group(GroupRegistry.Weapons_Group).maxCount(1));
    public static final AbstractLightsaberItem LIGHTSABER_ITEM = new AbstractLightsaberItem(new Item.Settings().rarity(Rarity.EPIC).maxCount(1));

    public static final LightsaberBlue LIGHTSABER_BLUE = new LightsaberBlue(new Item.Settings().group(GroupRegistry.Weapons_Group));


    public static void initialize(){
        // random items
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "beskar_ingot"), BESKAR_INGOT);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "plastoid"), PLASTOID);

        // kyber crystals
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "kyber_crystal"), KYBER_CRYSTAL);
        
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "blue_kyber_crystal"), BLUE_KYBER_CRYSTAL);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "green_kyber_crystal"), GREEN_KYBER_CRYSTAL);


        // spawn eggs
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "porg_spawn_egg"),
                new SpawnEggItem(EntityRegistry.PORG, 0xc2c2c2, 0x7e5e60, new Item.Settings().group(GroupRegistry.ANIMALS_GROUP)));

        // food
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "raw_porg"), RAW_PORG);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "cooked_porg"), COOKED_PORG);

        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "death_sticks"), DEATH_STICKS);

        // items
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "lightsaber_hilt"), LIGHTSABER_HILT);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "lightsaber"), LIGHTSABER_ITEM);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "lightsaber_blue"), LIGHTSABER_BLUE);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "beskar_ore"), new BlockItem(BlockRegistry.BESKAR_ORE, new Item.Settings()));
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "ice_crystal"), new BlockItem(BlockRegistry.ICE_CRYSTALS, new Item.Settings()));


        //////////////////// armor ////////////////////
        // mandalorian armor
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "mandalorian_helmet"), new BaseArmor(ArmorRegistry.MANDALORIAN_ARMOR, EquipmentSlot.HEAD));
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "mandalorian_chestplate"), new BaseArmor(ArmorRegistry.MANDALORIAN_ARMOR, EquipmentSlot.CHEST));
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "mandalorian_leggings"), new BaseArmor(ArmorRegistry.MANDALORIAN_ARMOR, EquipmentSlot.LEGS));
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "mandalorian_boots"), new BaseArmor(ArmorRegistry.MANDALORIAN_ARMOR, EquipmentSlot.FEET));
    }
}
