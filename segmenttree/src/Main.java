/**
 * @author chao.li@quvideo.com
 * @date 2019/10/22 14:22
 */
public class Main {
    public static void main(String[] args) {
        Integer[] nums = {-2, 0, 3, -5, 2, -1};
        SegmentTree<Integer> segTree = new SegmentTree<>(nums, Integer::sum);
        System.out.println(segTree.toString());
        System.out.println(segTree.query(1,2));
        System.out.println(segTree.query(0,5));

    }
}
