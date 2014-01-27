package uz.micros;

public interface PluginEx extends Plugin {
    boolean allow(String code, CameraHost host);
}
