public class AStarTies {
    public static void main(String[] args) {
        Grid gridworld = new Grid(0, 0, 4, 4, false);
        System.out.println("Original Grid:");
        gridworld.printGrid();
        System.out.println();

        // AStar favor_small_g = new AStar(gridworld, 0);
        // if (favor_small_g.search()) {
        //     System.out.println("Path Found!");
        // } else {
        //     System.out.println("Path not found!");
        // }
        // System.out.println("Number of expansions: " + favor_small_g.numExpansions);
        

        AStar favor_large_g = new AStar(gridworld, 1);
        if (favor_large_g.search()) {
            System.out.println("Path Found!");
        } else {
            System.out.println("Path not found!");
        }
        System.out.println(favor_large_g.numExpansions);
    }
}