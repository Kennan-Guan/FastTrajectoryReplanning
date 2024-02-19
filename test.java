public class test {
    public static void main(String[] args) {
        Grid gridworld = new Grid(0, 0, 49, 49);

        AStar run1 = new AStar(gridworld);

        gridworld.printGrid();
    }
}