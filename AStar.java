import java.util.ArrayList;
import java.util.Stack;

//Implements Repeated Forward A* with ties being broken in favor of smaller g-values
public class AStar {
    MinHeap openList;
    ArrayList<Node> closedList;
    Stack<Node> foundPath;
    boolean isFound;
    Node currentNode;
    int numExpansions;
    Grid grid;


    public AStar(Grid grid) {
        this.grid = grid;
        openList = new MinHeap(grid.SIZE * grid.SIZE);
        closedList = new ArrayList<Node>();
        foundPath = new Stack<>();

        currentNode = grid.start;
        isFound = false;

        numExpansions = 0;
        while (!currentNode.equals(grid.goal)) {
            closedList.add(currentNode);
            findNextStep(currentNode);
            if (openList.getHeapSize() == 0) {
                break;
            }
            currentNode = openList.extractMin();
            numExpansions++;
        }

        while (!currentNode.getParent().equals(currentNode)) {
            foundPath.push(currentNode);
            currentNode.path = true;
            currentNode = currentNode.getParent();    
        }

    }

    public void findNextStep(Node node){
        if (checkDown(node)) {
            Node neighbor = grid.grid[node.row+1][node.col];
            if(!closedList.contains(neighbor) && openList.findIndex(neighbor) == -1) {
                neighbor.setGVal(node.gVal + 1);
                openListAdd(neighbor);
            }
        }
        if (checkUp(node)) {
            Node neighbor = grid.grid[node.row-1][node.col];
            if(!closedList.contains(neighbor) && openList.findIndex(neighbor) == -1) {
                neighbor.setGVal(node.gVal + 1);
                openListAdd(neighbor);
            }
        }

        if (checkRight(node)) {
            Node neighbor = grid.grid[node.row][node.col+1];
            if(!closedList.contains(neighbor) && openList.findIndex(neighbor) == -1) {
                neighbor.setGVal(node.gVal + 1);
                openListAdd(neighbor);
            }
        }

        if (checkLeft(node)) {
            Node neighbor = grid.grid[node.row][node.col-1];
            if(!closedList.contains(neighbor) && openList.findIndex(neighbor) == -1) {
                neighbor.setGVal(node.gVal + 1);
                openListAdd(neighbor);
            }
        }
    }

    //Returns true if node is available and unblocked, otherwise false
    public boolean checkDown(Node node) {
        return node.row+1 < grid.SIZE && !grid.grid[node.row+1][node.col].isBlocked;
    }

        //Returns true if node is available and unblocked, otherwise false
    public boolean checkUp(Node node) {
        return node.row-1 >= 0 && !grid.grid[node.row-1][node.col].isBlocked;
    }

        //Returns true if node is available and unblocked, otherwise false
    public boolean checkRight(Node node) {
        return node.col+1 < grid.SIZE && !grid.grid[node.row][node.col+1].isBlocked;
    }

        //Returns true if node is available and unblocked, otherwise false
    public boolean checkLeft(Node node) {
        return node.col-1 >= 0 && !grid.grid[node.row][node.col-1].isBlocked;
    }

    public void openListAdd(Node node) {
        int existing_node = openList.findIndex(node);
        if (existing_node > -1) {
            openList.changeValueOnAKey(existing_node, node);
        } else {
            node.setParent(currentNode);
            openList.insertKey(node);
        }
    }
}
