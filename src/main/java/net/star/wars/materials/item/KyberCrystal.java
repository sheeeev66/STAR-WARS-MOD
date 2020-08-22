package net.star.wars.materials.item;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.star.wars.materials.registry.ItemRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class KyberCrystal extends Item {

    public KyberCrystal(Settings settings) {
        super(settings);
    }

    Random random = new Random();

    public String green = "green";
    public String blue = "blue";
    public String purple = "purple";
    public String yellow = "yellow";

    List<String> colors = new ArrayList<>();

    private void newColorPosibility() {
        int i = 40;
        int o = 5;

        while (i >= 1) {
            colors.add(blue);
            colors.add(green);
            

            i = i - 1;
        }

        while  (o >= 1) {
            colors.add(yellow);

            o = o - 1;
        }

        colors.add(purple);

    }

    int getRandomColor;
    String selectedColor;

    private String selectColor() {
        getRandomColor = random.nextInt(colors.size());
        selectedColor = colors.get(getRandomColor);

        return selectedColor;
    }

    @Environment(EnvType.CLIENT)
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        int slot =  player.inventory.getSlotWithStack(player.getStackInHand(hand));

        newColorPosibility();
        selectColor();

        if (selectedColor.equals(green)) {
            player.inventory.removeStack(slot, 1);
            player.inventory.insertStack(slot, new ItemStack(ItemRegistry.GREEN_KYBER_CRYSTAL));
        }

        if (selectedColor.equals(blue)) {
            player.inventory.removeStack(slot, 1);
            player.inventory.insertStack(slot, new ItemStack(ItemRegistry.BLUE_KYBER_CRYSTAL));
        }

        if (selectedColor.equals(purple)) {
            player.inventory.removeStack(slot, 1);
            player.inventory.insertStack(slot, new ItemStack(ItemRegistry.PURPLE_KYBER_CRYSTAL));
        }

        if (selectedColor.equals(yellow)) {
            player.inventory.removeOne(new ItemStack(ItemRegistry.KYBER_CRYSTAL));
            player.inventory.insertStack(slot, new ItemStack(ItemRegistry.PURPLE_KYBER_CRYSTAL));
        }



        colors.clear();

        return new TypedActionResult<ItemStack>(ActionResult.SUCCESS, player.getStackInHand(hand));
    }
    
}