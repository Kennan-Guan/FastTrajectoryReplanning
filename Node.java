public class Node{
    private Node parent;
    private int row, col, fVal, gVal, hVal;
    private boolean blocked, path;


    public Node(int row, int col, int hVal, boolean blocked){
        this.parent = null;
        this.row = row; 
        this.col = col;
        this.hVal = hVal;
        this.fVal = 0;
        this.gVal = 0;
        this.blocked = blocked;
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
        fVal = this.gVal + this.hVal;
        return fVal;
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

    public void setHVal(int newHVal){
        this.hVal = newHVal;
        this.fVal = this.gVal + this.hVal;
    }

    public boolean isBlocked() {
        return blocked;
    }
    public void setBlocked(boolean blocked){
        this.blocked = blocked;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void setPath(boolean inPath) {
        path = inPath;
    }

    public boolean onPath() {
        return path;
    }

    public boolean equals(Node other) {
        return (this.col == other.col) && (this.row == other.row); 
    }

}