package net.star.wars.materials.armor;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.star.wars.materials.StarWarsMaterials;

public class BaseArmor extends ArmorItem{

    public BaseArmor(ArmorMaterial material, EquipmentSlot slot) {
        super(material, slot, new Item.Settings().group(StarWarsMaterials.ARMOR_GROUP));
    }
    
}