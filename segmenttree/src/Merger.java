/**
 * @author chao.li@quvideo.com
 * @date 2019/10/22 14:34
 */
public interface Merger<E> {
    E merge(E a, E b);
}
