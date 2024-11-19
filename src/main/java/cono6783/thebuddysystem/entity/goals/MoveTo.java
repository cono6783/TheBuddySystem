package cono6783.thebuddysystem.entity.goals;

import com.mojang.logging.LogUtils;
import cono6783.thebuddysystem.entity.Buddy;
import cono6783.thebuddysystem.entity.pathfinding.Path;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.LinkedList;

public class MoveTo extends Goal {

    private final Buddy buddy;
    private final double speed;
    private Path path;
    private LinkedList<BlockPos> route;
    private BlockPos lastKnownPosGoal;

    public MoveTo(Buddy buddy, double speed) {
        this.buddy = buddy;

        this.speed = speed * 1.5;

    }

    @Override
    public boolean canUse() {
        LogUtils.getLogger().info("Block Pos is {} ; Block Goal is {} ; Equal: {}", buddy.blockPosition(), buddy.getPosGoal(), buddy.blockPosition().equals(buddy.getPosGoal()));


        return buddy.getPosGoal() != null && !buddy.blockPosition().equals(buddy.getPosGoal());
    }

    @Override
    public void start() {
        LogUtils.getLogger().info("Statred");
        lastKnownPosGoal = buddy.getPosGoal();
        computePathAndNodeList();

    }
    BlockPos currentMoveGoal;
    @Override
    public void tick() {
        /* This will be implemented in the BuddyNavigation
        if (!(buddy.getBlockInFront().is(Blocks.AIR) || buddy.getBlockInFront().is(Blocks.SNOW))) {
            LogUtils.getLogger().info("Block in front of buddy");
            buddy.jump();
        } */

        if (!lastKnownPosGoal.equals(buddy.getPosGoal())) {
            LogUtils.getLogger().info("Recomputed from the tick");
            computePathAndNodeList();
        }




        if (currentMoveGoal == null || currentMoveGoal.equals(buddy.blockPosition())) {
            currentMoveGoal = route.removeFirst();
        }

        LogUtils.getLogger().info("the route is currently {}", route);
        buddy.getMoveControl().setWantedPosition(currentMoveGoal.getX() + 0.5, currentMoveGoal.getY(), currentMoveGoal.getZ() + 0.5, speed);
        lastKnownPosGoal = buddy.getPosGoal();
    }

    @Override
    public boolean canContinueToUse() {
        return !route.isEmpty();
    }

    private void computePathAndNodeList() {
        this.path = buddy.getBuddyNavigation().computePath(buddy.blockPosition(), buddy.getPosGoal());
        this.route = path.getNodeListToFollow();
    }











}
