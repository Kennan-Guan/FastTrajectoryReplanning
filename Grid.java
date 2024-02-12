public class Grid{
    Node[][] grid;
    int size;
    
    public static final double OBSTACLE_PERCENTAGE = 0.20;

    //Creates an n x n empty grid of nodes
    public Grid(int n) {
        size = n;
        grid = new Node[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++){
                if (setObstacle()) {
                    grid[i][j] = null;
                } else {
                    grid[i][j] = new Node();
                }
            }
        }
    }

    //Randomly determines if an obstacle should be present or not
    public boolean setObstacle() {
        return Math.random() < OBSTACLE_PERCENTAGE;
    }

    public void printGrid() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (grid[i][j] == null) {
                    System.out.print("[X]");
                } else {
                    System.out.print("[ ]");
                }
            }
        }


    }



}