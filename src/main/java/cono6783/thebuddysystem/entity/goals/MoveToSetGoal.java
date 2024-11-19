package cono6783.thebuddysystem.entity.goals;

import cono6783.thebuddysystem.entity.Buddy;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ai.goal.Goal;

public class MoveToSetGoal extends Goal {

    private final Buddy buddy;
    private final BlockPos targetPos;
    private final double speed;

    public MoveToSetGoal(Buddy buddy, BlockPos targetPos, double speed) {
        this.buddy = buddy;
        this.targetPos = targetPos;
        this.speed = speed;
    }


    public boolean canUse() {


        return this.buddy.getWantsToMove();
    }

    @Override
    public void start() {

    }

    @Override
    public void tick() {
        buddy.getNavigation().moveTo(targetPos.getX(), targetPos.getY(), targetPos.getZ(), speed);
    }





}
