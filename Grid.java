public class Grid{
    Node[][] grid;
    int size;

    //Creates an n x n empty grid of nodes
    public Grid(int n) {
        size = n;
        grid = new Node[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++){
                grid[i][j] = new Node();
            }
        }
    }

    public void setObstacles() {

    }

    public void printGrid() {

    }



}