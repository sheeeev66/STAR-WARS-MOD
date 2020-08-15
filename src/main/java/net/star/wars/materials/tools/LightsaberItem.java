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
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        // nbt
        ItemStack stack = playerEntity.getMainHandStack();
        CompoundTag tag = stack.getOrCreateTag();
        lightsaberOn = tag.getBoolean("on");
        lightsaberOn =  !lightsaberOn;
        tag.putBoolean("on", lightsaberOn); 

        

        if (playerEntity.getStackInHand(hand).getItem() == StarWarsMaterials.LIGHTSABER_ITEM) {
            playerEntity.inventory.removeStack(playerEntity.inventory.getSlotWithStack(new ItemStack(StarWarsMaterials.LIGHTSABER_ITEM)));
            playerEntity.inventory.insertStack(playerEntity.inventory.getSlotWithStack(playerEntity.getStackInHand(hand)), new ItemStack(StarWarsMaterials.LIGHTSABER_HILT));
                if(world.isClient) {
                    playerEntity.playSound(StarWarsMaterials.LIGHTSABER_OFF, 5.0f, 1.0f);
                }
            }
        
        return new TypedActionResult<ItemStack>(ActionResult.SUCCESS, playerEntity.getStackInHand(hand));
    }
}
