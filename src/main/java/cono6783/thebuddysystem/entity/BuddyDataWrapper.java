package cono6783.thebuddysystem.entity;

import com.mojang.logging.LogUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.level.Level;
import org.slf4j.Logger;

public class BuddyDataWrapper extends PathfinderMob {

    public static final Logger LOGGER = LogUtils.getLogger();
    public boolean wantsToMove;
    private final BlockPos posGoal = new BlockPos(-14, 72, -42);



    protected BuddyDataWrapper(EntityType<? extends PathfinderMob> entityType, Level level) {
        super(entityType, level);
    }






    public void setWantsToMove(boolean value) {
        this.wantsToMove = value;
    }

    public boolean getWantsToMove() {
        LOGGER.info("getWantsToMove called on instance: {}", System.identityHashCode(this));
        LOGGER.info("In getWantsToMove the value of wantsToMove is {}", wantsToMove);
        return this.wantsToMove;
    }

    public BlockPos getPosGoal() {
        return posGoal;
    }



}
