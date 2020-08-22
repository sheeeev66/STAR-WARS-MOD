package net.star.wars.materials.util;

public class LightSaberUtil {
    private static boolean isHolding = false;
    public static boolean isHoldingLightsaber(){
        return isHolding;
    }
    public static void setHolding(boolean bo){
        isHolding = bo;
    }
}
