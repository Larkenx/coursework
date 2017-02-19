import java.util.Stack;
import java.util.HashSet;

class BSTNode {
    int key;
    int kPos;
    BSTNode left;
    BSTNode right;

    BSTNode(int k, BSTNode l, BSTNode r, int kpos) {
        key = k;
        left = l;
        right = r;
        kPos = kpos;
    }
}


class BinarySearchTree {
    BSTNode root;
    BinarySearchTree() {
        root = null;
    }

    boolean find(int k) {
        return find_helper(root, k) != null;
    }
    void insert(int key) {
        if (root == null) {
            root = insert_helper(root, key, 0, root);
        } else {
            root = insert_helper(root, key, root.kPos, root);
        }
    }
    void remove(int key) {
        root = remove_helper(root, key);
    }

    int k_helper(BSTNode n, int k) {
        //   System.out.println("N=" + n.key + ", k=" + n.kPos);
        if (n == null) {
            throw new java.lang.UnsupportedOperationException();
        } else if (n.kPos < k) {
            return k_helper(n.right, k);
        } else if (n.kPos > k) {
            return k_helper(n.left, k);
        } else {
            //   System.out.println("Found node with kpos= " + k);
            return n.key;
        }
    }

    int kth_smallest(int k) throws java.lang.Exception {
        return k_helper(root, k);
    }

    public void print_tree() { System.out.print(tree_to_string(root)); }

    private String tree_to_string(BSTNode n) {
        if (n != null) {
            return String.format("(%s %d %s)",
            tree_to_string(n.left),
            n.key,
            tree_to_string(n.right));
        }
        return "";
    }

    // Helper Functions

    private BSTNode find_helper(BSTNode n, int key) {
        if (n == null) {
            return null;
        } else if (key < n.key) {
            return find_helper(n.left, key);
        } else if (key > n.key) {
            return find_helper(n.right, key);
        } else {
            return n;
        }
    }

    private void increment_k(BSTNode n) {
        if (n == null) {
            return;
        } else {
            n.kPos++;
            increment_k(n.left);
            increment_k(n.right);
        }
    }

    private BSTNode insert_helper(BSTNode n, int key, int k, BSTNode original) { // k is initially 0
        BSTNode tmp = n;
        if (n == null) {
            return new BSTNode(key, null, null, k);
        } else if (key < n.key) {
            n.kPos += 1;
            n.left = insert_helper(n.left, key, n.kPos - 1, tmp);
            increment_k(n.right);
            return n;
        } else if (key > n.key) {
            n.right = insert_helper(n.right, key, n.kPos + 1, tmp);
            return n;
        } else { // no need to insert, already there
            return original;
        }
    }

    private BSTNode delete_min(BSTNode n) {
        if (n.left == null) {
            return n.right;
        } else {
            n.left = delete_min(n.left);
            return n;
        }
    }

    private BSTNode get_min(BSTNode n) {
        if (n.left == null) {
            return n;
        }
        else {
            return get_min(n.left);
        }
    }

    private BSTNode remove_helper(BSTNode n, int key) {
        if (n == null) {
            return null;
        } else if (key < n.key) { // remove in left subtree
            n.left = remove_helper(n.left, key);
            return n;
        } else if (key > n.key) { // remove in right subtree
            n.right = remove_helper(n.right, key);
            return n;
        } else { // remove this node
            if (n.left == null) {
                return n.right;
            } else if (n.right == null) {
                return n.left;
            } else { // two children, replace this with min of right subtree
                BSTNode min = get_min(n.right);
                n.key = min.key;
                n.right = delete_min(n.right);
                return n;
            }
        }
    }

    private BSTNode get_helper(BSTNode n, int key) throws java.lang.Exception  {
        if (n.key == key) {
            return n;
        } else if (key < n.key) {
            return find_helper(n.left, key);
        } else if (key > n.key) {
            return find_helper(n.right, key);
        } else {
            throw new java.lang.UnsupportedOperationException();
        }
    }

    // I know it's a terrible solution..but hey, it works
    private int smallcount(int k) {
        BSTNode result = null;
        try {
            BSTNode tmp = this.root;
            if (find_helper(tmp, k) != null) {
                result = get_helper(tmp, k);
                return result.kPos + 1;
            } else {
                insert_helper(tmp, k, tmp.kPos, tmp);
                result = get_helper(tmp, k);
                return result.kPos;
            }
        } catch (Exception e) {}
        return -1;
    }

    public static void main(String[] args) {
        BinarySearchTree T = new BinarySearchTree();
        HashSet<Integer> H = new HashSet<Integer>();
        int[] A = {5, 4, 3, 1, 8, 9};
        int[] A_sorted = { 1, 2, 4, 5, 8, 9 };

        for (int i = 0; i != A.length; ++i) {
            T.insert(A[i]);
            H.add(A[i]);
        }

        System.out.println(T.smallcount(5));
    }
}
