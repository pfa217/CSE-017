/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prog10;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 *
 * @author Pablo
 */
public class InFixBinaryTreeConverter {

    List<String> inFix;
    List<String> stack;
    List<Node> btstack;
    String expression;

    public InFixBinaryTreeConverter() {
        inFix = new LinkedList<>();
        stack = new ArrayList<>();
        btstack = new ArrayList<>();
    }

    public void run(String e) {
        expression = e;
        createInFix();
        createBinaryTree();
    }

    private void createInFix() {
        String[] a;
        a = expression.split("\\s+");
        inFix.addAll(Arrays.asList(a));
    }
    
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
                        while (isOperator(inFix.get(0)) && isHigherPrecedence(stack.get(stack.size()-1), inFix.get(0))) {
                            Node n1 = btstack.get(0);
                            btstack.remove(0);
                            Node n2 = btstack.get(0);
                            btstack.remove(0);

                            Node n3 = new Node(stack.get(stack.size()-1), n1, n2);
                            btstack.add(n3);
                            stack.remove(stack.size()-1);
                        }
                        stack.add(inFix.get(0));
                        inFix.remove(0);
                    } else {
                        if (isRParen(inFix.get(0))) {
                            while (!isLParen(stack.get(stack.size()-1))) {
                                Node n1 = btstack.get(0);
                                btstack.remove(0);
                                Node n2 = btstack.get(0);
                                btstack.remove(0);

                                Node n3 = new Node(stack.get(stack.size()-1), n1, n2);
                                btstack.add(n3);
                                //stack.add(inFix.get(0));
                                inFix.remove(0);
                                stack.remove(stack.size()-1);
                            }
                            stack.remove(stack.size()-1);
                        }
                    }
                }
            }
        }
    }

    public Node optimize()
    
    public boolean isNumber(String s) {
        return s.matches("-?\\d+(\\.\\d+)?");
    }

    public boolean isOperator(String s) {
        return s.equals("+") || s.equals("/") || s.equals("*") || s.equals("-");
    }

    public boolean isLParen(String s) {
        return s.equals("(");
    }

    public boolean isRParen(String s) {
        return s.equals(")");
    }

    public boolean isHigherPrecedence(String operator1, String operator2) {
        return rankOperator(operator1) > rankOperator(operator2) || rankOperator(operator1) == rankOperator(operator2);
    }

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
