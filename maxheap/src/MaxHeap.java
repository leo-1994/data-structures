/**
 * @author chao.li@quvideo.com
 * @date 2019/10/18 17:51
 */
public class MaxHeap<E extends Comparable<E>> {
    private Array<E> data;

    public MaxHeap(int capacity) {
        data = new Array<>(capacity);
    }

    public MaxHeap() {
        data = new Array<>();
    }

    /**
     * 将数组转换成最大堆
     *
     * @param arr
     */
    public MaxHeap(E[] arr) {
        data = new Array<>(arr);
        for (int i = parent(arr.length - 1); i >= 0; i--) {
            siftDown(i);
        }
    }

    public int size() {
        return data.getSize();
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }

    /**
     * 找到index位置元素的父节点索引
     *
     * @param index
     * @return
     */
    private int parent(int index) {
        if (index == 0) {
            throw new IllegalArgumentException("index-0 doesn't have parent.");
        }
        return (index - 1) / 2;
    }

    /**
     * 找到index位置元素的左孩子节点索引
     *
     * @param index
     * @return
     */
    private int leftChild(int index) {
        return index * 2 + 1;
    }

    /**
     * 找到index位置元素的右孩子节点索引
     *
     * @param index
     * @return
     */
    private int rightChild(int index) {
        return index * 2 + 2;
    }

    public void add(E e) {
        data.addLast(e);
        siftUp(data.getSize() - 1);
    }

    /**
     * 获得堆中最大元素
     *
     * @return
     */
    public E findMax() {
        if (data.getSize() == 0) {
            throw new IllegalArgumentException("findMax error,heap is empty");
        }
        return data.get(0);
    }

    /**
     * 取出堆中最大元素
     *
     * @return
     */
    public E extractMax() {
        E ret = findMax();
        data.swap(0, data.getSize() - 1);
        data.removeLast();
        siftDown(0);
        return ret;
    }

    /**
     * 上浮
     *
     * @param k
     */
    private void siftUp(int k) {
        int parent;
        while (k > 0 && data.get(parent = parent(k)).compareTo(data.get(k)) < 0) {
            // 如果父节点比子节点还小，则交换父子节点
            data.swap(parent, k);
            k = parent;
        }
    }

    /**
     * 下沉
     *
     * @param k
     */
    private void siftDown(int k) {
        while (leftChild(k) < data.getSize()) {
            int j = leftChild(k);
            if (j + 1 < data.getSize() && data.get(j + 1).compareTo(data.get(j)) > 0) {
                // j为左孩子节点的索引，j+1为右孩子节点的索引
                j = j + 1;
                // 此时data[j]是k的左右孩子节点的较大值
            }
            if (data.get(k).compareTo(data.get(j)) >= 0) {
                // 如果data[k]的值大于data[j],则无需下沉，循环结束
                break;
            }
            // 否则交换k和j，下沉一层
            data.swap(k, j);
            k = j;
        }
    }

    /**
     * 取出堆中最大元素，并且替换成元素e
     *
     * @param e
     * @return
     */
    public E replace(E e) {
        E ret = findMax();
        data.set(0, e);
        siftDown(0);
        return ret;
    }

}
