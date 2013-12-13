package input;

import java.util.Scanner;
import org.jnativehook.keyboard.NativeKeyEvent;
/**
 *
 * @author artem
 */
public class Input implements NativeKeyListenerEventSink, OutputConsole {
    private final Scanner _scanner = new Scanner(System.in);
    private final InputKeyListener _keyListener;
    private int _key;
    private volatile boolean _catched;
    
    
    public Input(){
        _keyListener = new InputKeyListener(this);
        _keyListener.Init();
    }
    
    public void println(String s){
        System.out.println(s);
    }
    
    public String readln(){
        return _scanner.nextLine();
    }
    
    @SuppressWarnings("empty-statement")
    public int readKey(){
        _catched = false;
        while(!_catched);

        return _key;
    }

    @Override
    public void notify(int key) {
        _key = key;
        _catched = true;
    }

    @Override
    public void error(String err) {
        System.err.println("There was a problem registering the native hook.");
        System.err.println(err);

        System.exit(1);
    }

}
