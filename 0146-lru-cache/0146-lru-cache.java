



class LRUCache {
    
    class Node {
        int key;
        int value;
        Node next;
        Node prev;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    class LinkedList {
        Node b; // most recently used (tail)
        Node f; // least recently used (head)
        HashMap<Integer, Node> map = new HashMap<>();
        int capacity;

        LinkedList(int capacity) {
            this.capacity = capacity;
            this.f = null;
            this.b = null;
        }

        private void removeNode(Node temp) {
            if (temp.prev != null) {
                temp.prev.next = temp.next;
            } else {
                // temp was the front
                f = temp.next;
            }
            if (temp.next != null) {
                temp.next.prev = temp.prev;
            } else {
                // temp was the back
                b = temp.prev;
            }
            temp.next = null;
            temp.prev = null;
        }

        private void addToBack(Node temp) {
            if (b == null) {
                f = b = temp;
            } else {
                b.next = temp;
                temp.prev = b;
                b = temp;
            }
        }

        public void add(int key, int value) {
            if (map.containsKey(key)) {
                // update existing
                Node temp = map.get(key);
                temp.value = value;
                removeNode(temp);
                addToBack(temp);
                return;
            }

            // new key
            if (capacity == 0) {
                // evict LRU
                Node lru = f;
                map.remove(lru.key);
                removeNode(lru);
                capacity++;
            }

            // insert new node
            Node temp = new Node(key, value);
            addToBack(temp);
            map.put(key, temp);
            capacity--;
        }

        public int get(int key) {
            if (!map.containsKey(key)) {
                return -1;
            }
            Node temp = map.get(key);
            removeNode(temp);
            addToBack(temp);
            return temp.value;
        }
    }

    LinkedList cache;

    public LRUCache(int capacity) {
        cache = new LinkedList(capacity);
    }
    
    public int get(int key) {
        return cache.get(key);
    }
    
    public void put(int key, int value) {
        cache.add(key, value);
    }
}


// class LRUCache {
    
//     class Node{
//         int key;
//         int value;
//         Node next;
//         Node prev;

//         Node(int key,int value){
//             this.key=key;
//             this.value=value;
//         }
//     }

//     class linkedList{
        
//         Node b;
//         Node f;
//         HashMap<Integer,Node> map=new HashMap<>();
//         int capacity;

//         linkedList(int capacity){this.capacity=capacity; this.f=null;this.b=null;}

//         public void add(int key,int value){
//             if(this.b==null){
//                 this.b=new Node(key,value);
//                 this.f=b;
//                 map.put(key,this.b);
//                 this.capacity--;
//                 return;
//             }

//             if(capacity==0&&!this.map.containsKey(key)){
//                 this.map.remove(this.f.key); 
//                 Node temp=this.f;
//                 this.f=temp.next;

//                 if(this.f!=null)
//                     this.f.prev=null;

//                 temp.next=null;

//                 this.capacity++;
//             }
//             if(!this.map.containsKey(key)){
//                 this.capacity--;
//                 Node temp=new Node(key,value);
//                 this.b.next=temp;
//                 temp.prev=this.b;
//                 this.b=temp;
//                 this.map.put(key,temp); 
            
                
//             }
//             else{
//                 Node temp=map.get(key);
//                 if(temp==this.b){
//                     this.b.value=value;
//                     return;
//                 }
//                 temp.value=value;
//                 Node t1=temp.prev;
//                 Node t2=temp.next;
//                 temp.next=null;
//                 temp.prev=null;
//                 if(t1!=null)t1.next=t2;
//                 if(t2!=null)t2.prev=t1;
//                 this.b.next=temp;
//                 temp.prev=this.b;
//                 this.b=temp;  
//             }
//         }


//         public int get(int key){
//             if(this.map.containsKey(key)){
//                 Node temp=this.map.get(key);

//                 if(temp != this.b){
//                     Node t1=temp.prev;
//                     Node t2=temp.next;
//                     temp.next=null;
//                     temp.prev=null;
//                     if(t1!=null)t1.next=t2;
//                     if(t2!=null)t2.prev=t1;
//                     this.b.next=temp;
//                     temp.prev=this.b;
//                     this.b=temp;
//                 }

//                 return temp.value;
//             }
//             return -1;
//         }
//     }

//     linkedList cache;

//     public LRUCache(int capacity) {
//         cache = new linkedList(capacity);
        
//     }
    
//     public int get(int key) {
        
//         return cache.get(key);
        
//     }
    
//     public void put(int key, int value) {
//         cache.add(key,value);
        
//     }
// }

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */