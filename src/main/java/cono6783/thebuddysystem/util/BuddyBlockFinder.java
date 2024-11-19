package cono6783.thebuddysystem.util;

import cono6783.thebuddysystem.entity.Buddy;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.pathfinder.PathFinder;

import java.util.ArrayList;

public class BuddyBlockFinder {

    public static BlockPos findBlockAroundPos(Buddy buddy, Block blockToFind, Level level, int radius) {
        ArrayList<BlockPos> foundBlocks = new ArrayList<>();

        int searchStartX = buddy.blockPosition().getX() - radius;
        int searchStartY = buddy.blockPosition().getY() - radius;
        int searchStartZ = buddy.blockPosition().getZ() - radius;

        for (int x = 0; x < radius*2 + 1; x++) {
            for (int y = 0; y < radius*2 + 1; y++) {
                for (int z = 0; z < radius*2 + 1; z++) {
                    BlockPos currentBlockPos = new BlockPos(searchStartX + x, searchStartY + y, searchStartZ + z);
                    if (level.getBlockState(currentBlockPos).is(blockToFind)) {
                        foundBlocks.add(currentBlockPos);
                    }

                }
            }
        }

        int shortestPath = 0;
        for (BlockPos blockPos : foundBlocks) {


            shortestPath = Math.min(shortestPath, LocationFindUtil.getDistance(blockPos, buddy.blockPosition()));



        }


        return null;




    }



}
