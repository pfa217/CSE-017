 /*
CSE 17
Pablo Aviles Ruiz
pfa217
Program Description: Graphical Calculator
*/

package prog10;

import java.util.ArrayList;

public class Stack {

    private int size = 0;
    private ArrayList<Object> list = new ArrayList<>();

    public Stack() {
        
    }
    
    /**
     * Pushes number into the stack
     * @param num 
     */
    public void push(Object e) {
        size++;
        list.add(e);
    }

    /**
     * Takes number from the stack
     * @return Number
     * @throws IllegalArgumentException 
     */
    public Object pop() throws IllegalArgumentException {
        if (size > 0) {
            Object num = list.remove(size - 1);
            size--;
            return num;
        } else {
            throw new IllegalArgumentException("Stack is Empty");
        }
    }

    /**
     * Returns number from the top of the stack without taking it out
     * @return Number
     */
    public Object peek() {
        if(size>0){
            return list.get(size-1);
        }
        else{
            return 0.0;
        }
    }

    /**
     * Checks if stack is empty
     * @return The stack is empty
     */
    public boolean isEmpty() {
        return size == 0;
    }
    
    /**
     * Clears the stack
     */
    public void clear(){
        list.removeAll(list);
        size = 0;
    }
}
