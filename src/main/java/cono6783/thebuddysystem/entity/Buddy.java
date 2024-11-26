package cono6783.thebuddysystem.entity;

import com.mojang.logging.LogUtils;
import cono6783.thebuddysystem.entity.goals.HarvestGoal;
import cono6783.thebuddysystem.entity.goals.MoveTo;
import cono6783.thebuddysystem.entity.pathfinding.BuddyNavigation;
import cono6783.thebuddysystem.util.BuddyBlockFinder;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.VisibleForDebug;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.behavior.BehaviorUtils;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.npc.InventoryCarrier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.slf4j.Logger;

public class Buddy extends PathfinderMob implements InventoryCarrier {

    //Constants
    public static final Logger LOGGER = LogUtils.getLogger();
    private BuddyNavigation buddyNavigation;



    //Values and Multipliers for movement
    private double JUMP_ENERGY = 0.3;
    private double RUNNING_ENERGY_MULTIPLIER = 1.5;
    private double BUDDY_MAX_ENERGY = 200;

    //Variables
    private boolean wantsToMove;
    private final SimpleContainer inventory = new SimpleContainer(8);
    private BlockPos posGoal; // Used to be a default pos here; BlockPos(-46, 86, -44);
    public Block blockTarget;

    private double buddyEnergy;


    public Buddy(EntityType<? extends PathfinderMob> entityType, Level level) {
        super(entityType, level);

        this.buddyNavigation = new BuddyNavigation(level);
        this.setPersistenceRequired();
        this.setCustomName(Component.literal("Buddy"));
        this.setCanPickUpLoot(true);
        buddyEnergy = BUDDY_MAX_ENERGY / 2;
    }



    public void tick() {
        super.tick();
        if (getHealth() < 5.0) {
            setWantsToMove(true);
        }
        if (posGoal == null) {
            try {
                posGoal = BuddyBlockFinder.findBlockAroundBuddy(this, Blocks.GOLD_BLOCK, this.level(), 10).offset(0, 1, 0);
            } catch (Exception e) {
                posGoal = null;
            }

        }




        //LOGGER.info("Buddy's energy is: {}", buddyEnergy);
        /*
        BlockPos blockToRemove = new BlockPos((int) Math.floor(position().x), (int) Math.floor(position().y), (int) Math.floor(position().z)).below();
        this.level().destroyBlock(blockToRemove, false); */


    }

    public InteractionResult mobInteract(Player player, InteractionHand interactionHand) {
        ItemStack itemInHand = player.getItemInHand(interactionHand);
        LOGGER.info(getInventory().toString());
        if (player.isCrouching() && itemInHand.isEmpty()) {
            for (ItemStack itemToThrow : getInventory().removeAllItems()) {
                BehaviorUtils.throwItem(this, itemToThrow, player.position());
            }
        }
        if (attemptEquipItem(itemInHand)) {
            player.setItemInHand(interactionHand, ItemStack.EMPTY);

        }
//        LOGGER.info("Buddy's current position is {}", this.position());
//        LOGGER.info("He is trying to move to {}", this.position().add(0, 1, 0));
//        this.getMoveControl().setWantedPosition(this.position().x + 1, this.position().y, this.position().z, 1.0);


        return InteractionResult.sidedSuccess(true);
    }

    public void die(DamageSource damageSource) {
        LOGGER.info("You kilt him");
        for (ItemStack droppedItem : getInventory().removeAllItems()) {
            this.spawnAtLocation(droppedItem);
        }
        for (ItemStack droppedItem : getAllSlots()) {
            this.spawnAtLocation(droppedItem);
        }
    }







    public void buddyChattedWith(String message) {

        LOGGER.info("You chatted and the buddy heard it");
        LOGGER.info("The buddy heard: {}", message);







        if (message.equals("move")) {
            LOGGER.info("Should be moving");
            setWantsToMove(true);
            //LOGGER.info("The value of wantsToMove is now {}", (Boolean) this.entityData.get(WANTS_TO_MOVE));
            LOGGER.info("The health of buddy is {}", getHealth());



        }

        if (message.equals("jump")) {
            LOGGER.info("Should be jumping");
            jumpFromGround();
        }

        if (message.equals("stop")) {
            setWantsToMove(false);
        }


    }



    //Buddy Vision
    /*
    private boolean buddyCanSee(Block block, int range) {
        AABB viewRange = new AABB(position().subtract(range, range, range), position().add(range, range, range));
    } */



    //Equipping Items

    private boolean attemptEquipItem(ItemStack itemToEquip) {
        EquipmentSlot itemGoesIn = getEquipmentSlotForItem(itemToEquip);
        if (itemToEquip.isEmpty()) {
            return false;
        }

        this.setItemSlot(itemGoesIn, itemToEquip);
        if (itemToEquip.is(ItemTags.AXES)) {
            blockTarget = Blocks.OAK_LOG;
        }



        return true;
    }







    //Inventory
    @VisibleForDebug
    public SimpleContainer getInventory() {
        return this.inventory;
    }

    protected ItemStack addToInventory(ItemStack p_34779_) {
        return this.inventory.addItem(p_34779_);
    }

    protected void pickUpItem(ItemEntity item) {
        addToInventory(item.getItem());
        item.discard();
    }


    //Movement
    public void setWantsToMove(boolean value) {
        wantsToMove = value;
    }

    public boolean getWantsToMove() {
        return wantsToMove;
    }

    public BlockPos getPosGoal() {
        return posGoal;
    }

    public void setPosGoal(BlockPos newPosition) {
        posGoal = newPosition;
        LOGGER.info("Pos goal set to {}", posGoal);
    }

    public BlockState getBlockInFront() {
        return getBlockInFront(1);
    }

    public BlockState getBlockInFront(int range) {
        return this.level().getBlockState(BlockPos.containing(this.position().relative(this.getMotionDirection(), range)));
        //return this.level().getBlockState(BlockPos.containing(this.position().x + range * this.getMotionDirection().getStepX(), this.position().y, this.position().z + range * this.getMotionDirection().getStepZ()));
    }

    public BuddyNavigation getBuddyNavigation() {
        return this.buddyNavigation;
    }


    // Implementation of Movement
    public void jump() {
        if (buddyEnergy >= JUMP_ENERGY) {
            buddyEnergy -= JUMP_ENERGY;
            getJumpControl().jump();
        }


    }

    //Constructing the entity for use


    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 10.0D)
                .add(Attributes.MOVEMENT_SPEED, .25D);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(0, new MoveTo(this, 1));
        this.goalSelector.addGoal(1, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(2, new HarvestGoal(this, 1.0D));
    }






}
