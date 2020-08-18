package net.star.wars.materials.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.star.wars.materials.registry.ItemRegistry;

public class KyberCrystal extends Item {

    public KyberCrystal(Settings settings) {
        super(settings);
    }

    // get random number between 1 - 5
    int random = (int)(Math.random() * 5 + 1);

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {

        
        int slot =  player.inventory.getSlotWithStack(player.getStackInHand(hand));
        player.inventory.setStack(slot, new ItemStack(ItemRegistry.BLUE_KYBER_CRYSTAL));

        return new TypedActionResult<ItemStack>(ActionResult.SUCCESS, player.getStackInHand(hand));
    }
    
}