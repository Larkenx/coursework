import java.util.function.BiPredicate;

/**
 * Created by Steven Myers on 9/22/16.
 */

public class BinarySearchTree<Key> implements SearchTree<Key> {
    BSTNode root;
    BiPredicate<Key, Key> pred; // Predicate is true for a left traversal, false for right traversal


    public BinarySearchTree(BiPredicate<Key, Key> pred) {
        this.pred = pred;
    }

    public class BSTNode<Key> implements Node<Key> {
        BSTNode<Key> right;
        BSTNode<Key> left;
        BSTNode<Key> parent;
        Key key;

        public BSTNode(Key key, BSTNode left, BSTNode right, BSTNode parent) {
            this.key = key;
            this.left = left;
            this.right = right;
            this.parent = parent;
        }

        public Node<Key> getMin(BSTNode n) {
            if (n.left == null) {
                return n;
            } else {
                return getMin(n.left);
            }
        }

        public Node<Key> after() { // O(1)
            if (this.right != null) { // Left will always be in-order
                return getMin(this.right);
            } else if (this.parent == null) {
                return null;
            } else if (this.parent.left == this) {
                return this.parent;
            } else {
                if (this.parent.parent != null) {
                    if (this.parent == this.parent.parent.left) {
                        return this.parent.parent;
                    } else { // Duplicate case...
                        return null;
                    }
                } else { // Duplicate case...
                    return null;
                }
            }
        }

        public Node<Key> getMax(BSTNode n) {
            if (n.right == null) {
                return n;
            } else {
                return getMax(n.right);
            }
        }

        public Node<Key> before() { // O(1)
            if (this.left != null) { // Left will always be in-order
                return getMax(this.left);
            } else if (this.parent == null) {
                return null;
            } else if (this.parent.right == this) {
                return this.parent;
            } else {
                if (this.parent.parent != null) {
                    if (this.parent == this.parent.parent.right) {
                        return this.parent.parent;
                    } else { // Duplicate case...
                        return null;
                    }
                } else { // Duplicate case...
                    return null;
                }
            }
        }

        public Key getKey() {
            return this.key;
        }

    }

    public Node<Key> insert(Key key) {
        return this.root = (BSTNode) insert_helper(this.root, key, this.root);
    }

    public Node<Key> insert_helper(BSTNode n, Key key, BSTNode parent) {
        if (n == null) {
            return new BSTNode<>(key, null, null, parent);
        } else if (this.pred.test(key, (Key) n.getKey())) { // Left
            n.left = (BSTNode) insert_helper(n.left, key, n);
            return n;
        } else if (!(this.pred.test(key, (Key) n.getKey()))) { // Right
            n.right = (BSTNode) insert_helper(n.right, key, n);
            return n;
        } else {
            return n;
        }
    }

    public Node<Key> search(Key key) {
        return search_helper(root, key);

    }

    public Node<Key> search_helper(BSTNode n, Key key) {
        if (n == null) {
            return null;
        } else if (this.pred.test(key, (Key) n.getKey())) { // Left
            return search_helper(n.left, key);
        } else if ((!(this.pred.test(key, (Key) n.getKey()))) && (n.key != key)) { // Right
            return search_helper(n.right, key);
        } else {
            return n;
        }
    }

    public BSTNode delete_min(BSTNode n) {
        if (n.left == null) {
            return n.right;
        } else {
            n.left = delete_min(n.left);
            return n;
        }
    }

    public BSTNode get_min(BSTNode n) { // Duplicate method, but exists outside of Node<Key> class
        if (n.left == null) {
            return n;
        } else {
            return get_min(n.left);
        }
    }

    public void delete(Key key) {
        root = delete_helper(root, key);
    }

    public BSTNode delete_helper(BSTNode n, Key key) {
        if (n == null) {
            return null;
        } else if (this.pred.test(key, (Key) n.getKey())) { // remove in left subtree
            n.left = delete_helper(n.left, key);
            return n;
        } else if (! this.pred.test(key, (Key) n.getKey()) && (n.key != key)) { // remove in right subtree, check for equality!
            n.right = delete_helper(n.right, key);
            return n;
        } else { // remove this node
            if (n.left == null) {
                if (n.right != null) n.right.parent = n.parent;
                return n.right;
            } else if (n.right == null) {
                n.left.parent = n.parent;
                return n.left;
            } else { // two children, replace this with min of right subtree
                BSTNode min = get_min((BSTNode) n.right);
                n.key = min.key;
                n.right = delete_min(n.right);
                return n;
            }
        }
    }

    public void printBST_helper(BSTNode n) { // Prints BST preorder
        if (n == null) {
        } else {
            System.out.println(n.key);
            printBST_helper(n.left);
            printBST_helper(n.right);
        }
    }

    public void printBST() {
        printBST_helper(this.root);
    }

    public static void main(String[] args) {
        BiPredicate<Integer, Integer> testPred = (x, y) -> x < y; // If currVal is less than some key
        BinarySearchTree<Integer> T = new BinarySearchTree<Integer>(testPred);
        int[] A = {6, 3, 4, 7, 1, 9, 8};
        for (int i = 0; i < A.length; i++) {
            T.insert(A[i]);
        }
        System.out.println("Original:");
        T.printBST();
        T.delete(7);
        System.out.println("Deleted 7:");
        T.printBST();
        System.out.println("Before 6: " + T.search(6).before().getKey());


    }
}
