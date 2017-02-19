import java.util.function.BiPredicate;

/**
 * Created by larken on 9/22/16.
 */
public class AVLTree<Line2D> implements SearchTree {

    public AVLTree(BiPredicate pred) {

    }

    Node<Key> insert(Key key);

    Node<Key> search(Key key);

    void delete(Key key);
}
