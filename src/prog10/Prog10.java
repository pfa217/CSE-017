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
public class Prog10 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        InFixBinaryTreeConverter fp = new InFixBinaryTreeConverter();
        fp.run("( ( 6 + 2 ) - 5 ) * 8 / 2");
        //fp.run("( 14 + 12 ) * ( 5 - 2 )");
        
        
//        
//        Node n1L = new Node("5", null, null);
//        Node n1R = new Node("6", null, null);
//        Node m1L = new Node("10", null, null);
//        Node m1R = new Node("8", null, null);
//        Node n2L = new Node("+", n1L, n1R);
//        Node n2R = new Node("-", m1L, m1R);
//        Node root = new Node("*", n2L, n2R);
//        
//        System.out.println(root.toString());
    }
    
}
