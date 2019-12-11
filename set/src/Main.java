import java.util.ArrayList;

/**
 * @author chao.li@quvideo.com
 * @date 2019/10/17 11:16
 */
public class Main {

    private static double testSet(Set<String> set, String filename) {
        long startTime = System.nanoTime();

        System.out.println(filename);
        ArrayList<String> words = new ArrayList<>();
        FileOperation.readFile(filename, words);
        System.out.println("Total words:" + words.size());
        for (String word : words) {
            set.add(word);
        }
        System.out.println("Total different words:" + set.getSize());

        long endTime = System.nanoTime();
        return (endTime - startTime) / 1000000000.0;
    }

    public static void main(String[] args) {
        String filename = "/Users/leo/workspace/data-structures/set/pride-and-prejudice.txt";
        Set<String> bstSet = new BSTSet<>();
        System.out.println("BST Set:" + testSet(bstSet, filename) + "s");
        Set<String> linkedListSet = new LinkedListSet<>();
        System.out.println("LinkedList Set:" + testSet(linkedListSet, filename) + "s");
    }

}
