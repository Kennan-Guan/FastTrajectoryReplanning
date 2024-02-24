/* Shows the difference between adaptive and basic A* 
 * @author Kennan Guan, David Nguyen
*/
public class Adaptive {
    public static void main(String[] args) {
         Grid gridworld = new Grid(0, 0, 19, 19, false);
         System.out.println("Original Grid:");
         gridworld.printGrid();
         System.out.println();

         //Runs basic A*
         AStar basic = new AStar(gridworld, false, false, false);
         if (basic.search()) {
            System.out.println("Path Found!");
         } else {
            System.out.println("Path not found!");
         }
         System.out.println("Repeated Forward A* Number of expansions: " +   basic.numExpansions);

         //Runs adaptive A*
         AStar adaptive = new AStar(gridworld, false, true, false);
         if (adaptive.search()) {
               System.out.println("Path Found!");
            } else {
               System.out.println("Path not found!");
            }
            System.out.println("Repeated Forward Adaptive A* Number of expansions: " +   adaptive.numExpansions);
    }
}
