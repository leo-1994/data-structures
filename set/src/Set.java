/**
 * @author chao.li@quvideo.com
 * @date 2019/10/17 11:10
 */
public interface Set<E> {
    void add(E e);

    void remove(E e);

    boolean contains(E e);

    int getSize();

    boolean isEmpty();
}
