/* Shows the difference between adaptive and basic A* 
 * @author Kennan Guan, David Nguyen
*/
public class Adaptive {
   public static void main(String[] args) {
      int counter = 0;
      int basicExpansions = 0;
      int adaptiveExpansions = 0;
      while (counter < 50) {
         Grid gridworld = new Grid(0, 0, 100, 100, false);
         // System.out.println("Original Grid:");
         // gridworld.printGrid();
         // System.out.println();

         // Runs Repeated Forward A*
         AStar basic = new AStar(gridworld, false, false, false);
         if (basic.search()) {
            System.out.println("Path Found!");
            System.out.println("Repeated Forward A* Number of expansions: " + basic.numExpansions);
            basicExpansions += basic.numExpansions;

            // Runs Adaptive A*
            AStar adaptive = new AStar(gridworld, false, true, false);
            if (adaptive.search()) {
               System.out.println("Path Found!");
               System.out.println("Adaptive A* Number of expansions: " + adaptive.numExpansions);
               adaptiveExpansions += adaptive.numExpansions;
               counter++;
            } else {
               System.out.println("This should not be happening, path not found for adaptive A*!");
            }

         } else {
            System.out.println("Path not found! Moving to next grid...");
         }
      }

      System.out.println("\nAverage number of expansions after 50 successful searches: ");
      System.out.println("Repeated Repeated Forward A*: " + basicExpansions / 50);
      System.out.println("Repeated Adaptive A*: " + adaptiveExpansions / 50);
   }
}
