class List {
    Node head;
    List() {
        this.head = null;
    }

    List(int[] A) {
        head = array_to_list(A);
    }

    static Iterator linear_search(Sequence L, int x) {
        for (Iterator i = L.begin(); ! i.equals(L.end()); i.advance()) {
            if (i.get() == x) {
                return i;
            }
        }
        return L.end();
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
        return new Iterator(null); // Null pointer!
    }

    boolean equals(Iterator other) {
        return curr == other.curr;
    }


}
