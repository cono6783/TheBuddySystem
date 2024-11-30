package cono6783.thebuddysystem.entity.pathfinding;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.LinkedList;

public class Path {
    private ArrayList<Node> openList = new ArrayList<>();
    private ArrayList<Node> closedList = new ArrayList<>();
    private final BlockPos target;
    private Level level;
    private boolean finished;
    private Node finalNode;




    public Path(BlockPos start, BlockPos target, Level level) {
        openList.add(new Node(start, false));
        this.target = target;
        this.level = level;
    }


    public BlockPos getTarget() {
        return target;
    }

    public ArrayList<Node> getOpenList() {
        return openList;
    }

    public ArrayList<Node> getClosedList() {
        return closedList;
    }

    public Node getLowestFCost() {
        Node lowest = openList.get(0);
        for (Node node : openList) {
            lowest = lowest.f > node.f ? node : lowest;
        }
        return lowest;
    }



    public void updateCurrentNode(Node node) {
        openList.remove(node);
        closedList.add(node);
        finished = node.getPos().equals(target);
    }

    public boolean isOpenEmpty() {
        return openList.isEmpty();
    }

    public void addToOpenList(Node node) {
        openList.add(node);
    }

    public boolean isComplete() {
        return finished;
    }

    public boolean hasClosedNodeByPos(BlockPos blockPos) {
        for (Node node : closedList) {
            if (node.getPos().equals(blockPos)) {
                return true;
            }
        }
        return false;
    }

    public boolean hasOpenNodeByPos(BlockPos blockPos) {
        for (Node node : openList) {
            if (node.getPos().equals(blockPos)) {
                return true;
            }
        }
        return false;
    }

    public Node getNodeByPos(BlockPos blockPos) {
        for (Node node : openList) {
            if (node.getPos().equals(blockPos)) {
                return node;
            }
        }
        return null;
    }

    public LinkedList<Node> getNodeListToFollow() {
        LinkedList<Node> posList = new LinkedList<>();
        Node currentNode = closedList.get(closedList.size() - 1);
        while (currentNode.parent != null) {
            posList.addFirst(currentNode);
            currentNode = currentNode.parent;
        }
        return posList;
    }
}
