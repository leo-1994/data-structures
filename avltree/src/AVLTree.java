import java.util.ArrayList;
import java.util.List;

/**
 * @author chao.li@quvideo.com
 * @date 2019/10/24 11:38
 */
public class AVLTree<K extends Comparable<K>, V> {
    private class Node {
        public K key;
        public V value;
        public Node left, right;
        public int height;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.height = 1;
        }
    }

    private Node root;
    private int size;

    public AVLTree() {
        root = null;
        size = 0;
    }

    private int getHeight(Node node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    /**
     * 获得节点node的平衡因子
     *
     * @param node
     * @return
     */
    private int getBalanceFactor(Node node) {
        if (node == null) {
            return 0;
        }
        return getHeight(node.left) - getHeight(node.right);
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
        // 更新height
        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
        // 计算平衡影子
        int balanceFactor = getBalanceFactor(node);
        // 平衡维护
        // LL
        if (balanceFactor > 1 && getBalanceFactor(node.left) >= 0) {
            return rightRotate(node);
        }
        // RR
        if (balanceFactor < -1 && getBalanceFactor(node.right) <= 0) {
            return leftRotate(node);
        }
        // LR
        if (balanceFactor > 1 && getBalanceFactor(node.left) < 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        // RL
        if (balanceFactor < -1 && getBalanceFactor(node.right) > 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        return node;
    }

    /**
     * 右旋转
     * ｜       y                       x
     * ｜      / \                     /  \
     * ｜     x   T4                  z    y
     * ｜    / \        ------>     / \   / \
     * ｜   z  T3                  T1 T2 T3 T4
     * ｜  / \
     * ｜ T1 T2
     *
     * @param y
     * @return
     */
    private Node rightRotate(Node y) {
        Node x = y.left;
        Node t3 = x.right;
        x.right = y;
        y.left = t3;
        // 更新height
        updateHeight(y);
        updateHeight(x);
        return x;
    }

    /**
     * 左旋
     *
     * @param y
     * @return
     */
    private Node leftRotate(Node y) {
        Node x = y.right;
        Node t3 = x.left;
        x.left = y;
        y.right = t3;
        // 更新height
        updateHeight(y);
        updateHeight(x);
        return x;
    }

    private void updateHeight(Node node) {
        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
    }

    public void add(K key, V value) {
        root = add(root, key, value);
    }

    public boolean contains(K key) {
        return getNode(root, key) != null;
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

    public void set(K key, V value) {
        Node node = getNode(root, key);
        if (node == null) {
            throw new IllegalArgumentException(key + " doesn't exist!");
        }
        node.value = value;
    }

    public V get(K key) {
        Node node = getNode(root, key);
        return node == null ? null : node.value;
    }

    public boolean isBST() {
        ArrayList<K> keys = new ArrayList<>();
        inOrder(root, keys);
        for (int i = 1; i < keys.size(); i++) {
            if (keys.get(i - 1).compareTo(keys.get(i)) > 0) {
                return false;
            }
        }
        return true;
    }

    private void inOrder(Node node, ArrayList<K> keys) {
        if (node == null) {
            return;
        }
        inOrder(node.left, keys);
        keys.add(node.key);
        inOrder(node.right, keys);
    }

    /**
     * 判断该二叉树是否是一棵平衡二叉树
     *
     * @return
     */
    public boolean isBalanced() {
        return isBalanced(root);
    }

    private boolean isBalanced(Node node) {
        if (node == null) {
            return true;
        }
        int balanceFactor = getBalanceFactor(node);
        if (Math.abs(balanceFactor) > 1) {
            return false;
        }
        return isBalanced(node.left) && isBalanced(node.right);
    }

    public void remove(K key) {
        remove(root, key);
    }

    private Node remove(Node node, K key) {
        if (node == null) {
            return null;
        }
        Node ret;
        if (key.compareTo(node.key) < 0) {
            node.left = remove(node.left, key);
            ret = node;
        } else if (key.compareTo(node.key) > 0) {
            node.right = remove(node.right, key);
            ret = node;
        } else {
            // e == node.e
            // 删除只有左子树或只有右子树的节点很简单
            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                size--;
                ret = rightNode;
            } else if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size--;
                ret = leftNode;
            } else {
                // 删除左右都有孩子的节点d，需要找到s=min(d->right)
                // s是d的后继
                // s->right=delMin(d->right)
                // s->left=d->left
                Node successor = minimum(node.right);
                successor.right = remove(node.right, successor.key);
                successor.left = node.left;
                node.left = node.right = null;
                ret = successor;
            }
        }
        if (ret == null) {
            return ret;
        }
        // 更新height
        updateHeight(ret);
        // 平衡维护
        int balanceFactor = getBalanceFactor(ret);
        if (balanceFactor > 1) {
            if (getBalanceFactor(node.left) >= 0) {
                // LL
                return rightRotate(ret);
            } else {
                // LR
                ret.left = leftRotate(ret.left);
                return rightRotate(ret);
            }
        }
        if (balanceFactor < -1) {
            if (getBalanceFactor(node.right) <= 0) {
                // RR
                return leftRotate(ret);
            } else {
                // RL
                ret.right = rightRotate(ret.right);
                return leftRotate(ret);
            }
        }
        return ret;
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

    public static void main(String[] args) {
        String filename = "/Users/leo/workspace/data-structures/set/pride-and-prejudice.txt";
        List<String> words = new ArrayList<>();
        FileOperation.readFile(filename, words);
        System.out.println("Total words:" + words.size());
        AVLTree<String, Integer> map = new AVLTree<>();
        for (String word : words) {
            if (map.contains(word)) {
                map.set(word, map.get(word) + 1);
            } else {
                map.add(word, 1);
            }
        }
        System.out.println("is BST:" + map.isBST());
        System.out.println("is Balanced:" + map.isBalanced());
        for (String word : words) {
            map.remove(word);
            if (!map.isBalanced() || !map.isBalanced()) {
                throw new RuntimeException("Error");
            }
        }
    }



}
