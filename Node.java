public class Node{
    public Node parent;
    public int row, col, fVal, gVal, hVal;
    public boolean  visited, isBlocked, path;


    public Node(int row, int col, int hVal, boolean isBlocked){
        this.parent = null;
        this.row = row; 
        this.col = col;
        this.hVal = hVal;
        this.fVal = 0;
        this.gVal = 0;
        this.isBlocked = isBlocked;
        this.visited = false;
        this.path = false;
    }

    // Set and get parent nodes

    public void setParent(Node parent){
        this.parent = parent;
    }

    public Node getParent(){
        return this.parent;
    }

    // Get f, g, h values 

    public int getFVal(){
        return this.gVal + this.hVal;
    }

    public int getGVal(){
        return this.gVal;
    }

    public int getHVal(){
        return this.hVal;
    }

    // Set new g value

    public void setGVal(int newGVal){
        this.gVal = newGVal;
        this.fVal = this.gVal + this.hVal;
    }

    // Check visited

    public void visit(){
        this.visited = true;
    }

    public boolean isVisited(){
        return this.visited;
    }

    public int compareTo(Node other){
        if (this.fVal == other.fVal) {
            return this.gVal - other.gVal;
        }
        return this.fVal - other.fVal;
    }

}