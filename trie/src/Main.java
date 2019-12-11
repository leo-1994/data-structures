import java.util.ArrayList;

/**
 * @author chao.li@quvideo.com
 * @date 2019/10/22 19:00
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Pride and Prejudice");
        ArrayList<String> words1 = new ArrayList<>();
        FileOperation.readFile("/Users/leo/workspace/data-structures/set/pride-and-prejudice.txt", words1);
        System.out.println("Total words:" + words1.size());
        long startTime = System.nanoTime();
        BSTSet<String> bstSet = new BSTSet<>();

    }
}
