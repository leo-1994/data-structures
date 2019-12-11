import java.util.ArrayList;

/**
 * 基于二分搜索树的集合
 *
 * @author chao.li@quvideo.com
 * @date 2019/10/17 11:11
 */
public class BSTSet<E extends Comparable<E>> implements Set<E> {

    private BST<E> bst;

    public BSTSet() {
        this.bst = new BST<>();
    }

    @Override
    public void add(E e) {
        bst.add(e);
    }

    @Override
    public void remove(E e) {
        bst.remove(e);
    }

    @Override
    public boolean contains(E e) {
        return bst.contains(e);
    }

    @Override
    public int getSize() {
        return bst.size();
    }

    @Override
    public boolean isEmpty() {
        return bst.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println("Pride and Prejudice");

        ArrayList<String> words1 = new ArrayList<>();
        FileOperation.readFile("/Users/leo/workspace/data-structures/set/pride-and-prejudice.txt", words1);
        System.out.println("Total wwords:" + words1.size());
        BSTSet<String> set1 = new BSTSet<>();
        for (String word : words1) {
            set1.add(word);
        }
        System.out.println("Total different words:" + set1.getSize());

        System.out.println();

        System.out.println("a-tale-of-two-cities");

        ArrayList<String> words2 = new ArrayList<>();
        FileOperation.readFile("/Users/leo/workspace/data-structures/set/a-tale-of-two-cities.txt", words2);
        System.out.println("Total wwords:" + words2.size());
        BSTSet<String> set2 = new BSTSet<>();
        for (String word : words2) {
            set2.add(word);
        }
        System.out.println("Total different words:" + set2.getSize());
    }
}
