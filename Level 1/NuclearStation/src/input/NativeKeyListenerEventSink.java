package input;

interface NativeKeyListenerEventSink {
    void notify(int key);
    void error(String err);
}
