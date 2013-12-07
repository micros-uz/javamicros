/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package consolehelpers;

import java.util.Scanner;

/**
 *
 * @author artem
 */
public class Input {
    
    private Scanner _scanner;
    
    public Input(){
        _scanner = new Scanner(System.in);
    }
    
    public void print(String s) {
        System.out.println(s);
    }

    public double getInteger() {
        String с = _scanner.nextLine();

        return Double.parseDouble(с);
    }
    
    public String getLine(){
        return _scanner.nextLine();
    }
}
