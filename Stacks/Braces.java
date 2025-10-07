package Stacks;

import java.util.Stack;

public class Braces {
    // If it's an opening, push to stack
    // If it's a closing:
    // 1. check if stack is empty, and if it is - return false
    // 2. match the brace/bracket/parenthesis, and if matched, continue but first pop it out
    // 3.check if it's empty, if not - return false, otehrwise return true

    // abc{dc[]egd}
    // stack: {[
    public static boolean isBalanced(String message) {
        Stack<Character> stack = new Stack<>();
        for (char c : message.toCharArray()) {
            if (c == '{' || c == '[') {
                stack.push(c);
            }
            else if (c == '}' || c == ']') {
                if (stack.empty()) return false;

                if ((stack.peek() == '{' && c == '}') || 
                    (stack.peek() == '[' && c == ']')) {
                        stack.pop();
                }
                else {
                    return false;
                }
            }
        }
        return stack.empty();
    }

    public static void main(String[] args) {
        System.out.println(isBalanced("abc{defg{[ijk]}{l{mn}}op}qr"));
    }
}
