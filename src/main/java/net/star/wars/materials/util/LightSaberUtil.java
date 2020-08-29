package net.star.wars.materials.util;

public class LightSaberUtil {
    private static boolean isBreakingWithLightsaber = false;
    private static int getLightsaberIndex = 0;

    public static boolean isBreakingWithLightsaber(){
        return isBreakingWithLightsaber;
    }
    public static void setBreakingWithLightSaber(boolean bo){
        isBreakingWithLightsaber = bo;
    }
    public static int getLightsaberIndex(){
        return getLightsaberIndex;

    }
    public static void setLightsaberIndex(int index){
        getLightsaberIndex = index;
    }

}
