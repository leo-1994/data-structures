import java.util.Random;

/**
 * @author chao.li@quvideo.com
 * @date 2019/10/24 10:13
 */
public class Main {
    public static void main(String[] args) {
        int size = 10000000;
        int m = 10000000;
//        UnionFind1 uf1 = new UnionFind1(size);
//        System.out.println("UnionFind1:" + testUf(uf1, m));
//        UnionFind2 uf2 = new UnionFind2(size);
//        System.out.println("UnionFind2:" + testUf(uf2, m));
        UnionFind3 uf3 = new UnionFind3(size);
        System.out.println("UnionFind3:" + testUf(uf3, m));
        UnionFind4 uf4 = new UnionFind4(size);
        System.out.println("UnionFind4:" + testUf(uf4, m));
        UnionFind5 uf5 = new UnionFind5(size);
        System.out.println("UnionFind5:" + testUf(uf5, m));
        UnionFind6 uf6 = new UnionFind6(size);
        System.out.println("UnionFind6:" + testUf(uf6, m));
    }

    private static double testUf(UF uf, int m) {
        int size = uf.getSize();
        Random random = new Random();
        long startTime = System.nanoTime();
        for (int i = 0; i < m; i++) {
            int a = random.nextInt(size);
            int b = random.nextInt(size);
            uf.unionElements(a, b);
        }
        for (int i = 0; i < m; i++) {
            int a = random.nextInt(size);
            int b = random.nextInt(size);
            uf.isConnected(a, b);
        }
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1000000000.0;
    }
}
