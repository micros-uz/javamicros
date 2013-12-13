package input;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

public class InputKeyListener implements NativeKeyListener {
    private NativeKeyListenerEventSink _eventSink;


    public InputKeyListener(NativeKeyListenerEventSink eventSink){
        _eventSink = eventSink;
    }

    public void Init(){

        try{
            GlobalScreen.registerNativeHook();
        }
        catch (NativeHookException ex) {
            _eventSink.error(ex.getMessage());
        }

        GlobalScreen.getInstance().addNativeKeyListener(this);
    }

    public void nativeKeyPressed(NativeKeyEvent e) {
        _eventSink.notify(e.getKeyCode());       
    }

    public void nativeKeyReleased(NativeKeyEvent e) {
    }

    public void nativeKeyTyped(NativeKeyEvent e) {
    }
           
}
