public class AStarTies {
    public static void main(String[] args) {
        Grid gridworld = new Grid(0, 0, 19, 19, false);
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
        

        AStar favor_largeg = new AStar(gridworld, 1);
        if (favor_largeg.search()) {
           System.out.println("Path Found!");
        } else {
           System.out.println("Path not found!");
        }
        System.out.println("Number of expansions: " +   favor_largeg.numExpansions);
        //favor_largeg.numExpansions = 0;
        //favor_largeg.search();
        //System.out.println("Number of expansions: " +   favor_largeg.numExpansions);
        
        

        AdaptiveAStar favor_large_g = new AdaptiveAStar(gridworld);
        favor_large_g.numExpansions = 0;
        if (favor_large_g.search()) {
            System.out.println("Path Found2!");
        } else{
            System.out.println("Path not found2!");
        }
        System.out.println("Number of expansions: " + favor_large_g.numExpansions);
        favor_large_g.numExpansions = 0;

        //favor_large_g.search();
        //System.out.println("Number of expansions: " + favor_large_g.numExpansions);
        //favor_large_g.numExpansions = 0;
        //favor_large_g.search();
        //System.out.println("Number of expansions: " + favor_large_g.numExpansions);

    }
}