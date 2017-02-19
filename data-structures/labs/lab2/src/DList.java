class DList implements Sequence {
    DNode sentinel;
    DList() {
        sentinel = new DNode(444, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    // insert before pos, return iterator pointing to new node
    public Iterator insert(Iter pos, int e) {
        DNode tmp = pos.curr.prev;
        pos.curr.prev = new DNode(e, pos.curr, tmp);
        tmp.next = pos.curr.prev;
        return new Iter(pos.curr.prev);
    }

    // erase the node at pos, return iterator to the next element
    public Iterator erase(Iter pos) {
        pos.curr.prev.next = pos.curr.next;
        pos.curr.next.prev = pos.curr.prev;
        DNode tmp = pos.curr.next;
        return new Iter(tmp);
    }

    public boolean empty() {
        return (this.sentinel.next == sentinel);
    }

    public class Iter implements Iterator, BidirectionalIterator {
        DNode curr;
        Iter(DNode n) {
            curr = n;
        }

        public int get() { return curr.data; }

        public Iterator advance() {
            curr = curr.next;
            return this;
        }

        public BidirectionalIterator retreat() {
            curr = curr.prev;
            return this;
        }

        public boolean equals(Iterator other) {
            return curr == ((Iter)other).curr;
        }
    }

    public Iter begin() { return new Iter(sentinel.next); }
    public Iter end() { return new Iter(sentinel); }

    public static void main(String[] args) {
        DList L = new DList();
        assert L.empty();
        for (int i = 0; i != 10; ++i) {
            L.insert(L.end(), i);
        }
        assert ! L.empty();
        int k = 0;
        for (Iterator i=L.begin(); ! i.equals(L.end()); i.advance()) {
            assert i.get() == k;
            System.out.println(i.get());
            ++k;
        }
        k = 9;
        for (BidirectionalIterator i=L.end().retreat();
        ! i.equals(L.begin().retreat());
        i.retreat()) {
            assert i.get() == k;
            --k;
        }

        // DList test = new DList();
        // Iter iterator = test.begin();
        // test.insert(iterator, 42);
        // test.toString();
    }
}
