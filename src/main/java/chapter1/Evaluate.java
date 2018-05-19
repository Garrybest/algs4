package chapter1;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: dijkstra双栈算术表达式求值
 * @Author: Fang Rui
 * @Date: 2018/4/18
 * @Time: 16:06
 */
public class Evaluate {
    public static void main(String[] args) {
        while (!StdIn.isEmpty()) {
            Stack<String> ops = new Stack<>();
            Stack<Double> vals = new Stack<>();
            List<String> expression = new ArrayList<>();
            char[] expressionArray = StdIn.readString().trim().toCharArray();
            StringBuilder expressionItem = new StringBuilder();
            for (int i = 0; i < expressionArray.length; i++) {
                char element = expressionArray[i];
                expressionItem.append(element);
                if (i == expressionArray.length - 1) {
                    expression.add(expressionItem.toString());
                    break;
                }
                if (Character.isDigit(element) || element == '.') {
                    if (!(Character.isDigit(expressionArray[i + 1]) || expressionArray[i + 1] == '.')) {
                        expression.add(expressionItem.toString());
                        expressionItem.delete(0, expressionItem.length());
                    }
                    continue;
                }
                expression.add(expressionItem.toString());
                expressionItem.delete(0, expressionItem.length());
            }

            for (String element : expression) {
                if (element.equals("(")) ;
                else if (element.equals("+")) ops.push(element);
                else if (element.equals("-")) ops.push(element);
                else if (element.equals("*")) ops.push(element);
                else if (element.equals("/")) ops.push(element);
                else if (element.equals(")")) {
                    double a = vals.pop();
                    double b = vals.pop();
                    String op = ops.pop();
                    if (op.equals("+")) vals.push(a + b);
                    else if (op.equals("-")) vals.push(a - b);
                    else if (op.equals("*")) vals.push(a * b);
                    else if (op.equals("/")) vals.push(a / b);
                } else vals.push(Double.parseDouble(element));
            }
            StdOut.println(vals.pop());
        }
    }
}
