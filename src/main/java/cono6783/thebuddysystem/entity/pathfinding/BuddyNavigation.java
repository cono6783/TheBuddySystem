package cono6783.thebuddysystem.entity.pathfinding;

import com.mojang.logging.LogUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

public class BuddyNavigation {
    private Level level;

    public BuddyNavigation(Level level) {
        this.level = level;
    }

    private Path path;
    private Node current;
    public Path computePath(BlockPos start, BlockPos target) {
        LogUtils.getLogger().info("Computing Path");
        path = new Path(start, target, level);

        while (true) {
            current = path.getLowestFCost();
            path.updateCurrentNode(current);


            if (path.isComplete()) {
                LogUtils.getLogger().info("Path Found");
                return path;
            }



//            LogUtils.getLogger().info("Current node is {}", current.toString());
//            LogUtils.getLogger().info(path.getOpenList().toString());

            BlockPos[] diagonalBlocks = getDiagonalBlocks(current.getPos());
            individualBlockLoop(diagonalBlocks, target, 14);

            BlockPos[] adjacentBlocks = getAdjacentBlocks(current.getPos());
            individualBlockLoop(adjacentBlocks, target, 10);



            if (path.isOpenEmpty()) {
                LogUtils.getLogger().info("Failed To Find Path");
                return null;
            }
        }
    }

    /**
     * Returns adjacent Blocks like this
     * <p>X 0 X</p>
     * <p>1 X 2</p>
     * <p>X 3 X</p>
     * @param blockPos
     * @return Array of BlockPos
     */
    private BlockPos[] getAdjacentBlocks(BlockPos blockPos) {
        return new BlockPos[] {blockPos.offset(0, 0, 1), blockPos.offset(-1, 0, 0), blockPos.offset(1, 0, 0), blockPos.offset(0, 0, -1)};
    }

    /**
     * Returns diagonal BlockPos like:
     * <p>0 X 1</p>
     * <p>X X X</p>
     * <p>2 X 3</p>
     *
     * @param blockPos
     * @return Array of BlockPos
     */
    private BlockPos[] getDiagonalBlocks(BlockPos blockPos) {
        return new BlockPos[] {blockPos.offset(-1, 0, 1), blockPos.offset(1, 0, 1), blockPos.offset(-1, 0, -1), blockPos.offset(1, 0 ,-1)};
    }

    private void individualBlockLoop(BlockPos[] listOfNeighbors, BlockPos target, int heuristicOffset) {
        for (BlockPos neighbor : listOfNeighbors) {
            Node neighborNode = new Node(neighbor);
            neighborNode.setParent(current); // This needs to be up here so that i can calculate if the buddy can cut corners
            neighborNode.calculateHeuristic(heuristicOffset);
            //GCost used to be here
            if (path.hasClosedNodeByPos(neighborNode.getPos())) {
                continue;
            }
            //Check if the node is traversable or not
            if (!isBlockOpen(neighbor) || !isBlockOpen(neighbor.above()) || isBlockOpen(neighbor.below())) { // need to make this logic more
                continue;
            }
            if (!path.hasOpenNodeByPos(neighborNode.getPos()) || neighborNode.h < path.getNodeByPos(neighbor).h) {
                neighborNode.calculateGCost(target); // More efficient down here (slightly)
                neighborNode.calculateFCost();

                if (!path.hasOpenNodeByPos(neighborNode.getPos())) {
                    path.addToOpenList(neighborNode);
                }
            }
        }

    }

    private boolean isBlockOpen(BlockPos blockPos) {
        return !level.getBlockState(blockPos).isSolid(); //THE SOLUTION WAS SO SIMPLE, DEPRECIATED BUT SIMPLE
    }

    /**
     * Takes in the node the buddy will go to next and checks if it can get there from the previous node without problems
     * @param
     * @return
     */
    //private boolean isNodeTraversable(Node toNode) ill do this later

}






