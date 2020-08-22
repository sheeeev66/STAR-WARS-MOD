package net.star.wars.materials.mixin;


import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.star.wars.materials.tools.lightsaber.AbstractLightsaberItem;
import net.star.wars.materials.util.LightSaberUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
@Mixin(Block.class)


public abstract class BlockMixin extends AbstractBlock implements ItemConvertible {




    public BlockMixin(Settings settings) {
        super(settings);
    }


    @ModifyArg(method = "method_9544(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/item/ItemStack;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/Block;dropStack(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/item/ItemStack;)V"), index = 2)
    private static ItemStack dropStack(ItemStack stack){
        if(LightSaberUtil.isHoldingLightsaber()){
            return ItemStack.EMPTY;
        }
        return stack;
    }


    @Inject(method = "dropStacks(Lnet/minecraft/block/BlockState;Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/entity/BlockEntity;Lnet/minecraft/entity/Entity;Lnet/minecraft/item/ItemStack;)V", at = @At("HEAD"))
    private static void inject(BlockState state, World world, BlockPos pos, BlockEntity blockEntity, Entity entity, ItemStack stack, CallbackInfo ci){
        if(entity instanceof PlayerEntity){
          PlayerEntity entity1 = (PlayerEntity)entity;
          if (entity1.getStackInHand(Hand.MAIN_HAND).getItem() instanceof AbstractLightsaberItem){
              LightSaberUtil.setHolding(true);
          }
        }
    }



}
