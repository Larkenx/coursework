class BinarySearchTree {
    BSTNode root;

    boolean find(int k) {
        return true;
    }

    BSTNode find_helper(BSTNode n, int key) {
        if (n == null) {
            return null;
        } else {
            if (key < n.key) {
                return find_helper(n.left, key);
            } else if (key > n.key) {
                return find_helper(n.right, key);
            } else {
                return n;
            }
        }
    }

    BSTNode get_min(BSTNode n) {
        if (n.left == null) {
            return n;
        } else {
            return get_min(n.left);
        }
    }

    BSTNode get_max(BSTNode n) {
        if (n.right == null) {
            return n;
        } else {
            return get_max(n.right);
        }
    }

    public static void main(String[] args) {

    }
}
