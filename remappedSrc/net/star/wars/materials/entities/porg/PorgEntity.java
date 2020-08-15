package net.star.wars.materials.entities.porg;

import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Flutterer;
import net.minecraft.entity.ai.control.FlightMoveControl;
import net.minecraft.entity.ai.goal.AnimalMateGoal;
import net.minecraft.entity.ai.goal.EscapeDangerGoal;
import net.minecraft.entity.ai.goal.FlyOntoTreeGoal;
import net.minecraft.entity.ai.goal.FollowParentGoal;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.ai.goal.WanderAroundFarGoal;
import net.minecraft.entity.ai.pathing.BirdNavigation;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.star.wars.materials.StarWarsMaterials;

public class PorgEntity extends AnimalEntity implements Flutterer {

    private static final Ingredient BREEDING_INGREDIENT;
    public float flapProgress;
    public float maxWingDeviation;
    public float prevMaxWingDeviation;
    public float prevFlapProgress;
    private float flapSpeed = 1.0F;
    
    public PorgEntity(EntityType<? extends PorgEntity> entityType, World world) {
        super(entityType, world);
        this.moveControl = new FlightMoveControl(this, 10, false);
        this.setPathfindingPenalty(PathNodeType.WATER, 0.0F);
    }

// adding goals:
protected void initGoals() {
    this.goalSelector.add(0, new SwimGoal(this));
    this.goalSelector.add(1, new EscapeDangerGoal(this, 1.4D));
    this.goalSelector.add(2, new AnimalMateGoal(this, 1.0D));
    this.goalSelector.add(2, new FlyOntoTreeGoal(this, 1.0D));
    this.goalSelector.add(3, new TemptGoal(this, 1.0D, false, BREEDING_INGREDIENT));
    this.goalSelector.add(4, new FollowParentGoal(this, 1.1D));
    this.goalSelector.add(5, new WanderAroundFarGoal(this, 1.0D));
    this.goalSelector.add(6, new LookAtEntityGoal(this, PlayerEntity.class, 6.0F));
    this.goalSelector.add(7, new LookAroundGoal(this));
}


    public static DefaultAttributeContainer.Builder createPorgAttributes() {
        return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 6.0D).add(EntityAttributes.GENERIC_FLYING_SPEED, 0.4D).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.2D);
    }

    protected EntityNavigation createNavigation(World world) {
        BirdNavigation birdNavigation = new BirdNavigation(this, world);
        birdNavigation.setCanPathThroughDoors(false);
        birdNavigation.setCanSwim(true);
        birdNavigation.setCanEnterOpenDoors(true);
        return birdNavigation;
    }

    protected float getActiveEyeHeight(EntityPose pose, EntityDimensions dimensions) {
        return dimensions.height * 0.6F;
    }

    
    public void tickMovement() {
        super.tickMovement();
        this.flapWings();
    }

    private void flapWings() {
        this.prevFlapProgress = this.flapProgress;
        this.prevMaxWingDeviation = this.maxWingDeviation;
        this.maxWingDeviation = (float)((double)this.maxWingDeviation + (double)(!this.onGround && !this.hasVehicle() ? 4 : -1) * 0.3D);
        this.maxWingDeviation = MathHelper.clamp(this.maxWingDeviation, 0.0F, 1.0F);
        if (!this.onGround && this.flapSpeed < 1.0F) {
           this.flapSpeed = 1.0F;
        }
  
        this.flapSpeed = (float)((double)this.flapSpeed * 0.9D);
        Vec3d vec3d = this.getVelocity();
        if (!this.onGround && vec3d.y < 0.0D) {
           this.setVelocity(vec3d.multiply(1.0D, 0.6D, 1.0D));
        }
  
        this.flapProgress += this.flapSpeed * 2.0F;
    }

    public boolean isInAir() {
        return !this.onGround;
    }

    public boolean handleFallDamage(float fallDistance, float damageMultiplier) {
        return false;
    }


    protected SoundEvent getAmbientSound() {
        return StarWarsMaterials.PORG_AMBIENT;
    }
  
    protected SoundEvent getHurtSound(DamageSource source) {
        return StarWarsMaterials.PORG_HURT;
    }
  
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_CHICKEN_DEATH;
    }

    protected void playStepSound(BlockPos pos, BlockState state) {
        this.playSound(SoundEvents.ENTITY_PARROT_STEP, 0.15F, 1.0F);
    }
  
    protected float playFlySound(float distance) {
        this.playSound(SoundEvents.ENTITY_PARROT_FLY, 0.15F, 1.0F);
        return distance + this.maxWingDeviation / 2.0F;
    }
  
    protected boolean hasWings() {
        return true;
    }
  
    public boolean isBreedingItem(ItemStack stack) {
        return BREEDING_INGREDIENT.test(stack);
    }


    
    @Override
    public PorgEntity createChild(PassiveEntity mate) {
        return (PorgEntity)StarWarsMaterials.PORG.create(this.world);
    }

    static {
        BREEDING_INGREDIENT = Ingredient.ofItems(Items.TROPICAL_FISH, Items.SALMON, Items.COOKED_SALMON, Items.COD, Items.COOKED_COD);
    }
}