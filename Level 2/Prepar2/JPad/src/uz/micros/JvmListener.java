package uz.micros;

public interface JvmListener {
    void sendEvent(JvmEvent type, String data);
}
