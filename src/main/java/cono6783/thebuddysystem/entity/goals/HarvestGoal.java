package cono6783.thebuddysystem.entity.goals;

import com.mojang.logging.LogUtils;
import cono6783.thebuddysystem.entity.Buddy;
import cono6783.thebuddysystem.util.BuddyBlockFinder;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ai.goal.Goal;

public class HarvestGoal extends Goal {

    private final Buddy buddy;
    private final double speed;




    public HarvestGoal(Buddy buddy, double speed) {
        this.buddy = buddy;
        this.speed = speed;

    }

    @Override
    public boolean canUse() {
        return buddy.blockTarget != null;
    }


    public void tick() {
        BlockPos blockPosFound = BuddyBlockFinder.findBlockAroundPos(buddy, buddy.blockTarget, buddy.level(), 10);
        if (blockPosFound != null) {
            LogUtils.getLogger().info(blockPosFound.toString());
            buddy.setPosGoal(blockPosFound);
        } else {
            LogUtils.getLogger().info("No block found");
        }

    }


}
