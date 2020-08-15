package net.star.wars.materials.block;

import java.util.Random;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.block.TransparentBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

import net.minecraft.world.LightType;
import net.minecraft.world.World;

public class IceCrystals extends TransparentBlock {

	public IceCrystals() {
        super(FabricBlockSettings.of(Material.ICE).breakInstantly());
    }

    public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, BlockEntity blockEntity,
            ItemStack stack) {
        super.afterBreak(world, player, pos, state, blockEntity, stack);
        if (EnchantmentHelper.getLevel(Enchantments.SILK_TOUCH, stack) == 0) {
           if (world.getDimension().isUltrawarm()) {
              world.removeBlock(pos, false);
              return;
           }
        }
  
    }
  
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (world.getLightLevel(LightType.BLOCK, pos) > 11 - state.getOpacity(world, pos)) {
           this.melt(state, world, pos);
        }
  
    }
  
    protected void melt(BlockState state, World world, BlockPos pos) {
        if (world.getDimension().isUltrawarm()) {
           world.removeBlock(pos, false);
        } else {
           world.setBlockState(pos, Blocks.CAVE_AIR.getDefaultState());
           world.updateNeighbor(pos, Blocks.CAVE_AIR, pos);
        }
    }
  
    public PistonBehavior getPistonBehavior(BlockState state) {
        return PistonBehavior.DESTROY;
    }

}