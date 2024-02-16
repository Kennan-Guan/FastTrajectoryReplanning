import java.util.ArrayList;
import java.util.Stack;

//Implements Repeated Forward A* with ties being broken in favor of smaller g-values
public class AStarSmall {
    ArrayList<Node> openList;
    ArrayList<Node> closedList;
    Stack<Node> foundPath;
    boolean isFound;
    Node currentNode;
    int numExpansions;


    public AStarSmall(Grid grid) {
        openList = new ArrayList<Node>();
        closedList = new ArrayList<Node>();

        currentNode = grid.start;
        isFound = false;

        numExpansions = 0;
        while (currentNode != grid.goal) {
            numExpansions++;

            openList.add(currentNode);
            computePath();

            if (openList.size() == 0) {
                break;
            }


        }

    }

    public void computePath() {
        while (grid.goal.getGVal > minFValueNode.getFVal) {
            openList.remove(minFValueNode);
            closedList.add(minFValueNode);

            findNextStep();

            //SORT OPEN LIST HERE
            currentNode = openList.get(0);

            foundPath.push(currentNode)

        }

    }

    public void findNextStep(){
        if (checkDown(minFValueNode)) {
            Node neighbor = grid[node.row+1][node.col];
            if(!closedList.contains(neighbor)) {
                neighbor.setGVal(neighbor.gVal + 1);
                openListAdd(neighbor);
            }
        }
        if (checkUp(minFValueNode)) {
            Node neighbor = grid[node.row-1][node.col];
            if(!closedList.contains(neighbor)) {
                neighbor.setGVal(neighbor.gVal + 1);
                openListAdd(neighbor);
            }
        }

        if (checkRight(minFValueNode)) {
            Node neighbor = grid[node.row][node.col+1];
            if(!closedList.contains(neighbor)) {
                neighbor.setGVal(neighbor.gVal + 1);
                openListAdd(neighbor);
            }
        }

        if (checkDown(minFValueNode)) {
            Node neighbor = grid[node.row][node.col-1];
            if(!closedList.contains(neighbor)) {
                neighbor.setGVal(neighbor.gVal + 1);
                openListAdd(neighbor);
            }
        }
    }

    //Returns true if node is available and unblocked, otherwise false
    public boolean checkDown(Node node) {
        return (node.row+1 < grid.SIZE && node.col < grid.SIZE) && !grid[node.row+1][node.col].isBlocked;
    }

        //Returns true if node is available and unblocked, otherwise false
    public boolean checkUp(Node node) {
        return (node.row-1 < grid.SIZE && node.col < grid.SIZE) && !grid[node.row-1][node.col].isBlocked;
    }

        //Returns true if node is available and unblocked, otherwise false
    public boolean checkRight(Node node) {
        return (node.row < grid.SIZE && node.col+1 < grid.SIZE) && !grid[node.row][node.col+1].isBlocked;
    }

        //Returns true if node is available and unblocked, otherwise false
    public boolean checkLeft(Node node) {
        return (node.row < grid.SIZE && node.col-1 < grid.SIZE) && !grid[node.row][node.col-1].isBlocked;
    }

    public void openListAdd(Node node) {
        if (openList.contains(node) && ) {
            //if the node that is in the open list has a greater f-value than the current version of that node, swap
        } else {
            //otherwise add current node to the list 
            node.setParent(currentNode);
            openList.add(node);
        }
    }

    public Node minFValueNode() {
        for ()
    }
}
