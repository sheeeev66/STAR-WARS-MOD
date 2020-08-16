package net.star.wars.materials;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.BlockItem;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.star.wars.materials.armor.BaseArmor;
import net.star.wars.materials.armor.BeskarArmorMaterial;
import net.star.wars.materials.block.BeskarOre;
import net.star.wars.materials.block.IceCrystals;
import net.star.wars.materials.entities.porg.PorgEntity;
import net.star.wars.materials.item.BeskarIngot;
import net.star.wars.materials.item.CookedPorg;
import net.star.wars.materials.item.DeathSticks;
import net.star.wars.materials.item.KyberCrystal;
import net.star.wars.materials.item.LightsaberHilt;
import net.star.wars.materials.item.Plastoid;
import net.star.wars.materials.item.RawPorg;
import net.star.wars.materials.tools.LightsaberBlue;
import net.star.wars.materials.tools.LightsaberItem;

public class StarWarsMaterials implements ModInitializer
{   
    public static final String MOD_ID = "starwarsmaterials";
    
    
    /////////////// instances ///////////////
    //// Items ////
    // Random materials
    public static final BeskarIngot BESKAR_INGOT = new BeskarIngot(new Item.Settings());
    public static final Plastoid PLASTOID = new Plastoid(new Item.Settings());
    public static final KyberCrystal KYBER_CRYSTAL =  new KyberCrystal(new Item.Settings());

    // Food
    public static final RawPorg RAW_PORG = new RawPorg(new Item.Settings().group(ItemGroup.FOOD)
    .food(new FoodComponent.Builder().hunger(2).saturationModifier(1.4f).meat().build()));
    public static final CookedPorg COOKED_PORG = new CookedPorg(new Item.Settings().group(ItemGroup.FOOD)
    .food(new FoodComponent.Builder().hunger(5).saturationModifier(4.6f).meat().build()));

    public static final DeathSticks DEATH_STICKS = new DeathSticks(new Item.Settings()
    .food(new FoodComponent.Builder().alwaysEdible().statusEffect(StatusEffectInstance(StatusEffects.NAUSEA, 20*60*2), 1).build()));

    // Lightsabers
    public static final LightsaberHilt LIGHTSABER_HILT = new LightsaberHilt(new Item.Settings());
    public static final LightsaberItem LIGHTSABER_ITEM = new LightsaberItem(new Item.Settings());
    
    public static final LightsaberBlue LIGHTSABER_BLUE = new LightsaberBlue(new Item.Settings());
   
    // Networking
    public static final Identifier LIGHTSABER_HUM_PACKET_ID = new Identifier(MOD_ID, "lighsaberhum");

    /////////////// Blocks ///////////////

    // Ice Crystals
    public static final IceCrystals ICE_CRYSTALS = new IceCrystals();
    ///// Ores ////
    // Beskar
    public static final BeskarOre BESKAR_ORE = new BeskarOre();
    
    /////////////// Armor ///////////////
    //// mandalorian armor instance ////
    public static final ArmorMaterial MANDALORIAN_ARMOR = new BeskarArmorMaterial();

    /////////////// Groups ///////////////
    public static final ItemGroup ARMOR_GROUP = FabricItemGroupBuilder.build(
		new Identifier(MOD_ID, "general"),
		() -> new ItemStack(Items.DIAMOND_CHESTPLATE));
 
	public static final ItemGroup MATERIALS_GROUP = FabricItemGroupBuilder.create(
		new Identifier(MOD_ID, "materials"))
        .icon(() -> new ItemStack(StarWarsMaterials.BESKAR_INGOT))
        .appendItems(stacks ->
        {
            stacks.add(new ItemStack(StarWarsMaterials.BESKAR_INGOT));
            stacks.add(new ItemStack(StarWarsMaterials.BESKAR_ORE));
            stacks.add(new ItemStack(StarWarsMaterials.PLASTOID));
            stacks.add(new ItemStack(StarWarsMaterials.KYBER_CRYSTAL));
            stacks.add(new ItemStack(StarWarsMaterials.ICE_CRYSTALS));
        })
        .build();

    public static final ItemGroup FOOD_GROUP = FabricItemGroupBuilder.create(
		new Identifier(MOD_ID, "food"))
        .icon(() -> new ItemStack(StarWarsMaterials.COOKED_PORG))
        .appendItems(stacks ->
        {
            stacks.add(new ItemStack(StarWarsMaterials.RAW_PORG));
            stacks.add(new ItemStack(StarWarsMaterials.COOKED_PORG));
        })
        .build();

    public static final ItemGroup ANIMALS_GROUP = FabricItemGroupBuilder.build(
        new Identifier(MOD_ID, "animals"),
        () -> new ItemStack(Items.SALMON_SPAWN_EGG));
    // ...
    
    
    ///////////// Ore Generation /////////////

    ////////////// entities ////////////////
    //porg
    public static final EntityType<PorgEntity> PORG = Registry.register(
        Registry.ENTITY_TYPE,
        new Identifier(MOD_ID, "porg"),
        FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, PorgEntity::new).dimensions(EntityDimensions.fixed(0.75f, 0.75f)).build()
        );

    // TaunTaun

    // Loth cat

    // Shaak (the big animal from naboo)

    ////////////// sounds ///////////////
    //porg
    public static final Identifier PORG_AMBIENT_ID = new Identifier("starwarsmaterials:porg_ambient");
    public static SoundEvent PORG_AMBIENT = new SoundEvent(PORG_AMBIENT_ID);

    public static final Identifier PORG_HURT_ID = new Identifier("starwarsmaterials:porg_hurt");
    public static SoundEvent PORG_HURT = new SoundEvent(PORG_HURT_ID);

    ///// Lightsaber
    // ON
    public static final Identifier LIGHTSABER_ON_ID = new Identifier("starwarsmaterials:lightsaber_on");
    public static SoundEvent LIGHTSABER_ON = new SoundEvent(LIGHTSABER_ON_ID);
    // OFF
    public static final Identifier LIGHTSABER_OFF_ID = new Identifier("starwarsmaterials:lightsaber_off");
    public static SoundEvent LIGHTSABER_OFF = new SoundEvent(LIGHTSABER_OFF_ID);
    // HUMMING
    public static final Identifier LIGHTSABER_HUMMING_ID = new Identifier("starwarsmaterials:lightsaber_humming");
    public static SoundEvent LIGHTSABER_HUMMING = new SoundEvent(LIGHTSABER_HUMMING_ID);
    
    // MOVE
    public static final Identifier LIGHTSABER_MOVE_ID = new Identifier("starwarsmaterials:lightsaber_move");
    public static SoundEvent LIGHTSABER_MOVE = new SoundEvent(LIGHTSABER_MOVE_ID);
    // HIT
    public static final Identifier LIGHTSABER_HIT_ID = new Identifier("starwarsmaterials:lightsaber_hit");
    public static SoundEvent LIGHTSABER_HIT = new SoundEvent(LIGHTSABER_HIT_ID);

    //////////// world generation ///////////////
    
    /////// identifier /////
    public static Identifier identifier(String name) {
		return new Identifier(MOD_ID, name);
	}


	private static StatusEffectInstance StatusEffectInstance(StatusEffect nausea, int i) {
        return null;
    }

    ////////////////////////////////////////////// registries
    ////////////////////////////////////////////// //////////////////////////////////////////////
    @Override
    public void onInitialize()
    {
        ////////////////// items and blocks //////////////////
        //items
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "beskar_ingot"), BESKAR_INGOT);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "plastoid"), PLASTOID);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "kyber_crystal"), KYBER_CRYSTAL);

        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "porg_spawn_egg"),
        new SpawnEggItem(StarWarsMaterials.PORG, 0xc2c2c2, 0x7e5e60, new Item.Settings().group(ANIMALS_GROUP)));

        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "raw_porg"), RAW_PORG);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "cooked_porg"), COOKED_PORG);

        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "death_sticks"), DEATH_STICKS);


        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "lightsaber_hilt"), LIGHTSABER_HILT);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "lightsaber"), LIGHTSABER_ITEM);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "lightsaber_blue"), LIGHTSABER_BLUE);

        //blocks 
        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "beskar_ore"), BESKAR_ORE);
        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "ice_crystals"), ICE_CRYSTALS);

        //block items
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "beskar_ore"), new BlockItem(BESKAR_ORE, new Item.Settings()));
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "ice_crystal"), new BlockItem(ICE_CRYSTALS, new Item.Settings()));


        //////////////////// armor ////////////////////
        // mandalorian armor
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "mandalorian_helmet"), new BaseArmor(MANDALORIAN_ARMOR, EquipmentSlot.HEAD));
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "mandalorian_chestplate"), new BaseArmor(MANDALORIAN_ARMOR, EquipmentSlot.CHEST));
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "mandalorian_leggings"), new BaseArmor(MANDALORIAN_ARMOR, EquipmentSlot.LEGS));
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "mandalorian_boots"), new BaseArmor(MANDALORIAN_ARMOR, EquipmentSlot.FEET));

        ///////////////// Tools //////////////////////
        // lightsabers

        
        /////////////////// Biomes //////////////////
        // Tatooine

        // Ahch_To

        ////////////////// Dimentions ////////////////////
        // Tatooine

        // Ahch_To
            
        ///////////////// Entities /////////////////////
        // Porg
        FabricDefaultAttributeRegistry.register(PORG, PorgEntity.createPorgAttributes());

        // Tauntaun
        
        //////////////// Sounds /////////////////////
        ////// Entities //////
        // Porg
        Registry.register(Registry.SOUND_EVENT, StarWarsMaterials.PORG_AMBIENT_ID, PORG_AMBIENT);
        Registry.register(Registry.SOUND_EVENT, StarWarsMaterials.PORG_HURT_ID, PORG_HURT);
    }
    
}