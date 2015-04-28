/*
CSE 17
Pablo Aviles
pfa217
Program Description: Creates a binary tree to solve arithmetic expressions
Program #10
*/

package prog10;

public class Node {

    String element;
    Node left;
    Node right;


    public Node(String o) {
        element = o;
    }

    /**
     * Node constructor
     * @param o
     * @param left
     * @param right 
     */
    public Node(String o, Node left, Node right) {
        this.left = left;
        this.right = right;
        element = o;
    }

    /**
     * Overrides toString by calling toString0()
     * @return 
     */
    @Override
    public String toString() {
        return toString0(this);
    }

    /**
     * Generates a String for the binary tree expression
     * @param node
     * @return 
     */
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
