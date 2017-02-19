import java.util.function.BiPredicate;

/**
 * Created by larken on 9/22/16.
 */

public class BinarySearchTree<Key> implements SearchTree<Key> {
    BSTNode root;
    BiPredicate<Key, Key> pred;


    public BinarySearchTree(BiPredicate<Key, Key> pred) {
        this.pred = pred;
    }

    public class BSTNode<Key> implements Node<Key> {
        BSTNode<Key> next;
        BSTNode<Key> prev;
        Key key;

        public BSTNode(Key key, BSTNode prev, BSTNode next) {
            this.key = key;
            this.prev = prev;
            this.next = next;
        }

        public Node<Key> after() {
            return this.next;
        }

        public Node<Key> before() {
            return this.prev;

        }

        public Key getKey() {
            return this.key;

        }

    }

    public Node<Key> insert(Key key) {
        return this.root = (BSTNode) insert_helper(this.root, key);
    }

    public Node<Key> insert_helper(BSTNode n, Key key) {
        if (n == null) {
            return new BSTNode<>(key, null, null);
        } else  if (this.pred.test(key, (Key) n.getKey())) { // Left
            n.prev = (BSTNode) insert_helper((BSTNode) n.before(), key);
            return n;
        } else if (! (this.pred.test(key, (Key) n.getKey()))) { // Right
            n.next = (BSTNode) insert_helper((BSTNode) n.after(), key);
            return n;
        } else {
            return n;
        }
    }

    public Node<Key> search(Key key) {
        return null;

    }

    public void delete(Key key) {

    }
}
