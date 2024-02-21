import java.util.ArrayList;
import java.util.Stack;

//Implements Repeated Forward A* with ties being broken in favor of smaller g-values
public class AStar {
    MinHeap openList;
    ArrayList<Node> closedList;
    Stack<Node> foundPath;
    Node currentNode;
    int numExpansions, tie;
    Grid gridWorld, agentWorld;


    public AStar(Grid grid, int tie) {
        gridWorld = grid;
        agentWorld = new Grid(gridWorld.agent.getRow(), gridWorld.agent.getCol(), gridWorld.goal.getRow(), gridWorld.goal.getCol(), true);
        checkAdjacency();
        openList = new MinHeap(25, tie);
        closedList = new ArrayList<Node>();
        foundPath = new Stack<>();
        currentNode = agentWorld.agent;
        numExpansions = 0;
        this.tie = tie;
    }

    public boolean search() {
        while (!currentNode.equals(agentWorld.goal)) {
            closedList.add(currentNode);
            findNextStep(currentNode);
            if (openList.getHeapSize() == 0) {
                return false;
            }
            currentNode = openList.extractMin();
            numExpansions++;
        }

        while (!currentNode.equals(agentWorld.agent)) { //backtrack from goal to agent
            foundPath.push(currentNode);
            currentNode.setPath(true);
            currentNode = currentNode.getParent();    
        }


        while (!foundPath.isEmpty()) {
            Node nextNode = foundPath.pop();
            if (agentWorld.grid[nextNode.getRow()][nextNode.getCol()].isBlocked()) {
                agentWorld.clearPaths();
                nextNode.setBlocked(true);
                closedList.clear();
                foundPath.clear();
                openList.clearHeap();
                currentNode = agentWorld.agent;
                System.out.println("\nGenerating New Path!");
                search();
            } else {
                agentWorld.agent = nextNode;
                checkAdjacency();
                System.out.println();
                agentWorld.printGrid();
            }
        }
        return true;

    }

    public void findNextStep(Node node){
        if (checkDown(node)) {
            Node neighbor = agentWorld.grid[node.getRow()+1][node.getCol()];
            if(!closedList.contains(neighbor) && openList.findIndex(neighbor) == -1) {
                neighbor.setGVal(node.getGVal() + 1);
                openListAdd(neighbor);
            }
        }
        if (checkUp(node)) {
            Node neighbor = agentWorld.grid[node.getRow()-1][node.getCol()];
            if(!closedList.contains(neighbor) && openList.findIndex(neighbor) == -1) {
                neighbor.setGVal(node.getGVal() + 1);
                openListAdd(neighbor);
            }
        }

        if (checkRight(node)) {
            Node neighbor = agentWorld.grid[node.getRow()][node.getCol()+1];
            if(!closedList.contains(neighbor) && openList.findIndex(neighbor) == -1) {
                neighbor.setGVal(node.getGVal() + 1);
                openListAdd(neighbor);
            }
        }

        if (checkLeft(node)) {
            Node neighbor = agentWorld.grid[node.getRow()][node.getCol()-1];
            if(!closedList.contains(neighbor) && openList.findIndex(neighbor) == -1) {
                neighbor.setGVal(node.getGVal() + 1);
                openListAdd(neighbor);
            }
        }
    }

    //Adds obstacles around current agent position
    public void checkAdjacency() {
        if (agentWorld.agent.getRow()+1 < agentWorld.SIZE) {
            boolean gridBlocked = gridWorld.grid[agentWorld.agent.getRow()+1][agentWorld.agent.getCol()].isBlocked();
            agentWorld.grid[agentWorld.agent.getRow()+1][agentWorld.agent.getCol()].setBlocked(gridBlocked);
        } 
        if (agentWorld.agent.getRow()-1 >= 0) {
            boolean gridBlocked = gridWorld.grid[agentWorld.agent.getRow()-1][agentWorld.agent.getCol()].isBlocked();
            agentWorld.grid[agentWorld.agent.getRow()-1][agentWorld.agent.getCol()].setBlocked(gridBlocked);
        }
        if (agentWorld.agent.getCol()+1 < agentWorld.SIZE) {
            boolean gridBlocked = gridWorld.grid[agentWorld.agent.getRow()][agentWorld.agent.getCol()+1].isBlocked();
            agentWorld.grid[agentWorld.agent.getRow()][agentWorld.agent.getCol()+1].setBlocked(gridBlocked);
        }
        if (agentWorld.agent.getCol()-1 >= 0) {
            boolean gridBlocked = gridWorld.grid[agentWorld.agent.getRow()][agentWorld.agent.getCol()-1].isBlocked();
            agentWorld.grid[agentWorld.agent.getRow()][agentWorld.agent.getCol()-1].setBlocked(gridBlocked);
        }
    }

    //Returns true if node is available and unblocked, otherwise false
    public boolean checkDown(Node node) {
        return node.getRow()+1 < agentWorld.SIZE && !agentWorld.grid[node.getRow()+1][node.getCol()].isBlocked();
    }

    //Returns true if node is available and unblocked, otherwise false
    public boolean checkUp(Node node) {
        return node.getRow()-1 >= 0 && !agentWorld.grid[node.getRow()-1][node.getCol()].isBlocked();
    }

        //Returns true if node is available and unblocked, otherwise false
    public boolean checkRight(Node node) {
        return node.getCol()+1 < agentWorld.SIZE && !agentWorld.grid[node.getRow()][node.getCol()+1].isBlocked();
    }
        //Returns true if node is available and unblocked, otherwise false
    public boolean checkLeft(Node node) {
        return node.getCol()-1 >= 0 && !agentWorld.grid[node.getRow()][node.getCol()-1].isBlocked();
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

    public void printAgentGrid() {
        agentWorld.printGrid();
    }
}
