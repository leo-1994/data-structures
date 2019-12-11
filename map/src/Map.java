/**
 * @author chao.li@quvideo.com
 * @date 2019/10/17 15:31
 */
public interface Map<K,V> {

    void add(K key,V value);

    V remove(K key);

    boolean contains(K key);

    V get(K key);

    void set(K key,V value);

    int getSize();

    boolean isEmpty();
}
