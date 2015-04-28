/*
 CSE 17
 Pablo Aviles
 pfa217
 Program Description: Creates a binary tree to solve arithmetic expressions
 Program #10
 */
package prog10;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class InFixBinaryTreeConverter {

    List<String> inFix;
    List<String> stack;
    List<Node> btstack;
    String expression;

    /**
     * Constructor, initializes binary tree
     */
    public InFixBinaryTreeConverter() {
        inFix = new LinkedList<>();
        stack = new ArrayList<>();
        btstack = new ArrayList<>();
    }

    /**
     * Driver for the program
     *
     * @param e
     */
    public void run(String e) {
        expression = e;
        System.out.println(expression);
        createInFix();
        createBinaryTree();
        System.out.println(btstack.get(0).toString());
        System.out.println(inorder(btstack.get(0)));
        System.out.println(preorder(btstack.get(0)));
        System.out.println(postorder(btstack.get(0)));
        System.out.println(optimize(btstack.get(0)).element);

    }

    /**
     *
     * Fills inFix with values from the arithmetic expression
     */
    private void createInFix() {
        String[] a;
        a = expression.split("\\s+");
        inFix.addAll(Arrays.asList(a));
    }

    /**
     * Creates a binary from inFix values
     */
    public void createBinaryTree() {
        stack.add("(");
        inFix.add(")");

        while (!inFix.isEmpty()) {
            if (isNumber(inFix.get(0))) {
                Node n1 = new Node(inFix.get(0), null, null);
                btstack.add(n1);
                inFix.remove(0);
            } else {
                if (isLParen(inFix.get(0))) {
                    stack.add(inFix.get(0));
                    inFix.remove(0);
                } else {
                    if (isOperator(inFix.get(0))) {
                        while (isOperator(inFix.get(0)) && isHigherPrecedence(stack.get(stack.size() - 1), inFix.get(0))) {
                            Node n1 = btstack.get(0);
                            btstack.remove(0);
                            Node n2 = btstack.get(0);
                            btstack.remove(0);

                            Node n3 = new Node(stack.get(stack.size() - 1), n1, n2);
                            btstack.add(n3);
                            stack.remove(stack.size() - 1);
                        }
                        stack.add(inFix.get(0));
                        inFix.remove(0);
                    } else {
                        if (isRParen(inFix.get(0))) {
                            while (!isLParen(stack.get(stack.size() - 1))) {
                                Node n1 = btstack.get(0);
                                btstack.remove(0);
                                Node n2 = btstack.get(0);
                                btstack.remove(0);

                                Node n3 = new Node(stack.get(stack.size() - 1), n1, n2);
                                btstack.add(n3);
                                //stack.add(inFix.get(0));
                                inFix.remove(0);
                                stack.remove(stack.size() - 1);
                            }
                            stack.remove(stack.size() - 1);
                        }
                    }
                }
            }
        }
    }

    /**
     * Solves arithmetic expression
     *
     * @param node
     * @return Solved node
     */
    public Node optimize(Node node) {
        Node n = node;
        int r = 0;

        if (n == null) {
            return null;
        } else {
            optimize(node.left);
            optimize(node.right);
            if (isOperator(n.element)) {
                if (n.element.equals("*")) {
                    if (n.left.equals("0") || n.right.equals(0)) {
                        n.element = "0";
                    } else {
                        if (n.left.equals("1")) {
                            n.element = n.right.toString();
                        } else {
                            if (n.right.equals("1")) {
                                n.element = n.left.toString();
                            } else {
                                if (isNumber(n.left.element) && isNumber(n.right.element)) {
                                    int a = Integer.parseInt(n.left.element);
                                    int b = Integer.parseInt(n.right.element);
                                    r = a * b;
                                    n.element = "";
                                    n.element += r;
                                }
                            }
                        }
                    }
                } else {
                    if (n.element.equals("+")) {
                        if (isNumber(n.left.element) && isNumber(n.right.element)) {
                            int a = Integer.parseInt(n.left.element);
                            int b = Integer.parseInt(n.right.element);
                            r = a + b;
                            n.element = "";
                            n.element += r;

                        }
                    } else {
                        if (n.element.equals("-")) {
                            if (isNumber(n.left.element) && isNumber(n.right.element)) {
                                int a = Integer.parseInt(n.left.element);
                                int b = Integer.parseInt(n.right.element);
                                r = a - b;
                                n.element = "";
                                n.element += r;

                            }
                        } else {
                            if (n.element.equals("/")) {
                                if (isNumber(n.left.element) && isNumber(n.right.element)) {
                                    int a = Integer.parseInt(n.left.element);
                                    int b = Integer.parseInt(n.right.element);
                                    r = a / b;
                                    n.element = "";
                                    n.element += r;

                                }
                            }
                        }

                    }
                }
            }
        }

        return n;
    }

    /**
     * Shows in order expression
     *
     * @param node
     * @return expression in order
     */
    public String inorder(Node node) {
        String ex = "";
        ex += node.left.left.left.left.element + " ";
        ex += node.left.left.left.element + " ";
        ex += node.left.left.left.right.element + " ";
        ex += node.left.left.element + " ";
        ex += node.left.left.right.element + " ";
        ex += node.left.element + " ";
        ex += node.left.right.element + " ";
        ex += node.element + " ";
        ex += node.right.element + " ";
        return ex;
    }

    /**
     * Shows pre order expression
     *
     * @param node
     * @return expression in pre order
     */
    public String preorder(Node node) {
        String ex = "";
        ex += node.element + " ";
        ex += node.left.element + " ";
        ex += node.left.left.element + " ";
        ex += node.left.left.left.element + " ";
        ex += node.left.left.left.left.element + " ";
        ex += node.left.left.left.right.element + " ";
        ex += node.left.left.right.element + " ";
        ex += node.left.right.element + " ";
        ex += node.right.element + " ";
        return ex;
    }

    /**
     * Shows post order expression
     *
     * @param node
     * @return expression in post order
     */
    public String postorder(Node node) {
        String ex = "";
        ex += node.left.left.left.left.element + " ";
        ex += node.left.left.left.right.element + " ";
        ex += node.left.left.left.element + " ";
        ex += node.left.left.right.element + " ";
        ex += node.left.left.element + " ";
        ex += node.left.right.element + " ";
        ex += node.left.element + " ";
        ex += node.right.element + " ";
        ex += node.element + " ";
        return ex;
    }

    /**
     * Verifies if the String is a number using a regular expression
     *
     * @param s
     * @return the string is a number
     */
    public boolean isNumber(String s) {
        return s.matches("-?\\d+(\\.\\d+)?");
    }

    /**
     * Verifies if the String is an operator
     *
     * @param s
     * @return the string is an operator
     */
    public boolean isOperator(String s) {
        return s.equals("+") || s.equals("/") || s.equals("*") || s.equals("-");
    }

    /**
     * Verifies if the String is a left parenthesis
     *
     * @param s
     * @return the string is a left parenthesis
     */
    public boolean isLParen(String s) {
        return s.equals("(");
    }

    /**
     * Verifies if the String is a right parenthesis
     *
     * @param s
     * @return the string is a right parenthesis
     */
    public boolean isRParen(String s) {
        return s.equals(")");
    }

    /**
     * Verifies which operator has a higher precedence
     *
     * @param s
     * @return operator1 is higher or equal precedence to operator2
     */
    public boolean isHigherPrecedence(String operator1, String operator2) {
        return rankOperator(operator1) > rankOperator(operator2) || rankOperator(operator1) == rankOperator(operator2);
    }

    /**
     * Ranks operator by precedence
     * @param o
     * @return 1 or 2, according to precedence
     */
    public int rankOperator(String o) {
        int rank = 0;
        switch (o.charAt(0)) {
            case '+':
                rank = 1;
                break;
            case '-':
                rank = 1;
                break;
            case '/':
                rank = 2;
                break;
            case '*':
                rank = 2;
                break;
        }
        return rank;
    }

}
