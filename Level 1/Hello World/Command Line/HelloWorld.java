import java.util.Scanner;
import java.io.IOException;

public class HelloWorld {
    public static void main(String[] args) throws IOException{
        System.out.println("Hello everybody!");

        Scanner input = new Scanner( System.in );

        String name = input.next( );

        System.out.println("Hi, " + name);

    	System.in.read();
    }    
}