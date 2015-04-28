/*
CSE 17
Pablo Aviles
pfa217
Program Description: Creates a binary tree to solve arithmetic expressions
Program #10
*/

package prog10;

public class Prog10 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        InFixBinaryTreeConverter fp = new InFixBinaryTreeConverter();
        fp.run("( ( 6 + 2 ) - 5 ) * 8 / 2");
    }
    
}
