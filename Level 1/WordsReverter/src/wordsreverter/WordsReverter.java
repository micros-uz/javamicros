/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package wordsreverter;

import java.util.Scanner;

/**
 *
 * @author artem
 */
public class WordsReverter {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Enter the word");
        
        Scanner scanner = new Scanner(System.in);
        
        String word = scanner.nextLine();

        for(int k = word.length() - 1; k >= 0;k--)
            System.out.print(word.charAt(k));

        scanner.nextLine();
    }
    
}
