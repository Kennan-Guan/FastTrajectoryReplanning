public class Grid{
    Node[][] grid;
    int size;
    Node start, goal;
    
    public static final double OBSTACLE_PERCENTAGE = 0.20;

    //Creates an n x n empty grid of nodes with starting node at (start_row, start_col)
    //and goal node at (goal_row, goal_col) with indices starting at 0
    public Grid(int n, int start_row, int start_col, int goal_row, int goal_col) {
        size = n;
        grid = new Node[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++){
                if (i == start_row && j == start_col) {
                    start = new Node(i, j, i+j, false);
                    grid[i][j] = start;
                } else if (i  == goal_row && j == goal_col) {
                    goal = new Node(i, j, i+j, false);
                    grid[i][j] = goal;
                } else {
                    grid[i][j] = new Node(i, j, i+j, setObstacle());
                }
            }
        }

        printGrid();
    }

    //Randomly determines if an obstacle should be present or not
    public boolean setObstacle() {
        return Math.random() < OBSTACLE_PERCENTAGE;
    }

    public void printGrid() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (grid[i][j].isBlocked) {
                    System.out.print("[X]");
                } else if (grid[i][j] == start) {
                    System.out.print("[A]");
                } else if (grid[i][j] == goal) {
                    System.out.print("[G]");
                } else {
                    System.out.print("[ ]");
                }
            }
            System.out.println();
        }
    }
}