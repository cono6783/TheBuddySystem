package cono6783.thebuddysystem.entity.pathfinding;

import net.minecraft.core.BlockPos;

public class Node {
    public Node parent;
    public int f, g, h;
    private BlockPos pos;
    private boolean jumpNode;


    public Node(BlockPos pos, boolean jumpNode) {
        this.pos = pos;
        this.jumpNode = jumpNode;
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

    public void setJumpNode(boolean value) {
        this.jumpNode = value;
    }

    public boolean isJumpNode() {
        return this.jumpNode;
    }

    public String toString() {
        return "F, G, H: " + f + ", " + g + ", " + h + ", BlockPos: " + pos.toString() ;
    }

    public boolean isSamePos(BlockPos other) {
        return this.pos.equals(other);
    }
}
