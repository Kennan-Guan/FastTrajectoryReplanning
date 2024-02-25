/* Shows the effects of repeated forward and backward A* 
 * @author Kennan Guan, David Nguyen
*/
public class ForwardBackward {
   public static void main(String[] args) {
      int counter = 0;
      int forwardExpansions = 0;
      int backwardExpansions = 0;
      while (counter < 50) {
         Grid gridworld = new Grid(0, 0, 100, 100, false);
         // System.out.println("Original Grid:");
         // gridworld.printGrid();
         // System.out.println();

         // Runs Repeated Forward A*
         AStar forward = new AStar(gridworld, false, false, false);
         if (forward.search()) {
            System.out.println("Path Found!");
            System.out.println("Repeated Forward A* Number of expansions: " + forward.numExpansions);
            forwardExpansions += forward.numExpansions;

            // Runs Repeated Backward A* if the grid has an actual path discovered by forward A*
            AStar backward = new AStar(gridworld, false, false, true);
            if (backward.search()) {
               System.out.println("Path Found!");
               System.out.println("Repeated Backward A* Number of expansions: " + backward.numExpansions);
               backwardExpansions += backward.numExpansions;
               counter++;
            } else {
               System.out.println("This should not be happening, path not found by Backward A*!");
            }
         } else {
            System.out.println("Path not found! Moving to next grid...");
         }
      }

      System.out.println("\nAverage number of expansions after 50 successful searches: ");
      System.out.println("Repeated Forward A*: "+ forwardExpansions / 50);
      System.out.println("Repeated Backward A*: "+ backwardExpansions / 50);
   }
}
