//Haleigh Jayde Doetschman
//09.11.2019
//CMSC 451 Project 1

package sortmain;

import javax.swing.JOptionPane;

public class UnsortedException extends Exception {
    public UnsortedException(){
        JOptionPane.showMessageDialog(null, "Unsorted Exception Thrown");
    }
}
