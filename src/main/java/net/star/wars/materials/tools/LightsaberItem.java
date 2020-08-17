package net.star.wars.materials.tools;

import java.util.List;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.star.wars.materials.StarWarsMaterials;

public class LightsaberItem extends Item {

    public LightsaberItem(Settings settings) {
        super(settings);
    }

    boolean lightsaberOn;
    
    @Environment(EnvType.CLIENT)
    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);
    }

    @Environment(EnvType.CLIENT)
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        // nbt
        ItemStack stack = player.getStackInHand(hand);
        CompoundTag tag = stack.getOrCreateTag();
        lightsaberOn = tag.getBoolean("on");

        // play sound
        player.playSound(
            lightsaberOn ? StarWarsMaterials.LIGHTSABER_ON : StarWarsMaterials.LIGHTSABER_OFF,
            1.0f, 1.0f
        );

        lightsaberOn =  !lightsaberOn;
        tag.putBoolean("on", lightsaberOn); 


        
/*
        if (player.getStackInHand(hand).getItem() == StarWarsMaterials.LIGHTSABER_ITEM) { // this causes the game to crash...
            player.inventory.removeStack(player.inventory.getSlotWithStack(new ItemStack(StarWarsMaterials.LIGHTSABER_ITEM)));
            player.inventory.insertStack(player.inventory.getSlotWithStack(player.getStackInHand(hand)), new ItemStack(StarWarsMaterials.LIGHTSABER_ITEM));
        }
*/
        return new TypedActionResult<ItemStack>(ActionResult.SUCCESS, player.getStackInHand(hand));
    }
}
