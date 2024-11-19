package cono6783.thebuddysystem.entity.pathfinding;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;

public class Node {
    public Node parent;
    public int f, g, h;
    private BlockPos pos;


    public Node(BlockPos pos) {
        this.pos = pos;
    }

    public BlockPos getPos() {
        return pos;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public void calculateHeuristic(int hOffset) {
        h = parent == null ? 0 : parent.h + hOffset;
    }

    public void calculateFCost() {
        f = g + h;
    }

    public void calculateGCost(BlockPos target) {
        g = (int) pos.distSqr(target);
    }

    public String toString() {
        return "F, G, H: " + f + ", " + g + ", " + h + ", BlockPos: " + pos.toString() ;
    }
}
