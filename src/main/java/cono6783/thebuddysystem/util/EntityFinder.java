package cono6783.thebuddysystem.util;

import cono6783.thebuddysystem.entity.Buddy;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.*;
import java.util.logging.Level;

public class EntityFinder {
    private static final Buddy[] emptyArray = {};

    public static Buddy[] findBuddysAroundPlayer(@Nullable Player player, ClientLevel level) {
        if (player == null) {
            return emptyArray;
        }

        Vec3 min = player.position().subtract(5, 5, 5);
        Vec3 max = player.position().add(5, 5, 5);

        AABB boundingBox = new AABB(min, max);

        return level.getEntitiesOfClass(Buddy.class, boundingBox).toArray(new Buddy[0]);
    }

}
