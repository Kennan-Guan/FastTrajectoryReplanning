public class AStarTies {
    public static void main(String[] args) {
        Grid gridworld = new Grid(0, 0, 49, 49);

        AStar favor_small_g = new AStar(gridworld, 0);
        System.out.println(favor_small_g.numExpansions);
        gridworld.printGrid();

        AStar favor_large_g = new AStar(gridworld, 1);
        System.out.println(favor_large_g.numExpansions);
        gridworld.printGrid();
    }
}