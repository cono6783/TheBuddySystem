package cono6783.thebuddysystem.entity.goals;

import com.mojang.logging.LogUtils;
import cono6783.thebuddysystem.entity.Buddy;
import cono6783.thebuddysystem.entity.pathfinding.Node;
import cono6783.thebuddysystem.entity.pathfinding.Path;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.block.Block;

import java.util.LinkedList;

public class MoveTo extends Goal {

    private final Buddy buddy;
    private final double speed;
    private Path path;
    private LinkedList<Node> route;
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
    Node currentMoveGoal;
    @Override
    public void tick() {
        /* This will be implemented in the BuddyNavigation
        if (!(buddy.getBlockInFront().is(Blocks.AIR) || buddy.getBlockInFront().is(Blocks.SNOW))) {
            LogUtils.getLogger().info("Block in front of buddy");
            buddy.jump();
        } */

        if (!lastKnownPosGoal.equals(buddy.getPosGoal())) {
            LogUtils.getLogger().info("posGoal changed. Recomputing path...");
            computePathAndNodeList();
        }

        if (route == null) {
            return;
        }


        if (currentMoveGoal == null || currentMoveGoal.isSamePos(buddy.blockPosition())) {
            currentMoveGoal = route.removeFirst();
        }
        if (!buddy.blockPosition().equals(buddy.getPosGoal())) {
            LogUtils.getLogger().info("the route is currently {}", route);
            buddy.getMoveControl().setWantedPosition(currentMoveGoal.getPos().getX() + 0.5, currentMoveGoal.getPos().getY(), currentMoveGoal.getPos().getZ() + 0.5, speed);
            if (currentMoveGoal.isJumpNode()) {
                buddy.jump();
            }
            lastKnownPosGoal = buddy.getPosGoal();
        } else {
            buddy.setPosGoal(null);
            route.clear();
        }


    }

    @Override
    public boolean canContinueToUse() {
        return route == null || !route.isEmpty();
    }

    private void computePathAndNodeList() {
        this.path = buddy.getBuddyNavigation().computePath(buddy.blockPosition(), buddy.getPosGoal());
        this.route = path == null ? null : path.getNodeListToFollow();


    }











}
