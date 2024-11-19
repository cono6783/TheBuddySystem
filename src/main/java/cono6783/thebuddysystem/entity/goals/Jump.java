package cono6783.thebuddysystem.entity.goals;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;

public class Jump extends Goal {

    private final LivingEntity mob;

    public Jump(LivingEntity mob) {
        this.mob = mob;
    }


    @Override
    public boolean canUse() {
        return mob.onGround();
    }

    @Override
    public void tick() {
        mob.setJumping(true);
        mob.setDeltaMovement(mob.getDeltaMovement().add(0.0, 4.2, 0.0));
        mob.hasImpulse = true;
    }
}
