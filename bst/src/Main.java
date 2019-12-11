import java.util.ArrayList;
import java.util.Random;

/**
 * @author chao.li@quvideo.com
 * @date 2019/10/7 18:51
 */
public class Main {

    public static void main(String[] args) {
        BST<Integer> bst = new BST<>();
        Random random = new Random();
        int n = 1000;
        for (int i = 0; i < n; i++) {
            bst.add(random.nextInt(10000));
        }
        ArrayList<Integer> nums = new ArrayList<>();
        while (!bst.isEmpty()) {
            nums.add(bst.removeMax());
        }
        System.out.println(nums);
    }
}
