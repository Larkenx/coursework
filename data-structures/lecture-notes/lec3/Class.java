class List {
    Node head;
    List() {
        this.head = null;
    }

    List(int[] A) {
        head = array_to_list(A);
    }

}

class Iterator {
    Node curr;
    Iterator(Node n) {
        this.curr = n;
    }

    int get() {
        return curr.data;
    }

    void advance() {
        curr = curr.next;
    }

    Iterator begin() {
        return new Iterator(head);
    }

    Iterator end() {
        return new Iterator(null);
    }

    boolean equals(Iterator other) {
        return curr == other.curr;
    }
        
        
}
