import java.util.ArrayList;
import java.util.Stack;

//Implements Repeated Forward A* with ties being broken in favor of smaller g-values
public class AStarSmall {
    MinHeap openList;
    ArrayList<Node> closedList;
    Stack<Node> foundPath;
    boolean isFound;
    Node currentNode;
    int numExpansions;
    Grid grid;


    public AStarSmall(Grid grid) {
        this.grid = grid;
        openList = new MinHeap(grid.SIZE);
        closedList = new ArrayList<Node>();

        currentNode = grid.start;
        isFound = false;

        numExpansions = 0;
        while (currentNode != grid.goal) {
            numExpansions++;

            openList.insertKey(currentNode);
            computePath();

            if (openList.getHeapSize() == 0) {
                break;
            }


        }

    }

    public void computePath() {
        while (grid.goal.getGVal() > openList.getMin().getFVal()) {
            closedList.add(openList.getMin());
            findNextStep(openList.getMin());

            openList.deleteKey(0);
            currentNode = openList.getMin();

            foundPath.push(currentNode);

        }

    }

    public void findNextStep(Node node){
        if (checkDown(node)) {
            Node neighbor = grid.grid[node.row+1][node.col];
            if(!closedList.contains(neighbor)) {
                neighbor.setGVal(node.gVal + 1);
                openListAdd(neighbor);
            }
        }
        if (checkUp(node)) {
            Node neighbor = grid.grid[node.row-1][node.col];
            if(!closedList.contains(neighbor)) {
                neighbor.setGVal(currentNode.gVal + 1);
                openListAdd(neighbor);
            }
        }

        if (checkRight(node)) {
            Node neighbor = grid.grid[node.row][node.col+1];
            if(!closedList.contains(neighbor)) {
                neighbor.setGVal(currentNode.gVal + 1);
                openListAdd(neighbor);
            }
        }

        if (checkDown(node)) {
            Node neighbor = grid.grid[node.row][node.col-1];
            if(!closedList.contains(neighbor)) {
                neighbor.setGVal(currentNode.gVal + 1);
                openListAdd(neighbor);
            }
        }
    }

    //Returns true if node is available and unblocked, otherwise false
    public boolean checkDown(Node node) {
        return (node.row+1 < grid.SIZE && node.col < grid.SIZE) && !grid.grid[node.row+1][node.col].isBlocked;
    }

        //Returns true if node is available and unblocked, otherwise false
    public boolean checkUp(Node node) {
        return (node.row-1 < grid.SIZE && node.col < grid.SIZE) && !grid.grid[node.row-1][node.col].isBlocked;
    }

        //Returns true if node is available and unblocked, otherwise false
    public boolean checkRight(Node node) {
        return (node.row < grid.SIZE && node.col+1 < grid.SIZE) && !grid.grid[node.row][node.col+1].isBlocked;
    }

        //Returns true if node is available and unblocked, otherwise false
    public boolean checkLeft(Node node) {
        return (node.row < grid.SIZE && node.col-1 < grid.SIZE) && !grid.grid[node.row][node.col-1].isBlocked;
    }

    public void openListAdd(Node node) {
        int existing_node = openList.findIndex(node);
        if (existing_node > 0) {
            openList.changeValueOnAKey(existing_node, node);
        } else {
            node.setParent(currentNode);
            openList.insertKey(node);
        }
    }
}
