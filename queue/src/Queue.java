/**
 * @author chao.li@quvideo.com
 * @date 2019/9/30 16:30
 */
public interface Queue<E> {
    /**
     * 入队
     *
     * @param e e
     */
    void enqueue(E e);

    /**
     * 出队
     *
     * @return E
     */
    E dequeue();

    /**
     * 查看队首
     *
     * @return E
     */
    E getFront();

    int getSize();

    boolean isEmpty();
}
