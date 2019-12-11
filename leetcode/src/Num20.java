import java.util.Stack;

/**
 * 有效的括号
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 * <p>
 * 有效字符串需满足：
 * <p>
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 注意空字符串可被认为是有效字符串。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "()"
 * 输出: true
 * 示例 2:
 * <p>
 * 输入: "()[]{}"
 * 输出: true
 * 示例 3:
 * <p>
 * 输入: "(]"
 * 输出: false
 * 示例 4:
 * <p>
 * 输入: "([)]"
 * 输出: false
 * 示例 5:
 * <p>
 * 输入: "{[]}"
 * 输出: true
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author chao.li@quvideo.com
 * @date 2019/9/30 15:40
 */
public class Num20 {
    private static final char LEFT1 = '(';
    private static final char LEFT2 = '{';
    private static final char LEFT3 = '[';
    private static final char RIGHT1 = ')';
    private static final char RIGHT2 = '}';
    private static final char RIGHT3 = ']';

    public boolean isValid(String s) {
        if (s.isEmpty()) {
            return true;
        }
        if (s.length() % 2 == 1) {
            return false;
        }
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (isLeft(c)) {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                char t = stack.pop();
                if (c != getRight(t)) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    private boolean isLeft(char c) {
        return LEFT1 == c || LEFT2 == c || LEFT3 == c;
    }

    private char getRight(char c) {
        if (LEFT1 == c) {
            return RIGHT1;
        }
        if (LEFT2 == c) {
            return RIGHT2;
        }
        if (LEFT3 == c) {
            return RIGHT3;
        }
        return ' ';
    }

    public static void main(String[] args) {
        Num20 num20 = new Num20();
        System.out.println(num20.isValid("(("));
    }
}
