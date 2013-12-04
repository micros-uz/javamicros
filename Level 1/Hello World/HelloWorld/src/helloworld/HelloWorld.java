/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//package helloworld;

import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author admin
 */
public class HelloWorld {

    public static void main(String[] args) throws IOException {
        System.out.println("Hello everybody!");

        Scanner input = new Scanner( System.in );
        
        String name = input.next( );
        
        System.out.println("Hi, " + name);
    	
        System.in.read();
    }
    
}
