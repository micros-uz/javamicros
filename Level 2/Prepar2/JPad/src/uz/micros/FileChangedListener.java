package uz.micros;

public interface FileChangedListener {
    void fileChanged(String path, long size);
}
