package net.star.wars.materials.tools.lightsaber;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.star.wars.materials.registry.ItemRegistry;
import net.star.wars.materials.registry.SoundRegistry;

import java.util.List;

public class AbstractLightsaberItem extends SwordItem {

    public AbstractLightsaberItem(Settings settings, ToolMaterial material) {
        super(material, 14, 9, settings);
    }

    boolean lightsaberOn;
    
    @Environment(EnvType.CLIENT)
    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);
    }
    //testing


    @Override
    public float getMiningSpeedMultiplier(ItemStack stack, BlockState state) {

        return 14f;
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        attacker.playSound(SoundRegistry.LIGHTSABER_HIT_ONE, 1f, 1f);
        return true;
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
                SoundRegistry.LIGHTSABER_OFF,
                1.0f, 1.0f
        );

        lightsaberOn = !lightsaberOn;
        tag.putBoolean("on", lightsaberOn);
        if (!world.isClient) {
           int slot =  player.inventory.getSlotWithStack(player.getStackInHand(hand));
           player.inventory.setStack(slot, new ItemStack(ItemRegistry.LIGHTSABER_BLUE));
        }
            return new TypedActionResult<ItemStack>(ActionResult.SUCCESS, player.getStackInHand(hand));
        }
    }

