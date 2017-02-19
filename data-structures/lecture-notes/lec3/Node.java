public class Node {
    int data;
    Node next;

    Node(int d, Node n) {
        this.data = d;
        this.next = next;
    }

    static Node arrayToList(int[]A) {
        Node list = null;
        for (int i = A.length; i != -1; --i) {
            list = new Node(A[i], list);
        }
        return list;
    }

    static Node listRef(Node list, int k) {
        if (list == null) {
            return null;
        } else if (k == 0) {
            return list;
        } else {
            return listRef(list.next, k - 1);
        }
    }

    static Node listRef2(Node list, int k) {
        for (Node n = list; n != null; n = n.next) {
            if (k == 0) {
                return n;
            } else {
                --k;
            }
        }
        return null;
    }

    static void removeNext(Node n) {
        if ((n.next != null) || n != null) {
            n.next = (n.next).next;
        }
    }

    /*
     * Forward Traversal Pattern for Linked List
     * -----------------------------------------
     *  for (Node n = begin; n != end; n = n.next) {
     *      ... n.data ...
     *  }
     *
     *  # Doubly linked lists feature forward and backward pointers
     */
}
