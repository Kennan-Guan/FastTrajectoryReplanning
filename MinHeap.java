 /* Min binary heap to store values of the open list in order 
  * @author Kennan Guan, David Nguyen
 */

 public class MinHeap { 
    Node[] heapArray; 
    int capacity; 
    int size;
    boolean tie;
    
    public MinHeap(int n, boolean tie) { 
        capacity = n; 
        this.tie = tie;
        heapArray = new Node[capacity]; 
        size = 0; 
    } 
       
    public void swap(int a, int b) { 
        Node temp = heapArray[a]; 
        heapArray[a] = heapArray[b]; 
        heapArray[b] = temp; 
    } 
      
    public int parent(int key) { 
        return (key - 1) / 2; 
    } 
      
    public int left(int key) { 
        return 2 * key + 1; 
    } 
      
    public int right(int key) { 
        return 2 * key + 2; 
    } 
      
    public int getHeapSize() {
        return size;
    }
      
    public boolean insertKey(Node key) { 
        if (size == capacity) { 
            return false; 
        } 
       
        int i = size; 
        heapArray[i] = key; 
        size++;
        
           
        if (tie) {
            while (i != 0 && heapArray[i].getFVal() < heapArray[parent(i)].getFVal() || (heapArray[i].getFVal() == heapArray[parent(i)].getFVal() && (heapArray[i].getGVal() < heapArray[parent(i)].getGVal()))) { 
                swap(i, parent(i)); 
                i = parent(i); 
            } 
        } else {
            while (i != 0 && heapArray[i].getFVal() < heapArray[parent(i)].getFVal() || (heapArray[i].getFVal() == heapArray[parent(i)].getFVal() && (heapArray[i].getGVal() > heapArray[parent(i)].getGVal()))) { 
                swap(i, parent(i)); 
                i = parent(i); 
            } 
        }
        return true; 
    } 
      
    public void decreaseKey(int key, Node new_val) { 
        heapArray[key] = new_val; 

        if (tie) {
            while (key != 0 && heapArray[key].getFVal() < heapArray[parent(key)].getFVal() || (heapArray[key].getFVal() == heapArray[parent(key)].getFVal() && (heapArray[key].getGVal() < heapArray[parent(key)].getGVal()))) { 
                swap(key, parent(key)); 
                key = parent(key); 
            } 
        } else {
            while (key != 0 && heapArray[key].getFVal() < heapArray[parent(key)].getFVal() || (heapArray[key].getFVal() == heapArray[parent(key)].getFVal() && (heapArray[key].getGVal() > heapArray[parent(key)].getGVal()))) { 
                swap(key, parent(key)); 
                key = parent(key); 
            } 
        }
    } 
      
    public Node extractMin() { 
        if (size <= 0) { 
            return null; 
        } 
  
        if (size == 1) { 
            size--; 
            return heapArray[0]; 
        } 
          
        Node root = heapArray[0]; 
  
        heapArray[0] = heapArray[size-1]; 
        size--; 
        MinHeapify(0); 
  
        return root; 
    }
    
    public void deleteKey(int key) { 
        heapArray[key].setGVal(Integer.MIN_VALUE);
        decreaseKey(key, heapArray[key]); 
        extractMin(); 
    } 

    //Orders the heap on the current key to a minimum heap
    private void MinHeapify(int key) { 
        int left = left(key); 
        int right = right(key); 
  
        int smallest = key; 
        if (left < size && heapArray[left].getFVal() < heapArray[smallest].getFVal()) { 
            smallest = left; 
        } 
        if (right < size && heapArray[right].getFVal() < heapArray[smallest].getFVal()) { 
            smallest = right;
        } 
        if (left < size && heapArray[left].getFVal() == heapArray[smallest].getFVal()) {
            if(tie) {
                if (heapArray[left].getGVal() < heapArray[smallest].getGVal())
                    smallest = left;
            } else {
                if (heapArray[left].getGVal() > heapArray[smallest].getGVal())
                    smallest = left;
            }
        }
        if (right < size && heapArray[right].getFVal() == heapArray[smallest].getFVal()) {
            if(tie) {
                if (heapArray[right].getGVal() < heapArray[smallest].getGVal())
                    smallest = right;
            } else {
                if (heapArray[right].getGVal() > heapArray[smallest].getGVal())
                    smallest = right;
            }
    }
        
  
        if (smallest != key) { 
            swap(key, smallest); 
            MinHeapify(smallest); 
        } 
    } 

    public boolean changeValueOnAKey(int key, Node new_val) { 
        if (heapArray[key].getFVal() < new_val.getFVal()) { 
            return false;
        } 
        
        if ((heapArray[key].getFVal() == new_val.getFVal())) {
            if(tie) {
                if (heapArray[key].getGVal() < new_val.getGVal())
                    return false;
            } else {
                if (heapArray[key].getGVal() > new_val.getGVal())
                    return false;
            }
        }
        decreaseKey(key, new_val); 
        return true; 
    } 

    public int findIndex(Node node) {
        for (int i = 0; i < heapArray.length; i++) {
            if (heapArray[i] != null && heapArray[i].equals(node)) {
                return i;
            }
        }
        return -1;
    }

    public void clearHeap() {
        heapArray = new Node[capacity];
        size = 0;
    }
} 
  