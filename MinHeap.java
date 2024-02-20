 class MinHeap { 
    public Node[] heapArray; 
    private int capacity; 
    private int current_heap_size;
    private int tie;
    
    public MinHeap(int n, int tie) { 
        capacity = n; 
        this.tie = tie;
        heapArray = new Node[capacity]; 
        current_heap_size = 0; 
    } 
       
    private void swap(Node[] arr, int a, int b) { 
        Node temp = arr[a]; 
        arr[a] = arr[b]; 
        arr[b] = temp; 
    } 
      
    private int parent(int key) { 
        return (key - 1) / 2; 
    } 
      
    private int left(int key) { 
        return 2 * key + 1; 
    } 
      
    private int right(int key) { 
        return 2 * key + 2; 
    } 
      
    public int getHeapSize() {
        return current_heap_size;
    }
      
    public boolean insertKey(Node key) { 
        if (current_heap_size == capacity) { 
            return false; 
        } 
       
        int i = current_heap_size; 
        heapArray[i] = key; 
        current_heap_size++; 
           
        while (i != 0 && heapArray[i].getFVal() < heapArray[parent(i)].getFVal()) { 
            swap(heapArray, i, parent(i)); 
            i = parent(i); 
        } 
        return true; 
    } 
      
    public void decreaseKey(int key, Node new_val) { 
        heapArray[key] = new_val; 
  
        while (key != 0 && heapArray[key].getFVal() < heapArray[parent(key)].getFVal()) { 
            swap(heapArray, key, parent(key)); 
            key = parent(key); 
        } 
    } 
      
    public Node getMin() { 
        return heapArray[0]; 
    } 
      
    public Node extractMin() { 
        if (current_heap_size <= 0) { 
            return null; 
        } 
  
        if (current_heap_size == 1) { 
            current_heap_size--; 
            return heapArray[0]; 
        } 
          
        Node root = heapArray[0]; 
  
        heapArray[0] = heapArray[current_heap_size - 1]; 
        current_heap_size--; 
        MinHeapify(0); 
  
        return root; 
    }
    
    public void deleteKey(int key) { 
        heapArray[key].setGVal(Integer.MIN_VALUE);
        decreaseKey(key, heapArray[key]); 
        extractMin(); 
    } 

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
        if (l < current_heap_size && heapArray[l].getFVal() == heapArray[smallest].getFVal()) {
            if(tie == 0) {
                if (heapArray[l].getGVal() < heapArray[smallest].getGVal())
                    smallest = l;
            } else {
                if (heapArray[l].getGVal() > heapArray[smallest].getGVal())
                    smallest = l;
            }
        }
        if (r < current_heap_size && heapArray[r].getFVal() == heapArray[smallest].getFVal() && heapArray[r].getGVal() < heapArray[smallest].getGVal()) {
            if(tie == 0) {
                if (heapArray[r].getGVal() < heapArray[smallest].getGVal())
                    smallest = r;
            } else {
                if (heapArray[r].getGVal() > heapArray[smallest].getGVal())
                    smallest = r;
            }
    }
        
  
        if (smallest != key) { 
            swap(heapArray, key, smallest); 
            MinHeapify(smallest); 
        } 
    } 

    public boolean changeValueOnAKey(int key, Node new_val) { 
        if (heapArray[key].getFVal() <= new_val.getFVal()) { 
            return false; 
        } else { 
            decreaseKey(key, new_val); 
            return true;
        } 
    } 

    public int findIndex(Node node) {
        for (int i = 0; i < heapArray.length; i++) {
            if (heapArray[i] != null && heapArray[i].equals(node)) {
                return i;
            }
        }
        return -1;
    }
} 
  