/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prog10;

/**
 *
 * @author Pablo
 */
public class Node {

    String element;
    Node left;
    Node right;


    public Node(String o) {
        element = o;
    }

    public Node(String o, Node left, Node right) {
        this.left = left;
        this.right = right;
        element = o;
    }

    @Override
    public String toString() {
        return toString0(this);
    }

    public String toString0(Node node) {
        String expression = "";
        expression += node.element;
        if (node.left == null) {
            return expression;
        } else {
            expression += "(" + toString0(node.left) + ",";
            expression += toString0(node.right) + ")";
        }
        return expression;
    }
}
