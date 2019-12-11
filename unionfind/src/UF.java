/**
 * 并查集 Union Find
 *
 * @author chao.li@quvideo.com
 * @date 2019/10/24 09:54
 */
public interface UF {

    /**
     * 查看元素p和元素q是否属于一个集合
     *
     * @param p
     * @param q
     * @return
     */
    boolean isConnected(int p, int q);

    void unionElements(int p, int q);

    int getSize();
}
