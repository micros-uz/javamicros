package input;

public interface NativeKeyListenerEventSink {
    void notify(int key);
    void error(String err);
}
