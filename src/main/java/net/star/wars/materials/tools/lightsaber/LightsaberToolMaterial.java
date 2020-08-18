package net.star.wars.materials.tools.lightsaber;

import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public class LightsaberToolMaterial implements ToolMaterial {
    private int durability;
    private float miningspeed;
    private float attackDamage;
    private int miningLevel;
    private int enchantibility;
    private Ingredient repairer;
    // create repairer using Ingredient.ofItems(ItemRegistry.your_item))
    public LightsaberToolMaterial(int Durability, float MiningSpeedMultiplier, float attackDamage, int MiningLevel, int Enchantibility, Ingredient repair){
            this.durability = Durability;
            this.miningspeed = MiningSpeedMultiplier;
            this.attackDamage = attackDamage;
            this.miningLevel = MiningLevel;
            this.enchantibility = Enchantibility;
            this.repairer = repair;
    }
    @Override
    public int getDurability() {
        return durability;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return miningspeed;
    }

    @Override
    public float getAttackDamage() {
        return attackDamage;
    }

    @Override
    public int getMiningLevel() {
        return miningLevel;
    }

    @Override
    public int getEnchantability() {
        return enchantibility;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return repairer;
    }
}
