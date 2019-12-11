/**
 * @author chao.li@quvideo.com
 * @date 2019/9/27 17:14
 */
public class Main {
    public static void main(String[] args) {
        Array<Integer> array=new Array<>(2);
        for (int i=0;i<10;i++){
            array.addLast(i);
        }
        array.add(1,100);
        System.out.println(array);
    }
}
