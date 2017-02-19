import java.util.function.BiPredicate;
/**
 * Created by Steven Myers on 9/22/16.
 */
public class AVLTree<Key> implements SearchTree<Key> {
    AVLNode<Key> root;
    BiPredicate pred;
    // We can keep a running total of our rotations to see if it's what we intended
    int LR, RL, LL, RR = 0;

    public AVLTree(BiPredicate pred) {
        this.pred = pred;
    }

    public class AVLNode<Key> implements Node<Key> {
        AVLNode<Key> right, left, parent;
        Key key;
        int height;


        public AVLNode(Key key, AVLNode left, AVLNode right, AVLNode parent) {
            this.key = key;
            this.left = left;
            this.right = right;
            this.parent = parent;
        }

        public Node<Key> getMin(AVLNode n) {
            if (n.left == null) {
                return n;
            } else {
                return getMin(n.left);
            }
        }

        public Node<Key> after() {
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

        public Node<Key> getMax(AVLNode n) {
            if (n.right == null) {
                return n;
            } else {
                return getMax(n.right);
            }
        }

        public Node<Key> before() {
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

    /* End AVLNode class */

    public int getHeight(AVLNode n) {
        if (n != null) {
            return n.height;
        } else {
            return -1;
        }
    }


    public AVLNode insert_helper(AVLNode n, Key key, AVLNode parent) {
        if (n == null) {
            n = new AVLNode(key, null, null, parent);
        } else if (this.pred.test(key, n.getKey())) { // Left
            n.left = insert_helper(n.left, key, n);
            // Check if we need to rotate
            if (Math.abs(getHeight(n.left) - getHeight(n.right)) == 2) {
                // We have a bf of 2 so we need to rotate
                // We need to check the left node's key against the key we're inserting into the left node.
                // so that we know whether or not we have a left child or right child (ll or lr) rotation
                if (this.pred.test(key, n.left.getKey())) { // Left Child
                    // LL rotation
                    n = LLRotation(n);
                } else {
                    // LR rotation
                    n = LRRotation(n);
                }
            }
        } else if (!(this.pred.test(key, n.getKey()))) { // Right
            n.right = insert_helper(n.right, key, n);
            // Check if we need to rotate
            if (Math.abs(getHeight(n.right) - getHeight(n.left)) == 2) {
                // We have a bf of 2 so we need to rotate
                // We need to check the left node's key against the key we're inserting into the left node.
                // so that we know whether or not we have a left child or right child (rr or rl)
                if ((! this.pred.test(key, n.right.getKey())) && key != n.right.getKey()) { // Right Child
                    // RR rotation
                    n = RRRotation(n);
                } else {
                    // RL rotation
                    n = RLRotation(n);
                }
            }

        } else { // Duplicate!
            System.out.println("Error - tree does not support duplicates.");
        }

        n.height = Math.max(getHeight(n.left), getHeight(n.right)) + 1;
        return n;
    }


    // Returns newly inserted node in the AVLTree
    public Node<Key> insert(Key key) {
        return this.root = insert_helper(this.root, key, null); // Newly inserted node
    }

    /* Rotation Functions */

    public AVLNode LLRotation(AVLNode a) {
        // Doing an LL so let's update our running count
        LL++;
        // We need the node to the left of a
        AVLNode b = a.left;
        // Set the a.left to b.right, since b.right is < a
        a.left = b.right;
        // Set b right to be a
        b.right = a;
        // Fix the parents
        b.parent = a.parent;
        a.parent = b;
        // Fix the heights for a both a and b
        a.height = Math.max(getHeight(a.left), getHeight(a.right)) + 1;
        b.height = Math.max(getHeight (b.left), a.height) + 1;
        return b;
    }

    public AVLNode RRRotation(AVLNode a) {
        // Doing an RR so let's update our running count
        RR++;
        // We need the node to the right of a
        AVLNode b = a.right;
        // Set the a.right to b.left, since b.left is < a
        a.right = b.left;
        // Set b left to be a
        b.left = a;
        // Fix the parents
        b.parent = a.parent;
        a.parent = b;
        // Fix the heights for a both a and b
        a.height = Math.max(getHeight(a.right), getHeight(a.left)) + 1;
        b.height = Math.max(getHeight (b.right), a.height) + 1;
        return b;
    }
    /* Our double rotations are easy, since they are just reusing the ll and rr rotations defined above. */
    public AVLNode RLRotation(AVLNode a) {
        // Doing an RL so let's update our running count
        RL++;
        // We take the node to the right of the root (a), and perform a LL rotation
        a.right = LLRotation(a.right);
        a = RRRotation(a);
        return a;
    }
    // We do the same for LR rotation, but flipped
    public AVLNode LRRotation(AVLNode a) {
        // Doing an LR so let's update our running count
        LR++;
        a.left = RRRotation(a.left);
        a = LLRotation(a);
        return a;
    }

    public Node<Key> search(Key key) {
        return search_helper(root, key);
    }

    public Node<Key> search_helper(AVLNode n, Key key) {
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

    public AVLNode get_min(AVLNode n) { // Duplicate *-
        // method, but exists outside of Node<Key> class
        if (n.left == null) {
            return n;
        } else {
            return get_min(n.left);
        }
    }

    public int calculateBF(AVLNode n) { // If n is null return 0 else return the BF
        return (n == null) ? 0: (getHeight(n.left) - getHeight(n.right));
    }
    /* Very similar to BST delete; however, instead of setting the root equal to delete_helper we will handle the
     *  changes within the delete_helper function because we need to work up from the deleted node */
    public AVLNode delete_helper(Key key, AVLNode n) {
        if (n == null) {
            return n;
        } else if (this.pred.test(key, (Key) n.getKey())) { // remove in left subtree
            n.left = delete_helper(key, n.left);
        } else if (! this.pred.test(key, (Key) n.getKey()) && (n.key != key)) { // remove in right subtree, check for equality!
            n.right = delete_helper(key, n.right);
        } else { // remove this node
            if (n.right == null || n.left == null) { // We have either no children, or just one.
                // We want to store our child in a tmp var, since we don't want to delete anything to the
                // left or right of the node - just the node we're at
                AVLNode tmp;
                if (n.left == null) {
                    tmp = n.right;
                } else {
                    tmp = n.left;
                }

                // Check to see if there were any children
                if (tmp == null) { // no children
                    tmp = n;
                    n = null;
                } else { // one child
                    // Here, our nodes's only child becomes the current node. We can't forget to fix the new node's parent though
                    tmp.parent = n.parent;
                    n = tmp;
                }

            } else { // Our node to be deleted has two children in this case
                // We need to get the in-order successor, just as in a BST delete.
                AVLNode min = get_min((AVLNode) n.right);
                n.key = min.key;
                n.right = delete_helper((Key) min.key, n.right);
            }
        }

        /* We've removed our node - but we need to work up from the deleted node (or the new node in its place)
           and adjust the tree accordingly. We also need to update the height of our current node */
        if (n == null) {
            return n;
        } else {
            n.height = Math.max(getHeight(n.left), getHeight(n.right)) + 1;
            // To decide what rotations we need to do, we need to check the BF for
            // the current node and its children for each case (LL, RR, RL, LR)
            if (calculateBF(n) > 1 && calculateBF(n.left) >= 0) {
                // LL
                return LLRotation(n);
            } else if (calculateBF(n) > 1 && calculateBF(n.left) < 0) {
                // LR
                return LRRotation(n);
            } else if (calculateBF(n) < -1 && calculateBF(n.right) <= 0) {
                // RR
                return RRRotation(n);
            } else if (calculateBF(n) < -1 && calculateBF(n.right) > 0) {
                // RL
                return RLRotation(n);
            }
        }
        return n;
    }

    public void delete(Key key) {
        this.root = delete_helper(key, this.root);
    }

    public void printAVLTree_helper(AVLNode n) { // Prints BST preorder
        if (n == null) {
        } else {
            System.out.println(n.key);
//            System.out.format("Left of %d:\n", n.key);
            printAVLTree_helper(n.left);
//            System.out.format("Right of %d:\n", n.key);
            printAVLTree_helper(n.right);

        }
    }

    public void printAVLTree() {
        printAVLTree_helper(this.root);
    }

    public static void main(String[] args) {
        BiPredicate<Integer, Integer> testPred = (x, y) -> x < y; // If currVal is less than some key
        AVLTree<Integer> T = new AVLTree<Integer>(testPred);
        // int[] A = {5, 4, 3, 6, 7}; // Contains LL and RR rotations
        int[] A = {6,4,5, 8, 7}; // Contains LR and RL

        for (int i = 0; i < A.length; i++) {
//            System.out.format("Inserting %d\n", A[i]);
            T.insert(A[i]);
        }
        System.out.println("Finished AVL Tree with: ");
        System.out.println("Rotations *LL and RR rotations will +1 for each LR/RL since it's used in those functions as well");
        System.out.println("---------");
        System.out.println("LL\tRR\tLR\tRL");
        System.out.format("%d\t%d\t%d\t%d\n", T.LL, T.RR, T.LR, T.RL);
        System.out.println("Printing tree in pre-order:");
        T.printAVLTree();
        /* Testing before, after, and search */
//        System.out.println("Before 6: " + T.search(6).before().getKey());
//        System.out.println("After 6: " + T.search(6).after().getKey());
        /* Deletion Testing */
        T.LL = 0;
        T.RR = 0;
        T.LR = 0;
        T.RL = 0;

        System.out.println("Deleting 4:");
        T.delete(4);
        System.out.println("Rotations *LL and RR rotations will +1 for each LR/RL since it's used in those functions as well");
        System.out.println("---------");
        System.out.println("LL\tRR\tLR\tRL");
        System.out.format("%d\t%d\t%d\t%d\n", T.LL, T.RR, T.LR, T.RL);
        T.printAVLTree();
    }
}
