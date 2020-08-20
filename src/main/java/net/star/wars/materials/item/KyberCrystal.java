package net.star.wars.materials.item;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    Random random = new Random();

    public String green = "green";
    public String blue = "blue";
    public String purple = "purple";

    List<String> colors = new ArrayList<>();

    private void newColorPosibility() {
        colors.add(green);
        colors.add(blue);
    }

    int getRandomColor;
    String selectedColor;

    private String selectColor() {
        getRandomColor = random.nextInt(colors.size());
        selectedColor = colors.get(getRandomColor);

        return selectedColor;
    }


    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        int slot =  player.inventory.getSlotWithStack(player.getStackInHand(hand));

        newColorPosibility();
        selectColor();

        if (selectedColor == green) {
            player.inventory.removeOne(new ItemStack(ItemRegistry.KYBER_CRYSTAL));
            player.inventory.insertStack(slot, new ItemStack(ItemRegistry.GREEN_KYBER_CRYSTAL));
        }

        if (selectedColor == blue) {
            player.inventory.removeOne(new ItemStack(ItemRegistry.KYBER_CRYSTAL));
            player.inventory.insertStack(slot, new ItemStack(ItemRegistry.BLUE_KYBER_CRYSTAL));
        }

        if (selectedColor == purple) {
            player.inventory.removeOne(new ItemStack(ItemRegistry.KYBER_CRYSTAL));
            player.inventory.insertStack(slot, new ItemStack(ItemRegistry.PURPLE_KYBER_CRYSTAL));
        }

        

        

        return new TypedActionResult<ItemStack>(ActionResult.SUCCESS, player.getStackInHand(hand));
    }
    
}