import java.util.LinkedHashSet;
import java.util.function.BiFunction;
import java.util.Set;
import java.util.LinkedList;
import java.util.Random;

class Entry<K,V> {
    Entry(K k, V v) { key = k; value = v; }
    final K key;
    V value;
    int hash;

    public String toString() {
        return "{" +String.valueOf(key) + "=" + String.valueOf(value) + "}";
    }
}


@SuppressWarnings("unchecked")
public class HashTableImp<K,V> implements HashTable<K,V>{
    // an array of linked lists to handle chaining
    private LinkedList<Entry<K,V>>[] array;
    private int itemCount;
    private BiFunction<K,Integer,Integer> h;

    private int hash(K k){
	return this.h.apply(k,this.itemCount);
    }

    public HashTableImp(int initialCapacity, HashMethod hm){
	// This cast violates type safety of the program but we wanted
	// you to implement hash table on top of a data structure that
	// does not do dynamic resizing (arrays instead of ArrayList)
	// so you can do the hash table growing yourself.
	// Do NOT do it in the future.
//	this.array = (LinkedList<Entry<K,V>>[]) new Object[initialCapacity];
    this.array = (LinkedList<Entry<K,V>>[]) new LinkedList<?>[initialCapacity];

	this.itemCount = 0;
	switch (hm) {
        case Div:
            // division method
            h = (k,m) -> {return k.hashCode() % m;};
            break;
        case MAD:
            h = (k,m) -> {return mad(k,m);};
            break;
	    }
    }

    // Implement all the following stubs

    /* I found this helper function on stackoverflow.com, at
     * http://stackoverflow.com/questions/23644479/find-next-prime-number written by @Ben_Aaronson */
    public int nextPrime(int number) {
        while(true) {
            boolean isPrime = true;
            //increment the number by 1 each time
            number = number + 1;

            int x = (int) Math.sqrt(number);

            //start at 2 and increment by 1 until it gets to the squared number
            for (int i = 2; i <= x; i++)  {
                if (number % i == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) return number;
        }
    }

    // Multiply-Add-and-Divide (MAD) hashing method
    private int mad(K k, int m) {
        Random rand = new Random();
        /* Select the next prime number greater than m */
        int p = nextPrime(m);
        /* Choose two numbers between 1 and m - 1 */
        int a = rand.nextInt(m) + 1;
        int b = rand.nextInt(m) + 1;
        int index = ((a * k.hashCode() + b) % p) % m;
        return index;
    }

    // this method needs to be called appropriately in the put method
    private void growTable() {
        // Instantiate new array double the size
        LinkedList<Entry<K, V>>[] tmp = (LinkedList<Entry<K, V>>[]) new LinkedList<?>[this.array.length * 2];
        for (int i = 0; i < this.array.length; i++) {
            // Visit every chain of our current array
            if (this.array[i] != null) {
                // If there's a linked list there, instantiate a new linked list
                // with all the same key, values into the new, expanded array
                tmp[i] = new LinkedList<Entry<K, V>>(this.array[i]);
            }
        }
        // Set the current array to be the updated expanded array
        this.array = tmp;
    }

    @Override
    public boolean containsKey(K key){
//        int index = h.apply(key, this.array.length);
//        LinkedList<Entry<K,V>> chain =  this.array[index];
//        boolean found = false;
//        for (Entry<K,V> item : chain) {
//            if (item.key == key) {
//                found = true;
//            }
//        }
//        return found;
        for (int i = 0; i < this.array.length; i++) {
            if (this.array[i] != null) {
                for (Entry<K,V> item : this.array[i]) {
                    if (item.key == key) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public V get(K key) {
//        THIS ONLY WORKS FOR DIV
//        int index = h.apply(key, this.array.length);
//        System.out.println("Trying to grab entry at index " + index);
//        LinkedList<Entry<K,V>> chain =  this.array[index];
//        for (Entry<K,V> item : chain) {
//   )        if (item.key == key) {
//                return item.value;
//            }
//        }
//        return null; // Should throw an exception
        for (int i = 0; i < this.array.length; i++) {
            if (this.array[i] != null) {
                for (Entry<K,V> item : this.array[i]) {
                    if (item.key == key) {
                        return item.value;
                    }
                }
            }
        }
        return null;
    }

    @Override
    public V put(K key, V value) {
        if (! containsKey(key)) {
            int index = h.apply(key, this.array.length);
            if (this.array[index] == null) {
                this.array[index] = new LinkedList<Entry<K,V>>();
            }
            LinkedList<Entry<K,V>> chain =  this.array[index];
            chain.add(new Entry<K,V>(key, value));
            ++itemCount;

            // If the array is more than 3/4 full, we need to double the table
            if (itemCount > (this.array.length * 3) / 4) growTable();
            return value;
        } else {
            return null;
        }
    }

    // not used in this client code so make sure it works correctly by
    // implementing unit tests.
    @Override
    public boolean isEmpty(){
        return itemCount == 0;
    }

    @Override
    public Set<K> keySet() {
        LinkedHashSet<K> set = new LinkedHashSet<K>();
        for (int i = 0; i < this.array.length; i++) {
            if (this.array[i] != null) {
                for (Entry<K,V> item : this.array[i]) {
                    set.add(item.key);
                }
            }
        }
        return set;
    }

    // not used in this client code so make sure it works correctly by
    // implementing unit tests.
    @Override
    public V remove(K key) {
//        int index = h.apply(key, this.array.length);
//        LinkedList<Entry<K,V>> chain =  this.array[index];
//        for (Entry<K,V> item : chain) {
//            if (item.key == key) {
//                chain.remove(item);
//                itemCount--;
//                return item.value;
//            }
//        }
//        return null; // Throw exception
        for (int i = 0; i < this.array.length; i++) {
            if (this.array[i] != null) {
                for (Entry<K,V> item : this.array[i]) {
                    if (item.key == key) {
                        this.array[i].remove(item);
                        itemCount--;
                        return item.value;
                    }
                }
            }
        }
        return null;
    }

    public String toString() {
        String buf = "";
        for (int i = 0; i < this.array.length; i++) {
            if (this.array[i] != null) {
                for (Entry<K,V> item : this.array[i]) {
                    buf += item.toString() + " ";
                }
            }
        }
        return buf;
    }

    public void print() {
        for (int i = 0; i < this.array.length; i++) {
            System.out.format("["+i+"] [");
            if (this.array[i] != null) {
                for (Entry<K,V> item : this.array[i]) {
                    System.out.format("%s ", item.toString());
                }
            }
            System.out.format("]\n");
        }
    }

    public static void main(String[] args) {
        HashTableImp<Character, Integer> HT = new HashTableImp<>(12, HashMethod.MAD);
        Character keys[] = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l',};
        assert HT.isEmpty();
        for (int i = 0; i < keys.length; i++) {
            if (i == (HT.array.length * 3 / 4)) {
                int tmp = HT.array.length;
                HT.put(keys[i], i);
                assert HT.array.length == tmp * 2;
//                System.out.format("Increasing table size from %d to %d\n", tmp, HT.array.length);
            } else {
                HT.put(keys[i], i);
            }
            assert HT.get(keys[i]) == i;
            assert HT.containsKey(keys[i]);
            assert HT.itemCount == i + 1;

        }
        assert ! HT.isEmpty();
//        HT.print();
//        System.out.println("SET: " + HT.keySet().toString());
        for (int i = 0; i < keys.length; i++) {
            assert HT.get(keys[i]) == HT.remove(keys[i]);
            assert ! HT.containsKey(keys[i]);
        }
        HashTableImp<Character, Integer> HT2 = new HashTableImp<>(12, HashMethod.Div);
        assert HT2.isEmpty();
        for (int i = 0; i < keys.length; i++) {
            if (i == (HT2.array.length * 3 / 4)) {
                int tmp = HT2.array.length;
                HT2.put(keys[i], i);
                assert HT2.array.length == tmp * 2;
//                System.out.format("Increasing table size from %d to %d\n", tmp, HT2.array.length);
            } else {
                HT2.put(keys[i], i);
            }
            assert HT2.get(keys[i]) == i;
            assert HT2.containsKey(keys[i]);
            assert HT2.itemCount == i + 1;

        }
        assert ! HT2.isEmpty();
//        HT2.print();
//        System.out.println("SET: " + HT2.keySet().toString());
        for (int i = 0; i < keys.length; i++) {
            assert HT2.get(keys[i]) == HT2.remove(keys[i]);
            assert ! HT2.containsKey(keys[i]);
        }

    }
}
