/* Shows the effects of different tiebreak versions 
 * @author Kennan Guan, David Nguyen
*/
public class AStarTies {
    public static void main(String[] args) {
        int counter = 0;
        int smallGExpansions = 0;
        int largeGExpansions = 0;
        while (counter < 50) {
            Grid gridworld = new Grid(0, 0, 100, 100, false);
            // System.out.println("Original Grid:");
            // gridworld.printGrid();
            // System.out.println();

            // Runs A* with ties being broken in favor of smaller g-values
            AStar favor_small_g = new AStar(gridworld, true, false, false);
            if (favor_small_g.search()) {
                System.out.println("Path Found!");
                System.out.println("Smaller G-Values Number of expansions: " + favor_small_g.numExpansions);
                smallGExpansions += favor_small_g.numExpansions;
                counter++;

                // Runs A* with ties being broken in favor of larger g-values
                AStar favor_large_g = new AStar(gridworld, false, false, false);
                if (favor_large_g.search()) {
                    System.out.println("Path Found!");
                    System.out.println("Larger G-Values Number of expansions: " + favor_large_g.numExpansions);
                    largeGExpansions += favor_large_g.numExpansions;
                } else {
                    System.out.println("This should not be happening, path not found for favor_large_g!");
                }
            } else {
                System.out.println("Path not found! Moving to next grid...");
            }
        }

        System.out.println("\nAverage number of expansions after 50 successful searches:");
        System.out.println("Repeated Forward A* favoring smaller g-values: " + smallGExpansions / 50);
        System.out.println("Repeated Forward A* favoring larger g-values: " + largeGExpansions / 50);
    }
}