/**
 * @author chao.li@quvideo.com
 * @date 2019/9/30 15:20
 */
public interface Stack<E> {

    void push(E e);

    E pop();

    E peek();

    int getSize();

    boolean isEmpty();
}
