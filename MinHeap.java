// A class for Min Heap  
class MinHeap { 
      
    // To store array of elements in heap 
    private Node[] heapArray; 
      
    // max size of the heap 
    private int capacity; 
      
    // Current number of elements in the heap 
    private int current_heap_size; 
  
    // Constructor  
    public MinHeap(int n) { 
        capacity = n; 
        heapArray = new Node[capacity]; 
        current_heap_size = 0; 
    } 
      
    // Swapping using reference  
    private void swap(Node[] arr, int a, int b) { 
        Node temp = arr[a]; 
        arr[a] = arr[b]; 
        arr[b] = temp; 
    } 
      
      
    // Get the Parent index for the given index 
    private int parent(int key) { 
        return (key - 1) / 2; 
    } 
      
    // Get the Left Child index for the given index 
    private int left(int key) { 
        return 2 * key + 1; 
    } 
      
    // Get the Right Child index for the given index 
    private int right(int key) { 
        return 2 * key + 2; 
    } 
      
    public int getHeapSize() {
        return current_heap_size;
    }
      
    // Inserts a new key 
    public boolean insertKey(Node key) { 
        if (current_heap_size == capacity) { 
              
            // heap is full 
            return false; 
        } 
      
        // First insert the new key at the end  
        int i = current_heap_size; 
        heapArray[i] = key; 
        current_heap_size++; 
          
        // Fix the min heap property if it is violated  
        while (i != 0 && heapArray[i].getFVal() < heapArray[parent(i)].getFVal()) { 
            swap(heapArray, i, parent(i)); 
            i = parent(i); 
        } 
        return true; 
    } 
      
    // Decreases value of given key to new_val.  
    // It is assumed that new_val is smaller  
    // than heapArray[key].  
    public void decreaseKey(int key, Node new_val) { 
        heapArray[key] = new_val; 
  
        while (key != 0 && heapArray[key].getFVal() < heapArray[parent(key)].getFVal()) { 
            swap(heapArray, key, parent(key)); 
            key = parent(key); 
        } 
    } 
      
    // Returns the minimum key (key at 
    // root) from min heap  
    public Node getMin() { 
        return heapArray[0]; 
    } 
      
      
    // Method to remove minimum element  
    // (or root) from min heap  
    public Node extractMin() { 
        if (current_heap_size <= 0) { 
            return null; 
        } 
  
        if (current_heap_size == 1) { 
            current_heap_size--; 
            return heapArray[0]; 
        } 
          
        // Store the minimum value,  
        // and remove it from heap  
        Node root = heapArray[0]; 
  
        heapArray[0] = heapArray[current_heap_size - 1]; 
        current_heap_size--; 
        MinHeapify(0); 
  
        return root; 
    }
      
    //Breaks ties in favor or lower G values, otherwise picks lowest F value
    private void MinHeapify(int key) { 
        int l = left(key); 
        int r = right(key); 
  
        int smallest = key; 
        if (l < current_heap_size && heapArray[l].getFVal() < heapArray[smallest].getFVal()) { 
            smallest = l; 
        } 
        if (r < current_heap_size && heapArray[r].getFVal() < heapArray[smallest].getFVal()) { 
            smallest = r;
        } 
        if (l < current_heap_size && heapArray[l].getFVal() == heapArray[smallest].getFVal() && heapArray[l].getGVal() < heapArray[smallest].getGVal()) {
                smallest = l;
        }
        if (r < current_heap_size && heapArray[r].getFVal() == heapArray[smallest].getFVal() && heapArray[r].getGVal() < heapArray[smallest].getGVal()) {
            smallest = r;
    }
        
  
        if (smallest != key) { 
            swap(heapArray, key, smallest); 
            MinHeapify(smallest); 
        } 
    } 
      
    // Increases value of given key to new_val. 
    // It is assumed that new_val is greater  
    // than heapArray[key].  
    // Heapify from the given key 
    public void increaseKey(int key, Node new_val) { 
        heapArray[key] = new_val; 
        MinHeapify(key); 
    } 
      
    // Changes value on a key 
    public boolean changeValueOnAKey(int key, Node new_val) { 
        if (heapArray[key].getFVal() == new_val.getFVal()) { 
            return false; 
        } 
        if (heapArray[key].getFVal() < new_val.getFVal()) { 
            increaseKey(key, new_val); 
            return true;
        } else { 
            decreaseKey(key, new_val); 
            return true;
        } 
    } 

    //Returns the index of the desired node, otherwise returns -1
    public int findIndex(Node node) {
        for (int i = 0; i < heapArray.length; i++) {
            if (heapArray[i] != null && heapArray[i].equals(node)) {
                return i;
            }
        }
        return -1;
    }
} 
  