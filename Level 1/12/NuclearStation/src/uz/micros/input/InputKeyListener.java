package uz.micros.input;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

public class InputKeyListener implements NativeKeyListener {


    public void init(){
        try{
            GlobalScreen.registerNativeHook();
        }
        catch (NativeHookException ex) {
        }

        GlobalScreen.getInstance().addNativeKeyListener(this);
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent nativeKeyEvent) {
        int r = nativeKeyEvent.getKeyCode();

    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent nativeKeyEvent) {

    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent nativeKeyEvent) {

    }
}
