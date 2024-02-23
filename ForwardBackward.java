//Shows the effects of repeated forward and backward A*
public class ForwardBackward {
    public static void main(String[] args) {
        Grid gridworld = new Grid(0, 0, 19, 19, false);
        System.out.println("Original Grid:");
        gridworld.printGrid();
        System.out.println();


        AStar forward = new AStar(gridworld, false, false, false);
        if (forward.search()) {
           System.out.println("Path Found!");
        } else {
           System.out.println("Path not found!");
        }
        System.out.println("Repeated Forward A* Number of expansions: " +   forward.numExpansions);

        AStar backward = new AStar(gridworld, false, false, true);
        if (backward.search()) {
            System.out.println("Path Found!");
         } else {
            System.out.println("Path not found!");
         }
         System.out.println("Repeated Backward A* Number of expansions: " +   backward.numExpansions);
    }
}
