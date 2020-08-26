package net.star.wars.materials.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.*;

import java.util.Random;

public class IceCrystals extends Block {

	public IceCrystals() {
        super(FabricBlockSettings.of(Material.ICE).breakInstantly().nonOpaque());
        this.setDefaultState(((this.stateManager.getDefaultState()).with(HANGING, false)).with(field_26441, false));
        this.setDefaultState(((this.stateManager.getDefaultState()).with(PICKLES, 1)));
    }

    public static final BooleanProperty HANGING;
    public static final IntProperty PICKLES;
    public static final BooleanProperty field_26441;

    protected static final VoxelShape ONE_STANDING_SHAPE;
    protected static final VoxelShape TWO_STANDING_SHAPE;
    protected static final VoxelShape THREE_STANDING_SHAPE;
    protected static final VoxelShape FOUR_STANDING_SHAPE;

    protected static final VoxelShape ONE_HANGING_SHAPE;
    protected static final VoxelShape TWO_HANGING_SHAPE;
    protected static final VoxelShape THREE_HANGING_SHAPE;
    protected static final VoxelShape FOUR_HANGING_SHAPE;
 


    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockState blockState1 = ctx.getWorld().getBlockState(ctx.getBlockPos());
        FluidState fluidState = ctx.getWorld().getFluidState(ctx.getBlockPos());
        Direction[] var3 = ctx.getPlacementDirections();
        int var4 = var3.length;

        for(int var5 = 0; var5 < var4; ++var5) {

            Direction direction = var3[var5];
            if (direction.getAxis() == Direction.Axis.Y) {
                if (blockState1.isOf(this)) {
                    return blockState1.with(PICKLES, Math.min(4, (Integer)blockState1.get(PICKLES) + 1));
                }

                BlockState blockState = this.getDefaultState().with(HANGING, direction == Direction.UP);
                if (blockState.canPlaceAt(ctx.getWorld(), ctx.getBlockPos())) {
                    return blockState.with(field_26441, fluidState.getFluid() == Fluids.WATER);
                } else {
                    return super.getPlacementState(ctx);
                }
            }
        }
        return null;
    }


    @Override
    public boolean canReplace(BlockState state, ItemPlacementContext context) {
        return context.getStack().getItem() == this.asItem() && state.get(PICKLES) < 4 || super.canReplace(state, context);
     }
  
     @Override
     public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        switch(state.get(PICKLES)) {
        case 1:
        default:
        return state.get(HANGING) ? ONE_HANGING_SHAPE : ONE_STANDING_SHAPE;
        case 2:
        return state.get(HANGING) ? TWO_HANGING_SHAPE : TWO_STANDING_SHAPE;
        case 3:
        return state.get(HANGING) ? THREE_HANGING_SHAPE : THREE_STANDING_SHAPE;
        case 4:
        return state.get(HANGING) ? FOUR_HANGING_SHAPE : FOUR_STANDING_SHAPE;
        }
     }
  
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(HANGING, PICKLES, field_26441);
    }
  
    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        Direction direction = attachedDirection(state).getOpposite();
        return Block.sideCoversSmallSquare(world, pos.offset(direction), direction.getOpposite());
    }
  
    protected static Direction attachedDirection(BlockState state) {
        return (Boolean)state.get(HANGING) ? Direction.DOWN : Direction.UP;
    }
  
     @Override
     public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState newState, WorldAccess world, BlockPos pos, BlockPos posFrom) {
        if ((Boolean)state.get(field_26441)) {
           world.getFluidTickScheduler().schedule(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }

        if (!state.canPlaceAt(world, pos)) {
            return Blocks.AIR.getDefaultState();
        }
  
        return attachedDirection(state).getOpposite() == direction && !state.canPlaceAt(world, pos) ? Blocks.AIR.getDefaultState() : super.getStateForNeighborUpdate(state, direction, newState, world, pos, posFrom);
    }
  
    public FluidState getFluidState(BlockState state) {
        return (Boolean)state.get(field_26441) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }
  
    public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
        return false;
    }
 

    public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, BlockEntity blockEntity,
            ItemStack stack) {
        super.afterBreak(world, player, pos, state, blockEntity, stack);
        if (EnchantmentHelper.getLevel(Enchantments.SILK_TOUCH, stack) == 0) {
           if (world.getDimension().isUltrawarm()) {
              world.removeBlock(pos, false);
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

    static {
            HANGING = Properties.HANGING;
            PICKLES = Properties.PICKLES;
            field_26441 = Properties.WATERLOGGED;

            ONE_STANDING_SHAPE = VoxelShapes.union(
                Block.createCuboidShape(8.0D, 0.0D, 8.0D, 9.0D, 9.0D, 9.0D),
                Block.createCuboidShape(7.0D, 0.0D, 7.0D, 8.0D, 3.0D, 8.0D)
            );
            
            TWO_STANDING_SHAPE = VoxelShapes.union(
                Block.createCuboidShape(8.0D, 0.0D, 8.0D, 9.0D, 9.0D, 9.0D),
                Block.createCuboidShape(7.0D, 0.0D, 7.0D, 8.0D, 3.0D, 8.0D),
                Block.createCuboidShape(4.0D ,0.0D ,10.0D , 5.0D ,6.0D ,11.0D)
                );

            THREE_STANDING_SHAPE = VoxelShapes.union(
                Block.createCuboidShape(8.0D, 0.0D, 8.0D, 9.0D, 9.0D, 9.0D),
                Block.createCuboidShape(7.0D, 0.0D, 7.0D, 8.0D, 3.0D, 8.0D),
                Block.createCuboidShape(4.0D ,0.0D ,10.0D , 5.0D ,6.0D ,11.0D),
                Block.createCuboidShape(10.0D ,0.0D ,5.0D , 11.0D ,6.0D ,6.0D)
                );

            FOUR_STANDING_SHAPE = VoxelShapes.union(
                Block.createCuboidShape(8.0D, 0.0D, 8.0D, 9.0D, 9.0D, 9.0D),
                Block.createCuboidShape(7.0D, 0.0D, 7.0D, 8.0D, 3.0D, 8.0D),
                Block.createCuboidShape(4.0D ,0.0D ,10.0D , 5.0D ,6.0D ,11.0D),
                Block.createCuboidShape(10.0D ,0.0D ,5.0D , 11.0D ,6.0D ,6.0D),
                Block.createCuboidShape(3.0D ,0.0D ,2.0D , 4.0D ,3.0D ,3.0D)
                );

            
            ONE_HANGING_SHAPE = VoxelShapes.union(
                Block.createCuboidShape(8.0D, 7.0D, 8.0D, 9.0D, 16.0D, 9.0D),
                Block.createCuboidShape(7.0D, 13.0D, 7.0D, 8.0D, 16.0D, 8.0D)
                );
            
            TWO_HANGING_SHAPE = VoxelShapes.union(
                Block.createCuboidShape(8.0D, 7.0D, 8.0D, 9.0D, 16.0D, 9.0D),
                Block.createCuboidShape(7.0D, 13.0D, 7.0D, 8.0D, 16.0D, 8.0D),
                Block.createCuboidShape(4.0D ,10.0D ,10.0D , 5.0D ,16.0D ,11.0D)
                );
            
            THREE_HANGING_SHAPE = VoxelShapes.union(
                Block.createCuboidShape(8.0D, 7.0D, 8.0D, 9.0D, 16.0D, 9.0D),
                Block.createCuboidShape(7.0D, 13.0D, 7.0D, 8.0D, 16.0D, 8.0D),
                Block.createCuboidShape(4.0D ,10.0D ,10.0D , 5.0D ,16.0D ,11.0D),
                Block.createCuboidShape(10.0D ,10.0D ,5.0D , 11.0D ,16.0D ,6.0D)
                );
            
            FOUR_HANGING_SHAPE = VoxelShapes.union(
                Block.createCuboidShape(8.0D, 7.0D, 8.0D, 9.0D, 16.0D, 9.0D),
                Block.createCuboidShape(7.0D, 13.0D, 7.0D, 8.0D, 16.0D, 8.0D),
                Block.createCuboidShape(4.0D ,10.0D ,10.0D , 5.0D ,16.0D ,11.0D),
                Block.createCuboidShape(10.0D ,10.0D ,5.0D , 11.0D ,16.0D ,6.0D),
                Block.createCuboidShape(3.0D ,13.0D ,2.0D , 4.0D ,16.0D ,3.0D)
                );
    }

}