package net.star.wars.materials.util;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

public class LightSaberUtil {
    private static boolean isBreakingWithLightsaber = false;
    private static boolean isHoldingOnLeave = false;

    public static boolean isBreakingWithLightsaber(){
        return isBreakingWithLightsaber;
    }
    public static void setBreakingWithLightSaber(boolean bo){
        isBreakingWithLightsaber = bo;
    }
    public static void setIsHoldingOnLeave(boolean bl){
        isHoldingOnLeave = bl;
    }
    public static boolean isHoldingOnLeave(){
        return isHoldingOnLeave;
    }
    public static void replaceStack(ItemStack oldStack, ItemStack newStack, PlayerEntity entity){

    }



}
