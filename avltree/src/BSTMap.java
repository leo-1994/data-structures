import java.util.ArrayList;
import java.util.List;

/**
 * @author chao.li@quvideo.com
 * @date 2019/10/17 15:53
 */
public class BSTMap<K extends Comparable<K>, V> {

    private class Node {
        public K key;
        public V value;
        public Node left, right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private Node root;
    private int size;

    public BSTMap() {
        this.root = null;
        this.size = 0;
    }

    private Node add(Node node, K key, V value) {
        if (node == null) {
            size++;
            return new Node(key, value);
        }
        int c = key.compareTo(node.key);
        if (c < 0) {
            node.left = add(node.left, key, value);
        } else if (c > 0) {
            node.right = add(node.right, key, value);
        }
        return node;
    }

    private Node getNode(Node node, K key) {
        if (node == null) {
            return null;
        }
        int c = key.compareTo(node.key);
        if (c == 0) {
            return node;
        } else if (c < 0) {
            return getNode(node.left, key);
        } else {
            return getNode(node.right, key);
        }
    }

    private Node remove(Node node, K key) {
        if (node == null) {
            return null;
        }
        if (node.key.compareTo(key) < 0) {
            node.left = remove(node.left, key);
            return node;
        } else if (node.key.compareTo(key) > 0) {
            node.right = remove(node.right, key);
            return node;
        } else {
            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            } else if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            } else {
                // 删除左右都有孩子的节点d，需要找到s=min(d->right)
                // s是d的后继
                // s->right=delMin(d->right)
                // s->left=d->left
                Node successor = minimum(node.right);
                successor.right = removeMin(node.right);
                successor.left = node.left;
                node.left = node.right = null;
                return successor;
            }
        }
    }

    /**
     * 删除掉以node为根的二分搜索树中的最小节点
     * 返回删除节点后新的二分搜索树的根
     *
     * @param node
     * @return
     */
    private Node removeMin(Node node) {
        if (node.left == null) {
            Node rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }
        node.left = removeMin(node.left);
        return node;
    }

    /**
     * 返回以node为根的二分搜索树的最小值所在的节点
     *
     * @param node
     * @return
     */
    private Node minimum(Node node) {
        if (node.left == null) {
            return node;
        }
        return minimum(node.left);
    }


    public static void main(String[] args) {
        String filename = "/Users/leo/workspace/data-structures/set/pride-and-prejudice.txt";
        List<String> words = new ArrayList<>();
        FileOperation.readFile(filename, words);
        System.out.println("Total words:" + words.size());
        
    }
}
