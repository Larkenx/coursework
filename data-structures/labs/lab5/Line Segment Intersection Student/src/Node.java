public interface Node<Key> {

	Node<Key> after();

	Node<Key> before();
	
	Key getKey();
}
