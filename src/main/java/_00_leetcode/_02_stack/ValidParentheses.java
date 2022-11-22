package _00_leetcode._02_stack;

import java.util.Stack;

/**
 * 20. Valid Parentheses
 * https://leetcode.com/problems/valid-parentheses/
 */

public class ValidParentheses {

    //利用stack的方式
    // 见左括号，就入栈。
    // 见右括号，就弹栈，来匹配

    public boolean isValid(String s){
        Stack<Character> stack = new Stack<>();

        // Step 1: when you meet the left character, push it into the stack
        for(int i = 0; i < s.length(); i++){
            char ch = s.charAt(i);

            // Step 1: when you meet the left character, push it into the stack
            if (ch == '(' || ch == '[' || ch == '{') stack.push(ch);
            else { // Step 2: when you meet the right character
                // Situation 2.1: if the stack is empty, the brackets are invalid
                if (stack.isEmpty()) return false;
                // Situation 2.2: if the stack is not empty, pop the top character of the stack to match the right character
                else {
                    Character leftB = stack.pop();

                    if (leftB == '(' && ch != ')') return false;
                    if (leftB == '[' && ch != ']') return false;
                    if (leftB == '{' && ch != '}') return false;
                }
            }
        }

        // Step 3: After all characters are scanned
        // The stack is empty, indicating that the brackets are valid
        // The stack is not empty, indicating that the brackets are invalid

        return stack.isEmpty();

    }


    // 下面这个代码，按逻辑是正确的，但是会出现Time limit exceed 的错误，原因是：效率过低
//    public boolean isValid(String s){
//        while(s.contains("()") || s.contains("[]") || s.contains("{}")){
//            s.replace("()", "");
//            s.replace("[]", "");
//            s.replace("{}", "");
//        }
//
//        return s.isEmpty();
//    }

}
