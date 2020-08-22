package net.star.wars.materials.tools.lightsaber;

import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public class LightsaberToolMaterial implements ToolMaterial {
    private int durability;
    private float miningSpeedMultiplier;
    private float attackDamage;
    private int MiningLevel;
    private Ingredient repair;
    private int enchantitbility;



    public LightsaberToolMaterial(int durability, float miningSpeedMultiply, float attackDamage, int MiningLevel, int Enchantibility,  Ingredient repair){
        this.durability = durability;
        this.miningSpeedMultiplier = miningSpeedMultiply;
        this.attackDamage = attackDamage;
        this.MiningLevel = MiningLevel;
        this.repair = repair;
        this.enchantitbility = Enchantibility;
    }

    @Override
    public int getDurability() {
        return durability;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return miningSpeedMultiplier;
    }

    @Override
    public float getAttackDamage() {
        return attackDamage;
    }

    @Override
    public int getMiningLevel() {
        return MiningLevel;
    }

    @Override
    public int getEnchantability() {
        return enchantitbility;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return repair;
    }
}
