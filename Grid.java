public class Grid{
    Node[][] grid;
    Node agent, goal;
    
    public final int SIZE = 5;
    public final double OBSTACLE_PERCENTAGE = 0.20;

    //Creates an 50 x 50 empty grid of nodes with starting node at (start_row, start_col)
    //and goal node at (goal_row, goal_col) with indices starting at 0
    public Grid(int start_row, int start_col, int goal_row, int goal_col, boolean empty) {
        grid = new Node[SIZE][SIZE];

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++){
                if (i == start_row && j == start_col) {
                    agent = new Node(i, j, Math.abs(goal_row - i) + Math.abs(goal_col - j), false);
                    agent.onPath();
                    agent.setParent(agent);
                    grid[i][j] = agent;
                } else if (i  == goal_row && j == goal_col) {
                    goal = new Node(i, j, Math.abs(goal_row - i) + Math.abs(goal_col - j), false);
                    goal.setGVal(Integer.MAX_VALUE);
                    grid[i][j] = goal;
                } else {
                    if (empty) {
                        grid[i][j] = new Node(i, j, Math.abs(goal_row - i) + Math.abs(goal_col - j), false);
                    } else{
                        grid[i][j] = new Node(i, j, Math.abs(goal_row - i) + Math.abs(goal_col - j), setObstacle());
                    }
                    
                }
            }
        }
    }

    //Randomly determines if an obstacle should be present or not
    public boolean setObstacle() {
        return Math.random() < OBSTACLE_PERCENTAGE;
    }

    public void clearPaths() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (grid[i][j] != agent) {
                    grid[i][j].setPath(false);
                    grid[i][j].setParent(null);
                    grid[i][j].setGVal(0);
                }

            }
        }
        agent.setParent(agent);
    }

    public void printGrid() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (grid[i][j].isBlocked()) {
                    System.out.print("[X]");
                } else if (grid[i][j] == agent) {
                    System.out.print("[A]");
                } else if (grid[i][j] == goal) {
                    System.out.print("[G]");
                } else if (grid[i][j].onPath()) {
                    System.out.print("[*]");
                } else {
                    System.out.print("[ ]");
                }
            }
            System.out.println();
        }
    }
}