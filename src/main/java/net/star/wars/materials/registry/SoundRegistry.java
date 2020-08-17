package net.star.wars.materials.registry;

import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
public class SoundRegistry {
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
    public static void initialize(){
        Registry.register(Registry.SOUND_EVENT, SoundRegistry.PORG_AMBIENT_ID, PORG_AMBIENT);
        Registry.register(Registry.SOUND_EVENT, SoundRegistry.PORG_HURT_ID, PORG_HURT);
    }
}
